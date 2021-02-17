package com.wade.decompiler.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.base.JsrInstruction;
import com.wade.decompiler.generic.base.VariableLengthInstruction;
import com.wade.decompiler.generic.gen.Visitor;

public class JSR extends JsrInstruction implements VariableLengthInstruction {
    public JSR() {
    }

    public JSR(final InstructionHandle target) {
        super(Const.JSR, target);
    }

    @Override
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitVariableLengthInstruction(this);
        v.visitBranchInstruction(this);
        v.visitJsrInstruction(this);
        v.visitJSR(this);
    }

    @Override
    public void dump(final DataOutputStream out) throws IOException {
        super.setIndex(getTargetOffset());
        if (super.getOpcode() == Const.JSR) {
            super.dump(out);
        } else { // JSR_W
            super.setIndex(getTargetOffset());
            out.writeByte(super.getOpcode());
            out.writeInt(super.getIndex());
        }
    }

    @Override
    protected int updatePosition(final int offset, final int max_offset) {
        final int i = getTargetOffset(); // Depending on old position value
        setPosition(getPosition() + offset); // Position may be shifted by preceding expansions
        if (Math.abs(i) >= (Short.MAX_VALUE - max_offset)) { // to large for short (estimate)
            super.setOpcode(Const.JSR_W);
            final short old_length = (short) super.getLength();
            super.setLength(5);
            return super.getLength() - old_length;
        }
        return 0;
    }
}
