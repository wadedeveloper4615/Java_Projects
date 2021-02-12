package org.apache.bcel.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.classfile.attribute.Attribute;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.classfile.constant.ConstantUtf8;
import org.apache.bcel.enums.ClassFileAttributes;
import org.apache.bcel.enums.ClassFileConstants;

public final class PMGClass extends Attribute {
    private int pmgClassIndex;
    private int pmgIndex;

    public PMGClass(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool) throws IOException {
        this(name_index, length, input.readUnsignedShort(), input.readUnsignedShort(), constant_pool);
    }

    public PMGClass(final int name_index, final int length, final int pmgIndex, final int pmgClassIndex, final ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_PMG, name_index, length, constantPool);
        this.pmgIndex = pmgIndex;
        this.pmgClassIndex = pmgClassIndex;
    }

    public PMGClass(final PMGClass pgmClass) {
        this(pgmClass.getNameIndex(), pgmClass.getLength(), pgmClass.getPMGIndex(), pgmClass.getPMGClassIndex(), pgmClass.getConstantPool());
    }

    @Override
    public void accept(final Visitor v) {
        println("Visiting non-standard PMGClass object");
    }

    @Override
    public Attribute copy(final ConstantPool _constant_pool) {
        return (Attribute) clone();
    }

    @Override
    public void dump(final DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(pmgIndex);
        file.writeShort(pmgClassIndex);
    }

    public int getPMGClassIndex() {
        return pmgClassIndex;
    }

    public String getPMGClassName() {
        final ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(pmgClassIndex, ClassFileConstants.CONSTANT_Utf8);
        return c.getBytes();
    }

    public int getPMGIndex() {
        return pmgIndex;
    }

    public String getPMGName() {
        final ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(pmgIndex, ClassFileConstants.CONSTANT_Utf8);
        return c.getBytes();
    }

    public void setPMGClassIndex(final int pmgClassIndex) {
        this.pmgClassIndex = pmgClassIndex;
    }

    public void setPMGIndex(final int pmgIndex) {
        this.pmgIndex = pmgIndex;
    }

    @Override
    public String toString() {
        return "PMGClass(" + getPMGName() + ", " + getPMGClassName() + ")";
    }
}
