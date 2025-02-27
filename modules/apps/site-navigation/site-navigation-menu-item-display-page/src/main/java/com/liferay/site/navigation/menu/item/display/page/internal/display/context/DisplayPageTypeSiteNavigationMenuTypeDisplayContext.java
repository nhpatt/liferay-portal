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

package com.liferay.site.navigation.menu.item.display.page.internal.display.context;

import com.liferay.info.item.InfoItemFormVariation;
import com.liferay.info.item.provider.InfoItemFormVariationsProvider;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.criteria.InfoItemItemSelectorReturnType;
import com.liferay.item.selector.criteria.info.item.criterion.InfoItemItemSelectorCriterion;
import com.liferay.layout.display.page.LayoutDisplayPageObjectProvider;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.site.navigation.constants.SiteNavigationWebKeys;
import com.liferay.site.navigation.menu.item.display.page.internal.constants.SiteNavigationMenuItemTypeDisplayPageWebKeys;
import com.liferay.site.navigation.menu.item.display.page.internal.type.DisplayPageTypeContext;
import com.liferay.site.navigation.model.SiteNavigationMenuItem;

import java.util.Map;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lourdes Fernández Besada
 */
public class DisplayPageTypeSiteNavigationMenuTypeDisplayContext {

	public DisplayPageTypeSiteNavigationMenuTypeDisplayContext(
		HttpServletRequest httpServletRequest) {

		_displayPageTypeContext =
			(DisplayPageTypeContext)httpServletRequest.getAttribute(
				SiteNavigationMenuItemTypeDisplayPageWebKeys.
					DISPLAY_PAGE_TYPE_CONTEXT);
		_itemSelector = (ItemSelector)httpServletRequest.getAttribute(
			SiteNavigationMenuItemTypeDisplayPageWebKeys.ITEM_SELECTOR);
		_siteNavigationMenuItem =
			(SiteNavigationMenuItem)httpServletRequest.getAttribute(
				SiteNavigationWebKeys.SITE_NAVIGATION_MENU_ITEM);
		_themeDisplay = (ThemeDisplay)httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public Map<String, Object> getChooseInfoItemButtonContext(
		HttpServletRequest httpServletRequest,
		LiferayPortletResponse liferayPortletResponse) {

		return HashMapBuilder.<String, Object>put(
			"eventName",
			liferayPortletResponse.getNamespace() + "selectInfoItem"
		).put(
			"getItemTypeURL", getItemTypeURL(liferayPortletResponse)
		).put(
			"itemSelectorURL",
			() -> {
				InfoItemItemSelectorCriterion itemSelectorCriterion =
					new InfoItemItemSelectorCriterion();

				itemSelectorCriterion.setDesiredItemSelectorReturnTypes(
					new InfoItemItemSelectorReturnType());
				itemSelectorCriterion.setItemType(
					_displayPageTypeContext.getClassName());

				RequestBackedPortletURLFactory requestBackedPortletURLFactory =
					RequestBackedPortletURLFactoryUtil.create(
						httpServletRequest);

				PortletURL infoItemSelectorURL =
					_itemSelector.getItemSelectorURL(
						requestBackedPortletURLFactory,
						liferayPortletResponse.getNamespace() +
							"selectInfoItem",
						itemSelectorCriterion);

				if (infoItemSelectorURL == null) {
					return StringPool.BLANK;
				}

				return infoItemSelectorURL.toString();
			}
		).put(
			"modalTitle",
			LanguageUtil.format(
				_themeDisplay.getLocale(), "select-x",
				_displayPageTypeContext.getLabel(_themeDisplay.getLocale()))
		).build();
	}

	public long getClassNameId() {
		if (_classNameId != null) {
			return _classNameId;
		}

		UnicodeProperties typeSettingsUnicodeProperties =
			UnicodePropertiesBuilder.fastLoad(
				_siteNavigationMenuItem.getTypeSettings()
			).build();

		_classNameId = GetterUtil.getLong(
			typeSettingsUnicodeProperties.get("classNameId"));

		return _classNameId;
	}

	public long getClassPK() {
		if (_classPK != null) {
			return _classPK;
		}

		UnicodeProperties typeSettingsUnicodeProperties =
			UnicodePropertiesBuilder.fastLoad(
				_siteNavigationMenuItem.getTypeSettings()
			).build();

		_classPK = GetterUtil.getLong(
			typeSettingsUnicodeProperties.get("classPK"));

		return _classPK;
	}

	public long getClassTypeId() {
		if (_classTypeId != null) {
			return _classTypeId;
		}

		LayoutDisplayPageObjectProvider<?> layoutDisplayPageObjectProvider =
			_getLayoutDisplayPageObjectProvider();

		if (layoutDisplayPageObjectProvider != null) {
			_classTypeId = layoutDisplayPageObjectProvider.getClassTypeId();
		}
		else {
			UnicodeProperties typeSettingsUnicodeProperties =
				UnicodePropertiesBuilder.fastLoad(
					_siteNavigationMenuItem.getTypeSettings()
				).build();

			_classTypeId = GetterUtil.getLong(
				typeSettingsUnicodeProperties.get("classTypeId"));
		}

		return _classTypeId;
	}

	public String getItemSubtype() {
		InfoItemFormVariationsProvider<?> infoItemFormVariationsProvider =
			_displayPageTypeContext.getInfoItemFormVariationsProvider();

		if (infoItemFormVariationsProvider == null) {
			return StringPool.BLANK;
		}

		LayoutDisplayPageObjectProvider<?> layoutDisplayPageObjectProvider =
			_getLayoutDisplayPageObjectProvider();

		if (layoutDisplayPageObjectProvider == null) {
			return StringPool.BLANK;
		}

		InfoItemFormVariation infoItemFormVariation =
			infoItemFormVariationsProvider.getInfoItemFormVariation(
				layoutDisplayPageObjectProvider.getGroupId(),
				String.valueOf(getClassTypeId()));

		if (infoItemFormVariation != null) {
			return infoItemFormVariation.getLabel(_themeDisplay.getLocale());
		}

		return StringPool.BLANK;
	}

	public String getItemType() {
		return _displayPageTypeContext.getLabel(_themeDisplay.getLocale());
	}

	public String getItemTypeURL(
		LiferayPortletResponse liferayPortletResponse) {

		LiferayPortletURL itemTypeURL =
			(LiferayPortletURL)liferayPortletResponse.createResourceURL();

		itemTypeURL.setCopyCurrentRenderParameters(false);
		itemTypeURL.setResourceID("/navigation_menu/get_item_type");

		return itemTypeURL.toString();
	}

	public String getOriginalTitle() {
		if (Validator.isNotNull(_originalTitle)) {
			return _originalTitle;
		}

		LayoutDisplayPageObjectProvider<?> layoutDisplayPageObjectProvider =
			_getLayoutDisplayPageObjectProvider();

		if (layoutDisplayPageObjectProvider == null) {
			UnicodeProperties typeSettingsUnicodeProperties =
				UnicodePropertiesBuilder.fastLoad(
					_siteNavigationMenuItem.getTypeSettings()
				).build();

			_originalTitle = typeSettingsUnicodeProperties.getProperty("title");
		}
		else {
			_originalTitle = layoutDisplayPageObjectProvider.getTitle(
				_themeDisplay.getLocale());
		}

		return _originalTitle;
	}

	public String getTitle() {
		if (Validator.isNotNull(_title)) {
			return _title;
		}

		UnicodeProperties typeSettingsUnicodeProperties =
			UnicodePropertiesBuilder.fastLoad(
				_siteNavigationMenuItem.getTypeSettings()
			).build();

		_title = typeSettingsUnicodeProperties.get("title");

		return _title;
	}

	public String getType() {
		if (Validator.isNotNull(_type)) {
			return _type;
		}

		UnicodeProperties typeSettingsUnicodeProperties =
			UnicodePropertiesBuilder.fastLoad(
				_siteNavigationMenuItem.getTypeSettings()
			).build();

		_type = typeSettingsUnicodeProperties.get("type");

		return _type;
	}

	private LayoutDisplayPageObjectProvider<?>
		_getLayoutDisplayPageObjectProvider() {

		if (_layoutDisplayPageObjectProvider != null) {
			return _layoutDisplayPageObjectProvider;
		}

		_layoutDisplayPageObjectProvider =
			_displayPageTypeContext.getLayoutDisplayPageObjectProvider(
				getClassPK());

		return _layoutDisplayPageObjectProvider;
	}

	private Long _classNameId;
	private Long _classPK;
	private Long _classTypeId;
	private final DisplayPageTypeContext _displayPageTypeContext;
	private final ItemSelector _itemSelector;
	private LayoutDisplayPageObjectProvider<?> _layoutDisplayPageObjectProvider;
	private String _originalTitle;
	private final SiteNavigationMenuItem _siteNavigationMenuItem;
	private final ThemeDisplay _themeDisplay;
	private String _title;
	private String _type;

}