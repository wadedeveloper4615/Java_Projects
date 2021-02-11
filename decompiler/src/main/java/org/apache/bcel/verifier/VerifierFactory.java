
package org.apache.bcel.verifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.bcel.ClassFileName;

public class VerifierFactory {

    private static Map<String, Verifier> hashMap = new HashMap<>();

    private static List<VerifierFactoryObserver> observers = new Vector<>();

    private VerifierFactory() {
    }

    public static void attach(VerifierFactoryObserver o) {
        observers.add(o);
    }

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

    public static Verifier[] getVerifiers() {
        Verifier[] vs = new Verifier[hashMap.size()];
        return hashMap.values().toArray(vs); // Because vs is big enough, vs is used to store the values into and returned!
    }

    private static void notify(String fullyQualifiedClassName) {
        // notify the observers
        for (VerifierFactoryObserver vfo : observers) {
            vfo.update(fullyQualifiedClassName);
        }
    }
}
