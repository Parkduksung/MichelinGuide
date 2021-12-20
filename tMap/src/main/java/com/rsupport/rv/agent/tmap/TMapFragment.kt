package com.rsupport.rv.agent.tmap


import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import com.example.basemaplib.module.base.BaseFragment
import com.rsupport.rv.agent.tmap.databinding.FragmentTmapBinding
import com.skt.Tmap.TMapMarkerItem
import com.skt.Tmap.TMapPoint
import com.skt.Tmap.TMapView


class TMapFragment : BaseFragment<FragmentTmapBinding>(R.layout.fragment_tmap) {

    private val tmapView: TMapView by lazy {
        TMapView(requireContext()).apply {
            setSKTMapApiKey("l7xxa6447d6ca5454d68a5e88e41e52e7c24")
        }
    }

    private fun addMarker(tMapPoint: TMapPoint, name: String) {
        val tMapMarkerItem = TMapMarkerItem().apply {
            setPosition(0.5f, 1.0f)
            this.tMapPoint = tMapPoint
            this.name = name
        }
        tmapView.addMarkerItem(name, tMapMarkerItem)
    }

    override fun setMockMarker() {
        addMarker(TMapPoint(37.5670135, 126.9783740), "서울시청")
        addMarker(TMapPoint(37.6563403513278, 127.063449137455), "노원역")
        tmapView.setZoom(11.0f)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerTmap.addView(tmapView)
    }
}