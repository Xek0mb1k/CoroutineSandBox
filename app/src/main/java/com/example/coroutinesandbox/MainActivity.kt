package com.example.coroutinesandbox

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.coroutinesandbox.databinding.ActivityMainBinding
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {

    private var city = ""
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.downloadButton.setOnClickListener {
            loadWithoutCoroutine()
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

    private fun loadWithoutCoroutine(step: Int = 0, obj: Any? = null){
        when (step){
            0 -> {
                binding.progressBar.isVisible = true
                binding.downloadButton.isEnabled = false
                loadCityWithoutCoroutine {
                    loadWithoutCoroutine(1, it)
                }
            }

            1 -> {
                city = obj as String
                binding.cityTextView.text = city
                loadTempWithoutCoroutine(city){
                    loadWithoutCoroutine(2, it)
                }
            }

            2 -> {
                val temp = obj as Int
                binding.tempTextView.text = temp.toString()
                loadHumidityWithoutCoroutine(city){
                    loadWithoutCoroutine(3, it)
                }
            }

            3 -> {
                val hum = obj as Int
                binding.hum.text = hum.toString()
                loadVisibilityWithoutCoroutine(city){
                    loadWithoutCoroutine(4, it)
                }
            }

            4 -> {
                val visibility = obj as Int
                binding.visibility.text = visibility.toString()
                loadWindWithoutCoroutine(city){
                    loadWithoutCoroutine(5, it)
                }
            }

            5 -> {
                val wind = obj as Int
                binding.wind.text = wind.toString()

                binding.progressBar.isVisible = false
                binding.downloadButton.isEnabled = true
            }
        }
    }




    private fun loadCityWithoutCoroutine(callback: (String) -> Unit){
        Handler(Looper.getMainLooper()).postDelayed({
            callback.invoke("Moscow")
        }, 2000)
    }

    private fun loadTempWithoutCoroutine(city: String, callback: (Int) -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            Toast.makeText(this, "Loading temp from this city: $city", Toast.LENGTH_SHORT)
                .show()
            callback.invoke(17)
        }, 3000)
    }

    private fun loadHumidityWithoutCoroutine(city: String, callback: (Int) -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            Toast.makeText(this, "Loading HUMUDUNITY from this city: $city", Toast.LENGTH_SHORT)
                .show()
            callback.invoke(50)
        }, 3000)
    }

    private fun loadVisibilityWithoutCoroutine(city: String, callback: (Int) -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            Toast.makeText(this, "Loading VISIBILITY from this city: $city", Toast.LENGTH_SHORT)
                .show()
            callback.invoke(1100)
        }, 1000)
    }

    private fun loadWindWithoutCoroutine(city: String, callback: (Int) -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            Toast.makeText(this, "Loading WIND from this city: $city", Toast.LENGTH_SHORT)
                .show()
            callback.invoke(2)
        }, 1000)
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
        delay(1000)
        return 5
    }
}
