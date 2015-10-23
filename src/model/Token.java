package model;

/**
 * Clase que encapsula a cada componente enviado desde el analizador léxico al
 * analizador sintáctico para su procesamiento.
 *
 * @author Grupo 7 - Lenguajes UNAL
 */
public class Token implements Comparable<Token> {

    /**
     * Constructor principal del Token a partir del símbolo que se le pasa. Se
     * asume que el símbolo es válido ya que se deja la validcación al
     * analizador léxico.
     */
    public Token(String simbolo) {
        this.valor = simbolo;
        this.setTipo(simbolo);
    }

    /**
     * Método abstracto de la clase Comparable implementado por Token para poder
     * utilizar el operador == para las comparaciones <br><br>
     *
     * @param t Token con el que se comparará el actual.
     * @return <ul> <li><b>0 (Cero)</b> si son iguales         </li>
     * <li><b>-1 (Menos Uno)</b> si no son iguales </li>
     * </ul>
     */
    public int compareTo(Token t) {
        if (this.getTipo() == t.getTipo()
                && this.getValor().compareTo(t.getValor()) == 0) {
            return 0;
        } else {
            return -1;
        }
    }

    private void setTipo(String simbolo) {

        if (simbolo.isEmpty()) {
            this.tipo = TipoToken.FIN;
        } else {

            switch (simbolo.charAt(0)) {
                case '*':
                    this.tipo = TipoToken.KLEENE;
                    break;
                case '+':
                    this.tipo = TipoToken.PLUS;
                    break;
                case '?':
                    this.tipo = TipoToken.CEROUNO;
                    break;
                case '|':
                    this.tipo = TipoToken.OR;
                    break;
                case '(':
                    this.tipo = TipoToken.PARI;
                    break;
                case ')':
                    this.tipo = TipoToken.PARD;
                    break;
                default:
                    this.tipo = TipoToken.ALFA;
                    this.valor = simbolo;
                    break;
            }
        }
    }

    /**
     * Función que retorna el tipo de token actual
     *
     * @return Retorna el tipo de token
     */
    public TipoToken getTipo() {
        return tipo;
    }

    /**
     * Método que retorna el valor (char) del token actual.
     *
     * @return
     */
    public String getValor() {
        return valor;
    }

    public enum TipoToken {

        NONE, // token erróneo
        KLEENE, // '*' --> cerradura de kleene
        PLUS, // '+' --> cerradura positiva de kleene
        CEROUNO, // '?' --> Cero o una instancia
        OR, // '|' --> Disyunción
        PARI, // '(' --> Paréntesis izquierdo
        PARD, // ')' --> Paréntesis derecho
        ALFA, // Cualquier letra del alfabeto
        FIN         // Fin de la expresión regular
    }

    private TipoToken tipo;
    private String valor;
}
