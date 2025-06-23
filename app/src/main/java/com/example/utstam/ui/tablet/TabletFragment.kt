package com.example.utstam.ui.tablet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.utstam.R
import com.example.utstam.adapter.ProdukAdapter
import com.example.utstam.model.Produk
import android.widget.LinearLayout
import android.widget.TextView

class TabletFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var produkAdapter: ProdukAdapter
    private lateinit var allProduk: List<Produk>
    private var filteredProduk: List<Produk> = emptyList()

    // UI Components
    private lateinit var filterOverlay: LinearLayout
    private lateinit var btnFilterIcon: ImageView
    private lateinit var btnFilterApple: Button
    private lateinit var btnFilterSamsung: Button
    private lateinit var btnFilterXiaomi: Button
    private lateinit var btnFilterHuawei: Button
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
        val view = inflater.inflate(R.layout.fragment_tablet, container, false)

        initializeViews(view)
        setupData()
        setupAdapter()
        setupClickListeners()

        return view
    }

    private fun initializeViews(view: View) {
        recyclerView = view.findViewById(R.id.recyclerTablet)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        filterOverlay = view.findViewById(R.id.filterOverlay)
        btnFilterIcon = view.findViewById(R.id.btnFilterIcon)
        btnFilterApple = view.findViewById(R.id.btnFilterApple)
        btnFilterSamsung = view.findViewById(R.id.btnFilterSamsung)
        btnFilterXiaomi = view.findViewById(R.id.btnFilterXiaomi)
        btnFilterHuawei = view.findViewById(R.id.btnFilterHuawei)
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
            ),
            Produk(
                nama = "iPad Air 5th Gen",
                gambar = R.drawable.tablet_ipad_air,
                harga = "Rp 9.999.000 - Rp 13.999.000",
                deskripsi = "Tablet premium Apple dengan chip M1 yang powerful. Mendukung Apple Pencil dan Magic Keyboard untuk produktivitas maksimal dengan ekosistem Apple yang seamless.",
                spesifikasi = listOf(
                    "Chip: Apple M1",
                    "RAM: 8GB",
                    "Storage: 64GB/256GB",
                    "Layar: 10.9 inci Liquid Retina",
                    "Refresh Rate: 60Hz",
                    "Baterai: 10 jam usage",
                    "Sistem Operasi: iPadOS 17"
                ),
                rating = 4.8f,
                kategori = "Tablet"
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

        btnFilterApple.setOnClickListener {
            filterByBrand("iPad")
            updateBrandButtonStates(btnFilterApple)
        }

        btnFilterSamsung.setOnClickListener {
            filterByBrand("Samsung")
            updateBrandButtonStates(btnFilterSamsung)
        }

        btnFilterXiaomi.setOnClickListener {
            filterByBrand("Xiaomi")
            updateBrandButtonStates(btnFilterXiaomi)
        }

        btnFilterHuawei.setOnClickListener {
            filterByBrand("Huawei")
            updateBrandButtonStates(btnFilterHuawei)
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

    private fun filterByBrand(brand: String) {
        currentBrandFilter = brand
        applyFiltersAndSort()
    }

    private fun updateAdapter() {
        produkAdapter = ProdukAdapter(filteredProduk) { produk ->
            val bundle = bundleOf("produk" to produk)
            findNavController().navigate(R.id.detailProdukFragment, bundle)
        }
        recyclerView.adapter = produkAdapter
    }

    private fun updateBrandButtonStates(activeButton: Button) {
        val buttons = listOf(btnFilterAll, btnFilterApple, btnFilterSamsung, btnFilterXiaomi, btnFilterHuawei)

        buttons.forEach { button ->
            button.setBackgroundColor(resources.getColor(R.color.white, null))
            button.setTextColor(resources.getColor(R.color.black, null))
        }

        activeButton.setBackgroundColor(resources.getColor(R.color.purple_dark, null))
        activeButton.setTextColor(resources.getColor(R.color.white, null))
    }
}
