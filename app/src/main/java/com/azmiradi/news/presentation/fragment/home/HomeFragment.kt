package com.azmiradi.news.presentation.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.azmiradi.news.R
import com.azmiradi.news.databinding.FragmentHomeBinding
import com.azmiradi.news.presentation.adaptors.BannerSliderAdapter
import com.azmiradi.news.presentation.adaptors.LatestNewsAdapter
import com.azmiradi.news.presentation.fragment.BaseFragment
import com.azmiradi.news.presentation.ui_handlers.HandlerRequest
import com.azmiradi.news.utils.NewsSections
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    HandlerRequest {

    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var bannerSliderAdapter: BannerSliderAdapter

    @Inject
    lateinit var latestNewsAdapter: LatestNewsAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdaptor()
        handleUI()
        observeData()
    }


    override fun setupAdaptor() {
        bannerSliderAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_item", it)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_detailsFragment, bundle
            )
        }

        latestNewsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_item", it)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_detailsFragment, bundle
            )
        }
    }

    override fun handleUI() {
        binding.latestNewsRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.banner.setAdapter(bannerSliderAdapter)
        binding.latestNewsRecycler.adapter = latestNewsAdapter
    }

    override fun observeData() {
        viewModel.allNewsState.observe(requireActivity()) {
            if (it.isLoading) {
                startRequest()
            }
            if (it.error.isNotEmpty()) {
                endRequest(it.error)
            }
            it.data?.let { data ->
                endRequest()
                data[NewsSections.TopBanner.name]?.let {
                    bannerSliderAdapter.differ.submitList(it.subList(0, 5))
                }

                data[NewsSections.LatestNews.name]?.let {
                    latestNewsAdapter.differ.submitList(it)
                }
            }

        }
    }

    override fun startRequest() {
        binding.latestNews.visibility = GONE
        binding.newsTitle.visibility = GONE
        binding.errorMessage.visibility = GONE
        binding.progress.visibility = VISIBLE
    }

    override fun endRequest(errorMessage: String?) {
        binding.progress.visibility = GONE
        errorMessage?.let {
            binding.errorMessage.visibility = VISIBLE
            binding.errorMessage.text=it
            return
        }
        binding.latestNews.visibility = VISIBLE
        binding.newsTitle.visibility = VISIBLE
    }

}
