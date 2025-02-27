/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import ClayAlert from '@clayui/alert';
import ClayButton from '@clayui/button';
import {Align} from '@clayui/drop-down';
import ClayEmptyState from '@clayui/empty-state';
import ClayIcon from '@clayui/icon';
import ClayList from '@clayui/list';
import ClayLoadingIndicator from '@clayui/loading-indicator';
import ClayManagementToolbar from '@clayui/management-toolbar';
import {ClayPaginationWithBasicItems} from '@clayui/pagination';
import ClayPaginationBar from '@clayui/pagination-bar';
import getCN from 'classnames';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

import ErrorListItem from '../shared/ErrorListItem';
import {PreviewModalWithCopyDownload} from '../shared/PreviewModal';
import SearchInput from '../shared/SearchInput';
import {sub} from '../utils/language';
import useDidUpdateEffect from '../utils/useDidUpdateEffect';
import {parseAndPrettifyJSON} from '../utils/utils';
import PreviewAttributesModal from './PreviewAttributesModal';
import ResultListItem from './ResultListItem';

const DELTAS = [10, 20, 30, 50];

function PreviewSidebar({
	loading,
	onFetchResults,
	onFocusSXPElement,
	onToggle,
	results,
	visible,
}) {
	const [activeDelta, setActiveDelta] = useState(10);
	const [activePage, setActivePage] = useState(1);
	const [attributes, setAttributes] = useState([]);
	const [value, setValue] = useState('');

	const _handleAttributesSubmit = (attributes) => {
		setAttributes(attributes);
	};

	const _handleFetch = () => {
		onFetchResults(value, activeDelta, activePage, attributes);
	};

	useDidUpdateEffect(() => {
		_handleFetch();
	}, [activeDelta, activePage]);

	const _handleDeltaChange = (delta) => () => {
		setActiveDelta(delta);
		setActivePage(1);
	};

	const _renderErrors = () => (
		<ClayList className="preview-error-list">
			{results.errors.map((error, index) => (
				<ErrorListItem
					item={error}
					key={index}
					onFocusSXPElement={onFocusSXPElement}
				/>
			))}
		</ClayList>
	);

	const _renderHits = () => (
		<div className="preview-results-list sidebar-body">
			<ClayList>
				{results?.response?.hits?.hits.map((result) => (
					<ResultListItem
						item={{...result, ...result.fields}}
						key={result._id}
					/>
				))}
			</ClayList>

			<ClayPaginationBar>
				<ClayPaginationBar.DropDown
					alignmentPosition={Align.TopLeft}
					items={DELTAS.map((delta) => ({
						label: delta,
						onClick: _handleDeltaChange(delta),
					}))}
					trigger={
						<ClayButton displayType="unstyled">
							{sub(Liferay.Language.get('x-entries'), [
								activeDelta,
							])}

							<ClayIcon symbol="caret-double-l" />
						</ClayButton>
					}
				/>

				<ClayPaginationBar.Results>
					{sub(Liferay.Language.get('showing-x-to-x-of-x-entries'), [
						(activePage - 1) * activeDelta + 1,
						activePage * activeDelta < results.totalHits
							? activePage * activeDelta
							: results.totalHits,
						results.totalHits,
					])}
				</ClayPaginationBar.Results>

				<ClayPaginationWithBasicItems
					activePage={activePage}
					alignmentPosition={Align.TopCenter}
					ellipsisBuffer={1}
					onPageChange={setActivePage}
					totalPages={Math.ceil(results.totalHits / activeDelta)}
				/>
			</ClayPaginationBar>
		</div>
	);

	const _renderResultsManagementBar = () => (
		<ClayManagementToolbar>
			<ClayManagementToolbar.ItemList>
				<ClayManagementToolbar.Item>
					<span className="text-truncate-inline total-hits-label">
						<span className="text-truncate">
							{sub(Liferay.Language.get('x-results'), [
								results.totalHits.toLocaleString(),
							])}
						</span>
					</span>
				</ClayManagementToolbar.Item>

				<ClayManagementToolbar.Item>
					<ClayButton
						aria-label={Liferay.Language.get('refresh')}
						disabled={loading}
						displayType="secondary"
						onClick={_handleFetch}
						small
					>
						{Liferay.Language.get('refresh')}
					</ClayButton>
				</ClayManagementToolbar.Item>
			</ClayManagementToolbar.ItemList>

			<ClayManagementToolbar.ItemList>
				<ClayManagementToolbar.Item>
					<PreviewModalWithCopyDownload
						fileName="raw_response.json"
						folded
						lineWrapping={false}
						size="lg"
						text={parseAndPrettifyJSON(results.responseString)}
						title={Liferay.Language.get('raw-response')}
					>
						<ClayButton
							borderless
							className="raw-response"
							disabled={loading}
							displayType="secondary"
							small
						>
							{Liferay.Language.get('view-raw-response')}
						</ClayButton>
					</PreviewModalWithCopyDownload>
				</ClayManagementToolbar.Item>
			</ClayManagementToolbar.ItemList>
		</ClayManagementToolbar>
	);

	return (
		<div
			className={getCN('preview-sidebar', 'sidebar', 'sidebar-light', {
				open: visible,
			})}
		>
			<div className="sidebar-header">
				<h4 className="component-title">
					<span className="text-truncate-inline">
						<span className="text-truncate">
							{Liferay.Language.get('preview')}
						</span>
					</span>
				</h4>

				<span>
					<PreviewAttributesModal
						onSubmit={_handleAttributesSubmit}
					/>

					<ClayButton
						aria-label={Liferay.Language.get('close')}
						borderless
						displayType="secondary"
						monospaced
						onClick={() => onToggle(false)}
						small
					>
						<ClayIcon symbol="times" />
					</ClayButton>
				</span>
			</div>

			<nav
				aria-label="preview-searchbar"
				className="component-tbar sidebar-search tbar"
			>
				<div className="container-fluid">
					<SearchInput
						disabled={loading}
						onChange={setValue}
						onEnter={_handleFetch}
					/>
				</div>
			</nav>

			{!!results?.warnings?.length && (
				<div className="warning-container">
					{results.warnings.map((warning, index) => (
						<ClayAlert
							displayType="warning"
							key={index}
							title={Liferay.Language.get('warning')}
							variant="stripe"
						>
							{warning.msg}
						</ClayAlert>
					))}
				</div>
			)}

			{!!results.totalHits &&
				(!results.errors || !results.errors.length) &&
				_renderResultsManagementBar()}

			{!loading ? (
				results.errors && !!results.errors.length ? (
					_renderErrors()
				) : results?.response?.hits?.hits?.length ? (
					_renderHits()
				) : results.response ? (
					<div className="empty-list-message">
						<ClayEmptyState description="" />
					</div>
				) : (
					<div className="search-message">
						{Liferay.Language.get(
							'perform-a-search-to-preview-your-blueprints-search-results'
						)}
					</div>
				)
			) : (
				<ClayLoadingIndicator />
			)}
		</div>
	);
}

PreviewSidebar.propTypes = {
	loading: PropTypes.bool,
	onFetchResults: PropTypes.func,
	onFocusSXPElement: PropTypes.func,
	onToggle: PropTypes.func,
	results: PropTypes.object,
	visible: PropTypes.bool,
};

export default React.memo(PreviewSidebar);
