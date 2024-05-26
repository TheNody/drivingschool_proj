package com.example.drivingschool76.components

import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.drivingschool76.map.data.PointsData
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil
import com.yandex.mapkit.Animation
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView


@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context).apply {
            mapWindow.map.move(
                CameraPosition(Point(57.626559, 39.893813), 11.0f, 0.0f, 0.0f),
                Animation(Animation.Type.SMOOTH, 1f), null
            )
        }
    }
    val lifecycleObserver = rememberMapLifecycleObserver(mapView)
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle) {
        Log.d("MapViewLifecycle", "Adding lifecycle observer")
        lifecycle.addObserver(lifecycleObserver)
        onDispose {
            Log.d("MapViewLifecycle", "Removing lifecycle observer")
            lifecycle.removeObserver(lifecycleObserver)
        }
    }
    return mapView
}

@Composable
fun rememberMapLifecycleObserver(mapView: MapView): LifecycleEventObserver =
    remember(mapView) {
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> mapView.onStart()
                Lifecycle.Event.ON_PAUSE -> mapView.onStop()
                else -> {}
            }
        }
    }

@Suppress("DEPRECATION")
@Composable
fun rememberUserLocation(mapView: MapView): MutableState<Pair<Point?, Double>> {
    val context = LocalContext.current
    val locationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    var mapPoint by remember { mutableStateOf<Point?>(null) }
    val nearestDistance by remember { mutableDoubleStateOf(Double.MAX_VALUE) }

    val locationCallback = remember {
        object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                result.locations.lastOrNull()?.let { newLocation ->
                    val newPoint = Point(newLocation.latitude, newLocation.longitude)
                    mapPoint = newPoint
                    moveToNearestPointAndLog(mapView, newLocation, PointsData.points.map { it.point })
                }
            }
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted: Boolean ->
            if (isGranted) {
                val locationRequest = LocationRequest.create().apply {
                    interval = 60000
                    fastestInterval = 30000
                    priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                }

                if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationClient.requestLocationUpdates(locationRequest, locationCallback, context.mainLooper)
                }
            }
        }
    )

    DisposableEffect(locationClient, locationCallback) {
        onDispose {
            locationClient.removeLocationUpdates(locationCallback)
        }
    }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
    }

    return remember { mutableStateOf(Pair(mapPoint, nearestDistance)) }
}

fun findNearestPoint(currentPoint: Point, points: List<Point>): Pair<Point, Double> {
    var nearestPoint: Point = points.first()
    var nearestDistance = Double.MAX_VALUE

    points.forEach { point ->
        val distance = SphericalUtil.computeDistanceBetween(
            LatLng(currentPoint.latitude, currentPoint.longitude),
            LatLng(point.latitude, point.longitude)
        )
        if (distance < nearestDistance) {
            nearestDistance = distance
            nearestPoint = point
        }
    }
    return Pair(nearestPoint, nearestDistance)
}

fun moveToNearestPointAndLog(mapView: MapView, currentLocation: Location, points: List<Point>) {
    val currentPoint = Point(currentLocation.latitude, currentLocation.longitude)
    val (nearestPoint, distance) = findNearestPoint(currentPoint, points)

    mapView.mapWindow.map.move(
        CameraPosition(nearestPoint, 14.0f, 0.0f, 0.0f),
        Animation(Animation.Type.SMOOTH, 1f), null
    )

    Log.d("Nearest Point", "Вы успешно перенесены в точку: ${nearestPoint.latitude}, ${nearestPoint.longitude}, Расстояние: $distance метров")

}




