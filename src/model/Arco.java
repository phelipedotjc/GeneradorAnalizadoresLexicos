package model;

/**
 * Representa los enlaces (transiciones) entre estados del automata
 *
 * @author Grupo 7 - Lenguajes UNAL
 */
public class Arco implements Comparable<Arco> {

    /**
     * Constructor de la transicion entre estados
     *
     * @param origen Estado de origen del enlace.
     * @param destino Estado de destino del enlace.
     * @param label Etiqueta del enlace
     */
    public Arco(Estado origen, Estado destino, String label) {
        this.origen = origen;
        this.destino = destino;
        this.etiqueta = label;

        this.vacio = label.compareTo("") == 0;
    }

    /**
     * Verifica si dos arcos son iguales o no
     * 
     * @param e Estado con el cual queremos comparar el actual
     * @return
     * <ul> 
     * <li> <b>0 (Cero)</b> si son iguales </li>
     * <li> <b>-1 (Menos Uno)</b> si son <b>distintos</b> </li>
     * </ul>
     */
    @Override
    public int compareTo(Arco e) {
        if (e.getOrigen() == this.getOrigen()
                && e.getDestino() == this.getDestino()
                && e.getEtiqueta().equals(this.getEtiqueta())) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * Obtiene el estado de Origen del arco
     * 
     * @return Estado Origen
     */
    public Estado getOrigen() {
        return origen;
    }

    /**
     * Establece el estado de origen del arco
     * 
     * @param origen Estado de origen
     */
    public void setOrigen(Estado origen) {
        this.origen = origen;
    }

    /**
     * Obtiene el estado de destino del arco
     * 
     * @return Estado de Destino
     */
    public Estado getDestino() {
        return destino;
    }

    /**
     * Estblece el estado de destino del arco
     * 
     * @param destino  Estado de Destino
     */
    public void setDestino(Estado destino) {
        this.destino = destino;
    }

    /**
     * Obtiene la etiqeuta que marca el arco
     * 
     * @return Etiqueta
     */
    public String getEtiqueta() {
        return this.etiqueta;
    }

    /**
     * Detrmina si es un arco vacio ("λ" o "ε")
     * 
     * @return retorna "true" si es un arco vacio y "false" si no
     */
    public boolean isVacio() {
        return vacio;
    }

    private Estado origen;
    private Estado destino;
    private String etiqueta;
    private boolean vacio;
}
