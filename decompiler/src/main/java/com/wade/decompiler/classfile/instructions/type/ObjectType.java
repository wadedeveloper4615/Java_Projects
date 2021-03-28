package com.wade.decompiler.classfile.instructions.type;

import com.wade.decompiler.enums.TypeEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class ObjectType extends ReferenceType {
    private String className;

    public ObjectType(String className) {
        super(TypeEnum.T_REFERENCE, "L" + className.replace('.', '/') + ";");
        this.className = className.replace('/', '.');
    }

    public static ObjectType getInstance(final String className) {
        return new ObjectType(className);
    }

    public String getClassName() {
        return className;
    }
}
