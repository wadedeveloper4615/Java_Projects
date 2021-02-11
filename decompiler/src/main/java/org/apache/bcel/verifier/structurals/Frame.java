
package org.apache.bcel.verifier.structurals;

public class Frame {

    @Deprecated
    protected static UninitializedObjectType _this;

    private final LocalVariables locals;

    private final OperandStack stack;

    public Frame(final int maxLocals, final int maxStack) {
        locals = new LocalVariables(maxLocals);
        stack = new OperandStack(maxStack);
    }

    public Frame(final LocalVariables locals, final OperandStack stack) {
        this.locals = locals;
        this.stack = stack;
    }

    @Override
    protected Object clone() {
        final Frame f = new Frame(locals.getClone(), stack.getClone());
        return f;
    }

    public Frame getClone() {
        return (Frame) clone();
    }

    public LocalVariables getLocals() {
        return locals;
    }

    public OperandStack getStack() {
        return stack;
    }

    @Override
    public int hashCode() {
        return stack.hashCode() ^ locals.hashCode();
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Frame)) {
            return false; // implies "null" is non-equal.
        }
        final Frame f = (Frame) o;
        return this.stack.equals(f.stack) && this.locals.equals(f.locals);
    }

    @Override
    public String toString() {
        String s = "Local Variables:\n";
        s += locals;
        s += "OperandStack:\n";
        s += stack;
        return s;
    }

    public static UninitializedObjectType getThis() {
        return _this;
    }

    public static void setThis(final UninitializedObjectType _this) {
        Frame._this = _this;
    }
}
