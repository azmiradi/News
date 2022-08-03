package com.azmiradi.news.presentation.fragment.search

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.azmiradi.news.R
import com.azmiradi.news.databinding.FragmentSearchBinding
import com.azmiradi.news.presentation.fragment.BaseFragment
import com.azmiradi.news.presentation.ui_handlers.HandlerRequest
import com.azmiradi.news.presentation.adaptors.LatestNewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate),
    HandlerRequest {

    private val viewModel: SearchViewModel by viewModels()

    @Inject
    lateinit var latestNewsAdapter: LatestNewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdaptor()
        handleUI()
        observeData()
    }

    override fun setupAdaptor() {
        latestNewsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_item", it)
            }
            findNavController().navigate(
                R.id.action_searchFragment_to_detailsFragment, bundle
            )
        }
    }

    override fun handleUI() {
        binding.newsRecycler.adapter = latestNewsAdapter
        binding.newsRecycler.layoutManager = LinearLayoutManager(requireContext())


        binding.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    if (it.isNotEmpty())
                        viewModel.search(it)
                    else
                        latestNewsAdapter.differ.submitList(ArrayList())
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (it.isNotEmpty()) {
                        viewModel.search(it)
                    } else
                        latestNewsAdapter.differ.submitList(ArrayList())
                }
                return false
            }
        })
    }

    override fun observeData() {
        viewModel.allNewsState.observe(requireActivity()) {
            if (it.error.isNotEmpty()) {
                endRequest(it.error)
            }
            if (it.isLoading) {
                startRequest()
            }
            it.data?.let {
                if (it.isEmpty()) {
                    endRequest(getString(R.string.not_found_result))
                    latestNewsAdapter.differ.submitList(ArrayList())
                } else {
                    endRequest()
                    latestNewsAdapter.differ.submitList(it)
                }

            }

        }
    }

    override fun startRequest() {
        binding.errorMessage.visibility = GONE
        binding.progress.visibility = VISIBLE
    }

    override fun endRequest(errorMessage: String?) {
        binding.progress.visibility = GONE
        errorMessage?.let {
            binding.errorMessage.visibility = VISIBLE
            binding.errorMessage.text = it
        }
    }
}