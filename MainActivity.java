package com.example.angel.eltresenraya;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * Activity principal. Menú del juego
 * @Author ángel nácar
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//Pide permiso la primera vez que se instala para poder acceder a la memoria interna del teléfono
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

            }
        }

    }
//comprueba el resultado de la elección del usuario en la autorización de acceso a la memoria
    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {

                if ((grantResults.length > 0) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                return;
            }
        }
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.play:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Elija modo de juego")
                        .setCancelable(false)
                        .setPositiveButton("Player vs Player", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Intent intent = new Intent(MainActivity.this,Game.class);
                                intent.putExtra("MODO",2);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Player vs Machine", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Intent intent = new Intent(MainActivity.this,Game.class);
                                intent.putExtra("MODO",1);
                                startActivity(intent);
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                break;
            case R.id.points:
                Intent intent = new Intent(MainActivity.this,Puntuaciones.class);
                startActivity(intent);
                break;
            case R.id.help:
                Intent intent2 = new Intent(MainActivity.this,Ayuda.class);
                startActivity(intent2);
                break;

        }
    }
}
