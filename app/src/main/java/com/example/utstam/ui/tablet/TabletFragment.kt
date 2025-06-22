package com.example.utstam.ui.tablet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.utstam.R
import com.example.utstam.adapter.ProdukAdapter
import com.example.utstam.model.Produk

class TabletFragment: Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var produkAdapter: ProdukAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tablet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerTablet)

        val listProduk = listOf(
            Produk("Xiaomi Pad 6S", R.drawable.tablet_xiaomi_pad_6s),
            Produk("Samsung Galaxy", R.drawable.tablet_samsung_galaxy),
            Produk("Huawei MatePad", R.drawable.tablet_huawei_matepad),
        )

        produkAdapter = ProdukAdapter(listProduk)

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = produkAdapter
    }
}

