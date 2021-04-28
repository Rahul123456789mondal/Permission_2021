package com.example.permission

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)
        button.setOnClickListener {
            requestPermissions()
        }
    }

    private fun hasWriteExternalStoragePermission() = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
    private fun hasLocationForegroundPermission() = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED
    private fun hasLocationBackgroundPermission() = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_DENIED

    private fun requestPermissions(){
        var permissionToRequest = mutableListOf<String>()
        if(!hasWriteExternalStoragePermission()){
            permissionToRequest.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if(!hasLocationForegroundPermission()){
            permissionToRequest.add(android.Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if(!hasLocationBackgroundPermission()){
            permissionToRequest.add(android.Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }
        if (permissionToRequest.isNotEmpty()){
            ActivityCompat.requestPermissions(this, permissionToRequest.toTypedArray(), 0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0 && grantResults.isNotEmpty()){
            for (i in grantResults.indices){
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    Log.d("PermissionRequest", "${permissions[i]} granted.")
                }
            }
        }

    }
}