package com.lich.minskattractions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var item: Item
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val id_map=intent.getSerializableExtra("map_id") as Int
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        val db=MainDB.getDb(this@MapActivity)

        Thread{
            item = db.getDao().getItById(id_map)
        }.start()
        Log.d("MyTag","${id_map}")

    }




    override fun onMapReady(googleMap: GoogleMap) {
        val place = LatLng(item.lat, item.longit)
        googleMap.addMarker(
            MarkerOptions()
                .position(place)
                .title("Marker in Sydney")
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place,16f))
    }
}
