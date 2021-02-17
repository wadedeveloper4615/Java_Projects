package com.wade.decompiler.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Hashtable;

import com.wade.decompiler.classfile.ClassParser;
import com.wade.decompiler.classfile.JavaClass;
import com.wade.decompiler.classfile.constant.ConstantClass;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassFileConstants;

@Deprecated
public class ClassLoader extends java.lang.ClassLoader {
    private static String BCEL_TOKEN = "$$BCEL$$";
    public static String[] DEFAULT_IGNORED_PACKAGES = { "java.", "javax.", "sun." };
    private Hashtable<String, Class<?>> classes = new Hashtable<>();
    // Hashtable is synchronized thus thread-safe
    private String[] ignored_packages;
    private Repository repository = SyntheticRepository.getInstance();

    public ClassLoader() {
        this(DEFAULT_IGNORED_PACKAGES);
    }

    public ClassLoader(java.lang.ClassLoader deferTo) {
        super(deferTo);
        this.ignored_packages = DEFAULT_IGNORED_PACKAGES;
        this.repository = new ClassLoaderRepository(deferTo);
    }

    public ClassLoader(java.lang.ClassLoader deferTo, String[] ignored_packages) {
        this(ignored_packages);
        this.repository = new ClassLoaderRepository(deferTo);
    }

    public ClassLoader(String[] ignored_packages) {
        this.ignored_packages = ignored_packages;
    }

    protected JavaClass createClass(String class_name) {
        int index = class_name.indexOf(BCEL_TOKEN);
        String real_name = class_name.substring(index + BCEL_TOKEN.length());
        JavaClass clazz = null;
        try {
            byte[] bytes = Utility.decode(real_name, true);
            ClassParser parser = new ClassParser(new ByteArrayInputStream(bytes), "foo");
            clazz = parser.parse();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        // Adapt the class name to the passed value
        ConstantPool cp = clazz.getConstantPool();
        ConstantClass cl = (ConstantClass) cp.getConstant(clazz.getClassNameIndex(), ClassFileConstants.CONSTANT_Class);
        ConstantUtf8 name = (ConstantUtf8) cp.getConstant(cl.getNameIndex(), ClassFileConstants.CONSTANT_Utf8);
        name.setBytes(class_name.replace('.', '/'));
        return clazz;
    }

    @Override
    protected Class<?> loadClass(String class_name, boolean resolve) throws ClassNotFoundException {
        Class<?> cl = null;
        if ((cl = classes.get(class_name)) == null) {
            for (String ignored_package : ignored_packages) {
                if (class_name.startsWith(ignored_package)) {
                    cl = getParent().loadClass(class_name);
                    break;
                }
            }
            if (cl == null) {
                JavaClass clazz = null;
                if (class_name.contains(BCEL_TOKEN)) {
                    clazz = createClass(class_name);
                } else if ((clazz = repository.loadClass(class_name)) != null) {
                    clazz = modifyClass(clazz);
                } else {
                    throw new ClassNotFoundException(class_name);
                }
                if (clazz != null) {
                    byte[] bytes = clazz.getBytes();
                    cl = defineClass(class_name, bytes, 0, bytes.length);
                } else {
                    cl = Class.forName(class_name);
                }
            }
            if (resolve) {
                resolveClass(cl);
            }
        }
        classes.put(class_name, cl);
        return cl;
    }

    protected JavaClass modifyClass(JavaClass clazz) {
        return clazz;
    }
}
