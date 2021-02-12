
package org.apache.bcel.generic.base;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Const;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.generic.ALOAD;
import org.apache.bcel.generic.ANEWARRAY;
import org.apache.bcel.generic.ASTORE;
import org.apache.bcel.generic.BIPUSH;
import org.apache.bcel.generic.BREAKPOINT;
import org.apache.bcel.generic.CHECKCAST;
import org.apache.bcel.generic.DLOAD;
import org.apache.bcel.generic.DSTORE;
import org.apache.bcel.generic.FLOAD;
import org.apache.bcel.generic.FSTORE;
import org.apache.bcel.generic.GETFIELD;
import org.apache.bcel.generic.GETSTATIC;
import org.apache.bcel.generic.GOTO;
import org.apache.bcel.generic.GOTO_W;
import org.apache.bcel.generic.IFEQ;
import org.apache.bcel.generic.IFGE;
import org.apache.bcel.generic.IFGT;
import org.apache.bcel.generic.IFLE;
import org.apache.bcel.generic.IFLT;
import org.apache.bcel.generic.IFNE;
import org.apache.bcel.generic.IFNONNULL;
import org.apache.bcel.generic.IFNULL;
import org.apache.bcel.generic.IF_ACMPEQ;
import org.apache.bcel.generic.IF_ACMPNE;
import org.apache.bcel.generic.IF_ICMPEQ;
import org.apache.bcel.generic.IF_ICMPGE;
import org.apache.bcel.generic.IF_ICMPGT;
import org.apache.bcel.generic.IF_ICMPLE;
import org.apache.bcel.generic.IF_ICMPLT;
import org.apache.bcel.generic.IF_ICMPNE;
import org.apache.bcel.generic.IINC;
import org.apache.bcel.generic.ILOAD;
import org.apache.bcel.generic.IMPDEP1;
import org.apache.bcel.generic.IMPDEP2;
import org.apache.bcel.generic.INSTANCEOF;
import org.apache.bcel.generic.INVOKEDYNAMIC;
import org.apache.bcel.generic.INVOKEINTERFACE;
import org.apache.bcel.generic.INVOKESPECIAL;
import org.apache.bcel.generic.INVOKESTATIC;
import org.apache.bcel.generic.INVOKEVIRTUAL;
import org.apache.bcel.generic.ISTORE;
import org.apache.bcel.generic.JSR;
import org.apache.bcel.generic.JSR_W;
import org.apache.bcel.generic.LDC;
import org.apache.bcel.generic.LDC2_W;
import org.apache.bcel.generic.LDC_W;
import org.apache.bcel.generic.LLOAD;
import org.apache.bcel.generic.LOOKUPSWITCH;
import org.apache.bcel.generic.LSTORE;
import org.apache.bcel.generic.MULTIANEWARRAY;
import org.apache.bcel.generic.NEW;
import org.apache.bcel.generic.NEWARRAY;
import org.apache.bcel.generic.PUTFIELD;
import org.apache.bcel.generic.PUTSTATIC;
import org.apache.bcel.generic.RET;
import org.apache.bcel.generic.SIPUSH;
import org.apache.bcel.generic.TABLESWITCH;
import org.apache.bcel.generic.control.InstructionComparator;
import org.apache.bcel.generic.control.InstructionConst;
import org.apache.bcel.generic.gen.ConstantPoolGen;
import org.apache.bcel.util.ByteSequence;

public abstract class Instruction implements Cloneable {
    private static InstructionComparator cmp = InstructionComparator.DEFAULT;
    protected short length = 1; // Length of instruction in bytes
    protected short opcode = -1; // Opcode number

    public Instruction() {
    }

    public Instruction(final short opcode, final short length) {
        this.length = length;
        this.opcode = opcode;
    }

    public abstract void accept(Visitor v);

    public int consumeStack(final ConstantPoolGen cpg) {
        return Const.getConsumeStack(opcode);
    }

    public Instruction copy() {
        Instruction i = null;
        // "Constant" instruction, no need to duplicate
        if (InstructionConst.getInstruction(this.getOpcode()) != null) {
            i = this;
        } else {
            try {
                i = (Instruction) clone();
            } catch (final CloneNotSupportedException e) {
                System.err.println(e);
            }
        }
        return i;
    }

    public void dispose() {
    }

    public void dump(final DataOutputStream out) throws IOException {
        out.writeByte(opcode); // Common for all instructions
    }

    @Override
    public boolean equals(final Object that) {
        return (that instanceof Instruction) ? cmp.equals(this, (Instruction) that) : false;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return Const.getOpcodeName(opcode);
    }

    public short getOpcode() {
        return opcode;
    }

    @Override
    public int hashCode() {
        return opcode;
    }

    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
    }

    public int produceStack(final ConstantPoolGen cpg) {
        return Const.getProduceStack(opcode);
    }

    protected final void setLength(final int length) {
        this.length = (short) length; // TODO check range?
    }

    protected final void setOpcode(final short opcode) {
        this.opcode = opcode;
    }

    @Override
    public String toString() {
        return toString(true);
    }

    public String toString(final boolean verbose) {
        if (verbose) {
            return getName() + "[" + opcode + "](" + length + ")";
        }
        return getName();
    }

    public String toString(final ConstantPool cp) {
        return toString(false);
    }

    @Deprecated
    public static InstructionComparator getComparator() {
        return cmp;
    }

    public static boolean isValidByte(final int value) {
        return value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE;
    }

    public static boolean isValidShort(final int value) {
        return value >= Short.MIN_VALUE && value <= Short.MAX_VALUE;
    }

    // @since 6.0 no longer final
    public static Instruction readInstruction(final ByteSequence bytes) throws IOException {
        boolean wide = false;
        short opcode = (short) bytes.readUnsignedByte();
        Instruction obj = null;
        if (opcode == Const.WIDE) { // Read next opcode after wide byte
            wide = true;
            opcode = (short) bytes.readUnsignedByte();
        }
        final Instruction instruction = InstructionConst.getInstruction(opcode);
        if (instruction != null) {
            return instruction; // Used predefined immutable object, if available
        }

        obj = switch (opcode) {
            case Const.BIPUSH -> new BIPUSH();
            case Const.SIPUSH -> new SIPUSH();
            case Const.LDC -> new LDC();
            case Const.LDC_W -> new LDC_W();
            case Const.LDC2_W -> new LDC2_W();
            case Const.ILOAD -> new ILOAD();
            case Const.LLOAD -> new LLOAD();
            case Const.FLOAD -> new FLOAD();
            case Const.DLOAD -> new DLOAD();
            case Const.ALOAD -> new ALOAD();
            case Const.ILOAD_0 -> new ILOAD(0);
            case Const.ILOAD_1 -> new ILOAD(1);
            case Const.ILOAD_2 -> new ILOAD(2);
            case Const.ILOAD_3 -> new ILOAD(3);
            case Const.LLOAD_0 -> new LLOAD(0);
            case Const.LLOAD_1 -> new LLOAD(1);
            case Const.LLOAD_2 -> new LLOAD(2);
            case Const.LLOAD_3 -> new LLOAD(3);
            case Const.FLOAD_0 -> new FLOAD(0);
            case Const.FLOAD_1 -> new FLOAD(1);
            case Const.FLOAD_2 -> new FLOAD(2);
            case Const.FLOAD_3 -> new FLOAD(3);
            case Const.DLOAD_0 -> new DLOAD(0);
            case Const.DLOAD_1 -> new DLOAD(1);
            case Const.DLOAD_2 -> new DLOAD(2);
            case Const.DLOAD_3 -> new DLOAD(3);
            case Const.ALOAD_0 -> new ALOAD(0);
            case Const.ALOAD_1 -> new ALOAD(1);
            case Const.ALOAD_2 -> new ALOAD(2);
            case Const.ALOAD_3 -> new ALOAD(3);
            case Const.ISTORE -> new ISTORE();
            case Const.LSTORE -> new LSTORE();
            case Const.FSTORE -> new FSTORE();
            case Const.DSTORE -> new DSTORE();
            case Const.ASTORE -> new ASTORE();
            case Const.ISTORE_0 -> new ISTORE(0);
            case Const.ISTORE_1 -> new ISTORE(1);
            case Const.ISTORE_2 -> new ISTORE(2);
            case Const.ISTORE_3 -> new ISTORE(3);
            case Const.LSTORE_0 -> new LSTORE(0);
            case Const.LSTORE_1 -> new LSTORE(1);
            case Const.LSTORE_2 -> new LSTORE(2);
            case Const.LSTORE_3 -> new LSTORE(3);
            case Const.FSTORE_0 -> new FSTORE(0);
            case Const.FSTORE_1 -> new FSTORE(1);
            case Const.FSTORE_2 -> new FSTORE(2);
            case Const.FSTORE_3 -> new FSTORE(3);
            case Const.DSTORE_0 -> new DSTORE(0);
            case Const.DSTORE_1 -> new DSTORE(1);
            case Const.DSTORE_2 -> new DSTORE(2);
            case Const.DSTORE_3 -> new DSTORE(3);
            case Const.ASTORE_0 -> new ASTORE(0);
            case Const.ASTORE_1 -> new ASTORE(1);
            case Const.ASTORE_2 -> new ASTORE(2);
            case Const.ASTORE_3 -> new ASTORE(3);
            case Const.IINC -> new IINC();
            case Const.IFEQ -> new IFEQ();
            case Const.IFNE -> new IFNE();
            case Const.IFLT -> new IFLT();
            case Const.IFGE -> new IFGE();
            case Const.IFGT -> new IFGT();
            case Const.IFLE -> new IFLE();
            case Const.IF_ICMPEQ -> new IF_ICMPEQ();
            case Const.IF_ICMPNE -> new IF_ICMPNE();
            case Const.IF_ICMPLT -> new IF_ICMPLT();
            case Const.IF_ICMPGE -> new IF_ICMPGE();
            case Const.IF_ICMPGT -> new IF_ICMPGT();
            case Const.IF_ICMPLE -> new IF_ICMPLE();
            case Const.IF_ACMPEQ -> new IF_ACMPEQ();
            case Const.IF_ACMPNE -> new IF_ACMPNE();
            case Const.GOTO -> new GOTO();
            case Const.JSR -> new JSR();
            case Const.RET -> new RET();
            case Const.TABLESWITCH -> new TABLESWITCH();
            case Const.LOOKUPSWITCH -> new LOOKUPSWITCH();
            case Const.GETSTATIC -> new GETSTATIC();
            case Const.PUTSTATIC -> new PUTSTATIC();
            case Const.GETFIELD -> new GETFIELD();
            case Const.PUTFIELD -> new PUTFIELD();
            case Const.INVOKEVIRTUAL -> new INVOKEVIRTUAL();
            case Const.INVOKESPECIAL -> new INVOKESPECIAL();
            case Const.INVOKESTATIC -> new INVOKESTATIC();
            case Const.INVOKEINTERFACE -> new INVOKEINTERFACE();
            case Const.INVOKEDYNAMIC -> new INVOKEDYNAMIC();
            case Const.NEW -> new NEW();
            case Const.NEWARRAY -> new NEWARRAY();
            case Const.ANEWARRAY -> new ANEWARRAY();
            case Const.CHECKCAST -> new CHECKCAST();
            case Const.INSTANCEOF -> new INSTANCEOF();
            case Const.MULTIANEWARRAY -> new MULTIANEWARRAY();
            case Const.IFNULL -> new IFNULL();
            case Const.IFNONNULL -> new IFNONNULL();
            case Const.GOTO_W -> new GOTO_W();
            case Const.JSR_W -> new JSR_W();
            case Const.BREAKPOINT -> new BREAKPOINT();
            case Const.IMPDEP1 -> new IMPDEP1();
            case Const.IMPDEP2 -> new IMPDEP2();
            default -> throw new ClassGenException("Illegal opcode detected: " + opcode);
        };

        if (wide && !((obj instanceof LocalVariableInstruction) || (obj instanceof IINC) || (obj instanceof RET))) {
            throw new ClassGenException("Illegal opcode after wide: " + opcode);
        }
        obj.setOpcode(opcode);
        obj.initFromFile(bytes, wide); // Do further initializations, if any
        return obj;
    }

    @Deprecated
    public static void setComparator(final InstructionComparator c) {
        cmp = c;
    }
}
