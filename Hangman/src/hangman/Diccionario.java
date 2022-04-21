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

import java.util.Scanner;

/**
 * Clase Diccionario. Contiene las palabras para Juego, y las maneja él mismo.
 * @author ale
 */
public class Diccionario {
    /**
     * Array de String. Contiene las palabras del diccionario.
     */
    protected final String palabras[];
    
    /**
     * Constructor que inicializa el diccionario con 5 palabras.
     */
    public Diccionario(){
        palabras= new String[20];
        palabras[0]="CARACOLA";
        palabras[1]="PAPEL";
        palabras[2]="PERRO";
        palabras[3]="MONITOR";
        palabras[4]="COCO";
    }
    
    /**
     * Método que imprime las palabras que contiene el diccionario, e invoca a 
     * <br> cantidadPalabras().
     */
    public void listarDiccionario(){
        boolean bool=false;
        System.out.println("Hay "+cantidadPalabras()+" palabras de 20.");
        for (int i = 0; i < palabras.length && bool==false; i++) {
            if(palabras[i]!=null)
                System.out.println((i+1)+". " + palabras[i]);
            else
                bool = true;
        }
        
    }
    /**
     * Método que invoca a cantidadPalabras(), analiza si hay huecos libres 
     * <br>en palabras[] para introducir una nueva.
     */
    public void actualizarDiccionario(){
        String palabra = new String();
        if(cantidadPalabras()>=20){
            System.out.println("Se ha alcanzado la máxima cantidad de palabras. No se puede introducir más.");
        } else{
            Scanner teclado = new Scanner(System.in);
            System.out.println("Hay "+cantidadPalabras()+" palabras de 20.");
            System.out.println("Introduce una palabra: ");
            try{
                palabra = teclado.next();
                Comprobaciones.hayNumero(palabra);
                palabras[cantidadPalabras()]= palabra.toUpperCase();
            }catch(introduccionNum ex){
                System.out.println(ex.getMessage());
            }
        }
    }
    /**
     * Método que analiza la cantidad de huecos ocupados en palabras[]
     * @return huecos ocupados
     */
    public int cantidadPalabras(){
        int i=0;
        while(palabras[i]!=null){
            i++;
        }
        return i;
    }
}
