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

package com.liferay.screens.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.screens.service.ScreensCommentServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>ScreensCommentServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author José Manuel Navarro
 * @see ScreensCommentServiceHttp
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ScreensCommentServiceSoap {

	public static String addComment(String className, long classPK, String body)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				ScreensCommentServiceUtil.addComment(className, classPK, body);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String getComment(long commentId) throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				ScreensCommentServiceUtil.getComment(commentId);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String getComments(
			String className, long classPK, int start, int end)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONArray returnValue =
				ScreensCommentServiceUtil.getComments(
					className, classPK, start, end);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getCommentsCount(String className, long classPK)
		throws RemoteException {

		try {
			int returnValue = ScreensCommentServiceUtil.getCommentsCount(
				className, classPK);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String updateComment(long commentId, String body)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				ScreensCommentServiceUtil.updateComment(commentId, body);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ScreensCommentServiceSoap.class);

}