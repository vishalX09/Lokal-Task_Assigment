package com.example.retrofit.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit.Fragments.DetailsFragment
import com.example.retrofit.model.Products
import com.example.retrofit.R
import com.example.retrofit.databinding.ItemProductsBinding

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    inner class ProductViewHolder(val binding: ItemProductsBinding) :
        RecyclerView.ViewHolder(binding.root)
    private val diffCallback = object : DiffUtil.ItemCallback<Products>() {
        override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)
    var products: List<Products>?
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = products!!.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.apply {
            val product = products?.get(position)
            tvtitle.text = product?.title
            tvrating.text = "${product?.rating.toString()}/5"
            tvprice.text = "$ "+product?.price.toString()
            val stock = product?.stock
            if (stock != null) {
                if(stock>0) {
                    tvstock.text = "In Stock"
                }
            }else{
                tvstock.text = "Out of Stock"
            }
            Glide.with(root.context)
                .load(product?.thumbnail)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_loading)
                .into(ivThumbnail)
            btnDetails.setOnClickListener {
                product?.let {
                    val fragment = DetailsFragment.newInstance(product)
                    val transaction = (root.context as AppCompatActivity)
                        .supportFragmentManager.beginTransaction()
                    transaction.setCustomAnimations(
                        R.anim.slide_in_bottom, 0, 0,
                        R.anim.slide_out_top
                    )
                    transaction.replace(R.id.fragmentContainer, fragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }
    }
}

