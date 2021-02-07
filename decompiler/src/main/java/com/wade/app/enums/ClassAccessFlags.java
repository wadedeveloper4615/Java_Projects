package com.wade.app.enums;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public enum ClassAccessFlags {
    ACC_PUBLIC(0x0001, "public"),

    ACC_PRIVATE(0x0002, "private"),

    ACC_PROTECTED(0x0004, "protected"),

    ACC_STATIC(0x0008, "static"),

    ACC_FINAL(0x0010, "final"),

    ACC_OPEN(0x0020, "open"),

    ACC_SUPER(0x0020, "super"),

    ACC_SYNCHRONIZED(0x0020, "synchronized"),

    ACC_TRANSITIVE(0x0020, "transitive"),

    ACC_BRIDGE(0x0040, "bridge"),

    ACC_STATIC_PHASE(0x0040, "static phase"),

    ACC_VOLATILE(0x0040, "volatile"),

    ACC_TRANSIENT(0x0080, "transient"),

    ACC_VARARGS(0x0080, "varargs"),

    ACC_NATIVE(0x0100, "native"),

    ACC_INTERFACE(0x0200, "interface"),

    ACC_ABSTRACT(0x0400, "abstract"),

    ACC_STRICT(0x0800, "strict"),

    ACC_SYNTHETIC(0x1000, "synthetic"),

    ACC_ANNOTATION(0x2000, "annotation"),

    ACC_ENUM(0x4000, "enum"),

    ACC_MANDATED(0x8000, "mandated"),

    ACC_MODULE(0x8000, "module"),

    MAX_ACC_FLAG(0x4000, "max flag"),

    MAX_ACC_FLAG_I(0x8000, "max flag 2"),

    ACC_DUMMY(-1, "");

    private int flag;
    private String name;

    ClassAccessFlags(int flag, String name) {
        this.flag = flag;
        this.name = name;
    }

    public int getFlag() {
        return flag;
    }

    public String getName() {
        return name;
    }

    public boolean isAbstract() {
        return (flag & ACC_ABSTRACT.getFlag()) != 0;
    }

    public final boolean isAnnotation() {
        return (flag & ACC_ANNOTATION.getFlag()) != 0;
    }

    public final boolean isEnum() {
        return (flag & ACC_ENUM.getFlag()) != 0;
    }

    public final boolean isFinal() {
        return (flag & ACC_FINAL.getFlag()) != 0;
    }

    public final boolean isInterface() {
        return (flag & ACC_INTERFACE.getFlag()) != 0;
    }

    public boolean isMandated() {
        return (flag & ACC_MANDATED.getFlag()) != 0;
    }

    public final boolean isNative() {
        return (flag & ACC_NATIVE.getFlag()) != 0;
    }

    public final boolean isPrivate() {
        return (flag & ACC_PRIVATE.getFlag()) != 0;
    }

    public final boolean isProtected() {
        return (flag & ACC_PROTECTED.getFlag()) != 0;
    }

    public final boolean isPublic() {
        return (flag & ACC_PUBLIC.getFlag()) != 0;
    }

    public final boolean isSet(int p) {
        return (flag & p) != 0;
    }

    public final boolean isStatic() {
        return (flag & ACC_STATIC.getFlag()) != 0;
    }

    public final boolean isStrictfp() {
        return (flag & ACC_STRICT.getFlag()) != 0;
    }

    public final boolean isSuper() {
        return (flag & ACC_SUPER.getFlag()) != 0;
    }

    public final boolean isSynchronized() {
        return (flag & ACC_SYNCHRONIZED.getFlag()) != 0;
    }

    public final boolean isSynthetic() {
        return (flag & ACC_SYNTHETIC.getFlag()) != 0;
    }

    public final boolean isTransient() {
        return (flag & ACC_TRANSIENT.getFlag()) != 0;
    }

    public final boolean isVarArgs() {
        return (flag & ACC_VARARGS.getFlag()) != 0;
    }

    public final boolean isVolatile() {
        return (flag & ACC_VOLATILE.getFlag()) != 0;
    }

    public ClassAccessFlags setFlag(int flag) {
        this.flag = flag;
        return this;
    }

    public static final boolean isSet(int flag, ClassAccessFlags p) {
        return (flag & p.getFlag()) != 0;
    }

    public static List<ClassAccessFlags> read(DataInputStream dataInputStream) throws IOException {
        List<ClassAccessFlags> list = new ArrayList<>();
        int value = dataInputStream.readUnsignedShort();
        for (ClassAccessFlags v : ClassAccessFlags.values()) {
            if (ClassAccessFlags.isSet(value, v)) {
                list.add(v);
            }
        }
        return list;
    }
}
