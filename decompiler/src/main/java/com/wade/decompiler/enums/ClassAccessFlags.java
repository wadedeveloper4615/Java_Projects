package com.wade.decompiler.enums;

import java.util.ArrayList;
import java.util.List;

public enum ClassAccessFlags {
  //@formatter:off
    ACC_PUBLIC(0x0001, 0),
    ACC_PRIVATE(0x0002, 1),
    ACC_PROTECTED(0x0004, 2),
    ACC_STATIC(0x0008, 3),
    ACC_FINAL(0x0010, 4),
    ACC_OPEN(0x0020, 5),
    ACC_SUPER(0x0020, 5),
    ACC_SYNCHRONIZED(0x0020, 5),
    ACC_TRANSITIVE(0x0020, 6),
    ACC_BRIDGE(0x0040, 6),
    ACC_STATIC_PHASE(0x0040, 6),
    ACC_VOLATILE(0x0040, 6),
    ACC_TRANSIENT(0x0080, 7),
    ACC_VARARGS(0x0080, 7),
    ACC_NATIVE(0x0100, 8),
    ACC_INTERFACE(0x0200, 9),
    ACC_ABSTRACT(0x0400, 10),
    ACC_STRICT(0x0800, 11),
    ACC_SYNTHETIC(0x1000, 12),
    ACC_ANNOTATION(0x2000, 13),
    ACC_ENUM(0x4000, 14),
    ACC_MANDATED(0x8000, 15),
    ACC_MODULE(0x8000, 15),
    MAX_ACC_FLAG(0x4000, 16),
    MAX_ACC_FLAG_I(0x8000, 17),
    ACC_DUMMY(0, 18);
  //@formatter:on
    private String[] ACCESS_NAMES = { "public", "private", "protected", "static", "", "synchronized", "volatile", "transient", "native", "interface", "abstract", "strictfp", "synthetic", "annotation", "enum", "module", "max flag", "max_flag 2", "DUMMY" };
    private int flag;
    private String name;

    ClassAccessFlags(int flag, int index) {
        this.flag = flag;
        this.name = ACCESS_NAMES[index];
    }

    public int getFlag() {
        return flag;
    }

    public String getName() {
        return name;
    }

    public boolean is() {
        return (flag & ACC_FINAL.getFlag()) != 0;
    }

    public boolean isAbstract() {
        return (flag & ACC_ABSTRACT.getFlag()) != 0;
    }

    public boolean isAndAbstract() {
        return this.is() && this.isAbstract();
    }

    public boolean isAnnotation() {
        return (flag & ACC_ANNOTATION.getFlag()) != 0;
    }

    public boolean isEnum() {
        return (flag & ACC_ENUM.getFlag()) != 0;
    }

    public boolean isInterface() {
        return (flag & ACC_INTERFACE.getFlag()) != 0;
    }

    public boolean isMandated() {
        return (flag & ACC_MANDATED.getFlag()) != 0;
    }

    public boolean isNative() {
        return (flag & ACC_NATIVE.getFlag()) != 0;
    }

    public boolean isPrivate() {
        return (flag & ACC_PRIVATE.getFlag()) != 0;
    }

    public boolean isProtected() {
        return (flag & ACC_PROTECTED.getFlag()) != 0;
    }

    public boolean isPublic() {
        return (flag & ACC_PUBLIC.getFlag()) != 0;
    }

    public boolean isSet(int p) {
        return (flag & p) != 0;
    }

    public boolean isStatic() {
        return (flag & ACC_STATIC.getFlag()) != 0;
    }

    public boolean isStrictfp() {
        return (flag & ACC_STRICT.getFlag()) != 0;
    }

    public boolean isSuper() {
        return (flag & ACC_SUPER.getFlag()) != 0;
    }

    public boolean isSynchronized() {
        return (flag & ACC_SYNCHRONIZED.getFlag()) != 0;
    }

    public boolean isSynthetic() {
        return (flag & ACC_SYNTHETIC.getFlag()) != 0;
    }

    public boolean isTransient() {
        return (flag & ACC_TRANSIENT.getFlag()) != 0;
    }

    public boolean isVarArgs() {
        return (flag & ACC_VARARGS.getFlag()) != 0;
    }

    public boolean isVolatile() {
        return (flag & ACC_VOLATILE.getFlag()) != 0;
    }

    public ClassAccessFlags setFlag(int flag) {
        this.flag = flag;
        this.name = ACCESS_NAMES[flag];
        return this;
    }

    public static boolean isSet(int flag, ClassAccessFlags p) {
        return (flag & p.getFlag()) != 0;
    }

    public static ClassAccessFlags read(int flags) {
        ClassAccessFlags flag = ClassAccessFlags.ACC_DUMMY.setFlag(flags);
        return flag;
    }

    public static List<ClassAccessFlags> readList(int accessFlags) {
        List<ClassAccessFlags> list = new ArrayList<>();
        for (ClassAccessFlags v : ClassAccessFlags.values()) {
            if (ClassAccessFlags.isSet(accessFlags, v)) {
                list.add(v);
            }
        }
        return list;
    }
}
