package com.example.camdia;


import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int LOCATION_REQUEST = 500;
    private LatLng ltlatual;
    public List<LatLng> listPoints;
    private LocationManager locationManager;
    private LocationListener locationListener;
    EditText oritx;
    EditText destx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        listPoints = new ArrayList<LatLng>();
        locationManager = (LocationManager) this.getSystemService(MapsActivity.this.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {

                Double latitude = location.getLatitude();
                Double longitude = location.getLongitude();
                ltlatual = new LatLng(latitude,longitude);

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude), 15));
                Log.d("testing", "onLocationChanged: nova lat long e´=== " + latitude.toString() + longitude.toString());
            }
        };
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        oritx= findViewById(R.id.txtmpO);
        destx = findViewById(R.id.txtmpD);
        findViewById(R.id.btnRota).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String or = oritx.getText().toString();
                String de = destx.getText().toString();
                or.replace(" ", "+").trim();
                de.replace(" ", "+").trim();
                String url = getRequestUrlTx(or, de);
                TaskRquestDirections taskRquestDirections = new TaskRquestDirections();
                taskRquestDirections.execute(url);

            }
        });findViewById(R.id.btnmpVoltar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ViewPrincipal.class));
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        mMap.getUiSettings().setZoomControlsEnabled(true);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
            Log.d("testing", "onMapReady:  mapa pronto click off");
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                atualizaPosição(10000);
                if (listPoints.size() == 2) {
                    listPoints.clear();
                    mMap.clear();
                }

                listPoints.add(latLng);



                if (listPoints.size() == 1) {
                    mMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Inicio")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.markflag))
                    );
                } else {
                    mMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Chegada")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.markflag))
                    );
                }


                if (listPoints.size() == 2) {
                    String url = getRequestUrl(listPoints.get(0), listPoints.get(1));

                    TaskRquestDirections taskRquestDirections = new TaskRquestDirections();
                    taskRquestDirections.execute(url);

                }

            }
        });



    }

    private String getRequestUrl(LatLng or, LatLng des) {
        String str_org = "origin=" + or.latitude + "," + or.longitude;

        String str_des = "destination=" + des.latitude + "," + des.longitude;

        String sensor = "sensor = false";

        String mode = "mode=driving";

        String key = "key=AIzaSyCRIJYOU9BIiB-wcM229kGNNBbEBbZvgas";

        String param = str_org + "&" + str_des + "&" + sensor + "&" + mode + "&" + key;

        String out = "json";

        String url = "https://maps.googleapis.com/maps/api/directions/" + out + "?" + param;

        return url;
    }
    private String getRequestUrlTx(String or, String des) {
        String str_org = "origin="+or;

        String str_des = "destination=" +des;

        String sensor = "sensor = false";

        String mode = "mode=driving";

        String key = "key=AIzaSyCRIJYOU9BIiB-wcM229kGNNBbEBbZvgas";

        String param = str_org + "&" + str_des + "&" + sensor + "&" + mode + "&" + key;

        String out = "json";

        String url = "https://maps.googleapis.com/maps/api/directions/" + out + "?" + param;

        return url;
    }

    private String requestDir(String requUrl) throws IOException {

        String responseString = "";
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;

        try {
            URL url = new URL(requUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuffer stringBuffer = new StringBuffer();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {

                stringBuffer.append(line);
            }

            responseString = stringBuffer.toString();
            bufferedReader.close();
            inputStreamReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            httpURLConnection.disconnect();

        }

        return responseString;


    }


    //permissão


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int permissaoResultado : grantResults) {
            if (permissaoResultado == PackageManager.PERMISSION_DENIED) {
                validaNegado();
            } else if (permissaoResultado == PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    finish();
                    startActivity(new Intent(getApplicationContext(), MapsActivity.class));

                    return;
                }


            }
        }
    }


    private void atualizaPosição(int taxaAtualiza){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    taxaAtualiza, 0,
                    locationListener
            );
        }
    }



    private void validaNegado() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissão negada");
        builder.setMessage("Para utilizar o App é necessário aceitar as permissões");
        builder.setCancelable(false);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public class TaskRquestDirections extends AsyncTask<String,Void,String>{


        @Override
        protected String doInBackground(String... strings) {
            String responseStr = "";
            try {
                responseStr = requestDir(strings[0]);
            }catch (IOException e){
                e.printStackTrace();
            }
            return responseStr;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            TaskParser taskParser = new TaskParser();
            taskParser.execute(s);

        }
    }

    public class TaskParser extends AsyncTask<String, Void, List<List<HashMap<String, String>>> >{

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... strings) {

            JSONObject jsonObject = null;
            List<List<HashMap<String, String>>> routes = null;
            try {
                jsonObject = new JSONObject(strings[0]);
                ADPDirectionsParser directionsParser = new ADPDirectionsParser();
                routes = directionsParser.parse(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> lists) {
            ArrayList points = null;
            PolylineOptions polylineOptions = null;
            for(List<HashMap<String, String>> path : lists){
                points = new ArrayList();
                polylineOptions = new PolylineOptions();

                for (HashMap<String, String> point : path){
                    double lat = Double.parseDouble(point.get("lat"));
                    double lon = Double.parseDouble(point.get("lng"));

                    points.add(new LatLng(lat,lon));

                }

                polylineOptions.addAll(points);
                polylineOptions.width(15);
                polylineOptions.color(Color.BLACK);
                polylineOptions.geodesic(true);

            }
            if(polylineOptions!=null){
                mMap.addPolyline(polylineOptions);

            }else {
                Toast.makeText(getApplicationContext(),"Direction não encontrado",Toast.LENGTH_SHORT).show();
                Log.d("testing", "onPostExecute: polylines ====" + polylineOptions);

            }


        }
    }

}

