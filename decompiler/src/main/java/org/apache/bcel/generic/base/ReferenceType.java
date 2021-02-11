
package org.apache.bcel.generic.base;

import org.apache.bcel.Const;
import org.apache.bcel.Repository;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.generic.BasicType;
import org.apache.bcel.generic.ObjectType;
import org.apache.bcel.generic.Type;

public abstract class ReferenceType extends Type {
    public ReferenceType() {
        super(Const.T_OBJECT, "<null object>");
    }

    protected ReferenceType(final byte t, final String s) {
        super(t, s);
    }

    @Deprecated
    public ReferenceType firstCommonSuperclass(final ReferenceType t) throws ClassNotFoundException {
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
            // TODO: Is there a proof of OBJECT being the direct ancestor of every
            // ArrayType?
        }
        if (((this instanceof ObjectType) && ((ObjectType) this).referencesInterface()) || ((t instanceof ObjectType) && ((ObjectType) t).referencesInterface())) {
            return Type.OBJECT;
            // TODO: The above line is correct comparing to the vmspec2. But one could
            // make class file verification a bit stronger here by using the notion of
            // superinterfaces or even castability or assignment compatibility.
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
                    return ObjectType.getInstance(this_sup.getClassName().getName());
                }
            }
        }
        // Huh? Did you ask for Type.OBJECT's superclass??
        return null;
    }

    public ReferenceType getFirstCommonSuperclass(final ReferenceType t) throws ClassNotFoundException {
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
            // TODO: Is there a proof of OBJECT being the direct ancestor of every
            // ArrayType?
        }
        if (((this instanceof ObjectType) && ((ObjectType) this).referencesInterfaceExact()) || ((t instanceof ObjectType) && ((ObjectType) t).referencesInterfaceExact())) {
            return Type.OBJECT;
            // TODO: The above line is correct comparing to the vmspec2. But one could
            // make class file verification a bit stronger here by using the notion of
            // superinterfaces or even castability or assignment compatibility.
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
                    return ObjectType.getInstance(this_sup.getClassName().getName());
                }
            }
        }
        // Huh? Did you ask for Type.OBJECT's superclass??
        return null;
    }

    public boolean isAssignmentCompatibleWith(final Type t) throws ClassNotFoundException {
        if (!(t instanceof ReferenceType)) {
            return false;
        }
        final ReferenceType T = (ReferenceType) t;
        if (this.equals(Type.NULL)) {
            return true; // This is not explicitely stated, but clear. Isn't it?
        }

        if ((this instanceof ObjectType) && (((ObjectType) this).referencesClassExact())) {

            if ((T instanceof ObjectType) && (((ObjectType) T).referencesClassExact())) {
                if (this.equals(T)) {
                    return true;
                }
                if (Repository.instanceOf(((ObjectType) this).getClassName(), ((ObjectType) T).getClassName())) {
                    return true;
                }
            }

            if ((T instanceof ObjectType) && (((ObjectType) T).referencesInterfaceExact())) {
                if (Repository.implementationOf(((ObjectType) this).getClassName(), ((ObjectType) T).getClassName())) {
                    return true;
                }
            }
        }

        if ((this instanceof ObjectType) && (((ObjectType) this).referencesInterfaceExact())) {

            if ((T instanceof ObjectType) && (((ObjectType) T).referencesClassExact())) {
                if (T.equals(Type.OBJECT)) {
                    return true;
                }
            }

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

            // TODO: Check if this is still valid or find a way to dynamically find out
            // which
            // interfaces arrays implement. However, as of the JVM specification edition 2,
            // there
            // are at least two different pages where assignment compatibility is defined
            // and
            // on one of them "interfaces implemented by arrays" is exchanged with
            // "'Cloneable' or
            // 'java.io.Serializable'"
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

    public boolean isCastableTo(final Type t) throws ClassNotFoundException {
        if (this.equals(Type.NULL)) {
            return t instanceof ReferenceType; // If this is ever changed in isAssignmentCompatible()
        }
        return isAssignmentCompatibleWith(t);

    }
}
