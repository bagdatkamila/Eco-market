package com.example.neobischallengeandroidapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailVIewModel: ViewModel() {

    private val _count = MutableLiveData<Int>()
    val count: LiveData<Int>
        get() = _count

    // Function to handle the button click and update the price
    init {
        _count.value = 1
    }

    fun incrementCount() {
        _count.value = (_count.value ?: 1) + 1

    }

    fun decrementCount() {
        if (_count.value == 1) {
            return
        } else {
            _count.value = (_count.value ?: 1) - 1
        }
    }
}