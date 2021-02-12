package org.apache.bcel.classfile.constant;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.classfile.Node;
import org.apache.bcel.classfile.Visitor;
import org.apache.bcel.compare.ConstantComparator;
import org.apache.bcel.enums.ClassFileConstants;
import org.apache.bcel.exceptions.ClassFormatException;
import org.apache.bcel.util.BCELComparator;

public abstract class Constant implements Cloneable, Node {
    private static BCELComparator bcelComparator = new ConstantComparator();
    protected ClassFileConstants tag;

    public Constant(ClassFileConstants tag) {
        this.tag = tag;
    }

    @Override
    public abstract void accept(Visitor v);

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (final CloneNotSupportedException e) {
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

    public ClassFileConstants getTag() {
        return tag;
    }

    @Override
    public int hashCode() {
        return bcelComparator.hashCode(this);
    }

    @Override
    public String toString() {
        return tag.getName() + "[" + tag + "]";
    }

    public static BCELComparator getComparator() {
        return bcelComparator;
    }

    public static Constant readConstant(final DataInput dataInput) throws IOException, ClassFormatException {
        ClassFileConstants b = ClassFileConstants.read(dataInput.readByte());
        switch (b) {
            case CONSTANT_Class:
                return new ConstantClass(dataInput);
            case CONSTANT_Fieldref:
                return new ConstantFieldref(dataInput);
            case CONSTANT_Methodref:
                return new ConstantMethodref(dataInput);
            case CONSTANT_InterfaceMethodref:
                return new ConstantInterfaceMethodref(dataInput);
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
                return ConstantUtf8.getInstance(dataInput);
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
                throw new ClassFormatException("Invalid byte tag in constant pool: " + b);
        }
    }

    public static void setComparator(final BCELComparator comparator) {
        bcelComparator = comparator;
    }
}
