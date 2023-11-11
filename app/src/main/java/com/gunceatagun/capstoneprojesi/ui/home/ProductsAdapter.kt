package com.gunceatagun.capstoneprojesi.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gunceatagun.capstoneprojesi.data.model.response.ProductListUI
import com.gunceatagun.capstoneprojesi.databinding.ProductRowItemBinding

class ProductsAdapter(
    private val onProductClick: (Int) -> Unit
) : ListAdapter<ProductListUI, ProductsAdapter.ProductViewHolder>(ProductDiffUtilCallBack()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        return ProductViewHolder(
            ProductRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onProductClick
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(getItem(position))

    class ProductViewHolder(
        private val binding: ProductRowItemBinding,
        private val onProductClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductListUI) {
            with(binding) {
                productName.text = product.title
                productPrize.text = "${product.price} ₺"
                productPrizeInSale.text = product.salePrice.toString()
                Glide.with(image).load(product.imageOne).into(image)
                root.setOnClickListener {
                    onProductClick(product.id)
                }
            }
        }
    }

    class ProductDiffUtilCallBack : DiffUtil.ItemCallback<ProductListUI>() {
        override fun areItemsTheSame(oldItem: ProductListUI, newItem: ProductListUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductListUI, newItem: ProductListUI): Boolean {
            return oldItem == newItem
        }

    }

}