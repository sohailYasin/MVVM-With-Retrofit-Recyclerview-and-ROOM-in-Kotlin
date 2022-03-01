package com.example.samplemvvmkotlin.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.samplemvvmkotlin.databinding.FragmentBuyBinding
import com.example.samplemvvmkotlin.network.interfaces.RetrofitService
import com.example.samplemvvmkotlin.network.repositories.BuyRepository
import com.example.samplemvvmkotlin.ui.activities.MainActivity
import com.example.samplemvvmkotlin.ui.adapters.BuyAdapter
import com.example.samplemvvmkotlin.viewModels.BuyViewModel
import com.example.samplemvvmkotlin.viewModels.BuyViewModelFactory
import com.kaopiz.kprogresshud.KProgressHUD

class BuyFragment : Fragment() {

    private val TAG = "BuyFragment"
    private var _binding: FragmentBuyBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: BuyViewModel
    private val retrofitService = RetrofitService.getInstance()
    val adapter = BuyAdapter()

    var progressHUD: KProgressHUD? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBuyBinding.inflate(inflater, container, false)
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
            ViewModelProvider(this, BuyViewModelFactory(BuyRepository(retrofitService))).get(
                BuyViewModel::class.java
            )
        binding.recyclerview.adapter = adapter
        viewModel.buyList.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "onSuccess: $it")
            adapter.setBuyList(it)
            progressHUD!!.dismiss()
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "OnError: $it")
            progressHUD!!.dismiss()
        })
        viewModel.getAllBuyList()
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