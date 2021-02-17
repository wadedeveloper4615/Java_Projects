package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.wade.decompiler.enums.ClassFileConstants;

public class ConstantUtf8 extends Constant {
    // TODO these should perhaps be AtomicInt?
    private static volatile int considered = 0;

    private static volatile int created = 0;
    private static volatile int hits = 0;
    private static volatile int skipped = 0;
    private static String SYS_PROP_CACHE_MAX_ENTRIES = "bcel.maxcached";
    private static String SYS_PROP_CACHE_MAX_ENTRY_SIZE = "bcel.maxcached.size";
    private static String SYS_PROP_STATISTICS = "bcel.statistics";
    static {
        if (Cache.BCEL_STATISTICS) {
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    printStats();
                }
            });
        }
    }

    private static class Cache {
        private static boolean BCEL_STATISTICS = Boolean.getBoolean(SYS_PROP_STATISTICS);
        private static int MAX_ENTRIES = Integer.getInteger(SYS_PROP_CACHE_MAX_ENTRIES, 0).intValue();
        private static int INITIAL_CAPACITY = (int) (MAX_ENTRIES / 0.75);
        private static HashMap<String, ConstantUtf8> CACHE = new LinkedHashMap<String, ConstantUtf8>(INITIAL_CAPACITY, 0.75f, true) {
            private static final long serialVersionUID = -8506975356158971766L;

            @Override
            protected boolean removeEldestEntry(Map.Entry<String, ConstantUtf8> eldest) {
                return size() > MAX_ENTRIES;
            }
        };
        // Set the size to 0 or below to skip caching entirely
        private static int MAX_ENTRY_SIZE = Integer.getInteger(SYS_PROP_CACHE_MAX_ENTRY_SIZE, 200).intValue();

        static boolean isEnabled() {
            return Cache.MAX_ENTRIES > 0 && MAX_ENTRY_SIZE > 0;
        }
    }

    private String value;

    public ConstantUtf8(ConstantUtf8 constantUtf8) {
        this(constantUtf8.getBytes());
    }

    ConstantUtf8(DataInput dataInput) throws IOException {
        super(ClassFileConstants.CONSTANT_Utf8);
        value = dataInput.readUTF();
        created++;
    }

    public ConstantUtf8(String value) {
        super(ClassFileConstants.CONSTANT_Utf8);
        if (value == null) {
            throw new IllegalArgumentException("Value must not be null.");
        }
        this.value = value;
        created++;
    }

    @Override
    public void accept(Visitor v) {
        v.visitConstantUtf8(this);
    }

    @Override
    public void dump(DataOutputStream file) throws IOException {
        file.writeByte(super.getTag().getTag());
        file.writeUTF(value);
    }

    public String getBytes() {
        return value;
    }

    @java.lang.Deprecated
    public void setBytes(String bytes) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return super.toString() + "(\"" + Utility.replace(value, "\n", "\\n") + "\")";
    }

    public static synchronized void clearCache() {
        Cache.CACHE.clear();
    }

    // for accesss by test code
    static synchronized void clearStats() {
        hits = considered = skipped = created = 0;
    }

    public static ConstantUtf8 getCachedInstance(String value) {
        if (value.length() > Cache.MAX_ENTRY_SIZE) {
            skipped++;
            return new ConstantUtf8(value);
        }
        considered++;
        synchronized (ConstantUtf8.class) { // might be better with a specific lock object
            ConstantUtf8 result = Cache.CACHE.get(value);
            if (result != null) {
                hits++;
                return result;
            }
            result = new ConstantUtf8(value);
            Cache.CACHE.put(value, result);
            return result;
        }
    }

    public static ConstantUtf8 getInstance(DataInput dataInput) throws IOException {
        return getInstance(dataInput.readUTF());
    }

    public static ConstantUtf8 getInstance(String value) {
        return Cache.isEnabled() ? getCachedInstance(value) : new ConstantUtf8(value);
    }

    // for accesss by test code
    static void printStats() {
        String prefix = "[Apache Commons BCEL]";
        System.err.printf("%s Cache hit %,d/%,d, %d skipped.%n", prefix, hits, considered, skipped);
        System.err.printf("%s Total of %,d ConstantUtf8 objects created.%n", prefix, created);
        System.err.printf("%s Configuration: %s=%,d, %s=%,d.%n", prefix, SYS_PROP_CACHE_MAX_ENTRIES, Cache.MAX_ENTRIES, SYS_PROP_CACHE_MAX_ENTRY_SIZE, Cache.MAX_ENTRY_SIZE);
    }
}
