package com.gunceatagun.capstoneprojesi.ui.basket

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.gunceatagun.capstoneprojesi.common.gone
import com.gunceatagun.capstoneprojesi.common.visible
import com.gunceatagun.capstoneprojesi.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private val viewModel by viewModels<CartViewModel>()
    private val userId = FirebaseAuth.getInstance().currentUser?.uid.orEmpty()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
        viewModel.getCartProducts(userId)
        observeData()
    }

    private fun observeData() = with(binding) {
        viewModel.cartState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CartState.SuccessState -> {
                    progressBar.gone()
                }

                is CartState.EmptyScreen -> {
                    progressBar.gone()
                    noDataText.visible()
                    noDataText.text = state.failMessage
                }

                is CartState.ShowPopup -> {
                    binding.progressBar.gone()
                    //Snackbar.make(requireView(), it.errorMessage, 1000).show()
                    AlertDialog.Builder(context)
                        .setTitle("Hata!")
                        .setMessage("Bir şeyler ters gitti, geri giderek tekrar deneyin")
                        .setPositiveButton("Tamam") { dialog, _ -> dialog.dismiss() }
                        .setNegativeButton("İptal") { dialog, _ -> dialog.dismiss() }
                        .create()
                        .show()
                }

                CartState.Loading -> binding.progressBar.visible()
            }
        }
    }
}