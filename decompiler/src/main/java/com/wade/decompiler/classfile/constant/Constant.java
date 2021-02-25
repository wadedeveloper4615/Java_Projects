package com.wade.decompiler.classfile.constant;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.ClassFormatException;
import com.wade.decompiler.enums.ClassFileConstants;

public abstract class Constant {
    protected ClassFileConstants tag;

    public Constant(ClassFileConstants tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Constant other = (Constant) obj;
        if (tag != other.tag)
            return false;
        return true;
    }

    public ClassFileConstants getTag() {
        return tag;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tag == null) ? 0 : tag.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return tag.getName();
    }

    public static Constant readConstant(DataInputStream dataInput) throws IOException, ClassFormatException {
        ClassFileConstants constant = ClassFileConstants.read(dataInput.readByte());
        switch (constant) {
            case CONSTANT_Class:
                return new ConstantClass(dataInput);
            case CONSTANT_Fieldref:
                return new ConstantFieldRef(dataInput);
            case CONSTANT_Methodref:
                return new ConstantMethodref(dataInput);
            case CONSTANT_InterfaceMethodref:
                return new ConstantInterfaceMethodRef(dataInput);
            case CONSTANT_String:
                return new ConstantString(dataInput);
            case CONSTANT_Integer:
                return new ConstantInteger(dataInput);
            case CONSTANT_Float:
                return new ConstantFloat(dataInput);
            case CONSTANT_Long:
                return new ConstantLong(dataInput);
            case CONSTANT_Double:
                return new ConstantDouble(dataInput);
            case CONSTANT_NameAndType:
                return new ConstantNameAndType(dataInput);
            case CONSTANT_Utf8:
                return new ConstantUtf8(dataInput);
            case CONSTANT_MethodHandle:
                return new ConstantMethodHandle(dataInput);
            case CONSTANT_MethodType:
                return new ConstantMethodType(dataInput);
            case CONSTANT_Dynamic:
                return new ConstantDynamic(dataInput);
            case CONSTANT_InvokeDynamic:
                return new ConstantInvokeDynamic(dataInput);
            case CONSTANT_Module:
                return new ConstantModule(dataInput);
            case CONSTANT_Package:
                return new ConstantPackage(dataInput);
            default:
                throw new ClassFormatException("Invalid byte tag in constant pool: " + constant);
        }
    }
}
