
package org.apache.bcel.verifier;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.UIManager;

import org.apache.bcel.generic.Type;

public class GraphicalVerifier {

    private final boolean packFrame = false;

    public GraphicalVerifier() {
        final VerifierAppFrame frame = new VerifierAppFrame();
        // Frames �berpr�fen, die voreingestellte Gr��e haben
        // Frames packen, die nutzbare bevorzugte Gr��eninformationen enthalten,
        // z.B. aus ihrem Layout
        if (packFrame) {
            frame.pack();
        } else {
            frame.validate();
        }
        // Das Fenster zentrieren
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        frame.setVisible(true);
        frame.getClassNamesJList().setModel(new VerifierFactoryListModel());
        VerifierFactory.getVerifier(Type.OBJECT.getClassName()); // Fill list with java.lang.Object
        frame.getClassNamesJList().setSelectedIndex(0); // default, will verify java.lang.Object
    }

    public static void main(final String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (final Exception e) {
            e.printStackTrace();
        }
        new GraphicalVerifier();
    }
}
