package com.azmiradi.news.presentation.fragment.setting

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import com.azmiradi.news.R
import com.azmiradi.news.databinding.FragmentSettingBinding
import com.azmiradi.news.presentation.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::inflate) {

    val viewModel: SettingViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMode()

        binding.darkMode.setOnCheckedChangeListener { _, isChecked ->
            viewModel.saveMode(binding.darkMode.isChecked)
            viewModel.getMode()
            println("Dark Mode: " + binding.darkMode.isChecked)

        }

        viewModel.isDark.observe(requireActivity()) {
            binding.darkMode.isChecked = it
            if (it) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.darkMode.text = getString(R.string.disable_dark)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.darkMode.text = getString(R.string.enable_dark)
            }
        }
    }
}