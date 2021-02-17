package com.wade.decompiler.generic.base;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.ALOAD;
import com.wade.decompiler.generic.ANEWARRAY;
import com.wade.decompiler.generic.ASTORE;
import com.wade.decompiler.generic.BIPUSH;
import com.wade.decompiler.generic.BREAKPOINT;
import com.wade.decompiler.generic.CHECKCAST;
import com.wade.decompiler.generic.DLOAD;
import com.wade.decompiler.generic.DSTORE;
import com.wade.decompiler.generic.FLOAD;
import com.wade.decompiler.generic.FSTORE;
import com.wade.decompiler.generic.GETFIELD;
import com.wade.decompiler.generic.GETSTATIC;
import com.wade.decompiler.generic.GOTO;
import com.wade.decompiler.generic.GOTO_W;
import com.wade.decompiler.generic.IFEQ;
import com.wade.decompiler.generic.IFGE;
import com.wade.decompiler.generic.IFGT;
import com.wade.decompiler.generic.IFLE;
import com.wade.decompiler.generic.IFLT;
import com.wade.decompiler.generic.IFNE;
import com.wade.decompiler.generic.IFNONNULL;
import com.wade.decompiler.generic.IFNULL;
import com.wade.decompiler.generic.IF_ACMPEQ;
import com.wade.decompiler.generic.IF_ACMPNE;
import com.wade.decompiler.generic.IF_ICMPEQ;
import com.wade.decompiler.generic.IF_ICMPGE;
import com.wade.decompiler.generic.IF_ICMPGT;
import com.wade.decompiler.generic.IF_ICMPLE;
import com.wade.decompiler.generic.IF_ICMPLT;
import com.wade.decompiler.generic.IF_ICMPNE;
import com.wade.decompiler.generic.IINC;
import com.wade.decompiler.generic.ILOAD;
import com.wade.decompiler.generic.IMPDEP1;
import com.wade.decompiler.generic.IMPDEP2;
import com.wade.decompiler.generic.INSTANCEOF;
import com.wade.decompiler.generic.INVOKEDYNAMIC;
import com.wade.decompiler.generic.INVOKEINTERFACE;
import com.wade.decompiler.generic.INVOKESPECIAL;
import com.wade.decompiler.generic.INVOKESTATIC;
import com.wade.decompiler.generic.INVOKEVIRTUAL;
import com.wade.decompiler.generic.ISTORE;
import com.wade.decompiler.generic.JSR;
import com.wade.decompiler.generic.JSR_W;
import com.wade.decompiler.generic.LDC;
import com.wade.decompiler.generic.LDC2_W;
import com.wade.decompiler.generic.LDC_W;
import com.wade.decompiler.generic.LLOAD;
import com.wade.decompiler.generic.LOOKUPSWITCH;
import com.wade.decompiler.generic.LSTORE;
import com.wade.decompiler.generic.MULTIANEWARRAY;
import com.wade.decompiler.generic.NEW;
import com.wade.decompiler.generic.NEWARRAY;
import com.wade.decompiler.generic.PUTFIELD;
import com.wade.decompiler.generic.PUTSTATIC;
import com.wade.decompiler.generic.RET;
import com.wade.decompiler.generic.SIPUSH;
import com.wade.decompiler.generic.TABLESWITCH;
import com.wade.decompiler.generic.gen.ClassGenException;
import com.wade.decompiler.generic.gen.ConstantPoolGen;
import com.wade.decompiler.generic.gen.Visitor;
import com.wade.decompiler.util.ByteSequence;

public abstract class Instruction implements Cloneable {
    private static InstructionComparator cmp = InstructionComparator.DEFAULT;
    protected int length = 1;
    protected InstructionOpCodes opcode = null;

    public Instruction() {
    }

    public Instruction(InstructionOpCodes opcode, int length) {
        this.length = (short) length;
        this.opcode = opcode;
    }

    public abstract void accept(Visitor v);

    public int consumeStack(ConstantPoolGen cpg) {
        return Const.getConsumeStack(opcode.getOpcode());
    }

    public Instruction copy() {
        Instruction i = null;
        if (InstructionConst.getInstruction(this.getOpcode()) != null) {
            i = this;
        } else {
            try {
                i = (Instruction) clone();
            } catch (CloneNotSupportedException e) {
                System.err.println(e);
            }
        }
        return i;
    }

    public void dispose() {
    }

    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode.getOpcode());
    }

    @Override
    public boolean equals(Object that) {
        return (that instanceof Instruction) ? cmp.equals(this, (Instruction) that) : false;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return Const.getOpcodeName(opcode.getOpcode());
    }

    public InstructionOpCodes getOpcode() {
        return opcode;
    }

    @Override
    public int hashCode() {
        return opcode.getOpcode();
    }

    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
    }

    public int produceStack(ConstantPoolGen cpg) {
        return Const.getProduceStack(opcode.getOpcode());
    }

    public void setLength(int length) {
        this.length = (short) length;
    }

    public void setOpcode(InstructionOpCodes opcode) {
        this.opcode = opcode;
    }

    @Override
    public String toString() {
        return toString(true);
    }

    public String toString(boolean verbose) {
        if (verbose) {
            return getName() + "[" + opcode + "](" + length + ")";
        }
        return getName();
    }

    public String toString(ConstantPool cp) {
        return toString(false);
    }

    @Deprecated
    public static InstructionComparator getComparator() {
        return cmp;
    }

    public static boolean isValidByte(int value) {
        return value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE;
    }

    public static boolean isValidShort(int value) {
        return value >= Short.MIN_VALUE && value <= Short.MAX_VALUE;
    }

    public static Instruction readInstruction(ByteSequence bytes) throws IOException {
        boolean wide = false;
        InstructionOpCodes opcode = InstructionOpCodes.read((short) bytes.readUnsignedByte());
        Instruction obj = null;
        if (opcode == InstructionOpCodes.WIDE) {
            wide = true;
            opcode = InstructionOpCodes.read((short) bytes.readUnsignedByte());
        }
        Instruction instruction = InstructionConst.getInstruction(opcode);
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

    @Deprecated
    public static void setComparator(InstructionComparator c) {
        cmp = c;
    }
}
