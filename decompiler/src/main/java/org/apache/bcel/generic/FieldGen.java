package org.apache.bcel.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.bcel.Const;
import org.apache.bcel.classfile.AnnotationEntry;
import org.apache.bcel.classfile.ConstantObject;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.Utility;
import org.apache.bcel.classfile.attribute.Annotations;
import org.apache.bcel.classfile.attribute.Attribute;
import org.apache.bcel.classfile.constant.Constant;
import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.classfile.constant.ConstantValue;
import org.apache.bcel.generic.base.ClassGenException;
import org.apache.bcel.generic.base.FieldObserver;
import org.apache.bcel.generic.gen.AnnotationEntryGen;
import org.apache.bcel.generic.gen.ConstantPoolGen;
import org.apache.bcel.generic.gen.FieldGenOrMethodGen;
import org.apache.bcel.util.BCELComparator;

public class FieldGen extends FieldGenOrMethodGen {
    private Object value = null;
    private static BCELComparator bcelComparator = new BCELComparator() {
        @Override
        public boolean equals(final Object o1, final Object o2) {
            final FieldGen THIS = (FieldGen) o1;
            final FieldGen THAT = (FieldGen) o2;
            return Objects.equals(THIS.getName(), THAT.getName()) && Objects.equals(THIS.getSignature(), THAT.getSignature());
        }

        @Override
        public int hashCode(final Object o) {
            final FieldGen THIS = (FieldGen) o;
            return THIS.getSignature().hashCode() ^ THIS.getName().hashCode();
        }
    };

    public FieldGen(final int access_flags, final Type type, final String name, final ConstantPoolGen cp) {
        super(access_flags);
        setType(type);
        setName(name);
        setConstantPool(cp);
    }

    public FieldGen(final Field field, final ConstantPoolGen cp) {
        this(field.getAccessFlags(), Type.getType(field.getSignature()), field.getName(), cp);
        final Attribute[] attrs = field.getAttributes();
        for (final Attribute attr : attrs) {
            if (attr instanceof ConstantValue) {
                setValue(((ConstantValue) attr).getConstantValueIndex());
            } else if (attr instanceof Annotations) {
                final Annotations runtimeAnnotations = (Annotations) attr;
                final AnnotationEntry[] annotationEntries = runtimeAnnotations.getAnnotationEntries();
                for (final AnnotationEntry element : annotationEntries) {
                    addAnnotationEntry(new AnnotationEntryGen(element, cp, false));
                }
            } else {
                addAttribute(attr);
            }
        }
    }

    private void setValue(final int index) {
        final ConstantPool cp = super.getConstantPool().getConstantPool();
        final Constant c = cp.getConstant(index);
        value = ((ConstantObject) c).getConstantValue(cp);
    }

    public void setInitValue(final String str) {
        checkType(ObjectType.getInstance("java.lang.String"));
        if (str != null) {
            value = str;
        }
    }

    public void setInitValue(final long l) {
        checkType(Type.LONG);
        if (l != 0L) {
            value = Long.valueOf(l);
        }
    }

    public void setInitValue(final int i) {
        checkType(Type.INT);
        if (i != 0) {
            value = Integer.valueOf(i);
        }
    }

    public void setInitValue(final short s) {
        checkType(Type.SHORT);
        if (s != 0) {
            value = Integer.valueOf(s);
        }
    }

    public void setInitValue(final char c) {
        checkType(Type.CHAR);
        if (c != 0) {
            value = Integer.valueOf(c);
        }
    }

    public void setInitValue(final byte b) {
        checkType(Type.BYTE);
        if (b != 0) {
            value = Integer.valueOf(b);
        }
    }

    public void setInitValue(final boolean b) {
        checkType(Type.BOOLEAN);
        if (b) {
            value = Integer.valueOf(1);
        }
    }

    public void setInitValue(final float f) {
        checkType(Type.FLOAT);
        if (f != 0.0) {
            value = new Float(f);
        }
    }

    public void setInitValue(final double d) {
        checkType(Type.DOUBLE);
        if (d != 0.0) {
            value = new Double(d);
        }
    }

    public void cancelInitValue() {
        value = null;
    }

    private void checkType(final Type atype) {
        final Type superType = super.getType();
        if (superType == null) {
            throw new ClassGenException("You haven't defined the type of the field yet");
        }
        if (!isFinal()) {
            throw new ClassGenException("Only final fields may have an initial value!");
        }
        if (!superType.equals(atype)) {
            throw new ClassGenException("Types are not compatible: " + superType + " vs. " + atype);
        }
    }

    public Field getField() {
        final String signature = getSignature();
        final int name_index = super.getConstantPool().addUtf8(super.getName());
        final int signature_index = super.getConstantPool().addUtf8(signature);
        if (value != null) {
            checkType(super.getType());
            final int index = addConstant();
            addAttribute(new ConstantValue(super.getConstantPool().addUtf8("ConstantValue"), 2, index, super.getConstantPool().getConstantPool()));
        }
        addAnnotationsAsAttribute(super.getConstantPool());
        return new Field(super.getAccessFlags(), name_index, signature_index, getAttributes(), super.getConstantPool().getConstantPool());
    }

    private void addAnnotationsAsAttribute(final ConstantPoolGen cp) {
        final Attribute[] attrs = AnnotationEntryGen.getAnnotationAttributes(cp, super.getAnnotationEntries());
        for (final Attribute attr : attrs) {
            addAttribute(attr);
        }
    }

    private int addConstant() {
        switch (super.getType().getType()) {
            case Const.T_INT:
            case Const.T_CHAR:
            case Const.T_BYTE:
            case Const.T_BOOLEAN:
            case Const.T_SHORT:
                return super.getConstantPool().addInteger(((Integer) value).intValue());
            case Const.T_FLOAT:
                return super.getConstantPool().addFloat(((Float) value).floatValue());
            case Const.T_DOUBLE:
                return super.getConstantPool().addDouble(((Double) value).doubleValue());
            case Const.T_LONG:
                return super.getConstantPool().addLong(((Long) value).longValue());
            case Const.T_REFERENCE:
                return super.getConstantPool().addString((String) value);
            default:
                throw new IllegalStateException("Unhandled : " + super.getType().getType());
        }
    }

    @Override
    public String getSignature() {
        return super.getType().getSignature();
    }

    private List<FieldObserver> observers;

    public void addObserver(final FieldObserver o) {
        if (observers == null) {
            observers = new ArrayList<>();
        }
        observers.add(o);
    }

    public void removeObserver(final FieldObserver o) {
        if (observers != null) {
            observers.remove(o);
        }
    }

    public void update() {
        if (observers != null) {
            for (final FieldObserver observer : observers) {
                observer.notify(this);
            }
        }
    }

    public String getInitValue() {
        if (value != null) {
            return value.toString();
        }
        return null;
    }

    @Override
    public final String toString() {
        String name;
        String signature;
        String access;
        access = Utility.accessToString(super.getAccessFlags());
        access = access.isEmpty() ? "" : (access + " ");
        signature = super.getType().toString();
        name = getName();
        final StringBuilder buf = new StringBuilder(32);
        buf.append(access).append(signature).append(" ").append(name);
        final String value = getInitValue();
        if (value != null) {
            buf.append(" = ").append(value);
        }
        return buf.toString();
    }

    public FieldGen copy(final ConstantPoolGen cp) {
        final FieldGen fg = (FieldGen) clone();
        fg.setConstantPool(cp);
        return fg;
    }

    public static BCELComparator getComparator() {
        return bcelComparator;
    }

    public static void setComparator(final BCELComparator comparator) {
        bcelComparator = comparator;
    }

    @Override
    public boolean equals(final Object obj) {
        return bcelComparator.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return bcelComparator.hashCode(this);
    }
}
