package com.app.ecom.presentation.home.state

import com.app.ecom.domain.model.ProductsListResponse

data class HomeState(
    val productsListResponse: List<ProductsListResponse>? = null,
    val error: String = "",
    val isLoading: Boolean = false
)
