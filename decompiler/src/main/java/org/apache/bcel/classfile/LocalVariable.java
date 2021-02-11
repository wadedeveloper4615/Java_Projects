
package org.apache.bcel.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Const;
import org.apache.bcel.Constants;

public final class LocalVariable implements Cloneable, Node, Constants {

    private int startPc; // Range in which the variable is valid
    private int length;
    private int nameIndex; // Index in constant pool of variable name
    // Technically, a decscriptor_index for a local variable table entry
    // and a signatureIndex for a local variable type table entry.
    private int signatureIndex; // Index of variable signature
    private int index;
    private ConstantPool constantPool;
    private int origIndex; // never changes; used to match up with LocalVariableTypeTable entries

    public LocalVariable(final LocalVariable localVariable) {
        this(localVariable.getStartPC(), localVariable.getLength(), localVariable.getNameIndex(), localVariable.getSignatureIndex(), localVariable.getIndex(), localVariable.getConstantPool());
        this.origIndex = localVariable.getOrigIndex();
    }

    LocalVariable(final DataInput file, final ConstantPool constant_pool) throws IOException {
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

    @Override
    public void accept(final Visitor v) {
        v.visitLocalVariable(this);
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

    public int getLength() {
        return length;
    }

    public String getName() {
        ConstantUtf8 c;
        c = (ConstantUtf8) constantPool.getConstant(nameIndex, Const.CONSTANT_Utf8);
        return c.getBytes();
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public String getSignature() {
        ConstantUtf8 c;
        c = (ConstantUtf8) constantPool.getConstant(signatureIndex, Const.CONSTANT_Utf8);
        return c.getBytes();
    }

    public int getSignatureIndex() {
        return signatureIndex;
    }

    public int getIndex() {
        return index;
    }

    public int getOrigIndex() {
        return origIndex;
    }

    public int getStartPC() {
        return startPc;
    }

    String toStringShared(final boolean typeTable) {
        final String name = getName();
        final String signature = Utility.signatureToString(getSignature(), false);
        final String label = "LocalVariable" + (typeTable ? "Types" : "");
        return label + "(startPc = " + startPc + ", length = " + length + ", index = " + index + ":" + signature + " " + name + ")";
    }

    public void setConstantPool(final ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public void setLength(final int length) {
        this.length = length;
    }

    public void setNameIndex(final int nameIndex) { // TODO unused
        this.nameIndex = nameIndex;
    }

    public void setSignatureIndex(final int signatureIndex) { // TODO unused
        this.signatureIndex = signatureIndex;
    }

    public void setIndex(final int index) { // TODO unused
        this.index = index;
    }

    public void setStartPC(final int startPc) { // TODO unused
        this.startPc = startPc;
    }

    @Override
    public String toString() {
        return toStringShared(false);
    }

    public LocalVariable copy() {
        try {
            return (LocalVariable) clone();
        } catch (final CloneNotSupportedException e) {
            // TODO should this throw?
        }
        return null;
    }
}
