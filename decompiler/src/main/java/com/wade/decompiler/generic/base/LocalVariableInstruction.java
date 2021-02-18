package com.wade.decompiler.generic.base;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.type.Type;
import com.wade.decompiler.util.ByteSequence;

public abstract class LocalVariableInstruction extends Instruction implements TypedInstruction, IndexedInstruction {
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
            out.writeByte(InstructionOpCodes.WIDE.getOpcode());
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
    public Type getType(ConstantPool cp) {
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
            if (((_opcode >= InstructionOpCodes.ILOAD.getOpcode()) && (_opcode <= InstructionOpCodes.ALOAD.getOpcode())) || ((_opcode >= InstructionOpCodes.ISTORE.getOpcode()) && (_opcode <= InstructionOpCodes.ASTORE.getOpcode()))) {
                n = bytes.readUnsignedByte();
                super.setLength(2);
            } else if (_opcode <= InstructionOpCodes.ALOAD_3.getOpcode()) { // compact load instruction such as ILOAD_2
                n = (_opcode - InstructionOpCodes.ILOAD_0.getOpcode()) % 4;
                super.setLength(1);
            } else { // Assert ISTORE_0 <= tag <= ASTORE_3
                n = (_opcode - InstructionOpCodes.ISTORE_0.getOpcode()) % 4;
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
        if (((_opcode >= InstructionOpCodes.ILOAD_0.getOpcode()) && (_opcode <= InstructionOpCodes.ALOAD_3.getOpcode())) || ((_opcode >= InstructionOpCodes.ISTORE_0.getOpcode()) && (_opcode <= InstructionOpCodes.ASTORE_3.getOpcode()))) {
            return super.toString(verbose);
        }
        return super.toString(verbose) + " " + n;
    }

    private boolean wide() {
        return n > Const.MAX_BYTE;
    }
}
