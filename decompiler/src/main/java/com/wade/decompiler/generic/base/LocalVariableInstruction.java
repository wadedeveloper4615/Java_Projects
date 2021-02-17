package com.wade.decompiler.generic.base;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.gen.ClassGenException;
import com.wade.decompiler.generic.gen.ConstantPoolGen;
import com.wade.decompiler.generic.type.Type;
import com.wade.decompiler.util.ByteSequence;

public abstract class LocalVariableInstruction extends Instruction implements TypedInstruction, IndexedInstruction {
    @Deprecated
    protected int n = -1; // index of referenced variable
    private InstructionOpCodes cTag = null; // compact version, such as ILOAD_0
    private InstructionOpCodes canonTag = null; // canonical tag such as ILOAD

    public LocalVariableInstruction() {
    }

    public LocalVariableInstruction(InstructionOpCodes canon_tag, InstructionOpCodes c_tag) {
        super();
        this.canonTag = canon_tag;
        this.cTag = c_tag;
    }

    protected LocalVariableInstruction(InstructionOpCodes opcode, InstructionOpCodes cTag, int n) {
        super(opcode, 2);
        this.cTag = cTag;
        canonTag = opcode;
        setIndex(n);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        if (wide()) {
            out.writeByte(Const.WIDE);
        }
        out.writeByte(super.getOpcode().getOpcode());
        if (super.getLength() > 1) { // Otherwise ILOAD_n, instruction, e.g.
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

    @Override
    public int getIndex() {
        return n;
    }

    @Override
    public Type getType(ConstantPoolGen cp) {
        switch (canonTag) {
            case ILOAD:
            case ISTORE:
                return Type.INT;
            case LLOAD:
            case LSTORE:
                return Type.LONG;
            case DLOAD:
            case DSTORE:
                return Type.DOUBLE;
            case FLOAD:
            case FSTORE:
                return Type.FLOAT;
            case ALOAD:
            case ASTORE:
                return Type.OBJECT;
            default:
                throw new ClassGenException("Unknown case in switch" + canonTag);
        }
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        if (wide) {
            n = bytes.readUnsignedShort();
            super.setLength(4);
        } else {
            short _opcode = (short) super.getOpcode().getOpcode();
            if (((_opcode >= Const.ILOAD) && (_opcode <= Const.ALOAD)) || ((_opcode >= Const.ISTORE) && (_opcode <= Const.ASTORE))) {
                n = bytes.readUnsignedByte();
                super.setLength(2);
            } else if (_opcode <= Const.ALOAD_3) { // compact load instruction such as ILOAD_2
                n = (_opcode - Const.ILOAD_0) % 4;
                super.setLength(1);
            } else { // Assert ISTORE_0 <= tag <= ASTORE_3
                n = (_opcode - Const.ISTORE_0) % 4;
                super.setLength(1);
            }
        }
    }

    @Override
    public void setIndex(int n) { // TODO could be package-protected?
        if ((n < 0) || (n > Const.MAX_SHORT)) {
            throw new ClassGenException("Illegal value: " + n);
        }
        this.n = n;
        // Cannot be < 0 as this is checked above
        if (n <= 3) { // Use more compact instruction xLOAD_n
            super.setOpcode(InstructionOpCodes.read((short) (cTag.getOpcode() + n)));
            super.setLength(1);
        } else {
            super.setOpcode(canonTag);
            if (wide()) {
                super.setLength(4);
            } else {
                super.setLength(2);
            }
        }
    }

    public void setIndexOnly(int n) {
        this.n = n;
    }

    @Override
    public String toString(boolean verbose) {
        short _opcode = (short) super.getOpcode().getOpcode();
        if (((_opcode >= Const.ILOAD_0) && (_opcode <= Const.ALOAD_3)) || ((_opcode >= Const.ISTORE_0) && (_opcode <= Const.ASTORE_3))) {
            return super.toString(verbose);
        }
        return super.toString(verbose) + " " + n;
    }

    private boolean wide() {
        return n > Const.MAX_BYTE;
    }
}
