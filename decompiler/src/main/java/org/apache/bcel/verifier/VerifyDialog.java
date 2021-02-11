
package org.apache.bcel.verifier;

import java.awt.Color;

import org.apache.bcel.Repository;
import org.apache.bcel.classfile.JavaClass;

public class VerifyDialog extends javax.swing.JDialog {

    private static final long serialVersionUID = -6374807677043142313L;

    private javax.swing.JPanel ivjJDialogContentPane = null;

    private javax.swing.JPanel ivjPass1Panel = null;

    private javax.swing.JPanel ivjPass2Panel = null;

    private javax.swing.JPanel ivjPass3Panel = null;

    private javax.swing.JButton ivjPass1Button = null;

    private javax.swing.JButton ivjPass2Button = null;

    private javax.swing.JButton ivjPass3Button = null;

    private final IvjEventHandler ivjEventHandler = new IvjEventHandler();

    private String class_name = "java.lang.Object";

    private static int classesToVerify;

    class IvjEventHandler implements java.awt.event.ActionListener {

        @Override
        public void actionPerformed(final java.awt.event.ActionEvent e) {
            if (e.getSource() == VerifyDialog.this.getPass1Button()) {
                connEtoC1(e);
            }
            if (e.getSource() == VerifyDialog.this.getPass2Button()) {
                connEtoC2(e);
            }
            if (e.getSource() == VerifyDialog.this.getPass3Button()) {
                connEtoC3(e);
            }
            if (e.getSource() == VerifyDialog.this.getFlushButton()) {
                connEtoC4(e);
            }
        }
    }

    private javax.swing.JButton ivjFlushButton = null;

    public VerifyDialog() {
        super();
        initialize();
    }

    public VerifyDialog(final java.awt.Dialog owner) {
        super(owner);
    }

    public VerifyDialog(final java.awt.Dialog owner, final String title) {
        super(owner, title);
    }

    public VerifyDialog(final java.awt.Dialog owner, final String title, final boolean modal) {
        super(owner, title, modal);
    }

    public VerifyDialog(final java.awt.Dialog owner, final boolean modal) {
        super(owner, modal);
    }

    public VerifyDialog(final java.awt.Frame owner) {
        super(owner);
    }

    public VerifyDialog(final java.awt.Frame owner, final String title) {
        super(owner, title);
    }

    public VerifyDialog(final java.awt.Frame owner, final String title, final boolean modal) {
        super(owner, title, modal);
    }

    public VerifyDialog(final java.awt.Frame owner, final boolean modal) {
        super(owner, modal);
    }

    public VerifyDialog(String fully_qualified_class_name) {
        super();
        final int dotclasspos = fully_qualified_class_name.lastIndexOf(".class");
        if (dotclasspos != -1) {
            fully_qualified_class_name = fully_qualified_class_name.substring(0, dotclasspos);
        }
        fully_qualified_class_name = fully_qualified_class_name.replace('/', '.');
        class_name = fully_qualified_class_name;
        initialize();
    }

    private void connEtoC1(final java.awt.event.ActionEvent arg1) {
        try {
            // user code begin {1}
            // user code end
            this.pass1Button_ActionPerformed(arg1);
            // user code begin {2}
            // user code end
        } catch (final java.lang.Throwable ivjExc) {
            // user code begin {3}
            // user code end
            handleException(ivjExc);
        }
    }

    private void connEtoC2(final java.awt.event.ActionEvent arg1) {
        try {
            // user code begin {1}
            // user code end
            this.pass2Button_ActionPerformed(arg1);
            // user code begin {2}
            // user code end
        } catch (final java.lang.Throwable ivjExc) {
            // user code begin {3}
            // user code end
            handleException(ivjExc);
        }
    }

    private void connEtoC3(final java.awt.event.ActionEvent arg1) {
        try {
            // user code begin {1}
            // user code end
            this.pass4Button_ActionPerformed(arg1);
            // user code begin {2}
            // user code end
        } catch (final java.lang.Throwable ivjExc) {
            // user code begin {3}
            // user code end
            handleException(ivjExc);
        }
    }

    private void connEtoC4(final java.awt.event.ActionEvent arg1) {
        try {
            // user code begin {1}
            // user code end
            this.flushButton_ActionPerformed(arg1);
            // user code begin {2}
            // user code end
        } catch (final java.lang.Throwable ivjExc) {
            // user code begin {3}
            // user code end
            handleException(ivjExc);
        }
    }

    public void flushButton_ActionPerformed(final java.awt.event.ActionEvent actionEvent) {
        VerifierFactory.getVerifier(class_name).flush();
        Repository.removeClass(class_name); // Make sure it will be reloaded.
        getPass1Panel().setBackground(Color.gray);
        getPass1Panel().repaint();
        getPass2Panel().setBackground(Color.gray);
        getPass2Panel().repaint();
        getPass3Panel().setBackground(Color.gray);
        getPass3Panel().repaint();
    }

    private javax.swing.JButton getFlushButton() {
        if (ivjFlushButton == null) {
            try {
                ivjFlushButton = new javax.swing.JButton();
                ivjFlushButton.setName("FlushButton");
                ivjFlushButton.setText("Flush: Forget old verification results");
                ivjFlushButton.setBackground(java.awt.SystemColor.controlHighlight);
                ivjFlushButton.setBounds(60, 215, 300, 30);
                ivjFlushButton.setForeground(java.awt.Color.red);
                ivjFlushButton.setActionCommand("FlushButton");
                // user code begin {1}
                // user code end
            } catch (final java.lang.Throwable ivjExc) {
                // user code begin {2}
                // user code end
                handleException(ivjExc);
            }
        }
        return ivjFlushButton;
    }

    private javax.swing.JPanel getJDialogContentPane() {
        if (ivjJDialogContentPane == null) {
            try {
                ivjJDialogContentPane = new javax.swing.JPanel();
                ivjJDialogContentPane.setName("JDialogContentPane");
                ivjJDialogContentPane.setLayout(null);
                getJDialogContentPane().add(getPass1Panel(), getPass1Panel().getName());
                getJDialogContentPane().add(getPass3Panel(), getPass3Panel().getName());
                getJDialogContentPane().add(getPass2Panel(), getPass2Panel().getName());
                getJDialogContentPane().add(getPass1Button(), getPass1Button().getName());
                getJDialogContentPane().add(getPass2Button(), getPass2Button().getName());
                getJDialogContentPane().add(getPass3Button(), getPass3Button().getName());
                getJDialogContentPane().add(getFlushButton(), getFlushButton().getName());
                // user code begin {1}
                // user code end
            } catch (final java.lang.Throwable ivjExc) {
                // user code begin {2}
                // user code end
                handleException(ivjExc);
            }
        }
        return ivjJDialogContentPane;
    }

    private javax.swing.JButton getPass1Button() {
        if (ivjPass1Button == null) {
            try {
                ivjPass1Button = new javax.swing.JButton();
                ivjPass1Button.setName("Pass1Button");
                ivjPass1Button.setText("Pass1: Verify binary layout of .class file");
                ivjPass1Button.setBackground(java.awt.SystemColor.controlHighlight);
                ivjPass1Button.setBounds(100, 40, 300, 30);
                ivjPass1Button.setActionCommand("Button1");
                // user code begin {1}
                // user code end
            } catch (final java.lang.Throwable ivjExc) {
                // user code begin {2}
                // user code end
                handleException(ivjExc);
            }
        }
        return ivjPass1Button;
    }

    private javax.swing.JPanel getPass1Panel() {
        if (ivjPass1Panel == null) {
            try {
                ivjPass1Panel = new javax.swing.JPanel();
                ivjPass1Panel.setName("Pass1Panel");
                ivjPass1Panel.setLayout(null);
                ivjPass1Panel.setBackground(java.awt.SystemColor.controlShadow);
                ivjPass1Panel.setBounds(30, 30, 50, 50);
                // user code begin {1}
                // user code end
            } catch (final java.lang.Throwable ivjExc) {
                // user code begin {2}
                // user code end
                handleException(ivjExc);
            }
        }
        return ivjPass1Panel;
    }

    private javax.swing.JButton getPass2Button() {
        if (ivjPass2Button == null) {
            try {
                ivjPass2Button = new javax.swing.JButton();
                ivjPass2Button.setName("Pass2Button");
                ivjPass2Button.setText("Pass 2: Verify static .class file constraints");
                ivjPass2Button.setBackground(java.awt.SystemColor.controlHighlight);
                ivjPass2Button.setBounds(100, 100, 300, 30);
                ivjPass2Button.setActionCommand("Button2");
                // user code begin {1}
                // user code end
            } catch (final java.lang.Throwable ivjExc) {
                // user code begin {2}
                // user code end
                handleException(ivjExc);
            }
        }
        return ivjPass2Button;
    }

    private javax.swing.JPanel getPass2Panel() {
        if (ivjPass2Panel == null) {
            try {
                ivjPass2Panel = new javax.swing.JPanel();
                ivjPass2Panel.setName("Pass2Panel");
                ivjPass2Panel.setLayout(null);
                ivjPass2Panel.setBackground(java.awt.SystemColor.controlShadow);
                ivjPass2Panel.setBounds(30, 90, 50, 50);
                // user code begin {1}
                // user code end
            } catch (final java.lang.Throwable ivjExc) {
                // user code begin {2}
                // user code end
                handleException(ivjExc);
            }
        }
        return ivjPass2Panel;
    }

    private javax.swing.JButton getPass3Button() {
        if (ivjPass3Button == null) {
            try {
                ivjPass3Button = new javax.swing.JButton();
                ivjPass3Button.setName("Pass3Button");
                ivjPass3Button.setText("Passes 3a+3b: Verify code arrays");
                ivjPass3Button.setBackground(java.awt.SystemColor.controlHighlight);
                ivjPass3Button.setBounds(100, 160, 300, 30);
                ivjPass3Button.setActionCommand("Button2");
                // user code begin {1}
                // user code end
            } catch (final java.lang.Throwable ivjExc) {
                // user code begin {2}
                // user code end
                handleException(ivjExc);
            }
        }
        return ivjPass3Button;
    }

    private javax.swing.JPanel getPass3Panel() {
        if (ivjPass3Panel == null) {
            try {
                ivjPass3Panel = new javax.swing.JPanel();
                ivjPass3Panel.setName("Pass3Panel");
                ivjPass3Panel.setLayout(null);
                ivjPass3Panel.setBackground(java.awt.SystemColor.controlShadow);
                ivjPass3Panel.setBounds(30, 150, 50, 50);
                // user code begin {1}
                // user code end
            } catch (final java.lang.Throwable ivjExc) {
                // user code begin {2}
                // user code end
                handleException(ivjExc);
            }
        }
        return ivjPass3Panel;
    }

    private void handleException(final java.lang.Throwable exception) {

        System.out.println("--------- UNCAUGHT EXCEPTION ---------");
        exception.printStackTrace(System.out);
        // manually added code
        if (exception instanceof ThreadDeath) {
            throw (ThreadDeath) exception;
        }
        if (exception instanceof VirtualMachineError) {
            throw (VirtualMachineError) exception;
        }
    }

    private void initConnections() throws java.lang.Exception {
        // user code begin {1}
        // user code end
        getPass1Button().addActionListener(ivjEventHandler);
        getPass2Button().addActionListener(ivjEventHandler);
        getPass3Button().addActionListener(ivjEventHandler);
        getFlushButton().addActionListener(ivjEventHandler);
    }

    private void initialize() {
        try {
            // user code begin {1}
            // user code end
            setName("VerifyDialog");
            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            setSize(430, 280);
            setVisible(true);
            setModal(true);
            setResizable(false);
            setContentPane(getJDialogContentPane());
            initConnections();
        } catch (final java.lang.Throwable ivjExc) {
            handleException(ivjExc);
        }
        // user code begin {2}
        setTitle("'" + class_name + "' verification - JustIce / BCEL");
        // user code end
    }

    public static void main(final java.lang.String[] args) {
        classesToVerify = args.length;
        for (final String arg : args) {
            try {
                VerifyDialog aVerifyDialog;
                aVerifyDialog = new VerifyDialog(arg);
                aVerifyDialog.setModal(true);
                aVerifyDialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(final java.awt.event.WindowEvent e) {
                        classesToVerify--;
                        if (classesToVerify == 0) {
                            System.exit(0);
                        }
                    }
                });
                aVerifyDialog.setVisible(true);
            } catch (final Throwable exception) {
                System.err.println("Exception occurred in main() of javax.swing.JDialog");
                exception.printStackTrace(System.out);
            }
        }
    }

    public void pass1Button_ActionPerformed(final java.awt.event.ActionEvent actionEvent) {
        final Verifier v = VerifierFactory.getVerifier(class_name);
        final VerificationResult vr = v.doPass1();
        if (vr.getStatus() == VerificationResult.VERIFIED_OK) {
            getPass1Panel().setBackground(Color.green);
            getPass1Panel().repaint();
        }
        if (vr.getStatus() == VerificationResult.VERIFIED_REJECTED) {
            getPass1Panel().setBackground(Color.red);
            getPass1Panel().repaint();
        }
    }

    public void pass2Button_ActionPerformed(final java.awt.event.ActionEvent actionEvent) {
        pass1Button_ActionPerformed(actionEvent);
        final Verifier v = VerifierFactory.getVerifier(class_name);
        final VerificationResult vr = v.doPass2();
        if (vr.getStatus() == VerificationResult.VERIFIED_OK) {
            getPass2Panel().setBackground(Color.green);
            getPass2Panel().repaint();
        }
        if (vr.getStatus() == VerificationResult.VERIFIED_NOTYET) {
            getPass2Panel().setBackground(Color.yellow);
            getPass2Panel().repaint();
        }
        if (vr.getStatus() == VerificationResult.VERIFIED_REJECTED) {
            getPass2Panel().setBackground(Color.red);
            getPass2Panel().repaint();
        }
    }

    public void pass4Button_ActionPerformed(final java.awt.event.ActionEvent actionEvent) {
        pass2Button_ActionPerformed(actionEvent);
        Color color = Color.green;
        final Verifier v = VerifierFactory.getVerifier(class_name);
        VerificationResult vr = v.doPass2();
        if (vr.getStatus() == VerificationResult.VERIFIED_OK) {
            JavaClass jc = null;
            try {
                jc = Repository.lookupClass(class_name);
                final int nr = jc.getMethods().length;
                for (int i = 0; i < nr; i++) {
                    vr = v.doPass3b(i);
                    if (vr.getStatus() != VerificationResult.VERIFIED_OK) {
                        color = Color.red;
                        break;
                    }
                }
            } catch (final ClassNotFoundException ex) {
                // FIXME: report the error
                ex.printStackTrace();
            }
        } else {
            color = Color.yellow;
        }
        getPass3Panel().setBackground(color);
        getPass3Panel().repaint();
    }
}
