package model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Grupo 7 - Lenguajes UNAL
 */
public class Alfabeto extends ArrayList<String>  {

    /**
     * Constructor del alfabeto: lista de simbolos como string (ArrayList<String>)
     * @param simbolos Cadena de caracteres, en la que cada carcater es un simbolo permitido
     */
    public Alfabeto(String simbolos) {

        for (int i = 0; i < simbolos.length(); i++) {
            
            String temp = "" + simbolos.charAt(i);

            if (!this.contains(temp) && temp.length() > 0) {
                this.add(temp);
            }
            
        }
        this.ordenar();
    }
    
    /**
     * Metodo que ordena lexicograficmente los simbolos del alfabeto
     */
    private void ordenar() {
        
        String a[] = new String[1];
        a = this.toArray(a);
        Arrays.sort(a);
        this.removeAll(this);
        this.addAll(Arrays.asList(a));
        
    }

    /**
     * Metodo que verifica si un simbolo se ecuentra en la lista
     * @param simbolo Simbolo que se desea verificar si esta contenido en el alfabeto
     * @return Retorna true si el simbolo esta contenido en el alfabeto
     */
    public boolean contiene(String simbolo) {
        return this.contains(simbolo);
    }
    
}
