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

package com.liferay.dynamic.data.lists.internal.exportimport.staged.model.repository;

import com.liferay.dynamic.data.lists.constants.DDLRecordSetConstants;
import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.model.DDLRecordVersion;
import com.liferay.dynamic.data.lists.service.DDLRecordLocalService;
import com.liferay.dynamic.data.lists.service.DDLRecordVersionLocalService;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerRegistryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelModifiedDateComparator;
import com.liferay.exportimport.staged.model.repository.StagedModelRepository;
import com.liferay.exportimport.staged.model.repository.StagedModelRepositoryHelper;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Tamas Molnar
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.dynamic.data.lists.model.DDLRecord",
	service = {
		DDLRecordStagedModelRepository.class, StagedModelRepository.class
	}
)
public class DDLRecordStagedModelRepository
	implements StagedModelRepository<DDLRecord> {

	@Override
	public DDLRecord addStagedModel(
			PortletDataContext portletDataContext, DDLRecord ddlRecord)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	public DDLRecord addStagedModel(
			PortletDataContext portletDataContext, DDLRecord ddlRecord,
			DDMFormValues ddmFormValues)
		throws PortalException {

		long userId = portletDataContext.getUserId(ddlRecord.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			ddlRecord);

		if (portletDataContext.isDataStrategyMirror()) {
			serviceContext.setUuid(ddlRecord.getUuid());
		}

		DDLRecord importedRecord = _ddlRecordLocalService.addRecord(
			userId, ddlRecord.getGroupId(), ddlRecord.getRecordSetId(),
			ddlRecord.getDisplayIndex(), ddmFormValues, serviceContext);

		updateVersions(importedRecord, ddlRecord.getVersion());

		return importedRecord;
	}

	@Override
	public void deleteStagedModel(DDLRecord ddlRecord) throws PortalException {
		_ddlRecordLocalService.deleteRecord(ddlRecord);
	}

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		DDLRecord ddlRecord = fetchStagedModelByUuidAndGroupId(uuid, groupId);

		if (ddlRecord != null) {
			deleteStagedModel(ddlRecord);
		}
	}

	@Override
	public void deleteStagedModels(PortletDataContext portletDataContext)
		throws PortalException {
	}

	@Override
	public DDLRecord fetchMissingReference(String uuid, long groupId) {
		return _stagedModelRepositoryHelper.fetchMissingReference(
			uuid, groupId, this);
	}

	@Override
	public DDLRecord fetchStagedModelByUuidAndGroupId(
		String uuid, long groupId) {

		return _ddlRecordLocalService.fetchDDLRecordByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public List<DDLRecord> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return _ddlRecordLocalService.getDDLRecordsByUuidAndCompanyId(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new StagedModelModifiedDateComparator<DDLRecord>());
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext) {

		return getExportActionableDynamicQuery(
			portletDataContext, DDLRecordSetConstants.SCOPE_DYNAMIC_DATA_LISTS);
	}

	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext, int scope) {

		ExportActionableDynamicQuery exportActionableDynamicQuery =
			_ddlRecordLocalService.getExportActionableDynamicQuery(
				portletDataContext);

		ActionableDynamicQuery.AddCriteriaMethod addCriteriaMethod =
			exportActionableDynamicQuery.getAddCriteriaMethod();

		exportActionableDynamicQuery.setAddCriteriaMethod(
			dynamicQuery -> {
				addCriteriaMethod.addCriteria(dynamicQuery);

				Property recordIdProperty = PropertyFactoryUtil.forName(
					"recordId");

				dynamicQuery.add(
					recordIdProperty.in(getRecordVersionDynamicQuery()));

				Property recordSetIdProperty = PropertyFactoryUtil.forName(
					"recordSetId");

				dynamicQuery.add(
					recordSetIdProperty.in(getRecordSetDynamicQuery(scope)));
			});

		return exportActionableDynamicQuery;
	}

	@Override
	public DDLRecord getStagedModel(long recordId) throws PortalException {
		return _ddlRecordLocalService.getDDLRecord(recordId);
	}

	@Override
	public DDLRecord saveStagedModel(DDLRecord ddlRecord)
		throws PortalException {

		return _ddlRecordLocalService.updateDDLRecord(ddlRecord);
	}

	@Override
	public DDLRecord updateStagedModel(
			PortletDataContext portletDataContext, DDLRecord ddlRecord)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	public DDLRecord updateStagedModel(
			PortletDataContext portletDataContext, DDLRecord ddlRecord,
			DDMFormValues ddmFormValues)
		throws PortalException {

		long userId = portletDataContext.getUserId(ddlRecord.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			ddlRecord);

		DDLRecord importedRecord = _ddlRecordLocalService.updateRecord(
			userId, ddlRecord.getRecordId(), false, ddlRecord.getDisplayIndex(),
			ddmFormValues, serviceContext);

		updateVersions(importedRecord, ddlRecord.getVersion());

		return importedRecord;
	}

	protected DynamicQuery getRecordSetDynamicQuery(int scope) {
		StagedModelDataHandler<?> stagedModelDataHandler =
			StagedModelDataHandlerRegistryUtil.getStagedModelDataHandler(
				DDLRecord.class.getName());

		Class<?> clazz = stagedModelDataHandler.getClass();

		DynamicQuery recordSetDynamicQuery = DynamicQueryFactoryUtil.forClass(
			DDLRecordSet.class, "recordSet", clazz.getClassLoader());

		recordSetDynamicQuery.setProjection(
			ProjectionFactoryUtil.property("recordSetId"));

		recordSetDynamicQuery.add(
			RestrictionsFactoryUtil.eqProperty(
				"recordSet.recordSetId", "recordSetId"));

		Property scopeProperty = PropertyFactoryUtil.forName("scope");

		recordSetDynamicQuery.add(scopeProperty.eq(scope));

		return recordSetDynamicQuery;
	}

	protected DynamicQuery getRecordVersionDynamicQuery() {
		StagedModelDataHandler<?> stagedModelDataHandler =
			StagedModelDataHandlerRegistryUtil.getStagedModelDataHandler(
				DDLRecord.class.getName());

		Class<?> clazz = stagedModelDataHandler.getClass();

		DynamicQuery recordVersionDynamicQuery =
			DynamicQueryFactoryUtil.forClass(
				DDLRecordVersion.class, "recordVersion",
				clazz.getClassLoader());

		recordVersionDynamicQuery.setProjection(
			ProjectionFactoryUtil.property("recordId"));

		Property statusProperty = PropertyFactoryUtil.forName("status");

		recordVersionDynamicQuery.add(
			statusProperty.in(stagedModelDataHandler.getExportableStatuses()));

		recordVersionDynamicQuery.add(
			RestrictionsFactoryUtil.eqProperty(
				"recordVersion.version", "version"));
		recordVersionDynamicQuery.add(
			RestrictionsFactoryUtil.eqProperty(
				"recordVersion.recordId", "recordId"));

		return recordVersionDynamicQuery;
	}

	protected void updateVersions(DDLRecord importedRecord, String version)
		throws PortalException {

		if (Objects.equals(importedRecord.getVersion(), version)) {
			return;
		}

		DDLRecordVersion importedRecordVersion =
			importedRecord.getRecordVersion();

		importedRecordVersion.setVersion(version);

		_ddlRecordVersionLocalService.updateDDLRecordVersion(
			importedRecordVersion);

		importedRecord.setVersion(version);

		_ddlRecordLocalService.updateDDLRecord(importedRecord);
	}

	@Reference
	private DDLRecordLocalService _ddlRecordLocalService;

	@Reference
	private DDLRecordVersionLocalService _ddlRecordVersionLocalService;

	@Reference
	private StagedModelRepositoryHelper _stagedModelRepositoryHelper;

}