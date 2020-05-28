package com.jeon.listsample

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import android.widget.Toast
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.GrantPermissionRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*


@RunWith(AndroidJUnit4::class)
class GeoTest {
    private val context: Context by lazy {
        InstrumentationRegistry.getInstrumentation().targetContext
    }
    @get:Rule
    var permissionRule: GrantPermissionRule =
        GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.INTERNET)

    private fun getLocationManager(): LocationManager?=(context.getSystemService(Context.LOCATION_SERVICE) as? LocationManager)

    @Test
    fun location_test() {
        getLocationManager()?.apply {
            // GPS 프로바이더 사용가능여부
            val isGPSEnabled = isProviderEnabled(LocationManager.GPS_PROVIDER)
            // 네트워크 프로바이더 사용가능여부
            val isNetworkEnabled = isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            val locationProvider = LocationManager.GPS_PROVIDER
            println("isGPSEnabled=$isGPSEnabled, isNetworkEnabled=$isNetworkEnabled")

            getLastKnownLocation(locationProvider)?.also {
                val lng: Double = String.format("%.2f", it.longitude).toDouble()
                val lat: Double = String.format("%.2f", it.latitude).toDouble()
                println("longtitude=$lng, latitude=$lat")
            }
        }
    }

    @Test
    fun get_addr_test() {
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses: List<Address>
        val location = getLocationManager()?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        val latt =location?.latitude?:0.0
        val long =location?.longitude?:0.0
        try {
            addresses = geocoder.getFromLocation(
                latt,
                long,
                7
            )
            println(addresses)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        } catch (illegalArgumentException: IllegalArgumentException) {
            println("잘못된 GPS 좌표")
        }

    }
}