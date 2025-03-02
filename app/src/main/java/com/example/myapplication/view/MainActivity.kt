package com.example.myapplication.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setBottomNavigation()
    }

    private fun setBottomNavigation() {

        val fragmentWithBottomBar = setOf(
            R.id.homeFragment,
            R.id.chartFragment,
            R.id.reportFragment,
            R.id.userFragment
        )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in fragmentWithBottomBar) {
                binding.bottomAppBar.visibility = android.view.View.VISIBLE
                binding.bottomNavigation.visibility = android.view.View.VISIBLE
                binding.addFabBtn.visibility = android.view.View.VISIBLE
            } else {
                binding.bottomAppBar.visibility = android.view.View.GONE
                binding.bottomNavigation.visibility = android.view.View.GONE
                binding.addFabBtn.visibility = android.view.View.GONE
            }
        }

        binding.addFabBtn.setColorFilter(ContextCompat.getColor(this, R.color.black1))

        binding.bottomNavigation.selectedItemId = R.id.bottom_home

        binding.addFabBtn.setOnClickListener {

            val navOptions = NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in_bottom)
                .setExitAnim(R.anim.slide_out_top)
                .setPopEnterAnim(R.anim.slide_in_bottom)
                .setPopExitAnim(R.anim.slide_out_top)
                .build()

            navController.navigate(R.id.addNewCategoryFragment, null, navOptions)
        }


        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_home -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }

                R.id.bottom_chart -> {
                    navController.navigate(R.id.chartFragment)
                    true
                }

                R.id.bottom_report -> {
                    navController.navigate(R.id.reportFragment)
                    true
                }

                R.id.bottom_user -> {
                    navController.navigate(R.id.userFragment)
                    true
                }

                else -> false
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
