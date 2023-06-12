package com.example.coroutinesandbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.coroutinesandbox.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.downloadButton.setOnClickListener {
            loadData()
        }
    }

    private fun loadData() {
        binding.progressBar.isVisible = true
        binding.downloadButton.isEnabled = false
        val city = loadCity()
        binding.cityTextView.text = city
        val temp = loadTemp(city)
        binding.tempTextView.text = temp.toString()
        binding.progressBar.isVisible = false
        binding.downloadButton.isEnabled = true


    }


    private fun loadCity(): String {
        Thread.sleep(3000)
        return "Moscow"
    }

    private fun loadTemp(city: String): Int {

        Toast.makeText(this, "Loading temp from this city: $city", Toast.LENGTH_SHORT)
            .show()
        Thread.sleep(2000)
        return 17
    }

}