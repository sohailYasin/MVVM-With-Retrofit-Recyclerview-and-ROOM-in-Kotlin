package com.example.samplemvvmkotlin.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.samplemvvmkotlin.databinding.FragmentDashboardBinding
import com.example.samplemvvmkotlin.ui.activities.MainActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val view = binding.root

        setClickListners()
        return view

    }


    private fun setClickListners() {

        binding.btnCallList.setOnClickListener {
            (activity as MainActivity).showFragment(
                CallFragment()
            )
        }

        binding.btnBuyList.setOnClickListener { (activity as MainActivity).showFragment(BuyFragment()) }
        binding.btnSellList.setOnClickListener {
            (activity as MainActivity).showFragment(
                SellFragment()
            )
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}