package com.wade.decompiler.util;

public abstract class AbstractRepository {
//    private static Repository repository = SyntheticRepository.getInstance();
//
//    public static JavaClass addClass(JavaClass clazz) {
//        JavaClass old = repository.findClass(clazz.getClassName());
//        repository.storeClass(clazz);
//        return old;
//    }
//
//    public static void clearCache() {
//        repository.clear();
//    }
//
//    public static JavaClass[] getInterfaces(JavaClass clazz) throws ClassNotFoundException {
//        return clazz.getAllInterfaces();
//    }
//
//    public static JavaClass[] getInterfaces(String class_name) throws ClassNotFoundException {
//        return getInterfaces(lookupClass(class_name));
//    }
//
//    public static com.wade.decompiler.util.Repository getRepository() {
//        return repository;
//    }
//
//    public static JavaClass[] getSuperClasses(JavaClass clazz) throws ClassNotFoundException {
//        return clazz.getSuperClasses();
//    }
//
//    public static JavaClass[] getSuperClasses(String class_name) throws ClassNotFoundException {
//        JavaClass jc = lookupClass(class_name);
//        return getSuperClasses(jc);
//    }
//
//    public static boolean implementationOf(JavaClass clazz, JavaClass inter) throws ClassNotFoundException {
//        return clazz.implementationOf(inter);
//    }
//
//    public static boolean implementationOf(JavaClass clazz, String inter) throws ClassNotFoundException {
//        return implementationOf(clazz, lookupClass(inter));
//    }
//
//    public static boolean implementationOf(String clazz, JavaClass inter) throws ClassNotFoundException {
//        return implementationOf(lookupClass(clazz), inter);
//    }
//
//    public static boolean implementationOf(String clazz, String inter) throws ClassNotFoundException {
//        return implementationOf(lookupClass(clazz), lookupClass(inter));
//    }
//
//    public static boolean instanceOf(JavaClass clazz, JavaClass super_class) throws ClassNotFoundException {
//        return clazz.instanceOf(super_class);
//    }
//
//    public static boolean instanceOf(JavaClass clazz, String super_class) throws ClassNotFoundException {
//        return instanceOf(clazz, lookupClass(super_class));
//    }
//
//    public static boolean instanceOf(String clazz, JavaClass super_class) throws ClassNotFoundException {
//        return instanceOf(lookupClass(clazz), super_class);
//    }
//
//    public static boolean instanceOf(String clazz, String super_class) throws ClassNotFoundException {
//        return instanceOf(lookupClass(clazz), lookupClass(super_class));
//    }
//
//    public static JavaClass lookupClass(Class<?> clazz) throws ClassNotFoundException {
//        return repository.loadClass(clazz);
//    }
//
//    public static JavaClass lookupClass(String class_name) throws ClassNotFoundException {
//        return repository.loadClass(class_name);
//    }
//
//    public static ClassFile lookupClassFile(String class_name) {
//        try {
//            ClassPath path = repository.getClassPath();
//            if (path == null) {
//                return null;
//            }
//            return path.getClassFile(class_name);
//        } catch (IOException e) {
//            return null;
//        }
//    }
//
//    public static void removeClass(JavaClass clazz) {
//        repository.removeClass(clazz);
//    }
//
//    public static void removeClass(String clazz) {
//        repository.removeClass(repository.findClass(clazz));
//    }
//
//    public static void setRepository(com.wade.decompiler.util.Repository rep) {
//        repository = rep;
//    }
}
