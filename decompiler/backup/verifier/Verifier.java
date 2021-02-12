
package org.apache.bcel.verifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.bcel.ClassFileName;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.verifier.statics.Pass1Verifier;
import org.apache.bcel.verifier.statics.Pass2Verifier;
import org.apache.bcel.verifier.statics.Pass3aVerifier;
import org.apache.bcel.verifier.structurals.Pass3bVerifier;

public class Verifier {
    private ClassFileName classname;
    private Pass1Verifier p1v;
    private Pass2Verifier p2v;
    private Map<String, Pass3aVerifier> p3avs = new HashMap<>();
    private Map<String, Pass3bVerifier> p3bvs = new HashMap<>();

    Verifier(ClassFileName fully_qualified_classname) {
        classname = fully_qualified_classname;
        flush();
    }

    Verifier(String fully_qualified_classname) {
        classname = new ClassFileName(fully_qualified_classname, -1);
        flush();
    }

    public VerificationResult doPass1() {
        if (p1v == null) {
            p1v = new Pass1Verifier(this);
        }
        return p1v.verify();
    }

    public VerificationResult doPass2() {
        if (p2v == null) {
            p2v = new Pass2Verifier(this);
        }
        return p2v.verify();
    }

    public VerificationResult doPass3a(int method_no) {
        String key = Integer.toString(method_no);
        Pass3aVerifier p3av;
        p3av = p3avs.get(key);
        if (p3avs.get(key) == null) {
            p3av = new Pass3aVerifier(this, method_no);
            p3avs.put(key, p3av);
        }
        return p3av.verify();
    }

    public VerificationResult doPass3b(int method_no) {
        String key = Integer.toString(method_no);
        Pass3bVerifier p3bv;
        p3bv = p3bvs.get(key);
        if (p3bvs.get(key) == null) {
            p3bv = new Pass3bVerifier(this, method_no);
            p3bvs.put(key, p3bv);
        }
        return p3bv.verify();
    }

    public void flush() {
        p1v = null;
        p2v = null;
        p3avs.clear();
        p3bvs.clear();
    }

    public ClassFileName getClassName() {
        return classname;
    }

    public String[] getMessages() throws ClassNotFoundException {
        List<String> messages = new ArrayList<>();
        if (p1v != null) {
            String[] p1m = p1v.getMessages();
            for (String element : p1m) {
                messages.add("Pass 1: " + element);
            }
        }
        if (p2v != null) {
            String[] p2m = p2v.getMessages();
            for (String element : p2m) {
                messages.add("Pass 2: " + element);
            }
        }
        for (Pass3aVerifier pv : p3avs.values()) {
            String[] p3am = pv.getMessages();
            int meth = pv.getMethodNo();
            for (String element : p3am) {
                messages.add("Pass 3a, method " + meth + " ('" + org.apache.bcel.Repository.lookupClass(classname.getName()).getMethods()[meth] + "'): " + element);
            }
        }
        for (Pass3bVerifier pv : p3bvs.values()) {
            String[] p3bm = pv.getMessages();
            int meth = pv.getMethodNo();
            for (String element : p3bm) {
                messages.add("Pass 3b, method " + meth + " ('" + org.apache.bcel.Repository.lookupClass(classname.getName()).getMethods()[meth] + "'): " + element);
            }
        }

        return messages.toArray(new String[messages.size()]);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("JustIce by Enver Haase, (C) 2001-2002.\n<http://bcel.sourceforge.net>\n<https://commons.apache.org/bcel>\n");
        for (int index = 0; index < args.length; index++) {
            try {
                if (args[index].endsWith(".class")) {
                    int dotclasspos = args[index].lastIndexOf(".class");
                    if (dotclasspos != -1) {
                        args[index] = args[index].substring(0, dotclasspos);
                    }
                }
                args[index] = args[index].replace('/', '.');
                System.out.println("Now verifying: " + args[index] + "\n");
                verifyType(args[index]);
                org.apache.bcel.Repository.clearCache();
                System.gc();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    static void verifyType(String fullyQualifiedClassName) throws ClassNotFoundException, IOException {
        Verifier verifier = VerifierFactory.getVerifier(fullyQualifiedClassName);
        VerificationResult verificationResult;
        verificationResult = verifier.doPass1();
        System.out.println("Pass 1:\n" + verificationResult);
        verificationResult = verifier.doPass2();
        System.out.println("Pass 2:\n" + verificationResult);
        if (verificationResult == VerificationResult.VR_OK) {
            JavaClass jc = org.apache.bcel.Repository.lookupClass(fullyQualifiedClassName);
            for (int i = 0; i < jc.getMethods().length; i++) {
                verificationResult = verifier.doPass3a(i);
                System.out.println("Pass 3a, method number " + i + " ['" + jc.getMethods()[i] + "']:\n" + verificationResult);
                verificationResult = verifier.doPass3b(i);
                System.out.println("Pass 3b, method number " + i + " ['" + jc.getMethods()[i] + "']:\n" + verificationResult);
            }
        }
        System.out.println("Warnings:");
        String[] warnings = verifier.getMessages();
        if (warnings.length == 0) {
            System.out.println("<none>");
        }
        for (String warning : warnings) {
            System.out.println(warning);
        }
        System.out.println("\n");
        // avoid swapping.
        verifier.flush();
    }
}