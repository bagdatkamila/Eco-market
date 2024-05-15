package com.example.neobischallengeandroidapp.app

import android.app.Application
import com.android.volley.toolbox.Volley
import dagger.hilt.android.HiltAndroidApp


class ShoppingApp :Application() {
    // Initialize Volley in your custom Application class
    val requestQueue by lazy {
        Volley.newRequestQueue(this)
    }
}