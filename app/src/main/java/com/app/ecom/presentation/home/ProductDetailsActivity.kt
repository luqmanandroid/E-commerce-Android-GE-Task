package com.app.ecom.presentation.home

import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import androidx.appcompat.app.AppCompatActivity
import com.app.ecom.databinding.ActivityProductDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

import androidx.lifecycle.lifecycleScope
import com.app.ecom.data.appdb.AppDatabase
import com.app.ecom.data.model.CartItem
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.withContext

@AndroidEntryPoint
class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding
    private var quantity = 1 // Initial quantity
    private lateinit var database: AppDatabase // Reference to Room database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the Room database
        database = AppDatabase.getDatabase(this)

        // Get product data from the Intent
        val productId = intent.getIntExtra("id", 0)
        val productTitle = intent.getStringExtra("title")
        val productPrice = intent.getDoubleExtra("price", 0.0)
        val productDescription = intent.getStringExtra("description")
        val productCategory = intent.getStringExtra("category")
        val productImage = intent.getStringExtra("image")
        val productRating = intent.getFloatExtra("rating", 0.0f)
        val productRatingCount = intent.getIntExtra("ratingCount", 0)

        // Set data to UI elements
        binding.productTitleTextView.text = productTitle
        binding.productPriceTextView.text = "Price: $${productPrice}"
        binding.productCategoryTextView.text = "Category: $productCategory"
        binding.productDescriptionTextView.text = productDescription
        binding.productRatingTextView.text = "Rating: $productRating ($productRatingCount reviews)"

        // Load the product image using Glide
        Glide.with(this)
            .load(productImage)
            .into(binding.productImageView)

        // Handle quantity increase button
        binding.btnIncreaseQuantity.setOnClickListener {
            quantity++
            binding.tvQuantity.text = quantity.toString()
        }

        // Handle quantity decrease button
        binding.btnDecreaseQuantity.setOnClickListener {
            if (quantity > 1) {
                quantity--
                binding.tvQuantity.text = quantity.toString()
            } else {
                Toast.makeText(this, "Quantity cannot be less than 1", Toast.LENGTH_SHORT).show()
            }
        }

        // Handle Add to Cart button click
        binding.btnAddToCart.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                // Check if product is already in the cart
                val existingCartItem = database.cartItemDao().getCartItemByProductId(productId)

                if (existingCartItem != null) {
                    // Update quantity if the product already exists
                    existingCartItem.quantity += quantity
                    database.cartItemDao().updateCartItem(existingCartItem)
                } else {
                    // Insert new product into the cart
                    val newCartItem = CartItem(
                        productId = productId,
                        title = productTitle!!,
                        price = productPrice,
                        image = productImage!!,
                        quantity = quantity
                    )
                    database.cartItemDao().insertCartItem(newCartItem)
                }

                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ProductDetailsActivity, "$quantity item(s) added to cart", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}
