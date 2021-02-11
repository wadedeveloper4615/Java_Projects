
package org.apache.bcel.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Const;

public abstract class ConstantCP extends Constant {

    // Note that this field is used to store the
    // bootstrap_method_attr_index of a ConstantInvokeDynamic.

    @java.lang.Deprecated
    protected int class_index; // TODO make private (has getter & setter)
    // This field has the same meaning for all subclasses.

    @java.lang.Deprecated
    protected int name_and_type_index; // TODO make private (has getter & setter)

    public ConstantCP(final ConstantCP c) {
        this(c.getTag(), c.getClassIndex(), c.getNameAndTypeIndex());
    }

    ConstantCP(final byte tag, final DataInput file) throws IOException {
        this(tag, file.readUnsignedShort(), file.readUnsignedShort());
    }

    protected ConstantCP(final byte tag, final int class_index, final int name_and_type_index) {
        super(tag);
        this.class_index = class_index;
        this.name_and_type_index = name_and_type_index;
    }

    @Override
    public final void dump(final DataOutputStream file) throws IOException {
        file.writeByte(super.getTag());
        file.writeShort(class_index);
        file.writeShort(name_and_type_index);
    }

    public final int getClassIndex() {
        return class_index;
    }

    public final void setClassIndex(final int class_index) {
        this.class_index = class_index;
    }

    public final int getNameAndTypeIndex() {
        return name_and_type_index;
    }

    public final void setNameAndTypeIndex(final int name_and_type_index) {
        this.name_and_type_index = name_and_type_index;
    }

    public String getClass(final ConstantPool cp) {
        return cp.constantToString(class_index, Const.CONSTANT_Class);
    }

    @Override
    public String toString() {
        return super.toString() + "(class_index = " + class_index + ", name_and_type_index = " + name_and_type_index + ")";
    }
}
