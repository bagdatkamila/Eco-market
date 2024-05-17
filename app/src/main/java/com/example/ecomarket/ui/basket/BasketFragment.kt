package com.example.ecomarket.ui.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ecomarket.R
import com.example.ecomarket.adapter.BasketAdapter
import com.example.ecomarket.databinding.FragmentBasketBinding
import com.example.ecomarket.room.BasketViewModel

class BasketFragment : Fragment() {

    private var _binding: FragmentBasketBinding? = null
    private lateinit var adapter: BasketAdapter
    private lateinit var basketViewModel: BasketViewModel
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().window.statusBarColor = resources.getColor(R.color.white)
        _binding = FragmentBasketBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = BasketAdapter(requireContext())
        binding.rvSavedProduct.adapter = adapter

        basketViewModel = ViewModelProvider(this).get(BasketViewModel::class.java)

        basketViewModel.allProducts.observe(viewLifecycleOwner, Observer { products ->
            adapter.setData(products)
        })
    }
}