package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.ClassFileConstants;
import com.wade.app.Const;
import com.wade.app.exception.ClassFormatException;
import com.wade.app.util.Utility;

public class ConstantPool {
    private Constant[] constantPool;

    public ConstantPool(DataInputStream input) throws IOException, ClassFormatException {
        ClassFileConstants tag;
        final int constant_pool_count = input.readUnsignedShort();
        constantPool = new Constant[constant_pool_count];
        for (int i = 1; i < constant_pool_count; i++) {
            constantPool[i] = Constant.readConstant(input);
            tag = ClassFileConstants.read(constantPool[i].getTag().getTag());
            if ((tag == ClassFileConstants.CONSTANT_Double) || (tag == ClassFileConstants.CONSTANT_Long)) {
                i++;
            }
        }
    }

    public String constantToString(Constant c) throws ClassFormatException, IOException {
        String str;
        int i;
        final ClassFileConstants tag = ClassFileConstants.read(c.getTag().getTag());
        switch (tag) {
            case CONSTANT_Class:
                i = ((ConstantClass) c).getNameIndex();
                c = getConstant(i, ClassFileConstants.CONSTANT_Utf8);
                str = Utility.compactClassName(((ConstantUtf8) c).getBytes(), false);
                break;
            case CONSTANT_String:
                i = ((ConstantString) c).getStringIndex();
                c = getConstant(i, ClassFileConstants.CONSTANT_Utf8);
                str = "\"" + escape(((ConstantUtf8) c).getBytes()) + "\"";
                break;
            case CONSTANT_Utf8:
                str = ((ConstantUtf8) c).getBytes();
                break;
            case CONSTANT_Double:
                str = String.valueOf(((ConstantDouble) c).getBytes());
                break;
            case CONSTANT_Float:
                str = String.valueOf(((ConstantFloat) c).getBytes());
                break;
            case CONSTANT_Long:
                str = String.valueOf(((ConstantLong) c).getBytes());
                break;
            case CONSTANT_Integer:
                str = String.valueOf(((ConstantInteger) c).getBytes());
                break;
            case CONSTANT_NameAndType:
                str = constantToString(((ConstantNameAndType) c).getNameIndex(), ClassFileConstants.CONSTANT_Utf8) + " " + constantToString(((ConstantNameAndType) c).getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8);
                break;
            case CONSTANT_InterfaceMethodref:
            case CONSTANT_Methodref:
            case CONSTANT_Fieldref:
                str = constantToString(((ConstantCP) c).getClassIndex(), ClassFileConstants.CONSTANT_Class) + "." + constantToString(((ConstantCP) c).getNameAndTypeIndex(), ClassFileConstants.CONSTANT_NameAndType);
                break;
            case CONSTANT_MethodHandle:
                // Note that the ReferenceIndex may point to a Fieldref, Methodref or
                // InterfaceMethodref - so we need to peek ahead to get the actual type.
                final ConstantMethodHandle cmh = (ConstantMethodHandle) c;
                Constant constant = getConstant(cmh.getReferenceIndex());
                str = Const.getMethodHandleName(cmh.getReferenceKind()) + " " + constantToString(cmh.getReferenceIndex(), constant.getTag());
                break;
            case CONSTANT_MethodType:
                final ConstantMethodType cmt = (ConstantMethodType) c;
                str = constantToString(cmt.getDescriptorIndex(), ClassFileConstants.CONSTANT_Utf8);
                break;
            case CONSTANT_InvokeDynamic:
                final ConstantInvokeDynamic cid = (ConstantInvokeDynamic) c;
                str = cid.getBootstrapMethodAttrIndex() + ":" + constantToString(cid.getNameAndTypeIndex(), ClassFileConstants.CONSTANT_NameAndType);
                break;
            case CONSTANT_Module:
                i = ((ConstantModule) c).getNameIndex();
                c = getConstant(i, ClassFileConstants.CONSTANT_Utf8);
                str = Utility.compactClassName(((ConstantUtf8) c).getBytes(), false);
                break;
            case CONSTANT_Package:
                i = ((ConstantPackage) c).getNameIndex();
                c = getConstant(i, ClassFileConstants.CONSTANT_Utf8);
                str = Utility.compactClassName(((ConstantUtf8) c).getBytes(), false);
                break;
            default: // Never reached
                throw new IllegalArgumentException("Unknown constant type " + tag);
        }
        return str;
    }

    public String constantToString(int index, byte tag) throws ClassFormatException, IOException {
        final Constant c = getConstant(index, tag);
        return constantToString(c);
    }

    public String constantToString(int index, ClassFileConstants tag) throws ClassFormatException, IOException {
        final Constant c = getConstant(index, tag);
        return constantToString(c);
    }

    public Constant getConstant(final int index) throws ClassFormatException {
        if (index >= constantPool.length || index < 0) {
            throw new ClassFormatException("Invalid constant pool reference: " + index + ". Constant pool size is: " + constantPool.length);
        }
        return constantPool[index];
    }

    public Constant getConstant(int index, byte tag) throws ClassFormatException, IOException {
        Constant c = getConstant(index);
        if (c == null) {
            throw new ClassFormatException("Constant pool at index " + index + " is null.");
        }
        if (c.getTag().getTag() != tag) {
            throw new ClassFormatException("Expected class `" + ClassFileConstants.read(tag).getName() + "' at index " + index + " and got " + c);
        }
        return c;
    }

    public Constant getConstant(int index, ClassFileConstants tag) throws ClassFormatException {
        Constant c = getConstant(index);
        if (c == null) {
            throw new ClassFormatException("Constant pool at index " + index + " is null.");
        }
        if (c.getTag().getTag() != tag.getTag()) {
            throw new ClassFormatException("Expected class `" + tag.getName() + "' at index " + index + " and got " + c);
        }
        return c;
    }

    public String getConstantString(int index, ClassFileConstants tag) throws ClassFormatException {
        int i;
        Constant c = getConstant(index, tag);
        switch (tag) {
            case CONSTANT_Class:
                i = ((ConstantClass) c).getNameIndex();
                break;
            case CONSTANT_String:
                i = ((ConstantString) c).getStringIndex();
                break;
            case CONSTANT_Module:
                i = ((ConstantModule) c).getNameIndex();
                break;
            case CONSTANT_Package:
                i = ((ConstantPackage) c).getNameIndex();
                break;
            default:
                throw new IllegalArgumentException("getConstantString called with illegal tag " + tag);
        }
        // Finally get the string from the constant pool
        c = getConstant(i, ClassFileConstants.CONSTANT_Utf8);
        return ((ConstantUtf8) c).getBytes();
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
