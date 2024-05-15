package com.example.neobischallengeandroidapp

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.neobischallengeandroidapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    val binding get() = _binding!!
    private var navController: NavController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Set the status bar background color to white
            window.statusBarColor = ContextCompat.getColor(this, R.color.white)

            // Set the status bar text color to dark
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarLayout)
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHost.navController


        val bottomNav = binding.navView
        bottomNav.setupWithNavController(navController!!)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.basketFragment,
                R.id.historyFragment,
                R.id.infoFragment
            )
        )
        binding.appBarLayout.setupWithNavController(navController!!, appBarConfiguration)

        navController!!.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.infoFragment) {
                binding.appBarLayout.visibility = View.GONE
                binding.navView.visibility = View.VISIBLE
            } else if (destination.id == R.id.detailFragment || destination.id == R.id.bottomDialogFragment) {
                binding.appBarLayout.visibility = View.GONE
                binding.navView.visibility = View.GONE
            } else {
                binding.appBarLayout.visibility = View.VISIBLE
                binding.navView.visibility = View.VISIBLE
            }

            if (destination.id == R.id.basketFragment){
                binding.tvMainName.text = getString(R.string.basket_name)
            } else if (destination.id == R.id.historyFragment) {
                binding.tvMainName.text = getString(R.string.history_name)
            } else {
                binding.tvMainName.text = getString(R.string.home_name)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}