
package org.apache.bcel.verifier.structurals;

import org.apache.bcel.generic.ObjectType;
import org.apache.bcel.generic.control.InstructionHandle;

public class ExceptionHandler {

    private final ObjectType catchType;

    private final InstructionHandle handlerPc;

    ExceptionHandler(final ObjectType catch_type, final InstructionHandle handler_pc) {
        catchType = catch_type;
        handlerPc = handler_pc;
    }

    public ObjectType getExceptionType() {
        return catchType;
    }

    public InstructionHandle getHandlerStart() {
        return handlerPc;
    }
}
