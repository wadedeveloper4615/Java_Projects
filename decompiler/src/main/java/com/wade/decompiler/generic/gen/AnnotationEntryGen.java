package com.wade.decompiler.generic.gen;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wade.decompiler.classfile.attribute.AnnotationEntry;
import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.attribute.RuntimeInvisibleAnnotations;
import com.wade.decompiler.classfile.attribute.RuntimeInvisibleParameterAnnotations;
import com.wade.decompiler.classfile.attribute.RuntimeVisibleAnnotations;
import com.wade.decompiler.classfile.attribute.RuntimeVisibleParameterAnnotations;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.classfile.element.ElementValuePair;
import com.wade.decompiler.generic.type.ObjectType;

public class AnnotationEntryGen {
    private int typeIndex;
    private List<ElementValuePairGen> evs;
    private ConstantPoolGen cpool;
    private boolean isRuntimeVisible = false;

    public AnnotationEntryGen(AnnotationEntry a, ConstantPoolGen cpool, boolean copyPoolEntries) {
        this.cpool = cpool;
        if (copyPoolEntries) {
            typeIndex = cpool.addUtf8(a.getAnnotationType());
        } else {
            typeIndex = a.getAnnotationTypeIndex();
        }
        isRuntimeVisible = a.isRuntimeVisible();
        evs = copyValues(a.getElementValuePairs(), cpool, copyPoolEntries);
    }

    private AnnotationEntryGen(ConstantPoolGen cpool) {
        this.cpool = cpool;
    }

    public AnnotationEntryGen(ObjectType type, List<ElementValuePairGen> elements, boolean vis, ConstantPoolGen cpool) {
        this.cpool = cpool;
        this.typeIndex = cpool.addUtf8(type.getSignature());
        evs = elements;
        isRuntimeVisible = vis;
    }

    public void addElementNameValuePair(ElementValuePairGen evp) {
        if (evs == null) {
            evs = new ArrayList<>();
        }
        evs.add(evp);
    }

    private List<ElementValuePairGen> copyValues(ElementValuePair[] in, ConstantPoolGen cpool, boolean copyPoolEntries) {
        List<ElementValuePairGen> out = new ArrayList<>();
        for (ElementValuePair nvp : in) {
            out.add(new ElementValuePairGen(nvp, cpool, copyPoolEntries));
        }
        return out;
    }

    public void dump(DataOutputStream dos) throws IOException {
        dos.writeShort(typeIndex); // u2 index of type name in cpool
        dos.writeShort(evs.size()); // u2 element_value pair count
        for (ElementValuePairGen envp : evs) {
            envp.dump(dos);
        }
    }

    public AnnotationEntry getAnnotation() {
        AnnotationEntry a = new AnnotationEntry(typeIndex, cpool.getConstantPool(), isRuntimeVisible);
        for (ElementValuePairGen element : evs) {
            a.addElementNameValuePair(element.getElementNameValuePair());
        }
        return a;
    }

    public int getTypeIndex() {
        return typeIndex;
    }

    public String getTypeName() {
        return getTypeSignature();// BCELBUG: Should I use this instead?
        // Utility.signatureToString(getTypeSignature());
    }

    public String getTypeSignature() {
        // ConstantClass c = (ConstantClass)cpool.getConstant(typeIndex);
        ConstantUtf8 utf8 = (ConstantUtf8) cpool.getConstant(typeIndex);
        return utf8.getBytes();
    }

    public List<ElementValuePairGen> getValues() {
        return evs;
    }

    public boolean isRuntimeVisible() {
        return isRuntimeVisible;
    }

    private void isRuntimeVisible(boolean b) {
        isRuntimeVisible = b;
    }

    public String toShortString() {
        StringBuilder s = new StringBuilder();
        s.append("@").append(getTypeName()).append("(");
        for (int i = 0; i < evs.size(); i++) {
            s.append(evs.get(i));
            if (i + 1 < evs.size()) {
                s.append(",");
            }
        }
        s.append(")");
        return s.toString();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(32); // CHECKSTYLE IGNORE MagicNumber
        s.append("AnnotationGen:[").append(getTypeName()).append(" #").append(evs.size()).append(" {");
        for (int i = 0; i < evs.size(); i++) {
            s.append(evs.get(i));
            if (i + 1 < evs.size()) {
                s.append(",");
            }
        }
        s.append("}]");
        return s.toString();
    }

    public static Attribute[] getAnnotationAttributes(ConstantPoolGen cp, AnnotationEntryGen[] annotationEntryGens) {
        if (annotationEntryGens.length == 0) {
            return new Attribute[0];
        }
        try {
            int countVisible = 0;
            int countInvisible = 0;
            // put the annotations in the right output stream
            for (AnnotationEntryGen a : annotationEntryGens) {
                if (a.isRuntimeVisible()) {
                    countVisible++;
                } else {
                    countInvisible++;
                }
            }
            ByteArrayOutputStream rvaBytes = new ByteArrayOutputStream();
            ByteArrayOutputStream riaBytes = new ByteArrayOutputStream();
            try (DataOutputStream rvaDos = new DataOutputStream(rvaBytes); DataOutputStream riaDos = new DataOutputStream(riaBytes)) {
                rvaDos.writeShort(countVisible);
                riaDos.writeShort(countInvisible);
                // put the annotations in the right output stream
                for (AnnotationEntryGen a : annotationEntryGens) {
                    if (a.isRuntimeVisible()) {
                        a.dump(rvaDos);
                    } else {
                        a.dump(riaDos);
                    }
                }
            }
            byte[] rvaData = rvaBytes.toByteArray();
            byte[] riaData = riaBytes.toByteArray();
            int rvaIndex = -1;
            int riaIndex = -1;
            if (rvaData.length > 2) {
                rvaIndex = cp.addUtf8("RuntimeVisibleAnnotations");
            }
            if (riaData.length > 2) {
                riaIndex = cp.addUtf8("RuntimeInvisibleAnnotations");
            }
            List<Attribute> newAttributes = new ArrayList<>();
            if (rvaData.length > 2) {
                newAttributes.add(new RuntimeVisibleAnnotations(rvaIndex, rvaData.length, new DataInputStream(new ByteArrayInputStream(rvaData)), cp.getConstantPool()));
            }
            if (riaData.length > 2) {
                newAttributes.add(new RuntimeInvisibleAnnotations(riaIndex, riaData.length, new DataInputStream(new ByteArrayInputStream(riaData)), cp.getConstantPool()));
            }
            return newAttributes.toArray(new Attribute[newAttributes.size()]);
        } catch (IOException e) {
            System.err.println("IOException whilst processing annotations");
            e.printStackTrace();
        }
        return null;
    }

    public static Attribute[] getParameterAnnotationAttributes(ConstantPoolGen cp, List<AnnotationEntryGen>[] vec) {
        int[] visCount = new int[vec.length];
        int totalVisCount = 0;
        int[] invisCount = new int[vec.length];
        int totalInvisCount = 0;
        try {
            for (int i = 0; i < vec.length; i++) {
                if (vec[i] != null) {
                    for (AnnotationEntryGen element : vec[i]) {
                        if (element.isRuntimeVisible()) {
                            visCount[i]++;
                            totalVisCount++;
                        } else {
                            invisCount[i]++;
                            totalInvisCount++;
                        }
                    }
                }
            }
            // Lets do the visible ones
            ByteArrayOutputStream rvaBytes = new ByteArrayOutputStream();
            try (DataOutputStream rvaDos = new DataOutputStream(rvaBytes)) {
                rvaDos.writeByte(vec.length); // First goes number of parameters
                for (int i = 0; i < vec.length; i++) {
                    rvaDos.writeShort(visCount[i]);
                    if (visCount[i] > 0) {
                        for (AnnotationEntryGen element : vec[i]) {
                            if (element.isRuntimeVisible()) {
                                element.dump(rvaDos);
                            }
                        }
                    }
                }
            }
            // Lets do the invisible ones
            ByteArrayOutputStream riaBytes = new ByteArrayOutputStream();
            try (DataOutputStream riaDos = new DataOutputStream(riaBytes)) {
                riaDos.writeByte(vec.length); // First goes number of parameters
                for (int i = 0; i < vec.length; i++) {
                    riaDos.writeShort(invisCount[i]);
                    if (invisCount[i] > 0) {
                        for (AnnotationEntryGen element : vec[i]) {
                            if (!element.isRuntimeVisible()) {
                                element.dump(riaDos);
                            }
                        }
                    }
                }
            }
            byte[] rvaData = rvaBytes.toByteArray();
            byte[] riaData = riaBytes.toByteArray();
            int rvaIndex = -1;
            int riaIndex = -1;
            if (totalVisCount > 0) {
                rvaIndex = cp.addUtf8("RuntimeVisibleParameterAnnotations");
            }
            if (totalInvisCount > 0) {
                riaIndex = cp.addUtf8("RuntimeInvisibleParameterAnnotations");
            }
            List<Attribute> newAttributes = new ArrayList<>();
            if (totalVisCount > 0) {
                newAttributes.add(new RuntimeVisibleParameterAnnotations(rvaIndex, rvaData.length, new DataInputStream(new ByteArrayInputStream(rvaData)), cp.getConstantPool()));
            }
            if (totalInvisCount > 0) {
                newAttributes.add(new RuntimeInvisibleParameterAnnotations(riaIndex, riaData.length, new DataInputStream(new ByteArrayInputStream(riaData)), cp.getConstantPool()));
            }
            return newAttributes.toArray(new Attribute[newAttributes.size()]);
        } catch (IOException e) {
            System.err.println("IOException whilst processing parameter annotations");
            e.printStackTrace();
        }
        return null;
    }

    public static AnnotationEntryGen read(DataInput dis, ConstantPoolGen cpool, boolean b) throws IOException {
        AnnotationEntryGen a = new AnnotationEntryGen(cpool);
        a.typeIndex = dis.readUnsignedShort();
        int elemValuePairCount = dis.readUnsignedShort();
        for (int i = 0; i < elemValuePairCount; i++) {
            int nidx = dis.readUnsignedShort();
            a.addElementNameValuePair(new ElementValuePairGen(nidx, ElementValueGen.readElementValue(dis, cpool), cpool));
        }
        a.isRuntimeVisible(b);
        return a;
    }
}
