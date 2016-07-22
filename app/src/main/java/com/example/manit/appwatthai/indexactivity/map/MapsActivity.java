package com.example.manit.appwatthai.indexactivity.map;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.manit.appwatthai.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private TextView latTextView, lngTextView;
    private Spinner spinner;
    private LocationManager locationManager;
    private Criteria criteria;
    private double latADouble, lngADouble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        ImageButton back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });
//Bind Widget
        bindWidget();

        //Setup location
        setUpLocation();

        List< String > list = new ArrayList< String >( );

        list.add ( "วัดกระจับพินิจ" );
        list.add ( "วัดกลางดาวคนอง" );
        list.add ( "วัดกันตทาราราม" );
        list.add ( "วัดกัลยาณมิตรวรมหาวิหาร" );
        list.add ( "วัดจันทารามวรวิหาร" );
        list.add ( "วัดดาวคนอง" );
        list.add ( "วัดบางน้ำชน" );
        list.add ( "วัดบางสะแกนอก" );
        list.add ( "วัดบางสะแกใน" );
        list.add ( "วัดบางไส้ไก่" );
        list.add ( "วัดบุคคโล" );
        list.add ( "วัดบุปผารามวรวิหาร" );
        list.add ( "วัดประดิษฐาราม" );
        list.add ( "วัดประยุรวงศาวาสวรวิหาร" );
        list.add ( "วัดโพธินิมิตสถิตมหาสีมาราม" );
        list.add ( "วัดราชคฤห์วรวิหาร" );
        list.add ( "วัดราชวรินทร์" );
        list.add ( "วัดวรามาตยภัณฑสาราราม" );
        list.add ( "วัดเวฬุราชิณ" );
        list.add ( "วัดสันติธรรมาราม" );
        list.add ( "วัดสุทธาวาส" );
        list.add ( "วัดหิรัญรูจีวรวิหาร" );
        list.add ( "วัดใหญ่ศรีสุพรรณ" );
        list.add ( "วัดใหม่ยายนุ้ย" );
        list.add ( "วัดอินทารามวรวิหาร" );

        ArrayAdapter< String > dataAdapter = new ArrayAdapter< String >( this, android.R.layout.simple_spinner_item, list );

        Spinner spinner = (Spinner) this.findViewById ( R.id.spinner );
        spinner.setAdapter ( dataAdapter );


    } // Main Method


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        locationManager.removeUpdates(locationListener);
        latADouble = 13.716630;
        lngADouble = 100.487487; //พิกัดฝั่งธนบุรี

        //ค้นหาจาก เบอร์ไอพีหรือจากเน็ตเวิค
        Location networkLocation = myFindLocation(LocationManager.NETWORK_PROVIDER);
        if(networkLocation != null){
            latADouble = networkLocation.getLatitude();
            lngADouble = networkLocation.getLongitude();
        }

        //ค้นหาจาก card gps
        Location gpsLocation = myFindLocation(LocationManager.GPS_PROVIDER);
        if(gpsLocation != null){
            latADouble = gpsLocation.getLatitude();
            lngADouble = gpsLocation.getLongitude();
        }
        latTextView.setText(String.format("%.7f", latADouble));
        lngTextView.setText(String.format("%.7f", lngADouble));
    } // Onresume


    @Override
    protected void onStop() {
        super.onStop();

        locationManager.removeUpdates(locationListener);
    }

    public Location myFindLocation(String strProvider) {

        Location location = null;

        if (locationManager.isProviderEnabled(strProvider)) {

            locationManager.requestLocationUpdates(strProvider, 1000, 10, locationListener);

        } else {
            Log.d("3JuneV1", "my Error");
        }


        return location;
    }

    public LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            latADouble = location.getLatitude();
            lngADouble = location.getLongitude();

            latTextView.setText(String.format("%.7f", latADouble));
            lngTextView.setText(String.format("%.7f", lngADouble));
        }//onlocationchang

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void setUpLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
    }

    private void bindWidget() {

        latTextView = (TextView) findViewById(R.id.textView2);
        lngTextView = (TextView) findViewById(R.id.textView4);
        spinner = (Spinner) findViewById(R.id.spinner);
    }//Bindwidget

    public void clickStartSearchAct(View view) {
        Intent intent = new Intent(MapsActivity.this, MapsActivity.class);

        intent.putExtra("MyLat", latADouble);
        intent.putExtra("MyLng", lngADouble);


        startActivity(intent);
    } //clickStart

    public void onZoom(View view) {
        if (view.getId() == R.id.Bzoomin) {
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        }
        if (view.getId() == R.id.Bzoomout) {
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
        }
    }

    public void changeType(View view) {
        if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL) {
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        } else
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(13.716630, 100.487487),13));

        mMap.addMarker(new MarkerOptions().position(new LatLng(13.735798, 100.492341)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)).title("วัดบุปผาราม").snippet("พระอารามหลวง,13.735798, 100.492341"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(13.739948, 100.491204)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)).title("วัดกัลยานมิตร").snippet("พระอารามหลวง,13.739948, 100.491204"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(13.728924, 100.490469)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)).title("วัดหิรัญรูจีวรวิหาร").snippet("พระอารามหลวง,13.728924, 100.490469"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(13.719178, 100.485974)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).title("วัดใหญ่ศรีสุพรรณ").snippet("วัดราษฏร์,13.719178, 100.485974"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(13.732347, 100.489228)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).title("วัดบางไส้ไก่").snippet("วัดราษฏร์,13.732347, 100.489228"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(13.734278, 100.488092)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).title("วัดประดิษฐาราม").snippet("วัดราษฏร์,13.734278, 100.488092"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(13.725190, 100.486169)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)).title("วัดเวฬุราชิณ").snippet("พระอารามหลวง,13.725190, 100.486169"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(13.720930, 100.484056)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)).title("วัดโพธินิมิตสถิตมหาสีมาราม").snippet("พระอารามหลวง,13.720930, 100.484056"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(13.722727, 100.483277)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)).title("วัดอินทารามวรวิหาร").snippet("พระอารามหลวง,13.722727, 100.483277"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(13.737324, 100.495369)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)).title("วัดประยุรวงศาวาสวรวิหาร").snippet("พระอารามหลวง,13.737324, 100.495369"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(13.722384, 100.481108)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)).title("วัดจันทารามวรวิหาร").snippet("พระอารามหลวง,13.722384, 100.481108"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(13.721798, 100.479666)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)).title("วัดราชคฤห์วรวิหาร").snippet("พระอารามหลวง,13.721798, 100.479666"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(13.704214, 100.490526)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).title("วัดบางน้ำชน").snippet("วัดราษฏร์,13.704214, 100.490526"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(13.715359, 100.486108)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).title("วัดกระจับพินิจ").snippet("วัดราษฏรม์,13.715359, 100.486108"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(13.696599, 100.488186)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).title("วัดกลางดาวคนอง").snippet("วัดราษฏร์,13.696599, 100.488186"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(13.695921, 100.488491)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).title("วัดดาวคนอง").snippet("วัดราษฏร์,13.695921, 100.488491"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(13.699800, 100.488770)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).title("วัดบุคคโล").snippet("พระอารามหลวง,13.699800, 100.488770"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(13.708958, 100.491758)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).title("วัดราชวรินทร์").snippet("พระอารามหลวง,13.708958, 100.491758"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(13.706977, 100.488643)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).title("วัดสันติธรรมาราม").snippet("พระอารามหลวง,13.706977, 100.488643"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(13.707980, 100.482146)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).title("วัดสุทธาวาส").snippet("พระอารามหลวง,13.707980, 100.482146"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(13.718941, 100.479304)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).title("วัดกันตทาราราม").snippet("พระอารามหลวง,13.718941, 100.479304"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(13.711757, 100.475893)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).title("วัดบางสะแกใน").snippet("พระอารามหลวง,13.711757, 100.475893"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(13.717944, 100.474997)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).title("วัดบางสะแกนอก").snippet("พระอารามหลวง,13.717944, 100.474997"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(13.711961, 100.468449)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).title("วัดใหม่ยายนุ้ย").snippet("พระอารามหลวง,13.711961, 100.468449"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(13.719772, 100.470898)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).title("วัดวรามาตยภัณฑสาราราม").snippet("พระอารามหลวง,13.719772, 100.470898"));
    }

}


