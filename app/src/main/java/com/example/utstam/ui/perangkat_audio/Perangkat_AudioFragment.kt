package com.example.utstam.ui.perangkat_audio

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

class Perangkat_AudioFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var produkAdapter: ProdukAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_perangkat_audio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerPerangkat_Audio)

        val listProduk = listOf(
            Produk("JBL Partybox 310", R.drawable.perangkat_audio_jbl_partybox_310),
            Produk("Logitech G335", R.drawable.perangkat_audio_logitech_g335),
            Produk("Lenovo Thinkplus", R.drawable.perangkat_audio_lenovo_thinkplus),
        )

        produkAdapter = ProdukAdapter(listProduk)

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = produkAdapter
    }
}