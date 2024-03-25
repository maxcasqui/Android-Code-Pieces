package com.adriand.kcodepieces

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.adriand.kcodepieces.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initDrawer()
    }

    private fun initDrawer() {
        val navView: NavigationView = binding.drawer
        val navHost = supportFragmentManager.findFragmentById(R.id.drawerNavMainContainer) as NavHostFragment

        navView.setupWithNavController(navHost.navController)
    }
}