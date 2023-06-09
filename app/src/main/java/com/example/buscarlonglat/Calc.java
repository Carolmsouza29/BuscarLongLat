package com.example.buscarlonglat;


import android.util.Log;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


public class Calc {
    private double velocMedia;

    private int velocRec;


    MinhaLocalizacaoListener m = new MinhaLocalizacaoListener();



    public int getVelocRec() {
        return velocRec;
    }



    public double calcVelocidade(double latI, double longI, double latF, double longF){
        LocalTime tI = LocalTime.now();
        LocalTime tF = LocalTime.of(17, 55);
        long d = m.calcTempo(tI,tF);
        double a = (m.calculaDistancia(latI,longI,latF,longF));
        Log.d("teste01", "dis: " + a + "Tempo " + d);
        return velocMedia = a/d;    /*km/minuto*/
    }

}
