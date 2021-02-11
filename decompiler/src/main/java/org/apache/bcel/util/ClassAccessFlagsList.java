package org.apache.bcel.util;

import java.io.IOException;
import java.util.List;

import org.apache.bcel.classfile.ClassFormatException;
import org.apache.bcel.enums.ClassAccessFlags;

public class ClassAccessFlagsList {
    private List<ClassAccessFlags> flags;
    private int accessFlags;

    public ClassAccessFlagsList(int accessFlags) throws IOException {
        this.accessFlags = accessFlags;
        this.flags = ClassAccessFlags.read(accessFlags);
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public List<ClassAccessFlags> getFlags() {
        return flags;
    }

    public void IfAbstractAndFinalThenError(String fileName) {
        if (((accessFlags & ClassAccessFlags.ACC_ABSTRACT.getFlag()) != 0) && ((accessFlags & ClassAccessFlags.ACC_FINAL.getFlag()) != 0)) {
            throw new ClassFormatException("Class " + fileName + " can't be both  and abstract");
        }
    }

    public void ifInterfaceThenAbstract() throws IOException {
        if ((accessFlags & ClassAccessFlags.ACC_INTERFACE.getFlag()) != 0) {
            setAccessFlags(accessFlags | ClassAccessFlags.ACC_ABSTRACT.getFlag());
        }
    }

    public void setAccessFlags(int accessFlags) throws IOException {
        this.accessFlags = accessFlags;
        flags.clear();
        flags = ClassAccessFlags.read(accessFlags);
    }
}
