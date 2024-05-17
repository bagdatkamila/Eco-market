package com.example.ecomarket.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.ecomarket.R
import com.example.ecomarket.adapter.Horizontaladapter
import com.example.ecomarket.adapter.VerticalAdapter
import com.example.ecomarket.databinding.FragmentDetailBinding
import com.example.ecomarket.module.CategoryModel
import com.example.ecomarket.module.DetailModel
import com.example.ecomarket.module.Product
import com.example.ecomarket.room.BasketViewModel
import com.example.ecomarket.ui.home.HomeViewModel
import com.example.ecomarket.utils.Constants
import com.mancj.materialsearchbar.MaterialSearchBar
import org.json.JSONArray


class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    val binding get() = _binding!!
    lateinit var horizonlatAdpater: Horizontaladapter
    lateinit var verticalAdapter: VerticalAdapter

    lateinit var viewModel: HomeViewModel
    private lateinit var basketViewModel: BasketViewModel

    private val args: DetailFragmentArgs by navArgs()
    val arrayList = ArrayList<CategoryModel>()
    val arrayListVertical = ArrayList<DetailModel>()
    val arrayListSearch = ArrayList<DetailModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ivProduct: ImageView = view.findViewById(R.id.iv_product)
        ivProduct.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // Initialize ViewModel
        basketViewModel = ViewModelProvider(this).get(BasketViewModel::class.java)
        // Observe changes in products
        basketViewModel.allProducts.observe(viewLifecycleOwner, Observer { products ->
            // Update your UI or perform actions when products change
        })

        verticalAdapter = VerticalAdapter ({ id ->
            val detailModel = arrayListVertical[id]
            val product = Product(
                name = detailModel.title,
                image = detailModel.image,
                count = detailModel.quantity,
                price = detailModel.price
            )
            basketViewModel.insertProduct(product)
            findNavController().navigate(
                DetailFragmentDirections.actionDetailFragmentToBottomDialogFragment(
                    arrayListVertical[id]
                )
            )
        },
            basketViewModel
        )




        // Observe the LiveData and update UI accordingly
        viewModel.categories.observe(viewLifecycleOwner, Observer { categories ->
            // Update your UI with the categories data
            // For example, you can use an adapter for RecyclerView
            arrayList.addAll(categories)
            val id = args.postionSelected
            horizonlatAdpater.setData(categories, id)
            with(binding.rvCategory) {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = horizonlatAdpater
            }
        })
        // Observe the products LiveData
        viewModel.detailCategory.observe(viewLifecycleOwner, { products ->
            // Update UI with the list of products
            // e.g., update RecyclerView adapter
            arrayListVertical.addAll(products)
            verticalAdapter.setData(products)
            if(!products.isEmpty()) {
                Log.d("Product Info", "Number of products: ${products.size}")
            }
            else {
                Log.d("Product Info", "No products available")
            }
            with(binding.rvVerticalCategory) {
                layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
                adapter = verticalAdapter
            }
        })

        // Make the API call when the activity is created or whenever needed
        viewModel.getAllCategories()
        val name = args.categoryName
        Log.d("TAG", "onViewCreated: $name")
        viewModel.fetchProducts(name!!)

        binding.searchBar.setOnSearchActionListener(object :
            MaterialSearchBar.OnSearchActionListener {
            override fun onSearchStateChanged(enabled: Boolean) {

            }

            override fun onSearchConfirmed(text: CharSequence?) {
                arrayListSearch.clear()
                getProducts(text.toString())
                binding.rvVerticalCategory.visibility = View.GONE
                binding.rvVerticalSearch.visibility = View.VISIBLE
//                findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToSearchFragment(text.toString()))
            }

            override fun onButtonClicked(buttonCode: Int) {

            }

        })
    }

    private fun getProducts(query: String) {
        val queue = Volley.newRequestQueue(requireContext())

        val url = Constants.GET_PRODUCTS_URL + "?search=" + query

        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null, { response ->
            // Handle the response
            Log.e("Response from search", response.toString())
            if (response.length() == 0) {
                binding.llError.visibility = View.VISIBLE
                binding.rvVerticalCategory.visibility = View.GONE
                binding.rvVerticalSearch.visibility = View.GONE
            } else {
                binding.llError.visibility = View.GONE
                handleSearchResponse(response)
            }
        }, { error ->
            // Handle errors
            Log.e("Volley Error", error.toString())
        })
        queue.add(jsonArrayRequest)
    }

    private fun handleSearchResponse(response: JSONArray) {
        arrayListSearch.clear()


        for (i in 0 until response.length()) {
            val product = response.getJSONObject(i)
            val productId = product.getInt("id")
            val productTitle = product.getString("title")
            val productDesc = product.getString("description")
            val productCategory = product.getInt("category")
            val productImage = product.getString("image")
            val productQuantity = product.getInt("quantity")
            val productPrice = product.getDouble("price")

            //  Log.e("Response from search",productTitle.toString())

            arrayListSearch.add(
                DetailModel(
                    productId,
                    productTitle,
                    productDesc,
                    productCategory,
                    productImage,
                    productQuantity,
                    productPrice
                )
            )


        }

        verticalAdapter.setData(arrayListSearch)
//        verticalAdapter = VerticalAdapter { id ->
//            findNavController().navigate(
//                DetailFragmentDirections.actionDetailFragmentToBottomDialogFragment(
//                    arrayListSearch[id]
//                )
//            )
//        }
//        verticalAdapter.setData(arrayListSearch)
//        with(binding.rvVerticalSearch) {
//            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
//            setHasFixedSize(true)
//            adapter = verticalAdapter
//        }


    }
}