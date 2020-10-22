package com.example.camdia;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private int taxaAtualiza;
    private GoogleMap mMap;
    private String[] permissoes = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Permissoes.validarPermissoes(permissoes, this, 1);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                Log.d("Localização", "onLocationChanged: " + location.toString());


                Double latitude = location.getLatitude();
                Double longitude = location.getLongitude();

                Double varEx =-23.571749 ;
                Double varEx2 =-46.4843544 ;

                mMap.clear();
                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                try {
                    List<Address> listaEndereco = geocoder.getFromLocation(latitude, longitude, 1);

                    if (listaEndereco != null && listaEndereco.size() > 0) {
                        Address endereco = listaEndereco.get(0);
                        String ruaBaNu = endereco.getAddressLine(0);

                        Double lat = endereco.getLatitude();
                        Double lon = endereco.getLongitude();
                        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                        LatLng localUsuario = new LatLng(varEx, varEx2);

                        mMap.addMarker(new MarkerOptions()
                                .position(localUsuario)
                                .title(ruaBaNu)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.markflag))
                        );
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localUsuario, 15));

                        /*
                         * D/local: onLocationChanged:
                         * Address[addressLines=[0:"R. Bonsucesso, 60 - Jardim Noronha, São Paulo - SP, 04853-192, Brazil"],
                         * feature=60,
                         * admin=São Paulo,
                         * sub-admin=São Paulo,
                         * locality=null,
                         * thoroughfare=Rua Bonsucesso,
                         * postalCode=04853-192,
                         * countryCode=BR,
                         * countryName=Brazil,
                         * hasLatitude=true,
                         * latitude=-23.7716203,
                         * hasLongitude=true,
                         * longitude=-46.6768499,
                         * phone=null,
                         * url=null,
                         * extras=null]
                         * */

                        Log.d("local", "onLocationChanged: " + endereco.toString());

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                taxaAtualiza = 5000;
                atualizaPosição(taxaAtualiza);

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));


            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


          for (int permissaoResultado : grantResults) {
              if (permissaoResultado == PackageManager.PERMISSION_DENIED) {
                  validaNegado();
            }
            else if (permissaoResultado == PackageManager.PERMISSION_GRANTED) {
                taxaAtualiza = 10000;
                atualizaPosição(taxaAtualiza);
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


}

