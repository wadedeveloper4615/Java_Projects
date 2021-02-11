
package org.apache.bcel.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Const;

public class ConstantPool implements Cloneable, Node {

    private Constant[] constantPool;

    public ConstantPool(final Constant[] constantPool) {
        this.constantPool = constantPool;
    }

    public ConstantPool(final DataInput input) throws IOException, ClassFormatException {
        byte tag;
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
        final byte tag = c.getTag();
        switch (tag) {
            case Const.CONSTANT_Class:
                i = ((ConstantClass) c).getNameIndex();
                c = getConstant(i, Const.CONSTANT_Utf8);
                str = Utility.compactClassName(((ConstantUtf8) c).getBytes(), false);
                break;
            case Const.CONSTANT_String:
                i = ((ConstantString) c).getStringIndex();
                c = getConstant(i, Const.CONSTANT_Utf8);
                str = "\"" + escape(((ConstantUtf8) c).getBytes()) + "\"";
                break;
            case Const.CONSTANT_Utf8:
                str = ((ConstantUtf8) c).getBytes();
                break;
            case Const.CONSTANT_Double:
                str = String.valueOf(((ConstantDouble) c).getBytes());
                break;
            case Const.CONSTANT_Float:
                str = String.valueOf(((ConstantFloat) c).getBytes());
                break;
            case Const.CONSTANT_Long:
                str = String.valueOf(((ConstantLong) c).getBytes());
                break;
            case Const.CONSTANT_Integer:
                str = String.valueOf(((ConstantInteger) c).getBytes());
                break;
            case Const.CONSTANT_NameAndType:
                str = constantToString(((ConstantNameAndType) c).getNameIndex(), Const.CONSTANT_Utf8) + " " + constantToString(((ConstantNameAndType) c).getSignatureIndex(), Const.CONSTANT_Utf8);
                break;
            case Const.CONSTANT_InterfaceMethodref:
            case Const.CONSTANT_Methodref:
            case Const.CONSTANT_Fieldref:
                str = constantToString(((ConstantCP) c).getClassIndex(), Const.CONSTANT_Class) + "." + constantToString(((ConstantCP) c).getNameAndTypeIndex(), Const.CONSTANT_NameAndType);
                break;
            case Const.CONSTANT_MethodHandle:
                // Note that the ReferenceIndex may point to a Fieldref, Methodref or
                // InterfaceMethodref - so we need to peek ahead to get the actual type.
                final ConstantMethodHandle cmh = (ConstantMethodHandle) c;
                str = Const.getMethodHandleName(cmh.getReferenceKind()) + " " + constantToString(cmh.getReferenceIndex(), getConstant(cmh.getReferenceIndex()).getTag());
                break;
            case Const.CONSTANT_MethodType:
                final ConstantMethodType cmt = (ConstantMethodType) c;
                str = constantToString(cmt.getDescriptorIndex(), Const.CONSTANT_Utf8);
                break;
            case Const.CONSTANT_InvokeDynamic:
                final ConstantInvokeDynamic cid = (ConstantInvokeDynamic) c;
                str = cid.getBootstrapMethodAttrIndex() + ":" + constantToString(cid.getNameAndTypeIndex(), Const.CONSTANT_NameAndType);
                break;
            case Const.CONSTANT_Module:
                i = ((ConstantModule) c).getNameIndex();
                c = getConstant(i, Const.CONSTANT_Utf8);
                str = Utility.compactClassName(((ConstantUtf8) c).getBytes(), false);
                break;
            case Const.CONSTANT_Package:
                i = ((ConstantPackage) c).getNameIndex();
                c = getConstant(i, Const.CONSTANT_Utf8);
                str = Utility.compactClassName(((ConstantUtf8) c).getBytes(), false);
                break;
            default: // Never reached
                throw new IllegalArgumentException("Unknown constant type " + tag);
        }
        return str;
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

    public String constantToString(final int index, final byte tag) throws ClassFormatException {
        final Constant c = getConstant(index, tag);
        return constantToString(c);
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

    public Constant getConstant(final int index, final byte tag) throws ClassFormatException {
        Constant c;
        c = getConstant(index);
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

        switch (tag) {
            case Const.CONSTANT_Class:
                i = ((ConstantClass) c).getNameIndex();
                break;
            case Const.CONSTANT_String:
                i = ((ConstantString) c).getStringIndex();
                break;
            case Const.CONSTANT_Module:
                i = ((ConstantModule) c).getNameIndex();
                break;
            case Const.CONSTANT_Package:
                i = ((ConstantPackage) c).getNameIndex();
                break;
            default:
                throw new IllegalArgumentException("getConstantString called with illegal tag " + tag);
        }
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
}
