package com.example.utstam.ui.tv

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

class TvFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var produkAdapter: ProdukAdapter
    private lateinit var allProduk: List<Produk>
    private var filteredProduk: List<Produk> = emptyList()

    // UI Components
    private lateinit var filterOverlay: LinearLayout
    private lateinit var btnFilterIcon: ImageView
    private lateinit var btnFilterLG: Button
    private lateinit var btnFilterSamsung: Button
    private lateinit var btnFilterSony: Button
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
    private lateinit var priceRangeAll: TextView

    // Current filters and sort
    private var currentBrandFilter: String = "All"
    private var currentPriceFilter: String = "All"
    private var currentSort: String = "Default"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tv, container, false)

        initializeViews(view)
        setupData()
        setupAdapter()
        setupClickListeners()

        return view
    }

    private fun initializeViews(view: View) {
        recyclerView = view.findViewById(R.id.recyclerTv)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        filterOverlay = view.findViewById(R.id.filterOverlay)
        btnFilterIcon = view.findViewById(R.id.btnFilterIcon)
        btnFilterLG = view.findViewById(R.id.btnFilterLG)
        btnFilterSamsung = view.findViewById(R.id.btnFilterSamsung)
        btnFilterSony = view.findViewById(R.id.btnFilterSony)
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
        priceRangeAll = view.findViewById(R.id.priceRangeAll)
    }

    private fun setupData() {
        allProduk = listOf(
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
            ),
            Produk(
                nama = "Sony Bravia X80J",
                gambar = R.drawable.tv_sony_bravia,
                harga = "Rp 11.999.000 - Rp 14.999.000",
                deskripsi = "Smart TV premium dengan teknologi Google TV dan kualitas gambar yang luar biasa. Dilengkapi dengan fitur gaming yang canggih dan audio yang jernih untuk pengalaman entertainment terbaik.",
                spesifikasi = listOf(
                    "Ukuran: 55 inci 4K HDR",
                    "Resolusi: 3840 x 2160",
                    "HDR: HDR10, HLG, Dolby Vision",
                    "Smart TV: Google TV",
                    "Konektivitas: Wi-Fi 6, Bluetooth, 4x HDMI",
                    "Audio: 20W Acoustic Multi-Audio",
                    "Fitur: Voice Remote, Chromecast built-in"
                ),
                rating = 4.6f,
                kategori = "TV"
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

        btnFilterLG.setOnClickListener {
            filterByBrand("LG")
            updateBrandButtonStates(btnFilterLG)
        }

        btnFilterSamsung.setOnClickListener {
            filterByBrand("Samsung")
            updateBrandButtonStates(btnFilterSamsung)
        }

        btnFilterSony.setOnClickListener {
            filterByBrand("Sony")
            updateBrandButtonStates(btnFilterSony)
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
            filterByPriceRange("3JT - 6JT")
            updatePriceFilterStates(priceRange1)
            hideFilterOverlay()
        }

        priceRange2.setOnClickListener {
            filterByPriceRange("6JT - 10JT")
            updatePriceFilterStates(priceRange2)
            hideFilterOverlay()
        }

        priceRange3.setOnClickListener {
            filterByPriceRange("10JT - 15JT")
            updatePriceFilterStates(priceRange3)
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
            "3JT - 6JT" -> avgPrice in 3000000..6000000
            "6JT - 10JT" -> avgPrice in 6000000..10000000
            "10JT - 15JT" -> avgPrice in 10000000..15000000
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
        val buttons = listOf(btnFilterAll, btnFilterLG, btnFilterSamsung, btnFilterSony)

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
        val priceOptions = listOf(priceRange1, priceRange2, priceRange3, priceRangeAll)

        priceOptions.forEach { option ->
            option.setTextColor(resources.getColor(R.color.black, null))
            option.setTypeface(null, android.graphics.Typeface.NORMAL)
        }

        activePriceFilter.setTextColor(resources.getColor(R.color.purple_dark, null))
        activePriceFilter.setTypeface(null, android.graphics.Typeface.BOLD)
    }
}
