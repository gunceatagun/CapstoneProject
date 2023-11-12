package com.gunceatagun.capstoneprojesi.ui.fovorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gunceatagun.capstoneprojesi.data.model.response.ProductListUI
import com.gunceatagun.capstoneprojesi.databinding.ItemFavProductBinding

class FavoritesAdapter(
    private val onProductClick: (Int) -> Unit,
    private val onDeleteClick:(ProductListUI) ->Unit
) : ListAdapter<ProductListUI, FavoritesAdapter.FavProductViewHolder>(FavProductDiffUtilCallBack()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavProductViewHolder {
        return FavProductViewHolder(
            ItemFavProductBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onProductClick,
            onDeleteClick
        )
    }

    override fun onBindViewHolder(holder: FavProductViewHolder, position: Int) =
        holder.bind(getItem(position))

    class FavProductViewHolder(
        private val binding: ItemFavProductBinding,
        private val onProductClick: (Int) -> Unit,
        private val onDeleteClick: (ProductListUI) -> Unit
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
                ivDelete.setOnClickListener {
                    onDeleteClick(product)
                }
            }
        }
    }

    class FavProductDiffUtilCallBack : DiffUtil.ItemCallback<ProductListUI>() {
        override fun areItemsTheSame(oldItem: ProductListUI, newItem: ProductListUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductListUI, newItem: ProductListUI): Boolean {
            return oldItem == newItem
        }

    }

}