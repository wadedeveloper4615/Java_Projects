
package org.apache.bcel.verifier;

import java.util.ArrayList;
import java.util.List;

public abstract class PassVerifier {

    private final List<String> messages = new ArrayList<>();

    private VerificationResult verificationResult = null;

    public void addMessage(final String message) {
        messages.add(message);
    }

    public abstract VerificationResult do_verify();

    public String[] getMessages() {
        verify(); // create messages if not already done (cached!)
        return messages.toArray(new String[messages.size()]);
    }

    public VerificationResult verify() {
        if (verificationResult == null) {
            verificationResult = do_verify();
        }
        return verificationResult;
    }
}
