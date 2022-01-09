package com.example.michelinguide

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task

class GpsTracker(private val application: Application) {

    //현재의 경도
    private var currentLongitude: Double = 0.0

    //현재의 위도
    private var currentLatitude: Double = 0.0

    private val fusedLocationProviderClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(application)
    }

    private var cancellationTokenSource = CancellationTokenSource()

    @SuppressLint("MissingPermission")
    fun getLocation(): Result<Task<Location>> {
        return try {
            val currentLocationTask: Task<Location> =
                fusedLocationProviderClient.getCurrentLocation(
                    PRIORITY_HIGH_ACCURACY,
                    cancellationTokenSource.token
                )

            Result.Success(currentLocationTask)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }


    //외부에서 현재 위도, 경도 (캡슐화 원리)
    fun getCurrentLongitude(): Double = currentLongitude
    fun getCurrentLatitude(): Double = currentLatitude


    fun onCancel() {
        cancellationTokenSource.cancel()
    }

}