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

import org.apache.bcel.generic.base.LoadInstruction;

/**
 * DLOAD - Load double from local variable
 *
 * <PRE>
 * Stack ... -&gt; ..., result.word1, result.word2
 * </PRE>
 *
 */
public class DLOAD extends LoadInstruction {

    /**
     * Empty constructor needed for Instruction.readInstruction. Not to be used
     * otherwise.
     */
    public DLOAD() {
        super(org.apache.bcel.Const.DLOAD, org.apache.bcel.Const.DLOAD_0);
    }

    /**
     * Load double from local variable
     *
     * @param n index of local variable
     */
    public DLOAD(final int n) {
        super(org.apache.bcel.Const.DLOAD, org.apache.bcel.Const.DLOAD_0, n);
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
        super.accept(v);
        v.visitDLOAD(this);
    }
}
