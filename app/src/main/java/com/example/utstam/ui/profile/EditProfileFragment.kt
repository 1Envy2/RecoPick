package com.example.utstam.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import android.util.Log
import com.example.utstam.R

class EditProfileFragment : Fragment() {

    private lateinit var etNama: EditText
    private lateinit var etEmail: EditText
    private lateinit var etUsername: EditText
    private lateinit var btnSave: Button
    private lateinit var btnBack: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_profile, container, false)

        etNama = view.findViewById(R.id.etNama)
        etEmail = view.findViewById(R.id.etEmail)
        etUsername = view.findViewById(R.id.etUsername)
        btnSave = view.findViewById(R.id.btnSave)
        btnBack = view.findViewById(R.id.btnBack)

        // Ambil data dari SharedPreferences
        val sharedPref = requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val savedName = sharedPref.getString("user_name", "")
        val savedEmail = sharedPref.getString("user_email", "")
        val savedUsername = sharedPref.getString("user_username", "")

        // Tampilkan data yang lama
        etNama.setText(savedName)
        etEmail.setText(savedEmail)
        etUsername.setText(savedUsername)

        // Tombol Simpan
        btnSave.setOnClickListener {
            val newName = etNama.text.toString().trim()
            val newEmail = etEmail.text.toString().trim()
            val newUsername = etUsername.text.toString().trim()

            val editor = sharedPref.edit()
            if (newName.isNotEmpty()) editor.putString("user_name", newName)
            if (newEmail.isNotEmpty()) editor.putString("user_email", newEmail)
            if (newUsername.isNotEmpty()) editor.putString("user_username", newUsername)
            editor.apply()
            Log.d("EditProfile", "Saved username: $newUsername")
            Toast.makeText(requireContext(), "Profil berhasil diperbarui", Toast.LENGTH_SHORT).show()

        }


        btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        return view
    }
}
