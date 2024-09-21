package com.app.ecom.presentation.home.x

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.ecom.R
import com.app.ecom.domain.model.ProductsListResponse
import com.app.ecom.presentation.home.ProductDetailsActivity
import com.bumptech.glide.Glide

class ProductListAdapter(private val ProductsList: List<ProductsListResponse>,val context: Context) :
    RecyclerView.Adapter<ProductListAdapter.ProductsViewHolder>() {

    class ProductsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvDescription: TextView = view.findViewById(R.id.tvDescription)
        val ivAttachment: ImageView = view.findViewById(R.id.ivAttachment)
        val tvCat: TextView = view.findViewById(R.id.tvPrice)
        val tvDetail: TextView = view.findViewById(R.id.tvDetail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product = ProductsList[position]
        holder.tvTitle.text = product.title
        holder.tvDescription.text = product.description
        holder.tvCat.text = "Price: $" + product.price


        holder.tvDetail.setOnClickListener {
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra("id", product.id)
            intent.putExtra("title", product.title)
            intent.putExtra("price", product.price)
            intent.putExtra("description", product.description)
            intent.putExtra("category", product.category)
            intent.putExtra("image", product.image)
            intent.putExtra("rating", product.rating.rate)
            intent.putExtra("ratingCount", product.rating.count)
            context.startActivity(intent)
        }


        // Load image if available, otherwise hide ImageView
        if (!product.image.isNullOrEmpty()) {
            holder.ivAttachment.visibility = View.VISIBLE
            Glide.with(holder.itemView.context)
                .load(product.image)
                .placeholder(R.drawable.ic_image_placeholder)  // Placeholder image
                .into(holder.ivAttachment)
        } else {
            holder.ivAttachment.visibility = View.GONE
        }
    }

    override fun getItemCount() = ProductsList.size
}
