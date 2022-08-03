package com.azmiradi.news.presentation.fragment.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.azmiradi.news.R
import com.azmiradi.news.data.model.Article
import com.azmiradi.news.databinding.FragmentDetailsBinding
import com.azmiradi.news.presentation.fragment.BaseFragment
import com.azmiradi.news.presentation.ui_handlers.BaseHandlers
import com.azmiradi.news.utils.convertDateFormate
import com.azmiradi.news.utils.showToast
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate),
    BaseHandlers {

    lateinit var article: Article
    private val viewModel: DetailsViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
        handleUI()
    }

    override fun handleUI() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.date.text = article.publishedAt.toString().convertDateFormate()
        binding.article = article

        Glide.with(binding.image.image.context).load(article.urlToImage)
            .placeholder(R.drawable.ic_loading).error(R.drawable.ic_no_image)
            .into(binding.image.image)
    }

    override fun observeData() {
        val args: DetailsFragmentArgs by navArgs()
        article = args.selectedItem


        viewModel.saveArticle.observe(this) {
            if (it) {
                requireContext().showToast(getString(R.string.article_saved))
            } else {
                requireContext().showToast(getString(R.string.article_not_saved))
            }
        }
    }


}