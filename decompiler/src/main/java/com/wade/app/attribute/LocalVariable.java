package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.classfile.Constants;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.constantpool.ConstantUtf8;
import com.wade.app.constantpool.Node;
import com.wade.app.enums.ClassFileConstants;
import com.wade.app.exception.ClassFormatException;

public final class LocalVariable implements Cloneable, Node, Constants {
    private int startPc;
    private int length;
    private int nameIndex;
    private int signatureIndex;
    private int index;
    private ConstantPool constantPool;
    private int origIndex;

    public LocalVariable(final DataInputStream file, final ConstantPool constant_pool) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), constant_pool);
    }

    public LocalVariable(final int startPc, final int length, final int nameIndex, final int signatureIndex, final int index, final ConstantPool constantPool) {
        this.startPc = startPc;
        this.length = length;
        this.nameIndex = nameIndex;
        this.signatureIndex = signatureIndex;
        this.index = index;
        this.constantPool = constantPool;
        this.origIndex = index;
    }

    public LocalVariable(final int startPc, final int length, final int nameIndex, final int signatureIndex, final int index, final ConstantPool constantPool, final int origIndex) {
        this.startPc = startPc;
        this.length = length;
        this.nameIndex = nameIndex;
        this.signatureIndex = signatureIndex;
        this.index = index;
        this.constantPool = constantPool;
        this.origIndex = origIndex;
    }

    public LocalVariable(final LocalVariable localVariable) {
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

    public String getName() throws ClassFormatException {
        ConstantUtf8 c;
        c = (ConstantUtf8) constantPool.getConstant(nameIndex, ClassFileConstants.CONSTANT_Utf8);
        return c.getBytes();
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getOrigIndex() {
        return origIndex;
    }

    public String getSignature() throws ClassFormatException {
        ConstantUtf8 c;
        c = (ConstantUtf8) constantPool.getConstant(signatureIndex, ClassFileConstants.CONSTANT_Utf8);
        return c.getBytes();
    }

    public int getSignatureIndex() {
        return signatureIndex;
    }

    public int getStartPC() {
        return startPc;
    }

}
