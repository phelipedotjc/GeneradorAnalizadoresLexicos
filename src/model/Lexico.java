package model;

import model.exeptions.LexicalError;

/**
 *
 * @author Grupo 7 - Lenguajes UNAL
 */
public class Lexico {

    public Lexico(String regex, Alfabeto alfabeto) {
        this.regex = new StringBuffer(regex);
        this.alphabet = alfabeto;
        this.specials = "*+?|()";
    }

//    /**
//     * Consume la entrada y devuelve el siguiente a procesar. Si no se trata de
//     * un token que pertenezca al alfabeto, entonces se lanza una Excepción.
//     *
//     * @return El siguiente caracter de la expresión regular
//     * @throws java.lang.Exception Se lanza una excepción si el siguiente símbolo
//     *                             no pertenece al alfabeto o a alguno de los
//     *                             símbolos conocidos
//     */
    public Token next() throws LexicalError {
        String s = consume();
        Token siguiente;

        if (s.equalsIgnoreCase(" ") || s.equalsIgnoreCase("\t")) {
            // Los espacios y tabuladores se ignoran
            siguiente = next();

        } else if (this.specials.contains(s) || this.alphabet.contiene(s)
                || s.length() == 0) {
            //verifica que sea un simbolo, un valor del alfabeto o un caracter vacio (terminacion)
            siguiente = new Token(s);

        } else {
            String except = "Simbolo no valido [ " + s + " ]";
            throw new LexicalError(except);
        }

        return siguiente;
    }

    /**
     * Extrae la primera letra de la regex y la devuelve como un String.
     *
     * @return El siguiente caracter en la regex
     */
    private String consume() {

        String consumido = "";
        if (this.regex.length() > 0) {
            consumido = Character.toString(this.regex.charAt(0));
            this.regex.deleteCharAt(0);
        }

        return consumido;
    }

    // expresion a analizar
    private StringBuffer regex;
    // conjunto de simbolos posibles
    private Alfabeto alphabet;
    // forma parte de la tabla de simbolos
    private String specials;
}
