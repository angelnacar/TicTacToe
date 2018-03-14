package com.example.angel.eltresenraya;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class Datos extends AppCompatActivity implements View.OnClickListener {
    private static final int PICK_IMAGE = 100;
    private Uri imageUri;
    private ImageView foto_gallery;
    private DataSource dataSource;
    private Button button;
    private EditText editText;
    private int resultado;
    private long time;
    private final long MILLIS_TO_MINUTES = 60000;
    private int seconds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        dataSource = new DataSource(this);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.nombre);
        Intent intent = getIntent();
        Bundle trae = intent.getExtras();
        dataSource.Open();
        resultado = trae.getInt("resultado");
        time = trae.getLong("tiempo");
        seconds = (int) (time / 1000) % 60;
        button.setOnClickListener(this);
        foto_gallery = (ImageView) findViewById(R.id.foto);
        foto_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }
//metodo para abrir galeria de fotos
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    public boolean  onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //hago un case por si en un futuro agrego mas opciones
                Intent intent = new Intent(Datos.this,MainActivity.class);
                startActivity(intent);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            foto_gallery.setImageURI(imageUri);

        }
    }
//metodo para el registro de jugadores o actualización de datos
    private void createData() {
        String nombre = editText.getText().toString();
        List<Jugadores> jugadores = dataSource.muestraCondicion(nombre); //hace consulta a la bbdd con el nombre del jugador

        if (resultado == 1) {
            String modo = "pGanadas";
            if (jugadores.size() > 0) { //si ya existe actualiza sus datos, sino crea uno nuevo
                dataSource.Actualizar(nombre, modo, seconds);
                Toast.makeText(this, "ACTUALIZADO", Toast.LENGTH_SHORT).show();
            } else {
                dataSource.Create(new Jugadores(imageUri.getPath(), nombre, 1, 0, 0,seconds));
                dataSource.Close();
                Toast.makeText(this, "CREADO", Toast.LENGTH_SHORT).show();
            }

        } else if (resultado == 2) {
            String modo = "pPerdidas";
            if (jugadores.size() > 0) {
                dataSource.Actualizar(nombre, modo, seconds);
                Toast.makeText(this, "ACTUALIZADO", Toast.LENGTH_SHORT).show();
            } else {
                dataSource.Create(new Jugadores(imageUri.getPath(), nombre, 0, 1, 0,seconds));
                dataSource.Close();

                Toast.makeText(this, "CREADO", Toast.LENGTH_SHORT).show();
            }
        } else if(resultado == 0){
            String modo = "empates";
            if (jugadores.size() > 0) {
                dataSource.Actualizar(nombre, modo,seconds);
                Toast.makeText(this, "ACTUALIZADO", Toast.LENGTH_SHORT).show();
            } else {
                dataSource.Create(new Jugadores(imageUri.getPath(), nombre, 0, 0, 1,seconds));
                dataSource.Close();
                Toast.makeText(this, "CREADO", Toast.LENGTH_SHORT).show();
            }
        }
        Intent intent = new Intent(Datos.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        createData();
    }

}


