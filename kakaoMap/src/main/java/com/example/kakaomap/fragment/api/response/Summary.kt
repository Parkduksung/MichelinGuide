package com.example.kakaomap.fragment.api.response

data class Summary(
    val bound: BoundX,
    val destination: Destination,
    val distance: Int,
    val duration: Int,
    val fare: Fare,
    val origin: Origin,
    val priority: String,
    val waypoints: List<Any>
)