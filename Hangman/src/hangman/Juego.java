/*
 * Copyright (c) 2022, Alejandro Parra Jiménez
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package hangman;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase Juego. Contiene la clase Diccionario y es encargada de iniciar el
 * juego.
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
     * Constructor vacío que inicializa el turno a 5, crea una instancia a
     * Diccionario
     * <br>y un ArrayList de las letras introducidas.
     */
    public Juego() {
        letras = new ArrayList();
        dic = new Diccionario();
        contTurno = 5;
    }

    /**
     * Método que muestra el menú inicial.
     */
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

    /**
     * Método que muestra el menú del diccionario.
     */
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

    /**
     * Método que incia el juego.
     */
    public void inicioJuego() {
        boolean bool;
        palabra = dic.palabras[(int) (Math.random() * dic.cantidadPalabras())];
        char[] palabraChar = palabra.toCharArray();
        char[] auxiliarChar = new char[palabra.length()];
        /* Descomentar para conocer la palabra.
        System.out.println(palabra);*/

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

    /**
     * Método que llama a pintarJuego y se encarga de recoger la jugada del
     * usuario
     * <br> y la analiza.
     *
     * @param palabraChar
     * @param auxiliarChar
     * @return si se ha acertado la palabra.
     */
    public boolean jugada(char[] palabraChar, char[] auxiliarChar) {
        Scanner teclado = new Scanner(System.in);
        boolean bool = false;
        String adivinanza = new String();
        System.out.println("Turno " + contTurno + " de 5.");
        pintarJuego(auxiliarChar);

        do {
            try {
                System.out.println("Introduce una letra o la palabra: ");
                bool = true;
                adivinanza = teclado.next().toUpperCase();
                Comprobaciones.hayNumero(adivinanza);
                bool = false;
            } catch (introduccionNum ex) {
                System.out.println(ex.getMessage());
            }
        } while (bool);

        if (adivinanza.length() == 1) { //UNA LETRA INTRODUCIDA
            char letra = adivinanza.charAt(0);
            boolean interruptor = false;
            
            letras.add(letra);

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
            if (!interruptor) {
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

    /**
     * Método que dibuja la palabra(con la sintaxis del ahorcado)
     * <br> y las letras ya introducidas.
     *
     * @param auxiliarChar
     */
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
