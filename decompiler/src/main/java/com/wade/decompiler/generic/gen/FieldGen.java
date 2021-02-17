package com.wade.decompiler.generic.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.wade.decompiler.Const;
import com.wade.decompiler.classfile.AnnotationEntry;
import com.wade.decompiler.classfile.Annotations;
import com.wade.decompiler.classfile.Attribute;
import com.wade.decompiler.classfile.Constant;
import com.wade.decompiler.classfile.ConstantObject;
import com.wade.decompiler.classfile.ConstantPool;
import com.wade.decompiler.classfile.ConstantValue;
import com.wade.decompiler.classfile.Field;
import com.wade.decompiler.classfile.Utility;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.generic.type.ObjectType;
import com.wade.decompiler.generic.type.Type;
import com.wade.decompiler.util.BCELComparator;

public class FieldGen extends FieldGenOrMethodGen {
    private static BCELComparator bcelComparator = new BCELComparator() {
        @Override
        public boolean equals(Object o1, Object o2) {
            FieldGen THIS = (FieldGen) o1;
            FieldGen THAT = (FieldGen) o2;
            return Objects.equals(THIS.getName(), THAT.getName()) && Objects.equals(THIS.getSignature(), THAT.getSignature());
        }

        @Override
        public int hashCode(Object o) {
            FieldGen THIS = (FieldGen) o;
            return THIS.getSignature().hashCode() ^ THIS.getName().hashCode();
        }
    };
    private Object value = null;
    private List<FieldObserver> observers;

    public FieldGen(Field field, ConstantPoolGen cp) {
        this(field.getFlags(), Type.getType(field.getSignature()), field.getName(), cp);
        Attribute[] attrs = field.getAttributes();
        for (Attribute attr : attrs) {
            if (attr instanceof ConstantValue) {
                setValue(((ConstantValue) attr).getConstantValueIndex());
            } else if (attr instanceof Annotations) {
                Annotations runtimeAnnotations = (Annotations) attr;
                AnnotationEntry[] annotationEntries = runtimeAnnotations.getAnnotationEntries();
                for (AnnotationEntry element : annotationEntries) {
                    addAnnotationEntry(new AnnotationEntryGen(element, cp, false));
                }
            } else {
                addAttribute(attr);
            }
        }
    }

    public FieldGen(int access_flags, Type type, String name, ConstantPoolGen cp) {
        super(access_flags);
        setType(type);
        setName(name);
        setConstantPool(cp);
    }

    private void addAnnotationsAsAttribute(ConstantPoolGen cp) {
        Attribute[] attrs = AnnotationEntryGen.getAnnotationAttributes(cp, super.getAnnotationEntries());
        for (Attribute attr : attrs) {
            addAttribute(attr);
        }
    }

    private int addConstant() {
        switch (super.getType().getType()) { // sic
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
                throw new IllegalStateException("Unhandled : " + super.getType().getType()); // sic
        }
    }

    public void addObserver(FieldObserver o) {
        if (observers == null) {
            observers = new ArrayList<>();
        }
        observers.add(o);
    }

    public void cancelInitValue() {
        value = null;
    }

    private void checkType(Type atype) {
        Type superType = super.getType();
        if (superType == null) {
            throw new ClassGenException("You haven't defined the type of the field yet");
        }
        if (!isFinal()) {
            throw new ClassGenException("Only  fields may have an initial value!");
        }
        if (!superType.equals(atype)) {
            throw new ClassGenException("Types are not compatible: " + superType + " vs. " + atype);
        }
    }

    public FieldGen copy(ConstantPoolGen cp) {
        FieldGen fg = (FieldGen) clone();
        fg.setConstantPool(cp);
        return fg;
    }

    @Override
    public boolean equals(Object obj) {
        return bcelComparator.equals(this, obj);
    }

    public Field getField() {
        String signature = getSignature();
        int name_index = super.getConstantPool().addUtf8(super.getName());
        int signature_index = super.getConstantPool().addUtf8(signature);
        if (value != null) {
            checkType(super.getType());
            int index = addConstant();
            addAttribute(new ConstantValue(super.getConstantPool().addUtf8("ConstantValue"), 2, index, super.getConstantPool().getConstantPool())); // sic
        }
        addAnnotationsAsAttribute(super.getConstantPool());
        return new Field(super.getFlags(), name_index, signature_index, getAttributes(), super.getConstantPool().getConstantPool()); // sic
    }

    public String getInitValue() {
        if (value != null) {
            return value.toString();
        }
        return null;
    }

    @Override
    public String getSignature() {
        return super.getType().getSignature();
    }

    @Override
    public int hashCode() {
        return bcelComparator.hashCode(this);
    }

    public void removeObserver(FieldObserver o) {
        if (observers != null) {
            observers.remove(o);
        }
    }

    public void setInitValue(boolean b) {
        checkType(Type.BOOLEAN);
        if (b) {
            value = Integer.valueOf(1);
        }
    }

    public void setInitValue(byte b) {
        checkType(Type.BYTE);
        if (b != 0) {
            value = Integer.valueOf(b);
        }
    }

    public void setInitValue(char c) {
        checkType(Type.CHAR);
        if (c != 0) {
            value = Integer.valueOf(c);
        }
    }

    public void setInitValue(double d) {
        checkType(Type.DOUBLE);
        if (d != 0.0) {
            value = Double.valueOf(d);
        }
    }

    public void setInitValue(float f) {
        checkType(Type.FLOAT);
        if (f != 0.0) {
            value = Float.valueOf(f);
        }
    }

    public void setInitValue(int i) {
        checkType(Type.INT);
        if (i != 0) {
            value = Integer.valueOf(i);
        }
    }

    public void setInitValue(long l) {
        checkType(Type.LONG);
        if (l != 0L) {
            value = Long.valueOf(l);
        }
    }

    public void setInitValue(short s) {
        checkType(Type.SHORT);
        if (s != 0) {
            value = Integer.valueOf(s);
        }
    }

    public void setInitValue(String str) {
        checkType(ObjectType.getInstance("java.lang.String"));
        if (str != null) {
            value = str;
        }
    }

    private void setValue(int index) {
        ConstantPool cp = super.getConstantPool().getConstantPool();
        Constant c = cp.getConstant(index);
        value = ((ConstantObject) c).getConstantValue(cp);
    }

    @Override
    public String toString() {
        String name;
        String signature;
        String access; // Short cuts to constant pool
        access = Utility.accessToString(new ClassAccessFlagsList(super.getFlags()));
        access = access.isEmpty() ? "" : (access + " ");
        signature = super.getType().toString();
        name = getName();
        StringBuilder buf = new StringBuilder(32); // CHECKSTYLE IGNORE MagicNumber
        buf.append(access).append(signature).append(" ").append(name);
        String value = getInitValue();
        if (value != null) {
            buf.append(" = ").append(value);
        }
        return buf.toString();
    }

    public void update() {
        if (observers != null) {
            for (FieldObserver observer : observers) {
                observer.notify(this);
            }
        }
    }

    public static BCELComparator getComparator() {
        return bcelComparator;
    }

    public static void setComparator(BCELComparator comparator) {
        bcelComparator = comparator;
    }
}
