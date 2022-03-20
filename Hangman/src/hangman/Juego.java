/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.util.Scanner;

/**
 *
 * @author ale
 */
public class Juego {
    private int contTurno;
    
    public Juego(){
        
    }
    
    public void menu(){
        Scanner teclado = new Scanner(System.in);
        System.out.println("Juego ahorcado.\n");
        System.out.println("\t1. Jugar.\n\t2. Gestionar diccionario.\n");
        System.out.println("Introduce la opción deseada:");
        int opc=teclado.nextInt();
        if(opc==1){
            //Inicio juego
        }
        else if(opc==2){
            //Diccionario
        }
    }
}
