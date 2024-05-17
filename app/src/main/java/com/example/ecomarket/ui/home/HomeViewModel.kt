package com.example.ecomarket.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomarket.module.CategoryModel
import com.example.ecomarket.module.DetailModel
import com.example.ecomarket.utils.Constants
import kotlinx.coroutines.launch


class HomeViewModel : ViewModel() {
    private val _categories = MutableLiveData<List<CategoryModel>>()
    val categories: LiveData<List<CategoryModel>> get() = _categories

    private val _detailCategory = MutableLiveData<List<DetailModel>>()
    val detailCategory: LiveData<List<DetailModel>> get() = _detailCategory



    // Function to make the API call and update the LiveData
    fun getAllCategories() {
        viewModelScope.launch {
            try {
                val response = Constants.apiService.getAllCategories()
                _categories.value = response
            } catch (e: Exception) {
                // Handle error (e.g., show an error message)
                Log.e("YourViewModel", "Error: ${e.message}", e)
            }
        }
    }
    fun fetchProducts(categoryName: String) {
        viewModelScope.launch {
            try {

                val response = Constants.apiService.getProducts(categoryName)
                val detailModelList: MutableList<DetailModel> = mutableListOf()

                // Извлекаем список результатов для каждого объекта ProductList
                val results: List<DetailModel> = response.results

                // Добавляем все объекты из списка результатов в общий список detailModelList
                detailModelList.addAll(results)

                Log.d("HomeViewModel", "Response: $response")
                _detailCategory.value = detailModelList
            } catch (e: Exception) {
                // Handle the error
                Log.e("HomeViewModel", "Error fetching products: ${e.message}", e)
            }
        }
    }
}