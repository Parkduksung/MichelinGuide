package com.rsupport.rv.agent.tmap


import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.example.basemaplib.module.base.BaseFragment
import com.rsupport.rv.agent.tmap.databinding.FragmentTmapBinding
import com.skt.Tmap.TMapData
import com.skt.Tmap.TMapMarkerItem
import com.skt.Tmap.TMapPoint
import com.skt.Tmap.TMapView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TMapFragment : BaseFragment<FragmentTmapBinding>(R.layout.fragment_tmap) {

    private val tmapView: TMapView by lazy {
        TMapView(requireContext()).apply {
            setSKTMapApiKey("l7xxa6447d6ca5454d68a5e88e41e52e7c24")
        }
    }

    private val markerA =
        TMapMarker(name = "서울시청", mapPoint = TMapPoint(37.5670135, 126.9783740))
    private val markerB =
        TMapMarker(name = "노원역", mapPoint = TMapPoint(37.6563403513278, 127.063449137455))

    private fun addMarker(TMapMarker: TMapMarker) {
        val tMapMarkerItem = TMapMarkerItem().apply {
            setPosition(0.5f, 1.0f)
            this.tMapPoint = TMapMarker.mapPoint
            this.name = TMapMarker.name
        }
        tmapView.addMarkerItem(TMapMarker.name, tMapMarkerItem)
    }

    override fun setMockMarker() {
        addMarker(markerA)
        addMarker(markerB)
        tmapView.setZoom(11.0f)

    }

    override fun showRoute() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val tMapPolyLine = TMapData().findPathData(markerA.mapPoint, markerB.mapPoint)
                tMapPolyLine.lineColor = Color.BLUE
                tMapPolyLine.lineWidth = 10f
                tmapView.addTMapPolyLine("tMapPolyLine", tMapPolyLine)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerTmap.addView(tmapView)
    }

    data class TMapMarker(
        val name: String,
        val mapPoint: TMapPoint
    )
}