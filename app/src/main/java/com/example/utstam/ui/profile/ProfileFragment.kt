package com.example.utstam.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.utstam.R
import android.content.Context
import android.util.Log
import com.example.utstam.databinding.FragmentProfileBinding
import android.widget.LinearLayout

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val name = sharedPref.getString("user_name", "Nama tidak tersedia")
        val email = sharedPref.getString("user_email", "Email tidak tersedia")
        val username = sharedPref.getString("user_username", "Username tidak tersedia")
        Log.d("ProfileFragment", "Loaded username: $username")

        binding.usernameTextView.text = username
        binding.nameTextView.text = name
        binding.emailTextView.text = email

        binding.editProfileLayout.setOnClickListener {
            findNavController().navigate(R.id.editProfileFragment)
        }

        // Settings click listener
        binding.root.findViewById<LinearLayout>(R.id.settingsLayout)?.setOnClickListener {
            findNavController().navigate(R.id.settingsFragment)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
