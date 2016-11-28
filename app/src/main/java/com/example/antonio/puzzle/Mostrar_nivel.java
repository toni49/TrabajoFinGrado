package com.example.antonio.puzzle;

/**
 * Created by antonio on 8/7/16.
 */
public class Mostrar_nivel {


    private static int nivel = 0;
    private static int fallos = 0;

    public void set_nivel(int valor){
        nivel = valor;
    }

    public int get_nivel() {return nivel; }

    public int get_fallos() {return fallos;}

    public void set_fallos(int err) { fallos = err; }



}
