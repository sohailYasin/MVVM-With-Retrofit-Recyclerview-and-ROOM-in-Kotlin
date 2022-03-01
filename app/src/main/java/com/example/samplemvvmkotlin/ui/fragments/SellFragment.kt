package com.example.samplemvvmkotlin.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.samplemvvmkotlin.databinding.FragmentSellBinding
import com.example.samplemvvmkotlin.db.Sell
import com.example.samplemvvmkotlin.ui.activities.MainActivity
import com.example.samplemvvmkotlin.ui.adapters.SellAdapter
import com.example.samplemvvmkotlin.viewModels.SellViewModel


class SellFragment : Fragment() {

    private val TAG = "BuyFragment"
    private var _binding: FragmentSellBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModal: SellViewModel
    val adapter = SellAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSellBinding.inflate(inflater, container, false)
        val view = binding.root

        setSellViewModel()
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

    private fun setSellViewModel() {

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(SellViewModel::class.java)

        binding.recyclerview.adapter = adapter
        // on below line we are calling all notes method
        // from our view modal class to observer the changes on list.
        viewModal.allSellItems.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                // on below line we are updating our list.
                if (it.isEmpty()) {
                    addSellItemList()
                }
                adapter.setSellList(it)
            }
        })
    }

    private fun addSellItemList() {
        viewModal.addNote(Sell("Table", "12000", "1", "2"))
        viewModal.addNote(Sell("Tv", "38000", "2", "2"))
        viewModal.addNote(Sell("iPhone X", "150000", "1", "2"))

    }

}