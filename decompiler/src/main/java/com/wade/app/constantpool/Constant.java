package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.Const;
import com.wade.app.exception.ClassFormatException;

public abstract class Constant {
    protected byte tag;

    public Constant(byte tag) {
        this.tag = tag;
    }

    public byte getTag() {
        return tag;
    }

    public static Constant readConstant(DataInputStream in) throws IOException, ClassFormatException {
        byte b = in.readByte();
        switch (b) {
            case Const.CONSTANT_Class:
                return new ConstantClass(in);
            case Const.CONSTANT_Fieldref:
                return new ConstantFieldref(in);
            case Const.CONSTANT_Methodref:
                return new ConstantMethodref(in);
            case Const.CONSTANT_InterfaceMethodref:
                return new ConstantInterfaceMethodref(in);
            case Const.CONSTANT_String:
                return new ConstantString(in);
            case Const.CONSTANT_Integer:
                return new ConstantInteger(in);
            case Const.CONSTANT_Float:
                return new ConstantFloat(in);
            case Const.CONSTANT_Long:
                return new ConstantLong(in);
            case Const.CONSTANT_Double:
                return new ConstantDouble(in);
            case Const.CONSTANT_NameAndType:
                return new ConstantNameAndType(in);
            case Const.CONSTANT_Utf8:
                return ConstantUtf8.getInstance(in);
            case Const.CONSTANT_MethodHandle:
                return new ConstantMethodHandle(in);
            case Const.CONSTANT_MethodType:
                return new ConstantMethodType(in);
            case Const.CONSTANT_Dynamic:
                return new ConstantDynamic(in);
            case Const.CONSTANT_InvokeDynamic:
                return new ConstantInvokeDynamic(in);
            case Const.CONSTANT_Module:
                return new ConstantModule(in);
            case Const.CONSTANT_Package:
                return new ConstantPackage(in);
            default:
                throw new ClassFormatException("Invalid byte tag in constant pool: " + b);
        }
    }
}
