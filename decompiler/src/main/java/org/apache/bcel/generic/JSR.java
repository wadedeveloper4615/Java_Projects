package org.apache.bcel.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.JsrInstruction;
import org.apache.bcel.generic.base.VariableLengthInstruction;
import org.apache.bcel.generic.control.InstructionHandle;

public class JSR extends JsrInstruction implements VariableLengthInstruction {
    public JSR() {
    }

    public JSR(final InstructionHandle target) {
        super(InstructionOpCodes.JSR, target);
    }
//    @Override
//    public void accept(final Visitor v) {
//        v.visitStackProducer(this);
//        v.visitVariableLengthInstruction(this);
//        v.visitBranchInstruction(this);
//        v.visitJsrInstruction(this);
//        v.visitJSR(this);
//    }

    @Override
    public void dump(final DataOutputStream out) throws IOException {
        super.setIndex(getTargetOffset());
        if (super.getOpcode() == InstructionOpCodes.JSR) {
            super.dump(out);
        } else {
            super.setIndex(getTargetOffset());
            out.writeByte(super.getOpcode().getOpcode());
            out.writeInt(super.getIndex());
        }
    }

    @Override
    protected int updatePosition(final int offset, final int max_offset) {
        final int i = getTargetOffset();
        setPosition(getPosition() + offset);
        if (Math.abs(i) >= (Short.MAX_VALUE - max_offset)) {
            super.setOpcode(InstructionOpCodes.JSR_W);
            final short old_length = (short) super.getLength();
            super.setLength(5);
            return super.getLength() - old_length;
        }
        return 0;
    }
}
