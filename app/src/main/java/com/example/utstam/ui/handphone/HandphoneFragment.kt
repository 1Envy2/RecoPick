<<<<<<< HEAD
package com.example.utstam.ui.handphone

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

class HandphoneFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var produkAdapter: ProdukAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_handphone, container, false)

        recyclerView = view.findViewById(R.id.recyclerHandphone)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        val dataProduk = listOf(
            Produk("Iphone 16 Pro", R.drawable.hp_iphone16),
            Produk("Samsung Galaxy", R.drawable.hp_samsung),
            Produk("Redmi Note 12", R.drawable.hp_redmi_note_12),
            Produk("Iphone 13", R.drawable.hp_iphone13),
            Produk("Xiaomi 14T Pro", R.drawable.hp_xiaomi_14t_pro),
            Produk("OPPO Find X8", R.drawable.hp_oppo_find_x8)
        )

        produkAdapter = ProdukAdapter(dataProduk)
        recyclerView.adapter = produkAdapter

        return view
    }
}
=======
package com.example.utstam.ui.handphone

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

class HandphoneFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var produkAdapter: ProdukAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_handphone, container, false)

        recyclerView = view.findViewById(R.id.recyclerHandphone)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        val dataProduk = listOf(
            Produk("Iphone 16 Pro", R.drawable.hp_iphone16),
            Produk("Samsung Galaxy", R.drawable.hp_samsung),
            Produk("Redmi Note 12", R.drawable.hp_redmi_note_12),
            Produk("Iphone 13", R.drawable.hp_iphone13),
            Produk("Xiaomi 14T Pro", R.drawable.hp_xiaomi_14t_pro),
            Produk("OPPO Find X8", R.drawable.hp_oppo_find_x8)
        )

        produkAdapter = ProdukAdapter(dataProduk)
        recyclerView.adapter = produkAdapter

        return view
    }
}
>>>>>>> 392c53d3cce54873d4fe3ec93820967f2d7bfb06
