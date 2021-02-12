
package org.apache.bcel.verifier.exc;

import java.util.Arrays;

public final class AssertionViolatedException extends RuntimeException {
    private static final long serialVersionUID = -129822266349567409L;

    private String detailMessage;

    public AssertionViolatedException() {
        super();
    }

    public AssertionViolatedException(String message) {
        super(message = "INTERNAL ERROR: " + message); // Thanks to Java, the constructor call here must be first.
        detailMessage = message;
    }

    public AssertionViolatedException(String message, final Throwable initCause) {
        super(message = "INTERNAL ERROR: " + message, initCause);
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

    public static void main(final String[] args) {
        final AssertionViolatedException ave = new AssertionViolatedException(Arrays.toString(args));
        ave.extendMessage("\nFOUND:\n\t", "\nExiting!!\n");
        throw ave;
    }

}
