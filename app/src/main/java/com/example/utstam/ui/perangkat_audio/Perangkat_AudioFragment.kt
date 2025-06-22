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
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController

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
            Produk(
                nama = "JBL Partybox 310",
                gambar = R.drawable.perangkat_audio_jbl_partybox_310,
                harga = "Rp 4.999.000 - Rp 6.499.000",
                deskripsi = "Speaker portabel dengan suara menggelegar dan lampu LED yang memukau. Sempurna untuk pesta, acara outdoor, dan gathering bersama teman-teman dengan kualitas audio yang luar biasa.",
                spesifikasi = listOf(
                    "Output Power: 240W RMS",
                    "Driver: 6.5 inci woofer + tweeter",
                    "Konektivitas: Bluetooth, USB, AUX",
                    "Fitur: LED light show, karaoke",
                    "Baterai: 18 jam playback",
                    "Dimensi: 369 x 687 x 374 mm",
                    "Berat: 17.9kg"
                ),
                rating = 4.4f,
                kategori = "Perangkat Audio"
            ),
            Produk(
                nama = "Logitech G335",
                gambar = R.drawable.perangkat_audio_logitech_g335,
                harga = "Rp 899.000 - Rp 1.299.000",
                deskripsi = "Gaming headset yang nyaman dengan audio berkualitas tinggi. Desain ringan dan breathable untuk sesi gaming yang panjang tanpa rasa tidak nyaman di telinga.",
                spesifikasi = listOf(
                    "Driver: 40mm neodymium",
                    "Frequency Response: 20Hz - 20KHz",
                    "Impedance: 36 ohms",
                    "Microphone: Flip-to-mute",
                    "Konektivitas: 3.5mm jack",
                    "Kompatibilitas: PC, Console, Mobile",
                    "Berat: 240g"
                ),
                rating = 4.2f,
                kategori = "Perangkat Audio"
            ),
            Produk(
                nama = "Lenovo Thinkplus",
                gambar = R.drawable.perangkat_audio_lenovo_thinkplus,
                harga = "Rp 299.000 - Rp 499.000",
                deskripsi = "Earbuds wireless dengan kualitas suara jernih dan desain ergonomis. Cocok untuk penggunaan sehari-hari, olahraga, dan conference call dengan fitur noise cancellation yang baik.",
                spesifikasi = listOf(
                    "Driver: 13mm dynamic driver",
                    "Bluetooth: 5.0",
                    "Battery Life: 6+24 jam dengan case",
                    "Charging: USB-C, wireless charging",
                    "Water Resistance: IPX5",
                    "Fitur: Touch control, voice assistant",
                    "Berat: 4.5g per earbud"
                ),
                rating = 4.0f,
                kategori = "Perangkat Audio"
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
