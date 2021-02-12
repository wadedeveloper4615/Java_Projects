
package org.apache.bcel.verifier.structurals;

import java.util.ArrayList;

import org.apache.bcel.generic.ObjectType;
import org.apache.bcel.generic.Type;
import org.apache.bcel.generic.base.ReferenceType;
import org.apache.bcel.verifier.exc.AssertionViolatedException;
import org.apache.bcel.verifier.exc.StructuralCodeConstraintException;

public class OperandStack implements Cloneable {

    private ArrayList<Type> stack = new ArrayList<>();

    private final int maxStack;

    public OperandStack(final int maxStack) {
        this.maxStack = maxStack;
    }

    public OperandStack(final int maxStack, final ObjectType obj) {
        this.maxStack = maxStack;
        this.push(obj);
    }

    @Override
    public Object clone() {
        final OperandStack newstack = new OperandStack(this.maxStack);
        @SuppressWarnings("unchecked") // OK because this.stack is the same type
        final ArrayList<Type> clone = (ArrayList<Type>) this.stack.clone();
        newstack.stack = clone;
        return newstack;
    }

    public void clear() {
        stack = new ArrayList<>();
    }

    @Override
    public int hashCode() {
        return stack.hashCode();
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof OperandStack)) {
            return false;
        }
        final OperandStack s = (OperandStack) o;
        return this.stack.equals(s.stack);
    }

    public OperandStack getClone() {
        return (OperandStack) this.clone();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int maxStack() {
        return this.maxStack;
    }

    public Type peek() {
        return peek(0);
    }

    public Type peek(final int i) {
        return stack.get(size() - i - 1);
    }

    public Type pop() {
        final Type e = stack.remove(size() - 1);
        return e;
    }

    public Type pop(final int i) {
        for (int j = 0; j < i; j++) {
            pop();
        }
        return null;
    }

    public void push(final Type type) {
        if (type == null) {
            throw new AssertionViolatedException("Cannot push NULL onto OperandStack.");
        }
        if (type == Type.BOOLEAN || type == Type.CHAR || type == Type.BYTE || type == Type.SHORT) {
            throw new AssertionViolatedException("The OperandStack does not know about '" + type + "'; use Type.INT instead.");
        }
        if (slotsUsed() >= maxStack) {
            throw new AssertionViolatedException("OperandStack too small, should have thrown proper Exception elsewhere. Stack: " + this);
        }
        stack.add(type);
    }

    public int size() {
        return stack.size();
    }

    public int slotsUsed() {

        int slots = 0;
        for (int i = 0; i < stack.size(); i++) {
            slots += peek(i).getSize();
        }
        return slots;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Slots used: ");
        sb.append(slotsUsed());
        sb.append(" MaxStack: ");
        sb.append(maxStack);
        sb.append(".\n");
        for (int i = 0; i < size(); i++) {
            sb.append(peek(i));
            sb.append(" (Size: ");
            sb.append(String.valueOf(peek(i).getSize()));
            sb.append(")\n");
        }
        return sb.toString();
    }

    public void merge(final OperandStack s) {
        try {
            if ((slotsUsed() != s.slotsUsed()) || (size() != s.size())) {
                throw new StructuralCodeConstraintException("Cannot merge stacks of different size:\nOperandStack A:\n" + this + "\nOperandStack B:\n" + s);
            }

            for (int i = 0; i < size(); i++) {
                // If the object _was_ initialized and we're supposed to merge
                // in some uninitialized object, we reject the code (see vmspec2, 4.9.4, last
                // paragraph).
                if ((!(stack.get(i) instanceof UninitializedObjectType)) && (s.stack.get(i) instanceof UninitializedObjectType)) {
                    throw new StructuralCodeConstraintException("Backwards branch with an uninitialized object on the stack detected.");
                }
                // Even harder, we're not initialized but are supposed to broaden
                // the known object type
                if ((!(stack.get(i).equals(s.stack.get(i)))) && (stack.get(i) instanceof UninitializedObjectType) && (!(s.stack.get(i) instanceof UninitializedObjectType))) {
                    throw new StructuralCodeConstraintException("Backwards branch with an uninitialized object on the stack detected.");
                }
                // on the other hand...
                if (stack.get(i) instanceof UninitializedObjectType) { // if we have an uninitialized object here
                    if (!(s.stack.get(i) instanceof UninitializedObjectType)) { // that has been initialized by now
                        stack.set(i, ((UninitializedObjectType) (stack.get(i))).getInitialized()); // note that.
                    }
                }
                if (!stack.get(i).equals(s.stack.get(i))) {
                    if ((stack.get(i) instanceof ReferenceType) && (s.stack.get(i) instanceof ReferenceType)) {
                        stack.set(i, ((ReferenceType) stack.get(i)).getFirstCommonSuperclass((ReferenceType) (s.stack.get(i))));
                    } else {
                        throw new StructuralCodeConstraintException("Cannot merge stacks of different types:\nStack A:\n" + this + "\nStack B:\n" + s);
                    }
                }
            }
        } catch (final ClassNotFoundException e) {
            // FIXME: maybe not the best way to handle this
            throw new AssertionViolatedException("Missing class: " + e, e);
        }
    }

    public void initializeObject(final UninitializedObjectType u) {
        for (int i = 0; i < stack.size(); i++) {
            if (stack.get(i) == u) {
                stack.set(i, u.getInitialized());
            }
        }
    }

}
