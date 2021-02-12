package org.apache.bcel.classfile;

import org.apache.bcel.enums.ClassAccessFlags;
import org.apache.bcel.util.ClassAccessFlagsList;

public abstract class AccessFlags {
    protected int access_flags;

    public AccessFlags() {
    }

    public AccessFlags(ClassAccessFlagsList a) {
        access_flags = a.getAccessFlags();
    }

    public AccessFlags(int a) {
        access_flags = a;
    }

    public int getAccessFlags() {
        return access_flags;
    }

    public int getModifiers() {
        return access_flags;
    }

    public boolean isAbstract() {
        return (access_flags & ClassAccessFlags.ACC_ABSTRACT.getFlag()) != 0;
    }

    public void isAbstract(boolean flag) {
        setFlag(ClassAccessFlags.ACC_ABSTRACT.getFlag(), flag);
    }

    public boolean isAnnotation() {
        return (access_flags & ClassAccessFlags.ACC_ANNOTATION.getFlag()) != 0;
    }

    public void isAnnotation(boolean flag) {
        setFlag(ClassAccessFlags.ACC_ANNOTATION.getFlag(), flag);
    }

    public boolean isEnum() {
        return (access_flags & ClassAccessFlags.ACC_ENUM.getFlag()) != 0;
    }

    public void isEnum(boolean flag) {
        setFlag(ClassAccessFlags.ACC_ENUM.getFlag(), flag);
    }

    public boolean isFinal() {
        return (access_flags & ClassAccessFlags.ACC_FINAL.getFlag()) != 0;
    }

    public void isFinal(boolean flag) {
        setFlag(ClassAccessFlags.ACC_FINAL.getFlag(), flag);
    }

    public boolean isInterface() {
        return (access_flags & ClassAccessFlags.ACC_INTERFACE.getFlag()) != 0;
    }

    public void isInterface(boolean flag) {
        setFlag(ClassAccessFlags.ACC_INTERFACE.getFlag(), flag);
    }

    public boolean isNative() {
        return (access_flags & ClassAccessFlags.ACC_NATIVE.getFlag()) != 0;
    }

    public void isNative(boolean flag) {
        setFlag(ClassAccessFlags.ACC_NATIVE.getFlag(), flag);
    }

    public boolean isPrivate() {
        return (access_flags & ClassAccessFlags.ACC_PRIVATE.getFlag()) != 0;
    }

    public void isPrivate(boolean flag) {
        setFlag(ClassAccessFlags.ACC_PRIVATE.getFlag(), flag);
    }

    public boolean isProtected() {
        return (access_flags & ClassAccessFlags.ACC_PROTECTED.getFlag()) != 0;
    }

    public void isProtected(boolean flag) {
        setFlag(ClassAccessFlags.ACC_PROTECTED.getFlag(), flag);
    }

    public boolean isPublic() {
        return (access_flags & ClassAccessFlags.ACC_PUBLIC.getFlag()) != 0;
    }

    public void isPublic(boolean flag) {
        setFlag(ClassAccessFlags.ACC_PUBLIC.getFlag(), flag);
    }

    public boolean isStatic() {
        return (access_flags & ClassAccessFlags.ACC_STATIC.getFlag()) != 0;
    }

    public void isStatic(boolean flag) {
        setFlag(ClassAccessFlags.ACC_STATIC.getFlag(), flag);
    }

    public boolean isStrictfp() {
        return (access_flags & ClassAccessFlags.ACC_STRICT.getFlag()) != 0;
    }

    public void isStrictfp(boolean flag) {
        setFlag(ClassAccessFlags.ACC_STRICT.getFlag(), flag);
    }

    public boolean isSynchronized() {
        return (access_flags & ClassAccessFlags.ACC_SYNCHRONIZED.getFlag()) != 0;
    }

    public void isSynchronized(boolean flag) {
        setFlag(ClassAccessFlags.ACC_SYNCHRONIZED.getFlag(), flag);
    }

    public boolean isSynthetic() {
        return (access_flags & ClassAccessFlags.ACC_SYNTHETIC.getFlag()) != 0;
    }

    public void isSynthetic(boolean flag) {
        setFlag(ClassAccessFlags.ACC_SYNTHETIC.getFlag(), flag);
    }

    public boolean isTransient() {
        return (access_flags & ClassAccessFlags.ACC_TRANSIENT.getFlag()) != 0;
    }

    public void isTransient(boolean flag) {
        setFlag(ClassAccessFlags.ACC_TRANSIENT.getFlag(), flag);
    }

    public boolean isVarArgs() {
        return (access_flags & ClassAccessFlags.ACC_VARARGS.getFlag()) != 0;
    }

    public void isVarArgs(boolean flag) {
        setFlag(ClassAccessFlags.ACC_VARARGS.getFlag(), flag);
    }

    public boolean isVolatile() {
        return (access_flags & ClassAccessFlags.ACC_VOLATILE.getFlag()) != 0;
    }

    public void isVolatile(boolean flag) {
        setFlag(ClassAccessFlags.ACC_VOLATILE.getFlag(), flag);
    }

    public void setAccessFlags(int access_flags) {
        this.access_flags = access_flags;
    }

    private void setFlag(int flag, boolean set) {
        if ((access_flags & flag) != 0) {
            if (!set) {
                access_flags ^= flag;
            }
        } else {
            if (set) {
                access_flags |= flag;
            }
        }
    }

    public void setModifiers(int access_flags) {
        setAccessFlags(access_flags);
    }
}
