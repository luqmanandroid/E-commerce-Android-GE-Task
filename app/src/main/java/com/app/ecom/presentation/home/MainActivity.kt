package com.app.ecom.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.ecom.data.appdb.AppDatabase
import com.app.ecom.databinding.ActivityMainBinding
import com.app.ecom.domain.model.ProductsListResponse
import com.app.ecom.presentation.home.x.ProductListAdapter
import com.app.ecom.utils.showPd

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    private lateinit var productListAdapter: ProductListAdapter
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var database: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding!!.root
        setContentView(view)


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.productListState.collect { state ->
                    if (state.isLoading) {
                        showPd(true,this@MainActivity)
                    }
                    if (state.error.isNotBlank()) {
                        showPd(false,this@MainActivity)
                    }
                    state.productsListResponse?.let {
                        showPd(false,this@MainActivity)
                        if (it.isNotEmpty()){
                            populateList(it)
                        }
                    }
                }
            }
        }


        viewModel.getProductsList()

        // Initialize the Room database
        database = AppDatabase.getDatabase(this)
        // Update cart item count


        lifecycleScope.launch {
            updateCartCount()
        }
        // Set listener on "View Cart" button
        binding?.cartContainer?.setOnClickListener {
            // Launch CartActivity to view the stored cart items
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }


    private fun populateList(productsList: List<ProductsListResponse>) {
        binding?.recyclerViewProducts?.layoutManager = LinearLayoutManager(this)
        productListAdapter = ProductListAdapter(productsList,this)
        binding?.recyclerViewProducts?.adapter = productListAdapter
    }

    override fun onBackPressed() {
            super.onBackPressed()
    }

    // Method to update the cart item count from the database
    private fun updateCartCount() {
        lifecycleScope.launch(Dispatchers.IO) {
            // Fetch the cart items in the IO context
            val cartItems = database.cartItemDao().getAllCartItems()

            // Calculate total quantity
            val totalCount = cartItems.sumOf { it.quantity }

            // Update UI on the main thread
            withContext(Dispatchers.Main) {
                binding?.cartContainer?.visibility = if (totalCount == 0) {
                    View.GONE
                } else {
                    View.VISIBLE
                }

                binding?.tvItemCount?.text = "$totalCount items"
            }
        }
    }


    override fun onResume() {
        super.onResume()
        // Update cart count whenever the activity resumes (in case the cart was updated)
        updateCartCount()
    }
}