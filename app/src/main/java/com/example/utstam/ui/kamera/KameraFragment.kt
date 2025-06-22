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
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController

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
            Produk(
                nama = "Fujifilm X-A5",
                gambar = R.drawable.kamera_fujifilm_x_a5,
                harga = "Rp 6.999.000 - Rp 8.499.000",
                deskripsi = "Kamera mirrorless yang sempurna untuk pemula dengan desain retro yang elegan. Dilengkapi dengan layar touchscreen yang dapat diputar dan berbagai filter kreatif untuk hasil foto yang menarik.",
                spesifikasi = listOf(
                    "Sensor: 24.2MP APS-C CMOS",
                    "Prosesor: X-Processor Pro",
                    "Layar: 3.0 inci LCD touchscreen",
                    "Video: Full HD 1080p",
                    "Konektivitas: Wi-Fi, Bluetooth",
                    "Baterai: 410 shots per charge",
                    "Berat: 361g (body only)"
                ),
                rating = 4.2f,
                kategori = "Kamera"
            ),
            Produk(
                nama = "Sony W830",
                gambar = R.drawable.kamera_sony_w830,
                harga = "Rp 2.299.000 - Rp 2.799.000",
                deskripsi = "Kamera compact yang mudah digunakan dengan zoom optik 8x. Ideal untuk traveling dan fotografi sehari-hari dengan fitur auto yang cerdas dan hasil foto yang tajam.",
                spesifikasi = listOf(
                    "Sensor: 20.1MP Super HAD CCD",
                    "Zoom: 8x optical zoom",
                    "Layar: 2.7 inci LCD",
                    "Video: HD 720p",
                    "Stabilisasi: SteadyShot",
                    "Baterai: 210 shots per charge",
                    "Berat: 120g"
                ),
                rating = 4.0f,
                kategori = "Kamera"
            ),
            Produk(
                nama = "Sony A6000",
                gambar = R.drawable.kamera_sony_g6000,
                harga = "Rp 7.999.000 - Rp 9.499.000",
                deskripsi = "Kamera mirrorless dengan sistem autofocus tercepat di dunia. Kombinasi sempurna antara kualitas gambar profesional dan portabilitas yang tinggi untuk fotografer enthusiast.",
                spesifikasi = listOf(
                    "Sensor: 24.3MP APS-C Exmor CMOS",
                    "Prosesor: BIONZ X",
                    "Autofocus: 179 phase-detection points",
                    "Layar: 3.0 inci LCD tilting",
                    "Video: Full HD 1080p",
                    "Konektivitas: Wi-Fi, NFC",
                    "Berat: 344g (body only)"
                ),
                rating = 4.5f,
                kategori = "Kamera"
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
