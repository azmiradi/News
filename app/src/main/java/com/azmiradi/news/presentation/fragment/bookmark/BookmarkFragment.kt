package com.azmiradi.news.presentation.fragment.bookmark

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.azmiradi.news.R
import com.azmiradi.news.databinding.FragmentBookmarkBinding
import com.azmiradi.news.presentation.fragment.BaseFragment
import com.azmiradi.news.presentation.ui_handlers.Handlers
import com.azmiradi.news.presentation.adaptors.LatestNewsAdapter
import com.azmiradi.news.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BookmarkFragment : BaseFragment<FragmentBookmarkBinding>
    (FragmentBookmarkBinding::inflate), Handlers {

    @Inject
    lateinit var latestNewsAdapter: LatestNewsAdapter

    private val viewModel: BookmarkViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdaptor()
        handleUI()
        observeData()
    }

    override fun setupAdaptor() {
        latestNewsAdapter.isBookmark = true
        latestNewsAdapter.onBookmarkClickListener {
            viewModel.deleteArticle(it)
        }
        latestNewsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_item", it)
            }
            findNavController().navigate(
                R.id.action_bookmarkFragment_to_detailsFragment, bundle
            )
        }
    }

    override fun handleUI() {
        binding.newsRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.newsRecycler.adapter = latestNewsAdapter
    }


    override fun observeData() {
        viewModel.geBookmark()

        viewModel.allNewsState.observe(requireActivity()) {
            if (it.isEmpty()) {
                binding.errorMessage.visibility = View.VISIBLE
                latestNewsAdapter.differ.submitList(ArrayList())
            } else {
                binding.errorMessage.visibility = View.GONE
                latestNewsAdapter.differ.submitList(it)
            }
        }

        viewModel.deleteArticle.observe(requireActivity()) {
            if (it) {
                viewModel.geBookmark()
                requireContext().showToast(getString(R.string.article_deleted))
            } else {
                requireContext().showToast(getString(R.string.article_not_deleted))
            }
        }
    }
}