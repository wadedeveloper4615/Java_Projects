package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.ClassFormatException;
import com.wade.app.Const;

public abstract class Constant {
    protected byte tag;

    public Constant(byte tag) {
        this.tag = tag;
    }

    public byte getTag() {
        return tag;
    }

    public static Constant readConstant(DataInputStream dataInput) throws IOException, ClassFormatException {
        byte b = dataInput.readByte();
        switch (b) {
            case Const.CONSTANT_Class:
                return new ConstantClass(dataInput);
            case Const.CONSTANT_Fieldref:
                return new ConstantFieldref(dataInput);
            case Const.CONSTANT_Methodref:
                return new ConstantMethodref(dataInput);
            case Const.CONSTANT_InterfaceMethodref:
                return new ConstantInterfaceMethodref(dataInput);
            case Const.CONSTANT_String:
                return new ConstantString(dataInput);
            case Const.CONSTANT_Integer:
                return new ConstantInteger(dataInput);
            case Const.CONSTANT_Float:
                return new ConstantFloat(dataInput);
            case Const.CONSTANT_Long:
                return new ConstantLong(dataInput);
            case Const.CONSTANT_Double:
                return new ConstantDouble(dataInput);
            case Const.CONSTANT_NameAndType:
                return new ConstantNameAndType(dataInput);
            case Const.CONSTANT_Utf8:
                return ConstantUtf8.getInstance(dataInput);
            case Const.CONSTANT_MethodHandle:
                return new ConstantMethodHandle(dataInput);
            case Const.CONSTANT_MethodType:
                return new ConstantMethodType(dataInput);
            case Const.CONSTANT_Dynamic:
                return new ConstantDynamic(dataInput);
            case Const.CONSTANT_InvokeDynamic:
                return new ConstantInvokeDynamic(dataInput);
            case Const.CONSTANT_Module:
                return new ConstantModule(dataInput);
            case Const.CONSTANT_Package:
                return new ConstantPackage(dataInput);
            default:
                throw new ClassFormatException("Invalid byte tag in constant pool: " + b);
        }
    }
}
