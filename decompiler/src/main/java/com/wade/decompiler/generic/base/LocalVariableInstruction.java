package com.wade.decompiler.generic.base;

import java.io.IOException;

import com.wade.decompiler.Const;
import com.wade.decompiler.classfile.constant.Constant;
import com.wade.decompiler.classfile.constant.ConstantClass;
import com.wade.decompiler.classfile.constant.ConstantFieldref;
import com.wade.decompiler.classfile.constant.ConstantMethodref;
import com.wade.decompiler.classfile.constant.ConstantNameAndType;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.type.Type;
import com.wade.decompiler.util.ByteSequence;

public abstract class LocalVariableInstruction extends Instruction implements TypedInstruction, IndexedInstruction {
    protected int index = -1;
    private InstructionOpCodes cTag = null;
    private InstructionOpCodes canonTag = null;
    private String superName;
    private String methodName;
    private String signature;

    public LocalVariableInstruction() {
    }

    public LocalVariableInstruction(InstructionOpCodes canonTag, InstructionOpCodes c_tag, ConstantPool cp) {
        this.setConstantPool(cp);
        this.canonTag = canonTag;
        this.cTag = c_tag;
    }

    protected LocalVariableInstruction(InstructionOpCodes opcode, InstructionOpCodes cTag, int n, ConstantPool constantPool) {
        super(opcode, 2, constantPool);
        this.cTag = cTag;
        this.canonTag = opcode;
        setIndex(n);
    }

    public InstructionOpCodes getCanonicalTag() {
        return canonTag;
    }

    public InstructionOpCodes getCanonTag() {
        return canonTag;
    }

    public InstructionOpCodes getcTag() {
        return cTag;
    }

    @Override
    public int getIndex() {
        return index;
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
            index = bytes.readUnsignedShort();
            super.setLength(4);
        } else {
            short _opcode = (short) super.getOpcode().getOpcode();
            if (((_opcode >= InstructionOpCodes.ILOAD.getOpcode()) && (_opcode <= InstructionOpCodes.ALOAD.getOpcode())) || ((_opcode >= InstructionOpCodes.ISTORE.getOpcode()) && (_opcode <= InstructionOpCodes.ASTORE.getOpcode()))) {
                index = bytes.readUnsignedByte();
                super.setLength(2);
            } else if (_opcode <= InstructionOpCodes.ALOAD_3.getOpcode()) { // compact load instruction such as ILOAD_2
                index = (_opcode - InstructionOpCodes.ILOAD_0.getOpcode()) % 4;
                super.setLength(1);
            } else { // Assert ISTORE_0 <= tag <= ASTORE_3
                index = (_opcode - InstructionOpCodes.ISTORE_0.getOpcode()) % 4;
                super.setLength(1);
            }
        }
        if (index > 0) {
            Constant c = constantPool.getConstant(index);
            extractConstantPoolInfo(c);
        }
    }

    private void extractConstantPoolInfo(Constant c) {
        if (c instanceof ConstantMethodref) {
            int classIndex = ((ConstantMethodref) c).getClassIndex();
            int nameAndTypeIndex = ((ConstantMethodref) c).getNameAndTypeIndex();

            ConstantClass cc = (ConstantClass) constantPool.getConstant(classIndex, ClassFileConstants.CONSTANT_Class);
            ConstantNameAndType cnt = (ConstantNameAndType) constantPool.getConstant(nameAndTypeIndex, ClassFileConstants.CONSTANT_NameAndType);

            superName = ((ConstantUtf8) constantPool.getConstant(cc.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
            methodName = ((ConstantUtf8) constantPool.getConstant(cnt.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
            signature = ((ConstantUtf8) constantPool.getConstant(cnt.getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        } else if (c instanceof ConstantFieldref) {
            int classIndex = ((ConstantFieldref) c).getClassIndex();
            int nameAndTypeIndex = ((ConstantFieldref) c).getNameAndTypeIndex();

            ConstantClass cc = (ConstantClass) constantPool.getConstant(classIndex, ClassFileConstants.CONSTANT_Class);
            ConstantNameAndType cnt = (ConstantNameAndType) constantPool.getConstant(nameAndTypeIndex, ClassFileConstants.CONSTANT_NameAndType);

            superName = ((ConstantUtf8) constantPool.getConstant(cc.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
            methodName = ((ConstantUtf8) constantPool.getConstant(cnt.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
            signature = ((ConstantUtf8) constantPool.getConstant(cnt.getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        } else {
            System.out.println(c.getClass().getName());
        }
    }

    public void setCanonTag(InstructionOpCodes canonTag) {
        this.canonTag = canonTag;
    }

    public void setcTag(InstructionOpCodes cTag) {
        this.cTag = cTag;
    }

    @Override
    public void setIndex(int index) {
        if ((index < 0) || (index > Const.MAX_SHORT)) {
            throw new ClassGenException("Illegal value: " + index);
        }
        this.index = index;
        if (index <= 3) {
            super.setOpcode(InstructionOpCodes.read((short) (cTag.getOpcode() + index)));
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

    public void setIndexOnly(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return super.toString() + "[index=" + index + ", superName=" + superName + ", methodName=" + methodName + ", signature=" + signature + "]";
    }

    private boolean wide() {
        return index > Const.MAX_BYTE;
    }
}
