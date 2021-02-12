package org.apache.bcel.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.classfile.constant.ConstantUtf8;
import org.apache.bcel.enums.ClassAccessFlags;
import org.apache.bcel.enums.ClassFileConstants;

public class MethodParameter implements Cloneable {
    private int nameIndex;
    private int accessFlags;

    public MethodParameter() {
    }

    public MethodParameter(final DataInput input) throws IOException {
        nameIndex = input.readUnsignedShort();
        accessFlags = input.readUnsignedShort();
    }

    public void accept(final Visitor v) {
        v.visitMethodParameter(this);
    }

    public MethodParameter copy() {
        try {
            return (MethodParameter) clone();
        } catch (final CloneNotSupportedException e) {
        }
        return null;
    }

    public final void dump(final DataOutputStream file) throws IOException {
        file.writeShort(nameIndex);
        file.writeShort(accessFlags);
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public String getParameterName(final ConstantPool constant_pool) {
        if (nameIndex == 0) {
            return null;
        }
        return ((ConstantUtf8) constant_pool.getConstant(nameIndex, ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }

    public boolean isFinal() {
        return (accessFlags & ClassAccessFlags.ACC_FINAL.getFlag()) != 0;
    }

    public boolean isMandated() {
        return (accessFlags & ClassAccessFlags.ACC_MANDATED.getFlag()) != 0;
    }

    public boolean isSynthetic() {
        return (accessFlags & ClassAccessFlags.ACC_SYNTHETIC.getFlag()) != 0;
    }

    public void setAccessFlags(final int access_flags) {
        this.accessFlags = access_flags;
    }

    public void setNameIndex(final int name_index) {
        this.nameIndex = name_index;
    }
}
