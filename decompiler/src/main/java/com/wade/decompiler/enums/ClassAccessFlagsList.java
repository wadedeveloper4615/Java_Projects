package com.wade.decompiler.enums;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassAccessFlagsList {
    private int flags;
    private List<ClassAccessFlags> flagsList;

    public ClassAccessFlagsList() {
    }

    public ClassAccessFlagsList(ClassAccessFlagsList access_flags) {
        this.flags = access_flags.getFlags();
        this.flagsList = access_flags.getFlagsList();
    }

    public ClassAccessFlagsList(DataInputStream dataInputStream) throws IOException {
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

    public int getFlags() {
        return flags;
    }

    public List<ClassAccessFlags> getFlagsList() {
        return flagsList;
    }

    public boolean hasFlag(ClassAccessFlags flags) {
        return (this.flags & flags.getFlag()) != 0;
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

    private void setFlags() {
        flags = 0;
        for (ClassAccessFlags v : flagsList) {
            flags |= v.getFlag();
        }
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public void setFlagsList(List<ClassAccessFlags> flagsList) {
        this.flagsList = flagsList;
        this.setFlags();
    }

    public String toString(boolean for_class) {
        Map<Integer, Boolean> map = new HashMap<>();
        StringBuilder buf = new StringBuilder();
        for (ClassAccessFlags p : flagsList) {
            if (for_class && ((p == ClassAccessFlags.ACC_SUPER) || (p == ClassAccessFlags.ACC_INTERFACE))) {
                continue;
            }
            if (map.get(p.getFlag()) == null) {
                buf.append(p.getName()).append(" ");
                map.put(p.getFlag(), Boolean.TRUE);
            }
        }
        return buf.toString().trim();
    }
}
