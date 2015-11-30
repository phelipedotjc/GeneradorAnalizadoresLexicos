/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ManuelFelipe
 */
public class TransitionMatrixKey {

    // fila, indicadas por una lista de estados

    private ListaEstados indiceEstados;
    // columna, indicada por un token del lenguaje
    private Token indiceToken;

    public TransitionMatrixKey(ListaEstados list, Token tok) {
        this.indiceEstados = list;
        this.indiceToken = tok;
    }

    public int compareTo(Object otro) {
        TransitionMatrixKey o = (TransitionMatrixKey) otro;
        if (indiceToken.getValor().compareTo(o.getIndiceToken().getValor()) == 0) {
            if (indiceEstados.compareTo(o.getIndiceEstados()) == 0) {
                return 0;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    public ListaEstados getIndiceEstados() {
        return indiceEstados;
    }

    public void setIndiceEstados(ListaEstados indiceEstados) {
        this.indiceEstados = indiceEstados;
    }

    public Token getIndiceToken() {
        return indiceToken;
    }

    public void setIndiceToken(Token indiceToken) {
        this.indiceToken = indiceToken;
    }
    
    
}
