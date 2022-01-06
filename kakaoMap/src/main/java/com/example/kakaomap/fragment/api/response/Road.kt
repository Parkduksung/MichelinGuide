package com.example.kakaomap.fragment.api.response

data class Road(
    val distance: Int,
    val duration: Int,
    val name: String,
    val traffic_speed: Double,
    val traffic_state: Int,
    val vertexes: List<Double>
)