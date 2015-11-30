package model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Representa un automata finito con una lista de estados (estado inicial y
 * estados finales)
 *
 * @author Grupo 7 - Lenguajes UNAL
 */
public class Automata {

    /**
     * Constructor de un automata vacio
     */
    public Automata() {
        listaEstados = new ListaEstados();
        listaEstadosFinales = new ListaEstados();
    }

    /**
     * Constructor de un automata con 2 estados (inicail y final) que se
     * conectan por un arco con el simbolo ingresado
     *
     * @param simbolo etiqueta del arco que une los 2 estados
     */
    public Automata(String simbolo) {
        this();

        Estado origen = new Estado(0, true, false, false);
        Estado destino = new Estado(1, false, true, false);
        Arco enlace = new Arco(origen, destino, simbolo);
        origen.addEnlace(enlace);
        estadoInicial = origen;

        listaEstados.insertar(origen);
        listaEstados.insertar(destino);

        listaEstadosFinales.add(destino);
    }

    /**
     * Constructor de un automata del tipo indicado con 2 estados (inicail y
     * final) que se conectan por un arco con el simbolo ingresado
     *
     * @param simbolo etiqueta del arco que une los 2 estados
     * @param tipo Tipo de automata AFN, AFD o AFDMin
     */
    public Automata(String simbolo, TipoAutomata tipo) {
        this(simbolo);

        this.tipoAutomata = tipo;
    }

    /**
     * Cambia la enumeracion de los estados aumentando su numero
     *
     * @param incremento Cantidad en la que se incrementa cada estado
     */
    public void renumerar(int incremento) {
        //Renumerar Estados
        Iterator it = this.listaEstados.getIterator();
        while (it.hasNext()) {
            Estado e = (Estado) it.next();
            e.setId(e.getId() + incremento);
        }
    }

    /**
     * Genera una cadena de caracteres que representa el Automata, muestra cada
     * uno de los estados: <i>([# estado])</i> y sus trasiciones
     * <i>---[etiqueta]---\>[Estado],</i> el estado inicial se representacon un
     * ">" al inicio \>(#) y los estados finales se representan con doble
     * parentesis ((#)))
     *
     * @return Cadena de caracteres que representa el Automata
     */
    public String imprimir() {

        String result = "Automata:\n";
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

    /**
     * Obtiene el estado inicial del Automata (unico)
     *
     * @return Estado inicial
     */
    public Estado getEstadoInicial() {
        return estadoInicial;
    }

    /**
     * Establece el estado inicial del Automata
     *
     * @param estadoInicial Estado inicial que se desea establecer como inicial
     */
    public void setEstadoInicial(Estado estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    /**
     * Obtiene la lista de todos estados que componene el automata
     *
     * @return Estados del automata
     */
    public ListaEstados getEstados() {
        return this.listaEstados;
    }

    /**
     * Obtiene la lista de los estados finales del automata
     *
     * @return Estados finales
     */
    public ListaEstados getEstadosFinales() {
        return listaEstadosFinales;
    }

    /**
     * Establece el alfabeto sobre el que esta definido el automata finito
     *
     * @param alpha Alfabeto sobre el cual esta definido el automata
     */
    public void setAlpha(ArrayList<String> alpha) {
        this.alphabet = alpha;
    }

    public ArrayList<String> getAlphabet() {
        return alphabet;
    }

    /**
     * Establece la expresion regular que representa el automata finito
     *
     * @param regex Expresion regular que representa el automata
     */
    public void setRegex(String regex) {
        this.regex = regex;
    }
    
    public Estado getEstadoById(int id) {
        return this.listaEstados.getEstadoById(id);
    }
    
    public void addEstado(Estado e) {
        this.listaEstados.insertar(e);
    }
    
    public ListaEstados getFinales() {
        return listaEstadosFinales;
    }
    
    public void setInicial(Estado ini) {
        this.estadoInicial = ini;
    }
    
    public Estado getInicial() {
        return estadoInicial;
    }
 
    public String getRegex() {
        return this.regex;
    }

    
    /**
     * Tipos de Automatas:
     * <ul>
     * <li> AFN: Automata Finito No-determinista </li>
     * <li> AFD: Automata Finito Determinista </li>
     * <li> AFDMin: Automata Finito Determinista Minimizado </li>
     * </ul>
     */
    public enum TipoAutomata {

        AFN,
        AFD,
        AFDMin
    }

    public static final String EMPTY = "λ";// "ε";
    public TipoAutomata tipoAutomata;
    private ListaEstados listaEstados;
    private ListaEstados listaEstadosFinales;
    private Estado estadoInicial;
    private String regex;
    private ArrayList<String> alphabet;
}
