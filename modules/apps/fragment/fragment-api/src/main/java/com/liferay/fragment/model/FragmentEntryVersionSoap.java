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

package com.liferay.fragment.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class FragmentEntryVersionSoap implements Serializable {

	public static FragmentEntryVersionSoap toSoapModel(
		FragmentEntryVersion model) {

		FragmentEntryVersionSoap soapModel = new FragmentEntryVersionSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setCtCollectionId(model.getCtCollectionId());
		soapModel.setFragmentEntryVersionId(model.getFragmentEntryVersionId());
		soapModel.setVersion(model.getVersion());
		soapModel.setUuid(model.getUuid());
		soapModel.setFragmentEntryId(model.getFragmentEntryId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setFragmentCollectionId(model.getFragmentCollectionId());
		soapModel.setFragmentEntryKey(model.getFragmentEntryKey());
		soapModel.setName(model.getName());
		soapModel.setCss(model.getCss());
		soapModel.setHtml(model.getHtml());
		soapModel.setJs(model.getJs());
		soapModel.setCacheable(model.isCacheable());
		soapModel.setConfiguration(model.getConfiguration());
		soapModel.setIcon(model.getIcon());
		soapModel.setPreviewFileEntryId(model.getPreviewFileEntryId());
		soapModel.setReadOnly(model.isReadOnly());
		soapModel.setType(model.getType());
		soapModel.setLastPublishDate(model.getLastPublishDate());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());

		return soapModel;
	}

	public static FragmentEntryVersionSoap[] toSoapModels(
		FragmentEntryVersion[] models) {

		FragmentEntryVersionSoap[] soapModels =
			new FragmentEntryVersionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static FragmentEntryVersionSoap[][] toSoapModels(
		FragmentEntryVersion[][] models) {

		FragmentEntryVersionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new FragmentEntryVersionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new FragmentEntryVersionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static FragmentEntryVersionSoap[] toSoapModels(
		List<FragmentEntryVersion> models) {

		List<FragmentEntryVersionSoap> soapModels =
			new ArrayList<FragmentEntryVersionSoap>(models.size());

		for (FragmentEntryVersion model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new FragmentEntryVersionSoap[soapModels.size()]);
	}

	public FragmentEntryVersionSoap() {
	}

	public long getPrimaryKey() {
		return _fragmentEntryVersionId;
	}

	public void setPrimaryKey(long pk) {
		setFragmentEntryVersionId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getCtCollectionId() {
		return _ctCollectionId;
	}

	public void setCtCollectionId(long ctCollectionId) {
		_ctCollectionId = ctCollectionId;
	}

	public long getFragmentEntryVersionId() {
		return _fragmentEntryVersionId;
	}

	public void setFragmentEntryVersionId(long fragmentEntryVersionId) {
		_fragmentEntryVersionId = fragmentEntryVersionId;
	}

	public int getVersion() {
		return _version;
	}

	public void setVersion(int version) {
		_version = version;
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getFragmentEntryId() {
		return _fragmentEntryId;
	}

	public void setFragmentEntryId(long fragmentEntryId) {
		_fragmentEntryId = fragmentEntryId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getFragmentCollectionId() {
		return _fragmentCollectionId;
	}

	public void setFragmentCollectionId(long fragmentCollectionId) {
		_fragmentCollectionId = fragmentCollectionId;
	}

	public String getFragmentEntryKey() {
		return _fragmentEntryKey;
	}

	public void setFragmentEntryKey(String fragmentEntryKey) {
		_fragmentEntryKey = fragmentEntryKey;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getCss() {
		return _css;
	}

	public void setCss(String css) {
		_css = css;
	}

	public String getHtml() {
		return _html;
	}

	public void setHtml(String html) {
		_html = html;
	}

	public String getJs() {
		return _js;
	}

	public void setJs(String js) {
		_js = js;
	}

	public boolean getCacheable() {
		return _cacheable;
	}

	public boolean isCacheable() {
		return _cacheable;
	}

	public void setCacheable(boolean cacheable) {
		_cacheable = cacheable;
	}

	public String getConfiguration() {
		return _configuration;
	}

	public void setConfiguration(String configuration) {
		_configuration = configuration;
	}

	public String getIcon() {
		return _icon;
	}

	public void setIcon(String icon) {
		_icon = icon;
	}

	public long getPreviewFileEntryId() {
		return _previewFileEntryId;
	}

	public void setPreviewFileEntryId(long previewFileEntryId) {
		_previewFileEntryId = previewFileEntryId;
	}

	public boolean getReadOnly() {
		return _readOnly;
	}

	public boolean isReadOnly() {
		return _readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		_readOnly = readOnly;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	public String getStatusByUserName() {
		return _statusByUserName;
	}

	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	private long _mvccVersion;
	private long _ctCollectionId;
	private long _fragmentEntryVersionId;
	private int _version;
	private String _uuid;
	private long _fragmentEntryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _fragmentCollectionId;
	private String _fragmentEntryKey;
	private String _name;
	private String _css;
	private String _html;
	private String _js;
	private boolean _cacheable;
	private String _configuration;
	private String _icon;
	private long _previewFileEntryId;
	private boolean _readOnly;
	private int _type;
	private Date _lastPublishDate;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;

}