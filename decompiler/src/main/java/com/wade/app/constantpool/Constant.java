package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.enums.ClassFileConstants;
import com.wade.app.exception.ClassFormatException;

public abstract class Constant {
    protected ClassFileConstants tag;

    public Constant(ClassFileConstants tag) {
        this.tag = tag;
    }

    public ClassFileConstants getTag() {
        return tag;
    }

    public static Constant readConstant(DataInputStream in) throws IOException, ClassFormatException {
        ClassFileConstants b = ClassFileConstants.read(in.readByte());
        switch (b) {
            case CONSTANT_Class:
                return new ConstantClass(in);
            case CONSTANT_Fieldref:
                return new ConstantFieldref(in);
            case CONSTANT_Methodref:
                return new ConstantMethodref(in);
            case CONSTANT_InterfaceMethodref:
                return new ConstantInterfaceMethodref(in);
            case CONSTANT_String:
                return new ConstantString(in);
            case CONSTANT_Integer:
                return new ConstantInteger(in);
            case CONSTANT_Float:
                return new ConstantFloat(in);
            case CONSTANT_Long:
                return new ConstantLong(in);
            case CONSTANT_Double:
                return new ConstantDouble(in);
            case CONSTANT_NameAndType:
                return new ConstantNameAndType(in);
            case CONSTANT_Utf8:
                return ConstantUtf8.getInstance(in);
            case CONSTANT_MethodHandle:
                return new ConstantMethodHandle(in);
            case CONSTANT_MethodType:
                return new ConstantMethodType(in);
            case CONSTANT_Dynamic:
                return new ConstantDynamic(in);
            case CONSTANT_InvokeDynamic:
                return new ConstantInvokeDynamic(in);
            case CONSTANT_Module:
                return new ConstantModule(in);
            case CONSTANT_Package:
                return new ConstantPackage(in);
            default:
                throw new ClassFormatException("Invalid byte tag in constant pool: " + b);
        }
    }
}
