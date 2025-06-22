package com.example.utstam

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnboardingFragment.newInstance(
                title = "RecoPick",
                subtitle = "Rekomendasi Barang Elektronik.",
                imageRes = R.drawable.onboarding_1
            )
            1 -> OnboardingFragment.newInstance(
                title = "Smart Choice, \n" +
                        "Better Life",
                subtitle = "Temukan elektronik terbaik hanya dalam satu genggaman.",
                imageRes = R.drawable.onboarding_2
            )
            else -> OnboardingFragment.newInstance("", "", R.drawable.onboarding_1)
        }
    }
}