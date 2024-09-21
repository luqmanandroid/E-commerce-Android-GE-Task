package com.app.ecom.domain.repository

import com.app.ecom.domain.model.ProductsListResponse

interface HomeRepository {
    suspend fun getProductList(): List<ProductsListResponse>

}