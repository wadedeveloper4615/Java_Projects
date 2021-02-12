
package org.apache.bcel.verifier.exc;

public abstract class VerifierConstraintViolatedException extends RuntimeException {
    //
    // String name_of_offending_class;

    private static final long serialVersionUID = 2946136970490179465L;

    private String detailMessage;

    VerifierConstraintViolatedException() {
        super();
    }

    VerifierConstraintViolatedException(final String message) {
        super(message); // Not that important
        detailMessage = message;
    }

    VerifierConstraintViolatedException(final String message, final Throwable initCause) {
        super(message, initCause);
        detailMessage = message;
    }

    public void extendMessage(String pre, String post) {
        if (pre == null) {
            pre = "";
        }
        if (detailMessage == null) {
            detailMessage = "";
        }
        if (post == null) {
            post = "";
        }
        detailMessage = pre + detailMessage + post;
    }

    @Override
    public String getMessage() {
        return detailMessage;
    }
}
