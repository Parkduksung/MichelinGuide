package com.example.michelinguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.basemaplib.module.fragment.MapProvider
import com.example.michelinlib.factory.MapProviderFactory
import com.example.michelinlib.factory.MapType

class MainActivity : AppCompatActivity() {

    private lateinit var mapProvider: MapProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapProvider = MapProviderFactory.create(MapType.KAKAO)

    }


}