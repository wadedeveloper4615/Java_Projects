package org.apache.bcel.generic.base;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.enums.InstructionOpCodes;
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
//import org.apache.bcel.generic.gen.ConstantPoolGen;
import org.apache.bcel.util.ByteSequence;

public abstract class Instruction implements Cloneable {
    private static InstructionComparator cmp = InstructionComparator.DEFAULT;
    protected short length = 1;
    protected InstructionOpCodes opcode = null;

    public Instruction() {
    }

    public Instruction(InstructionOpCodes opcode, final short length) {
        this.length = length;
        this.opcode = opcode;
    }
    // public abstract void accept(Visitor v);

    public int consumeStack() {// final ConstantPoolGen cpg) {
        return opcode.getConsumeStack();
    }

    public Instruction copy() {
        Instruction i = null;
        if (InstructionConst.getInstruction(this.getOpcode().getOpcode()) != null) {
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
        out.writeByte(opcode.getOpcode());
    }

    @Override
    public boolean equals(final Object that) {
        return (that instanceof Instruction) ? cmp.equals(this, (Instruction) that) : false;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return opcode.getName();
    }

    public InstructionOpCodes getOpcode() {
        return opcode;
    }

    @Override
    public int hashCode() {
        return opcode.getOpcode();
    }

    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
    }

    public int produceStack() {// final ConstantPoolGen cpg) {
        return opcode.getProduceStack();
    }

    protected final void setLength(final int length) {
        this.length = (short) length;
    }

    protected final void setOpcode(InstructionOpCodes opcode) {
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

    public static Instruction readInstruction(final ByteSequence bytes) throws IOException {
        boolean wide = false;
        InstructionOpCodes opcode = InstructionOpCodes.read((short) bytes.readUnsignedByte());
        Instruction obj = null;
        if (opcode == InstructionOpCodes.WIDE) {
            wide = true;
            opcode = InstructionOpCodes.read((short) bytes.readUnsignedByte());
        }
        Instruction instruction = InstructionConst.getInstruction(opcode.getOpcode());
        if (instruction != null) {
            return instruction;
        }
        obj = switch (opcode) {
            case BIPUSH -> new BIPUSH();
            case SIPUSH -> new SIPUSH();
            case LDC -> new LDC();
            case LDC_W -> new LDC_W();
            case LDC2_W -> new LDC2_W();
            case ILOAD -> new ILOAD();
            case LLOAD -> new LLOAD();
            case FLOAD -> new FLOAD();
            case DLOAD -> new DLOAD();
            case ALOAD -> new ALOAD();
            case ILOAD_0 -> new ILOAD(0);
            case ILOAD_1 -> new ILOAD(1);
            case ILOAD_2 -> new ILOAD(2);
            case ILOAD_3 -> new ILOAD(3);
            case LLOAD_0 -> new LLOAD(0);
            case LLOAD_1 -> new LLOAD(1);
            case LLOAD_2 -> new LLOAD(2);
            case LLOAD_3 -> new LLOAD(3);
            case FLOAD_0 -> new FLOAD(0);
            case FLOAD_1 -> new FLOAD(1);
            case FLOAD_2 -> new FLOAD(2);
            case FLOAD_3 -> new FLOAD(3);
            case DLOAD_0 -> new DLOAD(0);
            case DLOAD_1 -> new DLOAD(1);
            case DLOAD_2 -> new DLOAD(2);
            case DLOAD_3 -> new DLOAD(3);
            case ALOAD_0 -> new ALOAD(0);
            case ALOAD_1 -> new ALOAD(1);
            case ALOAD_2 -> new ALOAD(2);
            case ALOAD_3 -> new ALOAD(3);
            case ISTORE -> new ISTORE();
            case LSTORE -> new LSTORE();
            case FSTORE -> new FSTORE();
            case DSTORE -> new DSTORE();
            case ASTORE -> new ASTORE();
            case ISTORE_0 -> new ISTORE(0);
            case ISTORE_1 -> new ISTORE(1);
            case ISTORE_2 -> new ISTORE(2);
            case ISTORE_3 -> new ISTORE(3);
            case LSTORE_0 -> new LSTORE(0);
            case LSTORE_1 -> new LSTORE(1);
            case LSTORE_2 -> new LSTORE(2);
            case LSTORE_3 -> new LSTORE(3);
            case FSTORE_0 -> new FSTORE(0);
            case FSTORE_1 -> new FSTORE(1);
            case FSTORE_2 -> new FSTORE(2);
            case FSTORE_3 -> new FSTORE(3);
            case DSTORE_0 -> new DSTORE(0);
            case DSTORE_1 -> new DSTORE(1);
            case DSTORE_2 -> new DSTORE(2);
            case DSTORE_3 -> new DSTORE(3);
            case ASTORE_0 -> new ASTORE(0);
            case ASTORE_1 -> new ASTORE(1);
            case ASTORE_2 -> new ASTORE(2);
            case ASTORE_3 -> new ASTORE(3);
            case IINC -> new IINC();
            case IFEQ -> new IFEQ();
            case IFNE -> new IFNE();
            case IFLT -> new IFLT();
            case IFGE -> new IFGE();
            case IFGT -> new IFGT();
            case IFLE -> new IFLE();
            case IF_ICMPEQ -> new IF_ICMPEQ();
            case IF_ICMPNE -> new IF_ICMPNE();
            case IF_ICMPLT -> new IF_ICMPLT();
            case IF_ICMPGE -> new IF_ICMPGE();
            case IF_ICMPGT -> new IF_ICMPGT();
            case IF_ICMPLE -> new IF_ICMPLE();
            case IF_ACMPEQ -> new IF_ACMPEQ();
            case IF_ACMPNE -> new IF_ACMPNE();
            case GOTO -> new GOTO();
            case JSR -> new JSR();
            case RET -> new RET();
            case TABLESWITCH -> new TABLESWITCH();
            case LOOKUPSWITCH -> new LOOKUPSWITCH();
            case GETSTATIC -> new GETSTATIC();
            case PUTSTATIC -> new PUTSTATIC();
            case GETFIELD -> new GETFIELD();
            case PUTFIELD -> new PUTFIELD();
            case INVOKEVIRTUAL -> new INVOKEVIRTUAL();
            case INVOKESPECIAL -> new INVOKESPECIAL();
            case INVOKESTATIC -> new INVOKESTATIC();
            case INVOKEINTERFACE -> new INVOKEINTERFACE();
            case INVOKEDYNAMIC -> new INVOKEDYNAMIC();
            case NEW -> new NEW();
            case NEWARRAY -> new NEWARRAY();
            case ANEWARRAY -> new ANEWARRAY();
            case CHECKCAST -> new CHECKCAST();
            case INSTANCEOF -> new INSTANCEOF();
            case MULTIANEWARRAY -> new MULTIANEWARRAY();
            case IFNULL -> new IFNULL();
            case IFNONNULL -> new IFNONNULL();
            case GOTO_W -> new GOTO_W();
            case JSR_W -> new JSR_W();
            case BREAKPOINT -> new BREAKPOINT();
            case IMPDEP1 -> new IMPDEP1();
            case IMPDEP2 -> new IMPDEP2();
            default -> throw new ClassGenException("Illegal opcode detected: " + opcode);
        };
        if (wide && !((obj instanceof LocalVariableInstruction) || (obj instanceof IINC) || (obj instanceof RET))) {
            throw new ClassGenException("Illegal opcode after wide: " + opcode);
        }
        obj.setOpcode(opcode);
        obj.initFromFile(bytes, wide);
        return obj;
    }

    public static void setComparator(final InstructionComparator c) {
        cmp = c;
    }
}
