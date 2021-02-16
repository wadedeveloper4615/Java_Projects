package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;
import com.wade.decompiler.comparators.ConstantComparator;
import com.wade.decompiler.util.BCELComparator;

public abstract class Constant implements Cloneable, Node {
    private static BCELComparator bcelComparator = new ConstantComparator();
    protected byte tag;

    public Constant(byte tag) {
        this.tag = tag;
    }

    @Override
    public abstract void accept(Visitor v);

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error("Clone Not Supported");
        }
    }

    public Constant copy() {
        try {
            return (Constant) super.clone();
        } catch (final CloneNotSupportedException e) {
        }
        return null;
    }

    public abstract void dump(DataOutputStream file) throws IOException;

    @Override
    public boolean equals(final Object obj) {
        return bcelComparator.equals(this, obj);
    }

    public byte getTag() {
        return tag;
    }

    @Override
    public int hashCode() {
        return bcelComparator.hashCode(this);
    }

    @Override
    public String toString() {
        return Const.getConstantName(tag) + "[" + tag + "]";
    }

    public static BCELComparator getComparator() {
        return bcelComparator;
    }

    public static Constant readConstant(final DataInput dataInput) throws IOException, ClassFormatException {
        final byte b = dataInput.readByte(); // Read tag byte
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

    public static void setComparator(final BCELComparator comparator) {
        bcelComparator = comparator;
    }
}
