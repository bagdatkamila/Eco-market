package com.example.ecomarket.app

import android.app.Application
import com.android.volley.toolbox.Volley


class ShoppingApp :Application() {
    // Initialize Volley in your custom Application class
    val requestQueue by lazy {
        Volley.newRequestQueue(this)
    }
}