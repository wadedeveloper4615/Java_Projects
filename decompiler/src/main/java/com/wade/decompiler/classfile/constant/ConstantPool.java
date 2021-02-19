package com.wade.decompiler.classfile.constant;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.Const;
import com.wade.decompiler.classfile.ClassFormatException;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.util.Utility;

public class ConstantPool {
    private Constant[] constantPool;

    public ConstantPool(Constant[] constantPool) {
        this.constantPool = constantPool;
    }

    public ConstantPool(DataInput input) throws IOException, ClassFormatException {
        ClassFileConstants tag;
        int constant_pool_count = input.readUnsignedShort();
        constantPool = new Constant[constant_pool_count];
        for (int i = 1; i < constant_pool_count; i++) {
            constantPool[i] = Constant.readConstant(input);
            tag = constantPool[i].getTag();
            if ((tag == ClassFileConstants.CONSTANT_Double) || (tag == ClassFileConstants.CONSTANT_Long)) {
                i++;
            }
        }
    }

    public String constantToString(Constant c) throws ClassFormatException {
        String str;
        int i;
        ClassFileConstants tag = c.getTag();
        str = switch (tag) {
            case CONSTANT_Class -> {
                i = ((ConstantClass) c).getNameIndex();
                c = getConstant(i, ClassFileConstants.CONSTANT_Utf8);
                yield Utility.compactClassName(((ConstantUtf8) c).getBytes(), false);
            }
            case CONSTANT_String -> {
                i = ((ConstantString) c).getStringIndex();
                c = getConstant(i, ClassFileConstants.CONSTANT_Utf8);
                yield "\"" + escape(((ConstantUtf8) c).getBytes()) + "\"";
            }
            case CONSTANT_Utf8 -> ((ConstantUtf8) c).getBytes();
            case CONSTANT_Double -> String.valueOf(((ConstantDouble) c).getBytes());
            case CONSTANT_Float -> String.valueOf(((ConstantFloat) c).getBytes());
            case CONSTANT_Long -> String.valueOf(((ConstantLong) c).getBytes());
            case CONSTANT_Integer -> String.valueOf(((ConstantInteger) c).getBytes());
            case CONSTANT_NameAndType -> constantToString(((ConstantNameAndType) c).getNameIndex(), ClassFileConstants.CONSTANT_Utf8) + " " + constantToString(((ConstantNameAndType) c).getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8);
            case CONSTANT_InterfaceMethodref, CONSTANT_Methodref, CONSTANT_Fieldref -> constantToString(((ConstantCP) c).getClassIndex(), ClassFileConstants.CONSTANT_Class) + "." + constantToString(((ConstantCP) c).getNameAndTypeIndex(), ClassFileConstants.CONSTANT_NameAndType);
            case CONSTANT_MethodHandle -> {
                // Note that the ReferenceIndex may point to a Fieldref, Methodref or
                // InterfaceMethodref - so we need to peek ahead to get the actual type.
                ConstantMethodHandle cmh = (ConstantMethodHandle) c;
                yield Const.getMethodHandleName(cmh.getReferenceKind()) + " " + constantToString(cmh.getReferenceIndex(), getConstant(cmh.getReferenceIndex()).getTag());
            }
            case CONSTANT_MethodType -> {
                ConstantMethodType cmt = (ConstantMethodType) c;
                yield constantToString(cmt.getDescriptorIndex(), ClassFileConstants.CONSTANT_Utf8);
            }
            case CONSTANT_InvokeDynamic -> {
                ConstantInvokeDynamic cid = (ConstantInvokeDynamic) c;
                yield cid.getBootstrapMethodAttrIndex() + ":" + constantToString(cid.getNameAndTypeIndex(), ClassFileConstants.CONSTANT_NameAndType);
            }
            case CONSTANT_Module -> {
                i = ((ConstantModule) c).getNameIndex();
                c = getConstant(i, ClassFileConstants.CONSTANT_Utf8);
                yield Utility.compactClassName(((ConstantUtf8) c).getBytes(), false);
            }
            case CONSTANT_Package -> {
                i = ((ConstantPackage) c).getNameIndex();
                c = getConstant(i, ClassFileConstants.CONSTANT_Utf8);
                yield Utility.compactClassName(((ConstantUtf8) c).getBytes(), false);
            }
            default -> throw new IllegalArgumentException("Unknown constant type " + tag);
        };
        return str;
    }

    public String constantToString(int index, ClassFileConstants tag) throws ClassFormatException {
        Constant c = getConstant(index, tag.getTag());
        return constantToString(c);
    }

    public String constantToString(int index, int tag) throws ClassFormatException {
        Constant c = getConstant(index, tag);
        return constantToString(c);
    }

    public Constant getConstant(int index) {
        if (index >= constantPool.length || index < 0) {
            System.out.println("Invalid constant pool reference: " + index + ". Constant pool size is: " + constantPool.length);
            throw new ClassFormatException("Invalid constant pool reference: " + index + ". Constant pool size is: " + constantPool.length);
        }
        return constantPool[index];
    }

    public Constant getConstant(int index, ClassFileConstants tag) throws ClassFormatException {
        Constant c = getConstant(index);
        if (c == null) {
            throw new ClassFormatException("Constant pool at index " + index + " is null.");
        }
        if (c.getTag() != tag) {
            throw new ClassFormatException("Expected class `" + Const.getConstantName(tag.getTag()) + "' at index " + index + " and got " + c);
        }
        return c;
    }

    public Constant getConstant(int index, int tag) throws ClassFormatException {
        Constant c = getConstant(index);
        if (c == null) {
            throw new ClassFormatException("Constant pool at index " + index + " is null.");
        }
        if (c.getTag().getTag() != tag) {
            throw new ClassFormatException("Expected class `" + Const.getConstantName(tag) + "' at index " + index + " and got " + c);
        }
        return c;
    }

    public Constant[] getConstantPool() {
        return constantPool;
    }

    public String getConstantString(int index, ClassFileConstants tag) throws ClassFormatException {
        Constant c = getConstant(index, tag);
        int i = switch (tag) {
            case CONSTANT_Class -> ((ConstantClass) c).getNameIndex();
            case CONSTANT_String -> ((ConstantString) c).getStringIndex();
            case CONSTANT_Module -> ((ConstantModule) c).getNameIndex();
            case CONSTANT_Package -> ((ConstantPackage) c).getNameIndex();
            default -> throw new IllegalArgumentException("getConstantString called with illegal tag " + tag);
        };
        c = getConstant(i, ClassFileConstants.CONSTANT_Utf8);
        return ((ConstantUtf8) c).getBytes();
    }

    public int getLength() {
        return constantPool == null ? 0 : constantPool.length;
    }

    public void setConstant(int index, Constant constant) {
        constantPool[index] = constant;
    }

    public void setConstantPool(Constant[] constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        for (int i = 1; i < constantPool.length; i++) {
            buf.append(i).append(")").append(constantPool[i]).append("\n");
        }
        return buf.toString();
    }

    private static String escape(String str) {
        int len = str.length();
        StringBuilder buf = new StringBuilder(len + 5);
        char[] ch = str.toCharArray();
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
