package com.example.angel.eltresenraya;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static android.app.PendingIntent.getActivity;

/**
 * Activity con los modos de juego
 */
public class Game extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView, imageView2;
    private ImageView[][] matriz;
    private Random aleatorio = new Random();
    private int posicion = 0;
    private MediaPlayer mediaPlayer;
    private Logica logica;
    private int modo = 0;
    private int turno = 0;
    private int resultado;
    private TextView mTvTimer;
    private Chronometer mChrono;
    private Context context;
    private Thread mThreadChrono;
    private long time;
    private int toca = 0;
    private boolean juegoParado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_vsmachine);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent = getIntent();
        Bundle trae = intent.getExtras();

        modo = (int) trae.get("MODO"); //modo de juego elegido por el usuario
        context = this;
        mTvTimer = findViewById(R.id.reloj);
        matriz = new ImageView[3][3];
        imageView = findViewById(R.id.dos);
        imageView.setTag(R.drawable.cuadrado);
        logica = new Logica(); //instacia la clase Logica que controla quién gana al juego
        imageView2 = findViewById(R.id.imageButton);
        mChrono = new Chronometer(this, 0);
        mThreadChrono = new Thread(mChrono);
        mThreadChrono.start();
        mChrono.start(); //arranca el cronómetro

        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        play_mp(); //inicia el hilo de la música

        for (int i = 0; i < 3; i++) { //Este for inicializa la matriz con un valor por defecto
            for (int j = 0; j < 3; j++) {

                matriz[i][j] = imageView;
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
        mChrono.stop();
        mThreadChrono.interrupt();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mediaPlayer.start();
        mThreadChrono.start();
        mChrono.start();

    }

    @Override
    public void onClick(View v) {
        if (!juegoParado) {
            switch (modo) {
                case 1:
                    imageView = findViewById(v.getId());
                    toca = 0;

                    if (logica.GanaX(matriz)) {
                        resultado = 1;
                        Mensaje("GANA JUDADOR 1 !!!");

                    } else if (logica.GanaO(matriz)) {
                        resultado = 2;
                        Mensaje("GANA MÁQUINA !!!");


                    } else if (logica.Empate(matriz)) {
                        resultado = 0;
                        Mensaje("EMPATE !!!");


                    } else {

                        switch (v.getId()) {
                            case R.id.unoUno:
                                if (matriz[0][0].getTag().equals(R.drawable.cuadrado)) {
                                    imageView.setImageResource(R.drawable.equis);
                                    imageView.setTag(R.drawable.equis);
                                    matriz[0][0] = imageView;

                                } else {
                                    toca = 1;
                                }
                                break;
                            case R.id.unoDos:
                                if (matriz[0][1].getTag().equals(R.drawable.cuadrado)) {
                                    imageView.setImageResource(R.drawable.equis);
                                    imageView.setTag(R.drawable.equis);
                                    matriz[0][1] = imageView;
                                } else {
                                    toca = 1;
                                }
                                break;
                            case R.id.unoTres:
                                if (matriz[0][2].getTag().equals(R.drawable.cuadrado)) {
                                    imageView.setImageResource(R.drawable.equis);
                                    imageView.setTag(R.drawable.equis);
                                    matriz[0][2] = imageView;
                                } else {
                                    toca = 1;
                                }
                                break;
                            case R.id.dosUno:
                                if (matriz[1][0].getTag().equals(R.drawable.cuadrado)) {
                                    imageView.setImageResource(R.drawable.equis);
                                    imageView.setTag(R.drawable.equis);
                                    matriz[1][0] = imageView;
                                } else {
                                    toca = 1;
                                }
                                break;
                            case R.id.dosDos:
                                if (matriz[1][1].getTag().equals(R.drawable.cuadrado)) {
                                    imageView.setImageResource(R.drawable.equis);
                                    imageView.setTag(R.drawable.equis);
                                    matriz[1][1] = imageView;
                                } else {
                                    toca = 1;
                                }
                                break;
                            case R.id.dosTres:
                                if (matriz[1][2].getTag().equals(R.drawable.cuadrado)) {
                                    imageView.setImageResource(R.drawable.equis);
                                    imageView.setTag(R.drawable.equis);
                                    matriz[1][2] = imageView;
                                } else {
                                    toca = 1;
                                }
                                break;
                            case R.id.tresUno:
                                if (matriz[2][0].getTag().equals(R.drawable.cuadrado)) {
                                    imageView.setImageResource(R.drawable.equis);
                                    imageView.setTag(R.drawable.equis);
                                    matriz[2][0] = imageView;
                                } else {
                                    toca = 1;
                                }
                                break;
                            case R.id.tresDos:
                                if (matriz[2][1].getTag().equals(R.drawable.cuadrado)) {
                                    imageView.setImageResource(R.drawable.equis);
                                    imageView.setTag(R.drawable.equis);
                                    matriz[2][1] = imageView;
                                } else {
                                    toca = 1;
                                }
                                break;
                            case R.id.tresTres:
                                if (matriz[2][2].getTag().equals(R.drawable.cuadrado)) {
                                    imageView.setImageResource(R.drawable.equis);
                                    imageView.setTag(R.drawable.equis);
                                    matriz[2][2] = imageView;
                                } else {
                                    toca = 1;
                                }
                                break;

                        }

                        if (logica.GanaX(matriz)) {
                            resultado = 1;
                            Mensaje("GANA JUGADOR !!!");

                        } else if (logica.GanaO(matriz)) {
                            resultado = 2;
                            Mensaje("GANA MÁQUINA !!!");


                        } else if (logica.Empate(matriz)) {
                            resultado = 0;
                            Mensaje("EMPATE !!!");


                        } else if (toca != 1) {

                            while (!Maquina()) {
                            }

                        }
                        if (logica.GanaX(matriz)) {
                            resultado = 1;
                            Mensaje("GANA JUGADOR !!!");

                        } else if (logica.GanaO(matriz)) {
                            resultado = 2;
                            Mensaje("GANA MÁQUINA !!!");


                        } else if (logica.Empate(matriz)) {
                            resultado = 0;
                            Mensaje("EMPATE !!!");


                        }

                    }
                    break;
                case 2:
                    imageView = findViewById(v.getId());
                    if (turno % 2 == 0) {
                        if (logica.GanaX(matriz)) {
                            resultado = 1;
                            Mensaje("GANA JUGADOR 1!!!");

                        } else if (logica.GanaO(matriz)) {
                            resultado = 2;
                            Mensaje("GANA JUGADOR 2!!!");


                        } else if (logica.Empate(matriz)) {
                            resultado = 0;
                            Mensaje("EMPATE !!!!");


                        } else {

                            switch (v.getId()) {
                                case R.id.unoUno:
                                    if (matriz[0][0].getTag().equals(R.drawable.cuadrado)) {
                                        imageView.setImageResource(R.drawable.equis);
                                        imageView.setTag(R.drawable.equis);
                                        matriz[0][0] = imageView;

                                    }
                                    break;
                                case R.id.unoDos:
                                    if (matriz[0][1].getTag().equals(R.drawable.cuadrado)) {
                                        imageView.setImageResource(R.drawable.equis);
                                        imageView.setTag(R.drawable.equis);
                                        matriz[0][1] = imageView;
                                    }
                                    break;
                                case R.id.unoTres:
                                    if (matriz[0][2].getTag().equals(R.drawable.cuadrado)) {
                                        imageView.setImageResource(R.drawable.equis);
                                        imageView.setTag(R.drawable.equis);
                                        matriz[0][2] = imageView;
                                    }
                                    break;
                                case R.id.dosUno:
                                    if (matriz[1][0].getTag().equals(R.drawable.cuadrado)) {
                                        imageView.setImageResource(R.drawable.equis);
                                        imageView.setTag(R.drawable.equis);
                                        matriz[1][0] = imageView;
                                    }
                                    break;
                                case R.id.dosDos:
                                    if (matriz[1][1].getTag().equals(R.drawable.cuadrado)) {
                                        imageView.setImageResource(R.drawable.equis);
                                        imageView.setTag(R.drawable.equis);
                                        matriz[1][1] = imageView;
                                    }
                                    break;
                                case R.id.dosTres:
                                    if (matriz[1][2].getTag().equals(R.drawable.cuadrado)) {
                                        imageView.setImageResource(R.drawable.equis);
                                        imageView.setTag(R.drawable.equis);
                                        matriz[1][2] = imageView;
                                    }
                                    break;
                                case R.id.tresUno:
                                    if (matriz[2][0].getTag().equals(R.drawable.cuadrado)) {
                                        imageView.setImageResource(R.drawable.equis);
                                        imageView.setTag(R.drawable.equis);
                                        matriz[2][0] = imageView;
                                    }
                                    break;
                                case R.id.tresDos:
                                    if (matriz[2][1].getTag().equals(R.drawable.cuadrado)) {
                                        imageView.setImageResource(R.drawable.equis);
                                        imageView.setTag(R.drawable.equis);
                                        matriz[2][1] = imageView;
                                    }
                                    break;
                                case R.id.tresTres:
                                    if (matriz[2][2].getTag().equals(R.drawable.cuadrado)) {
                                        imageView.setImageResource(R.drawable.equis);
                                        imageView.setTag(R.drawable.equis);
                                        matriz[2][2] = imageView;
                                    }
                                    break;

                            }

                            if (logica.GanaX(matriz)) {
                                resultado = 1;
                                Mensaje("GANA JUGADOR 1!!!");

                            } else if (logica.GanaO(matriz)) {
                                resultado = 2;
                                Mensaje("GANA JUGADOR 2!!!");


                            } else if (logica.Empate(matriz)) {
                                resultado = 0;
                                Mensaje("EMPATE !!!!");


                            }
                            turno++;
                        }

                    } else {
                        switch (v.getId()) {
                            case R.id.unoUno:
                                if (matriz[0][0].getTag().equals(R.drawable.cuadrado)) {
                                    imageView.setImageResource(R.drawable.circulo);
                                    imageView.setTag(R.drawable.circulo);
                                    matriz[0][0] = imageView;

                                }
                                break;
                            case R.id.unoDos:
                                if (matriz[0][1].getTag().equals(R.drawable.cuadrado)) {
                                    imageView.setImageResource(R.drawable.circulo);
                                    imageView.setTag(R.drawable.circulo);
                                    matriz[0][1] = imageView;
                                }
                                break;
                            case R.id.unoTres:
                                if (matriz[0][2].getTag().equals(R.drawable.cuadrado)) {
                                    imageView.setImageResource(R.drawable.circulo);
                                    imageView.setTag(R.drawable.circulo);
                                    matriz[0][2] = imageView;
                                }
                                break;
                            case R.id.dosUno:
                                if (matriz[1][0].getTag().equals(R.drawable.cuadrado)) {
                                    imageView.setImageResource(R.drawable.circulo);
                                    imageView.setTag(R.drawable.circulo);
                                    matriz[1][0] = imageView;
                                }
                                break;
                            case R.id.dosDos:
                                if (matriz[1][1].getTag().equals(R.drawable.cuadrado)) {
                                    imageView.setImageResource(R.drawable.circulo);
                                    imageView.setTag(R.drawable.circulo);
                                    matriz[1][1] = imageView;
                                }
                                break;
                            case R.id.dosTres:
                                if (matriz[1][2].getTag().equals(R.drawable.cuadrado)) {
                                    imageView.setImageResource(R.drawable.circulo);
                                    imageView.setTag(R.drawable.circulo);
                                    matriz[1][2] = imageView;
                                }
                                break;
                            case R.id.tresUno:
                                if (matriz[2][0].getTag().equals(R.drawable.cuadrado)) {
                                    imageView.setImageResource(R.drawable.circulo);
                                    imageView.setTag(R.drawable.circulo);
                                    matriz[2][0] = imageView;
                                }
                                break;
                            case R.id.tresDos:
                                if (matriz[2][1].getTag().equals(R.drawable.cuadrado)) {
                                    imageView.setImageResource(R.drawable.circulo);
                                    imageView.setTag(R.drawable.circulo);
                                    matriz[2][1] = imageView;
                                }
                                break;
                            case R.id.tresTres:
                                if (matriz[2][2].getTag().equals(R.drawable.cuadrado)) {
                                    imageView.setImageResource(R.drawable.circulo);
                                    imageView.setTag(R.drawable.circulo);
                                    matriz[2][2] = imageView;
                                }
                                break;

                        }

                        if (logica.GanaX(matriz)) {
                            resultado = 1;
                            Mensaje("GANA JUGADOR 1!!!!");

                        } else if (logica.GanaO(matriz)) {
                            resultado = 2;
                            Mensaje("GANA JUGADOR 2!!!!");


                        } else if (logica.Empate(matriz)) {
                            resultado = 0;
                            Mensaje("EMPATE !!!!");


                        }
                        turno++;
                    }

                    break;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //hago un case por si en un futuro agrego mas opciones
                finish();
                return true;
            case R.id.play_musica:
                mediaPlayer.start();
                return true;
            case R.id.stop_musica:
                mediaPlayer.pause();
                return true;
            case R.id.stop_juego:
                finish();
                return true;
            case R.id.pause_game:
                time = mChrono.getTiempo();
                mChrono.stop();
                mThreadChrono.interrupt();
                juegoParado = true;
                return true;
            case R.id.play_juego:
                mThreadChrono.start();
                mChrono.start();
                juegoParado = false;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
//logica del juego de la máquina
    public boolean Maquina() {
        posicion = aleatorio.nextInt(9);
        switch (posicion) {
            case 0:
                imageView = findViewById(R.id.unoUno);
                if (matriz[0][0].getTag().equals(R.drawable.cuadrado)) {
                    imageView.setImageResource(R.drawable.circulo);
                    imageView.setTag(R.drawable.circulo);
                    matriz[0][0] = imageView;

                    return true;
                } else {
                    return false;
                }
            case 1:
                imageView = findViewById(R.id.unoDos);
                if (matriz[0][1].getTag().equals(R.drawable.cuadrado)) {
                    imageView.setImageResource(R.drawable.circulo);
                    imageView.setTag(R.drawable.circulo);
                    matriz[0][1] = imageView;

                    return true;
                } else {
                    return false;
                }
            case 2:
                imageView = findViewById(R.id.unoTres);
                if (matriz[0][2].getTag().equals(R.drawable.cuadrado)) {
                    imageView.setImageResource(R.drawable.circulo);
                    imageView.setTag(R.drawable.circulo);
                    matriz[0][2] = imageView;

                    return true;
                } else {
                    return false;
                }
            case 3:
                imageView = findViewById(R.id.dosUno);
                if (matriz[1][0].getTag().equals(R.drawable.cuadrado)) {
                    imageView.setImageResource(R.drawable.circulo);
                    imageView.setTag(R.drawable.circulo);
                    matriz[1][0] = imageView;

                    return true;
                } else {
                    return false;
                }
            case 4:
                imageView = findViewById(R.id.dosDos);
                if (matriz[1][1].getTag().equals(R.drawable.cuadrado)) {
                    imageView.setImageResource(R.drawable.circulo);
                    imageView.setTag(R.drawable.circulo);
                    matriz[1][1] = imageView;

                    return true;
                } else {
                    return false;
                }
            case 5:
                imageView = findViewById(R.id.dosTres);
                if (matriz[1][2].getTag().equals(R.drawable.cuadrado)) {
                    imageView.setImageResource(R.drawable.circulo);
                    imageView.setTag(R.drawable.circulo);
                    matriz[1][2] = imageView;

                    return true;
                } else {
                    return false;
                }
            case 6:
                imageView = findViewById(R.id.tresUno);
                if (matriz[2][0].getTag().equals(R.drawable.cuadrado)) {
                    imageView.setImageResource(R.drawable.circulo);
                    imageView.setTag(R.drawable.circulo);
                    matriz[2][0] = imageView;

                    return true;
                } else {
                    return false;
                }
            case 7:
                imageView = findViewById(R.id.tresDos);
                if (matriz[2][1].getTag().equals(R.drawable.cuadrado)) {
                    imageView.setImageResource(R.drawable.circulo);
                    imageView.setTag(R.drawable.circulo);
                    matriz[2][1] = imageView;

                    return true;
                } else {
                    return false;
                }
            case 8:
                imageView = findViewById(R.id.tresTres);
                if (matriz[2][2].getTag().equals(R.drawable.cuadrado)) {
                    imageView.setImageResource(R.drawable.circulo);
                    imageView.setTag(R.drawable.circulo);
                    matriz[2][2] = imageView;


                    return true;
                } else {
                    return false;
                }
        }

        return false;

    }
//hilo de la música
    private void play_mp() {
        Thread hilo = new Thread() {
            @Override
            public void run() {
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.mario);
                mediaPlayer.start();
            }
        };
        hilo.start();
    }
//metodo para mostrar mensaje de resultado de juego a traves de un alert dialog
    public void Mensaje(String mensaje) {
        final CharSequence[] items2 = {"OK"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(mensaje);
        builder.setItems(items2, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                switch (items2[item].toString()) {
                    case "OK":
                        mChrono.stop();//para cronometro
                        mThreadChrono.interrupt();
                        imageView2.setOnTouchListener(Mover); //activa onTouch
                        break;
                }
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }
//actualiza el crono visualmente
    public void actualizarTiempo(final String timeAsText) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTvTimer.setText(timeAsText);
            }
        });
    }
//onTouch
    View.OnTouchListener Mover = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Point Down = new Point();
            Point Start;
            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    Intent intent = new Intent(Game.this, Datos.class);
                    intent.putExtra("resultado", resultado); //pasa quién ha ganado
                    intent.putExtra("tiempo", mChrono.getTiempo()); //el tiempo
                    startActivity(intent);
                    break;
                default:
                    break;

            }
            return true;
        }
    };
}
