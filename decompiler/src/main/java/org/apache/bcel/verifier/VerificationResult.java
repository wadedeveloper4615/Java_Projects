
package org.apache.bcel.verifier;

public class VerificationResult {

    public static final int VERIFIED_NOTYET = 0;

    public static final int VERIFIED_OK = 1;

    public static final int VERIFIED_REJECTED = 2;

    private static final String VERIFIED_NOTYET_MSG = "Not yet verified.";

    private static final String VERIFIED_OK_MSG = "Passed verification.";

    public static final VerificationResult VR_NOTYET = new VerificationResult(VERIFIED_NOTYET, VERIFIED_NOTYET_MSG);

    public static final VerificationResult VR_OK = new VerificationResult(VERIFIED_OK, VERIFIED_OK_MSG);

    private final int numeric;

    private final String detailMessage;

    public VerificationResult(final int status, final String message) {
        numeric = status;
        detailMessage = message;
    }

    public int getStatus() {
        return numeric;
    }

    public String getMessage() {
        return detailMessage;
    }

    @Override
    public int hashCode() {
        return numeric ^ detailMessage.hashCode();
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof VerificationResult)) {
            return false;
        }
        final VerificationResult other = (VerificationResult) o;
        return (other.numeric == this.numeric) && other.detailMessage.equals(this.detailMessage);
    }

    @Override
    public String toString() {
        String ret = "";
        if (numeric == VERIFIED_NOTYET) {
            ret = "VERIFIED_NOTYET";
        }
        if (numeric == VERIFIED_OK) {
            ret = "VERIFIED_OK";
        }
        if (numeric == VERIFIED_REJECTED) {
            ret = "VERIFIED_REJECTED";
        }
        ret += "\n" + detailMessage + "\n";
        return ret;
    }
}
