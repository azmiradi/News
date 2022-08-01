package com.azmiradi.news.presentation.fragment

import android.os.Bundle
import android.view.View
import com.azmiradi.news.databinding.FragmentBookmarkBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment : BaseFragment<FragmentBookmarkBinding>(FragmentBookmarkBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}