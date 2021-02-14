package org.apache.bcel.generic;

import java.io.IOException;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.JsrInstruction;
import org.apache.bcel.generic.control.InstructionHandle;
import org.apache.bcel.util.ByteSequence;

public class JSR_W extends JsrInstruction {
    public JSR_W() {
    }

    public JSR_W(final InstructionHandle target) {
        super(InstructionOpCodes.JSR_W, target);
        super.setLength(5);
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        v.visitStackProducer(this);
//        v.visitBranchInstruction(this);
//        v.visitJsrInstruction(this);
//        v.visitJSR_W(this);
//    }
//
//    @Override
//    public void dump(final DataOutputStream out) throws IOException {
//        super.setIndex(getTargetOffset());
//        out.writeByte(super.getOpcode().getOpcode());
//        out.writeInt(super.getIndex());
//    }

    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        super.setIndex(bytes.readInt());
        super.setLength(5);
    }
}
