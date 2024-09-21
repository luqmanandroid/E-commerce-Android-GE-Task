package com.app.ecom.data.repository

import com.app.ecom.data.remote.ApiService
import com.app.ecom.domain.model.ProductsListResponse
import com.app.ecom.domain.repository.HomeRepository

class HomeRepistoryImpl(private val retroAPIInterface: ApiService): HomeRepository {
    override suspend fun getProductList(): List<ProductsListResponse> {
        return retroAPIInterface.getProductsList()
    }

}