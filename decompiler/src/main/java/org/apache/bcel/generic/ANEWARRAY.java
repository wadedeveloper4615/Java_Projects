/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.apache.bcel.generic;

import org.apache.bcel.ExceptionConst;
import org.apache.bcel.generic.base.AllocationInstruction;
import org.apache.bcel.generic.base.ArrayType;
import org.apache.bcel.generic.base.CPInstruction;
import org.apache.bcel.generic.base.ExceptionThrower;
import org.apache.bcel.generic.base.LoadClass;
import org.apache.bcel.generic.gen.ConstantPoolGen;

/**
 * ANEWARRAY - Create new array of references
 *
 * <PRE>
 * Stack: ..., count -&gt; ..., arrayref
 * </PRE>
 *
 */
public class ANEWARRAY extends CPInstruction implements LoadClass, AllocationInstruction, ExceptionThrower, StackConsumer, StackProducer {

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used
     * otherwise.
     */
    public ANEWARRAY() {
    }

    public ANEWARRAY(final int index) {
        super(org.apache.bcel.Const.ANEWARRAY, index);
    }

    /**
     * Call corresponding visitor method(s). The order is: Call visitor methods of
     * implemented interfaces first, then call methods according to the class
     * hierarchy in descending order, i.e., the most specific visitXXX() call comes
     * last.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitLoadClass(this);
        v.visitAllocationInstruction(this);
        v.visitExceptionThrower(this);
        v.visitStackProducer(this);
        v.visitTypedInstruction(this);
        v.visitCPInstruction(this);
        v.visitANEWARRAY(this);
    }

    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_CLASS_AND_INTERFACE_RESOLUTION, ExceptionConst.NEGATIVE_ARRAY_SIZE_EXCEPTION);
    }

    @Override
    public ObjectType getLoadClassType(final ConstantPoolGen cpg) {
        Type t = getType(cpg);
        if (t instanceof ArrayType) {
            t = ((ArrayType) t).getBasicType();
        }
        return (t instanceof ObjectType) ? (ObjectType) t : null;
    }
}
