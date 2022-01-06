package com.example.kakaomap.fragment.api.response

data class Guide(
    val distance: Int,
    val duration: Int,
    val guidance: String,
    val name: String,
    val road_index: Int,
    val type: Int,
    val x: Double,
    val y: Double
)