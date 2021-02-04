package com.wade.app;

import com.wade.app.exception.ClassFormatException;

public abstract class ReferenceType extends Type {
    public ReferenceType() {
        super(Const.T_OBJECT, "<null object>");
    }

    protected ReferenceType(final byte t, final String s) {
        super(t, s);
    }

    public ReferenceType firstCommonSuperclass(final ReferenceType t) throws ClassNotFoundException, ClassFormatException {
        if (this.equals(Type.NULL)) {
            return t;
        }
        if (t.equals(Type.NULL)) {
            return this;
        }
        if (this.equals(t)) {
            return this;
        }
        if ((this instanceof ArrayType) || (t instanceof ArrayType)) {
            return Type.OBJECT;
        }
        if (((this instanceof ObjectType) && ((ObjectType) this).referencesInterface()) || ((t instanceof ObjectType) && ((ObjectType) t).referencesInterface())) {
            return Type.OBJECT;
        }
        final ObjectType thiz = (ObjectType) this;
        final ObjectType other = (ObjectType) t;
        final JavaClass[] thiz_sups = Repository.getSuperClasses(thiz.getClassName());
        final JavaClass[] other_sups = Repository.getSuperClasses(other.getClassName());
        if ((thiz_sups == null) || (other_sups == null)) {
            return null;
        }
        // Waaahh...
        final JavaClass[] this_sups = new JavaClass[thiz_sups.length + 1];
        final JavaClass[] t_sups = new JavaClass[other_sups.length + 1];
        System.arraycopy(thiz_sups, 0, this_sups, 1, thiz_sups.length);
        System.arraycopy(other_sups, 0, t_sups, 1, other_sups.length);
        this_sups[0] = Repository.lookupClass(thiz.getClassName());
        t_sups[0] = Repository.lookupClass(other.getClassName());
        for (final JavaClass t_sup : t_sups) {
            for (final JavaClass this_sup : this_sups) {
                if (this_sup.equals(t_sup)) {
                    return ObjectType.getInstance(this_sup.getClassName());
                }
            }
        }
        // Huh? Did you ask for Type.OBJECT's superclass??
        return null;
    }

    public ReferenceType getFirstCommonSuperclass(final ReferenceType t) throws ClassNotFoundException, ClassFormatException {
        if (this.equals(Type.NULL)) {
            return t;
        }
        if (t.equals(Type.NULL)) {
            return this;
        }
        if (this.equals(t)) {
            return this;
        }
        if ((this instanceof ArrayType) && (t instanceof ArrayType)) {
            final ArrayType arrType1 = (ArrayType) this;
            final ArrayType arrType2 = (ArrayType) t;
            if ((arrType1.getDimensions() == arrType2.getDimensions()) && arrType1.getBasicType() instanceof ObjectType && arrType2.getBasicType() instanceof ObjectType) {
                return new ArrayType(((ObjectType) arrType1.getBasicType()).getFirstCommonSuperclass((ObjectType) arrType2.getBasicType()), arrType1.getDimensions());
            }
        }
        if ((this instanceof ArrayType) || (t instanceof ArrayType)) {
            return Type.OBJECT;
        }
        if (((this instanceof ObjectType) && ((ObjectType) this).referencesInterfaceExact()) || ((t instanceof ObjectType) && ((ObjectType) t).referencesInterfaceExact())) {
            return Type.OBJECT;
        }
        // this and t are ObjectTypes, see above.
        final ObjectType thiz = (ObjectType) this;
        final ObjectType other = (ObjectType) t;
        final JavaClass[] thiz_sups = Repository.getSuperClasses(thiz.getClassName());
        final JavaClass[] other_sups = Repository.getSuperClasses(other.getClassName());
        if ((thiz_sups == null) || (other_sups == null)) {
            return null;
        }
        // Waaahh...
        final JavaClass[] this_sups = new JavaClass[thiz_sups.length + 1];
        final JavaClass[] t_sups = new JavaClass[other_sups.length + 1];
        System.arraycopy(thiz_sups, 0, this_sups, 1, thiz_sups.length);
        System.arraycopy(other_sups, 0, t_sups, 1, other_sups.length);
        this_sups[0] = Repository.lookupClass(thiz.getClassName());
        t_sups[0] = Repository.lookupClass(other.getClassName());
        for (final JavaClass t_sup : t_sups) {
            for (final JavaClass this_sup : this_sups) {
                if (this_sup.equals(t_sup)) {
                    return ObjectType.getInstance(this_sup.getClassName());
                }
            }
        }
        // Huh? Did you ask for Type.OBJECT's superclass??
        return null;
    }

    public boolean isAssignmentCompatibleWith(final Type t) throws ClassNotFoundException, ClassFormatException {
        if (!(t instanceof ReferenceType)) {
            return false;
        }
        final ReferenceType T = (ReferenceType) t;
        if (this.equals(Type.NULL)) {
            return true; // This is not explicitely stated, but clear. Isn't it?
        }
        /*
         * If this is a class type then
         */
        if ((this instanceof ObjectType) && (((ObjectType) this).referencesClassExact())) {
            /*
             * If T is a class type, then this must be the same class as T, or this must be
             * a subclass of T;
             */
            if ((T instanceof ObjectType) && (((ObjectType) T).referencesClassExact())) {
                if (this.equals(T)) {
                    return true;
                }
                if (Repository.instanceOf(((ObjectType) this).getClassName(), ((ObjectType) T).getClassName())) {
                    return true;
                }
            }
            /*
             * If T is an interface type, this must implement interface T.
             */
            if ((T instanceof ObjectType) && (((ObjectType) T).referencesInterfaceExact())) {
                if (Repository.implementationOf(((ObjectType) this).getClassName(), ((ObjectType) T).getClassName())) {
                    return true;
                }
            }
        }
        /*
         * If this is an interface type, then:
         */
        if ((this instanceof ObjectType) && (((ObjectType) this).referencesInterfaceExact())) {
            /*
             * If T is a class type, then T must be Object (�2.4.7).
             */
            if ((T instanceof ObjectType) && (((ObjectType) T).referencesClassExact())) {
                if (T.equals(Type.OBJECT)) {
                    return true;
                }
            }
            /*
             * If T is an interface type, then T must be the same interface as this or a
             * superinterface of this (�2.13.2).
             */
            if ((T instanceof ObjectType) && (((ObjectType) T).referencesInterfaceExact())) {
                if (this.equals(T)) {
                    return true;
                }
                if (Repository.implementationOf(((ObjectType) this).getClassName(), ((ObjectType) T).getClassName())) {
                    return true;
                }
            }
        }
        if (this instanceof ArrayType) {
            if ((T instanceof ObjectType) && (((ObjectType) T).referencesClassExact())) {
                if (T.equals(Type.OBJECT)) {
                    return true;
                }
            }
            if (T instanceof ArrayType) {
                final Type sc = ((ArrayType) this).getElementType();
                final Type tc = ((ArrayType) T).getElementType();
                if (sc instanceof BasicType && tc instanceof BasicType && sc.equals(tc)) {
                    return true;
                }
                if (tc instanceof ReferenceType && sc instanceof ReferenceType && ((ReferenceType) sc).isAssignmentCompatibleWith(tc)) {
                    return true;
                }
            }
            if ((T instanceof ObjectType) && (((ObjectType) T).referencesInterfaceExact())) {
                for (final String element : Const.getInterfacesImplementedByArrays()) {
                    if (T.equals(ObjectType.getInstance(element))) {
                        return true;
                    }
                }
            }
        }
        return false; // default.
    }

    public boolean isCastableTo(final Type t) throws ClassNotFoundException, ClassFormatException {
        if (this.equals(Type.NULL)) {
            return t instanceof ReferenceType; // If this is ever changed in isAssignmentCompatible()
        }
        return isAssignmentCompatibleWith(t);
    }
}
