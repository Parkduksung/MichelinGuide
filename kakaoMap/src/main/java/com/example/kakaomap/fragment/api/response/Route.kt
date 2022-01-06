package com.example.kakaomap.fragment.api.response

data class Route(
    val result_code: Int,
    val result_msg: String,
    val sections: List<Section>,
    val summary: Summary
)