package com.wade.app.classfile;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.AccessFlags;
import com.wade.app.ClassFileConstants;
import com.wade.app.attribute.AnnotationEntry;
import com.wade.app.attribute.Attribute;
import com.wade.app.attribute.Signature;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.constantpool.ConstantUtf8;
import com.wade.app.constantpool.Node;
import com.wade.app.exception.ClassFormatException;

public abstract class FieldOrMethod extends AbstractAccessFlags implements Node {
    protected int name_index;
    protected int signature_index;
    protected Attribute[] attributes;
    protected int attributes_count;
    private AnnotationEntry[] annotationEntries;
    protected ConstantPool constant_pool;
    private String signatureAttributeString = null;
    private boolean searchedForSignatureAttribute = false;

    public FieldOrMethod() {
    }

    protected FieldOrMethod(final DataInputStream file, final ConstantPool constant_pool) throws IOException, ClassFormatException {
        this(file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), null, constant_pool);
        final int attributes_count = file.readUnsignedShort();
        attributes = new Attribute[attributes_count];
        for (int i = 0; i < attributes_count; i++) {
            attributes[i] = Attribute.readAttribute(file, constant_pool);
        }
        this.attributes_count = attributes_count;
    }

    protected FieldOrMethod(final FieldOrMethod c) {
        this(c.getAccessFlags().getFlag(), c.getNameIndex(), c.getSignatureIndex(), c.getAttributes(), c.getConstantPool());
    }

    protected FieldOrMethod(int access_flags, final int name_index, final int signature_index, final Attribute[] attributes, final ConstantPool constant_pool) {
        super(AccessFlags.ACC_DUMMY.setFlag(access_flags));
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

    public final Attribute[] getAttributes() {
        return attributes;
    }

    public int getAttributes_count() {
        return attributes_count;
    }

    public ConstantPool getConstant_pool() {
        return constant_pool;
    }

    public final ConstantPool getConstantPool() {
        return constant_pool;
    }

    public final String getGenericSignature() throws ClassFormatException {
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

    public final String getName() throws ClassFormatException {
        ConstantUtf8 c;
        c = (ConstantUtf8) constant_pool.getConstant(name_index, ClassFileConstants.CONSTANT_Utf8);
        return c.getBytes();
    }

    public int getName_index() {
        return name_index;
    }

    public final int getNameIndex() {
        return name_index;
    }

    public final String getSignature() throws ClassFormatException {
        ConstantUtf8 c;
        c = (ConstantUtf8) constant_pool.getConstant(signature_index, ClassFileConstants.CONSTANT_Utf8);
        return c.getBytes();
    }

    public int getSignature_index() {
        return signature_index;
    }

    public String getSignatureAttributeString() {
        return signatureAttributeString;
    }

    public final int getSignatureIndex() {
        return signature_index;
    }

    public boolean isSearchedForSignatureAttribute() {
        return searchedForSignatureAttribute;
    }

    public final void setAttributes(final Attribute[] attributes) {
        this.attributes = attributes;
        this.attributes_count = attributes != null ? attributes.length : 0; // init deprecated field
    }
}
