package com.wade.app.constantpool;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.ClassFormatException;
import com.wade.app.enums.ClassFileConstants;

public abstract class Constant {
    protected ClassFileConstants tag;

    public Constant(ClassFileConstants tag) {
        this.tag = tag;
    }

    public ClassFileConstants getTag() {
        return tag;
    }

    public static Constant readConstant(DataInputStream in) throws IOException {
        ClassFileConstants b = ClassFileConstants.read(in.readByte());
        switch (b) {
            case CONSTANT_Class:
                return new ConstantClass(in);
            case CONSTANT_Fieldref:
                return new ConstantFieldRef(in);
            case CONSTANT_Methodref:
                return new ConstantMethodRef(in);
            case CONSTANT_InterfaceMethodref:
                return new ConstantInterfaceMethodRef(in);
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
                return new ConstantUtf8(in);
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
