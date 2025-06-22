package com.example.utstam.ui.kamera

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

class KameraFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var produkAdapter: ProdukAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_kamera, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerKamera)

        val listProduk = listOf(
            Produk("Fujifilm X-A5", R.drawable.kamera_fujifilm_x_a5),
            Produk("Sony W830", R.drawable.kamera_sony_w830),
            Produk("Sony g6000", R.drawable.kamera_sony_g6000),
        )

        produkAdapter = ProdukAdapter(listProduk)

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = produkAdapter
    }
}