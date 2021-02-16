package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;
import com.wade.decompiler.enums.ClassFileAttributes;

public  class PMGClass extends Attribute {
    private int pmgClassIndex;
    private int pmgIndex;

    public PMGClass( int name_index,  int length,  DataInput input,  ConstantPool constant_pool) throws IOException {
        this(name_index, length, input.readUnsignedShort(), input.readUnsignedShort(), constant_pool);
    }

    public PMGClass( int name_index,  int length,  int pmgIndex,  int pmgClassIndex,  ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_PMG, name_index, length, constantPool);
        this.pmgIndex = pmgIndex;
        this.pmgClassIndex = pmgClassIndex;
    }

    public PMGClass( PMGClass pgmClass) {
        this(pgmClass.getNameIndex(), pgmClass.getLength(), pgmClass.getPMGIndex(), pgmClass.getPMGClassIndex(), pgmClass.getConstantPool());
    }

    @Override
    public void accept( Visitor v) {
        println("Visiting non-standard PMGClass object");
    }

    @Override
    public Attribute copy( ConstantPool _constant_pool) {
        return (Attribute) clone();
    }

    @Override
    public void dump( DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(pmgIndex);
        file.writeShort(pmgClassIndex);
    }

    public int getPMGClassIndex() {
        return pmgClassIndex;
    }

    public String getPMGClassName() {
        ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(pmgClassIndex, Const.CONSTANT_Utf8);
        return c.getBytes();
    }

    public int getPMGIndex() {
        return pmgIndex;
    }

    public String getPMGName() {
        ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(pmgIndex, Const.CONSTANT_Utf8);
        return c.getBytes();
    }

    public void setPMGClassIndex( int pmgClassIndex) {
        this.pmgClassIndex = pmgClassIndex;
    }

    public void setPMGIndex( int pmgIndex) {
        this.pmgIndex = pmgIndex;
    }

    @Override
    public String toString() {
        return "PMGClass(" + getPMGName() + ", " + getPMGClassName() + ")";
    }
}
