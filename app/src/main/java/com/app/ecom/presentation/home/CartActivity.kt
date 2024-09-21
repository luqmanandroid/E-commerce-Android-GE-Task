package com.app.ecom.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.ecom.data.appdb.AppDatabase
import com.app.ecom.databinding.ActivityCartBinding
import com.app.ecom.presentation.home.x.CartAdapter

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private lateinit var database: AppDatabase
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the Room database
        database = AppDatabase.getDatabase(this)

        // Setup RecyclerView
        cartAdapter = CartAdapter(database) {
            // Callback to reload cart items after deletion
            loadCartItems()
        }
        binding.recyclerViewCartItems.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCartItems.adapter = cartAdapter

        // Load cart items
        loadCartItems()
    }

    private fun loadCartItems() {
        lifecycleScope.launch(Dispatchers.IO) {
            val cartItems = database.cartItemDao().getAllCartItems()
            withContext(Dispatchers.Main) {
                if (cartItems.isEmpty()) {
                    // Close the activity if the cart is empty
                    finish()
                } else {
                    cartAdapter.submitList(cartItems)

                    // Calculate total price
                    val totalPrice = cartItems.sumOf { it.price * it.quantity }

                    // Update the total price TextView
                    binding?.tvTotalPrice?.text = " $${"%.2f".format(totalPrice)}"
                }
            }
        }
    }
}
