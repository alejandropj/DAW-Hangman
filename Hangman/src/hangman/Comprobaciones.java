/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import static java.lang.Character.isAlphabetic;

/**
 *
 * @author parjimal
 */
public class Comprobaciones {
    
    public static void hayNumero(String palabra) throws introduccionNum{
        char [] letras = palabra.toCharArray();
        
        for (int i = 0; i < letras.length; i++) {
            if(!isAlphabetic(letras[i])){
                throw new introduccionNum();
            }
        }
    }
    
    
    
}
