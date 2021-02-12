package org.apache.bcel.verifier.statics;

import org.apache.bcel.Repository;
import org.apache.bcel.classfile.ClassFormatException;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.verifier.PassVerifier;
import org.apache.bcel.verifier.VerificationResult;
import org.apache.bcel.verifier.Verifier;
import org.apache.bcel.verifier.exc.LoadingException;
import org.apache.bcel.verifier.exc.Utility;

public final class Pass1Verifier extends PassVerifier {
    private JavaClass jc;
    private final Verifier myOwner;

    public Pass1Verifier(final Verifier owner) {
        myOwner = owner;
    }

    @Override
    public VerificationResult do_verify() {
        JavaClass jc;
        try {
            jc = getJavaClass(); // loads in the class file if not already done.

            if (jc != null) {

                if (!myOwner.getClassName().equals(jc.getClassName())) {
                    // This should maybe caught by BCEL: In case of renamed .class files we get
                    // wrong
                    // JavaClass objects here.
                    throw new LoadingException("Wrong name: the internal name of the .class file '" + jc.getClassName() + "' does not match the file's name '" + myOwner.getClassName() + "'.");
                }
            }

        } catch (final LoadingException e) {
            return new VerificationResult(VerificationResult.VERIFIED_REJECTED, e.getMessage());
        } catch (final ClassFormatException e) {
            return new VerificationResult(VerificationResult.VERIFIED_REJECTED, e.getMessage());
        } catch (final RuntimeException e) {
            // BCEL does not catch every possible RuntimeException; e.g. if
            // a constant pool index is referenced that does not exist.
            return new VerificationResult(VerificationResult.VERIFIED_REJECTED, "Parsing via BCEL did not succeed. " + e.getClass().getName() + " occured:\n" + Utility.getStackTrace(e));
        }

        if (jc != null) {
            return VerificationResult.VR_OK;
        }
        // TODO: Maybe change Repository's behavior to throw a LoadingException instead
        // of just returning "null"
        // if a class file cannot be found or in another way be looked up.
        return new VerificationResult(VerificationResult.VERIFIED_REJECTED, "Repository.lookup() failed. FILE NOT FOUND?");
    }

    private JavaClass getJavaClass() {
        if (jc == null) {
            try {
                jc = Repository.lookupClass(myOwner.getClassName().getName());
            } catch (final ClassNotFoundException e) {
            }
        }
        return jc;
    }

    @Override
    public String[] getMessages() {
        // This method is only here to override the javadoc-comment.
        return super.getMessages();
    }

}
