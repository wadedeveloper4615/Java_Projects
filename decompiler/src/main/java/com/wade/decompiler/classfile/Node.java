package com.wade.decompiler.classfile;

import com.wade.decompiler.classfile.gen.Visitor;

public interface Node {
    void accept(Visitor obj);
}
