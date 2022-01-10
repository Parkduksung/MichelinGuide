package com.example.navermap.fragment

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.basemaplib.module.base.BaseFragment
import com.example.navermap.R
import com.example.navermap.databinding.FragmentNavermapBinding
import com.example.navermap.fragment.api.NaverApi
import com.example.navermap.fragment.api.response.ResultPath
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.LatLngBounds
import com.naver.maps.map.CameraPosition
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

    private val naverApi by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(NaverApi::class.java)
    }

    private lateinit var currentMarker: NaverMarker

    private val markerA =
        NaverMarker(name = "서울시청", mapPoint = LatLng(37.5670135, 126.9783740))
    private val markerB =
        NaverMarker(name = "노원역", mapPoint = LatLng(37.6563403513278, 127.063449137455))

    override fun setMockMarker() {
        addPOIItem(markerA)
        addPOIItem(markerB)
        val cameraUpdate = CameraUpdate.zoomTo(10.0)
        naverMap?.moveCamera(cameraUpdate)
    }

    private fun addPOIItem(naverMarker: NaverMarker) {
        val marker = Marker()
        marker.position = naverMarker.mapPoint
        marker.captionText = naverMarker.name
        marker.map = naverMap
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

        binding.navermap.getMapAsync {
            naverMap = it.apply {
                setOnMapLongClickListener { _, latLng ->
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setMessage("현재 위치부터 클릭한 위치까지의 경로를 맵에 표시하시겠습니까?")
                    builder.setPositiveButton(android.R.string.yes) { _, _ ->


                        val clickMarker = NaverMarker(name = "Click Location", mapPoint = latLng)


                        val callGetPath =
                            naverApi.getPath(
                                APIKEY_ID,
                                APIKEY,
                                "${currentMarker.mapPoint.longitude},${currentMarker.mapPoint.latitude}",
                                "${clickMarker.mapPoint.longitude},${clickMarker.mapPoint.latitude}"
                            )

                        callGetPath.enqueue(object : Callback<ResultPath> {

                            override fun onResponse(
                                call: Call<ResultPath>,
                                response: Response<ResultPath>
                            ) {
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

                                    val latLngBounds = LatLngBounds.Builder()
                                    latLngBounds.include(currentMarker.mapPoint)
                                    latLngBounds.include(clickMarker.mapPoint)

                                    addPOIItem(clickMarker)
                                    val cameraUpdate = CameraUpdate.fitBounds(latLngBounds.build())
                                    naverMap?.moveCamera(cameraUpdate)


                                    binding.time.bringToFront()
                                    binding.time.text =
                                        "소요시간 : ${(it.route.traoptimal[0].summary.duration) / (1000 * 60)}분"
                                }
                            }

                            override fun onFailure(call: Call<ResultPath>, t: Throwable) {

                            }
                        })
                    }

                    builder.setNegativeButton(android.R.string.no) { _, _ ->
                        Toast.makeText(
                            requireContext(),
                            android.R.string.no, Toast.LENGTH_SHORT
                        ).show()
                    }
                    builder.setCancelable(false)
                    builder.show()
                }
            }

        }
    }


    override fun getCurrentLocation(lat: Double, long: Double) {
        currentMarker = NaverMarker(name = "Current Location", mapPoint = LatLng(lat, long))
        addPOIItem(currentMarker)
        val cameraUpdate = CameraUpdate.scrollTo(LatLng(lat, long))
        naverMap?.moveCamera(cameraUpdate)
    }

    override fun showRoute() {


        val callGetPath =
            naverApi.getPath(
                APIKEY_ID,
                APIKEY,
                "${markerA.mapPoint.longitude},${markerA.mapPoint.latitude}",
                "${markerB.mapPoint.longitude},${markerB.mapPoint.latitude}"
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

    data class NaverMarker(
        val name: String,
        val mapPoint: LatLng
    )

    override fun getMapFragment(): Fragment = this

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
        private const val APIKEY_ID = "crkx9i5bze"
        private const val APIKEY = "1UdIei6JwnLH83za4uVJAcmCKgFsqdnfLMIgoHXe"
        private const val BASE_URL = "https://naveropenapi.apigw.ntruss.com/map-direction/"
    }
}