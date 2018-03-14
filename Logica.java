package com.example.angel.eltresenraya;

import android.widget.ImageView;

/**
 * clase con la lógica del juego
 * Created by Angel on 27/02/2018.
 */

public class Logica {

    public boolean GanaX(ImageView[][] matriz) {
        if (matriz[0][0].getTag().equals(R.drawable.equis) && matriz[0][1].getTag().equals(R.drawable.equis) && matriz[0][2].getTag().equals(R.drawable.equis)) {
            return true;
        } else if (matriz[1][0].getTag().equals(R.drawable.equis) && matriz[1][1].getTag().equals(R.drawable.equis) && matriz[1][2].getTag().equals(R.drawable.equis)) {
            return true;
        } else if (matriz[2][0].getTag().equals(R.drawable.equis) && matriz[2][1].getTag().equals(R.drawable.equis) && matriz[2][2].getTag().equals(R.drawable.equis)) {
            return true;
        } else if (matriz[0][0].getTag().equals(R.drawable.equis) && matriz[1][1].getTag().equals(R.drawable.equis) && matriz[2][2].getTag().equals(R.drawable.equis)) {
            return true;
        } else if (matriz[0][0].getTag().equals(R.drawable.equis) && matriz[1][0].getTag().equals(R.drawable.equis) && matriz[2][0].getTag().equals(R.drawable.equis)) {
            return true;
        } else if (matriz[0][1].getTag().equals(R.drawable.equis) && matriz[1][1].getTag().equals(R.drawable.equis) && matriz[2][1].getTag().equals(R.drawable.equis)) {
            return true;
        } else if (matriz[0][2].getTag().equals(R.drawable.equis) && matriz[1][2].getTag().equals(R.drawable.equis) && matriz[2][2].getTag().equals(R.drawable.equis)) {
            return true;
        } else if (matriz[0][2].getTag().equals(R.drawable.equis) && matriz[1][1].getTag().equals(R.drawable.equis) && matriz[2][0].getTag().equals(R.drawable.equis)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean GanaO(ImageView[][] matriz) {
        if (matriz[0][0].getTag().equals(R.drawable.circulo) && matriz[0][1].getTag().equals(R.drawable.circulo) && matriz[0][2].getTag().equals(R.drawable.circulo)) {
            return true;
        } else if (matriz[1][0].getTag().equals(R.drawable.circulo) && matriz[1][1].getTag().equals(R.drawable.circulo) && matriz[1][2].getTag().equals(R.drawable.circulo)) {
            return true;
        } else if (matriz[2][0].getTag().equals(R.drawable.circulo) && matriz[2][1].getTag().equals(R.drawable.circulo) && matriz[2][2].getTag().equals(R.drawable.circulo)) {
            return true;
        } else if (matriz[0][0].getTag().equals(R.drawable.circulo) && matriz[1][1].getTag().equals(R.drawable.circulo) && matriz[2][2].getTag().equals(R.drawable.circulo)) {
            return true;
        } else if (matriz[0][0].getTag().equals(R.drawable.circulo) && matriz[1][0].getTag().equals(R.drawable.circulo) && matriz[2][0].getTag().equals(R.drawable.circulo)) {
            return true;
        } else if (matriz[0][1].getTag().equals(R.drawable.circulo) && matriz[1][1].getTag().equals(R.drawable.circulo) && matriz[2][1].getTag().equals(R.drawable.circulo)) {
            return true;
        } else if (matriz[0][2].getTag().equals(R.drawable.circulo) && matriz[1][2].getTag().equals(R.drawable.circulo) && matriz[2][2].getTag().equals(R.drawable.circulo)) {
            return true;
        } else if (matriz[0][2].getTag().equals(R.drawable.circulo) && matriz[1][1].getTag().equals(R.drawable.circulo) && matriz[2][0].getTag().equals(R.drawable.circulo)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean Empate(ImageView[][] matriz) {
        int contador = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matriz[i][j].getTag().equals(R.drawable.cuadrado)) {
                    contador++;
                }
            }
        }
        if (contador < 1) {
            return true;
        } else {
            return false;
        }
    }

}
