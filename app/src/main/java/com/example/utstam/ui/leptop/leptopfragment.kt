package com.example.utstam.ui.laptop

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

class LaptopFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var produkAdapter: ProdukAdapter
    private lateinit var allProduk: List<Produk>
    private var filteredProduk: List<Produk> = emptyList()

    // UI Components
    private lateinit var filterOverlay: LinearLayout
    private lateinit var btnFilterIcon: ImageView
    private lateinit var btnFilterAsus: Button
    private lateinit var btnFilterLenovo: Button
    private lateinit var btnFilterMSI: Button
    private lateinit var btnFilterAcer: Button
    private lateinit var btnFilterHP: Button
    private lateinit var btnFilterAll: Button
    private lateinit var btnCloseFilter: ImageView

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
    private lateinit var priceRange5: TextView
    private lateinit var priceRangeHigh: TextView
    private lateinit var priceRangeAll: TextView

    // Tambahkan setelah inisialisasi priceRangeAll
    private lateinit var btnBack: ImageView

    // Current filters and sort
    private var currentBrandFilter: String = "All"
    private var currentPriceFilter: String = "All"
    private var currentSort: String = "Default"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_laptop, container, false)

        initializeViews(view)
        setupData()
        setupAdapter()
        setupClickListeners()

        return view
    }

    private fun initializeViews(view: View) {
        recyclerView = view.findViewById(R.id.recyclerLaptop)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        filterOverlay = view.findViewById(R.id.filterOverlay)
        btnFilterIcon = view.findViewById(R.id.btnFilterIcon)
        btnFilterAsus = view.findViewById(R.id.btnFilterAsus)
        btnFilterLenovo = view.findViewById(R.id.btnFilterLenovo)
        btnFilterMSI = view.findViewById(R.id.btnFilterMSI)
        btnFilterAcer = view.findViewById(R.id.btnFilterAcer)
        btnFilterHP = view.findViewById(R.id.btnFilterHP)
        btnFilterAll = view.findViewById(R.id.btnFilterAll)
        btnCloseFilter = view.findViewById(R.id.btnCloseFilter)

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
        priceRange5 = view.findViewById(R.id.priceRange5)
        priceRangeHigh = view.findViewById(R.id.priceRangeHigh)
        priceRangeAll = view.findViewById(R.id.priceRangeAll)

        // Tambahkan setelah baris priceRangeAll = view.findViewById(R.id.priceRangeAll)
        btnBack = view.findViewById(R.id.btnBack)
    }

    private fun setupData() {
        allProduk = listOf(
            // Rentang 5JT - 8JT (Entry Level)
            Produk(
                nama = "Acer Aspire 3",
                gambar = R.drawable.leptop_acer_aspire,
                harga = "Rp 5.999.000 - Rp 7.499.000",
                deskripsi = "Laptop entry-level yang cocok untuk kebutuhan dasar seperti browsing, office work, dan multimedia. Ideal untuk pelajar dan pekerja kantoran dengan budget terbatas.",
                spesifikasi = listOf(
                    "Processor: Intel Core i3-1115G4",
                    "GPU: Intel UHD Graphics",
                    "RAM: 4GB DDR4 (upgradeable)",
                    "Storage: 256GB SSD",
                    "Layar: 15.6 inci HD",
                    "Sistem Operasi: Windows 11",
                    "Berat: 1.9kg"
                ),
                rating = 3.8f,
                kategori = "Laptop"
            ),

            Produk(
                nama = "ASUS VivoBook 14",
                gambar = R.drawable.leptop_asus_vivobook,
                harga = "Rp 6.999.000 - Rp 8.999.000",
                deskripsi = "Laptop tipis dan ringan dengan desain modern. Cocok untuk mobilitas tinggi dengan performa yang cukup untuk produktivitas sehari-hari.",
                spesifikasi = listOf(
                    "Processor: AMD Ryzen 3 3250U",
                    "GPU: AMD Radeon Graphics",
                    "RAM: 4GB DDR4",
                    "Storage: 256GB SSD",
                    "Layar: 14 inci Full HD",
                    "Sistem Operasi: Windows 11",
                    "Berat: 1.6kg"
                ),
                rating = 4.0f,
                kategori = "Laptop"
            ),

            // Rentang 8JT - 12JT (Mid-Range)
            Produk(
                nama = "Lenovo IdeaPad 3",
                gambar = R.drawable.leptop_lenovo_ideapad,
                harga = "Rp 8.999.000 - Rp 11.999.000",
                deskripsi = "Laptop mid-range dengan performa seimbang untuk multitasking dan produktivitas. Dilengkapi dengan keyboard yang nyaman dan audio yang jernih.",
                spesifikasi = listOf(
                    "Processor: AMD Ryzen 5 5500U",
                    "GPU: AMD Radeon Graphics",
                    "RAM: 8GB DDR4",
                    "Storage: 512GB SSD",
                    "Layar: 15.6 inci Full HD",
                    "Sistem Operasi: Windows 11",
                    "Berat: 1.8kg"
                ),
                rating = 4.2f,
                kategori = "Laptop"
            ),

            Produk(
                nama = "HP Pavilion 14",
                gambar = R.drawable.leptop_hp_pavilion,
                harga = "Rp 9.999.000 - Rp 12.999.000",
                deskripsi = "Laptop stylish dengan performa solid untuk creative work dan entertainment. Dilengkapi dengan audio B&O dan layar yang vibrant.",
                spesifikasi = listOf(
                    "Processor: Intel Core i5-1135G7",
                    "GPU: Intel Iris Xe Graphics",
                    "RAM: 8GB DDR4",
                    "Storage: 512GB SSD",
                    "Layar: 14 inci Full HD IPS",
                    "Sistem Operasi: Windows 11",
                    "Berat: 1.5kg"
                ),
                rating = 4.3f,
                kategori = "Laptop"
            ),

            // Rentang 12JT - 18JT (Gaming & Performance)
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
                rating = 4.4f,
                kategori = "Laptop"
            ),

            Produk(
                nama = "ASUS TUF Gaming A15",
                gambar = R.drawable.leptop_asus_tuf,
                harga = "Rp 13.999.000 - Rp 17.999.000",
                deskripsi = "Laptop gaming tangguh dengan sertifikasi military-grade. Menawarkan performa gaming yang solid dengan daya tahan yang luar biasa.",
                spesifikasi = listOf(
                    "Processor: AMD Ryzen 5 6600H",
                    "GPU: NVIDIA GeForce RTX 3060",
                    "RAM: 16GB DDR5",
                    "Storage: 512GB SSD",
                    "Layar: 15.6 inci Full HD 144Hz",
                    "Sistem Operasi: Windows 11",
                    "Berat: 2.3kg"
                ),
                rating = 4.5f,
                kategori = "Laptop"
            ),

            Produk(
                nama = "Acer Predator Helios 300",
                gambar = R.drawable.leptop_acer_predator,
                harga = "Rp 15.999.000 - Rp 18.999.000",
                deskripsi = "Laptop gaming premium dengan cooling system yang canggih. Ideal untuk gaming berat dan streaming dengan performa yang konsisten.",
                spesifikasi = listOf(
                    "Processor: Intel Core i7-12700H",
                    "GPU: NVIDIA GeForce RTX 3070",
                    "RAM: 16GB DDR5",
                    "Storage: 1TB SSD",
                    "Layar: 15.6 inci Full HD 165Hz",
                    "Sistem Operasi: Windows 11",
                    "Berat: 2.5kg"
                ),
                rating = 4.6f,
                kategori = "Laptop"
            ),

            // Rentang 18JT - 25JT (Premium Business)
            Produk(
                nama = "MSI Summit E13",
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
                rating = 4.7f,
                kategori = "Laptop"
            ),

            Produk(
                nama = "HP Spectre x360",
                gambar = R.drawable.leptop_hp_spectre,
                harga = "Rp 19.999.000 - Rp 25.999.000",
                deskripsi = "Laptop convertible premium dengan build quality terbaik. Menawarkan fleksibilitas penggunaan dengan performa yang powerful untuk creative professionals.",
                spesifikasi = listOf(
                    "Processor: Intel Core i7-1255U",
                    "GPU: Intel Iris Xe Graphics",
                    "RAM: 16GB LPDDR4x",
                    "Storage: 1TB SSD",
                    "Layar: 13.5 inci 3K2K OLED touchscreen",
                    "Sistem Operasi: Windows 11",
                    "Berat: 1.27kg"
                ),
                rating = 4.8f,
                kategori = "Laptop"
            ),

            // Rentang Di atas 25JT (Workstation & Gaming High-End)
            Produk(
                nama = "ASUS ROG Strix SCAR 17",
                gambar = R.drawable.leptop_asus_rog,
                harga = "Rp 25.999.000 - Rp 35.999.000",
                deskripsi = "Laptop gaming flagship dengan performa tertinggi. Dilengkapi dengan teknologi cooling terdepan dan komponen premium untuk gaming dan content creation profesional.",
                spesifikasi = listOf(
                    "Processor: Intel Core i9-12900H",
                    "GPU: NVIDIA GeForce RTX 4070",
                    "RAM: 32GB DDR5",
                    "Storage: 1TB SSD",
                    "Layar: 17.3 inci QHD 240Hz",
                    "Sistem Operasi: Windows 11",
                    "Berat: 2.8kg"
                ),
                rating = 4.9f,
                kategori = "Laptop"
            ),

            Produk(
                nama = "MSI Creator Z17",
                gambar = R.drawable.leptop_msi_creator,
                harga = "Rp 28.999.000 - Rp 38.999.000",
                deskripsi = "Laptop workstation untuk content creator profesional. Dilengkapi dengan layar color-accurate dan performa yang powerful untuk video editing dan 3D rendering.",
                spesifikasi = listOf(
                    "Processor: Intel Core i7-12700H",
                    "GPU: NVIDIA GeForce RTX 4060",
                    "RAM: 32GB DDR5",
                    "Storage: 2TB SSD",
                    "Layar: 17 inci QHD+ 165Hz 100% DCI-P3",
                    "Sistem Operasi: Windows 11 Pro",
                    "Berat: 2.5kg"
                ),
                rating = 4.8f,
                kategori = "Laptop"
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
        btnFilterAsus.setOnClickListener {
            filterByBrand("ASUS")
            updateBrandButtonStates(btnFilterAsus)
        }

        btnFilterLenovo.setOnClickListener {
            filterByBrand("Lenovo")
            updateBrandButtonStates(btnFilterLenovo)
        }

        btnFilterMSI.setOnClickListener {
            filterByBrand("MSI")
            updateBrandButtonStates(btnFilterMSI)
        }

        btnFilterAcer.setOnClickListener {
            filterByBrand("Acer")
            updateBrandButtonStates(btnFilterAcer)
        }

        btnFilterHP.setOnClickListener {
            filterByBrand("HP")
            updateBrandButtonStates(btnFilterHP)
        }

        btnFilterAll.setOnClickListener {
            filterByBrand("All")
            updateBrandButtonStates(btnFilterAll)
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
            filterByPriceRange("5JT - 8JT")
            updatePriceFilterStates(priceRange1)
            hideFilterOverlay()
        }

        priceRange2.setOnClickListener {
            filterByPriceRange("8JT - 12JT")
            updatePriceFilterStates(priceRange2)
            hideFilterOverlay()
        }

        priceRange3.setOnClickListener {
            filterByPriceRange("12JT - 18JT")
            updatePriceFilterStates(priceRange3)
            hideFilterOverlay()
        }

        priceRange4.setOnClickListener {
            filterByPriceRange("18JT - 25JT")
            updatePriceFilterStates(priceRange4)
            hideFilterOverlay()
        }

        priceRange5.setOnClickListener {
            filterByPriceRange("25JT - 35JT")
            updatePriceFilterStates(priceRange5)
            hideFilterOverlay()
        }

        priceRangeHigh.setOnClickListener {
            filterByPriceRange("Di atas 35JT")
            updatePriceFilterStates(priceRangeHigh)
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
            "5JT - 8JT" -> avgPrice in 5000000..8000000
            "8JT - 12JT" -> avgPrice in 8000000..12000000
            "12JT - 18JT" -> avgPrice in 12000000..18000000
            "18JT - 25JT" -> avgPrice in 18000000..25000000
            "25JT - 35JT" -> avgPrice in 25000000..35000000
            "Di atas 35JT" -> avgPrice > 35000000
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
        val buttons = listOf(btnFilterAsus, btnFilterLenovo, btnFilterMSI, btnFilterAcer, btnFilterHP, btnFilterAll)

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
        val priceOptions = listOf(priceRange1, priceRange2, priceRange3, priceRange4, priceRange5, priceRangeHigh, priceRangeAll)

        priceOptions.forEach { option ->
            option.setTextColor(resources.getColor(R.color.black, null))
            option.setTypeface(null, android.graphics.Typeface.NORMAL)
        }

        activePriceFilter.setTextColor(resources.getColor(R.color.purple_dark, null))
        activePriceFilter.setTypeface(null, android.graphics.Typeface.BOLD)
    }
}
