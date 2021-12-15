package com.example.navermap.fragment

import androidx.fragment.app.Fragment
import com.example.basemaplib.module.fragment.MapProvider

class NaverMapFragment : Fragment(), MapProvider {

    override fun getMapFragment(): Fragment =
        this@NaverMapFragment
}