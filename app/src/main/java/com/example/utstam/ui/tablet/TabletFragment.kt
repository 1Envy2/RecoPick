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
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController

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
            Produk(
                nama = "Xiaomi Pad 6S",
                gambar = R.drawable.tablet_xiaomi_pad_6s,
                harga = "Rp 4.999.000 - Rp 6.999.000",
                deskripsi = "Tablet premium dengan performa tinggi untuk produktivitas dan entertainment. Layar berkualitas tinggi dan dukungan stylus membuat tablet ini cocok untuk digital art dan note-taking.",
                spesifikasi = listOf(
                    "Processor: Snapdragon 8 Gen 2",
                    "RAM: 8GB/12GB",
                    "Storage: 128GB/256GB",
                    "Layar: 12.4 inci 2.8K LCD",
                    "Refresh Rate: 144Hz",
                    "Baterai: 10000mAh",
                    "Sistem Operasi: MIUI Pad 14"
                ),
                rating = 4.5f,
                kategori = "Tablet"
            ),
            Produk(
                nama = "Samsung Galaxy Tab S9",
                gambar = R.drawable.tablet_samsung_galaxy,
                harga = "Rp 8.999.000 - Rp 12.999.000",
                deskripsi = "Tablet flagship dengan S Pen yang responsif dan ekosistem Samsung yang terintegrasi. Sempurna untuk profesional kreatif dan produktivitas tingkat tinggi.",
                spesifikasi = listOf(
                    "Processor: Snapdragon 8 Gen 2 for Galaxy",
                    "RAM: 8GB/12GB",
                    "Storage: 128GB/256GB",
                    "Layar: 11 inci Dynamic AMOLED 2X",
                    "Refresh Rate: 120Hz",
                    "Baterai: 8400mAh",
                    "Fitur: S Pen included, DeX mode"
                ),
                rating = 4.7f,
                kategori = "Tablet"
            ),
            Produk(
                nama = "Huawei MatePad",
                gambar = R.drawable.tablet_huawei_matepad,
                harga = "Rp 3.999.000 - Rp 5.999.000",
                deskripsi = "Tablet dengan desain premium dan performa yang solid untuk kebutuhan multimedia dan produktivitas. Dilengkapi dengan fitur multi-window yang memudahkan multitasking.",
                spesifikasi = listOf(
                    "Processor: Kirin 820",
                    "RAM: 6GB/8GB",
                    "Storage: 64GB/128GB",
                    "Layar: 10.4 inci 2K IPS",
                    "Refresh Rate: 60Hz",
                    "Baterai: 7250mAh",
                    "Sistem Operasi: HarmonyOS"
                ),
                rating = 4.2f,
                kategori = "Tablet"
            )
        )

        produkAdapter = ProdukAdapter(listProduk) { produk ->
            // Navigasi ke detail produk
            val bundle = bundleOf("produk" to produk)
            findNavController().navigate(R.id.detailProdukFragment, bundle)
        }

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = produkAdapter
    }
}
