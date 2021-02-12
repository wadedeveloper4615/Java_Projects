
package org.apache.bcel.classfile;

import org.apache.bcel.classfile.constant.ConstantPool;

public interface ConstantObject {

    Object getConstantValue(ConstantPool cp);
}
