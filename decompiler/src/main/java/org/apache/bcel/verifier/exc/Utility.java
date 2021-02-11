
package org.apache.bcel.verifier.exc;

import java.io.PrintWriter;
import java.io.StringWriter;

public final class Utility {

    private Utility() {
    }

    public static String getStackTrace(final Throwable t) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }
}
