package com.example.michelinguide

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.basemaplib.module.fragment.MapProvider
import com.example.michelinguide.databinding.ActivityMainBinding
import com.example.michelinlib.factory.MapProviderFactory
import com.example.michelinlib.factory.MapType
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mapProvider: MapProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.activity = this
        setContentView(binding.root)

    }

    fun createMap(type: MapType) {
        mapProvider = MapProviderFactory.create(type)
        checkPermission { isGranted ->
            if (isGranted) {
                supportFragmentManager.beginTransaction()
                    .replace(binding.containerMap.id, mapProvider.getMapFragment()).commit()
            }
        }
    }

    private fun checkPermission(callback: (Boolean) -> Unit) {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            callback(false)
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 999)
        } else {
            callback(true)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 999) {
            supportFragmentManager.beginTransaction()
                .replace(binding.containerMap.id, mapProvider.getMapFragment()).commit()
        }
    }

    @SuppressLint("PackageManagerGetSignatures")
    private fun getHashKey() {
        var packageInfo: PackageInfo? = null
        try {
            packageInfo =
                packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        if (packageInfo == null) Log.e("KeyHash", "KeyHash:null")
        for (signature in packageInfo!!.signatures) {
            try {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            } catch (e: NoSuchAlgorithmException) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=$signature", e)
            }
        }
    }

}