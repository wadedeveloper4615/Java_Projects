package com.wade.decompiler.test;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Test1 implements Serializable {
    private static final long serialVersionUID = -5103281989426867172L;
    private int var1;
    private int var2;

    public void switch1(int choice) {
        var1 = switch (choice) {
            case 0 -> 0;
            case 1 -> 5;
            default -> 10;
        };
    }

    public void switch2(int choice) {
        var1 = switch (choice) {
            case 5 -> 5;
            case 10 -> 10;
            default -> 15;
        };
    }

    public void switch3(int choice) {
        switch (choice) {
            case 0:
                var1 = 0;
                break;
            case 1:
                var2 = 5;
                break;
            default:
                var1 = 10;
                break;
        }
        ;
    }

    public void switch4(int choice) {
        switch (choice) {
            case 5:
                var1 = 0;
                break;
            case 10:
                var2 = 5;
                break;
            default:
                var1 = 10;
                break;
        }
        ;
    }
}
