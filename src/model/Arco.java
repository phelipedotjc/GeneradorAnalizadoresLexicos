package model;

/**
 *
 * @author Grupo 7 - Lenguajes UNAL
 */
public class Arco implements Comparable<Arco> {

    /**
     *
     * @param origen Estado de origen del enlace.
     * @param destino Estado de destino del enlace.
     * @param label Etiqueta del Arco
     */
    public Arco(Estado origen, Estado destino, String label) {
        this.origen = origen;
        this.destino = destino;
        this.etiqueta = label;

        this.vacio = label.compareTo("") == 0;
    }

    /**
     * @param e Estado al cual queremos comparar el actual
     * @return <ul> <li><b>0 (Cero)</b> si son iguales</li>
     * <li><b>-1 (Menos Uno)</b> si son <b>distintos</b></li>
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

    public Estado getOrigen() {
        return origen;
    }

    public void setOrigen(Estado origen) {
        this.origen = origen;
    }

    public Estado getDestino() {
        return destino;
    }

    public void setDestino(Estado destino) {
        this.destino = destino;
    }

    public String getEtiqueta() {
        return this.etiqueta;
    }

    public boolean isVacio() {
        return vacio;
    }

    private Estado origen;
    private Estado destino;
    private String etiqueta;
    private boolean vacio;
}
