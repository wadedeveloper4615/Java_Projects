
package org.apache.bcel.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.ExceptionConst;
import org.apache.bcel.generic.base.AllocationInstruction;
import org.apache.bcel.generic.base.ArrayType;
import org.apache.bcel.generic.base.ExceptionThrower;
import org.apache.bcel.generic.base.Instruction;
import org.apache.bcel.generic.base.Visitor;
import org.apache.bcel.util.ByteSequence;

public class NEWARRAY extends Instruction implements AllocationInstruction, ExceptionThrower, StackProducer {

    private byte type;

    public NEWARRAY() {
    }

    public NEWARRAY(final BasicType type) {
        this(type.getType());
    }

    public NEWARRAY(final byte type) {
        super(org.apache.bcel.Const.NEWARRAY, (short) 2);
        this.type = type;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitAllocationInstruction(this);
        v.visitExceptionThrower(this);
        v.visitStackProducer(this);
        v.visitNEWARRAY(this);
    }

    @Override
    public void dump(final DataOutputStream out) throws IOException {
        out.writeByte(super.getOpcode());
        out.writeByte(type);
    }

    @Override
    public Class<?>[] getExceptions() {
        return new Class[] { ExceptionConst.NEGATIVE_ARRAY_SIZE_EXCEPTION };
    }

    public final Type getType() {
        return new ArrayType(BasicType.getType(type), 1);
    }

    public final byte getTypecode() {
        return type;
    }

    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        type = bytes.readByte();
        super.setLength(2);
    }

    @Override
    public String toString(final boolean verbose) {
        return super.toString(verbose) + " " + org.apache.bcel.Const.getTypeName(type);
    }
}
