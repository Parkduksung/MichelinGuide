package com.example.basemaplib.module

interface MapEventListener {

    fun setMockMarker()
    fun showRoute()
    fun getCurrentLocation(lat: Double, long: Double)
}