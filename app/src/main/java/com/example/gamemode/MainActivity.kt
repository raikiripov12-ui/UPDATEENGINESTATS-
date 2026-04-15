package com.example.gamemode

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.gamemode.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var active = false
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnToggle.setOnClickListener {
            if (!active) {
                startService(Intent(this, GameService::class.java))
                binding.statusText.text = "Activo 🔥"
                active = true
            } else {
                stopService(Intent(this, GameService::class.java))
                binding.statusText.text = "Inactivo"
                active = false
            }
        }

        updateUI()
    }

    private fun updateUI() {
        handler.post(object : Runnable {
            override fun run() {
                binding.tempText.text = "${TemperatureMonitor.currentTemp} °C"
                binding.performanceText.text =
                    "Lag: ${LagMonitor.lagLevel} | Micro: ${LagMonitor.microStutterLevel}"

                handler.postDelayed(this, 2000)
            }
        })
    }
}
