package com.example.navermap.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.basemaplib.module.base.BaseFragment
import com.example.navermap.R
import com.example.navermap.databinding.FragmentNavermapBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource

class NaverMapFragment : BaseFragment<FragmentNavermapBinding>(R.layout.fragment_navermap) {

    private lateinit var locationSource: FusedLocationSource

    private var naverMap: NaverMap? = null

    override fun getCurrentLocation() {
        val position = LatLng(37.5670135, 126.9783740)
        val cameraUpdate = CameraUpdate.scrollTo(position)
            .animate(CameraAnimation.Easing, 1000)
            .finishCallback {
                val marker = Marker()
                marker.position = position
                marker.captionText = "시청"
                marker.map = naverMap
            }
            .cancelCallback {
                Toast.makeText(context, "카메라 이동 취소", Toast.LENGTH_SHORT).show()
            }

        naverMap?.moveCamera(cameraUpdate)
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