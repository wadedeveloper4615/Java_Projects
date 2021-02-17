package com.wade.decompiler.generic.gen;

import java.util.HashMap;
import java.util.Map;

import com.wade.decompiler.classfile.Constant;
import com.wade.decompiler.classfile.ConstantCP;
import com.wade.decompiler.classfile.ConstantClass;
import com.wade.decompiler.classfile.ConstantDouble;
import com.wade.decompiler.classfile.ConstantFieldref;
import com.wade.decompiler.classfile.ConstantFloat;
import com.wade.decompiler.classfile.ConstantInteger;
import com.wade.decompiler.classfile.ConstantInterfaceMethodref;
import com.wade.decompiler.classfile.ConstantInvokeDynamic;
import com.wade.decompiler.classfile.ConstantLong;
import com.wade.decompiler.classfile.ConstantMethodHandle;
import com.wade.decompiler.classfile.ConstantMethodType;
import com.wade.decompiler.classfile.ConstantMethodref;
import com.wade.decompiler.classfile.ConstantModule;
import com.wade.decompiler.classfile.ConstantNameAndType;
import com.wade.decompiler.classfile.ConstantPackage;
import com.wade.decompiler.classfile.ConstantPool;
import com.wade.decompiler.classfile.ConstantString;
import com.wade.decompiler.classfile.ConstantUtf8;
import com.wade.decompiler.generic.type.ArrayType;
import com.wade.decompiler.generic.type.ObjectType;

public class ConstantPoolGen {
    private static int DEFAULT_BUFFER_SIZE = 256;

    private static String METHODREF_DELIM = ":";
    private static String IMETHODREF_DELIM = "#";
    private static String FIELDREF_DELIM = "&";
    private static String NAT_DELIM = "%"; // Name and Type

    private static class Index {
        int index;

        Index(int i) {
            index = i;
        }
    }

    @Deprecated
    protected int size;
    @Deprecated
    protected Constant[] constants;
    @Deprecated
    protected int index = 1; // First entry (0) used by JVM
    private Map<String, Index> stringTable = new HashMap<>();
    private Map<String, Index> classTable = new HashMap<>();
    private Map<String, Index> utf8Table = new HashMap<>();
    private Map<String, Index> natTable = new HashMap<>();
    private Map<String, Index> cpTable = new HashMap<>();

    public ConstantPoolGen() {
        size = DEFAULT_BUFFER_SIZE;
        constants = new Constant[size];
    }

    public ConstantPoolGen(Constant[] cs) {
        StringBuilder sb = new StringBuilder(DEFAULT_BUFFER_SIZE);
        size = Math.max(DEFAULT_BUFFER_SIZE, cs.length + 64);
        constants = new Constant[size];
        System.arraycopy(cs, 0, constants, 0, cs.length);
        if (cs.length > 0) {
            index = cs.length;
        }
        for (int i = 1; i < index; i++) {
            Constant c = constants[i];
            if (c instanceof ConstantString) {
                ConstantString s = (ConstantString) c;
                ConstantUtf8 u8 = (ConstantUtf8) constants[s.getStringIndex()];
                String key = u8.getBytes();
                if (!stringTable.containsKey(key)) {
                    stringTable.put(key, new Index(i));
                }
            } else if (c instanceof ConstantClass) {
                ConstantClass s = (ConstantClass) c;
                ConstantUtf8 u8 = (ConstantUtf8) constants[s.getNameIndex()];
                String key = u8.getBytes();
                if (!classTable.containsKey(key)) {
                    classTable.put(key, new Index(i));
                }
            } else if (c instanceof ConstantNameAndType) {
                ConstantNameAndType n = (ConstantNameAndType) c;
                ConstantUtf8 u8 = (ConstantUtf8) constants[n.getNameIndex()];
                ConstantUtf8 u8_2 = (ConstantUtf8) constants[n.getSignatureIndex()];
                sb.append(u8.getBytes());
                sb.append(NAT_DELIM);
                sb.append(u8_2.getBytes());
                String key = sb.toString();
                sb.delete(0, sb.length());
                if (!natTable.containsKey(key)) {
                    natTable.put(key, new Index(i));
                }
            } else if (c instanceof ConstantUtf8) {
                ConstantUtf8 u = (ConstantUtf8) c;
                String key = u.getBytes();
                if (!utf8Table.containsKey(key)) {
                    utf8Table.put(key, new Index(i));
                }
            } else if (c instanceof ConstantCP) {
                ConstantCP m = (ConstantCP) c;
                String class_name;
                ConstantUtf8 u8;
                if (c instanceof ConstantInvokeDynamic) {
                    class_name = Integer.toString(((ConstantInvokeDynamic) m).getBootstrapMethodAttrIndex());
                    // since name can't begin with digit, can use
                    // METHODREF_DELIM with out fear of duplicates.
                } else {
                    ConstantClass clazz = (ConstantClass) constants[m.getClassIndex()];
                    u8 = (ConstantUtf8) constants[clazz.getNameIndex()];
                    class_name = u8.getBytes().replace('/', '.');
                }
                ConstantNameAndType n = (ConstantNameAndType) constants[m.getNameAndTypeIndex()];
                u8 = (ConstantUtf8) constants[n.getNameIndex()];
                String method_name = u8.getBytes();
                u8 = (ConstantUtf8) constants[n.getSignatureIndex()];
                String signature = u8.getBytes();
                String delim = METHODREF_DELIM;
                if (c instanceof ConstantInterfaceMethodref) {
                    delim = IMETHODREF_DELIM;
                } else if (c instanceof ConstantFieldref) {
                    delim = FIELDREF_DELIM;
                }
                sb.append(class_name);
                sb.append(delim);
                sb.append(method_name);
                sb.append(delim);
                sb.append(signature);
                String key = sb.toString();
                sb.delete(0, sb.length());
                if (!cpTable.containsKey(key)) {
                    cpTable.put(key, new Index(i));
                }
            } else if (c == null) { // entries may be null
                // nothing to do
            } else if (c instanceof ConstantInteger) {
                // nothing to do
            } else if (c instanceof ConstantLong) {
                // nothing to do
            } else if (c instanceof ConstantFloat) {
                // nothing to do
            } else if (c instanceof ConstantDouble) {
                // nothing to do
            } else if (c instanceof ConstantMethodType) {
                // TODO should this be handled somehow?
            } else if (c instanceof ConstantMethodHandle) {
                // TODO should this be handled somehow?
            } else if (c instanceof ConstantModule) {
                // TODO should this be handled somehow?
            } else if (c instanceof ConstantPackage) {
                // TODO should this be handled somehow?
            } else {
                assert false : "Unexpected constant type: " + c.getClass().getName();
            }
        }
    }

    public ConstantPoolGen(ConstantPool cp) {
        this(cp.getConstantPool());
    }

    public int addArrayClass(ArrayType type) {
        return addClass_(type.getSignature());
    }

    public int addClass(ObjectType type) {
        return addClass(type.getClassName());
    }

    public int addClass(String str) {
        return addClass_(str.replace('.', '/'));
    }

    private int addClass_(String clazz) {
        int ret;
        if ((ret = lookupClass(clazz)) != -1) {
            return ret; // Already in CP
        }
        adjustSize();
        ConstantClass c = new ConstantClass(addUtf8(clazz));
        ret = index;
        constants[index++] = c;
        if (!classTable.containsKey(clazz)) {
            classTable.put(clazz, new Index(ret));
        }
        return ret;
    }

    public int addConstant(Constant c, ConstantPoolGen cp) {
        Constant[] constants = cp.getConstantPool().getConstantPool();
        switch (c.getTag()) {
            case CONSTANT_String: {
                ConstantString s = (ConstantString) c;
                ConstantUtf8 u8 = (ConstantUtf8) constants[s.getStringIndex()];
                return addString(u8.getBytes());
            }
            case CONSTANT_Class: {
                ConstantClass s = (ConstantClass) c;
                ConstantUtf8 u8 = (ConstantUtf8) constants[s.getNameIndex()];
                return addClass(u8.getBytes());
            }
            case CONSTANT_NameAndType: {
                ConstantNameAndType n = (ConstantNameAndType) c;
                ConstantUtf8 u8 = (ConstantUtf8) constants[n.getNameIndex()];
                ConstantUtf8 u8_2 = (ConstantUtf8) constants[n.getSignatureIndex()];
                return addNameAndType(u8.getBytes(), u8_2.getBytes());
            }
            case CONSTANT_Utf8:
                return addUtf8(((ConstantUtf8) c).getBytes());
            case CONSTANT_Double:
                return addDouble(((ConstantDouble) c).getBytes());
            case CONSTANT_Float:
                return addFloat(((ConstantFloat) c).getBytes());
            case CONSTANT_Long:
                return addLong(((ConstantLong) c).getBytes());
            case CONSTANT_Integer:
                return addInteger(((ConstantInteger) c).getBytes());
            case CONSTANT_InterfaceMethodref:
            case CONSTANT_Methodref:
            case CONSTANT_Fieldref: {
                ConstantCP m = (ConstantCP) c;
                ConstantClass clazz = (ConstantClass) constants[m.getClassIndex()];
                ConstantNameAndType n = (ConstantNameAndType) constants[m.getNameAndTypeIndex()];
                ConstantUtf8 u8 = (ConstantUtf8) constants[clazz.getNameIndex()];
                String class_name = u8.getBytes().replace('/', '.');
                u8 = (ConstantUtf8) constants[n.getNameIndex()];
                String name = u8.getBytes();
                u8 = (ConstantUtf8) constants[n.getSignatureIndex()];
                String signature = u8.getBytes();
                switch (c.getTag()) {
                    case CONSTANT_InterfaceMethodref:
                        return addInterfaceMethodref(class_name, name, signature);
                    case CONSTANT_Methodref:
                        return addMethodref(class_name, name, signature);
                    case CONSTANT_Fieldref:
                        return addFieldref(class_name, name, signature);
                    default: // Never reached
                        throw new IllegalArgumentException("Unknown constant type " + c);
                }
            }
            default: // Never reached
                throw new IllegalArgumentException("Unknown constant type " + c);
        }
    }

    public int addDouble(double n) {
        int ret;
        if ((ret = lookupDouble(n)) != -1) {
            return ret; // Already in CP
        }
        adjustSize();
        ret = index;
        constants[index] = new ConstantDouble(n);
        index += 2; // Wastes one entry according to spec
        return ret;
    }

    public int addFieldref(String class_name, String field_name, String signature) {
        int ret;
        int class_index;
        int name_and_type_index;
        if ((ret = lookupFieldref(class_name, field_name, signature)) != -1) {
            return ret; // Already in CP
        }
        adjustSize();
        class_index = addClass(class_name);
        name_and_type_index = addNameAndType(field_name, signature);
        ret = index;
        constants[index++] = new ConstantFieldref(class_index, name_and_type_index);
        String key = class_name + FIELDREF_DELIM + field_name + FIELDREF_DELIM + signature;
        if (!cpTable.containsKey(key)) {
            cpTable.put(key, new Index(ret));
        }
        return ret;
    }

    public int addFloat(float n) {
        int ret;
        if ((ret = lookupFloat(n)) != -1) {
            return ret; // Already in CP
        }
        adjustSize();
        ret = index;
        constants[index++] = new ConstantFloat(n);
        return ret;
    }

    public int addInteger(int n) {
        int ret;
        if ((ret = lookupInteger(n)) != -1) {
            return ret; // Already in CP
        }
        adjustSize();
        ret = index;
        constants[index++] = new ConstantInteger(n);
        return ret;
    }

    public int addInterfaceMethodref(MethodGen method) {
        return addInterfaceMethodref(method.getClassName(), method.getName(), method.getSignature());
    }

    public int addInterfaceMethodref(String class_name, String method_name, String signature) {
        int ret;
        int class_index;
        int name_and_type_index;
        if ((ret = lookupInterfaceMethodref(class_name, method_name, signature)) != -1) {
            return ret; // Already in CP
        }
        adjustSize();
        class_index = addClass(class_name);
        name_and_type_index = addNameAndType(method_name, signature);
        ret = index;
        constants[index++] = new ConstantInterfaceMethodref(class_index, name_and_type_index);
        String key = class_name + IMETHODREF_DELIM + method_name + IMETHODREF_DELIM + signature;
        if (!cpTable.containsKey(key)) {
            cpTable.put(key, new Index(ret));
        }
        return ret;
    }

    public int addLong(long n) {
        int ret;
        if ((ret = lookupLong(n)) != -1) {
            return ret; // Already in CP
        }
        adjustSize();
        ret = index;
        constants[index] = new ConstantLong(n);
        index += 2; // Wastes one entry according to spec
        return ret;
    }

    public int addMethodref(MethodGen method) {
        return addMethodref(method.getClassName(), method.getName(), method.getSignature());
    }

    public int addMethodref(String class_name, String method_name, String signature) {
        int ret;
        int class_index;
        int name_and_type_index;
        if ((ret = lookupMethodref(class_name, method_name, signature)) != -1) {
            return ret; // Already in CP
        }
        adjustSize();
        name_and_type_index = addNameAndType(method_name, signature);
        class_index = addClass(class_name);
        ret = index;
        constants[index++] = new ConstantMethodref(class_index, name_and_type_index);
        String key = class_name + METHODREF_DELIM + method_name + METHODREF_DELIM + signature;
        if (!cpTable.containsKey(key)) {
            cpTable.put(key, new Index(ret));
        }
        return ret;
    }

    public int addNameAndType(String name, String signature) {
        int ret;
        int name_index;
        int signature_index;
        if ((ret = lookupNameAndType(name, signature)) != -1) {
            return ret; // Already in CP
        }
        adjustSize();
        name_index = addUtf8(name);
        signature_index = addUtf8(signature);
        ret = index;
        constants[index++] = new ConstantNameAndType(name_index, signature_index);
        String key = name + NAT_DELIM + signature;
        if (!natTable.containsKey(key)) {
            natTable.put(key, new Index(ret));
        }
        return ret;
    }

    public int addString(String str) {
        int ret;
        if ((ret = lookupString(str)) != -1) {
            return ret; // Already in CP
        }
        int utf8 = addUtf8(str);
        adjustSize();
        ConstantString s = new ConstantString(utf8);
        ret = index;
        constants[index++] = s;
        if (!stringTable.containsKey(str)) {
            stringTable.put(str, new Index(ret));
        }
        return ret;
    }

    public int addUtf8(String n) {
        int ret;
        if ((ret = lookupUtf8(n)) != -1) {
            return ret; // Already in CP
        }
        adjustSize();
        ret = index;
        constants[index++] = new ConstantUtf8(n);
        if (!utf8Table.containsKey(n)) {
            utf8Table.put(n, new Index(ret));
        }
        return ret;
    }

    protected void adjustSize() {
        if (index + 3 >= size) {
            Constant[] cs = constants;
            size *= 2;
            constants = new Constant[size];
            System.arraycopy(cs, 0, constants, 0, index);
        }
    }

    public Constant getConstant(int i) {
        return constants[i];
    }

//    public ConstantPool getConstantPool() {
//        return new ConstantPool(constants);
//    }
//
    public ConstantPool getConstantPool() {
        Constant[] cs = new Constant[index];
        System.arraycopy(constants, 0, cs, 0, index);
        return new ConstantPool(cs);
    }

    public int getSize() {
        return index;
    }

    public int lookupClass(String str) {
        Index index = classTable.get(str.replace('.', '/'));
        return (index != null) ? index.index : -1;
    }

    public int lookupDouble(double n) {
        long bits = Double.doubleToLongBits(n);
        for (int i = 1; i < index; i++) {
            if (constants[i] instanceof ConstantDouble) {
                ConstantDouble c = (ConstantDouble) constants[i];
                if (Double.doubleToLongBits(c.getBytes()) == bits) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int lookupFieldref(String class_name, String field_name, String signature) {
        Index index = cpTable.get(class_name + FIELDREF_DELIM + field_name + FIELDREF_DELIM + signature);
        return (index != null) ? index.index : -1;
    }

    public int lookupFloat(float n) {
        int bits = Float.floatToIntBits(n);
        for (int i = 1; i < index; i++) {
            if (constants[i] instanceof ConstantFloat) {
                ConstantFloat c = (ConstantFloat) constants[i];
                if (Float.floatToIntBits(c.getBytes()) == bits) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int lookupInteger(int n) {
        for (int i = 1; i < index; i++) {
            if (constants[i] instanceof ConstantInteger) {
                ConstantInteger c = (ConstantInteger) constants[i];
                if (c.getBytes() == n) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int lookupInterfaceMethodref(MethodGen method) {
        return lookupInterfaceMethodref(method.getClassName(), method.getName(), method.getSignature());
    }

    public int lookupInterfaceMethodref(String class_name, String method_name, String signature) {
        Index index = cpTable.get(class_name + IMETHODREF_DELIM + method_name + IMETHODREF_DELIM + signature);
        return (index != null) ? index.index : -1;
    }

    public int lookupLong(long n) {
        for (int i = 1; i < index; i++) {
            if (constants[i] instanceof ConstantLong) {
                ConstantLong c = (ConstantLong) constants[i];
                if (c.getBytes() == n) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int lookupMethodref(MethodGen method) {
        return lookupMethodref(method.getClassName(), method.getName(), method.getSignature());
    }

    public int lookupMethodref(String class_name, String method_name, String signature) {
        Index index = cpTable.get(class_name + METHODREF_DELIM + method_name + METHODREF_DELIM + signature);
        return (index != null) ? index.index : -1;
    }

    public int lookupNameAndType(String name, String signature) {
        Index _index = natTable.get(name + NAT_DELIM + signature);
        return (_index != null) ? _index.index : -1;
    }

    public int lookupString(String str) {
        Index index = stringTable.get(str);
        return (index != null) ? index.index : -1;
    }

    public int lookupUtf8(String n) {
        Index index = utf8Table.get(n);
        return (index != null) ? index.index : -1;
    }

    public void setConstant(int i, Constant c) {
        constants[i] = c;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        for (int i = 1; i < index; i++) {
            buf.append(i).append(")").append(constants[i]).append("\n");
        }
        return buf.toString();
    }
}
