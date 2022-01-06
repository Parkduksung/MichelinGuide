package com.example.kakaomap.fragment.api.response

data class DirectionResponse(
    val routes: List<Route>,
    val trans_id: String
)