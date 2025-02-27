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

import ClayButton from '@clayui/button';
import ClayEmptyState from '@clayui/empty-state';
import {ClayCheckbox} from '@clayui/form';
import ClayLoadingIndicator from '@clayui/loading-indicator';
import ClayTable from '@clayui/table';
import InfiniteScroller from 'commerce-frontend-js/components/infinite_scroller/InfiniteScroller';
import PropTypes from 'prop-types';
import React, {useCallback, useEffect, useRef, useState} from 'react';

import {
	ADD_TO_CART_FROM_TABLE,
	DIAGRAM_EVENTS,
	DIAGRAM_TABLE_EVENTS,
} from '../utilities/constants';
import {getMappedProducts} from '../utilities/data';
import {formatLabel} from '../utilities/index';
import ManagementBar from './ManagementBar';

const PAGE_SIZE = 15;

function DiagramTable({isAdmin, productId}) {
	const [currentPage, setCurrentPage] = useState(1);
	const [loaderActive, setLoaderActive] = useState(false);
	const [lastPage, setLastPage] = useState(null);
	const [query, setQuery] = useState('');
	const [results, setResults] = useState(null);
	const [refreshTrigger, setRefreshTrigger] = useState(false);
	const [selectedProducts, setSelectedProducts] = useState([]);
	const wrapperRef = useRef();

	const handleDiagramUpdated = useCallback(
		({diagramProductId}) => {
			if (diagramProductId === productId) {
				setRefreshTrigger((trigger) => !trigger);
				setCurrentPage(1);
			}
		},
		[productId]
	);

	useEffect(() => {
		Liferay.on(DIAGRAM_EVENTS.DIAGRAM_UPDATED, handleDiagramUpdated);

		return () => {
			Liferay.detach(
				DIAGRAM_EVENTS.DIAGRAM_UPDATED,
				handleDiagramUpdated
			);
		};
	}, [handleDiagramUpdated]);

	useEffect(() => {
		getMappedProducts(productId, query, currentPage, PAGE_SIZE).then(
			(data) => {
				setLoaderActive(false);

				setResults((results) =>
					results && currentPage > 1
						? [...results, ...data.items]
						: data.items
				);

				setLastPage(data.lastPage);
			}
		);
	}, [productId, currentPage, query, refreshTrigger]);

	const selectableProduct = results?.filter(
		(result) => result.type === 'sku'
	);

	function handleTitleClicked(product) {
		Liferay.fire(DIAGRAM_TABLE_EVENTS.SELECT_PIN, {
			diagramProductId: productId,
			product,
		});
	}

	function handleMouseEnter(product) {
		Liferay.fire(DIAGRAM_TABLE_EVENTS.HIGHLIGHT_PIN, {
			diagramProductId: productId,
			sequence: product.sequence,
		});
	}

	function handleMouseLeave(product) {
		Liferay.fire(DIAGRAM_TABLE_EVENTS.REMOVE_PIN_HIGHLIGHT, {
			diagramProductId: productId,
			sequence: product.sequence,
		});
	}

	return (
		<div className="shop-by-diagram-table" ref={wrapperRef}>
			<ManagementBar
				updateQuery={(query) => {
					setCurrentPage(1);
					setLoaderActive(true);
					setQuery(query);
					setResults(null);
				}}
			/>

			{results && !results.length && !loaderActive && (
				<ClayEmptyState
					className="my-5 text-center"
					title={Liferay.Language.get('there-are-no-results')}
				/>
			)}

			{loaderActive && <ClayLoadingIndicator className="my-5" />}

			{!loaderActive && results && !!results.length && (
				<InfiniteScroller
					onBottomTouched={() => setCurrentPage(currentPage + 1)}
					scrollCompleted={currentPage >= lastPage}
				>
					<ClayTable borderless>
						<ClayTable.Head>
							<ClayTable.Row>
								{!isAdmin && ADD_TO_CART_FROM_TABLE && (
									<ClayTable.Cell headingCell>
										<ClayCheckbox
											checked={
												!!selectableProduct &&
												selectedProducts.length ===
													selectableProduct.length
											}
											indeterminate={
												!!selectableProduct &&
												!!selectedProducts.length &&
												selectedProducts.length <
													selectableProduct.length
											}
											onChange={() => {
												if (
													selectableProduct &&
													selectedProducts.length !==
														selectableProduct.length
												) {
													setSelectedProducts(
														selectableProduct
													);
												}
												else {
													setSelectedProducts([]);
												}
											}}
										/>
									</ClayTable.Cell>
								)}

								<ClayTable.Cell headingCell>#</ClayTable.Cell>

								<ClayTable.Cell
									className="table-cell-expand-small"
									headingCell
								>
									{Liferay.Language.get('sku-or-diagram')}
								</ClayTable.Cell>

								<ClayTable.Cell headingCell>
									{Liferay.Language.get('quantity')}
								</ClayTable.Cell>
							</ClayTable.Row>
						</ClayTable.Head>

						<ClayTable.Body>
							{Boolean(results?.length) &&
								results.map((product) => (
									<ClayTable.Row
										key={product.id}
										onMouseEnter={() =>
											handleMouseEnter(product)
										}
										onMouseLeave={() =>
											handleMouseLeave(product)
										}
									>
										{!isAdmin && ADD_TO_CART_FROM_TABLE && (
											<ClayTable.Cell>
												<ClayCheckbox
													checked={
														!!selectedProducts.find(
															(selected) =>
																selected.id ===
																product.id
														)
													}
													disabled={
														product.type !== 'sku'
													}
													onChange={(event) => {
														if (
															event.target.checked
														) {
															setSelectedProducts(
																[
																	...selectedProducts,
																	product,
																]
															);
														}
														else {
															setSelectedProducts(
																selectedProducts.filter(
																	(
																		selectedProduct
																	) =>
																		selectedProduct.id !==
																		product.id
																)
															);
														}
													}}
												/>
											</ClayTable.Cell>
										)}

										<ClayTable.Cell>
											{formatLabel(product.sequence)}
										</ClayTable.Cell>

										<ClayTable.Cell>
											<div className="table-list-title">
												<ClayButton
													displayType="unstyled"
													onClick={() =>
														handleTitleClicked(
															product
														)
													}
												>
													{product.type === 'diagram'
														? product.productName[
																Liferay.ThemeDisplay.getLanguageId()
														  ]
														: product.sku}
												</ClayButton>
											</div>
										</ClayTable.Cell>

										<ClayTable.Cell>
											{product.quantity || ''}
										</ClayTable.Cell>
									</ClayTable.Row>
								))}
						</ClayTable.Body>
					</ClayTable>
				</InfiniteScroller>
			)}
		</div>
	);
}

DiagramTable.propTypes = {
	isAdmin: PropTypes.bool,
	productId: PropTypes.string.isRequired,
};

export default DiagramTable;
