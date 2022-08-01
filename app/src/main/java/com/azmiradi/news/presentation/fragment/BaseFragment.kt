package com.azmiradi.news.presentation.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB: ViewDataBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

 }

//
//abstract class BaseFragment<T : ViewDataBinding?> : Fragment() {
//    lateinit var spotsDialog: SpotsDialog
//    var binding: T? = null
//
//
//    @get:LayoutRes
//    abstract val layoutId: Int
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
//        return binding.getRoot()
//    }
////    override fun onCreateView(
////        inflater: LayoutInflater,
////        container: ViewGroup?,
////        savedInstanceState: Bundle?
////    ): View {
////
////        spotDialog()
////        onFragmentCreated(binding)
////        return binding.getRoot()
////    }
//
//    abstract fun onFragmentCreated(dataBinder: T?)
//    private fun spotDialog() {
//        spotsDialog = SpotsDialog(requireContext(), getString(R.string.loading))
//        spotsDialog.setCancelable(false)
//        spotsDialog.dismiss()
//    }
//
//    fun openActivityWithBundle(clas: Class<*>?, bundle: Bundle?) {
//        val intent = Intent(requireContext(), clas)
//        intent.putExtras(bundle!!)
//        startActivity(intent)
//    }
//
//    fun showSnake(message: String?) {
//        val snackbar = Snackbar
//            .make(binding.getRoot(), message!!, Snackbar.LENGTH_LONG)
//        val color: Int
//        val sbView = snackbar.view
//        val textView = sbView.findViewById<View>(R.id.snackbar_text) as TextView
//        color = Color.WHITE
//        textView.setTextColor(color)
//        textView.textSize = 18f
//        textView.gravity = Gravity.CENTER
//        snackbar.show()
//    }
//
//
//}

