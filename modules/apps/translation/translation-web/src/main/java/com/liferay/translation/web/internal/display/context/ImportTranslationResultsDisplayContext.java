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

package com.liferay.translation.web.internal.display.context;

import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alicia García
 */
public class ImportTranslationResultsDisplayContext implements Serializable {

	public ImportTranslationResultsDisplayContext(
		long classNameId, long classPK, long groupId,
		Map<String, String> failureMessages, String fileName,
		List<String> successMessages, String title) {

		_classNameId = classNameId;
		_classPK = classPK;
		_groupId = groupId;
		_failureMessages = failureMessages;
		_fileName = fileName;
		_successMessages = successMessages;
		_title = title;
	}

	public Map<String, String> getFailureMessages() {
		return _failureMessages;
	}

	public int getFailureMessagesCount() {
		return _failureMessages.size();
	}

	public String getFileName() {
		return _fileName;
	}

	public String getImportTranslationURL(
		HttpServletRequest httpServletRequest,
		LiferayPortletResponse liferayPortletResponse) {

		return PortletURLBuilder.createRenderURL(
			liferayPortletResponse
		).setMVCRenderCommandName(
			"/translation/import_translation"
		).setRedirect(
			getRedirect(httpServletRequest)
		).setParameter(
			"classNameId", _classNameId
		).setParameter(
			"classPK", _classPK
		).setParameter(
			"groupId", _groupId
		).buildString();
	}

	public String getRedirect(HttpServletRequest httpServletRequest) {
		if (Validator.isNotNull(_redirect)) {
			return _redirect;
		}

		_redirect = ParamUtil.getString(httpServletRequest, "redirect");

		return _redirect;
	}

	public List<String> getSuccessMessages() {
		return _successMessages;
	}

	public int getSuccessMessagesCount() {
		return _successMessages.size();
	}

	public String getTitle() {
		return _title;
	}

	private final long _classNameId;
	private final long _classPK;
	private final Map<String, String> _failureMessages;
	private final String _fileName;
	private final long _groupId;
	private String _redirect;
	private final List<String> _successMessages;
	private final String _title;

}