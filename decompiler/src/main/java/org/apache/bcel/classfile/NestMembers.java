package org.apache.bcel.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Const;
import org.apache.bcel.classfile.attribute.Attribute;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.enums.ClassFileConstants;

public final class NestMembers extends Attribute {
    private int[] classes;

    public NestMembers(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool) throws IOException {
        this(name_index, length, (int[]) null, constant_pool);
        final int number_of_classes = input.readUnsignedShort();
        classes = new int[number_of_classes];
        for (int i = 0; i < number_of_classes; i++) {
            classes[i] = input.readUnsignedShort();
        }
    }

    public NestMembers(final int name_index, final int length, final int[] classes, final ConstantPool constant_pool) {
        super(Const.ATTR_NEST_MEMBERS, name_index, length, constant_pool);
        this.classes = classes != null ? classes : new int[0];
    }

    public NestMembers(final NestMembers c) {
        this(c.getNameIndex(), c.getLength(), c.getClasses(), c.getConstantPool());
    }

    @Override
    public void accept(final Visitor v) {
        v.visitNestMembers(this);
    }

    @Override
    public Attribute copy(final ConstantPool _constant_pool) {
        final NestMembers c = (NestMembers) clone();
        if (classes != null) {
            c.classes = new int[classes.length];
            System.arraycopy(classes, 0, c.classes, 0, classes.length);
        }
        c.setConstantPool(_constant_pool);
        return c;
    }

    @Override
    public void dump(final DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(classes.length);
        for (final int index : classes) {
            file.writeShort(index);
        }
    }

    public int[] getClasses() {
        return classes;
    }

    public String[] getClassNames() {
        final String[] names = new String[classes.length];
        for (int i = 0; i < classes.length; i++) {
            names[i] = super.getConstantPool().getConstantString(classes[i], ClassFileConstants.CONSTANT_Class).replace('/', '.');
        }
        return names;
    }

    public int getNumberClasses() {
        return classes == null ? 0 : classes.length;
    }

    public void setClasses(final int[] classes) {
        this.classes = classes != null ? classes : new int[0];
    }

    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder();
        buf.append("NestMembers(");
        buf.append(classes.length);
        buf.append("):\n");
        for (final int index : classes) {
            final String class_name = super.getConstantPool().getConstantString(index, ClassFileConstants.CONSTANT_Class);
            buf.append("  ").append(Utility.compactClassName(class_name, false)).append("\n");
        }
        return buf.substring(0, buf.length() - 1); // remove the last newline
    }
}
