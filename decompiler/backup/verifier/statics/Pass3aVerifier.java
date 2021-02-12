package org.apache.bcel.verifier.statics;

import org.apache.bcel.Const;
import org.apache.bcel.Repository;
import org.apache.bcel.classfile.Attribute;
import org.apache.bcel.classfile.Code;
import org.apache.bcel.classfile.CodeException;
import org.apache.bcel.classfile.Constant;
import org.apache.bcel.classfile.ConstantClass;
import org.apache.bcel.classfile.ConstantDouble;
import org.apache.bcel.classfile.ConstantFieldref;
import org.apache.bcel.classfile.ConstantFloat;
import org.apache.bcel.classfile.ConstantInteger;
import org.apache.bcel.classfile.ConstantInterfaceMethodref;
import org.apache.bcel.classfile.ConstantLong;
import org.apache.bcel.classfile.ConstantMethodref;
import org.apache.bcel.classfile.ConstantNameAndType;
import org.apache.bcel.classfile.ConstantString;
import org.apache.bcel.classfile.ConstantUtf8;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.LineNumber;
import org.apache.bcel.classfile.LineNumberTable;
import org.apache.bcel.classfile.LocalVariable;
import org.apache.bcel.classfile.LocalVariableTable;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.enums.ClassAccessFlags;
import org.apache.bcel.generic.ALOAD;
import org.apache.bcel.generic.ANEWARRAY;
import org.apache.bcel.generic.ASTORE;
import org.apache.bcel.generic.ATHROW;
import org.apache.bcel.generic.BREAKPOINT;
import org.apache.bcel.generic.CHECKCAST;
import org.apache.bcel.generic.DLOAD;
import org.apache.bcel.generic.DSTORE;
import org.apache.bcel.generic.FLOAD;
import org.apache.bcel.generic.FSTORE;
import org.apache.bcel.generic.GETSTATIC;
import org.apache.bcel.generic.IINC;
import org.apache.bcel.generic.ILOAD;
import org.apache.bcel.generic.IMPDEP1;
import org.apache.bcel.generic.IMPDEP2;
import org.apache.bcel.generic.INSTANCEOF;
import org.apache.bcel.generic.INVOKEDYNAMIC;
import org.apache.bcel.generic.INVOKEINTERFACE;
import org.apache.bcel.generic.INVOKESPECIAL;
import org.apache.bcel.generic.INVOKESTATIC;
import org.apache.bcel.generic.INVOKEVIRTUAL;
import org.apache.bcel.generic.ISTORE;
import org.apache.bcel.generic.LDC;
import org.apache.bcel.generic.LDC2_W;
import org.apache.bcel.generic.LLOAD;
import org.apache.bcel.generic.LOOKUPSWITCH;
import org.apache.bcel.generic.LSTORE;
import org.apache.bcel.generic.MULTIANEWARRAY;
import org.apache.bcel.generic.NEW;
import org.apache.bcel.generic.NEWARRAY;
import org.apache.bcel.generic.ObjectType;
import org.apache.bcel.generic.PUTSTATIC;
import org.apache.bcel.generic.RET;
import org.apache.bcel.generic.TABLESWITCH;
import org.apache.bcel.generic.Type;
import org.apache.bcel.generic.base.ArrayType;
import org.apache.bcel.generic.base.FieldInstruction;
import org.apache.bcel.generic.base.GotoInstruction;
import org.apache.bcel.generic.base.Instruction;
import org.apache.bcel.generic.base.InvokeInstruction;
import org.apache.bcel.generic.base.JsrInstruction;
import org.apache.bcel.generic.base.LoadClass;
import org.apache.bcel.generic.base.ReferenceType;
import org.apache.bcel.generic.base.ReturnInstruction;
import org.apache.bcel.generic.control.InstructionHandle;
import org.apache.bcel.generic.control.InstructionList;
import org.apache.bcel.generic.gen.ConstantPoolGen;
import org.apache.bcel.verifier.PassVerifier;
import org.apache.bcel.verifier.VerificationResult;
import org.apache.bcel.verifier.Verifier;
import org.apache.bcel.verifier.VerifierFactory;
import org.apache.bcel.verifier.exc.AssertionViolatedException;
import org.apache.bcel.verifier.exc.ClassConstraintException;
import org.apache.bcel.verifier.exc.InvalidMethodException;
import org.apache.bcel.verifier.exc.StaticCodeConstraintException;
import org.apache.bcel.verifier.exc.StaticCodeInstructionConstraintException;
import org.apache.bcel.verifier.exc.StaticCodeInstructionOperandConstraintException;

public final class Pass3aVerifier extends PassVerifier {
    private class InstOperandConstraintVisitor extends org.apache.bcel.generic.base.EmptyVisitor {
        private final ConstantPoolGen constantPoolGen;

        InstOperandConstraintVisitor(final ConstantPoolGen constantPoolGen) {
            this.constantPoolGen = constantPoolGen;
        }

        private void constraintViolated(final Instruction i, final String message) {
            throw new StaticCodeInstructionOperandConstraintException("Instruction " + i + " constraint violated: " + message);
        }

        private Method getMethod(final JavaClass jc, final InvokeInstruction invoke) {
            final Method[] ms = jc.getMethods();
            for (final Method element : ms) {
                if ((element.getName().equals(invoke.getMethodName(constantPoolGen))) && (Type.getReturnType(element.getSignature()).equals(invoke.getReturnType(constantPoolGen))) && (objarrayequals(Type.getArgumentTypes(element.getSignature()), invoke.getArgumentTypes(constantPoolGen)))) {
                    return element;
                }
            }

            return null;
        }

        private Method getMethodRecursive(final JavaClass jc, final InvokeInstruction invoke) throws ClassNotFoundException {
            Method m;
            // look in the given class
            m = getMethod(jc, invoke);
            if (m != null) {
                // method found in given class
                return m;
            }
            // method not found, look in super classes
            for (final JavaClass superclass : jc.getSuperClasses()) {
                m = getMethod(superclass, invoke);
                if (m != null) {
                    // method found in super class
                    return m;
                }
            }
            // method not found, look in super interfaces
            for (final JavaClass superclass : jc.getInterfaceJavaClass()) {
                m = getMethod(superclass, invoke);
                if (m != null) {
                    // method found in super interface
                    return m;
                }
            }
            // method not found in the hierarchy
            return null;
        }

        private ObjectType getObjectType(final FieldInstruction o) {
            final ReferenceType rt = o.getReferenceType(constantPoolGen);
            if (rt instanceof ObjectType) {
                return (ObjectType) rt;
            }
            constraintViolated(o, "expecting ObjectType but got " + rt);
            return null;
        }

        // The target of each jump and branch instruction [...] must be the opcode [...]
        // BCEL _DOES_ handle this.

        // tableswitch: BCEL will do it, supposedly.

        // lookupswitch: BCEL will do it, supposedly.

        private void indexValid(final Instruction i, final int idx) {
            if (idx < 0 || idx >= constantPoolGen.getSize()) {
                constraintViolated(i, "Illegal constant pool index '" + idx + "'.");
            }
        }

        private int max_locals() {
            try {
                return Repository.lookupClass(myOwner.getClassName()).getMethods()[methodNo].getCode().getMaxLocals();
            } catch (final ClassNotFoundException e) {
                // FIXME: maybe not the best way to handle this
                throw new AssertionViolatedException("Missing class: " + e, e);
            }
        }

        private boolean objarrayequals(final Object[] o, final Object[] p) {
            if (o.length != p.length) {
                return false;
            }

            for (int i = 0; i < o.length; i++) {
                if (!(o[i].equals(p[i]))) {
                    return false;
                }
            }

            return true;
        }

        @Override
        public void visitALOAD(final ALOAD o) {
            final int idx = o.getIndex();
            if (idx < 0) {
                constraintViolated(o, "Index '" + idx + "' must be non-negative.");
            } else {
                final int maxminus1 = max_locals() - 1;
                if (idx > maxminus1) {
                    constraintViolated(o, "Index '" + idx + "' must not be greater than max_locals-1 '" + maxminus1 + "'.");
                }
            }
        }

        @Override
        public void visitANEWARRAY(final ANEWARRAY o) {
            indexValid(o, o.getIndex());
            final Constant c = constantPoolGen.getConstant(o.getIndex());
            if (!(c instanceof ConstantClass)) {
                constraintViolated(o, "Expecting a CONSTANT_Class operand, but found a '" + c + "'.");
            }
            final Type t = o.getType(constantPoolGen);
            if (t instanceof ArrayType) {
                final int dimensions = ((ArrayType) t).getDimensions();
                if (dimensions > Const.MAX_ARRAY_DIMENSIONS) {
                    constraintViolated(o, "Not allowed to create an array with more than " + Const.MAX_ARRAY_DIMENSIONS + " dimensions;" + " actual: " + dimensions);
                }
            }
        }

        @Override
        public void visitASTORE(final ASTORE o) {
            final int idx = o.getIndex();
            if (idx < 0) {
                constraintViolated(o, "Index '" + idx + "' must be non-negative.");
            } else {
                final int maxminus1 = max_locals() - 1;
                if (idx > maxminus1) {
                    constraintViolated(o, "Index '" + idx + "' must not be greater than max_locals-1 '" + maxminus1 + "'.");
                }
            }
        }

        @Override
        public void visitCHECKCAST(final CHECKCAST o) {
            indexValid(o, o.getIndex());
            final Constant c = constantPoolGen.getConstant(o.getIndex());
            if (!(c instanceof ConstantClass)) {
                constraintViolated(o, "Expecting a CONSTANT_Class operand, but found a '" + c + "'.");
            }
        }

        @Override
        public void visitDLOAD(final DLOAD o) {
            final int idx = o.getIndex();
            if (idx < 0) {
                constraintViolated(o, "Index '" + idx + "' must be non-negative." + " [Constraint by JustIce as an analogon to the single-slot xLOAD/xSTORE instructions; may not happen anyway.]");
            } else {
                final int maxminus2 = max_locals() - 2;
                if (idx > maxminus2) {
                    constraintViolated(o, "Index '" + idx + "' must not be greater than max_locals-2 '" + maxminus2 + "'.");
                }
            }
        }

        @Override
        public void visitDSTORE(final DSTORE o) {
            final int idx = o.getIndex();
            if (idx < 0) {
                constraintViolated(o, "Index '" + idx + "' must be non-negative." + " [Constraint by JustIce as an analogon to the single-slot xLOAD/xSTORE instructions; may not happen anyway.]");
            } else {
                final int maxminus2 = max_locals() - 2;
                if (idx > maxminus2) {
                    constraintViolated(o, "Index '" + idx + "' must not be greater than max_locals-2 '" + maxminus2 + "'.");
                }
            }
        }

        // getfield, putfield, getstatic, putstatic
        @Override
        public void visitFieldInstruction(final FieldInstruction o) {
            try {
                indexValid(o, o.getIndex());
                final Constant c = constantPoolGen.getConstant(o.getIndex());
                if (!(c instanceof ConstantFieldref)) {
                    constraintViolated(o, "Indexing a constant that's not a CONSTANT_Fieldref but a '" + c + "'.");
                }

                final String field_name = o.getFieldName(constantPoolGen);

                final JavaClass jc = Repository.lookupClass(getObjectType(o).getClassName());
                Field[] fields = jc.getFields();
                Field f = null;
                for (final Field field : fields) {
                    if (field.getName().equals(field_name)) {
                        final Type f_type = Type.getType(field.getSignature());
                        final Type o_type = o.getType(constantPoolGen);

                        if (f_type.equals(o_type)) {
                            f = field;
                            break;
                        }
                    }
                }
                if (f == null) {
                    final JavaClass[] superclasses = jc.getSuperClasses();
                    outer: for (final JavaClass superclass : superclasses) {
                        fields = superclass.getFields();
                        for (final Field field : fields) {
                            if (field.getName().equals(field_name)) {
                                final Type f_type = Type.getType(field.getSignature());
                                final Type o_type = o.getType(constantPoolGen);
                                if (f_type.equals(o_type)) {
                                    f = field;
                                    if ((f.getAccessFlags() & (ClassAccessFlags.ACC_PUBLIC.getFlag() | ClassAccessFlags.ACC_PROTECTED.getFlag())) == 0) {
                                        f = null;
                                    }
                                    break outer;
                                }
                            }
                        }
                    }
                    if (f == null) {
                        constraintViolated(o, "Referenced field '" + field_name + "' does not exist in class '" + jc.getClassName() + "'.");
                    }
                } else {

                    Type.getType(f.getSignature());
                    o.getType(constantPoolGen);
//                Type f_type = Type.getType(f.getSignature());
//                Type o_type = o.getType(cpg);

                    // Argh. Sun's implementation allows us to have multiple fields of
                    // the same name but with a different signature.
                    // if (! f_type.equals(o_type)) {
                    // constraintViolated(o,
                    // "Referenced field '"+field_name+"' has type '"+f_type+"' instead of
                    // '"+o_type+"' as expected.");
                    // }

                }
            } catch (final ClassNotFoundException e) {
                // FIXME: maybe not the best way to handle this
                throw new AssertionViolatedException("Missing class: " + e, e);
            }
        }

        @Override
        public void visitFLOAD(final FLOAD o) {
            final int idx = o.getIndex();
            if (idx < 0) {
                constraintViolated(o, "Index '" + idx + "' must be non-negative.");
            } else {
                final int maxminus1 = max_locals() - 1;
                if (idx > maxminus1) {
                    constraintViolated(o, "Index '" + idx + "' must not be greater than max_locals-1 '" + maxminus1 + "'.");
                }
            }
        }

        @Override
        public void visitFSTORE(final FSTORE o) {
            final int idx = o.getIndex();
            if (idx < 0) {
                constraintViolated(o, "Index '" + idx + "' must be non-negative.");
            } else {
                final int maxminus1 = max_locals() - 1;
                if (idx > maxminus1) {
                    constraintViolated(o, "Index '" + idx + "' must not be greater than max_locals-1 '" + maxminus1 + "'.");
                }
            }
        }

        @Override
        public void visitGETSTATIC(final GETSTATIC o) {
            try {
                final String field_name = o.getFieldName(constantPoolGen);
                final JavaClass jc = Repository.lookupClass(getObjectType(o).getClassName());
                final Field[] fields = jc.getFields();
                Field f = null;
                for (final Field field : fields) {
                    if (field.getName().equals(field_name)) {
                        f = field;
                        break;
                    }
                }
                if (f == null) {
                    throw new AssertionViolatedException("Field '" + field_name + "' not found in " + jc.getClassName());
                }

                if (!(f.isStatic())) {
                    constraintViolated(o, "Referenced field '" + f + "' is not static which it should be.");
                }
            } catch (final ClassNotFoundException e) {
                // FIXME: maybe not the best way to handle this
                throw new AssertionViolatedException("Missing class: " + e, e);
            }
        }

        @Override
        public void visitIINC(final IINC o) {
            final int idx = o.getIndex();
            if (idx < 0) {
                constraintViolated(o, "Index '" + idx + "' must be non-negative.");
            } else {
                final int maxminus1 = max_locals() - 1;
                if (idx > maxminus1) {
                    constraintViolated(o, "Index '" + idx + "' must not be greater than max_locals-1 '" + maxminus1 + "'.");
                }
            }
        }

        @Override
        public void visitILOAD(final ILOAD o) {
            final int idx = o.getIndex();
            if (idx < 0) {
                constraintViolated(o, "Index '" + idx + "' must be non-negative.");
            } else {
                final int maxminus1 = max_locals() - 1;
                if (idx > maxminus1) {
                    constraintViolated(o, "Index '" + idx + "' must not be greater than max_locals-1 '" + maxminus1 + "'.");
                }
            }
        }

        @Override
        public void visitINSTANCEOF(final INSTANCEOF o) {
            indexValid(o, o.getIndex());
            final Constant c = constantPoolGen.getConstant(o.getIndex());
            if (!(c instanceof ConstantClass)) {
                constraintViolated(o, "Expecting a CONSTANT_Class operand, but found a '" + c + "'.");
            }
        }

        @Override
        public void visitINVOKEDYNAMIC(final INVOKEDYNAMIC o) {
            throw new UnsupportedOperationException("INVOKEDYNAMIC instruction is not supported at this time");
        }

        @Override
        public void visitInvokeInstruction(final InvokeInstruction o) {
            indexValid(o, o.getIndex());
            if ((o instanceof INVOKEVIRTUAL) || (o instanceof INVOKESPECIAL) || (o instanceof INVOKESTATIC)) {
                final Constant c = constantPoolGen.getConstant(o.getIndex());
                if (!(c instanceof ConstantMethodref)) {
                    constraintViolated(o, "Indexing a constant that's not a CONSTANT_Methodref but a '" + c + "'.");
                } else {
                    // Constants are okay due to pass2.
                    final ConstantNameAndType cnat = (ConstantNameAndType) (constantPoolGen.getConstant(((ConstantMethodref) c).getNameAndTypeIndex()));
                    final ConstantUtf8 cutf8 = (ConstantUtf8) (constantPoolGen.getConstant(cnat.getNameIndex()));
                    if (cutf8.getBytes().equals(Const.CONSTRUCTOR_NAME) && (!(o instanceof INVOKESPECIAL))) {
                        constraintViolated(o, "Only INVOKESPECIAL is allowed to invoke instance initialization methods.");
                    }
                    if ((!(cutf8.getBytes().equals(Const.CONSTRUCTOR_NAME))) && (cutf8.getBytes().startsWith("<"))) {
                        constraintViolated(o, "No method with a name beginning with '<' other than the instance initialization methods" + " may be called by the method invocation instructions.");
                    }
                }
            } else { // if (o instanceof INVOKEINTERFACE) {
                final Constant c = constantPoolGen.getConstant(o.getIndex());
                if (!(c instanceof ConstantInterfaceMethodref)) {
                    constraintViolated(o, "Indexing a constant that's not a CONSTANT_InterfaceMethodref but a '" + c + "'.");
                }
                // TODO: From time to time check if BCEL allows to detect if the
                // 'count' operand is consistent with the information in the
                // CONSTANT_InterfaceMethodref and if the last operand is zero.
                // By now, BCEL hides those two operands because they're superfluous.

                // Invoked method must not be <init> or <clinit>
                final ConstantNameAndType cnat = (ConstantNameAndType) (constantPoolGen.getConstant(((ConstantInterfaceMethodref) c).getNameAndTypeIndex()));
                final String name = ((ConstantUtf8) (constantPoolGen.getConstant(cnat.getNameIndex()))).getBytes();
                if (name.equals(Const.CONSTRUCTOR_NAME)) {
                    constraintViolated(o, "Method to invoke must not be '" + Const.CONSTRUCTOR_NAME + "'.");
                }
                if (name.equals(Const.STATIC_INITIALIZER_NAME)) {
                    constraintViolated(o, "Method to invoke must not be '" + Const.STATIC_INITIALIZER_NAME + "'.");
                }
            }

            // The LoadClassType is the method-declaring class, so we have to check the
            // other types.

            Type t = o.getReturnType(constantPoolGen);
            if (t instanceof ArrayType) {
                t = ((ArrayType) t).getBasicType();
            }
            if (t instanceof ObjectType) {
                final Verifier v = VerifierFactory.getVerifier(((ObjectType) t).getClassName());
                final VerificationResult vr = v.doPass2();
                if (vr.getStatus() != VerificationResult.VERIFIED_OK) {
                    constraintViolated(o, "Return type class/interface could not be verified successfully: '" + vr.getMessage() + "'.");
                }
            }

            final Type[] ts = o.getArgumentTypes(constantPoolGen);
            for (final Type element : ts) {
                t = element;
                if (t instanceof ArrayType) {
                    t = ((ArrayType) t).getBasicType();
                }
                if (t instanceof ObjectType) {
                    final Verifier v = VerifierFactory.getVerifier(((ObjectType) t).getClassName());
                    final VerificationResult vr = v.doPass2();
                    if (vr.getStatus() != VerificationResult.VERIFIED_OK) {
                        constraintViolated(o, "Argument type class/interface could not be verified successfully: '" + vr.getMessage() + "'.");
                    }
                }
            }

        }

        @Override
        public void visitINVOKEINTERFACE(final INVOKEINTERFACE o) {
            try {
                // INVOKEINTERFACE is a LoadClass; the Class where the referenced method is
                // declared in,
                // is therefore resolved/verified.
                // INVOKEINTERFACE is an InvokeInstruction, the argument and return types are
                // resolved/verified,
                // too. So are the allowed method names.
                final String classname = o.getClassName(constantPoolGen);
                final JavaClass jc = Repository.lookupClass(classname);
                final Method m = getMethodRecursive(jc, o);
                if (m == null) {
                    constraintViolated(o, "Referenced method '" + o.getMethodName(constantPoolGen) + "' with expected signature '" + o.getSignature(constantPoolGen) + "' not found in class '" + jc.getClassName() + "'.");
                }
                if (jc.isClass()) {
                    constraintViolated(o, "Referenced class '" + jc.getClassName() + "' is a class, but not an interface as expected.");
                }
            } catch (final ClassNotFoundException e) {
                // FIXME: maybe not the best way to handle this
                throw new AssertionViolatedException("Missing class: " + e, e);
            }
        }

        @Override
        public void visitINVOKESPECIAL(final INVOKESPECIAL o) {
            try {
                // INVOKESPECIAL is a LoadClass; the Class where the referenced method is
                // declared in,
                // is therefore resolved/verified.
                // INVOKESPECIAL is an InvokeInstruction, the argument and return types are
                // resolved/verified,
                // too. So are the allowed method names.
                final String classname = o.getClassName(constantPoolGen);
                final JavaClass jc = Repository.lookupClass(classname);
                final Method m = getMethodRecursive(jc, o);
                if (m == null) {
                    constraintViolated(o, "Referenced method '" + o.getMethodName(constantPoolGen) + "' with expected signature '" + o.getSignature(constantPoolGen) + "' not found in class '" + jc.getClassName() + "'.");
                }

                JavaClass current = Repository.lookupClass(myOwner.getClassName());
                if (current.isSuper()) {

                    if ((Repository.instanceOf(current, jc)) && (!current.equals(jc))) {

                        if (!(o.getMethodName(constantPoolGen).equals(Const.CONSTRUCTOR_NAME))) {
                            // Special lookup procedure for ACC_SUPER classes.

                            int supidx = -1;

                            Method meth = null;
                            while (supidx != 0) {
                                supidx = current.getSuperClassName().getNameIndex();
                                current = Repository.lookupClass(current.getSuperClassName().getName());

                                final Method[] meths = current.getMethods();
                                for (final Method meth2 : meths) {
                                    if ((meth2.getName().equals(o.getMethodName(constantPoolGen))) && (Type.getReturnType(meth2.getSignature()).equals(o.getReturnType(constantPoolGen))) && (objarrayequals(Type.getArgumentTypes(meth2.getSignature()), o.getArgumentTypes(constantPoolGen)))) {
                                        meth = meth2;
                                        break;
                                    }
                                }
                                if (meth != null) {
                                    break;
                                }
                            }
                            if (meth == null) {
                                constraintViolated(o, "ACC_SUPER special lookup procedure not successful: method '" + o.getMethodName(constantPoolGen) + "' with proper signature not declared in superclass hierarchy.");
                            }
                        }
                    }
                }

            } catch (final ClassNotFoundException e) {
                // FIXME: maybe not the best way to handle this
                throw new AssertionViolatedException("Missing class: " + e, e);
            }

        }

        @Override
        public void visitINVOKESTATIC(final INVOKESTATIC o) {
            try {
                // INVOKESTATIC is a LoadClass; the Class where the referenced method is
                // declared in,
                // is therefore resolved/verified.
                // INVOKESTATIC is an InvokeInstruction, the argument and return types are
                // resolved/verified,
                // too. So are the allowed method names.
                final String classname = o.getClassName(constantPoolGen);
                final JavaClass jc = Repository.lookupClass(classname);
                final Method m = getMethodRecursive(jc, o);
                if (m == null) {
                    constraintViolated(o, "Referenced method '" + o.getMethodName(constantPoolGen) + "' with expected signature '" + o.getSignature(constantPoolGen) + "' not found in class '" + jc.getClassName() + "'.");
                } else if (!(m.isStatic())) { // implies it's not abstract, verified in pass 2.
                    constraintViolated(o, "Referenced method '" + o.getMethodName(constantPoolGen) + "' has ACC_STATIC unset.");
                }

            } catch (final ClassNotFoundException e) {
                // FIXME: maybe not the best way to handle this
                throw new AssertionViolatedException("Missing class: " + e, e);
            }
        }

        @Override
        public void visitINVOKEVIRTUAL(final INVOKEVIRTUAL o) {
            try {
                // INVOKEVIRTUAL is a LoadClass; the Class where the referenced method is
                // declared in,
                // is therefore resolved/verified.
                // INVOKEVIRTUAL is an InvokeInstruction, the argument and return types are
                // resolved/verified,
                // too. So are the allowed method names.
                final String classname = o.getClassName(constantPoolGen);
                final JavaClass jc = Repository.lookupClass(classname);
                final Method m = getMethodRecursive(jc, o);
                if (m == null) {
                    constraintViolated(o, "Referenced method '" + o.getMethodName(constantPoolGen) + "' with expected signature '" + o.getSignature(constantPoolGen) + "' not found in class '" + jc.getClassName() + "'.");
                }
                if (!(jc.isClass())) {
                    constraintViolated(o, "Referenced class '" + jc.getClassName() + "' is an interface, but not a class as expected.");
                }

            } catch (final ClassNotFoundException e) {
                // FIXME: maybe not the best way to handle this
                throw new AssertionViolatedException("Missing class: " + e, e);
            }
        }

        @Override
        public void visitISTORE(final ISTORE o) {
            final int idx = o.getIndex();
            if (idx < 0) {
                constraintViolated(o, "Index '" + idx + "' must be non-negative.");
            } else {
                final int maxminus1 = max_locals() - 1;
                if (idx > maxminus1) {
                    constraintViolated(o, "Index '" + idx + "' must not be greater than max_locals-1 '" + maxminus1 + "'.");
                }
            }
        }

        // LDC and LDC_W (LDC_W is a subclass of LDC in BCEL's model)
        @Override
        public void visitLDC(final LDC ldc) {
            indexValid(ldc, ldc.getIndex());
            final Constant c = constantPoolGen.getConstant(ldc.getIndex());
            if (c instanceof ConstantClass) {
                addMessage("Operand of LDC or LDC_W is CONSTANT_Class '" + c + "' - this is only supported in JDK 1.5 and higher.");
            } else {
                if (!((c instanceof ConstantInteger) || (c instanceof ConstantFloat) || (c instanceof ConstantString))) {
                    constraintViolated(ldc, "Operand of LDC or LDC_W must be one of CONSTANT_Integer, CONSTANT_Float or CONSTANT_String, but is '" + c + "'.");
                }
            }
        }

        // LDC2_W
        @Override
        public void visitLDC2_W(final LDC2_W o) {
            indexValid(o, o.getIndex());
            final Constant c = constantPoolGen.getConstant(o.getIndex());
            if (!((c instanceof ConstantLong) || (c instanceof ConstantDouble))) {
                constraintViolated(o, "Operand of LDC2_W must be CONSTANT_Long or CONSTANT_Double, but is '" + c + "'.");
            }
            try {
                indexValid(o, o.getIndex() + 1);
            } catch (final StaticCodeInstructionOperandConstraintException e) {
                throw new AssertionViolatedException("Does not BCEL handle that? LDC2_W operand has a problem.", e);
            }
        }

        @Override
        public void visitLLOAD(final LLOAD o) {
            final int idx = o.getIndex();
            if (idx < 0) {
                constraintViolated(o, "Index '" + idx + "' must be non-negative." + " [Constraint by JustIce as an analogon to the single-slot xLOAD/xSTORE instructions; may not happen anyway.]");
            } else {
                final int maxminus2 = max_locals() - 2;
                if (idx > maxminus2) {
                    constraintViolated(o, "Index '" + idx + "' must not be greater than max_locals-2 '" + maxminus2 + "'.");
                }
            }
        }

        ///////////////////////////////////////////////////////////
        // The Java Virtual Machine Specification, pages 134-137 //
        ///////////////////////////////////////////////////////////

        @Override
        public void visitLoadClass(final LoadClass loadClass) {
            final ObjectType t = loadClass.getLoadClassType(constantPoolGen);
            if (t != null) {// null means "no class is loaded"
                final Verifier v = VerifierFactory.getVerifier(t.getClassName());
                final VerificationResult vr = v.doPass1();
                if (vr.getStatus() != VerificationResult.VERIFIED_OK) {
                    constraintViolated((Instruction) loadClass, "Class '" + loadClass.getLoadClassType(constantPoolGen).getClassName() + "' is referenced, but cannot be loaded: '" + vr + "'.");
                }
            }
        }

        // public void visitPUTFIELD(PUTFIELD o) {
        // for performance reasons done in Pass 3b
        // }

        // public void visitGETFIELD(GETFIELD o) {
        // for performance reasons done in Pass 3b
        // }

        @Override
        public void visitLOOKUPSWITCH(final LOOKUPSWITCH o) {
            final int[] matchs = o.getMatchs();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < matchs.length; i++) {
                if (matchs[i] == max && i != 0) {
                    constraintViolated(o, "Match '" + matchs[i] + "' occurs more than once.");
                }
                if (matchs[i] < max) {
                    constraintViolated(o, "Lookup table must be sorted but isn't.");
                } else {
                    max = matchs[i];
                }
            }
        }

        @Override
        public void visitLSTORE(final LSTORE o) {
            final int idx = o.getIndex();
            if (idx < 0) {
                constraintViolated(o, "Index '" + idx + "' must be non-negative." + " [Constraint by JustIce as an analogon to the single-slot xLOAD/xSTORE instructions; may not happen anyway.]");
            } else {
                final int maxminus2 = max_locals() - 2;
                if (idx > maxminus2) {
                    constraintViolated(o, "Index '" + idx + "' must not be greater than max_locals-2 '" + maxminus2 + "'.");
                }
            }
        }

        @Override
        public void visitMULTIANEWARRAY(final MULTIANEWARRAY o) {
            indexValid(o, o.getIndex());
            final Constant c = constantPoolGen.getConstant(o.getIndex());
            if (!(c instanceof ConstantClass)) {
                constraintViolated(o, "Expecting a CONSTANT_Class operand, but found a '" + c + "'.");
            }
            final int dimensions2create = o.getDimensions();
            if (dimensions2create < 1) {
                constraintViolated(o, "Number of dimensions to create must be greater than zero.");
            }
            final Type t = o.getType(constantPoolGen);
            if (t instanceof ArrayType) {
                final int dimensions = ((ArrayType) t).getDimensions();
                if (dimensions < dimensions2create) {
                    constraintViolated(o, "Not allowed to create array with more dimensions ('" + dimensions2create + "') than the one referenced by the CONSTANT_Class '" + t + "'.");
                }
            } else {
                constraintViolated(o, "Expecting a CONSTANT_Class referencing an array type." + " [Constraint not found in The Java Virtual Machine Specification, Second Edition, 4.8.1]");
            }
        }

        @Override
        public void visitNEW(final NEW o) {
            indexValid(o, o.getIndex());
            final Constant c = constantPoolGen.getConstant(o.getIndex());
            if (!(c instanceof ConstantClass)) {
                constraintViolated(o, "Expecting a CONSTANT_Class operand, but found a '" + c + "'.");
            } else {
                final ConstantUtf8 cutf8 = (ConstantUtf8) (constantPoolGen.getConstant(((ConstantClass) c).getNameIndex()));
                final Type t = Type.getType("L" + cutf8.getBytes() + ";");
                if (t instanceof ArrayType) {
                    constraintViolated(o, "NEW must not be used to create an array.");
                }
            }

        }

        @Override
        public void visitNEWARRAY(final NEWARRAY o) {
            final byte t = o.getTypecode();
            if (!((t == Const.T_BOOLEAN) || (t == Const.T_CHAR) || (t == Const.T_FLOAT) || (t == Const.T_DOUBLE) || (t == Const.T_BYTE) || (t == Const.T_SHORT) || (t == Const.T_INT) || (t == Const.T_LONG))) {
                constraintViolated(o, "Illegal type code '+t+' for 'atype' operand.");
            }
        }

        @Override
        public void visitPUTSTATIC(final PUTSTATIC o) {
            try {
                final String field_name = o.getFieldName(constantPoolGen);
                final JavaClass jc = Repository.lookupClass(getObjectType(o).getClassName());
                final Field[] fields = jc.getFields();
                Field f = null;
                for (final Field field : fields) {
                    if (field.getName().equals(field_name)) {
                        f = field;
                        break;
                    }
                }
                if (f == null) {
                    throw new AssertionViolatedException("Field '" + field_name + "' not found in " + jc.getClassName());
                }

                if (f.isFinal()) {
                    if (!(myOwner.getClassName().equals(getObjectType(o).getClassName()))) {
                        constraintViolated(o, "Referenced field '" + f + "' is final and must therefore be declared in the current class '" + myOwner.getClassName() + "' which is not the case: it is declared in '" + o.getReferenceType(constantPoolGen) + "'.");
                    }
                }

                if (!(f.isStatic())) {
                    constraintViolated(o, "Referenced field '" + f + "' is not static which it should be.");
                }

                final String meth_name = Repository.lookupClass(myOwner.getClassName()).getMethods()[methodNo].getName();

                // If it's an interface, it can be set only in <clinit>.
                if ((!(jc.isClass())) && (!(meth_name.equals(Const.STATIC_INITIALIZER_NAME)))) {
                    constraintViolated(o, "Interface field '" + f + "' must be set in a '" + Const.STATIC_INITIALIZER_NAME + "' method.");
                }
            } catch (final ClassNotFoundException e) {
                // FIXME: maybe not the best way to handle this
                throw new AssertionViolatedException("Missing class: " + e, e);
            }
        }

        @Override
        public void visitRET(final RET o) {
            final int idx = o.getIndex();
            if (idx < 0) {
                constraintViolated(o, "Index '" + idx + "' must be non-negative.");
            } else {
                final int maxminus1 = max_locals() - 1;
                if (idx > maxminus1) {
                    constraintViolated(o, "Index '" + idx + "' must not be greater than max_locals-1 '" + maxminus1 + "'.");
                }
            }
        }

        // WIDE stuff is BCEL-internal and cannot be checked here.

        @Override
        public void visitTABLESWITCH(final TABLESWITCH o) {
            // "high" must be >= "low". We cannot check this, as BCEL hides
            // it from us.
        }

    }

    private final Verifier myOwner;

    private final int methodNo;

    private InstructionList instructionList;

    private Code code;

    public Pass3aVerifier(final Verifier owner, final int methodNo) {
        myOwner = owner;
        this.methodNo = methodNo;
    }

    private void delayedPass2Checks() {

        final int[] instructionPositions = instructionList.getInstructionPositions();
        final int codeLength = code.getCode().length;

        /////////////////////
        // LineNumberTable //
        /////////////////////
        final LineNumberTable lnt = code.getLineNumberTable();
        if (lnt != null) {
            final LineNumber[] lineNumbers = lnt.getLineNumberTable();
            final IntList offsets = new IntList();
            lineNumber_loop: for (final LineNumber lineNumber : lineNumbers) { // may appear in any order.
                for (final int instructionPosition : instructionPositions) {
                    // TODO: Make this a binary search! The instructionPositions array is naturally
                    // ordered!
                    final int offset = lineNumber.getStartPC();
                    if (instructionPosition == offset) {
                        if (offsets.contains(offset)) {
                            addMessage("LineNumberTable attribute '" + code.getLineNumberTable() + "' refers to the same code offset ('" + offset + "') more than once" + " which is violating the semantics [but is sometimes produced by IBM's 'jikes' compiler].");
                        } else {
                            offsets.add(offset);
                        }
                        continue lineNumber_loop;
                    }
                }
                throw new ClassConstraintException("Code attribute '" + code + "' has a LineNumberTable attribute '" + code.getLineNumberTable() + "' referring to a code offset ('" + lineNumber.getStartPC() + "') that does not exist.");
            }
        }

        ///////////////////////////
        // LocalVariableTable(s) //
        ///////////////////////////

        final Attribute[] atts = code.getAttributes();
        for (final Attribute att : atts) {
            if (att instanceof LocalVariableTable) {
                final LocalVariableTable lvt = (LocalVariableTable) att;
                final LocalVariable[] localVariables = lvt.getLocalVariableTable();
                for (final LocalVariable localVariable : localVariables) {
                    final int startpc = localVariable.getStartPC();
                    final int length = localVariable.getLength();

                    if (!contains(instructionPositions, startpc)) {
                        throw new ClassConstraintException("Code attribute '" + code + "' has a LocalVariableTable attribute '" + code.getLocalVariableTable() + "' referring to a code offset ('" + startpc + "') that does not exist.");
                    }
                    if ((!contains(instructionPositions, startpc + length)) && (startpc + length != codeLength)) {
                        throw new ClassConstraintException("Code attribute '" + code + "' has a LocalVariableTable attribute '" + code.getLocalVariableTable() + "' referring to a code offset start_pc+length ('" + (startpc + length) + "') that does not exist.");
                    }
                }
            }
        }

        ////////////////////
        // ExceptionTable //
        ////////////////////
        // In BCEL's "classfile" API, the startPC/endPC-notation is
        // inclusive/exclusive as in the Java Virtual Machine Specification.
        // WARNING: This is not true for BCEL's "generic" API.
        final CodeException[] exceptionTable = code.getExceptionTable();
        for (final CodeException element : exceptionTable) {
            final int startpc = element.getStartPC();
            final int endpc = element.getEndPC();
            final int handlerpc = element.getHandlerPC();
            if (startpc >= endpc) {
                throw new ClassConstraintException("Code attribute '" + code + "' has an exception_table entry '" + element + "' that has its start_pc ('" + startpc + "') not smaller than its end_pc ('" + endpc + "').");
            }
            if (!contains(instructionPositions, startpc)) {
                throw new ClassConstraintException("Code attribute '" + code + "' has an exception_table entry '" + element + "' that has a non-existant bytecode offset as its start_pc ('" + startpc + "').");
            }
            if ((!contains(instructionPositions, endpc)) && (endpc != codeLength)) {
                throw new ClassConstraintException("Code attribute '" + code + "' has an exception_table entry '" + element + "' that has a non-existant bytecode offset as its end_pc ('" + startpc + "') [that is also not equal to code_length ('" + codeLength + "')].");
            }
            if (!contains(instructionPositions, handlerpc)) {
                throw new ClassConstraintException("Code attribute '" + code + "' has an exception_table entry '" + element + "' that has a non-existant bytecode offset as its handler_pc ('" + handlerpc + "').");
            }
        }
    }

    @Override
    public VerificationResult do_verify() {
        try {
            if (myOwner.doPass2().equals(VerificationResult.VR_OK)) {
                // Okay, class file was loaded correctly by Pass 1
                // and satisfies static constraints of Pass 2.
                final JavaClass jc = Repository.lookupClass(myOwner.getClassName());
                final Method[] methods = jc.getMethods();
                if (methodNo >= methods.length) {
                    throw new InvalidMethodException("METHOD DOES NOT EXIST!");
                }
                final Method method = methods[methodNo];
                code = method.getCode();

                // No Code? Nothing to verify!
                if (method.isAbstract() || method.isNative()) { // IF mg HAS NO CODE (static constraint of Pass 2)
                    return VerificationResult.VR_OK;
                }

                // TODO:
                // We want a very sophisticated code examination here with good explanations
                // on where to look for an illegal instruction or such.
                // Only after that we should try to build an InstructionList and throw an
                // AssertionViolatedException if after our examination InstructionList building
                // still fails.
                // That examination should be implemented in a byte-oriented way, i.e. look for
                // an instruction, make sure its validity, count its length, find the next
                // instruction and so on.
                try {
                    instructionList = new InstructionList(method.getCode().getCode());
                } catch (final RuntimeException re) {
                    return new VerificationResult(VerificationResult.VERIFIED_REJECTED, "Bad bytecode in the code array of the Code attribute of method '" + method + "'.");
                }

                instructionList.setPositions(true);

                // Start verification.
                VerificationResult vr = VerificationResult.VR_OK; // default
                try {
                    delayedPass2Checks();
                } catch (final ClassConstraintException cce) {
                    vr = new VerificationResult(VerificationResult.VERIFIED_REJECTED, cce.getMessage());
                    return vr;
                }
                try {
                    pass3StaticInstructionChecks();
                    pass3StaticInstructionOperandsChecks();
                } catch (final StaticCodeConstraintException scce) {
                    vr = new VerificationResult(VerificationResult.VERIFIED_REJECTED, scce.getMessage());
                } catch (final ClassCastException cce) {
                    vr = new VerificationResult(VerificationResult.VERIFIED_REJECTED, "Class Cast Exception: " + cce.getMessage());
                }
                return vr;
            }
            // did not pass Pass 2.
            return VerificationResult.VR_NOTYET;
        } catch (final ClassNotFoundException e) {
            // FIXME: maybe not the best way to handle this
            throw new AssertionViolatedException("Missing class: " + e, e);
        }
    }

    public int getMethodNo() {
        return methodNo;
    }

    private void pass3StaticInstructionChecks() {

        // Code array must not be empty:
        // Enforced in pass 2 (also stated in the static constraints of the Code
        // array in vmspec2), together with pass 1 (reading code_length bytes and
        // interpreting them as code[]). So this must not be checked again here.

        if (code.getCode().length >= Const.MAX_CODE_SIZE) {// length must be LESS than the max
            throw new StaticCodeInstructionConstraintException("Code array in code attribute '" + code + "' too big: must be smaller than " + Const.MAX_CODE_SIZE + "65536 bytes.");
        }

        // First opcode at offset 0: okay, that's clear. Nothing to do.

        // Only instances of the instructions documented in Section 6.4 may appear in
        // the code array.

        // For BCEL's sake, we cannot handle WIDE stuff, but hopefully BCEL does its job
        // right :)

        // The last byte of the last instruction in the code array must be the byte at
        // index
        // code_length-1 : See the do_verify() comments. We actually don't iterate
        // through the
        // byte array, but use an InstructionList so we cannot check for this. But BCEL
        // does
        // things right, so it's implicitly okay.

        // TODO: Check how BCEL handles (and will handle) instructions like IMPDEP1,
        // IMPDEP2,
        // BREAKPOINT... that BCEL knows about but which are illegal anyway.
        // We currently go the safe way here.
        InstructionHandle ih = instructionList.getStart();
        while (ih != null) {
            final Instruction i = ih.getInstruction();
            if (i instanceof IMPDEP1) {
                throw new StaticCodeInstructionConstraintException("IMPDEP1 must not be in the code, it is an illegal instruction for _internal_ JVM use!");
            }
            if (i instanceof IMPDEP2) {
                throw new StaticCodeInstructionConstraintException("IMPDEP2 must not be in the code, it is an illegal instruction for _internal_ JVM use!");
            }
            if (i instanceof BREAKPOINT) {
                throw new StaticCodeInstructionConstraintException("BREAKPOINT must not be in the code, it is an illegal instruction for _internal_ JVM use!");
            }
            ih = ih.getNext();
        }

        // The original verifier seems to do this check here, too.
        // An unreachable last instruction may also not fall through the
        // end of the code, which is stupid -- but with the original
        // verifier's subroutine semantics one cannot predict reachability.
        final Instruction last = instructionList.getEnd().getInstruction();
        if (!((last instanceof ReturnInstruction) || (last instanceof RET) || (last instanceof GotoInstruction) || (last instanceof ATHROW))) {
            throw new StaticCodeInstructionConstraintException("Execution must not fall off the bottom of the code array." + " This constraint is enforced statically as some existing verifiers do" + " - so it may be a false alarm if the last instruction is not reachable.");
        }
    }

    private void pass3StaticInstructionOperandsChecks() {
        try {
            // When building up the InstructionList, BCEL has already done all those checks
            // mentioned in The Java Virtual Machine Specification, Second Edition, as
            // "static constraints on the operands of instructions in the code array".
            // TODO: see the do_verify() comments. Maybe we should really work on the
            // byte array first to give more comprehensive messages.
            // TODO: Review Exception API, possibly build in some "offending instruction"
            // thing
            // when we're ready to insulate the offending instruction by doing the
            // above thing.

            // TODO: Implement as much as possible here. BCEL does _not_ check everything.

            final ConstantPoolGen cpg = new ConstantPoolGen(Repository.lookupClass(myOwner.getClassName()).getConstantPool());
            final InstOperandConstraintVisitor v = new InstOperandConstraintVisitor(cpg);

            // Checks for the things BCEL does _not_ handle itself.
            InstructionHandle ih = instructionList.getStart();
            while (ih != null) {
                final Instruction i = ih.getInstruction();

                // An "own" constraint, due to JustIce's new definition of what "subroutine"
                // means.
                if (i instanceof JsrInstruction) {
                    final InstructionHandle target = ((JsrInstruction) i).getTarget();
                    if (target == instructionList.getStart()) {
                        throw new StaticCodeInstructionOperandConstraintException("Due to JustIce's clear definition of subroutines, no JSR or JSR_W may have a top-level instruction" + " (such as the very first instruction, which is targeted by instruction '" + ih + "' as its target.");
                    }
                    if (!(target.getInstruction() instanceof ASTORE)) {
                        throw new StaticCodeInstructionOperandConstraintException("Due to JustIce's clear definition of subroutines, no JSR or JSR_W may target anything else" + " than an ASTORE instruction. Instruction '" + ih + "' targets '" + target + "'.");
                    }
                }

                // vmspec2, page 134-137
                ih.accept(v);

                ih = ih.getNext();
            }

        } catch (final ClassNotFoundException e) {
            // FIXME: maybe not the best way to handle this
            throw new AssertionViolatedException("Missing class: " + e, e);
        }
    }

    private static boolean contains(final int[] ints, final int i) {
        for (final int k : ints) {
            if (k == i) {
                return true;
            }
        }
        return false;
    }
}