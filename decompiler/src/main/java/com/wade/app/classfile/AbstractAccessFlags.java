package com.wade.app.classfile;

import com.wade.app.enums.AccessFlags;

public abstract class AbstractAccessFlags {
    protected AccessFlags access_flags;

    public AbstractAccessFlags() {
    }

    public AbstractAccessFlags(AccessFlags access_flags) {
        this.access_flags = access_flags;
    }

    public AccessFlags getAccessFlags() {
        return access_flags;
    }

    public AccessFlags getModifiers() {
        return access_flags;
    }

    public boolean isAbstract() {
        return access_flags.isAbstract();
    }

    public final boolean isAnnotation() {
        return access_flags.isAnnotation();
    }

    public final boolean isEnum() {
        return access_flags.isEnum();
    }

    public final boolean isFinal() {
        return access_flags.isFinal();
    }

    public final boolean isInterface() {
        return access_flags.isInterface();
    }

    public final boolean isNative() {
        return access_flags.isNative();
    }

    public final boolean isPrivate() {
        return access_flags.isPrivate();
    }

    public final boolean isProtected() {
        return access_flags.isProtected();
    }

    public final boolean isPublic() {
        return access_flags.isPublic();
    }

    public final boolean isStatic() {
        return access_flags.isStatic();
    }

    public final boolean isStrictfp() {
        return access_flags.isStrictfp();
    }

    public final boolean isSynchronized() {
        return access_flags.isSynchronized();
    }

    public final boolean isSynthetic() {
        return access_flags.isSynthetic();
    }

    public final boolean isTransient() {
        return access_flags.isTransient();
    }

    public final boolean isVarArgs() {
        return access_flags.isVarArgs();
    }

    public final boolean isVolatile() {
        return access_flags.isVolatile();
    }

//    private void setFlag(final int flag, final boolean set) {
//        if ((access_flags & flag) != 0) { // Flag is set already
//            if (!set) {
//                access_flags ^= flag;
//            }
//        } else { // Flag not set
//            if (set) {
//                access_flags |= flag;
//            }
//        }
//    }
}
