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
package org.apache.bcel.verifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.bcel.ClassFileName;

/**
 * This class produces instances of the Verifier class. Its purpose is to make
 * sure that they are singleton instances with respect to the class name they
 * operate on. That means, for every class (represented by a unique fully
 * qualified class name) there is exactly one Verifier.
 *
 * @see Verifier
 */
public class VerifierFactory {

    /**
     * The HashMap that holds the data about the already-constructed Verifier
     * instances.
     */
    private static Map<String, Verifier> hashMap = new HashMap<>();
    /**
     * The VerifierFactoryObserver instances that observe the VerifierFactory.
     */
    private static List<VerifierFactoryObserver> observers = new Vector<>();

    /**
     * The VerifierFactory is not instantiable.
     */
    private VerifierFactory() {
    }

    /**
     * Adds the VerifierFactoryObserver o to the list of observers.
     */
    public static void attach(VerifierFactoryObserver o) {
        observers.add(o);
    }

    /**
     * Removes the VerifierFactoryObserver o from the list of observers.
     */
    public static void detach(VerifierFactoryObserver o) {
        observers.remove(o);
    }

    public static Verifier getVerifier(ClassFileName fullyQualifiedClassName) {
        Verifier v = hashMap.get(fullyQualifiedClassName.getName());
        if (v == null) {
            v = new Verifier(fullyQualifiedClassName);
            hashMap.put(fullyQualifiedClassName.getName(), v);
            notify(fullyQualifiedClassName.getName());
        }
        return v;
    }

    public static Verifier getVerifier(String fullyQualifiedClassName) {
        Verifier v = hashMap.get(fullyQualifiedClassName);
        if (v == null) {
            v = new Verifier(fullyQualifiedClassName);
            hashMap.put(fullyQualifiedClassName, v);
            notify(fullyQualifiedClassName);
        }
        return v;
    }

    /**
     * Returns all Verifier instances created so far. This is useful when a Verifier
     * recursively lets the VerifierFactory create other Verifier instances and if
     * you want to verify the transitive hull of referenced class files.
     */
    public static Verifier[] getVerifiers() {
        Verifier[] vs = new Verifier[hashMap.size()];
        return hashMap.values().toArray(vs); // Because vs is big enough, vs is used to store the values into and returned!
    }

    /**
     * Notifies the observers of a newly generated Verifier.
     */
    private static void notify(String fullyQualifiedClassName) {
        // notify the observers
        for (VerifierFactoryObserver vfo : observers) {
            vfo.update(fullyQualifiedClassName);
        }
    }
}
