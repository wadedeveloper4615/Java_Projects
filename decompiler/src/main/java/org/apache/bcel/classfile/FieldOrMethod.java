package org.apache.bcel.classfile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Const;

public abstract class FieldOrMethod extends AccessFlags implements Cloneable, Node {
    protected int nameIndex;
    protected int signatureIndex;
    protected Attribute[] attributes;
    protected int attributesCount;
    private AnnotationEntry[] annotationEntries;
    protected ConstantPool constant_pool;
    private String signatureAttributeString = null;
    private boolean searchedForSignatureAttribute = false;
    private String signature;
    private String name;

    public FieldOrMethod() {
    }

    protected FieldOrMethod(DataInputStream file, ConstantPool constant_pool) throws IOException, ClassFormatException {
        this(file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), null, constant_pool);
        int attributesCount = file.readUnsignedShort();
        attributes = new Attribute[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            attributes[i] = Attribute.readAttribute(file, constant_pool);
        }
        this.attributesCount = attributesCount;
    }

    protected FieldOrMethod(FieldOrMethod c) {
        this(c.getAccessFlags(), c.getNameIndex(), c.getSignatureIndex(), c.getAttributes(), c.getConstantPool());
    }

    protected FieldOrMethod(int access_flags, int name_index, int signature_index, Attribute[] attributes, ConstantPool constant_pool) {
        super(access_flags);
        this.nameIndex = name_index;
        this.signatureIndex = signature_index;
        this.constant_pool = constant_pool;
        setAttributes(attributes);
        this.signature = ((ConstantUtf8) constant_pool.getConstant(signature_index, Const.CONSTANT_Utf8)).getBytes();
        this.name = ((ConstantUtf8) constant_pool.getConstant(nameIndex, Const.CONSTANT_Utf8)).getBytes();
    }

    protected FieldOrMethod copy_(ConstantPool _constant_pool) {
        FieldOrMethod c = null;

        try {
            c = (FieldOrMethod) clone();
        } catch (CloneNotSupportedException e) {
        }

        c.constant_pool = constant_pool;
        c.attributes = new Attribute[attributes.length];
        c.attributesCount = attributesCount; // init deprecated field

        for (int i = 0; i < attributes.length; i++) {
            c.attributes[i] = attributes[i].copy(constant_pool);
        }

        return c;
    }

    public void dump(DataOutputStream file) throws IOException {
        file.writeShort(super.getAccessFlags());
        file.writeShort(nameIndex);
        file.writeShort(signatureIndex);
        file.writeShort(attributesCount);
        if (attributes != null) {
            for (Attribute attribute : attributes) {
                attribute.dump(file);
            }
        }
    }

    public AnnotationEntry[] getAnnotationEntries() {
        if (annotationEntries == null) {
            annotationEntries = AnnotationEntry.createAnnotationEntries(getAttributes());
        }

        return annotationEntries;
    }

    public Attribute[] getAttributes() {
        return attributes;
    }

    public ConstantPool getConstantPool() {
        return constant_pool;
    }

    public String getGenericSignature() {
        if (!searchedForSignatureAttribute) {
            boolean found = false;
            for (int i = 0; !found && i < attributes.length; i++) {
                if (attributes[i] instanceof Signature) {
                    signatureAttributeString = ((Signature) attributes[i]).getSignature();
                    found = true;
                }
            }
            searchedForSignatureAttribute = true;
        }
        return signatureAttributeString;
    }

    public String getName() {
        return name;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public String getSignature() {
        return signature;
    }

    public int getSignatureIndex() {
        return signatureIndex;
    }

    public void setAttributes(Attribute[] attributes) {
        this.attributes = attributes;
        this.attributesCount = attributes != null ? attributes.length : 0;
    }

    public void setConstantPool(ConstantPool constant_pool) {
        this.constant_pool = constant_pool;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public void setSignatureIndex(int signatureIndex) {
        this.signatureIndex = signatureIndex;
    }
}
