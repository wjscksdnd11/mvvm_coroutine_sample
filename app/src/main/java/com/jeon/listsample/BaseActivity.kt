package com.jeon.listsample

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


abstract class BaseActivity : AppCompatActivity() {
    companion object {
        private const val GPS_ENABLE_REQUEST_CODE = 2001
        private const val PERMISSIONS_REQUEST_CODE = 1001
        private val REQUIRED_PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (checkLocationServiceStatus()) {
            checkRunTimePermission()
        } else {
            showLocationDialog()
        }
    }

    private fun checkLocationServiceStatus(): Boolean {
        return (this.getSystemService(LOCATION_SERVICE) as LocationManager).let {
            it.isProviderEnabled(LocationManager.GPS_PROVIDER)
                    || it.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        if (requestCode == PERMISSIONS_REQUEST_CODE && grantResults.size == REQUIRED_PERMISSIONS.size) {
            val checkResult: Boolean =
                grantResults.find { it == PackageManager.PERMISSION_DENIED }?.let { false } ?: true
            if (checkResult) {
                onPermissionGranted()
            } else {
                onPermissionDenied()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            GPS_ENABLE_REQUEST_CODE -> {
                if (checkLocationServiceStatus()) {
                    checkRunTimePermission()
                }
            }
        }
    }

    private fun checkRunTimePermission() {
        val hasFinePermission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val hasCoarsePermission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
        if (hasFinePermission == PackageManager.PERMISSION_GRANTED && hasCoarsePermission == PackageManager.PERMISSION_GRANTED) {
            onPermissionGranted()
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE)
        }
    }

    private fun showLocationDialog() {
        AlertDialog.Builder(this).apply {
            setTitle(getString(R.string.location_permission_dialog_title))
            setMessage(getString(R.string.location_permission_dialog_msg))
            setCancelable(true)
            setPositiveButton(getString(R.string.dialog_setting_str)) { _, _ -> requestSystemLocation() }
            setNegativeButton(getString(R.string.dialog_cancel_str)) { dialog, _ -> dialog.cancel() }
            create().show()
        }

    }

    private fun requestSystemLocation() {
        startActivityForResult(
            Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS),
            GPS_ENABLE_REQUEST_CODE
        )
    }

    abstract fun onPermissionGranted()
    private fun onPermissionDenied() {
        finish()
    }

}
