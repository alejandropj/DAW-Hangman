/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ale
 */
public class Juego {

    protected int contTurno;
    protected Diccionario dic;
    /**
     * Palabra aleatoria de Diccionario.
     */
    protected String palabra;
    /**
     * ArrayList de las letras introducidas.
     */
    protected ArrayList<Character> letras;

    /**
     *
     */
    public Juego() {
        letras = new ArrayList();
        dic = new Diccionario();
        contTurno = 5;
    }

    public void menu() {
        Scanner teclado = new Scanner(System.in);
        int opc;
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
        int opc;
        Scanner teclado = new Scanner(System.in);
        String password;
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
                    System.out.println("EXCEPCION DICCIONARIO.");
            }
        } while (opc != 3);
    }

    public void inicioJuego() {
        boolean bool;
        palabra = dic.palabras[(int) (Math.random() * dic.cantidadPalabras())];
        char[] palabraChar = palabra.toCharArray();
        char[] auxiliarChar = new char[palabra.length()];
        System.out.println(palabra);

        do {
            bool = jugada(palabraChar, auxiliarChar);
            if (bool) {
                System.out.println("Enhorabuena. Has ganado.");
            } else {
                contTurno--;
                if (contTurno == 0) {
                    System.out.println("Fin de juego, has perdido.");
                }
            }
        } while (!bool && contTurno != 0);

    }

    public boolean jugada(char[] palabraChar, char[] auxiliarChar) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Turno " + contTurno + " de 5.");
        pintarJuego(auxiliarChar);

        System.out.println("Introduce una letra o la palabra: ");
        boolean bool = false;
        String adivinanza = teclado.next().toUpperCase();
        if (adivinanza.length() == 1) { //UNA LETRA INTRODUCIDA
            char letra = adivinanza.charAt(0);
            boolean interruptor = false;

            //comprobacion si la letra ya existe
            letras.add(letra);
            /*
            if (letras.size() >= 2) {
                for (int i = 0; i < letras.size() - 1
                        && interruptor == false; i++) {
                    for (int j = 0; j
                            < letras.size(); j++) {
                        if (letras.get(i) == letras.get(j)) {
                            interruptor = true;
                            System.out.println(
                                    "La letra ya ha sido introducida.Vuelva a intentarlo.\n");
                            letras.remove(j);
                        }
                    }
                }
            }*/

            //comprobacion de si la letra está en la palabra
            for (int i = 0; i < palabraChar.length; i++) {
                if (letra == palabraChar[i]) {
                    auxiliarChar[i] = letra;
                }
            }
            //comprobacion de si auxiliarChar tiene huecos (palabra completa)
            for (int i = 0; i < auxiliarChar.length && interruptor == false; i++) {
                if (auxiliarChar[i] == '\u0000') {
                    interruptor = true;
                }
            }
            if(!interruptor){
                bool = true;
                System.out.println(palabra);
            }

        } else if (adivinanza.equalsIgnoreCase(palabra)) { //INTENTO DE PALABRA, ACERTADA
            bool = true;
        } else { //NO ACETADA
            System.out.println("La palabra no es correcta.");
        }

        return bool;
    }

    public void pintarJuego(char[] auxiliarChar) {
        //huecos del ahorcado
        System.out.println("");
        for (int i = 0; i < palabra.length(); i++) {
            if (auxiliarChar[i] == '\u0000') {
                System.out.print(" _ ");
            } else {
                System.out.print(" " + auxiliarChar[i] + " ");
            }
        }

        //ArrayList letras
        System.out.println("\n\nLetras ya introducidas: ");
        for (int i = 0; i < letras.size(); i++) {
            System.out.print(" " + letras.get(i) + " ");
        }
        System.out.println("");
    }
}
