package com.example.basemaplib.module.fragment

import androidx.fragment.app.Fragment

interface MapProvider {
    fun getMapFragment(): Fragment
}