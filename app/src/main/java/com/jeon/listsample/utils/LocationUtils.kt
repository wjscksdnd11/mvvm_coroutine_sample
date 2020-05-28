package com.jeon.listsample.utils

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.jeon.listsample.data.LocationInfo
import java.io.IOException
import java.util.*


class LocationUtils {
    companion object {
        @JvmStatic
        fun setLocation(context: Context) {
            getLocationManager(context)?.apply {
                val isGPSEnabled = isProviderEnabled(LocationManager.GPS_PROVIDER)
                val isNetworkEnabled = isProviderEnabled(LocationManager.NETWORK_PROVIDER)
                val locationListener = getListener(context)
                if (ContextCompat.checkSelfPermission(
                        context,
                        ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    val provider =
                        getLastKnownLocation(LocationManager.GPS_PROVIDER) ?: getLastKnownLocation(
                            LocationManager.NETWORK_PROVIDER
                        )
                    provider?.apply {
                        val address = getCurrentAddress(context = context, latitude = latitude, longitude = longitude)
                        setLocationInfo(latitude, longitude, address)
                    }
                    if (isGPSEnabled) {
                        requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            20000,
                            1000f,
                            locationListener
                        )
                    } else if (isNetworkEnabled) {
                        requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            20000,
                            1000f,
                            locationListener
                        )
                    }
                }
                removeUpdates(locationListener)

            }
        }

        private fun getListener(context: Context) = object : LocationListener {
            override fun onLocationChanged(location: Location?) {
                location?.apply {
                    setLocationInfo(latitude, longitude, getCurrentAddress(context,latitude = latitude,longitude = longitude))
                }

            }

            override fun onStatusChanged(
                provider: String?,
                status: Int,
                extras: Bundle?
            ) {
            }

            override fun onProviderEnabled(provider: String?) {
            }

            override fun onProviderDisabled(provider: String?) {
            }
        }
        private fun setLocationInfo(lati: Double, long: Double, address: String?) {
            LocationInfo.longitude = long.toString()
            LocationInfo.latitude = lati.toString()
            address?.apply {
                LocationInfo.address = address
            }

        }

        private fun getCurrentAddress(context: Context, latitude: Double, longitude: Double): String{
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses: List<Address>
            addresses = try {
                geocoder.getFromLocation(
                    latitude,
                    longitude,
                    7
                )
            } catch (ioException: IOException) {
                return "not found location"
            } catch (illegalArgumentException: IllegalArgumentException) {
                return "not found location"
            }
            if (addresses == null || addresses.isEmpty()) {
                return "no search Address"
            }
            val address: Address = addresses[0]
            return address.locality
        }



        private fun getLocationManager(context: Context): LocationManager? =
            (context.getSystemService(Context.LOCATION_SERVICE) as? LocationManager)

    }
}