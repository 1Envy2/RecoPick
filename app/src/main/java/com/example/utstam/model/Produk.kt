package com.example.utstam.model

import java.io.Serializable

data class Produk(
    val nama: String,
    val gambar: Int,
    val harga: String = "",
    val deskripsi: String = "",
    val spesifikasi: List<String> = emptyList(),
    val rating: Float = 0f,
    val kategori: String = ""
) : Serializable
