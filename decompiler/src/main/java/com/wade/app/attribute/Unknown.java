package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.wade.app.ClassFileConstants;
import com.wade.app.Const;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.constantpool.ConstantUtf8;
import com.wade.app.exception.ClassFormatException;

public final class Unknown extends Attribute {
    private static final Map<String, Unknown> unknownAttributes = new HashMap<>();
    private byte[] bytes;
    private final String name;

    public Unknown(final int name_index, final int length, final byte[] bytes, final ConstantPool constant_pool) throws ClassFormatException {
        super(Const.ATTR_UNKNOWN, name_index, length, constant_pool);
        this.bytes = bytes;
        name = ((ConstantUtf8) constant_pool.getConstant(name_index, ClassFileConstants.CONSTANT_Utf8)).getBytes();
        unknownAttributes.put(name, this);
    }

    public Unknown(final int name_index, final int length, final DataInputStream input, final ConstantPool constant_pool) throws IOException, ClassFormatException {
        this(name_index, length, (byte[]) null, constant_pool);
        if (length > 0) {
            bytes = new byte[length];
            input.readFully(bytes);
        }
    }

    public Unknown(final Unknown c) throws ClassFormatException {
        this(c.getNameIndex(), c.getLength(), c.getBytes(), c.getConstantPool());
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public String getName() {
        return name;
    }

    static Unknown[] getUnknownAttributes() {
        final Unknown[] unknowns = new Unknown[unknownAttributes.size()];
        unknownAttributes.values().toArray(unknowns);
        unknownAttributes.clear();
        return unknowns;
    }
}
