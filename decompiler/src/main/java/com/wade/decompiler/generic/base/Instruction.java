package com.wade.decompiler.generic.base;

import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.constants.Const;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.*;
import com.wade.decompiler.util.ByteSequence;

public abstract class Instruction implements InstructionInit {
    protected int length;
    protected InstructionOpCodes opcode;
    private String name;
    private int consumeStack;
    private int produceStack;
    protected ConstantPool constantPool;

    public Instruction() {
        length = 1;
        opcode = null;
        name = "";
        consumeStack = -1;
        produceStack = -1;
    }

    public Instruction(InstructionOpCodes opcode, int length, ConstantPool constantPool) {
        this.length = (short) length;
        this.opcode = opcode;
        this.name = Const.getOpcodeName(opcode.getOpcode());
        this.consumeStack = Const.getConsumeStack(opcode.getOpcode());
        this.produceStack = Const.getProduceStack(opcode.getOpcode());
        this.constantPool = constantPool;
    }

    public int consumeStack(ConstantPool cpg) {
        return consumeStack;
    }

    public void dispose() {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Instruction other = (Instruction) obj;
        if (consumeStack != other.consumeStack)
            return false;
        if (length != other.length)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (opcode != other.opcode)
            return false;
        if (produceStack != other.produceStack)
            return false;
        return true;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public int getConsumeStack() {
        return consumeStack;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public InstructionOpCodes getOpcode() {
        return opcode;
    }

    public int getProduceStack() {
        return produceStack;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + consumeStack;
        result = prime * result + length;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((opcode == null) ? 0 : opcode.hashCode());
        result = prime * result + produceStack;
        return result;
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
    }

    public int produceStack(ConstantPool cpg) {
        return produceStack;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public void setConsumeStack(int consumeStack) {
        this.consumeStack = consumeStack;
    }

    public void setLength(int length) {
        this.length = (short) length;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOpcode(InstructionOpCodes opcode) {
        this.opcode = opcode;
    }

    public void setProduceStack(int produceStack) {
        this.produceStack = produceStack;
    }

    @Override
    public String toString() {
        return "Instruction [opcode=" + opcode + "]";
    }

    public static Instruction getInstructions(InstructionOpCodes opcode, ConstantPool cp) {
        Instruction obj = switch (opcode) {
            case NOP -> new NOP(cp);
            case ACONST_NULL -> new ACONST_NULL(cp);
            case ICONST_M1 -> new ICONST(-1, cp);
            case ICONST_0 -> new ICONST(0, cp);
            case ICONST_1 -> new ICONST(1, cp);
            case ICONST_2 -> new ICONST(2, cp);
            case ICONST_3 -> new ICONST(3, cp);
            case ICONST_4 -> new ICONST(4, cp);
            case ICONST_5 -> new ICONST(5, cp);
            case LCONST_0 -> new LCONST(0, cp);
            case LCONST_1 -> new LCONST(1, cp);
            case FCONST_0 -> new FCONST(0, cp);
            case FCONST_1 -> new FCONST(1, cp);
            case FCONST_2 -> new FCONST(2, cp);
            case DCONST_0 -> new DCONST(0, cp);
            case DCONST_1 -> new DCONST(1, cp);
            case BIPUSH -> new BIPUSH((byte) 0, cp);
            case SIPUSH -> new SIPUSH((byte) 0, cp);
            case LDC -> new LDC((byte) -1, cp);
            case LDC_W -> new LDC_W((byte) 0, cp);
            case LDC2_W -> new LDC2_W((byte) 0, cp);
            case ILOAD -> new ILOAD(cp);
            case LLOAD -> new LLOAD(cp);
            case FLOAD -> new FLOAD(cp);
            case DLOAD -> new DLOAD(cp);
            case ALOAD -> new ALOAD(cp);
            case ILOAD_0 -> new ILOAD(0, cp);
            case ILOAD_1 -> new ILOAD(1, cp);
            case ILOAD_2 -> new ILOAD(2, cp);
            case ILOAD_3 -> new ILOAD(3, cp);
            case LLOAD_0 -> new LLOAD(0, cp);
            case LLOAD_1 -> new LLOAD(1, cp);
            case LLOAD_2 -> new LLOAD(2, cp);
            case LLOAD_3 -> new LLOAD(3, cp);
            case FLOAD_0 -> new FLOAD(0, cp);
            case FLOAD_1 -> new FLOAD(1, cp);
            case FLOAD_2 -> new FLOAD(2, cp);
            case FLOAD_3 -> new FLOAD(3, cp);
            case DLOAD_0 -> new DLOAD(0, cp);
            case DLOAD_1 -> new DLOAD(1, cp);
            case DLOAD_2 -> new DLOAD(2, cp);
            case DLOAD_3 -> new DLOAD(3, cp);
            case ALOAD_0 -> new ALOAD(0, cp);
            case ALOAD_1 -> new ALOAD(1, cp);
            case ALOAD_2 -> new ALOAD(2, cp);
            case ALOAD_3 -> new ALOAD(3, cp);
            case IALOAD -> new IALOAD(cp);
            case LALOAD -> new LALOAD(cp);
            case FALOAD -> new FALOAD(cp);
            case DALOAD -> new DALOAD(cp);
            case AALOAD -> new AALOAD(cp);
            case BALOAD -> new BALOAD(cp);
            case CALOAD -> new CALOAD(cp);
            case SALOAD -> new SALOAD(cp);
            case ISTORE -> new ISTORE(cp);
            case LSTORE -> new LSTORE(cp);
            case FSTORE -> new FSTORE(cp);
            case DSTORE -> new DSTORE(cp);
            case ASTORE -> new ASTORE(cp);
            case ISTORE_0 -> new ISTORE(0, cp);
            case ISTORE_1 -> new ISTORE(1, cp);
            case ISTORE_2 -> new ISTORE(2, cp);
            case ISTORE_3 -> new ISTORE(3, cp);
            case LSTORE_0 -> new LSTORE(0, cp);
            case LSTORE_1 -> new LSTORE(1, cp);
            case LSTORE_2 -> new LSTORE(2, cp);
            case LSTORE_3 -> new LSTORE(3, cp);
            case FSTORE_0 -> new FSTORE(0, cp);
            case FSTORE_1 -> new FSTORE(1, cp);
            case FSTORE_2 -> new FSTORE(2, cp);
            case FSTORE_3 -> new FSTORE(3, cp);
            case DSTORE_0 -> new DSTORE(0, cp);
            case DSTORE_1 -> new DSTORE(1, cp);
            case DSTORE_2 -> new DSTORE(2, cp);
            case DSTORE_3 -> new DSTORE(3, cp);
            case ASTORE_0 -> new ASTORE(0, cp);
            case ASTORE_1 -> new ASTORE(1, cp);
            case ASTORE_2 -> new ASTORE(2, cp);
            case ASTORE_3 -> new ASTORE(3, cp);
            case IASTORE -> new IASTORE(cp);
            case LASTORE -> new LASTORE(cp);
            case FASTORE -> new FASTORE(cp);
            case DASTORE -> new DASTORE(cp);
            case AASTORE -> new AASTORE(cp);
            case BASTORE -> new BASTORE(cp);
            case CASTORE -> new CASTORE(cp);
            case SASTORE -> new SASTORE(cp);
            case POP -> new POP(cp);
            case POP2 -> new POP(cp);
            case DUP -> new DUP(cp);
            case DUP_X1 -> new DUP_X1(cp);
            case DUP_X2 -> new DUP_X2(cp);
            case DUP2 -> new DUP2(cp);
            case DUP2_X1 -> new DUP2_X1(cp);
            case DUP2_X2 -> new DUP2_X2(cp);
            case SWAP -> new SWAP(cp);
            case IADD -> new IADD(cp);
            case LADD -> new LADD(cp);
            case FADD -> new FADD(cp);
            case DADD -> new DADD(cp);
            case ISUB -> new ISUB(cp);
            case LSUB -> new LSUB(cp);
            case FSUB -> new FSUB(cp);
            case DSUB -> new DSUB(cp);
            case IMUL -> new IMUL(cp);
            case LMUL -> new LMUL(cp);
            case FMUL -> new FMUL(cp);
            case DMUL -> new DMUL(cp);
            case IDIV -> new IDIV(cp);
            case LDIV -> new LDIV(cp);
            case FDIV -> new FDIV(cp);
            case DDIV -> new DDIV(cp);
            case IREM -> new IREM(cp);
            case LREM -> new LREM(cp);
            case FREM -> new FREM(cp);
            case DREM -> new DREM(cp);
            case INEG -> new INEG(cp);
            case LNEG -> new LNEG(cp);
            case FNEG -> new FNEG(cp);
            case DNEG -> new DNEG(cp);
            case ISHL -> new ISHL(cp);
            case LSHL -> new LSHL(cp);
            case ISHR -> new ISHR(cp);
            case LSHR -> new LSHR(cp);
            case IUSHR -> new IUSHR(cp);
            case LUSHR -> new LUSHR(cp);
            case IAND -> new IAND(cp);
            case LAND -> new LAND(cp);
            case IOR -> new IOR(cp);
            case LOR -> new LOR(cp);
            case IXOR -> new IXOR(cp);
            case LXOR -> new LXOR(cp);
            case IINC -> new IINC(-1, -1, cp);
            case I2L -> new I2L(cp);
            case I2F -> new I2F(cp);
            case I2D -> new I2D(cp);
            case L2I -> new L2I(cp);
            case L2F -> new L2F(cp);
            case L2D -> new L2D(cp);
            case F2I -> new F2I(cp);
            case F2L -> new F2L(cp);
            case F2D -> new F2D(cp);
            case D2I -> new D2I(cp);
            case D2L -> new D2L(cp);
            case D2F -> new D2F(cp);
            case I2B -> new I2B(cp);
            case I2C -> new I2C(cp);
            case I2S -> new I2S(cp);
            case LCMP -> new LCMP(cp);
            case FCMPL -> new FCMPL(cp);
            case FCMPG -> new FCMPG(cp);
            case DCMPL -> new DCMPL(cp);
            case DCMPG -> new DCMPG(cp);
            case IFEQ -> new IFEQ(null, cp);
            case IFNE -> new IFNE(null, cp);
            case IFLT -> new IFLT(null, cp);
            case IFGE -> new IFGE(null, cp);
            case IFGT -> new IFGT(null, cp);
            case IFLE -> new IFLE(null, cp);
            case IF_ICMPEQ -> new IF_ICMPEQ(null, cp);
            case IF_ICMPNE -> new IF_ICMPNE(null, cp);
            case IF_ICMPLT -> new IF_ICMPLT(null, cp);
            case IF_ICMPGE -> new IF_ICMPGE(null, cp);
            case IF_ICMPGT -> new IF_ICMPGT(null, cp);
            case IF_ICMPLE -> new IF_ICMPLE(null, cp);
            case IF_ACMPEQ -> new IF_ACMPEQ(null, cp);
            case IF_ACMPNE -> new IF_ACMPNE(null, cp);
            case GOTO -> new GOTO(null, cp);
            case JSR -> new JSR(null, cp);
            case RET -> new RET(-1, cp);
            case TABLESWITCH -> new TABLESWITCH(null, null, null, cp);
            case LOOKUPSWITCH -> new LOOKUPSWITCH(null, null, null, cp);
            case IRETURN -> new IRETURN(cp);
            case LRETURN -> new LRETURN(cp);
            case FRETURN -> new FRETURN(cp);
            case DRETURN -> new DRETURN(cp);
            case ARETURN -> new ARETURN(cp);
            case RETURN -> new RETURN(cp);
            case GETSTATIC -> new GETSTATIC(-1, cp);
            case PUTSTATIC -> new PUTSTATIC(-1, cp);
            case GETFIELD -> new GETFIELD(-1, cp);
            case PUTFIELD -> new PUTFIELD(-1, cp);
            case INVOKEVIRTUAL -> new INVOKEVIRTUAL(-1, cp);
            case INVOKESPECIAL -> new INVOKESPECIAL(-1, cp);
            case INVOKESTATIC -> new INVOKESTATIC(-1, cp);
            case INVOKEINTERFACE -> new INVOKEINTERFACE(-1, -1, cp);
            case INVOKEDYNAMIC -> new INVOKEDYNAMIC(-1, cp);
            case NEW -> new NEW(-1, cp);
            case NEWARRAY -> new NEWARRAY((byte) -1, cp);
            case ANEWARRAY -> new ANEWARRAY(-1, cp);
            case ARRAYLENGTH -> new ARRAYLENGTH(cp);
            case ATHROW -> new ATHROW(cp);
            case CHECKCAST -> new CHECKCAST(-1, cp);
            case INSTANCEOF -> new INSTANCEOF(-1, cp);
            case MONITORENTER -> new MONITORENTER(cp);
            case MONITOREXIT -> new MONITOREXIT(cp);
            case MULTIANEWARRAY -> new MULTIANEWARRAY(-1, (short) -1, cp);
            case IFNULL -> new IFNULL(null, cp);
            case IFNONNULL -> new IFNONNULL(null, cp);
            case GOTO_W -> new GOTO_W(null, cp);
            case JSR_W -> new JSR_W(null, cp);
            case BREAKPOINT -> new BREAKPOINT(cp);
            case IMPDEP1 -> new IMPDEP1(cp);
            case IMPDEP2 -> new IMPDEP2(cp);
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

    public static Instruction readInstruction(ByteSequence bytes, ConstantPool cp) throws IOException {
        boolean wide = false;
        InstructionOpCodes opcode = InstructionOpCodes.read(bytes.readUnsignedByte());
        if (opcode == InstructionOpCodes.WIDE) {
            wide = true;
            opcode = InstructionOpCodes.read(bytes.readUnsignedByte());
        }
        Instruction obj = getInstructions(opcode, cp);
        if (wide && !((obj instanceof LocalVariableInstruction) || (obj instanceof IINC) || (obj instanceof RET))) {
            throw new ClassGenException("Illegal opcode after wide: " + opcode);
        }
        obj.setOpcode(opcode);
        obj.initFromFile(bytes, wide);
        return obj;
    }
}
