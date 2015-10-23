package model;

import java.util.ArrayList;
import java.util.Iterator;
import model.exeptions.AutomataException;

/**
 *
 * @author Grupo 7 - Lenguajes UNAL
 */
public class ListaEstados extends ArrayList<Estado> {

    public void insertar(Estado e) {
        this.add(e);
    }

    public Estado getEstadoById(int index) {
        Iterator it = this.getIterator();
        while (it.hasNext()) {
            Estado e = (Estado) it.next();
            if (e.getId() == index) {
                return e;
            }
        }
        throw new IndexOutOfBoundsException(" No existe en esta lista un Estado con id = " + index);
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
     * Devuelve un iterador para recorrer el listado de estados.
     *
     * @return Iterador sobre el conjunto de estados.
     */
    public Iterator<Estado> getIterator() {
        return this.iterator();
    }

    public Estado getEstadoInicial() throws AutomataException {
        int indice_ini = 0;
        int cant_iniciales = 0;
        for (int i = 0; i < cantidad(); i++) {
            if (getEstado(i).isEstadoinicial()) {
                indice_ini = i;
                cant_iniciales++;
            }
        }
        if (cant_iniciales == 1) {
            return getEstado(indice_ini);
        } else {
            throw new AutomataException("Solo debe haber un estado incial, y en esta lista existen " + cant_iniciales);
        }
    }

    public Estado getEstado(int index) {
        return this.get(index);
    }

    /**
     * Setter for property marcado.
     *
     * @param marcado New value of property marcado.
     */
    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private boolean marcado;
}
