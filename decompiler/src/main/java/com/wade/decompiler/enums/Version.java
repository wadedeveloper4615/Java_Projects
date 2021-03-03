package com.wade.decompiler.enums;

public enum Version {
    //@formatter:off
    Version_1_1(45, 3, "1.1"),
    Version_1_2(46, 0, "1.2"),
    Version_1_3(47, 0, "1.3"),
    Version_1_4(48, 0, "1.4"),
    Version_1_5(49, 0, "1.5"),
    Version_1_6(50, 0, "1.6"),
    Version_1_7(51, 0, "1.7"),
    Version_1_8(52, 0, "1.8"),
    Version_9(53, 0, "9"),
    Version_10(54, 0, "10"),
    Version_11(55, 0, "11"),
    Version_12(56, 0, "12"),
    Version_13(57, 0, "13"),
    Version_14(58, 0, "14"),
    Version_15(59, 0, "15"),
    Version_16(60, 0, "15+"),
    Unknown(-1, -1, "Unknown");
    //@formatter:on
    private int major;
    private int minor;
    private String versionString;

    private Version(int major, int minor, String versionString) {
        this.major = major;
        this.minor = minor;
        this.versionString = versionString;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public String getVersionString() {
        return versionString;
    }

    public static Version read(int minor, int major) {
        for (Version v : Version.values()) {
            if (v.getMajor() == major && v.getMinor() == minor) {
                return v;
            }
        }
        System.out.println("major=" + major + " minor=" + minor);
        return Unknown;
    }
}