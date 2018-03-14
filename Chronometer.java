package com.example.angel.eltresenraya;



import android.content.Context;


public class Chronometer implements Runnable {

    public static final long MILLIS_TO_MINUTES = 60000;
    int seconds;
    int minutes;
    int paso=1;
    Context mContext;
    long mStartTime;
    boolean IsRunning;
    long since = 0;


    public Chronometer(Context context) {
        mContext = context;
    }

    public Chronometer(Context context, long startTime) {
        this(context);
        mStartTime = startTime;
    }

    public void start() {
        IsRunning = true;
    }

    public void stop() {
        IsRunning = false;
        //since = 0;


    }
    public long getTiempo(){
        return since;
    }


    @Override
    public void run() {
        while(IsRunning) {

            //Calculamos la diferencia de tiempo desde que empezamos a contar con la actual
            since = (long) (since + 100);

            //Convierte el tiempo a segundos y minutos
            seconds = (int) (since / 1000) % 60;
            minutes = (int) ((since / (MILLIS_TO_MINUTES)) % 60);

            ((Game) mContext).actualizarTiempo(String.format("%02d:%02d", minutes, seconds));

            //Duerme el hilo durante un periodo muy corto, para prevenir la sobrecarga de la CPU
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
