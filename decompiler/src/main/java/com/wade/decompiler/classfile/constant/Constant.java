package com.wade.decompiler.classfile.constant;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.exceptions.ClassFormatException;
import com.wade.decompiler.enums.ClassFileConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public abstract class Constant {
    protected ClassFileConstants tag;

    public Constant(ClassFileConstants tag) {
        this.tag = tag;
    }

    public static Constant readConstant(DataInput dataInput) throws IOException, ClassFormatException {
        byte tag = dataInput.readByte();
        return readConstant(dataInput, tag);
    }

    public static Constant readConstant(DataInput dataInput, byte tag) throws IOException {
        ClassFileConstants constant = ClassFileConstants.read(tag);
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
