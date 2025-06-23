package com.example.utstam.ui.perangkat_audio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.utstam.R
import com.example.utstam.adapter.ProdukAdapter
import com.example.utstam.model.Produk

class Perangkat_AudioFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var produkAdapter: ProdukAdapter
    private lateinit var allProduk: List<Produk>
    private var filteredProduk: List<Produk> = emptyList()

    // UI Components
    private lateinit var filterOverlay: LinearLayout
    private lateinit var btnFilterIcon: ImageView
    private lateinit var btnFilterJBL: Button
    private lateinit var btnFilterLogitech: Button
    private lateinit var btnFilterSony: Button
    private lateinit var btnFilterSennheiser: Button
    private lateinit var btnFilterAll: Button
    private lateinit var btnCloseFilter: ImageView
    private lateinit var btnBack: ImageView

    // Sort options
    private lateinit var sortDefault: TextView
    private lateinit var sortPriceLowHigh: TextView
    private lateinit var sortPriceHighLow: TextView
    private lateinit var sortRating: TextView

    // Price filter options
    private lateinit var priceRange1: TextView
    private lateinit var priceRange2: TextView
    private lateinit var priceRange3: TextView
    private lateinit var priceRange4: TextView
    private lateinit var priceRangeAll: TextView

    // Current filters and sort
    private var currentBrandFilter: String = "All"
    private var currentPriceFilter: String = "All"
    private var currentSort: String = "Default"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_perangkat_audio, container, false)

        initializeViews(view)
        setupData()
        setupAdapter()
        setupClickListeners()

        return view
    }

    private fun initializeViews(view: View) {
        recyclerView = view.findViewById(R.id.recyclerPerangkat_Audio)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        filterOverlay = view.findViewById(R.id.filterOverlay)
        btnFilterIcon = view.findViewById(R.id.btnFilterIcon)
        btnFilterJBL = view.findViewById(R.id.btnFilterJBL)
        btnFilterLogitech = view.findViewById(R.id.btnFilterLogitech)
        btnFilterSony = view.findViewById(R.id.btnFilterSony)
        btnFilterSennheiser = view.findViewById(R.id.btnFilterSennheiser)
        btnFilterAll = view.findViewById(R.id.btnFilterAll)
        btnCloseFilter = view.findViewById(R.id.btnCloseFilter)
        btnBack = view.findViewById(R.id.btnBack)

        // Sort options
        sortDefault = view.findViewById(R.id.sortDefault)
        sortPriceLowHigh = view.findViewById(R.id.sortPriceLowHigh)
        sortPriceHighLow = view.findViewById(R.id.sortPriceHighLow)
        sortRating = view.findViewById(R.id.sortRating)

        // Price filter options
        priceRange1 = view.findViewById(R.id.priceRange1)
        priceRange2 = view.findViewById(R.id.priceRange2)
        priceRange3 = view.findViewById(R.id.priceRange3)
        priceRange4 = view.findViewById(R.id.priceRange4)
        priceRangeAll = view.findViewById(R.id.priceRangeAll)
    }

    private fun setupData() {
        allProduk = listOf(
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
                nama = "Sony WH-1000XM4",
                gambar = R.drawable.perangkat_audio_sony_wh1000xm4,
                harga = "Rp 3.999.000 - Rp 4.999.000",
                deskripsi = "Headphone wireless premium dengan noise cancellation terbaik di kelasnya. Kualitas audio yang luar biasa dan baterai yang tahan lama untuk pengalaman mendengarkan musik yang optimal.",
                spesifikasi = listOf(
                    "Driver: 40mm dynamic driver",
                    "Noise Cancellation: Industry-leading ANC",
                    "Battery Life: 30 jam dengan ANC",
                    "Charging: USB-C, quick charge 10 menit = 5 jam",
                    "Konektivitas: Bluetooth 5.0, NFC",
                    "Fitur: Touch control, voice assistant",
                    "Berat: 254g"
                ),
                rating = 4.7f,
                kategori = "Perangkat Audio"
            ),
            Produk(
                nama = "Sennheiser HD 560S",
                gambar = R.drawable.perangkat_audio_sennheiser_560s,
                harga = "Rp 2.299.000 - Rp 2.999.000",
                deskripsi = "Headphone open-back dengan kualitas audio audiophile. Ideal untuk mixing, mastering, dan critical listening dengan soundstage yang luas dan detail yang akurat.",
                spesifikasi = listOf(
                    "Driver: 38mm dynamic transducer",
                    "Frequency Response: 6Hz - 38KHz",
                    "Impedance: 120 ohms",
                    "Design: Open-back",
                    "Konektivitas: 3.5mm jack + 6.3mm adapter",
                    "Cable: 3m detachable cable",
                    "Berat: 240g"
                ),
                rating = 4.5f,
                kategori = "Perangkat Audio"
            ),
            Produk(
                nama = "JBL Flip 6",
                gambar = R.drawable.perangkat_audio_jbl_flip6,
                harga = "Rp 1.799.000 - Rp 2.299.000",
                deskripsi = "Speaker portabel dengan suara yang powerful dan desain yang tahan air. Sempurna untuk outdoor activities dengan kualitas bass yang menggelegar dan treble yang jernih.",
                spesifikasi = listOf(
                    "Output Power: 30W RMS",
                    "Driver: Racetrack woofer + tweeter",
                    "Konektivitas: Bluetooth 5.1",
                    "Water Resistance: IP67",
                    "Baterai: 12 jam playback",
                    "Fitur: PartyBoost, JBL Connect",
                    "Berat: 550g"
                ),
                rating = 4.3f,
                kategori = "Perangkat Audio"
            )
        )

        filteredProduk = allProduk
    }

    private fun setupAdapter() {
        produkAdapter = ProdukAdapter(filteredProduk) { produk ->
            val bundle = bundleOf("produk" to produk)
            findNavController().navigate(R.id.detailProdukFragment, bundle)
        }
        recyclerView.adapter = produkAdapter
    }

    private fun setupClickListeners() {
        // Back button click listener
        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        // Filter icon click - toggle filter dropdown
        btnFilterIcon.setOnClickListener {
            toggleFilterOverlay()
        }

        // Close filter button
        btnCloseFilter.setOnClickListener {
            hideFilterOverlay()
        }

        // Brand filter buttons
        btnFilterAll.setOnClickListener {
            filterByBrand("All")
            updateBrandButtonStates(btnFilterAll)
        }

        btnFilterJBL.setOnClickListener {
            filterByBrand("JBL")
            updateBrandButtonStates(btnFilterJBL)
        }

        btnFilterLogitech.setOnClickListener {
            filterByBrand("Logitech")
            updateBrandButtonStates(btnFilterLogitech)
        }

        btnFilterSony.setOnClickListener {
            filterByBrand("Sony")
            updateBrandButtonStates(btnFilterSony)
        }

        btnFilterSennheiser.setOnClickListener {
            filterByBrand("Sennheiser")
            updateBrandButtonStates(btnFilterSennheiser)
        }

        // Sort options
        sortDefault.setOnClickListener {
            sortBy("Default")
            updateSortButtonStates(sortDefault)
            hideFilterOverlay()
        }

        sortPriceLowHigh.setOnClickListener {
            sortBy("PriceLowHigh")
            updateSortButtonStates(sortPriceLowHigh)
            hideFilterOverlay()
        }

        sortPriceHighLow.setOnClickListener {
            sortBy("PriceHighLow")
            updateSortButtonStates(sortPriceHighLow)
            hideFilterOverlay()
        }

        sortRating.setOnClickListener {
            sortBy("Rating")
            updateSortButtonStates(sortRating)
            hideFilterOverlay()
        }

        // Price range options
        priceRange1.setOnClickListener {
            filterByPriceRange("200RB - 500RB")
            updatePriceFilterStates(priceRange1)
            hideFilterOverlay()
        }

        priceRange2.setOnClickListener {
            filterByPriceRange("500RB - 1JT")
            updatePriceFilterStates(priceRange2)
            hideFilterOverlay()
        }

        priceRange3.setOnClickListener {
            filterByPriceRange("1JT - 3JT")
            updatePriceFilterStates(priceRange3)
            hideFilterOverlay()
        }

        priceRange4.setOnClickListener {
            filterByPriceRange("3JT - 7JT")
            updatePriceFilterStates(priceRange4)
            hideFilterOverlay()
        }

        priceRangeAll.setOnClickListener {
            filterByPriceRange("All")
            updatePriceFilterStates(priceRangeAll)
            hideFilterOverlay()
        }

        // Close filter overlay when clicking background
        view?.findViewById<View>(R.id.filterOverlayBackground)?.setOnClickListener {
            hideFilterOverlay()
        }
    }

    private fun toggleFilterOverlay() {
        if (filterOverlay.visibility == View.VISIBLE) {
            hideFilterOverlay()
        } else {
            showFilterOverlay()
        }
    }

    private fun showFilterOverlay() {
        filterOverlay.visibility = View.VISIBLE
    }

    private fun hideFilterOverlay() {
        filterOverlay.visibility = View.GONE
    }

    private fun filterByBrand(brand: String) {
        currentBrandFilter = brand
        applyFiltersAndSort()
    }

    private fun filterByPriceRange(priceRange: String) {
        currentPriceFilter = priceRange
        applyFiltersAndSort()
    }

    private fun sortBy(sortType: String) {
        currentSort = sortType
        applyFiltersAndSort()
    }

    private fun applyFiltersAndSort() {
        // Apply filters first
        filteredProduk = allProduk.filter { produk ->
            val brandMatch = if (currentBrandFilter == "All") {
                true
            } else {
                produk.nama.contains(currentBrandFilter, ignoreCase = true)
            }

            val priceMatch = if (currentPriceFilter == "All") {
                true
            } else {
                isPriceInRange(produk.harga, currentPriceFilter)
            }

            brandMatch && priceMatch
        }

        // Apply sorting
        filteredProduk = when (currentSort) {
            "PriceLowHigh" -> filteredProduk.sortedBy { getAveragePrice(it.harga) }
            "PriceHighLow" -> filteredProduk.sortedByDescending { getAveragePrice(it.harga) }
            "Rating" -> filteredProduk.sortedByDescending { it.rating }
            else -> filteredProduk // Default order
        }

        updateAdapter()
    }

    private fun getAveragePrice(hargaString: String): Long {
        val priceNumbers = hargaString.replace("Rp ", "").replace(".", "").split(" - ")
        if (priceNumbers.size < 2) return 0L

        val minPrice = priceNumbers[0].toLongOrNull() ?: 0L
        val maxPrice = priceNumbers[1].toLongOrNull() ?: 0L
        return (minPrice + maxPrice) / 2
    }

    private fun isPriceInRange(hargaString: String, priceRange: String): Boolean {
        val avgPrice = getAveragePrice(hargaString)

        return when (priceRange) {
            "200RB - 500RB" -> avgPrice in 200000..500000
            "500RB - 1JT" -> avgPrice in 500000..1000000
            "1JT - 3JT" -> avgPrice in 1000000..3000000
            "3JT - 7JT" -> avgPrice in 3000000..7000000
            else -> true
        }
    }

    private fun updateAdapter() {
        produkAdapter = ProdukAdapter(filteredProduk) { produk ->
            val bundle = bundleOf("produk" to produk)
            findNavController().navigate(R.id.detailProdukFragment, bundle)
        }
        recyclerView.adapter = produkAdapter
    }

    private fun updateBrandButtonStates(activeButton: Button) {
        val buttons = listOf(btnFilterAll, btnFilterJBL, btnFilterLogitech, btnFilterSony, btnFilterSennheiser)

        buttons.forEach { button ->
            button.setBackgroundColor(resources.getColor(R.color.white, null))
            button.setTextColor(resources.getColor(R.color.black, null))
        }

        activeButton.setBackgroundColor(resources.getColor(R.color.purple_dark, null))
        activeButton.setTextColor(resources.getColor(R.color.white, null))
    }

    private fun updateSortButtonStates(activeSort: TextView) {
        val sortOptions = listOf(sortDefault, sortPriceLowHigh, sortPriceHighLow, sortRating)

        sortOptions.forEach { option ->
            option.setTextColor(resources.getColor(R.color.black, null))
            option.setTypeface(null, android.graphics.Typeface.NORMAL)
        }

        activeSort.setTextColor(resources.getColor(R.color.purple_dark, null))
        activeSort.setTypeface(null, android.graphics.Typeface.BOLD)
    }

    private fun updatePriceFilterStates(activePriceFilter: TextView) {
        val priceOptions = listOf(priceRange1, priceRange2, priceRange3, priceRange4, priceRangeAll)

        priceOptions.forEach { option ->
            option.setTextColor(resources.getColor(R.color.black, null))
            option.setTypeface(null, android.graphics.Typeface.NORMAL)
        }

        activePriceFilter.setTextColor(resources.getColor(R.color.purple_dark, null))
        activePriceFilter.setTypeface(null, android.graphics.Typeface.BOLD)
    }
}
