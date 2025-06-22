package com.example.utstam.ui.laptop

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
            Produk(
                nama = "Lenovo LOQ Gaming",
                gambar = R.drawable.leptop_leptop_loq,
                harga = "Rp 12.999.000 - Rp 16.999.000",
                deskripsi = "Laptop gaming dengan performa tinggi yang cocok untuk gaming dan content creation. Dilengkapi dengan GPU dedicated dan sistem pendingin yang efisien untuk performa maksimal.",
                spesifikasi = listOf(
                    "Processor: Intel Core i5-12450H",
                    "GPU: NVIDIA GeForce RTX 3050",
                    "RAM: 8GB DDR5",
                    "Storage: 512GB SSD",
                    "Layar: 15.6 inci Full HD 144Hz",
                    "Sistem Operasi: Windows 11",
                    "Berat: 2.4kg"
                ),
                rating = 4.3f,
                kategori = "Laptop"
            ),
            Produk(
                nama = "MSI Summit",
                gambar = R.drawable.leptop_msi_summit,
                harga = "Rp 18.999.000 - Rp 24.999.000",
                deskripsi = "Laptop bisnis premium dengan desain elegan dan performa tinggi. Cocok untuk profesional yang membutuhkan mobilitas tinggi tanpa mengorbankan produktivitas.",
                spesifikasi = listOf(
                    "Processor: Intel Core i7-1260P",
                    "GPU: Intel Iris Xe Graphics",
                    "RAM: 16GB LPDDR5",
                    "Storage: 1TB SSD",
                    "Layar: 13.4 inci 2.8K touchscreen",
                    "Sistem Operasi: Windows 11 Pro",
                    "Berat: 1.35kg"
                ),
                rating = 4.6f,
                kategori = "Laptop"
            ),
            Produk(
                nama = "Acer Aspire 5",
                gambar = R.drawable.leptop_acer_aspire,
                harga = "Rp 7.999.000 - Rp 10.999.000",
                deskripsi = "Laptop serbaguna dengan value terbaik untuk kebutuhan sehari-hari. Ideal untuk pelajar, mahasiswa, dan pekerja kantoran dengan budget terbatas namun tetap menginginkan performa yang handal.",
                spesifikasi = listOf(
                    "Processor: AMD Ryzen 5 5500U",
                    "GPU: AMD Radeon Graphics",
                    "RAM: 8GB DDR4",
                    "Storage: 512GB SSD",
                    "Layar: 15.6 inci Full HD",
                    "Sistem Operasi: Windows 11",
                    "Berat: 1.8kg"
                ),
                rating = 4.1f,
                kategori = "Laptop"
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
