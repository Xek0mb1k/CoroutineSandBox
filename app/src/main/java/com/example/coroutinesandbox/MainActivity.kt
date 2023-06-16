package com.example.coroutinesandbox

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.coroutinesandbox.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.downloadButton.setOnClickListener {

            lifecycleScope.launch {
                loadData()
            }

        }
    }

    private suspend fun loadData() {
        binding.progressBar.isVisible = true
        binding.downloadButton.isEnabled = false
        val city = loadCity()
        binding.cityTextView.text = city



        val humidity = loadHumidity(city)
        Log.d("debug", humidity.toString())
        val temp = loadTemp(city)
        Log.d("debug", temp.toString())
        binding.tempTextView.text = temp.toString()
        val vis = loadVisibility(city)
        Log.d("debug", vis.toString())
        val wind = loadWind(city)
        Log.d("debug", wind.toString())


        binding.progressBar.isVisible = false
        binding.downloadButton.isEnabled = true

    }

    private suspend fun loadCity(): String {
        delay(3000)
        return "Moscow"
    }

    private suspend fun loadTemp(city: String): Int {

        Toast.makeText(this, "Loading temp from this city: $city", Toast.LENGTH_SHORT)
            .show()
        delay(1000)
        return 17
    }

    private suspend fun loadHumidity(city: String): Int {
        Toast.makeText(this, "Loading HUMIDITY from this city: $city", Toast.LENGTH_SHORT)
            .show()
        delay(1000)
        return 17
    }

    private suspend fun loadVisibility(city: String): Int {
        Toast.makeText(this, "Loading visibility from this city: $city", Toast.LENGTH_SHORT)
            .show()
        delay(1000)
        return 2000
    }

    private suspend fun loadWind(city: String): Int {
        Toast.makeText(this, "Loading wind from this city: $city", Toast.LENGTH_SHORT)
            .show()
        delay(3000)
        return 5
    }
}
