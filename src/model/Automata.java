package model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Grupo 7 - Lenguajes UNAL
 */
public class Automata {

    public Automata() {
        listaEstados = new ListaEstados();
        listaEstadosFinales = new ListaEstados();
    }

    public Automata(String simbolo) {
        listaEstados = new ListaEstados();

        Estado origen = new Estado(0, true, false, false);
        Estado destino = new Estado(1, false, true, false);
        Arco enlace = new Arco(origen, destino, simbolo);
        origen.addEnlace(enlace);
        estadoInicial = origen;

        listaEstados.insertar(origen);
        listaEstados.insertar(destino);

        listaEstadosFinales = new ListaEstados();
        listaEstadosFinales.add(destino);
    }

    public Automata(String simbolo, int tipo) {
        this(simbolo);
        this.tipoAutomata = tipo;
    }

    public void renumerar(int incremento) {
        //Renumerar Estados
        Iterator it = this.listaEstados.getIterator();
        while (it.hasNext()) {
            Estado e = (Estado) it.next();
            e.setId(e.getId() + incremento);
        }
    }

    public String imprimir() {

        String result = "AFN Generado: \n";
        Iterator it = this.listaEstados.getIterator();
        while (it.hasNext()) {
            Estado e = (Estado) it.next();
            result += "\n";
            if (e.isEstadoinicial()) {
                result += ">";
            }
            if (e.isEstadofinal()) {
                result += "(";
            }
            result += "(" + e.getId() + ")";
            if (e.isEstadofinal()) {
                result += ")";
            }
            result += "\n";

            Iterator itenlaces = e.getEnlaces().getIterator();
            while (itenlaces.hasNext()) {
                Arco enlace = (Arco) itenlaces.next();
                result += " ---" + enlace.getEtiqueta() + "---> " + enlace.getDestino().getId() + "\n";
            }
        }
        return result;
    }

    public Estado getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(Estado estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public ListaEstados getListaEstados() {
        return listaEstados;
    }

    public ListaEstados getListaEstadosFinales() {
        return listaEstadosFinales;
    }

    public ListaEstados getEstados() {
        return this.listaEstados;
    }

    public ListaEstados getFinales() {
        return listaEstadosFinales;
    }

    public Estado getInicial() {
        return estadoInicial;
    }

    public void setAlpha(ArrayList<String> alpha) {
        this.alphabet = alpha;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public enum TipoAutomata {

        AFN,
        AFD,
        AFDMin
    }

    public static final String EMPTY = "λ";// "ε";
    public int tipoAutomata;
    private ListaEstados listaEstados;
    private ListaEstados listaEstadosFinales;
    private Estado estadoInicial;
    private String regex;
    private ArrayList<String> alphabet;
}
