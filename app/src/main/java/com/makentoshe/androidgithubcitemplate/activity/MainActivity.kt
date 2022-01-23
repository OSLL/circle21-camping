package com.makentoshe.androidgithubcitemplate.activity


import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.makentoshe.androidgithubcitemplate.R
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.*
import org.osmdroid.views.overlay.compass.CompassOverlay
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay
import org.osmdroid.views.overlay.gridlines.LatLonGridlineOverlay2
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay
import java.util.*
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import androidx.annotation.RequiresApi
import org.osmdroid.bonuspack.routing.OSRMRoadManager
import org.osmdroid.bonuspack.routing.RoadManager
import org.osmdroid.views.overlay.Polyline
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Marker.OnMarkerDragListener
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.library.BuildConfig


class MainActivity : AppCompatActivity() {
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 1


    private lateinit var map: MapView

    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val policy = ThreadPolicy.Builder().permitAll().build()
        Configuration.getInstance().userAgentValue = "MyOwnUserAgent/1.0"
        StrictMode.setThreadPolicy(policy)

        //handle permissions first, before map is created. not depicted here

        //load/initialize the osmdroid configuration, this can be done
        // This won't work unless you have imported this: org.osmdroid.config.Configuration.*
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))
        //setting this before the layout is inflated is a good idea
        //it 'should' ensure that the map has a writable location for the map cache, even without permissions
        //if no tiles are displayed, you can try overriding the cache path using Configuration.getInstance().setCachePath
        //see also StorageUtils
        //tile servers will get you banned based on this string.
        //Убрал Navigation buttons снизу , открываются по свайпу by Nikita Sosno
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            }
        }
        //inflate and create the map
        setContentView(R.layout.activity_main);

        map = findViewById(R.id.map)
        map.setTileSource(TileSourceFactory.MAPNIK);
        class OnMarkerDragListenerDrawer : OnMarkerDragListener {
            var mTrace: ArrayList<GeoPoint> = ArrayList(100)
            var mPolyline: Polyline = Polyline()
            override fun onMarkerDrag(marker: Marker) {
                //mTrace.add(marker.getPosition());
            }

            override fun onMarkerDragEnd(marker: Marker) {
                mTrace.add(marker.position)
                mPolyline.setPoints(mTrace)
                map.invalidate()
            }

            override fun onMarkerDragStart(marker: Marker) {
                //mTrace.add(marker.getPosition());
            }

            init {
                mPolyline.color = -0x55ffff01
                mPolyline.width = 2.0f
                mPolyline.isGeodesic = true
                map.overlays.add(mPolyline)
            }
        }

        val mapController = map.controller
        mapController.setZoom(10.5)
        val startPoint = GeoPoint(59.9333, 30.3)
        mapController.setCenter(startPoint)
        val locationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(this), map)
        locationOverlay.enableMyLocation()
        map.overlays.add(locationOverlay)
        val overlay = LatLonGridlineOverlay2()
        map.overlays.add(overlay)
        val rotationGestureOverlay = RotationGestureOverlay(this, map)
        rotationGestureOverlay.isEnabled
        map.setMultiTouchControls(true)
        map.overlays.add(rotationGestureOverlay)
        val compassOverlay = CompassOverlay(this, InternalCompassOrientationProvider(this), map)
        compassOverlay.enableCompass()
        map.overlays.add(compassOverlay)
        val dm: DisplayMetrics = this.resources.displayMetrics
        val scaleBarOverlay = ScaleBarOverlay(map)
        scaleBarOverlay.setCentred(true)
//play around with these values to get the location on screen in the right place for your application
        scaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 10)
        map.overlays.add(scaleBarOverlay)
        val minimapOverlay = MinimapOverlay(this, map.tileRequestCompleteHandler)
        minimapOverlay.width = dm.widthPixels / 5
        minimapOverlay.height = dm.heightPixels / 5
//optionally, you can set the minimap to a different tile source
//minimapOverlay.setTileSource(....);
        map.overlays.add(minimapOverlay)
        val marker = Marker(map)
        marker.position = GeoPoint(59.9333, 30.3)
        marker.isDraggable = true
        marker.setOnMarkerDragListener(OnMarkerDragListenerDrawer())
        marker.icon = ContextCompat.getDrawable(this, R.drawable.maker_icon)
        marker.title = "Test Marker"
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        map.overlays.add(marker)
        map.invalidate()
        val geoPoints = ArrayList<GeoPoint>();
        geoPoints.add(startPoint)
        val line = Polyline()
        var i = 1
        val mReceive: MapEventsReceiver = object : MapEventsReceiver {
            override fun singleTapConfirmedHelper(p: GeoPoint): Boolean {
                Toast.makeText(
                    baseContext,
                    p.latitude.toString() + " - " + p.longitude,
                    Toast.LENGTH_LONG
                ).show()
                val marker2 = Marker(map)
                marker2.position = GeoPoint(geoPoints[i-1].latitude, geoPoints[i-1].longitude)
                marker2.isDraggable = true
                marker2.setOnMarkerDragListener(OnMarkerDragListenerDrawer())
                marker2.icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.maker_icon)
                marker2.title = "$i"
                i += 1
                marker2.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
                map.overlays.add(marker2)
                marker.position = GeoPoint(p.latitude, p.longitude)
                marker.isDraggable = true
                marker.setOnMarkerDragListener(OnMarkerDragListenerDrawer())
                marker.icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.maker_icon)
                marker.title = "$i"
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
                map.overlays.add(marker)
                geoPoints.add(marker.position)
                val roadManager: RoadManager = OSRMRoadManager(this@MainActivity, BuildConfig.APPLICATION_ID)
                (roadManager as OSRMRoadManager).setMean(OSRMRoadManager.MEAN_BY_BIKE)
                val road = roadManager.getRoad(geoPoints)
                val roadOverlay = RoadManager.buildRoadOverlay(road)
                map.overlays.add(roadOverlay);






                return false
            }

            override fun longPressHelper(p: GeoPoint): Boolean {
                return false
            }
        }
        map.overlays.add(MapEventsOverlay(mReceive))
        map.invalidate();
        line.setOnClickListener { pl, mv, gp ->
            Toast.makeText(
                map.context,
                "polyline with " + line.actualPoints.size + " pts was tapped",
                Toast.LENGTH_LONG
            ).show()
            return@setOnClickListener false
        }
        map.overlays.add(line)


    }


    override fun onResume() {
        super.onResume()
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        map.onResume()//needed for compass, my location overlays, v6.0.0 and up
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            }
        }


    }
}