package com.example.utstam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class OnboardingFragment : Fragment() {

    companion object {
        private const val ARG_TITLE = "title"
        private const val ARG_SUBTITLE = "subtitle"
        private const val ARG_IMAGE_RES = "image_res"

        fun newInstance(title: String, subtitle: String, imageRes: Int): OnboardingFragment {
            val fragment = OnboardingFragment()
            val args = Bundle()
            args.putString(ARG_TITLE, title)
            args.putString(ARG_SUBTITLE, subtitle)
            args.putInt(ARG_IMAGE_RES, imageRes)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_onboarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleText = view.findViewById<TextView>(R.id.titleText)
        val subtitleText = view.findViewById<TextView>(R.id.subtitleText)
        val imageView = view.findViewById<ImageView>(R.id.imageView)

        arguments?.let { args ->
            titleText.text = args.getString(ARG_TITLE)
            subtitleText.text = args.getString(ARG_SUBTITLE)
            imageView.setImageResource(args.getInt(ARG_IMAGE_RES))
        }
    }
}