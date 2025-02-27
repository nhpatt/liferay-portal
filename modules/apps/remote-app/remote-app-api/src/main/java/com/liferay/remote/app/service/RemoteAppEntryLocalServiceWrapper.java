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

package com.liferay.remote.app.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link RemoteAppEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see RemoteAppEntryLocalService
 * @generated
 */
public class RemoteAppEntryLocalServiceWrapper
	implements RemoteAppEntryLocalService,
			   ServiceWrapper<RemoteAppEntryLocalService> {

	public RemoteAppEntryLocalServiceWrapper(
		RemoteAppEntryLocalService remoteAppEntryLocalService) {

		_remoteAppEntryLocalService = remoteAppEntryLocalService;
	}

	@Override
	public com.liferay.remote.app.model.RemoteAppEntry
			addCustomElementRemoteAppEntry(
				long userId, String customElementCSSURLs,
				String customElementHTMLElementName, String customElementURLs,
				String description, String friendlyURLMapping,
				boolean instanceable,
				java.util.Map<java.util.Locale, String> nameMap,
				String portletCategoryName, String properties,
				String sourceCodeURL, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remoteAppEntryLocalService.addCustomElementRemoteAppEntry(
			userId, customElementCSSURLs, customElementHTMLElementName,
			customElementURLs, description, friendlyURLMapping, instanceable,
			nameMap, portletCategoryName, properties, sourceCodeURL, status);
	}

	@Override
	public com.liferay.remote.app.model.RemoteAppEntry addIFrameRemoteAppEntry(
			long userId, String description, String friendlyURLMapping,
			String iFrameURL, boolean instanceable,
			java.util.Map<java.util.Locale, String> nameMap,
			String portletCategoryName, String properties, String sourceCodeURL)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remoteAppEntryLocalService.addIFrameRemoteAppEntry(
			userId, description, friendlyURLMapping, iFrameURL, instanceable,
			nameMap, portletCategoryName, properties, sourceCodeURL);
	}

	/**
	 * Adds the remote app entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RemoteAppEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param remoteAppEntry the remote app entry
	 * @return the remote app entry that was added
	 */
	@Override
	public com.liferay.remote.app.model.RemoteAppEntry addRemoteAppEntry(
		com.liferay.remote.app.model.RemoteAppEntry remoteAppEntry) {

		return _remoteAppEntryLocalService.addRemoteAppEntry(remoteAppEntry);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remoteAppEntryLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new remote app entry with the primary key. Does not add the remote app entry to the database.
	 *
	 * @param remoteAppEntryId the primary key for the new remote app entry
	 * @return the new remote app entry
	 */
	@Override
	public com.liferay.remote.app.model.RemoteAppEntry createRemoteAppEntry(
		long remoteAppEntryId) {

		return _remoteAppEntryLocalService.createRemoteAppEntry(
			remoteAppEntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remoteAppEntryLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the remote app entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RemoteAppEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param remoteAppEntryId the primary key of the remote app entry
	 * @return the remote app entry that was removed
	 * @throws PortalException if a remote app entry with the primary key could not be found
	 */
	@Override
	public com.liferay.remote.app.model.RemoteAppEntry deleteRemoteAppEntry(
			long remoteAppEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remoteAppEntryLocalService.deleteRemoteAppEntry(
			remoteAppEntryId);
	}

	/**
	 * Deletes the remote app entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RemoteAppEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param remoteAppEntry the remote app entry
	 * @return the remote app entry that was removed
	 * @throws PortalException
	 */
	@Override
	public com.liferay.remote.app.model.RemoteAppEntry deleteRemoteAppEntry(
			com.liferay.remote.app.model.RemoteAppEntry remoteAppEntry)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remoteAppEntryLocalService.deleteRemoteAppEntry(remoteAppEntry);
	}

	@Override
	public void deployRemoteAppEntry(
		com.liferay.remote.app.model.RemoteAppEntry remoteAppEntry) {

		_remoteAppEntryLocalService.deployRemoteAppEntry(remoteAppEntry);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _remoteAppEntryLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _remoteAppEntryLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _remoteAppEntryLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _remoteAppEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.remote.app.model.impl.RemoteAppEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _remoteAppEntryLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.remote.app.model.impl.RemoteAppEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _remoteAppEntryLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _remoteAppEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _remoteAppEntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.remote.app.model.RemoteAppEntry fetchRemoteAppEntry(
		long remoteAppEntryId) {

		return _remoteAppEntryLocalService.fetchRemoteAppEntry(
			remoteAppEntryId);
	}

	/**
	 * Returns the remote app entry with the matching UUID and company.
	 *
	 * @param uuid the remote app entry's UUID
	 * @param companyId the primary key of the company
	 * @return the matching remote app entry, or <code>null</code> if a matching remote app entry could not be found
	 */
	@Override
	public com.liferay.remote.app.model.RemoteAppEntry
		fetchRemoteAppEntryByUuidAndCompanyId(String uuid, long companyId) {

		return _remoteAppEntryLocalService.
			fetchRemoteAppEntryByUuidAndCompanyId(uuid, companyId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _remoteAppEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _remoteAppEntryLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _remoteAppEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _remoteAppEntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remoteAppEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns a range of all the remote app entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.remote.app.model.impl.RemoteAppEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of remote app entries
	 * @param end the upper bound of the range of remote app entries (not inclusive)
	 * @return the range of remote app entries
	 */
	@Override
	public java.util.List<com.liferay.remote.app.model.RemoteAppEntry>
		getRemoteAppEntries(int start, int end) {

		return _remoteAppEntryLocalService.getRemoteAppEntries(start, end);
	}

	/**
	 * Returns the number of remote app entries.
	 *
	 * @return the number of remote app entries
	 */
	@Override
	public int getRemoteAppEntriesCount() {
		return _remoteAppEntryLocalService.getRemoteAppEntriesCount();
	}

	@Override
	public int getRemoteAppEntriesCount(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remoteAppEntryLocalService.getRemoteAppEntriesCount(companyId);
	}

	/**
	 * Returns the remote app entry with the primary key.
	 *
	 * @param remoteAppEntryId the primary key of the remote app entry
	 * @return the remote app entry
	 * @throws PortalException if a remote app entry with the primary key could not be found
	 */
	@Override
	public com.liferay.remote.app.model.RemoteAppEntry getRemoteAppEntry(
			long remoteAppEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remoteAppEntryLocalService.getRemoteAppEntry(remoteAppEntryId);
	}

	/**
	 * Returns the remote app entry with the matching UUID and company.
	 *
	 * @param uuid the remote app entry's UUID
	 * @param companyId the primary key of the company
	 * @return the matching remote app entry
	 * @throws PortalException if a matching remote app entry could not be found
	 */
	@Override
	public com.liferay.remote.app.model.RemoteAppEntry
			getRemoteAppEntryByUuidAndCompanyId(String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remoteAppEntryLocalService.getRemoteAppEntryByUuidAndCompanyId(
			uuid, companyId);
	}

	@Override
	public java.util.List<com.liferay.remote.app.model.RemoteAppEntry> search(
			long companyId, String keywords, int start, int end,
			com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remoteAppEntryLocalService.search(
			companyId, keywords, start, end, sort);
	}

	@Override
	public int searchCount(long companyId, String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remoteAppEntryLocalService.searchCount(companyId, keywords);
	}

	@Override
	public void undeployRemoteAppEntry(
		com.liferay.remote.app.model.RemoteAppEntry remoteAppEntry) {

		_remoteAppEntryLocalService.undeployRemoteAppEntry(remoteAppEntry);
	}

	@Override
	public com.liferay.remote.app.model.RemoteAppEntry
			updateCustomElementRemoteAppEntry(
				long remoteAppEntryId, String customElementCSSURLs,
				String customElementHTMLElementName, String customElementURLs,
				String description, String friendlyURLMapping,
				java.util.Map<java.util.Locale, String> nameMap,
				String portletCategoryName, String properties,
				String sourceCodeURL)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remoteAppEntryLocalService.updateCustomElementRemoteAppEntry(
			remoteAppEntryId, customElementCSSURLs,
			customElementHTMLElementName, customElementURLs, description,
			friendlyURLMapping, nameMap, portletCategoryName, properties,
			sourceCodeURL);
	}

	@Override
	public com.liferay.remote.app.model.RemoteAppEntry
			updateIFrameRemoteAppEntry(
				long remoteAppEntryId, String description,
				String friendlyURLMapping, String iFrameURL,
				java.util.Map<java.util.Locale, String> nameMap,
				String portletCategoryName, String properties,
				String sourceCodeURL)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remoteAppEntryLocalService.updateIFrameRemoteAppEntry(
			remoteAppEntryId, description, friendlyURLMapping, iFrameURL,
			nameMap, portletCategoryName, properties, sourceCodeURL);
	}

	/**
	 * Updates the remote app entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RemoteAppEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param remoteAppEntry the remote app entry
	 * @return the remote app entry that was updated
	 */
	@Override
	public com.liferay.remote.app.model.RemoteAppEntry updateRemoteAppEntry(
		com.liferay.remote.app.model.RemoteAppEntry remoteAppEntry) {

		return _remoteAppEntryLocalService.updateRemoteAppEntry(remoteAppEntry);
	}

	@Override
	public com.liferay.remote.app.model.RemoteAppEntry updateStatus(
			long userId, long remoteAppEntryId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _remoteAppEntryLocalService.updateStatus(
			userId, remoteAppEntryId, status);
	}

	@Override
	public RemoteAppEntryLocalService getWrappedService() {
		return _remoteAppEntryLocalService;
	}

	@Override
	public void setWrappedService(
		RemoteAppEntryLocalService remoteAppEntryLocalService) {

		_remoteAppEntryLocalService = remoteAppEntryLocalService;
	}

	private RemoteAppEntryLocalService _remoteAppEntryLocalService;

}