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
    protected int contTurno;
    protected Diccionario dic;

    public Juego() {
        dic = new Diccionario();
    }

    public void menu() {
        Scanner teclado = new Scanner(System.in);
        int opc = 0;
        do {
            System.out.println("Juego ahorcado.\n");
            System.out.println("\t1. Jugar.\n\t2. Gestionar diccionario.\n");
            System.out.println("Introduce la opción deseada:");
            opc = teclado.nextInt();

            switch (opc) {
                case 1:
                    inicioJuego();
                    break;
                case 2:
                    menuDiccionario();
                    break;
                default:
                    System.out.println("Error.");
            }
        } while (opc <= 0 || opc >= 2);
    }

    public void menuDiccionario() {
        int opc = 0;
        Scanner teclado = new Scanner(System.in);
        String password = new String();
        System.out.println("Diccionario\n");
        do {
            System.out.println("Introduce la contraseña (palomitas): ");
            password = teclado.next();
        } while (!password.equalsIgnoreCase("palomitas"));
        System.out.println("Enhorabuena. Sistema desbloqueado.");
        do {
            System.out.println("\n\t1. Lista diccionario.\n\t2. Actualiza el diccionario.\n\t3. Salir.\n");
            System.out.println("Introduce la opción deseada: ");
            opc = teclado.nextInt();
            switch (opc) {
                case 1:
                    dic.listarDiccionario();
                    break;
                case 2:
                    dic.actualizarDiccionario();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Error.");
            }
        } while (opc != 3);
    }

    public void inicioJuego() {
        String palabra = dic.palabras[(int)(Math.random()*dic.cantidadPalabras())];
        System.out.println(palabra);
    }
}
