package com.wade.app;

public abstract class AccessFlags {
    protected int access_flags;

    public AccessFlags() {
    }

    public AccessFlags(final int a) {
        access_flags = a;
    }

    public final int getAccessFlags() {
        return access_flags;
    }

    public final int getModifiers() {
        return access_flags;
    }

    public final boolean isAbstract() {
        return (access_flags & Const.ACC_ABSTRACT) != 0;
    }

    public final void isAbstract(final boolean flag) {
        setFlag(Const.ACC_ABSTRACT, flag);
    }

    public final boolean isAnnotation() {
        return (access_flags & Const.ACC_ANNOTATION) != 0;
    }

    public final void isAnnotation(final boolean flag) {
        setFlag(Const.ACC_ANNOTATION, flag);
    }

    public final boolean isEnum() {
        return (access_flags & Const.ACC_ENUM) != 0;
    }

    public final void isEnum(final boolean flag) {
        setFlag(Const.ACC_ENUM, flag);
    }

    public final boolean isFinal() {
        return (access_flags & Const.ACC_FINAL) != 0;
    }

    public final void isFinal(final boolean flag) {
        setFlag(Const.ACC_FINAL, flag);
    }

    public final boolean isInterface() {
        return (access_flags & Const.ACC_INTERFACE) != 0;
    }

    public final void isInterface(final boolean flag) {
        setFlag(Const.ACC_INTERFACE, flag);
    }

    public final boolean isNative() {
        return (access_flags & Const.ACC_NATIVE) != 0;
    }

    public final void isNative(final boolean flag) {
        setFlag(Const.ACC_NATIVE, flag);
    }

    public final boolean isPrivate() {
        return (access_flags & Const.ACC_PRIVATE) != 0;
    }

    public final void isPrivate(final boolean flag) {
        setFlag(Const.ACC_PRIVATE, flag);
    }

    public final boolean isProtected() {
        return (access_flags & Const.ACC_PROTECTED) != 0;
    }

    public final void isProtected(final boolean flag) {
        setFlag(Const.ACC_PROTECTED, flag);
    }

    public final boolean isPublic() {
        return (access_flags & Const.ACC_PUBLIC) != 0;
    }

    public final void isPublic(final boolean flag) {
        setFlag(Const.ACC_PUBLIC, flag);
    }

    public final boolean isStatic() {
        return (access_flags & Const.ACC_STATIC) != 0;
    }

    public final void isStatic(final boolean flag) {
        setFlag(Const.ACC_STATIC, flag);
    }

    public final boolean isStrictfp() {
        return (access_flags & Const.ACC_STRICT) != 0;
    }

    public final void isStrictfp(final boolean flag) {
        setFlag(Const.ACC_STRICT, flag);
    }

    public final boolean isSynchronized() {
        return (access_flags & Const.ACC_SYNCHRONIZED) != 0;
    }

    public final void isSynchronized(final boolean flag) {
        setFlag(Const.ACC_SYNCHRONIZED, flag);
    }

    public final boolean isSynthetic() {
        return (access_flags & Const.ACC_SYNTHETIC) != 0;
    }

    public final void isSynthetic(final boolean flag) {
        setFlag(Const.ACC_SYNTHETIC, flag);
    }

    public final boolean isTransient() {
        return (access_flags & Const.ACC_TRANSIENT) != 0;
    }

    public final void isTransient(final boolean flag) {
        setFlag(Const.ACC_TRANSIENT, flag);
    }

    public final boolean isVarArgs() {
        return (access_flags & Const.ACC_VARARGS) != 0;
    }

    public final void isVarArgs(final boolean flag) {
        setFlag(Const.ACC_VARARGS, flag);
    }

    public final boolean isVolatile() {
        return (access_flags & Const.ACC_VOLATILE) != 0;
    }

    public final void isVolatile(final boolean flag) {
        setFlag(Const.ACC_VOLATILE, flag);
    }

    /**
     * Set access flags aka "modifiers".
     *
     * @param access_flags Access flags of the object.
     */
    public final void setAccessFlags(final int access_flags) {
        this.access_flags = access_flags;
    }

    private void setFlag(final int flag, final boolean set) {
        if ((access_flags & flag) != 0) { // Flag is set already
            if (!set) {
                access_flags ^= flag;
            }
        } else { // Flag not set
            if (set) {
                access_flags |= flag;
            }
        }
    }

    /**
     * Set access flags aka "modifiers".
     *
     * @param access_flags Access flags of the object.
     */
    public final void setModifiers(final int access_flags) {
        setAccessFlags(access_flags);
    }
}
