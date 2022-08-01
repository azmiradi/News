package com.azmiradi.news.presentation.fragment.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.azmiradi.news.databinding.FragmentHomeBinding
import com.azmiradi.news.presentation.fragment.BaseFragment
import com.azmiradi.news.utils.NewsSections
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var bannerSliderAdapter: BannerSliderAdapter
    private lateinit var latestNewsAdapter: LatestNewsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bannerSliderAdapter = BannerSliderAdapter()
        latestNewsAdapter = LatestNewsAdapter()

        binding.banner.setAdapter(bannerSliderAdapter)
        binding.latestNewsRecycler.layoutManager=LinearLayoutManager(requireContext())
        binding.latestNewsRecycler.adapter = latestNewsAdapter
        viewModel.getNews()
        handleUI()
    }

    private fun handleUI() {
        viewModel.allNewsState.observe(requireActivity()) {
            if (it.isLoading) {

            }
            if (it.error.isNotEmpty()) {
            }
            it.data?.let {
                it[NewsSections.TopBanner.name]?.let {
                    bannerSliderAdapter.differ.submitList(it.subList(0, 5))
                }

                it[NewsSections.LatestNews.name]?.let {
                     latestNewsAdapter.differ.submitList(it)
                }
            }

        }
    }
}
