package com.wade.decompiler.generic;

import java.util.ArrayList;
import java.util.List;

import com.wade.decompiler.Const;
import com.wade.decompiler.classfile.AccessFlags;
import com.wade.decompiler.classfile.Attribute;

public abstract class FieldGenOrMethodGen extends AccessFlags implements NamedAndTyped, Cloneable {
    @Deprecated
    protected String name;
    @Deprecated
    protected Type type;
    @Deprecated
    protected ConstantPoolGen cp;
    private final List<Attribute> attributeList = new ArrayList<>();
    // @since 6.0
    private final List<AnnotationEntryGen> annotationList = new ArrayList<>();

    protected FieldGenOrMethodGen() {
    }

    protected FieldGenOrMethodGen(final int access_flags) { // TODO could this be package protected?
        super(access_flags);
    }

    public void addAnnotationEntry(final AnnotationEntryGen ag) {
        annotationList.add(ag);
    }

    public void addAttribute(final Attribute a) {
        attributeList.add(a);
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (final CloneNotSupportedException e) {
            throw new Error("Clone Not Supported"); // never happens
        }
    }

    public AnnotationEntryGen[] getAnnotationEntries() {
        final AnnotationEntryGen[] annotations = new AnnotationEntryGen[annotationList.size()];
        annotationList.toArray(annotations);
        return annotations;
    }

    public Attribute[] getAttributes() {
        final Attribute[] attributes = new Attribute[attributeList.size()];
        attributeList.toArray(attributes);
        return attributes;
    }

    public ConstantPoolGen getConstantPool() {
        return cp;
    }

    @Override
    public String getName() {
        return name;
    }

    public abstract String getSignature();

    @Override
    public Type getType() {
        return type;
    }

    public void removeAnnotationEntries() {
        annotationList.clear();
    }

    public void removeAnnotationEntry(final AnnotationEntryGen ag) {
        annotationList.remove(ag);
    }

    public void removeAttribute(final Attribute a) {
        attributeList.remove(a);
    }

    public void removeAttributes() {
        attributeList.clear();
    }

    public void setConstantPool(final ConstantPoolGen cp) { // TODO could be package-protected?
        this.cp = cp;
    }

    @Override
    public void setName(final String name) { // TODO could be package-protected?
        this.name = name;
    }

    @Override
    public void setType(final Type type) { // TODO could be package-protected?
        if (type.getType() == Const.T_ADDRESS) {
            throw new IllegalArgumentException("Type can not be " + type);
        }
        this.type = type;
    }
}
