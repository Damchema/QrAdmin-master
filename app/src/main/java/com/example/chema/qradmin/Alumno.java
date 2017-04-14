package com.example.chema.qradmin;

import android.graphics.Bitmap;

import java.lang.ref.SoftReference;
import java.text.DateFormat;
import java.util.Date;

import static android.R.attr.bitmap;

/**
 * Created by chema on 14/04/2017.
 */

public class Alumno {
    private String nombre, apellido;
    private String curso;
    private String anio, mes, dia;
    private Bitmap image;

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Alumno(String no, String cu, String timpo) {
        nombre = no;
        curso = cu;
        String[] te = timpo.split("-");
        anio = te[0];
        mes = te[1];
        dia = te[2];
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean calcularedad() {
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        String[] ho = currentDateTimeString.split("/");
        String dia = ho[0];
        String mes = ho[1];
        String anio = ho[2];
        char[] bn = anio.toCharArray();
        anio = new String();
        for (int i = 0; i < 4; i++) {
            anio = anio + bn[i];
        }
        System.out.println("dia" + dia);
        System.out.println(mes);
        System.out.println(anio);
        int va = Integer.parseInt(dia);
        int ru = Integer.parseInt(mes);
        int em = Integer.parseInt(anio);
        int b = ((em - Integer.parseInt(this.anio)));
        b = b - 1;
        if (va >= Integer.parseInt(this.dia) && ru >= Integer.parseInt(this.mes)) {
            b = b + 1;
        }

        if (b >= 18) {
            return true;
        } else {
            return false;
        }

    }

    public int edadcalculada() {
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        String[] ho = currentDateTimeString.split("/");
        String dia = ho[0];
        String mes = ho[1];
        String anio = ho[2];
        char[] bn = anio.toCharArray();
        anio = new String();
        for (int i = 0; i < 4; i++) {
            anio = anio + bn[i];
        }
        System.out.println("dia" + dia);
        System.out.println(mes);
        System.out.println(anio);
        int va = Integer.parseInt(dia);
        int ru = Integer.parseInt(mes);
        int em = Integer.parseInt(anio);
        int b = ((em - Integer.parseInt(this.anio)));
        b = b - 1;
        if (va >= Integer.parseInt(this.dia) && ru >= Integer.parseInt(this.mes)) {
            b = b + 1;
        }

        return b;

    }

    public void setApelli(String apelli){
        apellido=apelli;
    }
}
