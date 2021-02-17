package com.wade.decompiler.generic.gen;

import java.util.ArrayList;
import java.util.List;

import com.wade.decompiler.Const;
import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.generic.base.NamedAndTyped;
import com.wade.decompiler.generic.type.Type;

public abstract class FieldGenOrMethodGen extends ClassAccessFlagsList implements NamedAndTyped, Cloneable {
    @Deprecated
    protected String name;
    @Deprecated
    protected Type type;
    @Deprecated
    protected ConstantPoolGen cp;
    private List<Attribute> attributeList = new ArrayList<>();
    // @since 6.0
    private List<AnnotationEntryGen> annotationList = new ArrayList<>();

    protected FieldGenOrMethodGen() {
    }

    protected FieldGenOrMethodGen(int access_flags) { // TODO could this be package protected?
        super(access_flags);
    }

    public void addAnnotationEntry(AnnotationEntryGen ag) {
        annotationList.add(ag);
    }

    public void addAttribute(Attribute a) {
        attributeList.add(a);
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error("Clone Not Supported"); // never happens
        }
    }

    public AnnotationEntryGen[] getAnnotationEntries() {
        AnnotationEntryGen[] annotations = new AnnotationEntryGen[annotationList.size()];
        annotationList.toArray(annotations);
        return annotations;
    }

    public Attribute[] getAttributes() {
        Attribute[] attributes = new Attribute[attributeList.size()];
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

    public void removeAnnotationEntry(AnnotationEntryGen ag) {
        annotationList.remove(ag);
    }

    public void removeAttribute(Attribute a) {
        attributeList.remove(a);
    }

    public void removeAttributes() {
        attributeList.clear();
    }

    public void setConstantPool(ConstantPoolGen cp) { // TODO could be package-protected?
        this.cp = cp;
    }

    @Override
    public void setName(String name) { // TODO could be package-protected?
        this.name = name;
    }

    @Override
    public void setType(Type type) { // TODO could be package-protected?
        if (type.getType() == Const.T_ADDRESS) {
            throw new IllegalArgumentException("Type can not be " + type);
        }
        this.type = type;
    }
}
