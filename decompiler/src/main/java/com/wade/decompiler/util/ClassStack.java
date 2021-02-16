package com.wade.decompiler.util;

import java.util.Stack;

import com.wade.decompiler.classfile.JavaClass;

public class ClassStack {
    private final Stack<JavaClass> stack = new Stack<>();

    public boolean empty() {
        return stack.empty();
    }

    public JavaClass pop() {
        return stack.pop();
    }

    public void push(final JavaClass clazz) {
        stack.push(clazz);
    }

    public JavaClass top() {
        return stack.peek();
    }
}
