package com.wade.decompiler.generic.base;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.*;
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

    // public abstract void accept(Visitor v);

    public int consumeStack(ConstantPool cpg) {
        return Const.getConsumeStack(opcode.getOpcode());
    }

    public Instruction copy() {
        Instruction i = null;
        if (InstructionOpCodes.read((short) this.getOpcode().getOpcode()) != null) {
            // if (InstructionConst.getInstruction(this.getOpcode()) != null) {
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

    public int produceStack(ConstantPool cpg) {
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

    public static Instruction getInstructions(InstructionOpCodes opcode) {
        Instruction obj = switch (opcode) {
            case NOP -> new NOP();
            case ACONST_NULL -> new ACONST_NULL();
            case ICONST_M1 -> new ICONST(-1);
            case ICONST_0 -> new ICONST(0);
            case ICONST_1 -> new ICONST(1);
            case ICONST_2 -> new ICONST(2);
            case ICONST_3 -> new ICONST(3);
            case ICONST_4 -> new ICONST(4);
            case ICONST_5 -> new ICONST(5);
            case LCONST_0 -> new LCONST(0);
            case LCONST_1 -> new LCONST(1);
            case FCONST_0 -> new FCONST(0);
            case FCONST_1 -> new FCONST(1);
            case FCONST_2 -> new FCONST(2);
            case DCONST_0 -> new DCONST(0);
            case DCONST_1 -> new DCONST(1);
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
            case IALOAD -> new IALOAD();
            case LALOAD -> new LALOAD();
            case FALOAD -> new FALOAD();
            case DALOAD -> new DALOAD();
            case AALOAD -> new AALOAD();
            case BALOAD -> new BALOAD();
            case CALOAD -> new CALOAD();
            case SALOAD -> new SALOAD();
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
            case FSTORE_0 -> new FSTORE();
            case FSTORE_1 -> new FSTORE();
            case FSTORE_2 -> new FSTORE();
            case FSTORE_3 -> new FSTORE(3);
            case DSTORE_0 -> new DSTORE(0);
            case DSTORE_1 -> new DSTORE(1);
            case DSTORE_2 -> new DSTORE(2);
            case DSTORE_3 -> new DSTORE(3);
            case ASTORE_0 -> new ASTORE(0);
            case ASTORE_1 -> new ASTORE(1);
            case ASTORE_2 -> new ASTORE(2);
            case ASTORE_3 -> new ASTORE(3);
            case IASTORE -> new IASTORE();
            case LASTORE -> new LASTORE();
            case FASTORE -> new FASTORE();
            case DASTORE -> new DASTORE();
            case AASTORE -> new AASTORE();
            case BASTORE -> new BASTORE();
            case CASTORE -> new CASTORE();
            case SASTORE -> new SASTORE();
            case POP -> new POP();
            case POP2 -> new POP();
            case DUP -> new DUP();
            case DUP_X1 -> new DUP_X1();
            case DUP_X2 -> new DUP_X2();
            case DUP2 -> new DUP2();
            case DUP2_X1 -> new DUP2_X1();
            case DUP2_X2 -> new DUP2_X2();
            case SWAP -> new SWAP();
            case IADD -> new IADD();
            case LADD -> new LADD();
            case FADD -> new FADD();
            case DADD -> new DADD();
            case ISUB -> new ISUB();
            case LSUB -> new LSUB();
            case FSUB -> new FSUB();
            case DSUB -> new DSUB();
            case IMUL -> new IMUL();
            case LMUL -> new LMUL();
            case FMUL -> new FMUL();
            case DMUL -> new DMUL();
            case IDIV -> new IDIV();
            case LDIV -> new LDIV();
            case FDIV -> new FDIV();
            case DDIV -> new DDIV();
            case IREM -> new IREM();
            case LREM -> new LREM();
            case FREM -> new FREM();
            case DREM -> new DREM();
            case INEG -> new INEG();
            case LNEG -> new LNEG();
            case FNEG -> new FNEG();
            case DNEG -> new DNEG();
            case ISHL -> new ISHL();
            case LSHL -> new LSHL();
            case ISHR -> new ISHR();
            case LSHR -> new LSHR();
            case IUSHR -> new IUSHR();
            case LUSHR -> new LUSHR();
            case IAND -> new IAND();
            case LAND -> new LAND();
            case IOR -> new IOR();
            case LOR -> new LOR();
            case IXOR -> new IXOR();
            case LXOR -> new LXOR();
            case IINC -> new IINC();
            case I2L -> new I2L();
            case I2F -> new I2F();
            case I2D -> new I2D();
            case L2I -> new L2I();
            case L2F -> new L2F();
            case L2D -> new L2D();
            case F2I -> new F2I();
            case F2L -> new F2L();
            case F2D -> new F2D();
            case D2I -> new D2I();
            case D2L -> new D2L();
            case D2F -> new D2F();
            case I2B -> new I2B();
            case I2C -> new I2C();
            case I2S -> new I2S();
            case LCMP -> new LCMP();
            case FCMPL -> new FCMPL();
            case FCMPG -> new FCMPG();
            case DCMPL -> new DCMPL();
            case DCMPG -> new DCMPG();
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
            case IRETURN -> new IRETURN();
            case LRETURN -> new LRETURN();
            case FRETURN -> new FRETURN();
            case DRETURN -> new DRETURN();
            case ARETURN -> new ARETURN();
            case RETURN -> new RETURN();
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
            case ARRAYLENGTH -> new ARRAYLENGTH();
            case ATHROW -> new ATHROW();
            case CHECKCAST -> new CHECKCAST();
            case INSTANCEOF -> new INSTANCEOF();
            case MONITORENTER -> new MONITORENTER();
            case MONITOREXIT -> new MONITOREXIT();
            // case WIDE->new WIDE();
            case MULTIANEWARRAY -> new MULTIANEWARRAY();
            case IFNULL -> new IFNULL();
            case IFNONNULL -> new IFNONNULL();
            case GOTO_W -> new GOTO_W();
            case JSR_W -> new JSR_W();
            case BREAKPOINT -> new BREAKPOINT();
            // case LDC_QUICK->new LDC_QUICK();
            // case LDC_W_QUICK->new LDC_W_QUICK();
            // case LDC2_W_QUICK->new LDC2_W_QUICK();
            // case GETFIELD_QUICK->new GETFIELD_QUICK();
            // case PUTFIELD_QUICK->new PUTFIELD_QUICK();
            // case GETFIELD2_QUICK->new GETFIELD2_QUICK();
            // case PUTFIELD2_QUICK->new PUTFIELD2_QUICK();
            // case GETSTATIC_QUICK->new GETSTATIC_QUICK();
            // case PUTSTATIC_QUICK->new PUTSTATIC_QUICK();
            // case GETSTATIC2_QUICK->new GETSTATIC2_QUICK();
            // case PUTSTATIC2_QUICK->new PUTSTATIC2_QUICK();
            // case INVOKEVIRTUAL_QUICK->new INVOKEVIRTUAL_QUICK();
            // case INVOKENONVIRTUAL_QUICK->new INVOKENONVIRTUAL_QUICK();
            // case INVOKESUPER_QUICK->new INVOKESUPER_QUICK();
            // case INVOKESTATIC_QUICK->new INVOKESTATIC_QUICK();
            // case INVOKEINTERFACE_QUICK->new INVOKEINTERFACE_QUICK();
            // case INVOKEVIRTUALOBJECT_QUICK->new INVOKEVIRTUALOBJECT_QUICK();
            // case NEW_QUICK->new NEW_QUICK();
            // case ANEWARRAY_QUICK->new ANEWARRAY_QUICK();
            // case MULTIANEWARRAY_QUICK->new MULTIANEWARRAY_QUICK();
            // case CHECKCAST_QUICK->new CHECKCAST_QUICK();
            // case INSTANCEOF_QUICK->new INSTANCEOF_QUICK();
            // case INVOKEVIRTUAL_QUICK_W->new INVOKEVIRTUAL_QUICK_W();
            // case GETFIELD_QUICK_W->new GETFIELD_QUICK_W();
            // case PUTFIELD_QUICK_W->new PUTFIELD_QUICK_W();
            case IMPDEP1 -> new IMPDEP1();
            case IMPDEP2 -> new IMPDEP2();
            default -> throw new ClassGenException("Illegal opcode detected: " + opcode);
        };
        return obj;
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
        if (opcode == InstructionOpCodes.WIDE) {
            wide = true;
            opcode = InstructionOpCodes.read((short) bytes.readUnsignedByte());
        }
        Instruction obj = getInstructions(opcode);
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
