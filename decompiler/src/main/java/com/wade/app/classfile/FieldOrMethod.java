package com.wade.app.classfile;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

import com.wade.app.classfile.attribute.Attribute;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.constantpool.ConstantUtf8;
import com.wade.app.enums.ClassAccessFlags;
import com.wade.app.enums.ClassFileConstants;

@SuppressWarnings("unused")
public class FieldOrMethod {
    private ClassAccessFlags[] accessFlags;
    private int nameIndex;
    private int signatureIndex;
    private Attribute[] attributes;
    private ConstantPool constantPool;
    private int attributesCount;
    private String name;
    private String signature;

    public FieldOrMethod(DataInputStream in, ConstantPool constantPool) throws IOException {
        this(in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort(), null, constantPool);
        this.attributesCount = in.readUnsignedShort();
        attributes = new Attribute[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            attributes[i] = Attribute.readAttribute(in, constantPool);
        }
    }

    protected FieldOrMethod(int accessFlags, int nameIndex, int signatureIndex, Attribute[] attributes, ConstantPool constantPool) throws IOException {
        this.accessFlags = ClassAccessFlags.read(accessFlags);
        this.nameIndex = nameIndex;
        this.signatureIndex = signatureIndex;
        this.attributes = attributes;
        this.constantPool = constantPool;
        this.name = ((ConstantUtf8) constantPool.getConstant(nameIndex, ClassFileConstants.CONSTANT_Utf8)).getBytes();
        this.signature = ((ConstantUtf8) constantPool.getConstant(signatureIndex, ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }

    public ClassAccessFlags[] getAccessFlags() {
        return accessFlags;
    }

    public Attribute[] getAttributes() {
        return attributes;
    }

    public String getName() {
        return name;
    }

    public String getSignature() {
        return signature;
    }

    @Override
    public String toString() {
        return "\n\t\taccessFlags=" + Arrays.toString(accessFlags) + ", name=" + name + ", signature=" + signature + ", attributes=" + Arrays.toString(attributes);
    }
}
