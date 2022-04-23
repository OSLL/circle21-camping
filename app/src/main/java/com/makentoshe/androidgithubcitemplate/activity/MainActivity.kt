package com.makentoshe.androidgithubcitemplate.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.preference.PreferenceManager
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.makentoshe.androidgithubcitemplate.R
import com.makentoshe.androidgithubcitemplate.db.DbManager
import org.osmdroid.bonuspack.routing.OSRMRoadManager
import org.osmdroid.bonuspack.routing.RoadManager
import org.osmdroid.config.Configuration
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.library.BuildConfig
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Marker.OnMarkerDragListener
import org.osmdroid.views.overlay.Polyline
import org.osmdroid.views.overlay.ScaleBarOverlay
import org.osmdroid.views.overlay.compass.CompassOverlay
import org.osmdroid.views.overlay.compass.IOrientationProvider
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay
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
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
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
        var n = 59.9333
        var m = 30.3
        val z = 10.5
        DbManager.openDb()
        val datalist1 = DbManager.ReadDbData1()
        val datalist2 = DbManager.ReadDbData2()
        if(datalist1.size != 0){
            n = datalist1[0].toDouble()
            m = datalist2[0].toDouble()
        }
        else{
            n = 59.9333
            m = 30.3
            DbManager.insertToDb(0.toString(), n.toString(), m.toString())
        }


        onCreate(n, m, z)


    }

     fun onCreate(n:Double, m:Double, z:Double) {
         val startPoint = GeoPoint(n, m)
         val mTrace = ArrayList<GeoPoint>();
         val geoPoints = ArrayList<GeoPoint>();
         geoPoints.add(startPoint)
         var num = 0
         DbManager.openDb()
         val datalist1 = DbManager.ReadDbData1()
         val datalist2 = DbManager.ReadDbData2()


         val marker = Marker(map)
         marker.position = GeoPoint(n, m)
         marker.setDraggable(true)
         marker.icon = ContextCompat.getDrawable(this, R.drawable.maker_icon)

         marker.title = "1"
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




        class OnMarkerDragListenerDrawer : OnMarkerDragListener {
            override fun onMarkerDrag(marker: Marker) {
               marker.setTextIcon(marker.position.toDoubleString())
            }

            override fun onMarkerDragEnd(marker: Marker) {
                mTrace.add(marker.position)
                val roadManager: RoadManager =
                    OSRMRoadManager(this@MainActivity, BuildConfig.APPLICATION_ID)
                (roadManager as OSRMRoadManager).setMean(OSRMRoadManager.MEAN_BY_BIKE)
                val road = roadManager.getRoad(mTrace)
                val roadOverlay = RoadManager.buildRoadOverlay(road)
                map.overlays.add(roadOverlay);
                val startmarker = Marker(map)
                startmarker.position = GeoPoint(mTrace[mTrace.lastIndex-1])
                marker.icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.maker_icon2)
                map.overlays.add(marker)
                map.overlays.add(startmarker)

            }
            var num = 0
            override fun onMarkerDragStart(marker: Marker) {
                if (num == 0){
                    var strt = marker.position
                    var strtnum = 0
                    for (i in geoPoints){
                        if(i.toDoubleString() == strt.toDoubleString()){
                            strtnum = geoPoints.lastIndexOf(i)
                        }
                    }
                    for(poryadok in 0..strtnum-1){
                        mTrace.add(GeoPoint(datalist1[poryadok].toDouble(), datalist2[poryadok].toDouble()))
                    }
                    mTrace.add(strt)
                    num+=1
                }
            }

        }
         marker.setOnMarkerDragListener(OnMarkerDragListenerDrawer())


        val mapController = map.controller
         val dm: DisplayMetrics = this.resources.displayMetrics
         class MyCompassOverlay : CompassOverlay {

             constructor(context: Context?, mapView: MapView) : super(context, mapView)

             constructor(context: Context?, iOrientationProvider: IOrientationProvider, mapView: MapView) : super(
                 context,
                 iOrientationProvider,
                 mapView
             )

             override fun onLongPress(e: MotionEvent?, mapView: MapView?): Boolean {
                 map.mapOrientation = 0f
                 return super.onLongPress(e, map)
             }

         }
         val compass = MyCompassOverlay(this, map)
         compass.enableCompass()
         compass.setCompassCenter(100F,dm.heightPixels.toFloat()/2.6F)

         map.overlays.add(compass)

         val scaleBarOverlay = ScaleBarOverlay(map)
         scaleBarOverlay.setAlignBottom(true)
         scaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 10);
         map.overlays.add(scaleBarOverlay);
        mapController.setZoom(z)
        map.maxZoomLevel = 20.0
        map.minZoomLevel = 5.0
        val locationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(this), map);
        locationOverlay.enableMyLocation()
         val bitmap = Bitmap.createBitmap(
             100, 100,
             Bitmap.Config.ARGB_8888
         )
         locationOverlay.setPersonIcon(bitmap)
        map.overlays.add(locationOverlay)
        val overlay = LatLonGridlineOverlay2()
        overlay.setBackgroundColor(0)
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



         val roadManager: RoadManager =
             OSRMRoadManager(this@MainActivity, BuildConfig.APPLICATION_ID)
         (roadManager as OSRMRoadManager).setMean(OSRMRoadManager.MEAN_BY_BIKE)
         val road = roadManager.getRoad(geoPoints)
         val roadOverlay = RoadManager.buildRoadOverlay(road)
         map.overlays.add(roadOverlay);
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
            val z = map.zoomLevelDouble
            onCreate(n, m, z)
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
                val z = map.zoomLevelDouble
                onCreate(n, m, z)
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
             val n = geoPoints[geoPoints.lastIndex].latitude
             val m = geoPoints[geoPoints.lastIndex].longitude
             for (i  in 0..geoPoints.lastIndex){
                 DbManager.removeFromDb((geoPoints.lastIndex).toString())
                 geoPoints.remove(geoPoints[geoPoints.lastIndex])
         }
             DbManager.insertToDb(0.toString(), n.toString(), m.toString())
             val z = map.zoomLevelDouble
             onCreate(n, m, z)
         }
         val clear: Button = findViewById(R.id.clear)
         clear.setOnClickListener {
             map.overlays.clear()
             val n = geoPoints[0].latitude
             val m = geoPoints[0].longitude
             for (i  in 1..geoPoints.lastIndex){
                 DbManager.removeFromDb((geoPoints.lastIndex).toString())
                 geoPoints.remove(geoPoints[geoPoints.lastIndex])
             }
             DbManager.insertToDb(0.toString(), n.toString(), m.toString())
             val z = map.zoomLevelDouble
             onCreate(n, m, z)
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

         val compare: Button = findViewById(R.id.compare)
         compare.setOnClickListener {
             map.overlays.clear()
             val roadManager: RoadManager =
                 OSRMRoadManager(this@MainActivity, BuildConfig.APPLICATION_ID)
             (roadManager as OSRMRoadManager).setMean(OSRMRoadManager.MEAN_BY_BIKE)
             val roadmain = roadManager.getRoad(geoPoints)
             val roadad = roadManager.getRoad(mTrace)
            if(roadmain.mLength >= roadad.mLength){
                val z = map.zoomLevelDouble
                val n = geoPoints[0].latitude
                val m = geoPoints[0].longitude
                for (i  in 1..geoPoints.lastIndex){
                    DbManager.removeFromDb((geoPoints.lastIndex).toString())
                    geoPoints.remove(geoPoints[geoPoints.lastIndex])
                }
                var c = 0
                for (i in mTrace){
                    DbManager.insertToDb(c.toString(), i.latitude.toString(), i.longitude.toString())
                    c+=1
                }
                onCreate(n,m,z)
            }
             else{
                 mTrace.clear()
                val z = map.zoomLevelDouble
                val n = geoPoints[0].latitude
                val m = geoPoints[0].longitude
                onCreate(n,m,z)
             }

         }


    }



    override fun onResume() {
        super.onResume();
        map.onResume();
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



