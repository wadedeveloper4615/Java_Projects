package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;

public abstract class FieldOrMethod extends ClassAccessFlagsList implements Cloneable, Node {
    @java.lang.Deprecated
    protected int name_index; // Points to field name in constant pool
    @java.lang.Deprecated
    protected int signature_index; // Points to encoded signature
    @java.lang.Deprecated
    protected Attribute[] attributes; // Collection of attributes
    @java.lang.Deprecated
    protected int attributes_count; // No. of attributes
    // @since 6.0
    private AnnotationEntry[] annotationEntries; // annotations defined on the field or method
    @java.lang.Deprecated
    protected ConstantPool constant_pool;
    private String signatureAttributeString = null;
    private boolean searchedForSignatureAttribute = false;

    FieldOrMethod() {
    }

    protected FieldOrMethod(final DataInput file, final ConstantPool constant_pool) throws IOException, ClassFormatException {
        this(file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), null, constant_pool);
        final int attributes_count = file.readUnsignedShort();
        attributes = new Attribute[attributes_count];
        for (int i = 0; i < attributes_count; i++) {
            attributes[i] = Attribute.readAttribute(file, constant_pool);
        }
        this.attributes_count = attributes_count; // init deprecated field
    }

    @java.lang.Deprecated
    protected FieldOrMethod(final DataInputStream file, final ConstantPool constant_pool) throws IOException, ClassFormatException {
        this((DataInput) file, constant_pool);
    }

    protected FieldOrMethod(final FieldOrMethod c) {
        this(c.getFlags(), c.getNameIndex(), c.getSignatureIndex(), c.getAttributes(), c.getConstantPool());
    }

    protected FieldOrMethod(final int access_flags, final int name_index, final int signature_index, final Attribute[] attributes, final ConstantPool constant_pool) {
        super(access_flags);
        this.name_index = name_index;
        this.signature_index = signature_index;
        this.constant_pool = constant_pool;
        setAttributes(attributes);
    }

    protected FieldOrMethod copy_(final ConstantPool _constant_pool) {
        FieldOrMethod c = null;
        try {
            c = (FieldOrMethod) clone();
        } catch (final CloneNotSupportedException e) {
            // ignored, but will cause NPE ...
        }
        c.constant_pool = constant_pool;
        c.attributes = new Attribute[attributes.length];
        c.attributes_count = attributes_count; // init deprecated field
        for (int i = 0; i < attributes.length; i++) {
            c.attributes[i] = attributes[i].copy(constant_pool);
        }
        return c;
    }

    public final void dump(final DataOutputStream file) throws IOException {
        file.writeShort(super.getFlags());
        file.writeShort(name_index);
        file.writeShort(signature_index);
        file.writeShort(attributes_count);
        if (attributes != null) {
            for (final Attribute attribute : attributes) {
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

    public final Attribute[] getAttributes() {
        return attributes;
    }

    public final ConstantPool getConstantPool() {
        return constant_pool;
    }

    public final String getGenericSignature() {
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

    public final String getName() {
        ConstantUtf8 c;
        c = (ConstantUtf8) constant_pool.getConstant(name_index, Const.CONSTANT_Utf8);
        return c.getBytes();
    }

    public final int getNameIndex() {
        return name_index;
    }

    public final String getSignature() {
        ConstantUtf8 c;
        c = (ConstantUtf8) constant_pool.getConstant(signature_index, Const.CONSTANT_Utf8);
        return c.getBytes();
    }

    public final int getSignatureIndex() {
        return signature_index;
    }

    public final void setAttributes(final Attribute[] attributes) {
        this.attributes = attributes;
        this.attributes_count = attributes != null ? attributes.length : 0; // init deprecated field
    }

    public final void setConstantPool(final ConstantPool constant_pool) {
        this.constant_pool = constant_pool;
    }

    public final void setNameIndex(final int name_index) {
        this.name_index = name_index;
    }

    public final void setSignatureIndex(final int signature_index) {
        this.signature_index = signature_index;
    }
}
