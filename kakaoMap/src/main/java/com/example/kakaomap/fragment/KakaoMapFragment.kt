package com.example.kakaomap.fragment

import androidx.fragment.app.Fragment
import com.example.basemaplib.module.fragment.MapProvider
import com.example.kakaomap.R

class KakaoMapFragment : Fragment(R.layout.fragment_kakaomap), MapProvider {

    override fun getMapFragment(): Fragment =
        this@KakaoMapFragment
}