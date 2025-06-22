package com.example.utstam.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.utstam.databinding.FragmentDashboardBinding
import androidx.navigation.fragment.findNavController
import com.example.utstam.R // penting!

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardHandphone.setOnClickListener {
            findNavController().navigate(R.id.navigation_handphone)
        }

        binding.cardLaptop.setOnClickListener {
            findNavController().navigate(R.id.navigation_laptop)
        }

        binding.cardTv.setOnClickListener {
            findNavController().navigate(R.id.navigation_tv)
        }

        binding.cardTablet.setOnClickListener {
            findNavController().navigate(R.id.navigation_tablet)
        }

        binding.cardKamera.setOnClickListener {
            findNavController().navigate(R.id.navigation_kamera)
        }

        binding.cardPerangkatAudio.setOnClickListener {
            findNavController().navigate(R.id.navigation_Perangkat_audio)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
