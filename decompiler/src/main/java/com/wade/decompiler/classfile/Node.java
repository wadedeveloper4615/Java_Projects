package com.wade.decompiler.classfile;

public interface Node {
    void accept(Visitor obj);
}
