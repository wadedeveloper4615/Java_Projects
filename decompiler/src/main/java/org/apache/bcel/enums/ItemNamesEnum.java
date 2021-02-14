package org.apache.bcel.enums;

import org.apache.bcel.Const;

public enum ItemNamesEnum {
    //@formatter:off
    ITEM_Bogus(0),
    ITEM_Integer(1),
    ITEM_Float(2),
    ITEM_Double(3),
    ITEM_Long(4),
    ITEM_Null(5),
    ITEM_InitObject(6),
    ITEM_Object(7),
    ITEM_NewObject(8);
    //@formatter:on
    private int tag;
    private String name;

    ItemNamesEnum(int tag) {
        this.tag = tag;
        this.name = Const.ITEM_NAMES[tag];
    }

    public String getName() {
        return name;
    }

    public int getTag() {
        return tag;
    }

    public static ItemNamesEnum read(byte type) {
        for (ItemNamesEnum flag : ItemNamesEnum.values()) {
            if (type == flag.getTag()) {
                return flag;
            }
        }
        return null;
    }
}
