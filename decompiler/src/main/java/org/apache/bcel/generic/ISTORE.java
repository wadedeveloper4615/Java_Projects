
package org.apache.bcel.generic;

import org.apache.bcel.generic.base.StoreInstruction;
import org.apache.bcel.generic.base.Visitor;

public class ISTORE extends StoreInstruction {

    public ISTORE() {
        super(org.apache.bcel.Const.ISTORE, org.apache.bcel.Const.ISTORE_0);
    }

    public ISTORE(final int n) {
        super(org.apache.bcel.Const.ISTORE, org.apache.bcel.Const.ISTORE_0, n);
    }

    @Override
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitISTORE(this);
    }
}
