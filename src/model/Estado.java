package model;

/**
 *
 * @author Grupo 7 - Lenguajes UNAL
 */
public class Estado implements Comparable<Estado> {

    public Estado(int id, boolean esInicial, boolean esFinal, boolean visitado) {
        this.id = id;
        this.visitado = visitado;
        this.fin = esFinal;
        this.inicial = esInicial;
        this.enlaces = new ListaArcos();
    }

    @Override
    public int compareTo(Estado e) {
        if (this.getId() == e.getId()) {
            return 0;
        } else if (this.getId() > e.getId()) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * Agrega un nuevo enlace que sale de este estado
     *
     * @param e Arco a agregar
     */
    public void addEnlace(Arco e) {
        // Insertar en la lista de enlaces para tener un método eficiente de
        // recorrido en el futuro
        enlaces.insertar(e);
    }

    /**
     * Obtiene el primer enlace asociado al simbolo especificado que está
     * cargado en el Hash de enlaces
     *
     * @param simbolo
     * @return
     */
    public Estado getDestinoFromHash(String simbolo) {
        Arco link = this.getEnlaceSimboloFromHash(simbolo);
        Estado result = null;

        if (link != null) {
            result = link.getDestino();
        }
        return result;
    }

    /**
     * Devuelve el enlace relacionado con el símbolo
     *
     * @param simbolo
     * @return
     */
    public Arco getEnlaceSimboloFromHash(String simbolo) {
        return this.enlaces.getEnlaceSimbolo(simbolo);
    }

    /**
     * Obtener Id del Estado
     *
     * @return Id del estado
     */
    public int getId() {
        return id;
    }

    public ListaArcos getEnlaces() {
        return enlaces;
    }

    public boolean isEstadofinal() {
        return this.fin;
    }

    public boolean isEstadoinicial() {
        return this.inicial;
    }

    /**
     * Establece un valor para el identificador del estado
     *
     * @param id Identificador del Estado
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Establece si el estado es Final
     *
     * @param estadofinal Boolean que establece si el estado es o no Final
     */
    public void setEstadofinal(boolean estadofinal) {
        this.fin = estadofinal;
    }

    /**
     * Establece si el estado es inicial
     *
     * @param estadoinicial Boolean que establece si el estado es o no Inicial
     */
    public void setEstadoinicial(boolean estadoinicial) {
        this.inicial = estadoinicial;
    }

    private int id;
    private ListaArcos enlaces;
    private boolean visitado, inicial, fin;
}
