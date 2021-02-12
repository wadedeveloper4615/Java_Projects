package org.apache.bcel.generic.gen;

import java.util.ArrayList;
import java.util.List;

import org.apache.bcel.Const;
import org.apache.bcel.classfile.AccessFlags;
import org.apache.bcel.classfile.attribute.Attribute;
import org.apache.bcel.generic.Type;
import org.apache.bcel.generic.base.NamedAndTyped;

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

    @Override
    public void setType(final Type type) { // TODO could be package-protected?
        if (type.getType() == Const.T_ADDRESS) {
            throw new IllegalArgumentException("Type can not be " + type);
        }
        this.type = type;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(final String name) { // TODO could be package-protected?
        this.name = name;
    }

    public ConstantPoolGen getConstantPool() {
        return cp;
    }

    public void setConstantPool(final ConstantPoolGen cp) { // TODO could be package-protected?
        this.cp = cp;
    }

    public void addAttribute(final Attribute a) {
        attributeList.add(a);
    }

    public void addAnnotationEntry(final AnnotationEntryGen ag) {
        annotationList.add(ag);
    }

    public void removeAttribute(final Attribute a) {
        attributeList.remove(a);
    }

    public void removeAnnotationEntry(final AnnotationEntryGen ag) {
        annotationList.remove(ag);
    }

    public void removeAttributes() {
        attributeList.clear();
    }

    public void removeAnnotationEntries() {
        annotationList.clear();
    }

    public Attribute[] getAttributes() {
        final Attribute[] attributes = new Attribute[attributeList.size()];
        attributeList.toArray(attributes);
        return attributes;
    }

    public AnnotationEntryGen[] getAnnotationEntries() {
        final AnnotationEntryGen[] annotations = new AnnotationEntryGen[annotationList.size()];
        annotationList.toArray(annotations);
        return annotations;
    }

    public abstract String getSignature();

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (final CloneNotSupportedException e) {
            throw new Error("Clone Not Supported"); // never happens
        }
    }
}
