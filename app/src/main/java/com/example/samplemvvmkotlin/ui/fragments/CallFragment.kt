package com.example.samplemvvmkotlin.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.samplemvvmkotlin.databinding.FragmentCallBinding
import com.example.samplemvvmkotlin.network.interfaces.RetrofitService

import com.example.samplemvvmkotlin.network.repositories.CallRepository
import com.example.samplemvvmkotlin.ui.activities.MainActivity
import com.example.samplemvvmkotlin.ui.adapters.CallAdapter
import com.example.samplemvvmkotlin.viewModels.CallListViewModel
import com.example.samplemvvmkotlin.viewModels.CallListViewModelFactory
import com.kaopiz.kprogresshud.KProgressHUD


class CallFragment : Fragment() {

    private val TAG = "CallFragment"
    private var _binding: FragmentCallBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: CallListViewModel
    private val retrofitService = RetrofitService.getInstance()
    val adapter = CallAdapter()

    var progressHUD: KProgressHUD? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCallBinding.inflate(inflater, container, false)
        val view = binding.root

        getAndSetData()
        setBackListner()
        return view
    }

    private fun setBackListner() {
        binding.btnBack.setOnClickListener {
            (activity as MainActivity).showFragment(
                DashboardFragment()
            )
        }
    }

    private fun getAndSetData() {
        showProgress()
        viewModel =
            ViewModelProvider(this, CallListViewModelFactory(CallRepository(retrofitService))).get(
                CallListViewModel::class.java
            )
        binding.recyclerview.adapter = adapter
        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "onSuccess: $it")
            adapter.setCallsList(it)
            progressHUD!!.dismiss()
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "OnError: $it")
            progressHUD!!.dismiss()
        })
        viewModel.getAllCalls()
    }


    private fun showProgress() {
        if (progressHUD != null) {
            if (progressHUD!!.isShowing) {
                progressHUD!!.dismiss()
            }
        }
        progressHUD = KProgressHUD.create(context)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setCancellable(false)
            .setAnimationSpeed(2)
            .setDimAmount(0.5f)
            .show()
    }

}