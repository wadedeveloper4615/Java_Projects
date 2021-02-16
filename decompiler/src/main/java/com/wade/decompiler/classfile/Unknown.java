package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.wade.decompiler.Const;
import com.wade.decompiler.enums.ClassFileAttributes;

public class Unknown extends Attribute {
    private static Map<String, Unknown> unknownAttributes = new HashMap<>();
    private byte[] bytes;
    private String name;

    public Unknown(int name_index, int length, byte[] bytes, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_UNKNOWN, name_index, length, constant_pool);
        this.bytes = bytes;
        name = ((ConstantUtf8) constant_pool.getConstant(name_index, Const.CONSTANT_Utf8)).getBytes();
        unknownAttributes.put(name, this);
    }

    public Unknown(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, (byte[]) null, constant_pool);
        if (length > 0) {
            bytes = new byte[length];
            input.readFully(bytes);
        }
    }

    public Unknown(Unknown c) {
        this(c.getNameIndex(), c.getLength(), c.getBytes(), c.getConstantPool());
    }

    @Override
    public void accept(Visitor v) {
        v.visitUnknown(this);
    }

    @Override
    public Attribute copy(ConstantPool _constant_pool) {
        Unknown c = (Unknown) clone();
        if (bytes != null) {
            c.bytes = new byte[bytes.length];
            System.arraycopy(bytes, 0, c.bytes, 0, bytes.length);
        }
        c.setConstantPool(_constant_pool);
        return c;
    }

    @Override
    public void dump(DataOutputStream file) throws IOException {
        super.dump(file);
        if (super.getLength() > 0) {
            file.write(bytes, 0, super.getLength());
        }
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        if (super.getLength() == 0 || bytes == null) {
            return "(Unknown attribute " + name + ")";
        }
        String hex;
        if (super.getLength() > 10) {
            byte[] tmp = new byte[10];
            System.arraycopy(bytes, 0, tmp, 0, 10);
            hex = Utility.toHexString(tmp) + "... (truncated)";
        } else {
            hex = Utility.toHexString(bytes);
        }
        return "(Unknown attribute " + name + ": " + hex + ")";
    }

    static Unknown[] getUnknownAttributes() {
        Unknown[] unknowns = new Unknown[unknownAttributes.size()];
        unknownAttributes.values().toArray(unknowns);
        unknownAttributes.clear();
        return unknowns;
    }
}
