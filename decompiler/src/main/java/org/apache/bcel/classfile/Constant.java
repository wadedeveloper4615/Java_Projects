
package org.apache.bcel.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

import org.apache.bcel.Const;
import org.apache.bcel.util.BCELComparator;

public abstract class Constant implements Cloneable, Node {

    private static BCELComparator bcelComparator = new BCELComparator() {

        @Override
        public boolean equals(final Object o1, final Object o2) {
            final Constant THIS = (Constant) o1;
            final Constant THAT = (Constant) o2;
            return Objects.equals(THIS.toString(), THAT.toString());
        }

        @Override
        public int hashCode(final Object o) {
            final Constant THIS = (Constant) o;
            return THIS.toString().hashCode();
        }
    };

    @java.lang.Deprecated
    protected byte tag; // TODO should be private & final

    Constant(final byte tag) {
        this.tag = tag;
    }

    @Override
    public abstract void accept(Visitor v);

    public abstract void dump(DataOutputStream file) throws IOException;

    public final byte getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return Const.getConstantName(tag) + "[" + tag + "]";
    }

    public Constant copy() {
        try {
            return (Constant) super.clone();
        } catch (final CloneNotSupportedException e) {
            // TODO should this throw?
        }
        return null;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (final CloneNotSupportedException e) {
            throw new Error("Clone Not Supported"); // never happens
        }
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

    public static BCELComparator getComparator() {
        return bcelComparator;
    }

    public static void setComparator(final BCELComparator comparator) {
        bcelComparator = comparator;
    }

    @Override
    public boolean equals(final Object obj) {
        return bcelComparator.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return bcelComparator.hashCode(this);
    }
}
