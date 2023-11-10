package com.gunceatagun.capstoneprojesi.view.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.gunceatagun.capstoneprojesi.MainApplication
import com.gunceatagun.capstoneprojesi.data.model.GetProductDetailResponse
import com.gunceatagun.capstoneprojesi.databinding.FragmentProductDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailBinding
    private var productId = 0
    private val args by navArgs<ProductDetailFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            productId = ProductDetailFragmentArgs.fromBundle(it).productId
            getProductDetail(productId)
           // args.productId
        }
    }

    private fun getProductDetail(id: Int) {
        MainApplication.productService?.getProductDetail(id)
            ?.enqueue(object : Callback<GetProductDetailResponse> {
                override fun onResponse(
                    call: Call<GetProductDetailResponse>,
                    response: Response<GetProductDetailResponse>
                ) {
                    val results = response.body()
                    if (results?.status == 200 && results.product != null) {
                        results.product.let {
                            with(binding) {
                                Glide.with(productImage).load(it.imageOne).into(productImage)
                                productName.text = it.title
                                productDescription.text = it.description
                                productPrize.text = "${it.price} ₺"
                                productPrizeSale.text = it.salePrice.toString()
                            }
                        }

                    } else {
                        Toast.makeText(context, results?.message, Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<GetProductDetailResponse>, t: Throwable) {
                    Log.e("GetProductDetail", t.message.orEmpty())
                }

            }
            )
    }
}