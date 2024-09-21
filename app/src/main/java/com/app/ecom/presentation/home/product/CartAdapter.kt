package com.app.ecom.presentation.home.x

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.ecom.data.appdb.AppDatabase
import com.app.ecom.data.model.CartItem
import com.app.ecom.databinding.ItemCartBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartAdapter(
    private val database: AppDatabase, // Pass the database instance to the adapter
    private val onCartUpdated: () -> Unit // Callback to refresh cart in the activity
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private var cartItems: List<CartItem> = listOf()

    class CartViewHolder(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItems[position]

        // Bind cart item data to UI elements
        holder.binding.tvTitle.text = cartItem.title
        holder.binding.tvPrice.text = "Price: $${cartItem.price}"
        holder.binding.tvQuantity.text = "Quantity: ${cartItem.quantity}"

        // Load product image using Glide
        Glide.with(holder.itemView.context)
            .load(cartItem.image)
            .into(holder.binding.ivProductImage)

        // Handle delete button click
        holder.binding.btnDelete.setOnClickListener {
            deleteItem(cartItem)
        }
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    fun submitList(items: List<CartItem>) {
        cartItems = items
        notifyDataSetChanged()
    }

    // Function to delete item from the database and refresh the list
    private fun deleteItem(cartItem: CartItem) {
        CoroutineScope(Dispatchers.IO).launch {
            database.cartItemDao().deleteCartItem(cartItem)
            onCartUpdated() // Callback to refresh cart in activity
        }
    }
}
