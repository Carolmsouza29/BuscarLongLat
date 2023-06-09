package com.example.buscarlonglat;


import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;


import androidx.annotation.NonNull;


import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import java.text.SimpleDateFormat;

public class MinhaLocalizacaoListener implements LocationListener{
    private static double latitude;
    private static double longitude;
    protected static final double latitudeFinal = -21.3170788;
    protected static final double longitudeFinal = -44.8726802;

    private static int speed;
    private ArrayList<Double> arrayLat;
    private ArrayList<Double> arrayLong;

    public MinhaLocalizacaoListener(){
        arrayLat = new ArrayList<>();
        arrayLong = new ArrayList<>();
    }
    @Override
    public void onLocationChanged(Location location) {
        this.latitude  = location.getLatitude();
        this.longitude = location.getLongitude();
        this.speed = (int)(location.getSpeed()*3600/1000);
    }

    public static double getLatitude(){
        return latitude;
    }

    public static double getLongitude(){
        return longitude;
    }
    public static int getSpeed(){
        return speed;
    }

    public void addLatitude(Double latitude){
        arrayLat.add(latitude);
    }

    public void addLongitude(Double longitude){
        arrayLong.add(longitude);
    }


    @Override
    public void onProviderDisabled(@NonNull String provider) {
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    public double calculaDistancia(double lat1, double lng1, double lat2, double lng2) {
        //double earthRadius = 3958.75;//miles
        double earthRadius = 6371;//kilometers
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadius * c;

        return dist; //em metros
    }


    public long calcTempo(LocalTime tI, LocalTime tF){
        long horas = ChronoUnit.HOURS.between(tI, tF);
        long minutos = ChronoUnit.MINUTES.between(tI, tF) % 60;
        long segundos = ChronoUnit.SECONDS.between(tI, tF) % 60;

        return minutos;
    }

}