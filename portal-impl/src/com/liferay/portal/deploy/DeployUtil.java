/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.deploy;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.SystemProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.context.PortalContextLoaderListener;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.util.ant.CopyTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;

/**
 * @author Brian Wing Shun Chan
 */
public class DeployUtil {

	public static void copyDependencyXml(
			String fileName, String targetDir, String targetFileName,
			Map<String, String> filterMap, boolean overwrite)
		throws Exception {

		File targetFile = new File(targetDir, targetFileName);

		if (!targetFile.exists()) {
			Set<Path> tempPaths = new HashSet<>();

			File file = new File(getResourcePath(tempPaths, fileName));

			CopyTask.copyFile(
				file, new File(targetDir), targetFileName, filterMap, overwrite,
				true);

			for (Path tempPath : tempPaths) {
				deletePath(tempPath);
			}
		}
	}

	public static void deletePath(Path tempPath) throws IOException {
		Files.walkFileTree(
			tempPath,
			new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult postVisitDirectory(
						Path dirPath, IOException ioException)
					throws IOException {

					Files.delete(dirPath);

					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(
						Path filePath, BasicFileAttributes basicFileAttributes)
					throws IOException {

					Files.delete(filePath);

					return FileVisitResult.CONTINUE;
				}

			});
	}

	public static String getAutoDeployDestDir() throws Exception {
		String destDir = PropsValues.AUTO_DEPLOY_DEST_DIR;

		if (Validator.isNull(destDir)) {
			destDir = getAutoDeployServerDestDir();
		}

		FileUtil.mkdirs(destDir);

		return destDir;
	}

	public static String getAutoDeployServerDestDir() throws Exception {
		String destDir = null;

		String serverId = GetterUtil.getString(ServerDetector.getServerId());

		if (serverId.equals(ServerDetector.TOMCAT_ID)) {
			destDir = PropsValues.AUTO_DEPLOY_TOMCAT_DEST_DIR;
		}
		else {
			destDir = PrefsPropsUtil.getString(
				"auto.deploy." + serverId + ".dest.dir");
		}

		if (Validator.isNull(destDir)) {
			destDir = PropsValues.AUTO_DEPLOY_DEFAULT_DEST_DIR;
		}

		return StringUtil.replace(destDir, CharPool.BACK_SLASH, CharPool.SLASH);
	}

	public static String getResourcePath(Set<Path> tempPaths, String resource)
		throws Exception {

		return _deployUtil._getResourcePath(tempPaths, resource);
	}

	public static void redeployTomcat(String context) throws Exception {
		if (_isPortalContext(context)) {
			throw new UnsupportedOperationException(
				"This method is meant for redeploying plugins, not the portal");
		}

		File webXml = new File(
			getAutoDeployDestDir(), context + "/WEB-INF/web.xml");

		FileUtils.touch(webXml);
	}

	private static boolean _isPortalContext(String context) {
		if (Validator.isNull(context) || context.equals(StringPool.SLASH) ||
			context.equals(
				PortalContextLoaderListener.getPortalServletContextPath())) {

			return true;
		}

		return false;
	}

	private DeployUtil() {
	}

	private String _getResourcePath(Set<Path> tempDirPaths, String resource)
		throws Exception {

		Class<?> clazz = getClass();

		InputStream inputStream = clazz.getResourceAsStream(
			"dependencies/" + resource);

		if (inputStream == null) {
			return null;
		}

		Path tempDirPath = Files.createTempDirectory(
			Paths.get(SystemProperties.get(SystemProperties.TMP_DIR)), null);

		tempDirPaths.add(tempDirPath);

		File file = new File(
			tempDirPath + "/liferay/com/liferay/portal/deploy/dependencies/" +
				resource);

		File parentFile = file.getParentFile();

		if (parentFile != null) {
			FileUtil.mkdirs(parentFile);
		}

		StreamUtil.transfer(inputStream, new FileOutputStream(file));

		return FileUtil.getAbsolutePath(file);
	}

	private static final DeployUtil _deployUtil = new DeployUtil();

}