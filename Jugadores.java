package com.example.angel.eltresenraya;

import java.io.Serializable;

/**
 *
 * Created by Angel on 01/03/2018.
 */

public class Jugadores implements Serializable{

    String ruta;
    String nombre;
    int partidasGanadas;
    int partidasPerdidas;
    int empate;
    int tiempo;

    public Jugadores(){}

    public Jugadores( String ruta, String nombre, int partidasGanadas, int partidasPerdidas, int empate, int tiempo) {

        this.ruta = ruta;
        this.nombre = nombre;
        this.partidasGanadas = partidasGanadas;
        this.partidasPerdidas = partidasPerdidas;
        this.empate = empate;
        this.tiempo = tiempo;
    }

    public String getRuta() {
        return ruta;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public int getPartidasPerdidas() {
        return partidasPerdidas;
    }

    public int getEmpates() {
        return empate;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    public void setPartidasPerdidas(int partidasPerdidas) {
        this.partidasPerdidas = partidasPerdidas;
    }

    public void setEmpates(int empate) {
        this.empate = empate;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

}
