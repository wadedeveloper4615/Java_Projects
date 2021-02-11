
package org.apache.bcel.generic.base;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Const;
import org.apache.bcel.generic.Type;
import org.apache.bcel.generic.gen.ConstantPoolGen;
import org.apache.bcel.util.ByteSequence;

public abstract class LocalVariableInstruction extends Instruction implements TypedInstruction, IndexedInstruction {

    @Deprecated
    protected int n = -1; // index of referenced variable

    private short cTag = -1; // compact version, such as ILOAD_0
    private short canonTag = -1; // canonical tag such as ILOAD

    protected LocalVariableInstruction() {
    }

    protected LocalVariableInstruction(final short canon_tag, final short c_tag) {
        super();
        this.canonTag = canon_tag;
        this.cTag = c_tag;
    }

    protected LocalVariableInstruction(final short opcode, final short cTag, final int n) {
        super(opcode, (short) 2);
        this.cTag = cTag;
        canonTag = opcode;
        setIndex(n);
    }

    @Override
    public void dump(final DataOutputStream out) throws IOException {
        if (wide()) {
            out.writeByte(Const.WIDE);
        }
        out.writeByte(super.getOpcode());
        if (super.getLength() > 1) { // Otherwise ILOAD_n, instruction, e.g.
            if (wide()) {
                out.writeShort(n);
            } else {
                out.writeByte(n);
            }
        }
    }

    public short getCanonicalTag() {
        return canonTag;
    }

    @Override
    public final int getIndex() {
        return n;
    }

    @Override
    public Type getType(final ConstantPoolGen cp) {
        switch (canonTag) {
            case Const.ILOAD:
            case Const.ISTORE:
                return Type.INT;
            case Const.LLOAD:
            case Const.LSTORE:
                return Type.LONG;
            case Const.DLOAD:
            case Const.DSTORE:
                return Type.DOUBLE;
            case Const.FLOAD:
            case Const.FSTORE:
                return Type.FLOAT;
            case Const.ALOAD:
            case Const.ASTORE:
                return Type.OBJECT;
            default:
                throw new ClassGenException("Unknown case in switch" + canonTag);
        }
    }

    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        if (wide) {
            n = bytes.readUnsignedShort();
            super.setLength(4);
        } else {
            final short _opcode = super.getOpcode();
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
    public void setIndex(final int n) { // TODO could be package-protected?
        if ((n < 0) || (n > Const.MAX_SHORT)) {
            throw new ClassGenException("Illegal value: " + n);
        }
        this.n = n;
        // Cannot be < 0 as this is checked above
        if (n <= 3) { // Use more compact instruction xLOAD_n
            super.setOpcode((short) (cTag + n));
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

    protected final void setIndexOnly(final int n) {
        this.n = n;
    }

    @Override
    public String toString(final boolean verbose) {
        final short _opcode = super.getOpcode();
        if (((_opcode >= Const.ILOAD_0) && (_opcode <= Const.ALOAD_3)) || ((_opcode >= Const.ISTORE_0) && (_opcode <= Const.ASTORE_3))) {
            return super.toString(verbose);
        }
        return super.toString(verbose) + " " + n;
    }

    private boolean wide() {
        return n > Const.MAX_BYTE;
    }
}
