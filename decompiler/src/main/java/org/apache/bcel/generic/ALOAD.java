
package org.apache.bcel.generic;

import org.apache.bcel.generic.base.LoadInstruction;
import org.apache.bcel.generic.base.Visitor;

public class ALOAD extends LoadInstruction {

    public ALOAD() {
        super(org.apache.bcel.Const.ALOAD, org.apache.bcel.Const.ALOAD_0);
    }

    public ALOAD(final int n) {
        super(org.apache.bcel.Const.ALOAD, org.apache.bcel.Const.ALOAD_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitALOAD(this);
    }
}
