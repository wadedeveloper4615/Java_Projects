package com.wade.decompiler.decompiler;

import java.util.Stack;

public class ExpressionStack extends Stack<Expression> {
    private static final long serialVersionUID = -8692184386330343421L;

    @Override
    public boolean empty() {
        return super.empty();
    }

    @Override
    public synchronized Expression peek() {
        return super.peek();
    }

    @Override
    public synchronized Expression pop() {
        return super.pop();
    }

    @Override
    public Expression push(Expression item) {
        return super.push(item);
    }

    @Override
    public synchronized int search(Object o) {
        return super.search(o);
    }
}
