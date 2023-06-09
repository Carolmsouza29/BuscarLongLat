package com.example.buscarlonglat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {

    /*Rec rec = new Rec();*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void buscarInformacoesGPS(View v) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)   != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACCESS_NETWORK_STATE}, 1);
            return;
        }

        LocationManager  mLocManager  = (LocationManager) getSystemService(MainActivity.this.LOCATION_SERVICE);
        LocationListener mLocListener = new MinhaLocalizacaoListener();

        mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 100, mLocListener);
        if (mLocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Calc c = new Calc();
            double velc = c.calcVelocidade(MinhaLocalizacaoListener.getLatitude(), MinhaLocalizacaoListener.getLongitude(), MinhaLocalizacaoListener.latitudeFinal, MinhaLocalizacaoListener.longitudeFinal);
            String texto = ("Latitude: " + MinhaLocalizacaoListener.getLatitude() + "\n" +
                    "Longitude: " + MinhaLocalizacaoListener.getLongitude() + "\n" + "Velocidade:" + MinhaLocalizacaoListener.getSpeed() + "\n" +  "Velocidade Média: "+ velc);
            ((MinhaLocalizacaoListener) mLocListener).addLatitude(MinhaLocalizacaoListener.getLatitude());
            ((MinhaLocalizacaoListener) mLocListener).addLatitude(MinhaLocalizacaoListener.getLongitude());
            Toast.makeText(MainActivity.this, texto, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "GPS DESABILITADO", Toast.LENGTH_LONG).show();
        }

        this.mostrarGoogleMaps(MinhaLocalizacaoListener.getLatitude(), MinhaLocalizacaoListener.getLongitude());

    }

    public void mostrarGoogleMaps(double latitude, double longitude) {
        WebView wv = findViewById(R.id.webv);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl("https://www.google.com/maps/search/?api=1&query=" + latitude + "," + longitude);

    }


}
