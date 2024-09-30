package com.app.ecom.domain.model

data class ProductsListResponse(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating,
    var isFav: Boolean = false
)

data class Rating(
    val rate: Double,
    val count: Int
)