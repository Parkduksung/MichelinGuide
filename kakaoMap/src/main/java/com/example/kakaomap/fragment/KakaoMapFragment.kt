package com.example.kakaomap.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.basemaplib.module.base.BaseFragment
import com.example.kakaomap.R
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class KakaoMapFragment : BaseFragment(R.layout.fragment_kakaomap) {

    private val mapView by lazy { requireActivity().findViewById<MapView>(R.id.kakaomap) }

    override fun getCurrentLocation() {
        val getMapCenterPoint =
            mapView.mapCenterPoint.mapPointGeoCoord
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

        mapView.setMapViewEventListener(mapViewEventListener)

    }
}