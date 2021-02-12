
package org.apache.bcel.verifier;

import org.apache.bcel.Repository;
import org.apache.bcel.classfile.JavaClass;

public class TransitiveHull implements VerifierFactoryObserver {

    private int indent = 0;

    private TransitiveHull() {
    }

    @Override
    public void update(final String classname) {
        System.gc(); // avoid swapping if possible.
        for (int i = 0; i < indent; i++) {
            System.out.print(" ");
        }
        System.out.println(classname);
        indent += 1;
        final Verifier v = VerifierFactory.getVerifier(classname);
        VerificationResult vr;
        vr = v.doPass1();
        if (vr != VerificationResult.VR_OK) {
            System.out.println("Pass 1:\n" + vr);
        }
        vr = v.doPass2();
        if (vr != VerificationResult.VR_OK) {
            System.out.println("Pass 2:\n" + vr);
        }
        if (vr == VerificationResult.VR_OK) {
            try {
                final JavaClass jc = Repository.lookupClass(v.getClassName());
                for (int i = 0; i < jc.getMethods().length; i++) {
                    vr = v.doPass3a(i);
                    if (vr != VerificationResult.VR_OK) {
                        System.out.println(v.getClassName() + ", Pass 3a, method " + i + " ['" + jc.getMethods()[i] + "']:\n" + vr);
                    }
                    vr = v.doPass3b(i);
                    if (vr != VerificationResult.VR_OK) {
                        System.out.println(v.getClassName() + ", Pass 3b, method " + i + " ['" + jc.getMethods()[i] + "']:\n" + vr);
                    }
                }
            } catch (final ClassNotFoundException e) {
                System.err.println("Could not find class " + v.getClassName() + " in Repository");
            }
        }
        indent -= 1;
    }

    public static void main(final String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Need exactly one argument: The root class to verify.");
            System.exit(1);
        }
        final int dotclasspos = args[0].lastIndexOf(".class");
        if (dotclasspos != -1) {
            args[0] = args[0].substring(0, dotclasspos);
        }
        args[0] = args[0].replace('/', '.');
        final TransitiveHull th = new TransitiveHull();
        VerifierFactory.attach(th);
        VerifierFactory.getVerifier(args[0]); // the observer is called back and does the actual trick.
        VerifierFactory.detach(th);
    }
}
