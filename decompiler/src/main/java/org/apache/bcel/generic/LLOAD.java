
package org.apache.bcel.generic;

import org.apache.bcel.generic.base.LoadInstruction;
import org.apache.bcel.generic.base.Visitor;

public class LLOAD extends LoadInstruction {

    public LLOAD() {
        super(org.apache.bcel.Const.LLOAD, org.apache.bcel.Const.LLOAD_0);
    }

    public LLOAD(final int n) {
        super(org.apache.bcel.Const.LLOAD, org.apache.bcel.Const.LLOAD_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitLLOAD(this);
    }
}
