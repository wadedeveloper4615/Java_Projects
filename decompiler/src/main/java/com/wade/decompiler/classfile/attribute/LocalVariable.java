package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.constants.Constants;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.util.Utility;

public class LocalVariable implements Constants {
    private int startPc;
    private int length;
    private int nameIndex;
    private int signatureIndex;
    private int index;
    private ConstantPool constantPool;
    private int origIndex;
    private String name;
    private String signature;

    public LocalVariable(DataInput file, ConstantPool constant_pool) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), constant_pool);
    }

    public LocalVariable(int startPc, int length, int nameIndex, int signatureIndex, int index, ConstantPool constantPool) {
        this.startPc = startPc;
        this.length = length;
        this.nameIndex = nameIndex;
        this.signatureIndex = signatureIndex;
        this.index = index;
        this.constantPool = constantPool;
        this.origIndex = index;
        this.name = ((ConstantUtf8) constantPool.getConstant(nameIndex, ClassFileConstants.CONSTANT_Utf8)).getBytes();
        this.signature = ((ConstantUtf8) constantPool.getConstant(signatureIndex, ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }

    public LocalVariable(int startPc, int length, int nameIndex, int signatureIndex, int index, ConstantPool constantPool, int origIndex) {
        this.startPc = startPc;
        this.length = length;
        this.nameIndex = nameIndex;
        this.signatureIndex = signatureIndex;
        this.index = index;
        this.constantPool = constantPool;
        this.origIndex = origIndex;
        this.name = ((ConstantUtf8) constantPool.getConstant(nameIndex, ClassFileConstants.CONSTANT_Utf8)).getBytes();
        this.signature = ((ConstantUtf8) constantPool.getConstant(signatureIndex, ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }

    public LocalVariable(LocalVariable localVariable) {
        this(localVariable.getStartPC(), localVariable.getLength(), localVariable.getNameIndex(), localVariable.getSignatureIndex(), localVariable.getIndex(), localVariable.getConstantPool());
        this.origIndex = localVariable.getOrigIndex();
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public int getIndex() {
        return index;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getOrigIndex() {
        return origIndex;
    }

    public String getSignature() {
        return signature;
    }

    public int getSignatureIndex() {
        return signatureIndex;
    }

    public int getStartPC() {
        return startPc;
    }

    @Override
    public String toString() {
        return toStringShared(false);
    }

    public String toStringShared(boolean typeTable) {
        String name = getName();
        String signature = Utility.signatureToString(getSignature(), false);
        String label = "LocalVariable" + (typeTable ? "Types" : "");
        return label + "(startPc = " + startPc + ", length = " + length + ", index = " + index + ":" + signature + " " + name + ")";
    }
}
