package com.example.drivingschool76

import android.app.Application
import com.yandex.mapkit.MapKitFactory

class DrivingschoolApp : Application() {

    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey("51c79db9-8056-41d9-8974-6029b37eb8ad")
        MapKitFactory.initialize(this)
    }
}