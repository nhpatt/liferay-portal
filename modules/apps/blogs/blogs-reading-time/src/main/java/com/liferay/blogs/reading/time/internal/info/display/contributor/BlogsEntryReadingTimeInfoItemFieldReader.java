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

package com.liferay.blogs.reading.time.internal.info.display.contributor;

import com.liferay.blogs.model.BlogsEntry;
import com.liferay.info.field.InfoField;
import com.liferay.info.field.type.TextInfoFieldType;
import com.liferay.info.item.field.reader.InfoItemFieldReader;
import com.liferay.info.localized.InfoLocalizedValue;
import com.liferay.reading.time.message.ReadingTimeMessageProvider;
import com.liferay.reading.time.model.ReadingTimeEntry;
import com.liferay.reading.time.service.ReadingTimeEntryLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alejandro Tardín
 */
@Component(service = InfoItemFieldReader.class)
public class BlogsEntryReadingTimeInfoItemFieldReader
	implements InfoItemFieldReader<BlogsEntry> {

	/**
	 *   @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 *          #getInfoField()}
	 */
	@Deprecated
	@Override
	public InfoField getField() {
		return getInfoField();
	}

	@Override
	public InfoField getInfoField() {
		return InfoField.builder(
		).infoFieldType(
			TextInfoFieldType.INSTANCE
		).name(
			"readingTime"
		).labelInfoLocalizedValue(
			InfoLocalizedValue.localize(getClass(), "descriptive-reading-time")
		).build();
	}

	@Override
	public Object getValue(BlogsEntry blogsEntry) {
		return InfoLocalizedValue.function(
			locale -> {
				ReadingTimeEntry readingTimeEntry =
					_readingTimeEntryLocalService.fetchOrAddReadingTimeEntry(
						blogsEntry);

				return _readingTimeMessageProvider.provide(
					readingTimeEntry, locale);
			});
	}

	@Reference
	private ReadingTimeEntryLocalService _readingTimeEntryLocalService;

	@Reference(target = "(display.style=descriptive)")
	private ReadingTimeMessageProvider _readingTimeMessageProvider;

}