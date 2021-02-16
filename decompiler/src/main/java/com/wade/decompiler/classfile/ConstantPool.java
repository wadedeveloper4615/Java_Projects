package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;

public class ConstantPool implements Cloneable, Node {
    private Constant[] constantPool;

    public ConstantPool(final Constant[] constantPool) {
        this.constantPool = constantPool;
    }

    public ConstantPool(final DataInput input) throws IOException, ClassFormatException {
        int tag;
        final int constant_pool_count = input.readUnsignedShort();
        constantPool = new Constant[constant_pool_count];
        for (int i = 1; i < constant_pool_count; i++) {
            constantPool[i] = Constant.readConstant(input);
            tag = constantPool[i].getTag();
            if ((tag == Const.CONSTANT_Double) || (tag == Const.CONSTANT_Long)) {
                i++;
            }
        }
    }

    @Override
    public void accept(final Visitor v) {
        v.visitConstantPool(this);
    }

    public String constantToString(Constant c) throws ClassFormatException {
        String str;
        int i;
        byte tag = c.getTag();
        str = switch (tag) {
            case Const.CONSTANT_Class -> {
                i = ((ConstantClass) c).getNameIndex();
                c = getConstant(i, Const.CONSTANT_Utf8);
                yield Utility.compactClassName(((ConstantUtf8) c).getBytes(), false);
            }
            case Const.CONSTANT_String -> {
                i = ((ConstantString) c).getStringIndex();
                c = getConstant(i, Const.CONSTANT_Utf8);
                yield "\"" + escape(((ConstantUtf8) c).getBytes()) + "\"";
            }
            case Const.CONSTANT_Utf8 -> ((ConstantUtf8) c).getBytes();
            case Const.CONSTANT_Double -> String.valueOf(((ConstantDouble) c).getBytes());
            case Const.CONSTANT_Float -> String.valueOf(((ConstantFloat) c).getBytes());
            case Const.CONSTANT_Long -> String.valueOf(((ConstantLong) c).getBytes());
            case Const.CONSTANT_Integer -> String.valueOf(((ConstantInteger) c).getBytes());
            case Const.CONSTANT_NameAndType -> constantToString(((ConstantNameAndType) c).getNameIndex(), Const.CONSTANT_Utf8) + " " + constantToString(((ConstantNameAndType) c).getSignatureIndex(), Const.CONSTANT_Utf8);
            case Const.CONSTANT_InterfaceMethodref, Const.CONSTANT_Methodref, Const.CONSTANT_Fieldref -> constantToString(((ConstantCP) c).getClassIndex(), Const.CONSTANT_Class) + "." + constantToString(((ConstantCP) c).getNameAndTypeIndex(), Const.CONSTANT_NameAndType);
            case Const.CONSTANT_MethodHandle -> {
                // Note that the ReferenceIndex may point to a Fieldref, Methodref or
                // InterfaceMethodref - so we need to peek ahead to get the actual type.
                final ConstantMethodHandle cmh = (ConstantMethodHandle) c;
                yield Const.getMethodHandleName(cmh.getReferenceKind()) + " " + constantToString(cmh.getReferenceIndex(), getConstant(cmh.getReferenceIndex()).getTag());
            }
            case Const.CONSTANT_MethodType -> {
                final ConstantMethodType cmt = (ConstantMethodType) c;
                yield constantToString(cmt.getDescriptorIndex(), Const.CONSTANT_Utf8);
            }
            case Const.CONSTANT_InvokeDynamic -> {
                final ConstantInvokeDynamic cid = (ConstantInvokeDynamic) c;
                yield cid.getBootstrapMethodAttrIndex() + ":" + constantToString(cid.getNameAndTypeIndex(), Const.CONSTANT_NameAndType);
            }
            case Const.CONSTANT_Module -> {
                i = ((ConstantModule) c).getNameIndex();
                c = getConstant(i, Const.CONSTANT_Utf8);
                yield Utility.compactClassName(((ConstantUtf8) c).getBytes(), false);
            }
            case Const.CONSTANT_Package -> {
                i = ((ConstantPackage) c).getNameIndex();
                c = getConstant(i, Const.CONSTANT_Utf8);
                yield Utility.compactClassName(((ConstantUtf8) c).getBytes(), false);
            }
            default -> throw new IllegalArgumentException("Unknown constant type " + tag);
        };
        return str;
    }

    public String constantToString(final int index, int tag) throws ClassFormatException {
        final Constant c = getConstant(index, tag);
        return constantToString(c);
    }

    public ConstantPool copy() {
        ConstantPool c = null;
        try {
            c = (ConstantPool) clone();
            c.constantPool = new Constant[constantPool.length];
            for (int i = 1; i < constantPool.length; i++) {
                if (constantPool[i] != null) {
                    c.constantPool[i] = constantPool[i].copy();
                }
            }
        } catch (final CloneNotSupportedException e) {
            // TODO should this throw?
        }
        return c;
    }

    public void dump(final DataOutputStream file) throws IOException {
        file.writeShort(constantPool.length);
        for (int i = 1; i < constantPool.length; i++) {
            if (constantPool[i] != null) {
                constantPool[i].dump(file);
            }
        }
    }

    public Constant getConstant(final int index) {
        if (index >= constantPool.length || index < 0) {
            throw new ClassFormatException("Invalid constant pool reference: " + index + ". Constant pool size is: " + constantPool.length);
        }
        return constantPool[index];
    }

    public Constant getConstant(final int index, int tag) throws ClassFormatException {
        Constant c = getConstant(index);
        if (c == null) {
            throw new ClassFormatException("Constant pool at index " + index + " is null.");
        }
        if (c.getTag() != tag) {
            throw new ClassFormatException("Expected class `" + Const.getConstantName(tag) + "' at index " + index + " and got " + c);
        }
        return c;
    }

    public Constant[] getConstantPool() {
        return constantPool;
    }

    public String getConstantString(final int index, final byte tag) throws ClassFormatException {
        Constant c;
        int i;
        c = getConstant(index, tag);
        i = switch (tag) {
            case Const.CONSTANT_Class -> ((ConstantClass) c).getNameIndex();
            case Const.CONSTANT_String -> ((ConstantString) c).getStringIndex();
            case Const.CONSTANT_Module -> ((ConstantModule) c).getNameIndex();
            case Const.CONSTANT_Package -> ((ConstantPackage) c).getNameIndex();
            default -> throw new IllegalArgumentException("getConstantString called with illegal tag " + tag);
        };
        // Finally get the string from the constant pool
        c = getConstant(i, Const.CONSTANT_Utf8);
        return ((ConstantUtf8) c).getBytes();
    }

    public int getLength() {
        return constantPool == null ? 0 : constantPool.length;
    }

    public void setConstant(final int index, final Constant constant) {
        constantPool[index] = constant;
    }

    public void setConstantPool(final Constant[] constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder();
        for (int i = 1; i < constantPool.length; i++) {
            buf.append(i).append(")").append(constantPool[i]).append("\n");
        }
        return buf.toString();
    }

    private static String escape(final String str) {
        final int len = str.length();
        final StringBuilder buf = new StringBuilder(len + 5);
        final char[] ch = str.toCharArray();
        for (int i = 0; i < len; i++) {
            switch (ch[i]) {
                case '\n':
                    buf.append("\\n");
                    break;
                case '\r':
                    buf.append("\\r");
                    break;
                case '\t':
                    buf.append("\\t");
                    break;
                case '\b':
                    buf.append("\\b");
                    break;
                case '"':
                    buf.append("\\\"");
                    break;
                default:
                    buf.append(ch[i]);
            }
        }
        return buf.toString();
    }
}
