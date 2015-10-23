/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package generadoranalizadoreslexicos;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import view.MainFrame;

/**
 *
 * @author phelipedotjc
 */
public class GeneradorAnalizadoresLexicos {

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
        
        MainFrame mainFrame = new MainFrame();
        
        mainFrame.setVisible(true);
    }
}
