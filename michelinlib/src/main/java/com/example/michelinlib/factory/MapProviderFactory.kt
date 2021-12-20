package com.example.michelinlib.factory

import com.example.basemaplib.module.fragment.MapProvider
import com.example.kakaomap.fragment.KakaoMapFragment
import com.example.navermap.fragment.NaverMapFragment
import com.rsupport.rv.agent.tmap.TMapFragment

object MapProviderFactory {

    fun create(type: MapType): MapProvider {
        return when (type) {
            MapType.KAKAO -> {
                KakaoMapFragment()
            }
            MapType.NAVER -> {
                NaverMapFragment()
            }
            MapType.TMAP -> {
                TMapFragment()
            }
        }
    }
}