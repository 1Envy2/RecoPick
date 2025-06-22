package com.example.utstam.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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
            Produk(
                nama = "Samsung NU7300",
                gambar = R.drawable.tv_samsung_nu7300,
                harga = "Rp 6.999.000 - Rp 8.999.000",
                deskripsi = "Smart TV dengan layar curved yang memberikan pengalaman menonton yang immersive. Dilengkapi dengan Tizen OS dan berbagai aplikasi streaming populer untuk hiburan keluarga.",
                spesifikasi = listOf(
                    "Ukuran: 55 inci Curved UHD",
                    "Resolusi: 3840 x 2160 (4K)",
                    "HDR: HDR10+",
                    "Smart TV: Tizen OS",
                    "Konektivitas: Wi-Fi, Bluetooth, 3x HDMI",
                    "Audio: 20W Dolby Digital Plus",
                    "Fitur: Voice Control, Screen Mirroring"
                ),
                rating = 4.3f,
                kategori = "TV"
            ),
            Produk(
                nama = "LG 49LJ550T",
                gambar = R.drawable.tv_lg_49lj550t,
                harga = "Rp 4.999.000 - Rp 6.499.000",
                deskripsi = "Smart TV dengan webOS yang user-friendly dan kualitas gambar yang jernih. Cocok untuk ruang keluarga dengan berbagai fitur smart yang mudah digunakan oleh semua anggota keluarga.",
                spesifikasi = listOf(
                    "Ukuran: 49 inci Full HD",
                    "Resolusi: 1920 x 1080",
                    "Smart TV: webOS 3.5",
                    "Konektivitas: Wi-Fi, 2x HDMI, 1x USB",
                    "Audio: 10W Virtual Surround",
                    "Fitur: Magic Remote, LG Content Store",
                    "Dimensi: 110.3 x 64.2 x 8.5 cm"
                ),
                rating = 4.1f,
                kategori = "TV"
            ),
            Produk(
                nama = "LG 65UK6540PTD",
                gambar = R.drawable.tv_lg_65uk6540ptd,
                harga = "Rp 9.999.000 - Rp 12.999.000",
                deskripsi = "Smart TV 4K dengan teknologi AI ThinQ yang cerdas. Layar besar dengan kualitas gambar superior dan fitur smart home integration untuk pengalaman entertainment yang modern.",
                spesifikasi = listOf(
                    "Ukuran: 65 inci 4K UHD",
                    "Resolusi: 3840 x 2160",
                    "HDR: HDR10 Pro, HLG",
                    "Smart TV: webOS 4.0 dengan AI ThinQ",
                    "Konektivitas: Wi-Fi, Bluetooth, 4x HDMI",
                    "Audio: 20W Ultra Surround",
                    "Fitur: Voice Control, Smart Home Hub"
                ),
                rating = 4.5f,
                kategori = "TV"
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
