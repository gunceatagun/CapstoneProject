package com.gunceatagun.capstoneprojesi.view.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gunceatagun.capstoneprojesi.MainApplication
import com.gunceatagun.capstoneprojesi.data.model.GetProductsResponse
import com.gunceatagun.capstoneprojesi.databinding.FragmentProductsListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsListFragment : Fragment() {
    private lateinit var binding: FragmentProductsListBinding
    private val productsAdapter=ProductsAdapter(onProductClick = ::onProductClick)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getProducts()

        with(binding){
            productListRecycler.adapter = productsAdapter
        }

    }

    private fun getProducts(){
        MainApplication.productService?.getProducts()?.enqueue(object :Callback<GetProductsResponse>{
            override fun onResponse(
                call: Call<GetProductsResponse>,
                response: Response<GetProductsResponse>
            ) {
                val results = response.body()
                if(results?.status == 200){
                    productsAdapter.submitList(results.products.orEmpty())
                }else{
                    Toast.makeText(context,results?.message,Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<GetProductsResponse>, t: Throwable) {
                Log.e("GetProducts",t.message.orEmpty())
            }

        })
    }
    private fun onProductClick(id: Int) {
        findNavController().navigate(ProductsListFragmentDirections.actionProductsListFragmentToProductDetailFragment(productId = id))
    }
}