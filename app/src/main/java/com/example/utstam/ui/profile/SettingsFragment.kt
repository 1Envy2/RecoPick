package com.example.utstam.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.utstam.R

class SettingsFragment : Fragment() {

    private lateinit var btnBack: ImageView
    private lateinit var notificationLayout: LinearLayout
    private lateinit var privacyLayout: LinearLayout
    private lateinit var languageLayout: LinearLayout
    private lateinit var aboutLayout: LinearLayout
    private lateinit var helpLayout: LinearLayout
    private lateinit var termsLayout: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        initializeViews(view)
        setupClickListeners()

        return view
    }

    private fun initializeViews(view: View) {
        btnBack = view.findViewById(R.id.btnBack)
        notificationLayout = view.findViewById(R.id.notificationLayout)
        privacyLayout = view.findViewById(R.id.privacyLayout)
        languageLayout = view.findViewById(R.id.languageLayout)
        aboutLayout = view.findViewById(R.id.aboutLayout)
        helpLayout = view.findViewById(R.id.helpLayout)
        termsLayout = view.findViewById(R.id.termsLayout)
    }

    private fun setupClickListeners() {
        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        notificationLayout.setOnClickListener {
            Toast.makeText(requireContext(), "Notification Settings", Toast.LENGTH_SHORT).show()
        }

        privacyLayout.setOnClickListener {
            Toast.makeText(requireContext(), "Privacy Settings", Toast.LENGTH_SHORT).show()
        }

        languageLayout.setOnClickListener {
            Toast.makeText(requireContext(), "Language Settings", Toast.LENGTH_SHORT).show()
        }

        aboutLayout.setOnClickListener {
            Toast.makeText(requireContext(), "About RecoPick v1.0", Toast.LENGTH_SHORT).show()
        }

        helpLayout.setOnClickListener {
            Toast.makeText(requireContext(), "Help & Support", Toast.LENGTH_SHORT).show()
        }

        termsLayout.setOnClickListener {
            Toast.makeText(requireContext(), "Terms of Service", Toast.LENGTH_SHORT).show()
        }
    }
}
