package com.example.angel.eltresenraya;


import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Clase para mostrar en lista los datos de los jugadores y partidas
 */
public class Puntuaciones extends AppCompatActivity implements View.OnClickListener {

    ListView listado;
    ArrayList<Jugadores> listaJugadores;
    DataSource dataSource;
    Context context = this;
    AdaptadorPersonas adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuaciones);
        listado = findViewById(R.id.lista);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dataSource = new DataSource(this);
        dataSource.Open();
        listaJugadores = (ArrayList<Jugadores>) dataSource.muestraTodo();
        adaptador = new AdaptadorPersonas(this);


        dataSource.Close();
        listado.setAdapter(adaptador);
    }

    @Override
    public void onClick(View v) {


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //hago un case por si en un futuro agrego mas opciones
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    class AdaptadorPersonas extends ArrayAdapter<Jugadores> {


        AppCompatActivity appCompatActivity;

        AdaptadorPersonas(Puntuaciones context) {
            super(context, R.layout.jugadores, listaJugadores);
            appCompatActivity = context;
        }


        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.jugadores, null);

            ImageView imageView = (ImageView) item.findViewById(R.id.foto);
            TextView textView1 = (TextView) item.findViewById(R.id.nombre);
            TextView textView2 = (TextView) item.findViewById(R.id.victorias);
            TextView textView3 = (TextView) item.findViewById(R.id.derrotas);
            TextView textView4 = (TextView) item.findViewById(R.id.empates);
            TextView textView5 = (TextView) item.findViewById(R.id.tiempo);

            imageView.setImageURI(Uri.parse("content://media" + listaJugadores.get(position).getRuta()));
            textView1.setText(listaJugadores.get(position).getNombre());
            textView2.setText(String.valueOf(listaJugadores.get(position).getPartidasGanadas()));
            textView3.setText(String.valueOf(listaJugadores.get(position).getPartidasPerdidas()));
            textView4.setText(String.valueOf(listaJugadores.get(position).getEmpates()));
            textView5.setText(String.valueOf(listaJugadores.get(position).getTiempo()));

            return (item);


        }
    }

}
