package com.example.basemaplib.module.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.basemaplib.module.fragment.MapProvider

abstract class BaseFragment<B : ViewDataBinding>(@LayoutRes private val layoutId: Int) :
    Fragment(layoutId),
    MapProvider {

    protected lateinit var binding: B

    override fun getMapFragment(): Fragment = this

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }
}