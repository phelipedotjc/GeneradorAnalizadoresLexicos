/*
 * 
 * @author Grupo 7 - Lenguajes UNAL
 */
package generadoranalizadoreslexicos;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.Analizador;
import model.Automata.TipoAutomata;
import model.Minimizacion;
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
            for (String error : traductor.getErrMsg()) {
                System.err.println("ERROR: " + error);
            }
        } else {
            afn = traductor.traducir();
            errors = traductor.isError();
            if (errors) {
                for (String error : traductor.getErrMsg()) {
                    System.err.println("ERROR: " + error);
                }
            } else {
                System.out.println("INFO: AFN Generado con éxito");
                System.out.println(afn.imprimir());
            }
        }
        return afn;
    }

    //Generacion del AFD
    public Thompson generateAFD(Thompson afn) {
        Thompson afd = null;
        boolean errors;
        Subconjunto subconjunto;
        TransitionMatrix transMatriz;
        try {

            subconjunto = new Subconjunto(afn);
            transMatriz = subconjunto.ejecutar();
            afd = transMatriz.convertAutomata();
            afd = Subconjunto.eliminar_estados_inalcanzables(afd);
            afd.setAlpha(afn.getAlphabet());
            afd.setRegex(afn.getRegex());
            afd.tipoAutomata = TipoAutomata.AFD;

            System.out.println(afd.imprimir());

        } catch (AutomataException ex) {
            errors = true;
        } catch (Exception ex) {
            errors = true;
        }

        return afd;
    }

    //Generacion del AFD
    public Thompson generateAFDmin(Thompson afd) {
        Thompson afdmin = null;
        boolean errors;
        try {
            Minimizacion minimize = new Minimizacion(afd);
            afdmin = minimize.minimizar();
            afdmin.eliminarIslas();
            afdmin.setAlpha(afd.getAlphabet());
            afdmin.setRegex(afd.getRegex());
            afdmin.tipoAutomata = TipoAutomata.AFDMin;
        } catch (AutomataException ex) {
            errors = true;
        } catch (Exception ex) {
            errors = true;
        }
        return afdmin;
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
