/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;

/**
 * This class is derived from <em>Attribute</em> and represents a constant
 * value, i.e., a default value for initializing a class field. This class is
 * instantiated by the <em>Attribute.readAttribute()</em> method.
 *
 * @see Attribute
 */
public final class ConstantValue extends Attribute {
    private int constantValueIndex;

    /**
     * Initialize from another object. Note that both objects use the same
     * references (shallow copy). Use clone() for a physical copy.
     */
    public ConstantValue(final ConstantValue c) {
        this(c.getNameIndex(), c.getLength(), c.getConstantValueIndex(), c.getConstantPool());
    }

    /**
     * Construct object from input stream.
     *
     * @param name_index    Name index in constant pool
     * @param length        Content length in bytes
     * @param input         Input stream
     * @param constant_pool Array of constants
     * @throws IOException
     */
    ConstantValue(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool) throws IOException {
        this(name_index, length, input.readUnsignedShort(), constant_pool);
    }

    /**
     * @param name_index         Name index in constant pool
     * @param length             Content length in bytes
     * @param constantValueIndex Index in constant pool
     * @param constant_pool      Array of constants
     */
    public ConstantValue(final int name_index, final int length, final int constantValueIndex, final ConstantPool constant_pool) {
        super(Const.ATTR_CONSTANT_VALUE, name_index, length, constant_pool);
        this.constantValueIndex = constantValueIndex;
    }

    /**
     * Called by objects that are traversing the nodes of the tree implicitely
     * defined by the contents of a Java class. I.e., the hierarchy of methods,
     * fields, attributes, etc. spawns a tree of objects.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitConstantValue(this);
    }

    /**
     * @return deep copy of this attribute
     */
    @Override
    public Attribute copy(final ConstantPool _constant_pool) {
        final ConstantValue c = (ConstantValue) clone();
        c.setConstantPool(_constant_pool);
        return c;
    }

    /**
     * Dump constant value attribute to file stream on binary format.
     *
     * @param file Output file stream
     * @throws IOException
     */
    @Override
    public void dump(final DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(constantValueIndex);
    }

    /**
     * @return Index in constant pool of constant value.
     */
    public int getConstantValueIndex() {
        return constantValueIndex;
    }

    /**
     * @param constantValueIndex the index info the constant pool of this constant
     *                           value
     */
    public void setConstantValueIndex(final int constantValueIndex) {
        this.constantValueIndex = constantValueIndex;
    }

    /**
     * @return String representation of constant value.
     */
    @Override
    public String toString() {
        Constant c = super.getConstantPool().getConstant(constantValueIndex);
        String buf;
        int i;
        // Print constant to string depending on its type
        buf = switch (c.getTag()) {
            case Const.CONSTANT_Long -> String.valueOf(((ConstantLong) c).getBytes());
            case Const.CONSTANT_Float -> String.valueOf(((ConstantFloat) c).getBytes());
            case Const.CONSTANT_Double -> String.valueOf(((ConstantDouble) c).getBytes());
            case Const.CONSTANT_Integer -> String.valueOf(((ConstantInteger) c).getBytes());
            case Const.CONSTANT_String -> {
                i = ((ConstantString) c).getStringIndex();
                c = super.getConstantPool().getConstant(i, Const.CONSTANT_Utf8);
                yield "\"" + Utility.convertString(((ConstantUtf8) c).getBytes()) + "\"";
            }
            default -> throw new IllegalStateException("Type of ConstValue invalid: " + c);
        };
        return buf;
    }
}
