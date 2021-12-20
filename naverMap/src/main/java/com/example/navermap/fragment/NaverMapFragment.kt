package com.example.navermap.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.basemaplib.module.base.BaseFragment
import com.example.navermap.R
import com.example.navermap.databinding.FragmentNavermapBinding
import com.example.navermap.fragment.api.NaverApi
import com.example.navermap.fragment.api.response.ResultPath
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.PathOverlay
import com.naver.maps.map.util.FusedLocationSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NaverMapFragment : BaseFragment<FragmentNavermapBinding>(R.layout.fragment_navermap) {

    private lateinit var locationSource: FusedLocationSource

    private var naverMap: NaverMap? = null

    private val startPosition = LatLng(37.5670135, 126.9783740)
    private val endPosition = LatLng(37.6563403513278, 127.063449137455)

    override fun setMockMarker() {
        addPOIItem(LatLng(37.5670135, 126.9783740), "서울시청")
        addPOIItem(LatLng(37.6563403513278, 127.063449137455), "노원역")
        val cameraUpdate = CameraUpdate.zoomTo(10.0)
        naverMap?.moveCamera(cameraUpdate)
        showRoute()
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

    override fun showRoute() {

        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()

        val api = retrofit.create(NaverApi::class.java)


        val callGetPath =
            api.getPath(
                APIKEY_ID,
                APIKEY,
                "${startPosition.longitude},${startPosition.latitude}",
                "${endPosition.longitude},${endPosition.latitude}"
            )

        callGetPath.enqueue(object : Callback<ResultPath> {

            override fun onResponse(call: Call<ResultPath>, response: Response<ResultPath>) {
                response.body()?.let {
                    val toCoordsList = it.route.traoptimal[0].path.map { location ->
                        LatLng(location[1], location[0])
                    }
                    val path = PathOverlay().apply {
                        coords = toCoordsList
                        color = Color.GREEN
                        width = 10
                    }

                    path.map = naverMap

                    binding.time.bringToFront()
                    binding.time.text =
                        "소요시간 : ${(it.route.traoptimal[0].summary.duration) / (1000 * 60)}분"
                }
            }

            override fun onFailure(call: Call<ResultPath>, t: Throwable) {

            }
        })
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
        private const val APIKEY_ID = "crkx9i5bze"
        private const val APIKEY = "1UdIei6JwnLH83za4uVJAcmCKgFsqdnfLMIgoHXe"
        private const val BASE_URL = "https://naveropenapi.apigw.ntruss.com/map-direction/"
    }
}