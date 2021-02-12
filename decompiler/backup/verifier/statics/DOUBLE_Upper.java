
package org.apache.bcel.verifier.statics;

import org.apache.bcel.Const;
import org.apache.bcel.generic.Type;

public final class DOUBLE_Upper extends Type {

    private static final DOUBLE_Upper singleton = new DOUBLE_Upper();

    private DOUBLE_Upper() {
        super(Const.T_UNKNOWN, "Double_Upper");
    }

    public static DOUBLE_Upper theInstance() {
        return singleton;
    }
}
