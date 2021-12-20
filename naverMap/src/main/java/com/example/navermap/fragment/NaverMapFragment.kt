package com.example.navermap.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.basemaplib.module.base.BaseFragment
import com.example.navermap.R
import com.example.navermap.databinding.FragmentNavermapBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource

class NaverMapFragment : BaseFragment<FragmentNavermapBinding>(R.layout.fragment_navermap) {

    private lateinit var locationSource: FusedLocationSource

    private var naverMap: NaverMap? = null

    override fun setMockMarker() {
        addPOIItem(LatLng(37.5670135, 126.9783740), "서울시청")
        addPOIItem(LatLng(37.6563403513278, 127.063449137455), "노원역")
        val cameraUpdate = CameraUpdate.zoomTo(10.0)
        naverMap?.moveCamera(cameraUpdate)
    }

    private fun addPOIItem(latLng: LatLng, name: String) {
        val marker = Marker()
        marker.position = latLng
        marker.captionText = name
        marker.map = naverMap
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

        binding.navermap.getMapAsync {
            naverMap = it
        }
    }

    override fun onStart() {
        super.onStart()
        binding.navermap.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.navermap.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.navermap.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.navermap.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        binding.navermap.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.navermap.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.navermap.onLowMemory()
    }

    override fun getMapFragment(): Fragment = this

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }
}