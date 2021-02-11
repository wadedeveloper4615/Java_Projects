
package org.apache.bcel.util;

import java.util.Stack;

import org.apache.bcel.classfile.JavaClass;

public class ClassStack {

    private final Stack<JavaClass> stack = new Stack<>();

    public void push(final JavaClass clazz) {
        stack.push(clazz);
    }

    public JavaClass pop() {
        return stack.pop();
    }

    public JavaClass top() {
        return stack.peek();
    }

    public boolean empty() {
        return stack.empty();
    }
}
