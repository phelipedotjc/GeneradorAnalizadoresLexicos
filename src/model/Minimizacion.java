/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import model.exeptions.AutomataException;

/**
 *
 * @author ManuelFelipe
 */
public class Minimizacion {

    Automata AFD;

    public Minimizacion(Automata a) {
        this.AFD = a;
    }

    public Thompson minimizar() throws AutomataException {
        ArrayList<ListaEstados> anterior = new ArrayList<ListaEstados>();
        ArrayList<ListaEstados> actual = new ArrayList<ListaEstados>();

        int nro_est = 0;
        ListaEstados nofinales = AFD.getNoFinales();
        ListaEstados finales = AFD.getFinales();

        if (nofinales != null && nofinales.cantidad() > 0) {
            nofinales.setId(nro_est++);
            anterior.add(nofinales);
        }

        if (finales != null && finales.cantidad() > 0) {
            finales.setId(nro_est++);
            anterior.add(finales);
        }

        boolean seguir = true;
        while (seguir) {

            int cant = 0;
            for (ListaEstados cadaLista : anterior) {
                Iterator it = separarGrupos(anterior, cadaLista);
                while (it != null && it.hasNext()) {
                    ListaEstados list = (ListaEstados) it.next();
                    list.setId(cant++);
                    actual.add(list);
                }
            }

            if (anterior.size() == actual.size()) {
                seguir = false;
            } else {
                anterior = actual;
                actual = new ArrayList<ListaEstados>();
            }
        }
       //Fin del Algoritmo de Minimizacion.

        //Ahora se convierte "actual"  en "Automata"
        //Primero creamos los estados
        Thompson AFDM = new Thompson();
        Iterator it = actual.iterator();
        while (it.hasNext()) {
            ListaEstados lest = (ListaEstados) it.next();
            Estado nuevo = new Estado(lest.getId(), false, false, false);

            //Es estado inicial
            try {
                lest.getEstadoInicial();
                nuevo.setEstadoinicial(true);
                AFDM.setInicial(nuevo);
            } catch (Exception ex) {
                nuevo.setEstadoinicial(false);
            }

            //Es estado final
            if (lest.getEstadosFinales().cantidad() > 0) {
                nuevo.setEstadofinal(true);
                AFDM.getFinales().insertar(nuevo);
            } else {
                nuevo.setEstadofinal(false);
            }
            AFDM.addEstado(nuevo);
        }

        //Segundo, creamos los enlaces
        it = actual.iterator();
        while (it.hasNext()) {
            ListaEstados lest = (ListaEstados) it.next();
            Estado estado_afdm = AFDM.getEstadoById(lest.getId());
            Estado representante = lest.get(0);

            Iterator itenlaces = representante.getEnlaces().getIterator();
            while (itenlaces.hasNext()) {
                Arco e = (Arco) itenlaces.next();
                ListaEstados lista_destino = enqueLista(actual, e.getDestino());
                Estado est_destino = AFDM.getEstadoById(lista_destino.getId());
                Arco nuevo_enlace = new Arco(estado_afdm, est_destino, e.getEtiqueta());
                estado_afdm.addEnlace(nuevo_enlace);
            }
        }

        return AFDM;
    }

    public Iterator separarGrupos(ArrayList<ListaEstados> todas,
            ListaEstados lista) {
        Hashtable listasNuevas = new Hashtable();
        for (Estado estado : lista) {
            String claveSimbolos = "";
            String claveEstados = "";

            for (Arco enlace : estado.getEnlaces()) {
                Estado dest = enlace.getDestino();
                ListaEstados tmp = enqueLista(todas, dest);
                claveSimbolos += enlace.getEtiqueta().trim();
                claveEstados += tmp.getId();

            }
            String clave = generarClaveHash(claveSimbolos, claveEstados);
            if (listasNuevas.containsKey(clave)) {
                ((ListaEstados) listasNuevas.get(clave)).insertar(estado);
            } else {
                ListaEstados nueva = new ListaEstados();
                nueva.insertar(estado);
                listasNuevas.put(clave, nueva);
            }
        }
        return listasNuevas.values().iterator();
    }

    public ListaEstados enqueLista(ArrayList<ListaEstados> listas, Estado estado) {
        for (ListaEstados lista : listas) {
            try {
                lista.getEstadoById(estado.getId());
                return lista;
            } catch (Exception ex) {
            }
        }
        return null;
    }

    public String generarClaveHash(String simbolos, String estados) {
        String cadenaFinal = "";

        char est[] = estados.toCharArray();
        char c[] = simbolos.toCharArray();
        boolean hayCambios = true;
        for (int i = 0; hayCambios; i++) {
            hayCambios = false;
            for (int j = 0; j < c.length - 1; j++) {
                if (c[j] > c[j + 1]) {

                  //intercambiar(arreglo, j, j+1);
                    //ini intercambiar
                    char tmp = c[j + 1];
                    c[j + 1] = c[j];
                    c[j] = tmp;

                    char tmpEst = est[j + 1];
                    est[j + 1] = est[j];
                    est[j] = tmpEst;
                    //fin intercambiar

                    hayCambios = true;
                }
            }
        }
        cadenaFinal = String.copyValueOf(c) + String.copyValueOf(est);
        return cadenaFinal;
    }

}
