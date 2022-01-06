package com.example.kakaomap.fragment.api.response

data class Section(
    val bound: Bound,
    val distance: Int,
    val duration: Int,
    val guides: List<Guide>,
    val roads: List<Road>
)