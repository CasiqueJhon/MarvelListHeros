package com.prueba.tecnica.marvellistheros.features.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.prueba.tecnica.marvellistheros.R

class MainActivity : AppCompatActivity() {

    var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.navHostFragment)

        supportActionBar?.elevation = 0F
    }

    override fun onSupportNavigateUp() = findNavController(R.id.navHostFragment).navigateUp()

}