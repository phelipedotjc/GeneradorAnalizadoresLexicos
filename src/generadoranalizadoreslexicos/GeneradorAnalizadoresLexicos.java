/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package generadoranalizadoreslexicos;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.Analizador;
import model.Automata.TipoAutomata;
import model.Subconjunto;
import model.Thompson;
import model.TransitionMatrix;
import model.exeptions.AutomataException;
import view.MainFrame;

/**
 *
 * @author phelipedotjc
 */
public class GeneradorAnalizadoresLexicos {

    private static GeneradorAnalizadoresLexicos INSTANCE = new GeneradorAnalizadoresLexicos();

    private Thompson AFN;
    private Thompson AFD;

    private GeneradorAnalizadoresLexicos() {
    }

    public static GeneradorAnalizadoresLexicos getInstance() {
        return INSTANCE;
    }

    private void init() {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }

    //Generacion del AFN
    public Thompson generateAFN(String regex, String alpha) {
        Thompson afn = null;
        boolean errors;
        Analizador traductor = new Analizador(regex, alpha);
        errors = traductor.isError();

        if (errors) {
            System.err.println("ERROR: " + traductor.getErrMsg());
        } else {
            afn = traductor.traducir();
            errors = traductor.isError();

            if (errors) {
                System.err.println("ERROR: " + traductor.getErrMsg());
            } else {
                System.out.println("INFO: AFN Generado con éxito");
                System.out.println(afn.imprimir());
            }
        }
        this.AFN = afn;
        return afn;
    }

    //Generacion del AFD
    public Thompson generateAFD() {
        Thompson afd = null;
        boolean errors;

        Subconjunto subconjunto;
        TransitionMatrix transMatriz;

        try {

            subconjunto = new Subconjunto(this.AFN);
            transMatriz = subconjunto.ejecutar();
            afd = transMatriz.convertAutomata();
            afd = Subconjunto.eliminar_estados_inalcanzables(afd);
            afd.setAlpha(this.AFN.getAlphabet());
            afd.setRegex(this.AFN.getRegex());
            afd.tipoAutomata = TipoAutomata.AFD;

            System.out.println(afd.imprimir());

        } catch (AutomataException ex) {
            errors = true;
        } catch (Exception ex) {
            errors = true;
        }

        this.AFD = afd;
        return afd;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GeneradorAnalizadoresLexicos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(GeneradorAnalizadoresLexicos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GeneradorAnalizadoresLexicos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GeneradorAnalizadoresLexicos.class.getName()).log(Level.SEVERE, null, ex);
        }

        GeneradorAnalizadoresLexicos.getInstance().init();

    }
}
