package com.wade.decompiler.classfile;

import com.wade.decompiler.enums.ClassAccessFlags;

public abstract class AccessFlags {
    protected ClassAccessFlags access_flags;

    public AccessFlags() {
    }

    public AccessFlags(ClassAccessFlags a) {
        access_flags = a;
    }

    public ClassAccessFlags getAccessFlags() {
        return access_flags;
    }

    public ClassAccessFlags getModifiers() {
        return access_flags;
    }

    public final boolean isAbstract() {
        return (access_flags.getFlag() & ClassAccessFlags.ACC_ABSTRACT.getFlag()) != 0;
    }

    public final void isAbstract(boolean flag) {
        setFlag(ClassAccessFlags.ACC_ABSTRACT, flag);
    }

    public final boolean isAnnotation() {
        return (access_flags.getFlag() & ClassAccessFlags.ACC_ANNOTATION.getFlag()) != 0;
    }

    public final void isAnnotation(boolean flag) {
        setFlag(ClassAccessFlags.ACC_ANNOTATION, flag);
    }

    public final boolean isEnum() {
        return (access_flags.getFlag() & ClassAccessFlags.ACC_ENUM.getFlag()) != 0;
    }

    public final void isEnum(boolean flag) {
        setFlag(ClassAccessFlags.ACC_ENUM, flag);
    }

    public final boolean isFinal() {
        return (access_flags.getFlag() & ClassAccessFlags.ACC_FINAL.getFlag()) != 0;
    }

    public final void isFinal(boolean flag) {
        setFlag(ClassAccessFlags.ACC_FINAL, flag);
    }

    public final boolean isInterface() {
        return (access_flags.getFlag() & ClassAccessFlags.ACC_INTERFACE.getFlag()) != 0;
    }

    public final void isInterface(boolean flag) {
        setFlag(ClassAccessFlags.ACC_INTERFACE, flag);
    }

    public final boolean isNative() {
        return (access_flags.getFlag() & ClassAccessFlags.ACC_NATIVE.getFlag()) != 0;
    }

    public final void isNative(boolean flag) {
        setFlag(ClassAccessFlags.ACC_NATIVE, flag);
    }

    public final boolean isPrivate() {
        return (access_flags.getFlag() & ClassAccessFlags.ACC_PRIVATE.getFlag()) != 0;
    }

    public final void isPrivate(boolean flag) {
        setFlag(ClassAccessFlags.ACC_PRIVATE, flag);
    }

    public final boolean isProtected() {
        return (access_flags.getFlag() & ClassAccessFlags.ACC_PROTECTED.getFlag()) != 0;
    }

    public final void isProtected(boolean flag) {
        setFlag(ClassAccessFlags.ACC_PROTECTED, flag);
    }

    public final boolean isPublic() {
        return (access_flags.getFlag() & ClassAccessFlags.ACC_PUBLIC.getFlag()) != 0;
    }

    public final void isPublic(boolean flag) {
        setFlag(ClassAccessFlags.ACC_PUBLIC, flag);
    }

    public final boolean isStatic() {
        return (access_flags.getFlag() & ClassAccessFlags.ACC_STATIC.getFlag()) != 0;
    }

    public final void isStatic(boolean flag) {
        setFlag(ClassAccessFlags.ACC_STATIC, flag);
    }

    public final boolean isStrictfp() {
        return (access_flags.getFlag() & ClassAccessFlags.ACC_STRICT.getFlag()) != 0;
    }

    public final void isStrictfp(boolean flag) {
        setFlag(ClassAccessFlags.ACC_STRICT, flag);
    }

    public final boolean isSynchronized() {
        return (access_flags.getFlag() & ClassAccessFlags.ACC_SYNCHRONIZED.getFlag()) != 0;
    }

    public final void isSynchronized(boolean flag) {
        setFlag(ClassAccessFlags.ACC_SYNCHRONIZED, flag);
    }

    public final boolean isSynthetic() {
        return (access_flags.getFlag() & ClassAccessFlags.ACC_SYNTHETIC.getFlag()) != 0;
    }

    public final void isSynthetic(boolean flag) {
        setFlag(ClassAccessFlags.ACC_SYNTHETIC, flag);
    }

    public final boolean isTransient() {
        return (access_flags.getFlag() & ClassAccessFlags.ACC_TRANSIENT.getFlag()) != 0;
    }

    public final void isTransient(boolean flag) {
        setFlag(ClassAccessFlags.ACC_TRANSIENT, flag);
    }

    public final boolean isVarArgs() {
        return (access_flags.getFlag() & ClassAccessFlags.ACC_VARARGS.getFlag()) != 0;
    }

    public final void isVarArgs(boolean flag) {
        setFlag(ClassAccessFlags.ACC_VARARGS, flag);
    }

    public final boolean isVolatile() {
        return (access_flags.getFlag() & ClassAccessFlags.ACC_VOLATILE.getFlag()) != 0;
    }

    public final void isVolatile(boolean flag) {
        setFlag(ClassAccessFlags.ACC_VOLATILE, flag);
    }

    public final void setAccessFlags(int access_flags) {
        this.access_flags.setFlag(access_flags);
    }

    private void setFlag(ClassAccessFlags flag, boolean set) {
        if ((access_flags.getFlag() & flag.getFlag()) != 0) {
            if (!set) {
                access_flags.setFlag(access_flags.getFlag() ^ flag.getFlag());
            }
        } else if (set) {
            access_flags.setFlag(access_flags.getFlag() | flag.getFlag());
        }
    }

    public final void setModifiers(int access_flags) {
        setAccessFlags(access_flags);
    }
}
