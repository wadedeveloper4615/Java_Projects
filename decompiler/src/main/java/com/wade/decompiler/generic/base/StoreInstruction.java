package com.wade.decompiler.generic.base;

import com.wade.decompiler.generic.gen.Visitor;

public abstract class StoreInstruction extends LocalVariableInstruction implements PopInstruction {
    public StoreInstruction(final short canon_tag, final short c_tag) {
        super(canon_tag, c_tag);
    }

    public StoreInstruction(final short opcode, final short c_tag, final int n) {
        super(opcode, c_tag, n);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitPopInstruction(this);
        v.visitTypedInstruction(this);
        v.visitLocalVariableInstruction(this);
        v.visitStoreInstruction(this);
    }
}
