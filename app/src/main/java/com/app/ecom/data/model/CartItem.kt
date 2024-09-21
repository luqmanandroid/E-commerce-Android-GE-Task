package com.app.ecom.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cart_items")
data class CartItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productId: Int,
    val title: String = "",
    val price: Double = 0.0,
    val image: String = "",
    var quantity: Int = 0
)


