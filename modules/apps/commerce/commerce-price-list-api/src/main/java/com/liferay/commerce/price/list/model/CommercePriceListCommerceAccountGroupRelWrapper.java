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

package com.liferay.commerce.price.list.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CommercePriceListCommerceAccountGroupRel}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommercePriceListCommerceAccountGroupRel
 * @generated
 */
public class CommercePriceListCommerceAccountGroupRelWrapper
	extends BaseModelWrapper<CommercePriceListCommerceAccountGroupRel>
	implements CommercePriceListCommerceAccountGroupRel,
			   ModelWrapper<CommercePriceListCommerceAccountGroupRel> {

	public CommercePriceListCommerceAccountGroupRelWrapper(
		CommercePriceListCommerceAccountGroupRel
			commercePriceListCommerceAccountGroupRel) {

		super(commercePriceListCommerceAccountGroupRel);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("uuid", getUuid());
		attributes.put(
			"commercePriceListCommerceAccountGroupRelId",
			getCommercePriceListCommerceAccountGroupRelId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("commercePriceListId", getCommercePriceListId());
		attributes.put("commerceAccountGroupId", getCommerceAccountGroupId());
		attributes.put("order", getOrder());
		attributes.put("lastPublishDate", getLastPublishDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long commercePriceListCommerceAccountGroupRelId = (Long)attributes.get(
			"commercePriceListCommerceAccountGroupRelId");

		if (commercePriceListCommerceAccountGroupRelId != null) {
			setCommercePriceListCommerceAccountGroupRelId(
				commercePriceListCommerceAccountGroupRelId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long commercePriceListId = (Long)attributes.get("commercePriceListId");

		if (commercePriceListId != null) {
			setCommercePriceListId(commercePriceListId);
		}

		Long commerceAccountGroupId = (Long)attributes.get(
			"commerceAccountGroupId");

		if (commerceAccountGroupId != null) {
			setCommerceAccountGroupId(commerceAccountGroupId);
		}

		Integer order = (Integer)attributes.get("order");

		if (order != null) {
			setOrder(order);
		}

		Date lastPublishDate = (Date)attributes.get("lastPublishDate");

		if (lastPublishDate != null) {
			setLastPublishDate(lastPublishDate);
		}
	}

	@Override
	public CommercePriceListCommerceAccountGroupRel cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	@Override
	public com.liferay.commerce.account.model.CommerceAccountGroup
			getCommerceAccountGroup()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getCommerceAccountGroup();
	}

	/**
	 * Returns the commerce account group ID of this commerce price list commerce account group rel.
	 *
	 * @return the commerce account group ID of this commerce price list commerce account group rel
	 */
	@Override
	public long getCommerceAccountGroupId() {
		return model.getCommerceAccountGroupId();
	}

	@Override
	public CommercePriceList getCommercePriceList()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getCommercePriceList();
	}

	/**
	 * Returns the commerce price list commerce account group rel ID of this commerce price list commerce account group rel.
	 *
	 * @return the commerce price list commerce account group rel ID of this commerce price list commerce account group rel
	 */
	@Override
	public long getCommercePriceListCommerceAccountGroupRelId() {
		return model.getCommercePriceListCommerceAccountGroupRelId();
	}

	/**
	 * Returns the commerce price list ID of this commerce price list commerce account group rel.
	 *
	 * @return the commerce price list ID of this commerce price list commerce account group rel
	 */
	@Override
	public long getCommercePriceListId() {
		return model.getCommercePriceListId();
	}

	/**
	 * Returns the company ID of this commerce price list commerce account group rel.
	 *
	 * @return the company ID of this commerce price list commerce account group rel
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this commerce price list commerce account group rel.
	 *
	 * @return the create date of this commerce price list commerce account group rel
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the last publish date of this commerce price list commerce account group rel.
	 *
	 * @return the last publish date of this commerce price list commerce account group rel
	 */
	@Override
	public Date getLastPublishDate() {
		return model.getLastPublishDate();
	}

	/**
	 * Returns the modified date of this commerce price list commerce account group rel.
	 *
	 * @return the modified date of this commerce price list commerce account group rel
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this commerce price list commerce account group rel.
	 *
	 * @return the mvcc version of this commerce price list commerce account group rel
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the order of this commerce price list commerce account group rel.
	 *
	 * @return the order of this commerce price list commerce account group rel
	 */
	@Override
	public int getOrder() {
		return model.getOrder();
	}

	/**
	 * Returns the primary key of this commerce price list commerce account group rel.
	 *
	 * @return the primary key of this commerce price list commerce account group rel
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this commerce price list commerce account group rel.
	 *
	 * @return the user ID of this commerce price list commerce account group rel
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this commerce price list commerce account group rel.
	 *
	 * @return the user name of this commerce price list commerce account group rel
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this commerce price list commerce account group rel.
	 *
	 * @return the user uuid of this commerce price list commerce account group rel
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this commerce price list commerce account group rel.
	 *
	 * @return the uuid of this commerce price list commerce account group rel
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the commerce account group ID of this commerce price list commerce account group rel.
	 *
	 * @param commerceAccountGroupId the commerce account group ID of this commerce price list commerce account group rel
	 */
	@Override
	public void setCommerceAccountGroupId(long commerceAccountGroupId) {
		model.setCommerceAccountGroupId(commerceAccountGroupId);
	}

	/**
	 * Sets the commerce price list commerce account group rel ID of this commerce price list commerce account group rel.
	 *
	 * @param commercePriceListCommerceAccountGroupRelId the commerce price list commerce account group rel ID of this commerce price list commerce account group rel
	 */
	@Override
	public void setCommercePriceListCommerceAccountGroupRelId(
		long commercePriceListCommerceAccountGroupRelId) {

		model.setCommercePriceListCommerceAccountGroupRelId(
			commercePriceListCommerceAccountGroupRelId);
	}

	/**
	 * Sets the commerce price list ID of this commerce price list commerce account group rel.
	 *
	 * @param commercePriceListId the commerce price list ID of this commerce price list commerce account group rel
	 */
	@Override
	public void setCommercePriceListId(long commercePriceListId) {
		model.setCommercePriceListId(commercePriceListId);
	}

	/**
	 * Sets the company ID of this commerce price list commerce account group rel.
	 *
	 * @param companyId the company ID of this commerce price list commerce account group rel
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this commerce price list commerce account group rel.
	 *
	 * @param createDate the create date of this commerce price list commerce account group rel
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the last publish date of this commerce price list commerce account group rel.
	 *
	 * @param lastPublishDate the last publish date of this commerce price list commerce account group rel
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		model.setLastPublishDate(lastPublishDate);
	}

	/**
	 * Sets the modified date of this commerce price list commerce account group rel.
	 *
	 * @param modifiedDate the modified date of this commerce price list commerce account group rel
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this commerce price list commerce account group rel.
	 *
	 * @param mvccVersion the mvcc version of this commerce price list commerce account group rel
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the order of this commerce price list commerce account group rel.
	 *
	 * @param order the order of this commerce price list commerce account group rel
	 */
	@Override
	public void setOrder(int order) {
		model.setOrder(order);
	}

	/**
	 * Sets the primary key of this commerce price list commerce account group rel.
	 *
	 * @param primaryKey the primary key of this commerce price list commerce account group rel
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this commerce price list commerce account group rel.
	 *
	 * @param userId the user ID of this commerce price list commerce account group rel
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this commerce price list commerce account group rel.
	 *
	 * @param userName the user name of this commerce price list commerce account group rel
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this commerce price list commerce account group rel.
	 *
	 * @param userUuid the user uuid of this commerce price list commerce account group rel
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this commerce price list commerce account group rel.
	 *
	 * @param uuid the uuid of this commerce price list commerce account group rel
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected CommercePriceListCommerceAccountGroupRelWrapper wrap(
		CommercePriceListCommerceAccountGroupRel
			commercePriceListCommerceAccountGroupRel) {

		return new CommercePriceListCommerceAccountGroupRelWrapper(
			commercePriceListCommerceAccountGroupRel);
	}

}