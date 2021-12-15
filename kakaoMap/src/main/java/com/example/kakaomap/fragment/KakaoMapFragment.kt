package com.example.kakaomap.fragment

import androidx.fragment.app.Fragment
import com.example.basemaplib.module.fragment.MapProvider

class KakaoMapFragment : Fragment(), MapProvider {

    override fun getMapFragment(): Fragment =
        this@KakaoMapFragment
}