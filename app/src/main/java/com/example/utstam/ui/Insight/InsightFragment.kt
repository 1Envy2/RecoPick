package com.example.utstam.ui.insight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.utstam.databinding.FragmentInsightBinding
import com.example.utstam.R

class InsightFragment : Fragment() {

    private var _binding: FragmentInsightBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInsightBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        // Klik pada Card Handphone
        binding.cardInsightHandphone.setOnClickListener {
            val bundle = bundleOf(
                "id" to 1,
                "title" to "Tips Memilih Handphone Sesuai Kebutuhan",
                "shortDescription" to "Tidak semua smartphone cocok untuk semua orang. Berikut tips singkatnya:",
                "fullContent" to """Tidak semua smartphone cocok untuk semua orang.

Berikut tips singkatnya:

• Untuk multitasking: Pilih RAM minimal 8 GB dan prosesor terbaru.

• Untuk fotografi: Cari kamera dengan sensor besar dan fitur OIS.

• Untuk daya tahan: Prioritaskan baterai ≥ 5000 mAh dan fitur fast charging.

• Untuk budget terbatas: Bandingkan fitur inti, bukan sekadar merek.

Ingat: Sesuaikan pilihan dengan kebutuhan harian, bukan hanya tren!

Tips Tambahan:
1. Pertimbangkan ukuran layar yang nyaman untuk penggunaan sehari-hari
2. Periksa dukungan update sistem operasi jangka panjang
3. Bandingkan performa kamera dalam berbagai kondisi pencahayaan
4. Pastikan kapasitas penyimpanan sesuai dengan kebutuhan aplikasi dan media
5. Cek kualitas build dan material untuk daya tahan jangka panjang""",
                "imageResource" to R.drawable.insight_hp
            )
            findNavController().navigate(R.id.detailInsightFragment, bundle)
        }

        // Klik pada Card Laptop
        binding.cardInsightLaptop.setOnClickListener {
            val bundle = bundleOf(
                "id" to 2,
                "title" to "Panduan Memilih Laptop untuk Mahasiswa",
                "shortDescription" to "Laptop ideal untuk mahasiswa sebaiknya memiliki spesifikasi yang sesuai kebutuhan akademik.",
                "fullContent" to """Laptop ideal untuk mahasiswa sebaiknya memiliki RAM minimal 8GB, menggunakan SSD agar kinerja lebih cepat, dan layar berukuran sekitar 14 inci yang pas untuk dibawa ke kampus dan nyaman digunakan untuk belajar.

Spesifikasi yang Direkomendasikan:

• Processor: Intel Core i5 atau AMD Ryzen 5 generasi terbaru
• RAM: Minimal 8GB, idealnya 16GB untuk multitasking
• Storage: SSD 256GB atau lebih untuk kecepatan akses data
• Layar: 14-15 inci dengan resolusi Full HD
• Baterai: Minimal 6-8 jam untuk penggunaan normal

Pertimbangan Khusus:

1. Portabilitas: Pilih laptop dengan berat di bawah 2kg untuk mudah dibawa
2. Konektivitas: Pastikan tersedia port USB, HDMI, dan Wi-Fi 6
3. Keyboard: Pilih yang nyaman untuk mengetik dalam waktu lama
4. Budget: Sesuaikan dengan anggaran, prioritaskan spek yang paling dibutuhkan
5. Garansi: Pilih yang memiliki layanan purna jual yang baik

Tips Perawatan:
- Gunakan cooling pad saat penggunaan intensif
- Bersihkan keyboard dan layar secara rutin
- Update sistem operasi dan driver secara berkala
- Backup data penting secara rutin""",
                "imageResource" to R.drawable.insight_laptop
            )
            findNavController().navigate(R.id.detailInsightFragment, bundle)
        }

        // Klik pada Card TV
        binding.cardInsightTv.setOnClickListener {
            val bundle = bundleOf(
                "id" to 3,
                "title" to "Tips Membeli Smart TV untuk Rumah Anda",
                "shortDescription" to "Agar mendapatkan pengalaman menonton terbaik, pilih Smart TV dengan spesifikasi yang tepat.",
                "fullContent" to """Agar mendapatkan pengalaman menonton terbaik, pilih Smart TV dengan resolusi minimal Full HD atau 4K, fitur koneksi ke aplikasi seperti Netflix dan YouTube, serta ukuran layar yang sesuai dengan ukuran ruangan di rumah.

Panduan Memilih Smart TV:

• Ukuran Layar: Sesuaikan dengan jarak menonton dan luas ruangan
• Resolusi: 4K UHD untuk pengalaman visual terbaik
• Smart Platform: Android TV, Tizen, atau webOS dengan app store lengkap
• HDR Support: HDR10, Dolby Vision untuk kualitas gambar superior
• Audio: Dolby Atmos atau DTS untuk pengalaman audio immersive

Ukuran TV Berdasarkan Ruangan:

1. Ruang Tamu Kecil (< 20m²): 32-43 inci
2. Ruang Tamu Sedang (20-40m²): 50-55 inci  
3. Ruang Tamu Besar (> 40m²): 65-75 inci
4. Kamar Tidur: 32-43 inci
5. Ruang Keluarga: 55-65 inci

Fitur Penting:

- Multiple HDMI ports untuk berbagai perangkat
- Wi-Fi 6 untuk streaming lancar
- Voice control dan remote pintar
- Game mode untuk gaming console
- Screen mirroring dari smartphone

Tips Pemasangan:
- Pasang di ketinggian yang sejajar dengan mata saat duduk
- Hindari pantulan cahaya langsung
- Pastikan ventilasi yang cukup
- Gunakan bracket dinding yang kuat dan sesuai""",
                "imageResource" to R.drawable.insight_smarttv
            )
            findNavController().navigate(R.id.detailInsightFragment, bundle)
        }

        // Klik pada Card Kamera
        binding.cardInsightKamera.setOnClickListener {
            val bundle = bundleOf(
                "id" to 4,
                "title" to "Tips Membeli Kamera Yang Cocok Untuk Anda",
                "shortDescription" to "Kamera terbaik untuk pemula di fotografi dengan berbagai pertimbangan penting.",
                "fullContent" to """Kamera Terbaik untuk Pemula di Fotografi

Bagi pemula di dunia fotografi, kamera mirrorless adalah pilihan yang praktis dengan kualitas tinggi karena dilengkapi fitur autofocus cepat, stabilisasi gambar, dan lensa kit yang sudah cukup untuk mempelajari berbagai teknik pengambilan gambar.

Jenis Kamera untuk Pemula:

• Mirrorless: Ringan, kompak, kualitas gambar excellent
• DSLR Entry-level: Baterai tahan lama, lensa beragam
• Point & Shoot Advanced: Mudah digunakan, fitur lengkap
• Action Camera: Untuk aktivitas outdoor dan olahraga

Spesifikasi yang Perlu Diperhatikan:

1. Sensor: APS-C atau Full Frame untuk kualitas terbaik
2. Megapixel: 20-24MP sudah cukup untuk kebanyakan kebutuhan
3. ISO Performance: Kemampuan low light yang baik
4. Autofocus: Sistem AF yang cepat dan akurat
5. Video: 4K recording untuk content creation

Lensa untuk Pemula:

- Kit Lens 18-55mm: Serbaguna untuk berbagai situasi
- 50mm f/1.8: Portrait dan low light photography
- 35mm f/1.4: Street photography dan landscape
- 85mm f/1.8: Portrait dengan bokeh indah

Tips Belajar Fotografi:

1. Pelajari segitiga exposure (aperture, shutter speed, ISO)
2. Praktik komposisi rule of thirds
3. Eksperimen dengan berbagai mode kamera
4. Join komunitas fotografi untuk belajar bersama
5. Rajin latihan dan jangan takut bereksperimen

Aksesoris Penting:
- Tripod untuk foto landscape dan long exposure
- Memory card dengan kecepatan tinggi
- Extra battery untuk sesi panjang
- Lens filter untuk proteksi dan efek kreatif
- Camera bag untuk perlindungan""",
                "imageResource" to R.drawable.insight_kamera
            )
            findNavController().navigate(R.id.detailInsightFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
