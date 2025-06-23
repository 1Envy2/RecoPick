package com.example.utstam.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.utstam.databinding.FragmentDetailProdukBinding
import com.example.utstam.model.Produk

class DetailProdukFragment : Fragment() {

    private var _binding: FragmentDetailProdukBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailProdukBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mengambil data produk dari arguments
        val produk = arguments?.getSerializable("produk") as? Produk

        if (produk != null) {
            setupUI(produk)
        }
        setupClickListeners()
    }

    private fun setupUI(produk: Produk) {
        binding.apply {
            // Set gambar produk utama saja
            imgProdukUtama.setImageResource(produk.gambar)

            // Set nama produk
            txtNamaProduk.text = produk.nama

            // Set spesifikasi teknis
            if (produk.spesifikasi.isNotEmpty()) {
                val spesifikasiText = produk.spesifikasi.joinToString("\n") { "• $it" }
                txtSpesifikasiTeknis.text = spesifikasiText
            }

            // Set harga
            if (produk.harga.isNotEmpty()) {
                txtHarga.text = "${produk.harga} (tergantung varian dan promo yang tersedia)"
            }

            // Set alasan penggunaan (menggunakan deskripsi produk)
            if (produk.deskripsi.isNotEmpty()) {
                txtAlasanPenggunaan.text = produk.deskripsi
            }

            // Set kelebihan (bisa dikustomisasi berdasarkan kategori produk)
            val kelebihan = getKelebihanByCategory(produk.kategori, produk.nama)
            txtKelebihan.text = kelebihan
        }
    }

    private fun getKelebihanByCategory(kategori: String, namaProduk: String): String {
        return when {
            kategori.contains("Handphone", ignoreCase = true) -> {
                when {
                    namaProduk.contains("iPhone", ignoreCase = true) ->
                        "• Ekosistem Apple yang terintegrasi\n• Kualitas build premium\n• Performa processor terdepan\n• Dukungan update jangka panjang"
                    namaProduk.contains("Samsung", ignoreCase = true) ->
                        "• Layar AMOLED berkualitas tinggi\n• Fitur S Pen (untuk Note series)\n• Kamera versatile\n• One UI yang user-friendly"
                    namaProduk.contains("Xiaomi", ignoreCase = true) ->
                        "• Value for money terbaik\n• Spesifikasi tinggi dengan harga terjangkau\n• MIUI dengan fitur lengkap\n• Fast charging technology"
                    else -> "• Performa handal\n• Kamera berkualitas\n• Baterai tahan lama\n• Desain modern"
                }
            }
            kategori.contains("Laptop", ignoreCase = true) ->
                "• Performa multitasking optimal\n• Portabilitas tinggi\n• Kualitas layar jernih\n• Daya tahan baterai baik"
            kategori.contains("TV", ignoreCase = true) ->
                "• Kualitas gambar superior\n• Smart TV features\n• Audio berkualitas\n• Konektivitas lengkap"
            kategori.contains("Kamera", ignoreCase = true) ->
                "• Kualitas foto profesional\n• Fitur manual lengkap\n• Build quality solid\n• Lensa berkualitas tinggi"
            kategori.contains("Perangkat Audio", ignoreCase = true) ->
                "• Kualitas suara jernih\n• Desain ergonomis\n• Konektivitas mudah\n• Daya tahan baterai baik"
            else -> "• Kualitas build premium\n• Performa handal\n• Fitur lengkap\n• Value for money"
        }
    }

    private fun setupClickListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
