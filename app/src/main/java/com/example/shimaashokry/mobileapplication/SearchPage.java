package com.example.shimaashokry.mobileapplication;

import android.*;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ScrollingTabContainerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.MapsInitializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchPage extends AppCompatActivity {
    Spinner spinnerCity;
    ArrayAdapter<CharSequence> adapter;
    static final long meter = 1;
    static final long mill = 1000;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    protected String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION};
    private static final int REQUEST_CODE_PERMISSION = 4;

    //using Geocoder class, to use the function get place name, and give we adress
    // use this for simple function
    Geocoder gcObject = new Geocoder(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        // search by city
        spinnerCity = (Spinner) findViewById(R.id.searchByCity);
        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter = ArrayAdapter.createFromResource(this, R.array.city_Names, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Specify the layout to use when the list of choices appears
        spinnerCity.setAdapter(adapter);
        // create listener for selected item
        // create new array have all the name of city in spinner;
        final  String [] cityCoordinates= new String[5];
        cityCoordinates[1]="geo: 29.848505, 31.336853?q=hospitals";
        cityCoordinates[2]="geo: 30.057264, 31.325234?q=hospitals";
        cityCoordinates[3]="geo: 30.170547, 31.411875?q=hospitals";
        cityCoordinates[4]="geo: 30.099977, 31.343114?q=hospitals";

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0) {
                    String cityCoordinate=cityCoordinates[position];
                    //show the hospital in map
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(cityCoordinate));
                    intent.setPackage("com.google.android.apps.maps");
                    startActivity(intent);
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // to follow the mobile all the time
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        // 1 meter to execute the request and 1000 mills for update
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, meter, mill, new locationlistener(this));
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
     //   client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


  /*  public void getYourLocation(View view) {


    } */


    public void searchByName(View view) throws IOException {
        EditText txt1=null;
        //get text from edit text
        txt1= (EditText)findViewById(R.id.HName);
        String hospitalName=txt1.getText().toString();

        //use the adress class as data type, make list of adress
        List<Address> list= gcObject.getFromLocationName(hospitalName, 5, 31.6,37, 22, 26);
        //gcObject.getFromLocationName(hospitalName,5);
        // get the first item of list
        Address hospitalAddress = list.get(0);
        //  get the long and lat from the adress
        double lant=hospitalAddress.getLatitude();
        double longt=hospitalAddress.getLongitude();

        //show the hospital in map
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:lant,longt?q=("+hospitalName+")&z=15"));
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }
    public void searchByLoc(View view)

    {
        // to show nearby hospitals from the user ,put geo:0,0
        Uri gmmIntentUri= Uri.parse("geo:0,0?q=hospitals");
        // we want to use another activity so we use intent
        Intent mapIntent= new Intent(Intent.ACTION_VIEW,gmmIntentUri);
        // we know the name of package we want to use it so we set this package
        mapIntent.setPackage("com.google.android.apps.maps");
        // to start the other activity to do
        startActivity(mapIntent);

    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("SearchPage Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
     //   client.connect();
    //    AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
      //  AppIndex.AppIndexApi.end(client, getIndexApiAction());
       // client.disconnect();
    }

    }

