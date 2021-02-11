package org.apache.bcel.classfile;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.bcel.Const;
import org.apache.bcel.enums.Version;

public class ClassParser {
    private static int BUFSIZE = 8192;
    private DataInputStream dataInputStream;
    private boolean fileOwned;
    private String fileName;
    private String zipFile;
    private int classNameIndex;
    private int superclassNameIndex;
    private Version version;
    private int accessFlags;
    private int[] interfaces;
    private ConstantPool constantPool;
    private Field[] fields;
    private Method[] methods;
    private Attribute[] attributes;
    private boolean isZip;

    public ClassParser(InputStream inputStream, String fileName) {
        this.fileName = fileName;
        fileOwned = false;
        String clazz = inputStream.getClass().getName(); // Not a very clean solution ...
        isZip = clazz.startsWith("java.util.zip.") || clazz.startsWith("java.util.jar.");
        if (inputStream instanceof DataInputStream) {
            this.dataInputStream = (DataInputStream) inputStream;
        } else {
            this.dataInputStream = new DataInputStream(new BufferedInputStream(inputStream, BUFSIZE));
        }
    }

    /**
     * Parses class from given .class file.
     *
     * @param fileName file name
     */
    public ClassParser(String fileName) {
        isZip = false;
        this.fileName = fileName;
        fileOwned = true;
    }

    /**
     * Parses class from given .class file in a ZIP-archive
     *
     * @param zipFile  zip file name
     * @param fileName file name
     */
    public ClassParser(String zipFile, String fileName) {
        isZip = true;
        fileOwned = true;
        this.zipFile = zipFile;
        this.fileName = fileName;
    }

    /**
     * Parses the given Java class file and return an object that represents the
     * contained data, i.e., constants, methods, fields and commands. A
     * <em>ClassFormatException</em> is raised, if the file is not a valid .class
     * file. (This does not include verification of the byte code as it is performed
     * by the java interpreter).
     *
     * @return Class object representing the parsed class file
     * @throws IOException
     * @throws ClassFormatException
     */
    public JavaClass parse() throws IOException, ClassFormatException {
        ZipFile zip = null;
        try {
            if (fileOwned) {
                if (isZip) {
                    zip = new ZipFile(zipFile);
                    ZipEntry entry = zip.getEntry(fileName);

                    if (entry == null) {
                        throw new IOException("File " + fileName + " not found");
                    }

                    dataInputStream = new DataInputStream(new BufferedInputStream(zip.getInputStream(entry), BUFSIZE));
                } else {
                    dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName), BUFSIZE));
                }
            }
            /****************** Read headers ********************************/
            // Check magic tag of class file
            readID();
            // Get compiler version
            readVersion();
            /****************** Read constant pool and related **************/
            // Read constant pool entries
            readConstantPool();
            // Get class information
            readClassInfo();
            // Get interface information, i.e., implemented interfaces
            readInterfaces();
            /****************** Read class fields and methods ***************/
            // Read class fields, i.e., the variables of the class
            readFields();
            // Read class methods, i.e., the functions in the class
            readMethods();
            // Read class attributes
            readAttributes();
            // Check for unknown variables
            // Unknown[] u = Unknown.getUnknownAttributes();
            // for (int i=0; i < u.length; i++)
            // System.err.println("WARNING: " + u[i]);
            // Everything should have been read now
            // if(file.available() > 0) {
            // int bytes = file.available();
            // byte[] buf = new byte[bytes];
            // file.read(buf);
            // if(!(isZip && (buf.length == 1))) {
            // System.err.println("WARNING: Trailing garbage at end of " + fileName);
            // System.err.println(bytes + " extra bytes: " + Utility.toHexString(buf));
            // }
            // }
        } finally {
            // Read everything of interest, so close the file
            if (fileOwned) {
                try {
                    if (dataInputStream != null) {
                        dataInputStream.close();
                    }
                } catch (IOException ioe) {
                    // ignore close exceptions
                }
            }
            try {
                if (zip != null) {
                    zip.close();
                }
            } catch (IOException ioe) {
                // ignore close exceptions
            }
        }
        // Return the information we have gathered in a new object
        return new JavaClass(classNameIndex, superclassNameIndex, fileName, version, accessFlags, constantPool, interfaces, fields, methods, attributes, isZip ? JavaClass.ZIP : JavaClass.FILE);
    }

    /**
     * Reads information about the attributes of the class.
     *
     * @throws IOException
     * @throws ClassFormatException
     */
    private void readAttributes() throws IOException, ClassFormatException {
        int attributes_count = dataInputStream.readUnsignedShort();
        attributes = new Attribute[attributes_count];
        for (int i = 0; i < attributes_count; i++) {
            attributes[i] = Attribute.readAttribute(dataInputStream, constantPool);
        }
    }

    /**
     * Reads information about the class and its super class.
     *
     * @throws IOException
     * @throws ClassFormatException
     */
    private void readClassInfo() throws IOException, ClassFormatException {
        accessFlags = dataInputStream.readUnsignedShort();
        /*
         * Interfaces are implicitely abstract, the flag should be set according to the
         * JVM specification.
         */
        if ((accessFlags & Const.ACC_INTERFACE) != 0) {
            accessFlags |= Const.ACC_ABSTRACT;
        }
        if (((accessFlags & Const.ACC_ABSTRACT) != 0) && ((accessFlags & Const.ACC_FINAL) != 0)) {
            throw new ClassFormatException("Class " + fileName + " can't be both  and abstract");
        }
        classNameIndex = dataInputStream.readUnsignedShort();
        superclassNameIndex = dataInputStream.readUnsignedShort();
    }

    /**
     * Reads constant pool entries.
     *
     * @throws IOException
     * @throws ClassFormatException
     */
    private void readConstantPool() throws IOException, ClassFormatException {
        constantPool = new ConstantPool(dataInputStream);
    }

    /**
     * Reads information about the fields of the class, i.e., its variables.
     *
     * @throws IOException
     * @throws ClassFormatException
     */
    private void readFields() throws IOException, ClassFormatException {
        int fields_count = dataInputStream.readUnsignedShort();
        fields = new Field[fields_count];
        for (int i = 0; i < fields_count; i++) {
            fields[i] = new Field(dataInputStream, constantPool);
        }
    }

    private void readID() throws IOException, ClassFormatException {
        if (dataInputStream.readInt() != Const.JVM_CLASSFILE_MAGIC) {
            throw new ClassFormatException(fileName + " is not a Java .class file");
        }
    }

    private void readInterfaces() throws IOException, ClassFormatException {
        int interfaces_count = dataInputStream.readUnsignedShort();
        interfaces = new int[interfaces_count];
        for (int i = 0; i < interfaces_count; i++) {
            interfaces[i] = dataInputStream.readUnsignedShort();
        }
    }

    private void readMethods() throws IOException, ClassFormatException {
        int methods_count = dataInputStream.readUnsignedShort();
        methods = new Method[methods_count];
        for (int i = 0; i < methods_count; i++) {
            methods[i] = new Method(dataInputStream, constantPool);
        }
    }

    private void readVersion() throws IOException, ClassFormatException {
        version = Version.read(dataInputStream);
    }
}
