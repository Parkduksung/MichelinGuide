package com.example.michelinlib.factory

import com.example.basemaplib.module.fragment.MapProvider
import com.example.kakaomap.fragment.KakaoMapFragment
import com.example.navermap.fragment.NaverMapFragment

object MapProviderFactory {

    fun create(type: MapType): MapProvider {
        return when (type) {
            MapType.KAKAO -> {
                KakaoMapFragment()
            }
            MapType.NAVER -> {
                NaverMapFragment()
            }
        }
    }
}