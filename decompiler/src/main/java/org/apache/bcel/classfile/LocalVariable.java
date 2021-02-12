package org.apache.bcel.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Constants;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.classfile.constant.ConstantUtf8;
import org.apache.bcel.enums.ClassFileConstants;

public final class LocalVariable implements Cloneable, Node, Constants {
    private int startPc;
    private int length;
    private int nameIndex;
    private int signatureIndex;
    private int index;
    private ConstantPool constantPool;
    private int origIndex;

    public LocalVariable(final DataInput file, final ConstantPool constant_pool) throws IOException {
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

    @Override
    public void accept(final Visitor v) {
        v.visitLocalVariable(this);
    }

    public LocalVariable copy() {
        try {
            return (LocalVariable) clone();
        } catch (final CloneNotSupportedException e) {
        }
        return null;
    }

    public void dump(final DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(startPc);
        dataOutputStream.writeShort(length);
        dataOutputStream.writeShort(nameIndex);
        dataOutputStream.writeShort(signatureIndex);
        dataOutputStream.writeShort(index);
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

    public String getSignature() {
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

    public void setConstantPool(final ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public void setIndex(final int index) {
        this.index = index;
    }

    public void setLength(final int length) {
        this.length = length;
    }

    public void setNameIndex(final int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public void setSignatureIndex(final int signatureIndex) {
        this.signatureIndex = signatureIndex;
    }

    public void setStartPC(final int startPc) {
        this.startPc = startPc;
    }

    @Override
    public String toString() {
        return toStringShared(false);
    }

    public String toStringShared(final boolean typeTable) {
        final String name = getName();
        final String signature = Utility.signatureToString(getSignature(), false);
        final String label = "LocalVariable" + (typeTable ? "Types" : "");
        return label + "(startPc = " + startPc + ", length = " + length + ", index = " + index + ":" + signature + " " + name + ")";
    }
}
