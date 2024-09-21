package com.app.ecom.data.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CartItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCartItem(cartItem: CartItem)

    @Query("SELECT * FROM cart_items")
     fun getAllCartItems(): List<CartItem>

    @Delete
    fun deleteCartItem(cartItem: CartItem)

    @Update
     fun updateCartItem(cartItem: CartItem)

    @Query("SELECT * FROM cart_items WHERE productId = :productId LIMIT 1")
    fun getCartItemByProductId(productId: Int): CartItem?
}






