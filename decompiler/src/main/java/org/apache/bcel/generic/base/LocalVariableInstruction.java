package org.apache.bcel.generic.base;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Const;
import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.util.ByteSequence;

public abstract class LocalVariableInstruction extends Instruction implements TypedInstruction, IndexedInstruction {
    @Deprecated
    protected int n = -1;
    private InstructionOpCodes cTag = null;
    private InstructionOpCodes canonTag = null;

    protected LocalVariableInstruction() {
    }

    protected LocalVariableInstruction(InstructionOpCodes canon_tag, InstructionOpCodes c_tag) {
        super();
        this.canonTag = canon_tag;
        this.cTag = c_tag;
    }

    protected LocalVariableInstruction(InstructionOpCodes opcode, InstructionOpCodes cTag, final int n) {
        super(opcode, (short) 2);
        this.cTag = cTag;
        canonTag = opcode;
        // setIndex(n);
    }

    @Override
    public void dump(final DataOutputStream out) throws IOException {
        if (wide()) {
            out.writeByte(InstructionOpCodes.WIDE.getOpcode());
        }
        out.writeByte(super.getOpcode().getOpcode());
        if (super.getLength() > 1) {
            if (wide()) {
                out.writeShort(n);
            } else {
                out.writeByte(n);
            }
        }
    }

    public InstructionOpCodes getCanonicalTag() {
        return canonTag;
    }
//    @Override
//    public final int getIndex() {
//        return n;
//    }
//    @Override
//    public Type getType(final ConstantPoolGen cp) {
//        switch (canonTag) {
//            case ILOAD:
//            case ISTORE:
//                return Type.INT;
//            case LLOAD:
//            case LSTORE:
//                return Type.LONG;
//            case DLOAD:
//            case DSTORE:
//                return Type.DOUBLE;
//            case FLOAD:
//            case FSTORE:
//                return Type.FLOAT;
//            case ALOAD:
//            case ASTORE:
//                return Type.OBJECT;
//            default:
//                throw new ClassGenException("Unknown case in switch" + canonTag);
//        }
//    }

    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        if (wide) {
            n = bytes.readUnsignedShort();
            super.setLength(4);
        } else {
            InstructionOpCodes _opcode = super.getOpcode();
            if (((_opcode.getOpcode() >= InstructionOpCodes.ILOAD.getOpcode()) && (_opcode.getOpcode() <= InstructionOpCodes.ALOAD.getOpcode())) || ((_opcode.getOpcode() >= InstructionOpCodes.ISTORE.getOpcode()) && (_opcode.getOpcode() <= InstructionOpCodes.ASTORE.getOpcode()))) {
                n = bytes.readUnsignedByte();
                super.setLength(2);
            } else if (_opcode.getOpcode() <= InstructionOpCodes.ALOAD_3.getOpcode()) {
                n = (_opcode.getOpcode() - InstructionOpCodes.ILOAD_0.getOpcode()) % 4;
                super.setLength(1);
            } else {
                n = (_opcode.getOpcode() - InstructionOpCodes.ISTORE_0.getOpcode()) % 4;
                super.setLength(1);
            }
        }
    }
//    @Override
//    public void setIndex(final int n) {
//        if ((n < 0) || (n > Const.MAX_SHORT)) {
//            throw new ClassGenException("Illegal value: " + n);
//        }
//        this.n = n;
//        if (n <= 3) {
//            super.setOpcode(InstructionOpCodes.read((short) (cTag.getOpcode() + n)));
//            super.setLength(1);
//        } else {
//            super.setOpcode(canonTag);
//            if (wide()) {
//                super.setLength(4);
//            } else {
//                super.setLength(2);
//            }
//        }
//    }

    protected final void setIndexOnly(final int n) {
        this.n = n;
    }

    @Override
    public String toString(final boolean verbose) {
        final short _opcode = (short) super.getOpcode().getOpcode();
        if (((_opcode >= InstructionOpCodes.ILOAD_0.getOpcode()) && (_opcode <= InstructionOpCodes.ALOAD_3.getOpcode())) || ((_opcode >= InstructionOpCodes.ISTORE_0.getOpcode()) && (_opcode <= InstructionOpCodes.ASTORE_3.getOpcode()))) {
            return super.toString(verbose);
        }
        return super.toString(verbose) + " " + n;
    }

    private boolean wide() {
        return n > Const.MAX_BYTE;
    }
}
