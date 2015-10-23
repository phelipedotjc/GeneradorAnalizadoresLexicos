package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Se implementa una tabla Hash interna para la lista de enlaces que permita
 * indexar para cada símbolo del alfabeto, el índice del array list con el
 * enlace asociado.
 *
 * Por cada nuevo enlace, se tendrá que agregar la Hash la entrada
 * correspondiente. Esta tabla será útil para buscar los enlaces asociados a un
 * símbolo cuando se requiera recorrer el Automata.
 *
 * En esta tabla solo se guardarán los índices de enlaces asociados a símbolso
 * no vacíos.
 *
 * @author Grupo 7 - Lenguajes UNAL
 */
public class ListaArcos extends ArrayList<Arco> {

    public ListaArcos() {
        this.TablaEnlaces = new HashMap<String, Integer>();
        this.vacios = new ArrayList<Arco>();
    }

    /**
     * Insertar un nuevo estado a la lista
     *
     * @param e Estado a insertar.
     */
    public void insertar(Arco e) {

        int indexToInsert = this.cantidad();
        String simbolo = e.getEtiqueta();

        this.add(e);

        if (e.isVacio()) {
            this.agregarEnlaceVacio(e);
        } else {
            this.TablaEnlaces.put(simbolo, indexToInsert);
        }
    }

    public Arco getEnlaceSimbolo(String symbol) {
        Integer index = this.TablaEnlaces.get(symbol);
        Arco result = null;

        if (index != null) {
            result = this.get(index);
        }
        return result;
    }

    /**
     * Permite insertar un nuevo enlace cuya etiqueta es VACIO en la lista de
     * vacios
     *
     * @param e
     */
    private void agregarEnlaceVacio(Arco e) {
        this.getVacios().add(e);
    }

    /**
     * Obtener la cantidad de estados de la lista
     *
     * @return Número de estados de la lista
     */
    public int cantidad() {
        return this.size();
    }

    /**
     * Obtener la lista de enlaces asociados al símbolo vacíó
     *
     * @return Lista de enlaces del simbolo vacío
     */
    public ArrayList<Arco> getVacios() {
        return vacios;
    }

    /**
     * Devuelve un iterador para recorrer el listado de estados.
     *
     * @return Iterador sobre el conjunto de estados.
     */
    public Iterator<Arco> getIterator() {
        return this.iterator();
    }

    private HashMap<String, Integer> TablaEnlaces;
    private ArrayList<Arco> vacios;
}
