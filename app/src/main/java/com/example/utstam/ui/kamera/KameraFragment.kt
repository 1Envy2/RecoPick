package com.example.utstam.ui.kamera

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

class KameraFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var produkAdapter: ProdukAdapter
    private lateinit var allProduk: List<Produk>
    private var filteredProduk: List<Produk> = emptyList()

    // UI Components
    private lateinit var filterOverlay: LinearLayout
    private lateinit var btnFilterIcon: ImageView
    private lateinit var btnFilterSony: Button
    private lateinit var btnFilterFujifilm: Button
    private lateinit var btnFilterCanon: Button
    private lateinit var btnFilterNikon: Button
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
        val view = inflater.inflate(R.layout.fragment_kamera, container, false)

        initializeViews(view)
        setupData()
        setupAdapter()
        setupClickListeners()

        return view
    }

    private fun initializeViews(view: View) {
        recyclerView = view.findViewById(R.id.recyclerKamera)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        filterOverlay = view.findViewById(R.id.filterOverlay)
        btnFilterIcon = view.findViewById(R.id.btnFilterIcon)
        btnFilterSony = view.findViewById(R.id.btnFilterSony)
        btnFilterFujifilm = view.findViewById(R.id.btnFilterFujifilm)
        btnFilterCanon = view.findViewById(R.id.btnFilterCanon)
        btnFilterNikon = view.findViewById(R.id.btnFilterNikon)
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
            ),
            Produk(
                nama = "Canon EOS M50 Mark II",
                gambar = R.drawable.kamera_canon_m50,
                harga = "Rp 8.999.000 - Rp 10.999.000",
                deskripsi = "Kamera mirrorless dengan fitur live streaming dan video 4K. Sempurna untuk content creator dan vlogger dengan dual pixel autofocus yang cepat dan akurat.",
                spesifikasi = listOf(
                    "Sensor: 24.1MP APS-C CMOS",
                    "Prosesor: DIGIC 8",
                    "Autofocus: Dual Pixel CMOS AF",
                    "Layar: 3.0 inci vari-angle touchscreen",
                    "Video: 4K UHD, Full HD",
                    "Konektivitas: Wi-Fi, Bluetooth",
                    "Berat: 387g (body only)"
                ),
                rating = 4.4f,
                kategori = "Kamera"
            ),
            Produk(
                nama = "Nikon D3500",
                gambar = R.drawable.kamera_nikon_d3500,
                harga = "Rp 5.999.000 - Rp 7.499.000",
                deskripsi = "DSLR entry-level dengan kualitas gambar yang luar biasa dan baterai yang tahan lama. Ideal untuk pemula yang ingin belajar fotografi dengan kontrol manual yang lengkap.",
                spesifikasi = listOf(
                    "Sensor: 24.2MP APS-C CMOS",
                    "Prosesor: EXPEED 4",
                    "Autofocus: 11-point AF system",
                    "Layar: 3.0 inci LCD",
                    "Video: Full HD 1080p",
                    "Konektivitas: Bluetooth",
                    "Berat: 415g (body only)"
                ),
                rating = 4.3f,
                kategori = "Kamera"
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

        btnFilterSony.setOnClickListener {
            filterByBrand("Sony")
            updateBrandButtonStates(btnFilterSony)
        }

        btnFilterFujifilm.setOnClickListener {
            filterByBrand("Fujifilm")
            updateBrandButtonStates(btnFilterFujifilm)
        }

        btnFilterCanon.setOnClickListener {
            filterByBrand("Canon")
            updateBrandButtonStates(btnFilterCanon)
        }

        btnFilterNikon.setOnClickListener {
            filterByBrand("Nikon")
            updateBrandButtonStates(btnFilterNikon)
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
            filterByPriceRange("2JT - 5JT")
            updatePriceFilterStates(priceRange1)
            hideFilterOverlay()
        }

        priceRange2.setOnClickListener {
            filterByPriceRange("5JT - 10JT")
            updatePriceFilterStates(priceRange2)
            hideFilterOverlay()
        }

        priceRange3.setOnClickListener {
            filterByPriceRange("10JT - 20JT")
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
            "2JT - 5JT" -> avgPrice in 2000000..5000000
            "5JT - 10JT" -> avgPrice in 5000000..10000000
            "10JT - 20JT" -> avgPrice in 10000000..20000000
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
        val buttons = listOf(btnFilterAll, btnFilterSony, btnFilterFujifilm, btnFilterCanon, btnFilterNikon)

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
