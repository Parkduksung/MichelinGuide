package com.example.michelinguide

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.basemaplib.module.fragment.MapProvider
import com.example.michelinguide.databinding.ActivityMainBinding
import com.example.michelinlib.factory.MapProviderFactory
import com.example.michelinlib.factory.MapType

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mapProvider: MapProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.activity = this
        setContentView(binding.root)
    }

    fun createMap(type: MapType) {
        mapProvider = MapProviderFactory.create(type)
        supportFragmentManager.beginTransaction()
            .replace(binding.containerMap.id, mapProvider.getMapFragment()).commit()
    }

}