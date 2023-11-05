package com.gunceatagun.capstoneprojesi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class UrunlerListesiFragment : Fragment() {
    //Kitap Listemi burada görüntüleyeceğim
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_urunler_listesi, container, false)
    }
}