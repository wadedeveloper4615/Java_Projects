package com.wade.decompiler.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.base.JsrInstruction;
import com.wade.decompiler.generic.gen.Visitor;
import com.wade.decompiler.util.ByteSequence;

public class JSR_W extends JsrInstruction {
    public JSR_W() {
    }

    public JSR_W(final InstructionHandle target) {
        super(Const.JSR_W, target);
        super.setLength(5);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitBranchInstruction(this);
        v.visitJsrInstruction(this);
        v.visitJSR_W(this);
    }

    @Override
    public void dump(final DataOutputStream out) throws IOException {
        super.setIndex(getTargetOffset());
        out.writeByte(super.getOpcode());
        out.writeInt(super.getIndex());
    }

    @Override
    public void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        super.setIndex(bytes.readInt());
        super.setLength(5);
    }
}
