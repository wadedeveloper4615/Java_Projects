package com.wade.app.util;

import java.util.List;

import com.wade.app.enums.ClassAccessFlags;

public class ClassAccessFlagsList {
    private List<ClassAccessFlags> list;

    public ClassAccessFlagsList(List<ClassAccessFlags> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
