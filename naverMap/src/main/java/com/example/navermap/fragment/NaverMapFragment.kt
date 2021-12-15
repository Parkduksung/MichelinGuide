package com.example.navermap.fragment

import androidx.fragment.app.Fragment
import com.example.basemaplib.module.fragment.MapProvider
import com.example.navermap.R

class NaverMapFragment : Fragment(R.layout.fragment_navermap), MapProvider {

    override fun getMapFragment(): Fragment =
        this@NaverMapFragment
}