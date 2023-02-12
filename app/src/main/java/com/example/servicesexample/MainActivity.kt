package com.example.servicesexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.servicesexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val activityMainBinding by lazy{ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)


        activityMainBinding.btnStart.setOnClickListener {
            Intent(this, MyService::class.java).also {
                startService(it)
                activityMainBinding.tvServiceInfo.text = "Service running"
            }
        }

        activityMainBinding.btnStop.setOnClickListener {
            Intent(this, MyService::class.java).also {
                stopService(it)
                Log.d("MyService", "Service has stopped...")
                activityMainBinding.tvServiceInfo.text = "Service stopped"
            }
        }

        activityMainBinding.btnSendData.setOnClickListener {
            Intent(this, MyService::class.java).also {
                val dataString = activityMainBinding.etData.text.toString()
                it.putExtra("EXTRA_DATA", dataString)
                startService(it)
            }
        }
    }
}