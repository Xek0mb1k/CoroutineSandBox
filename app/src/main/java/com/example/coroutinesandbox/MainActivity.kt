package com.example.coroutinesandbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.coroutinesandbox.databinding.ActivityMainBinding
import kotlin.concurrent.thread

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
        loadCity{ city: String ->
            binding.cityTextView.text = city

            loadTemp(city){ temp: Int ->
                loadHumidity(city){
                    Log.d("debug", it.toString())
                    loadPressure(city){
                        Log.d("debug", it.toString())
                        loadVisibility(city){
                            Log.d("debug", it.toString())
                            loadWind(city){
                                Log.d("debug", it.toString())
                                binding.tempTextView.text = temp.toString()
                                binding.progressBar.isVisible = false
                                binding.downloadButton.isEnabled = true
                            }
                        }
                    }
                }
            }
        }
    }

    private fun loadCity(callBack: (String) -> Unit) {
        thread {
            Thread.sleep(3000)
            callBack.invoke("Moscow")
        }
    }

    private fun loadTemp(city: String, callBack: (Int) -> Unit) {

        Toast.makeText(this, "Loading temp from this city: $city", Toast.LENGTH_SHORT)
            .show()
        Thread.sleep(1000)
        callBack.invoke(17)
    }

    private fun loadPressure(city: String, callBack: (Int) -> Unit){
        Toast.makeText(this, "Loading PRESSURE from this city: $city", Toast.LENGTH_SHORT)
            .show()
        Thread.sleep(1000)
        callBack.invoke(500)
    }

    private fun loadHumidity(city: String, callBack: (Int) -> Unit){
        Toast.makeText(this, "Loading temp from this city: $city", Toast.LENGTH_SHORT)
            .show()
        Thread.sleep(1000)
        callBack.invoke(17)
    }

    private fun loadVisibility(city: String, callBack: (Int) -> Unit){
        Toast.makeText(this, "Loading visibility from this city: $city", Toast.LENGTH_SHORT)
            .show()
        Thread.sleep(1000)
        callBack.invoke(2000)
    }

    private fun loadWind(city: String, callBack: (Int) -> Unit){
        Toast.makeText(this, "Loading wind from this city: $city", Toast.LENGTH_SHORT)
            .show()
        Thread.sleep(3000)
        callBack.invoke(5)
    }
}
