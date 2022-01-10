package com.example.michelinguide

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.basemaplib.module.MapEventListener
import com.example.basemaplib.module.fragment.MapProvider
import com.example.michelinguide.databinding.ActivityMainBinding
import com.example.michelinlib.factory.MapProviderFactory
import com.example.michelinlib.factory.MapType

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mapProvider: MapProvider

    private lateinit var mapEventListener: MapEventListener

    private lateinit var gpsTracker: GpsTracker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.activity = this
        setContentView(binding.root)
    }

    fun createMap(type: MapType) {
        mapProvider = MapProviderFactory.create(type)
        mapEventListener = mapProvider
        checkPermission { isGranted ->
            if (isGranted) {
                supportFragmentManager.beginTransaction()
                    .replace(binding.containerMap.id, mapProvider.getMapFragment()).commit()
            }
        }
    }

    fun setMockMarker() {
        if (::mapProvider.isInitialized) {
            mapEventListener.setMockMarker()
        } else {
            Toast.makeText(this, "맵선택을 하세요.", Toast.LENGTH_SHORT).show()
        }
    }

    fun showCurrentLocation() {
        if (::mapProvider.isInitialized) {
            gpsTracker = GpsTracker(application)
            when (val result = gpsTracker.getLocation()) {

                is Result.Success -> {
                    result.data.addOnCompleteListener { task ->
                        val location = task.result
                        mapProvider.getCurrentLocation(
                            lat = location.latitude,
                            long = location.longitude
                        )
                    }
                }

                is Result.Error -> {
                    Toast.makeText(this, "현재 위치를 가져올 수 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(this, "맵선택을 하세요.", Toast.LENGTH_SHORT).show()
        }

    }


    fun showRoute() {
        if (::mapProvider.isInitialized) {
            mapEventListener.showRoute()

        } else {
            Toast.makeText(this, "맵선택을 하세요.", Toast.LENGTH_SHORT).show()
        }
    }


    private fun checkPermission(callback: (Boolean) -> Unit) {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            callback(false)
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 999)
        } else {
            callback(true)
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 999) {
            supportFragmentManager.beginTransaction()
                .replace(binding.containerMap.id, mapProvider.getMapFragment()).commit()
        }
    }

}