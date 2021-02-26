package com.wade.decompiler.enums;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClassAccessFlagsList {
    private int flags;
    private List<ClassAccessFlags> flagsList;

    public ClassAccessFlagsList() {
        flagsList = new ArrayList<>();
    }

    public ClassAccessFlagsList(DataInput dataInputStream) throws IOException {
        this.flags = dataInputStream.readUnsignedShort();
        this.flagsList = ClassAccessFlags.readList(flags);
    }

    public ClassAccessFlagsList(int flags) {
        this.flags = flags;
        this.flagsList = ClassAccessFlags.readList(flags);
    }

    public void addFlag(ClassAccessFlags flag) {
        flagsList.add(flag);
        this.setFlags();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ClassAccessFlagsList other = (ClassAccessFlagsList) obj;
        if (flags != other.flags)
            return false;
        if (flagsList == null) {
            if (other.flagsList != null)
                return false;
        } else if (!flagsList.equals(other.flagsList))
            return false;
        return true;
    }

    public int getFlags() {
        return flags;
    }

    public List<ClassAccessFlags> getFlagsList() {
        return flagsList;
    }

    public boolean hasFlag(ClassAccessFlags flags) {
        return (this.flags & flags.getFlag()) != 0;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + flags;
        result = prime * result + ((flagsList == null) ? 0 : flagsList.hashCode());
        return result;
    }

    public boolean isAbstract() {
        return hasFlag(ClassAccessFlags.ACC_ABSTRACT);
    }

    public boolean isAnnotation() {
        return hasFlag(ClassAccessFlags.ACC_ANNOTATION);
    }

    public boolean isEnum() {
        return hasFlag(ClassAccessFlags.ACC_ENUM);
    }

    public boolean isFinal() {
        return hasFlag(ClassAccessFlags.ACC_FINAL);
    }

    public boolean isFinalAndAbstract() {
        return hasFlag(ClassAccessFlags.ACC_FINAL) && hasFlag(ClassAccessFlags.ACC_ABSTRACT);
    }

    public boolean isInterface() {
        return hasFlag(ClassAccessFlags.ACC_INTERFACE);
    }

    public boolean isMandated() {
        return hasFlag(ClassAccessFlags.ACC_MANDATED);
    }

    public boolean isNative() {
        return hasFlag(ClassAccessFlags.ACC_NATIVE);
    }

    public boolean isPrivate() {
        return hasFlag(ClassAccessFlags.ACC_PRIVATE);
    }

    public boolean isProtected() {
        return hasFlag(ClassAccessFlags.ACC_PROTECTED);
    }

    public boolean isPublic() {
        return hasFlag(ClassAccessFlags.ACC_PUBLIC);
    }

    public boolean isStatic() {
        return hasFlag(ClassAccessFlags.ACC_STATIC);
    }

    public boolean isStrictfp() {
        return hasFlag(ClassAccessFlags.ACC_STRICT);
    }

    public boolean isSuper() {
        return hasFlag(ClassAccessFlags.ACC_SUPER);
    }

    public boolean isSynchronized() {
        return hasFlag(ClassAccessFlags.ACC_SYNCHRONIZED);
    }

    public boolean isSynthetic() {
        return hasFlag(ClassAccessFlags.ACC_SYNTHETIC);
    }

    public boolean isTransient() {
        return hasFlag(ClassAccessFlags.ACC_TRANSIENT);
    }

    public boolean isVarArgs() {
        return hasFlag(ClassAccessFlags.ACC_VARARGS);
    }

    public boolean isVolatile() {
        return hasFlag(ClassAccessFlags.ACC_VOLATILE);
    }

    public boolean remove(Object o) {
        boolean remove = flagsList.remove(o);
        setFlags();
        return remove;
    }

    private void setFlags() {
        flags = 0;
        for (ClassAccessFlags v : flagsList) {
            flags |= v.getFlag();
        }
    }

    public void setFlagsList(List<ClassAccessFlags> flagsList) {
        this.flagsList = flagsList;
        this.setFlags();
    }

    @Override
    public String toString() {
        return flagsList.toString();
    }
}
