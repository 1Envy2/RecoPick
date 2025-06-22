package com.example.utstam.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.utstam.R
import com.example.utstam.model.Produk

class ProdukAdapter(
    private val listProduk: List<Produk>,
    private val onItemClick: (Produk) -> Unit
) : RecyclerView.Adapter<ProdukAdapter.ProdukViewHolder>() {

    class ProdukViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProduk: ImageView = itemView.findViewById(R.id.imgProduk)
        val txtNamaProduk: TextView = itemView.findViewById(R.id.txtNamaProduk)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdukViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_produk, parent, false)
        return ProdukViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProdukViewHolder, position: Int) {
        val produk = listProduk[position]
        holder.imgProduk.setImageResource(produk.gambar)
        holder.txtNamaProduk.text = produk.nama

        // Set click listener untuk item
        holder.itemView.setOnClickListener {
            onItemClick(produk)
        }
    }

    override fun getItemCount(): Int = listProduk.size
}
