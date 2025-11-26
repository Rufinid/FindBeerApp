package com.example.beertry1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val customBeers = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val findBeer = findViewById<Button>(R.id.find_beer)
        val addCustomBeer = findViewById<Button>(R.id.add_custom_beer)
        val customBeerInput = findViewById<EditText>(R.id.custom_beer_input)
        val beerColor = findViewById<Spinner>(R.id.beer_color)
        val brands = findViewById<TextView>(R.id.brands)

        val beerColors = resources.getStringArray(R.array.beer_colors).toMutableList()
        val spinnerAdapter = ArrayAdapter (this, android.R.layout.simple_spinner_item, beerColors)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        beerColor.adapter = spinnerAdapter

        findBeer.setOnClickListener {
            val beerColor = findViewById<Spinner>(R.id.beer_color)
            val color = beerColor.selectedItem
            val beerList = getBeers(color.toString())
            val allBeers = beerList + customBeers
            val brands = findViewById<TextView>(R.id.brands)
            brands.text =  allBeers.joinToString("\n")
        }

        addCustomBeer.setOnClickListener{
            val newBeer = customBeerInput.text.toString().trim()
            if (newBeer.isNotEmpty()) {
                customBeers.add(newBeer)
                customBeerInput.text.clear()
            }
        }
    }

    fun getBeers(color: String): List<String> {
        return when (color) {
            "Light" -> listOf("Jail Pale Ale", "Lager Lite")
            "Amber" -> listOf("Jack Amber", "Red Moose")
            "Brown" -> listOf("Brown Bear Beer", "Bock Brownie")
            else -> listOf("Gout Stout", "Dark Daniel")
        }
    }
}