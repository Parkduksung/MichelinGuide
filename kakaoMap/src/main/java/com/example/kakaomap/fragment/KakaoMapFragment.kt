package com.example.kakaomap.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.basemaplib.module.base.BaseFragment
import com.example.kakaomap.R
import com.example.kakaomap.databinding.FragmentKakaomapBinding
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class KakaoMapFragment : BaseFragment<FragmentKakaomapBinding>(R.layout.fragment_kakaomap) {

    override fun getCurrentLocation() {
        val position = MapPoint.mapPointWithGeoCoord(37.5670135, 126.9783740)
        val mapPOIItem = MapPOIItem()
        mapPOIItem.itemName = "시청"
        mapPOIItem.markerType = MapPOIItem.MarkerType.YellowPin
        mapPOIItem.mapPoint = position
        binding.kakaomap.addPOIItem(mapPOIItem)
        binding.kakaomap.setMapCenterPoint(position, true)
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