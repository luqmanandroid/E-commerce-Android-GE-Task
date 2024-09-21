package com.app.ecom.data.remote


import com.app.ecom.domain.model.ProductsListResponse

import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProductsList(): List<ProductsListResponse>


}