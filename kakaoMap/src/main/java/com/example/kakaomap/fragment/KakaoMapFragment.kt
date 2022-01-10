package com.example.kakaomap.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.basemaplib.module.base.BaseFragment
import com.example.kakaomap.R
import com.example.kakaomap.databinding.FragmentKakaomapBinding
import com.example.kakaomap.fragment.api.KakaoApi
import com.example.kakaomap.fragment.api.response.DirectionResponse
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class KakaoMapFragment : BaseFragment<FragmentKakaomapBinding>(R.layout.fragment_kakaomap) {

    override fun setMockMarker() {
        addPOIItem(
            MapPoint.mapPointWithGeoCoord(37.5670135, 126.9783740),
            "서울시청"
        )

        addPOIItem(
            MapPoint.mapPointWithGeoCoord(37.6563403513278, 127.063449137455),
            "노원역"
        )

        binding.kakaomap.setZoomLevel(7, true)
    }

    private fun addPOIItem(mapPoint: MapPoint, name: String) {
        val mapPOIItem = MapPOIItem().apply {
            itemName = name
            markerType = MapPOIItem.MarkerType.RedPin
            this.mapPoint = mapPoint
        }
        binding.kakaomap.addPOIItem(mapPOIItem)
    }

    override fun showRoute() {

        val callDirection = Retrofit.Builder().baseUrl("https://apis-navi.kakaomobility.com/")
            .addConverterFactory(GsonConverterFactory.create()).build().create(KakaoApi::class.java)

        Log.d("결과", "여기나옴?!")


        callDirection.getDirection("126.9783740,37.5670135", "127.063449137455,37.6563403513278").enqueue(object  : Callback<DirectionResponse>{
            override fun onResponse(
                call: Call<DirectionResponse>,
                response: Response<DirectionResponse>
            ) {
                Log.d("결과", "성공")

                response.body()?.let {
                    Log.d("결과", it.toString())
                }
//                response.body()?.let {
//                    Log.d("결과", "여기나옴?")
//                    val t = it.routes[0].sections[0].roads.map { it.vertexes }
//
//                    t.forEach {
//                        Log.d("결과", it.size.toString())
//                    }
//
//                }
            }

            override fun onFailure(call: Call<DirectionResponse>, t: Throwable) {
                Log.d("결과", "실패")
                Log.d("결과", t.message.toString())
            }
        })

    }

    override fun getCurrentLocation(lat: Double, long: Double) {

    }

    private val mapViewEventListener = object : MapView.MapViewEventListener {
        override fun onMapViewInitialized(p0: MapView?) {

        }

        override fun onMapViewCenterPointMoved(p0: MapView?, p1: MapPoint?) {

        }

        override fun onMapViewZoomLevelChanged(p0: MapView?, p1: Int) {

        }

        override fun onMapViewSingleTapped(p0: MapView?, p1: MapPoint?) {

        }

        override fun onMapViewDoubleTapped(p0: MapView?, p1: MapPoint?) {

        }

        override fun onMapViewLongPressed(p0: MapView?, p1: MapPoint?) {

        }

        override fun onMapViewDragStarted(p0: MapView?, p1: MapPoint?) {

        }

        override fun onMapViewDragEnded(p0: MapView?, p1: MapPoint?) {

        }

        override fun onMapViewMoveFinished(p0: MapView?, p1: MapPoint?) {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.kakaomap.setMapViewEventListener(mapViewEventListener)
    }
}