
package org.apache.bcel.verifier.structurals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.bcel.generic.control.InstructionHandle;
import org.apache.bcel.generic.gen.CodeExceptionGen;
import org.apache.bcel.generic.gen.MethodGen;

public class ExceptionHandlers {

    private final Map<InstructionHandle, Set<ExceptionHandler>> exceptionHandlers;

    public ExceptionHandlers(final MethodGen mg) {
        exceptionHandlers = new HashMap<>();
        final CodeExceptionGen[] cegs = mg.getExceptionHandlers();
        for (final CodeExceptionGen ceg : cegs) {
            final ExceptionHandler eh = new ExceptionHandler(ceg.getCatchType(), ceg.getHandlerPC());
            for (InstructionHandle ih = ceg.getStartPC(); ih != ceg.getEndPC().getNext(); ih = ih.getNext()) {
                Set<ExceptionHandler> hs;
                hs = exceptionHandlers.get(ih);
                if (hs == null) {
                    hs = new HashSet<>();
                    exceptionHandlers.put(ih, hs);
                }
                hs.add(eh);
            }
        }
    }

    public ExceptionHandler[] getExceptionHandlers(final InstructionHandle ih) {
        final Set<ExceptionHandler> hsSet = exceptionHandlers.get(ih);
        if (hsSet == null) {
            return new ExceptionHandler[0];
        }
        return hsSet.toArray(new ExceptionHandler[hsSet.size()]);
    }

}
