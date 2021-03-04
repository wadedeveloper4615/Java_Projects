package com.wade.decompiler.classfile.instructions.base;

import java.io.IOException;

import com.wade.decompiler.classfile.constant.Constant;
import com.wade.decompiler.classfile.constant.ConstantClass;
import com.wade.decompiler.classfile.constant.ConstantFieldRef;
import com.wade.decompiler.classfile.constant.ConstantLong;
import com.wade.decompiler.classfile.constant.ConstantMethodref;
import com.wade.decompiler.classfile.constant.ConstantNameAndType;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.classfile.exceptions.ClassGenException;
import com.wade.decompiler.classfile.instructions.base.inter.IndexedInstruction;
import com.wade.decompiler.classfile.instructions.base.inter.TypedInstruction;
import com.wade.decompiler.classfile.instructions.type.Type;
import com.wade.decompiler.constants.Const;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generate.attribute.LocalVariableGen;
import com.wade.decompiler.generate.attribute.LocalVariableTableGen;
import com.wade.decompiler.util.ByteSequence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public abstract class LocalVariableInstruction extends Instruction implements TypedInstruction, IndexedInstruction {
    protected int index = -1;
    private InstructionOpCodes cTag = null;
    private InstructionOpCodes canonTag = null;
    private String superName;
    private String methodName;
    private String signature;
    private Object constantValue;
    private String constantString;
    protected LocalVariableGen localVariable;

    public LocalVariableInstruction(InstructionOpCodes opcode, InstructionOpCodes c_tag, ConstantPool constantPool) {
        super(opcode, 2, constantPool);
        this.canonTag = opcode;
        this.cTag = c_tag;
    }

    protected LocalVariableInstruction(InstructionOpCodes opcode, InstructionOpCodes cTag, int n, LocalVariableTableGen localVariableTable, ConstantPool constantPool) {
        super(opcode, 2, constantPool);
        this.cTag = cTag;
        this.canonTag = opcode;
        setIndex(n);
        LocalVariableGen[] localVariableTableEntries = localVariableTable.getLocalVariableTable();
        if (localVariableTableEntries != null && localVariableTableEntries.length > n) {
            localVariable = localVariableTableEntries[n];
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
        } else if (c instanceof ConstantFieldRef) {
            int classIndex = ((ConstantFieldRef) c).getClassIndex();
            int nameAndTypeIndex = ((ConstantFieldRef) c).getNameAndTypeIndex();

            ConstantClass cc = (ConstantClass) constantPool.getConstant(classIndex, ClassFileConstants.CONSTANT_Class);
            ConstantNameAndType cnt = (ConstantNameAndType) constantPool.getConstant(nameAndTypeIndex, ClassFileConstants.CONSTANT_NameAndType);

            superName = ((ConstantUtf8) constantPool.getConstant(cc.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
            methodName = ((ConstantUtf8) constantPool.getConstant(cnt.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
            signature = ((ConstantUtf8) constantPool.getConstant(cnt.getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        } else if (c instanceof ConstantClass) {
            constantValue = ((ConstantClass) c).getConstantValue(constantPool);
        } else if (c instanceof ConstantUtf8) {
            constantString = ((ConstantUtf8) c).getBytes();
        } else if (c instanceof ConstantNameAndType) {
            methodName = ((ConstantNameAndType) c).getName(constantPool);
            signature = ((ConstantNameAndType) c).getSignature(constantPool);
        } else if (c instanceof ConstantLong) {
            constantValue = ((ConstantLong) c).getConstantValue(constantPool);
        } else {
            if (c != null)
                System.out.println(c.getClass().getName());
        }
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public Type getType() {
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

    private boolean wide() {
        return index > Const.MAX_BYTE;
    }
}
