
package org.apache.bcel.verifier.statics;

import org.apache.bcel.Const;
import org.apache.bcel.generic.Type;

public final class LONG_Upper extends Type {

    private static final LONG_Upper singleton = new LONG_Upper();

    private LONG_Upper() {
        super(Const.T_UNKNOWN, "Long_Upper");
    }

    public static LONG_Upper theInstance() {
        return singleton;
    }
}
