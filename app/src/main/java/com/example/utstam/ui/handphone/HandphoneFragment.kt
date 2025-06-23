package com.example.utstam.ui.handphone

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

class HandphoneFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var produkAdapter: ProdukAdapter
    private lateinit var allProduk: List<Produk>
    private var filteredProduk: List<Produk> = emptyList()

    // UI Components
    private lateinit var filterOverlay: LinearLayout
    private lateinit var btnFilterIcon: ImageView
    private lateinit var btnFilterIphone: Button
    private lateinit var btnFilterSamsung: Button
    private lateinit var btnFilterXiaomi: Button
    private lateinit var btnFilterOppo: Button
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
    private lateinit var priceRange5: TextView
    private lateinit var priceRangeHigh: TextView
    private lateinit var priceRangeAll: TextView

    // Current filters and sort
    private var currentBrandFilter: String = "All"
    private var currentPriceFilter: String = "All"
    private var currentSort: String = "Default"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_handphone, container, false)

        initializeViews(view)
        setupData()
        setupAdapter()
        setupClickListeners()

        return view
    }

    private fun initializeViews(view: View) {
        recyclerView = view.findViewById(R.id.recyclerHandphone)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        filterOverlay = view.findViewById(R.id.filterOverlay)
        btnFilterIcon = view.findViewById(R.id.btnFilterIcon)
        btnFilterIphone = view.findViewById(R.id.btnFilterIphone)
        btnFilterSamsung = view.findViewById(R.id.btnFilterSamsung)
        btnFilterXiaomi = view.findViewById(R.id.btnFilterXiaomi)
        btnFilterOppo = view.findViewById(R.id.btnFilterOppo)
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
        priceRange5 = view.findViewById(R.id.priceRange5)
        priceRangeHigh = view.findViewById(R.id.priceRangeHigh)
        priceRangeAll = view.findViewById(R.id.priceRangeAll)
    }

    private fun setupData() {
        allProduk = listOf(
            // Rentang 500RB - 1JT
            Produk(
                nama = "Samsung Galaxy A04s",
                gambar = R.drawable.samsung_a04,
                harga = "Rp 799.000 - Rp 999.000",
                deskripsi = "Smartphone entry-level dengan layar besar dan baterai awet. Cocok untuk penggunaan dasar seperti WhatsApp, browsing, dan media sosial dengan performa yang stabil.",
                spesifikasi = listOf(
                    "Layar: 6.5 inci PLS LCD",
                    "Processor: Exynos 850",
                    "Kamera: Triple 50MP + 2MP + 2MP",
                    "RAM: 3GB/4GB",
                    "Memori Internal: 32GB/64GB",
                    "Baterai: 5000mAh dengan 15W charging",
                    "Sistem Operasi: Android 12, One UI Core"
                ),
                rating = 3.8f,
                kategori = "Handphone"
            ),

            // Rentang 1JT - 2JT
            Produk(
                nama = "Xiaomi Redmi 10A",
                gambar = R.drawable.redmi_10a,
                harga = "Rp 1.299.000 - Rp 1.699.000",
                deskripsi = "Smartphone budget dengan value terbaik dari Xiaomi. Menawarkan performa yang cukup untuk kebutuhan sehari-hari dengan harga yang sangat terjangkau.",
                spesifikasi = listOf(
                    "Layar: 6.53 inci IPS LCD",
                    "Processor: MediaTek Helio G25",
                    "Kamera: 13MP + 2MP",
                    "RAM: 3GB/4GB",
                    "Memori Internal: 32GB/64GB",
                    "Baterai: 5000mAh dengan 10W charging",
                    "Sistem Operasi: Android 11, MIUI 12.5"
                ),
                rating = 4.0f,
                kategori = "Handphone"
            ),

            Produk(
                nama = "OPPO A17k",
                gambar = R.drawable.oppo_a17k,
                harga = "Rp 1.599.000 - Rp 1.899.000",
                deskripsi = "Smartphone dengan desain elegan dan fitur keamanan sidik jari. Dilengkapi dengan ColorOS yang user-friendly dan performa yang smooth untuk aktivitas harian.",
                spesifikasi = listOf(
                    "Layar: 6.56 inci IPS LCD",
                    "Processor: MediaTek Helio G35",
                    "Kamera: 8MP",
                    "RAM: 3GB",
                    "Memori Internal: 64GB",
                    "Baterai: 5000mAh dengan 10W charging",
                    "Sistem Operasi: Android 12, ColorOS 12.1"
                ),
                rating = 3.9f,
                kategori = "Handphone"
            ),

            // Rentang 2JT - 3JT
            Produk(
                nama = "Redmi Note 12",
                gambar = R.drawable.hp_redmi_note_12,
                harga = "Rp 2.999.000 - Rp 3.999.000",
                deskripsi = "Smartphone dengan value terbaik di kelasnya. Pengguna menyukai layar AMOLED yang jernih, performa yang stabil untuk gaming ringan, dan baterai yang awet untuk penggunaan sehari-hari.",
                spesifikasi = listOf(
                    "Layar: 6.67 inci AMOLED",
                    "Processor: Snapdragon 685",
                    "Kamera: Triple 50MP + 8MP + 2MP",
                    "RAM: 4GB/6GB/8GB",
                    "Memori Internal: 128GB/256GB",
                    "Baterai: 5000mAh dengan 33W charging",
                    "Sistem Operasi: Android 12, MIUI 14"
                ),
                rating = 4.3f,
                kategori = "Handphone"
            ),

            Produk(
                nama = "Samsung Galaxy A24",
                gambar = R.drawable.samsung_a24,
                harga = "Rp 2.799.000 - Rp 3.299.000",
                deskripsi = "Smartphone mid-range dengan layar Super AMOLED yang cerah dan kamera yang versatile. Dilengkapi dengan fitur Samsung Knox untuk keamanan data yang lebih baik.",
                spesifikasi = listOf(
                    "Layar: 6.6 inci Super AMOLED",
                    "Processor: MediaTek Helio G99",
                    "Kamera: Triple 50MP + 5MP + 2MP",
                    "RAM: 6GB/8GB",
                    "Memori Internal: 128GB/256GB",
                    "Baterai: 5000mAh dengan 25W charging",
                    "Sistem Operasi: Android 13, One UI 5.1"
                ),
                rating = 4.2f,
                kategori = "Handphone"
            ),

            // Rentang 8JT - 12JT
            Produk(
                nama = "Xiaomi 14T Pro",
                gambar = R.drawable.hp_xiaomi_14t_pro,
                harga = "Rp 8.999.000 - Rp 10.999.000",
                deskripsi = "Smartphone flagship dengan teknologi kamera Leica yang memukau. Pengguna menyukai hasil foto yang profesional, performa gaming yang smooth, dan charging 120W yang super cepat.",
                spesifikasi = listOf(
                    "Layar: 6.67 inci AMOLED",
                    "Processor: MediaTek Dimensity 9300+",
                    "Kamera: Triple 50MP Leica + 50MP + 12MP",
                    "RAM: 12GB",
                    "Memori Internal: 256GB/512GB",
                    "Baterai: 5000mAh dengan 120W charging",
                    "Sistem Operasi: Android 14, HyperOS"
                ),
                rating = 4.5f,
                kategori = "Handphone"
            ),

            Produk(
                nama = "OPPO Reno11 Pro",
                gambar = R.drawable.oppo_reno_11,
                harga = "Rp 9.999.000 - Rp 11.999.000",
                deskripsi = "Smartphone dengan fokus pada fotografi portrait dan selfie. Dilengkapi dengan teknologi AI Portrait yang canggih dan desain premium yang elegan.",
                spesifikasi = listOf(
                    "Layar: 6.74 inci AMOLED",
                    "Processor: MediaTek Dimensity 8050",
                    "Kamera: Triple 50MP + 32MP + 8MP",
                    "RAM: 12GB",
                    "Memori Internal: 256GB/512GB",
                    "Baterai: 4600mAh dengan 80W charging",
                    "Sistem Operasi: Android 14, ColorOS 14"
                ),
                rating = 4.3f,
                kategori = "Handphone"
            ),

            // Rentang 12JT - 18JT
            Produk(
                nama = "iPhone 13",
                gambar = R.drawable.hp_iphone13,
                harga = "Rp 11.999.000 - Rp 15.999.000",
                deskripsi = "Pilihan solid untuk pengguna iOS dengan budget lebih terjangkau. Chip A15 Bionic masih sangat powerful, kamera ganda berkualitas tinggi, dan daya tahan baterai yang memuaskan.",
                spesifikasi = listOf(
                    "Layar: 6.1 inci Super Retina XDR",
                    "Chip: A15 Bionic",
                    "Kamera: Dual 12MP + 12MP",
                    "RAM: 4GB",
                    "Memori Internal: 128GB/256GB/512GB",
                    "Baterai: Hingga 19 jam video playback",
                    "Sistem Operasi: iOS 17"
                ),
                rating = 4.7f,
                kategori = "Handphone"
            ),

            Produk(
                nama = "Samsung Galaxy S24",
                gambar = R.drawable.samsung_s24,
                harga = "Rp 12.999.000 - Rp 16.999.000",
                deskripsi = "Pengguna mengapresiasi teknologi AI Galaxy yang canggih, kamera yang versatile, dan layar Dynamic AMOLED yang cerah. One UI 6.1 memberikan pengalaman yang smooth dan intuitif.",
                spesifikasi = listOf(
                    "Layar: 6.2 inci Dynamic AMOLED 2X",
                    "Processor: Snapdragon 8 Gen 3 for Galaxy",
                    "Kamera: Triple 50MP + 12MP + 10MP",
                    "RAM: 8GB",
                    "Memori Internal: 128GB/256GB",
                    "Baterai: 4000mAh dengan 25W charging",
                    "Sistem Operasi: Android 14, One UI 6.1"
                ),
                rating = 4.6f,
                kategori = "Handphone"
            ),

            Produk(
                nama = "OPPO Find X8",
                gambar = R.drawable.hp_oppo_find_x8,
                harga = "Rp 13.999.000 - Rp 16.999.000",
                deskripsi = "Smartphone premium dengan fokus pada fotografi mobile. Kolaborasi dengan Hasselblad menghasilkan kualitas foto yang luar biasa, terutama untuk portrait dan landscape photography.",
                spesifikasi = listOf(
                    "Layar: 6.59 inci AMOLED",
                    "Processor: MediaTek Dimensity 9400",
                    "Kamera: Triple 50MP Hasselblad + 50MP + 50MP",
                    "RAM: 12GB/16GB",
                    "Memori Internal: 256GB/512GB",
                    "Baterai: 5630mAh dengan 80W charging",
                    "Sistem Operasi: Android 14, ColorOS 14"
                ),
                rating = 4.4f,
                kategori = "Handphone"
            ),

            Produk(
                nama = "Xiaomi 13T",
                gambar = R.drawable.hp_xiaomi_14t_pro,
                harga = "Rp 14.999.000 - Rp 17.999.000",
                deskripsi = "Smartphone flagship dengan performa tinggi dan kamera Leica. Menawarkan pengalaman premium dengan harga yang lebih kompetitif dibanding pesaing.",
                spesifikasi = listOf(
                    "Layar: 6.67 inci AMOLED",
                    "Processor: MediaTek Dimensity 8200-Ultra",
                    "Kamera: Triple 50MP Leica + 50MP + 12MP",
                    "RAM: 8GB/12GB",
                    "Memori Internal: 256GB/512GB",
                    "Baterai: 5000mAh dengan 67W charging",
                    "Sistem Operasi: Android 13, MIUI 14"
                ),
                rating = 4.4f,
                kategori = "Handphone"
            ),

            // Rentang Di atas 18JT
            Produk(
                nama = "iPhone 16 Pro",
                gambar = R.drawable.hp_iphone16,
                harga = "Rp 19.999.000 - Rp 24.999.000",
                deskripsi = "Mayoritas pengguna menyukai performa A18 Pro yang luar biasa, sistem kamera Pro yang canggih, dan fitur Apple Intelligence. Namun, harga premium menjadi pertimbangan utama bagi sebagian konsumen.",
                spesifikasi = listOf(
                    "Layar: 6.3 inci Super Retina XDR",
                    "Chip: A18 Pro",
                    "Kamera: Triple 48MP + 12MP + 12MP",
                    "RAM: 8GB",
                    "Memori Internal: 128GB/256GB/512GB/1TB",
                    "Baterai: Hingga 27 jam video playback",
                    "Sistem Operasi: iOS 18"
                ),
                rating = 4.8f,
                kategori = "Handphone"
            ),

            Produk(
                nama = "iPhone 15 Pro Max",
                gambar = R.drawable.iphone_15_promax,
                harga = "Rp 20.999.000 - Rp 25.999.000",
                deskripsi = "iPhone flagship dengan layar terbesar dan baterai terbaik. Dilengkapi dengan kamera telephoto 5x dan build quality titanium yang premium.",
                spesifikasi = listOf(
                    "Layar: 6.7 inci Super Retina XDR",
                    "Chip: A17 Pro",
                    "Kamera: Triple 48MP + 12MP + 12MP (5x zoom)",
                    "RAM: 8GB",
                    "Memori Internal: 256GB/512GB/1TB",
                    "Baterai: Hingga 29 jam video playback",
                    "Sistem Operasi: iOS 17"
                ),
                rating = 4.9f,
                kategori = "Handphone"
            ),

            Produk(
                nama = "Samsung Galaxy S24 Ultra",
                gambar = R.drawable.hp_samsung,
                harga = "Rp 18.999.000 - Rp 23.999.000",
                deskripsi = "Smartphone Android flagship terbaik dengan S Pen dan kamera zoom 100x. Menawarkan produktivitas tingkat enterprise dengan performa gaming terdepan.",
                spesifikasi = listOf(
                    "Layar: 6.8 inci Dynamic AMOLED 2X",
                    "Processor: Snapdragon 8 Gen 3 for Galaxy",
                    "Kamera: Quad 200MP + 50MP + 10MP + 12MP",
                    "RAM: 12GB/16GB",
                    "Memori Internal: 256GB/512GB/1TB",
                    "Baterai: 5000mAh dengan 45W charging",
                    "Sistem Operasi: Android 14, One UI 6.1"
                ),
                rating = 4.7f,
                kategori = "Handphone"
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
        btnFilterIphone.setOnClickListener {
            filterByBrand("iPhone")
            updateBrandButtonStates(btnFilterIphone)
        }

        btnFilterSamsung.setOnClickListener {
            filterByBrand("Samsung")
            updateBrandButtonStates(btnFilterSamsung)
        }

        btnFilterXiaomi.setOnClickListener {
            filterByBrand("Xiaomi")
            updateBrandButtonStates(btnFilterXiaomi)
        }

        btnFilterOppo.setOnClickListener {
            filterByBrand("OPPO")
            updateBrandButtonStates(btnFilterOppo)
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

        // Price range options - menggunakan variabel yang sudah diinisialisasi
        priceRange1.setOnClickListener {
            filterByPriceRange("500RB - 1JT")
            updatePriceFilterStates(priceRange1)
            hideFilterOverlay()
        }

        priceRange2.setOnClickListener {
            filterByPriceRange("1JT - 2JT")
            updatePriceFilterStates(priceRange2)
            hideFilterOverlay()
        }

        priceRange3.setOnClickListener {
            filterByPriceRange("2JT - 3JT")
            updatePriceFilterStates(priceRange3)
            hideFilterOverlay()
        }

        priceRange4.setOnClickListener {
            filterByPriceRange("8JT - 12JT")
            updatePriceFilterStates(priceRange4)
            hideFilterOverlay()
        }

        priceRange5.setOnClickListener {
            filterByPriceRange("12JT - 18JT")
            updatePriceFilterStates(priceRange5)
            hideFilterOverlay()
        }

        priceRangeHigh.setOnClickListener {
            filterByPriceRange("Di atas 18JT")
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
            "500RB - 1JT" -> avgPrice in 500000..1000000
            "1JT - 2JT" -> avgPrice in 1000000..2000000
            "2JT - 3JT" -> avgPrice in 2000000..3000000
            "8JT - 12JT" -> avgPrice in 8000000..12000000
            "12JT - 18JT" -> avgPrice in 12000000..18000000
            "Di atas 18JT" -> avgPrice > 18000000
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
        val buttons = listOf(btnFilterAll, btnFilterIphone, btnFilterSamsung, btnFilterXiaomi, btnFilterOppo)

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
