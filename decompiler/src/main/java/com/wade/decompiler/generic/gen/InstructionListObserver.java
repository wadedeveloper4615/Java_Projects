package com.wade.decompiler.generic.gen;

import com.wade.decompiler.generic.base.InstructionList;

public interface InstructionListObserver {
    void notify(InstructionList list);
}
