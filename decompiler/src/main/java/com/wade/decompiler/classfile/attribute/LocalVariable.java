package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Constants;
import com.wade.decompiler.classfile.Node;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.classfile.gen.Visitor;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.util.Utility;

public class LocalVariable implements Cloneable, Node, Constants {
    private int startPc; // Range in which the variable is valid
    private int length;
    private int nameIndex; // Index in constant pool of variable name
    // Technically, a decscriptor_index for a local variable table entry
    // and a signatureIndex for a local variable type table entry.
    private int signatureIndex; // Index of variable signature
    private int index;
    private ConstantPool constantPool;
    private int origIndex; // never changes; used to match up with LocalVariableTypeTable entries

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
    }

    public LocalVariable(int startPc, int length, int nameIndex, int signatureIndex, int index, ConstantPool constantPool, int origIndex) {
        this.startPc = startPc;
        this.length = length;
        this.nameIndex = nameIndex;
        this.signatureIndex = signatureIndex;
        this.index = index;
        this.constantPool = constantPool;
        this.origIndex = origIndex;
    }

    public LocalVariable(LocalVariable localVariable) {
        this(localVariable.getStartPC(), localVariable.getLength(), localVariable.getNameIndex(), localVariable.getSignatureIndex(), localVariable.getIndex(), localVariable.getConstantPool());
        this.origIndex = localVariable.getOrigIndex();
    }

    @Override
    public void accept(Visitor v) {
        v.visitLocalVariable(this);
    }

    public LocalVariable copy() {
        try {
            return (LocalVariable) clone();
        } catch (CloneNotSupportedException e) {
            // TODO should this throw?
        }
        return null;
    }

    public void dump(DataOutputStream dataOutputStream) throws IOException {
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

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public void setIndex(int index) { // TODO unused
        this.index = index;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setNameIndex(int nameIndex) { // TODO unused
        this.nameIndex = nameIndex;
    }

    public void setSignatureIndex(int signatureIndex) { // TODO unused
        this.signatureIndex = signatureIndex;
    }

    public void setStartPC(int startPc) { // TODO unused
        this.startPc = startPc;
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
