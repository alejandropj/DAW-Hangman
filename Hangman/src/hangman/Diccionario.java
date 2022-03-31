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
public class Diccionario {
    protected final String palabras[];
    
    public Diccionario(){
        palabras= new String[20];
        palabras[0]="CARACOLA";
        palabras[1]="PAPEL";
        palabras[2]="PERRO";
        palabras[3]="MONITOR";
        palabras[4]="COCO";
    }
    
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
                //CORREGIR EXCEPCION
                palabras[cantidadPalabras()]= palabra.toUpperCase();
            }catch(introduccionNum ex){
                System.out.println(ex.getMessage());
            }
        }
    }
        
    public int cantidadPalabras(){
        int i=0;
        while(this.palabras[i]!=null){
            i++;
        }
        return i;
    }
}
