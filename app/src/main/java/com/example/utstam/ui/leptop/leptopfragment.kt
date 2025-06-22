package com.example.utstam.ui.laptop

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

class LaptopFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var produkAdapter: ProdukAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_laptop, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerLaptop)

        val listProduk = listOf(
            Produk("Asus ROG Zephyrus", R.drawable.leptop_leptop_loq),
            Produk("Lenovo Legion", R.drawable.leptop_msi_summit),
            Produk("Acer Swift 3", R.drawable.leptop_acer_aspire),
        )

        produkAdapter = ProdukAdapter(listProduk)

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = produkAdapter
    }
}
