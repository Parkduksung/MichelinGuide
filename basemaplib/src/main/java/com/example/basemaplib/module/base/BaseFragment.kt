package com.example.basemaplib.module.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.basemaplib.module.fragment.MapProvider

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId), MapProvider {

    override fun getMapFragment(): Fragment = this
}