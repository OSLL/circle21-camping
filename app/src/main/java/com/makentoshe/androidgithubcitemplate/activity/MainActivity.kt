package com.makentoshe.androidgithubcitemplate.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color.WHITE
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.preference.PreferenceManager
import android.util.DisplayMetrics
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.makentoshe.androidgithubcitemplate.R
import com.makentoshe.androidgithubcitemplate.db.DbManager
import org.osmdroid.bonuspack.routing.OSRMRoadManager
import org.osmdroid.bonuspack.routing.Road
import org.osmdroid.bonuspack.routing.RoadManager
import org.osmdroid.config.Configuration
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.library.BuildConfig
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.*
import org.osmdroid.views.overlay.Marker.OnMarkerDragListener
import org.osmdroid.views.overlay.compass.CompassOverlay
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay
import org.osmdroid.views.overlay.gridlines.LatLonGridlineOverlay
import org.osmdroid.views.overlay.gridlines.LatLonGridlineOverlay2
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay


class MainActivity : AppCompatActivity() {
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    val DbManager = DbManager(this)


    private lateinit var map: MapView;

    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main)
        val policy = ThreadPolicy.Builder().permitAll().build()
        Configuration.getInstance().setUserAgentValue("MyOwnUserAgent/1.0");
        StrictMode.setThreadPolicy(policy)

        //handle permissions first, before map is created. not depicted here

        //load/initialize the osmdroid configuration, this can be done
        // This won't work unless you have imported this: org.osmdroid.config.Configuration.*
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
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

        map = findViewById<MapView>(R.id.locationMap)
        map.setTileSource(TileSourceFactory.MAPNIK);
        val n = 59.9333
        val m = 30.3
        onCreate(n, m)


    }

     fun onCreate(n:Double, m:Double) {
        class OnMarkerDragListenerDrawer : OnMarkerDragListener {
            var mTrace: ArrayList<GeoPoint>
            var mPolyline: Polyline
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
                mTrace = ArrayList(100)
                mPolyline = Polyline()
                mPolyline.color = -0x55ffff01
                mPolyline.width = 2.0f
                mPolyline.isGeodesic = true
                map.overlays.add(mPolyline)
            }
        }

        val mapController = map.controller
        DbManager.openDb()
        val datalist1 = DbManager.ReadDbData1()
        val datalist2 = DbManager.ReadDbData2()
        mapController.setZoom(10.5)
        val startPoint = GeoPoint(n, m);
         val geoPoints = ArrayList<GeoPoint>();
         geoPoints.add(startPoint)
        map.maxZoomLevel = 20.0
        map.minZoomLevel = 5.0
        val locationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(this), map);
        locationOverlay.enableMyLocation();
        map.overlays.add(locationOverlay)
        val overlay = LatLonGridlineOverlay2()
        overlay.setBackgroundColor(1)
        map.overlays.add(overlay)
        val rotationGestureOverlay = RotationGestureOverlay(this, map);
        rotationGestureOverlay.isEnabled
        map.setMultiTouchControls(true);
        map.overlays.add(rotationGestureOverlay);
        val button: Button = findViewById(R.id.back)
        button.setOnClickListener {
            val intent = Intent(this, MainActivity_DD::class.java)
            startActivity(intent)
        }

        val marker = Marker(map)
        marker.position = GeoPoint(n, m)
        marker.setDraggable(true)
        marker.setOnMarkerDragListener(OnMarkerDragListenerDrawer())
        marker.icon = ContextCompat.getDrawable(this, R.drawable.maker_icon)
        marker.title = "Test Marker"
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        map.overlays.add(marker)
        map.invalidate()
        var x = 0
        for (v in datalist1) {
            marker.position = GeoPoint(v.toDouble(), datalist2[x].toDouble())
            x += 1
            marker.title = "$x"
            map.overlays.add(marker)
            map.invalidate()
            geoPoints.add(marker.position)
        }


        var y = 0

        for (j in geoPoints) {
            val marker2 = Marker(map)
            marker2.position = GeoPoint(j.latitude, j.longitude)
            marker2.isDraggable = true
            marker2.setOnMarkerDragListener(OnMarkerDragListenerDrawer())
            marker2.icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.maker_icon)
            y += 1
            marker2.title = "$y"
            marker2.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
            map.overlays.add(marker2)

        }
        val roadManager: RoadManager =
            OSRMRoadManager(this@MainActivity, BuildConfig.APPLICATION_ID)
        (roadManager as OSRMRoadManager).setMean(OSRMRoadManager.MEAN_BY_BIKE)
        val road = roadManager.getRoad(geoPoints)
        val roadOverlay = RoadManager.buildRoadOverlay(road)
        map.overlays.add(roadOverlay);

         mapController.setCenter(GeoPoint(geoPoints[geoPoints.lastIndex].latitude, geoPoints[geoPoints.lastIndex].longitude) )


        val line = Polyline();
        var i = y
        val buttondelete: Button = findViewById(R.id.delete)
        buttondelete.setOnClickListener {
            map.overlays.remove(map.overlays.findLast { it -> it is Marker })
            Toast.makeText(
                baseContext,
                geoPoints.lastIndex.toString(),
                Toast.LENGTH_LONG
            ).show()
            map.invalidate()
            DbManager.removeFromDb((geoPoints.lastIndex).toString())
            geoPoints.remove(geoPoints[geoPoints.lastIndex])
            map.overlays.clear()
            onCreate(n, m)
        }


        val mReceive: MapEventsReceiver = object : MapEventsReceiver {
            override fun singleTapConfirmedHelper(p: GeoPoint): Boolean {
                Toast.makeText(
                    baseContext,
                    p.latitude.toString() + " - " + p.longitude,
                    Toast.LENGTH_LONG
                ).show()
                val marker2 = Marker(map)
                marker2.position = GeoPoint(
                    geoPoints[geoPoints.lastIndex].latitude,
                    geoPoints[geoPoints.lastIndex].longitude
                )
                marker2.isDraggable = true
                marker2.setOnMarkerDragListener(OnMarkerDragListenerDrawer())
                marker2.icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.maker_icon)
                marker2.title = "$i"
                i += 1
                marker2.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
                map.overlays.add(marker2)
                marker.position = GeoPoint(p.latitude, p.longitude)
                marker.setDraggable(true)
                marker.setOnMarkerDragListener(OnMarkerDragListenerDrawer())
                marker.icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.maker_icon)
                marker.title = "$i"
                marker.id = "del"
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
                map.overlays.add(marker)
                geoPoints.add(marker.position)
                val roadManager1: RoadManager =
                    OSRMRoadManager(this@MainActivity, BuildConfig.APPLICATION_ID)
                (roadManager1 as OSRMRoadManager).setMean(OSRMRoadManager.MEAN_BY_BIKE)
                val road1 = roadManager1.getRoad(geoPoints)
                val roadOverlay1 = RoadManager.buildRoadOverlay(road1)
                map.overlays.add(roadOverlay1);
                DbManager.openDb()
                DbManager.insertToDb(i.toString(), p.latitude.toString(), p.longitude.toString())
                map.overlays.clear()
                onCreate(n, m)
                return false
            }

            override fun longPressHelper(p: GeoPoint): Boolean {
                return false
            }
        }


        map.overlays.add(MapEventsOverlay(mReceive))
        map.invalidate();

         val strt:Button = findViewById(R.id.startpoint)
         strt.setOnClickListener {
             map.overlays.clear()
             onCreate(geoPoints[1].latitude, geoPoints[1].longitude)
         }
        //

        line.setOnClickListener { pl, mv, gp ->
            Toast.makeText(
                map.context,
                "polyline with " + line.actualPoints.size + " pts was tapped",
                Toast.LENGTH_LONG
            ).show()
            return@setOnClickListener false
        }
        map.overlays.add(line);


    }



    override fun onResume() {
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        map.onResume();//needed for compass, my location overlays, v6.0.0 and up
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

    override fun onDestroy()    {
        super.onDestroy()
        DbManager.closeDb()
    }
}



