package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.attribute.AnnotationEntry;
import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.attribute.Code;
import com.wade.decompiler.classfile.attribute.Signature;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.ClassFileConstants;

@SuppressWarnings("unused")
public abstract class FieldOrMethod extends ClassAccessFlagsList {
    protected int name_index;
    protected int signature_index;
    protected Attribute[] attributes;
    protected int attributes_count;
    private AnnotationEntry[] annotationEntries;
    protected ConstantPool constant_pool;
    private String signatureAttributeString = null;
    private boolean searchedForSignatureAttribute = false;
    private Code code;

    public FieldOrMethod() {
    }

    protected FieldOrMethod(DataInput file, ConstantPool constant_pool) throws IOException, ClassFormatException {
        this(file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), null, constant_pool);
        int attributes_count = file.readUnsignedShort();
        attributes = new Attribute[attributes_count];
        for (int i = 0; i < attributes_count; i++) {
            attributes[i] = Attribute.readAttribute(file, constant_pool);
            attributes[i].setConstantPool(constant_pool);
            if (attributes[i] instanceof Code) {
                code = (Code) attributes[i];
            }
        }
        this.attributes_count = attributes_count;
    }

    protected FieldOrMethod(DataInputStream file, ConstantPool constant_pool) throws IOException, ClassFormatException {
        this((DataInput) file, constant_pool);
    }

    protected FieldOrMethod(FieldOrMethod c) {
        this(c.getFlags(), c.getNameIndex(), c.getSignatureIndex(), c.getAttributes(), c.getConstantPool());
    }

    protected FieldOrMethod(int access_flags, int name_index, int signature_index, Attribute[] attributes, ConstantPool constant_pool) {
        super(access_flags);
        this.name_index = name_index;
        this.signature_index = signature_index;
        this.constant_pool = constant_pool;
        setAttributes(attributes);
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
        ConstantUtf8 c;
        c = (ConstantUtf8) constant_pool.getConstant(name_index, ClassFileConstants.CONSTANT_Utf8);
        return c.getBytes();
    }

    public int getNameIndex() {
        return name_index;
    }

    public String getSignature() {
        ConstantUtf8 c;
        c = (ConstantUtf8) constant_pool.getConstant(signature_index, ClassFileConstants.CONSTANT_Utf8);
        return c.getBytes();
    }

    public int getSignatureIndex() {
        return signature_index;
    }

    public void setAttributes(Attribute[] attributes) {
        this.attributes = attributes;
        this.attributes_count = attributes != null ? attributes.length : 0; // init deprecated field
    }

    public void setConstantPool(ConstantPool constant_pool) {
        this.constant_pool = constant_pool;
    }

    public void setNameIndex(int name_index) {
        this.name_index = name_index;
    }

    public void setSignatureIndex(int signature_index) {
        this.signature_index = signature_index;
    }
}
