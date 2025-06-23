package com.example.utstam.ui.insight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.utstam.R
import com.example.utstam.databinding.FragmentDetailInsightBinding

class DetailInsightFragment : Fragment() {

    private var _binding: FragmentDetailInsightBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailInsightBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Aksi tombol kembali
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        // Ambil data insight dari arguments
        arguments?.let { bundle ->
            val title = bundle.getString("title", "")
            val fullContent = bundle.getString("fullContent", "")
            val imageResource = bundle.getInt("imageResource", R.drawable.insight_hp)

            displayInsightData(title, fullContent, imageResource)
        }
    }

    private fun displayInsightData(title: String, fullContent: String, imageResource: Int) {
        binding.apply {
            titleDetail.text = title
            contentDetail.text = fullContent
            imageDetail.setImageResource(imageResource)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
