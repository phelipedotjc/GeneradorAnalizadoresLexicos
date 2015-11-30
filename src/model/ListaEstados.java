package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
    
    /**
     * Con este método, se vuelven a marcar todos los estados de la lista
     * como no visitados. 
     */
    public void resetVisitas() {
        for (int i = 0; i < cantidad(); i++) {
            getEstado(i).setVisitado(false);
        }
    }

    /**
     * Método heredado reescrito para comparar dos listas de estados.
     *
     * Dos listas de estados son iguales si tienen la misma cantidad de
     * elementos y si los mismos son iguales en ambas listas.
     *
     * @param o ListaEstados con el que se comparará la lista actual.
     * @return <ul> <li><b>0 (Cero)</b> si son iguales                       </li>
     * <li><b>1 (Uno)</b> si Estado es mayor que <b>e</b>        </li>
     * <li><b>-1 (Menos Uno)</b> si Estado es menor que <b>e</b> </li>
     * </ul>.
     */
    public int compareTo(Object o) {

        int result = -1;

        ListaEstados otro = (ListaEstados) o;

        //Se ordenan ambas Listas
        otro.ordenar();
        this.ordenar();

        // comparación de cantidad de estados
        if (this.cantidad() == otro.cantidad()) {

            // comparación uno a uno
            for (int i = 0; i < this.cantidad(); i++) {

                Estado a = this.getEstado(i);
                try {
                    otro.getEstadoById(a.getId());
                } catch (Exception ex) {
                    return -1;
                }
            }

            result = 0; //Si llego hasta aqui es xq los elementos son iguales
        }

        return result;
    }

    /**
     * Método para ordenar los estados de la lista
     */
    public void ordenar() {

        Estado a[] = new Estado[1];

        a = this.toArray(a);
        Comparator<Estado> c = null;

        Arrays.sort(a, c);

        this.removeAll(this);

        for (int i = 0; i < a.length; i++) {
            this.add(a[i]);
        }
    }

    public boolean contieneInicial() {
        //verificar q contenga un estado inicial
        Estado ini = null;
        try {
            ini = getEstadoInicial();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean contieneFinal() {
        ListaEstados fin;
        try {
            fin = getEstadosFinales();
        } catch (AutomataException ex) {
            return false;
        }

        if (fin.cantidad() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public ListaEstados getEstadosFinales() throws AutomataException {
        ListaEstados nuevaLista = new ListaEstados();
        for (int i = 0; i < cantidad(); i++) {
            if (getEstado(i).isEstadofinal()) {
                nuevaLista.insertar(getEstado(i));
            }
        }
        return nuevaLista;
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

    public boolean isMarcado() {
        return marcado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private int id;
    private boolean marcado;
}
