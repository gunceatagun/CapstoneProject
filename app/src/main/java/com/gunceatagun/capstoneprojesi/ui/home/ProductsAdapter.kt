package com.gunceatagun.capstoneprojesi.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gunceatagun.capstoneprojesi.R
import com.gunceatagun.capstoneprojesi.data.model.response.ProductUI
import com.gunceatagun.capstoneprojesi.databinding.ItemProductBinding

class ProductsAdapter(
    private val onProductClick: (Int) -> Unit,
    private val onFavClick:(ProductUI) ->Unit
) : ListAdapter<ProductUI, ProductsAdapter.ProductViewHolder>(ProductDiffUtilCallBack()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onProductClick,
            onFavClick
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(getItem(position))

    class ProductViewHolder(
        private val binding: ItemProductBinding,
        private val onProductClick: (Int) -> Unit,
        private val onFavClicked: (ProductUI) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductUI) {
            with(binding) {
                ivFavorite.setBackgroundResource(
                    if (product.isFav) R.drawable.ic_fav_selected
                    else R.drawable.ic_fav_unselected
                )
                productName.text = product.title
                productPrize.text = "${product.price} ₺"
                productPrizeInSale.text = product.salePrice.toString()
                Glide.with(image).load(product.imageOne).into(image)
                root.setOnClickListener {
                    onProductClick(product.id)
                }
                ivFavorite.setOnClickListener {
                    onFavClicked(product)
                }
            }
        }
    }

    class ProductDiffUtilCallBack : DiffUtil.ItemCallback<ProductUI>() {
        override fun areItemsTheSame(oldItem: ProductUI, newItem: ProductUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductUI, newItem: ProductUI): Boolean {
            return oldItem == newItem
        }

    }

}