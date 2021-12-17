package com.example.basemaplib.module.fragment

import androidx.fragment.app.Fragment
import com.example.basemaplib.module.MapEventListener

interface MapProvider : MapEventListener {
    fun getMapFragment(): Fragment
}