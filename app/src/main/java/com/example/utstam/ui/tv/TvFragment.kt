package com.example.utstam.ui.tv

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

class TvFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var produkAdapter: ProdukAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerTv)

        val listProduk = listOf(
            Produk("Samsung NU7300", R.drawable.tv_samsung_nu7300),
            Produk("LG 49LJ550T", R.drawable.tv_lg_49lj550t),
            Produk("LG 65UK6540PTD", R.drawable.tv_lg_65uk6540ptd),
        )

        produkAdapter = ProdukAdapter(listProduk)

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = produkAdapter
    }
}