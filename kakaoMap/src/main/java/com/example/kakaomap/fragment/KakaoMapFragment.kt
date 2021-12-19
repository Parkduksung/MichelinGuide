package com.example.kakaomap.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.basemaplib.module.base.BaseFragment
import com.example.kakaomap.R
import com.example.kakaomap.databinding.FragmentKakaomapBinding
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class KakaoMapFragment : BaseFragment<FragmentKakaomapBinding>(R.layout.fragment_kakaomap) {

    override fun getCurrentLocation() {
        val getMapCenterPoint =
            binding.kakaomap.mapCenterPoint.mapPointGeoCoord
        Toast.makeText(
            requireContext(),
            "current lat : ${getMapCenterPoint.latitude}, log : ${getMapCenterPoint.longitude}",
            Toast.LENGTH_SHORT
        ).show()
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