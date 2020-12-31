package com.example.digikala.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.digikala.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var mBinding: FragmentMapBinding
    private lateinit var mMap: GoogleMap
    private lateinit var mLatLng: LatLng
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentMapBinding.inflate(inflater, container, false)
        mBinding.mapView.onCreate(savedInstanceState)
        mBinding.mapView.getMapAsync(this)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onMapReady(p0: GoogleMap?) {
        if (p0 != null) {
            mMap = p0
        }
        mMap.uiSettings.isMyLocationButtonEnabled = false

        val cameraUpdate: CameraUpdate = CameraUpdateFactory.newLatLngZoom(LatLng(35.6, 51.3), 10F)
        mMap.animateCamera(cameraUpdate)

        mMap.setOnMapLongClickListener {
            mLatLng = it
            Log.d("MAP",it.toString())
            val newCameraUpdate = CameraUpdateFactory.newLatLngZoom(it, 15f)
            val markerOptions = MarkerOptions()
            markerOptions.position(it)
            mMap.clear()
            mMap.animateCamera(newCameraUpdate)
            mMap.addMarker(markerOptions)
        }

    }

    override fun onResume() {
        mBinding.mapView.onResume()
        super.onResume()
    }

    override fun onPause() {
        mBinding.mapView.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mBinding.mapView.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mBinding.mapView.onLowMemory()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                MapFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }

}