package com.wade.decompiler.generic.gen;

import com.wade.decompiler.generic.*;
import com.wade.decompiler.generic.base.AllocationInstruction;
import com.wade.decompiler.generic.base.ArithmeticInstruction;
import com.wade.decompiler.generic.base.ArrayInstruction;
import com.wade.decompiler.generic.base.BranchInstruction;
import com.wade.decompiler.generic.base.CPInstruction;
import com.wade.decompiler.generic.base.ConstantPushInstruction;
import com.wade.decompiler.generic.base.ConversionInstruction;
import com.wade.decompiler.generic.base.ExceptionThrower;
import com.wade.decompiler.generic.base.FieldInstruction;
import com.wade.decompiler.generic.base.FieldOrMethod;
import com.wade.decompiler.generic.base.GotoInstruction;
import com.wade.decompiler.generic.base.IfInstruction;
import com.wade.decompiler.generic.base.JsrInstruction;
import com.wade.decompiler.generic.base.LoadClass;
import com.wade.decompiler.generic.base.LoadInstruction;
import com.wade.decompiler.generic.base.LocalVariableInstruction;
import com.wade.decompiler.generic.base.PopInstruction;
import com.wade.decompiler.generic.base.PushInstruction;
import com.wade.decompiler.generic.base.StackConsumer;
import com.wade.decompiler.generic.base.StackInstruction;
import com.wade.decompiler.generic.base.StackProducer;
import com.wade.decompiler.generic.base.StoreInstruction;
import com.wade.decompiler.generic.base.TypedInstruction;
import com.wade.decompiler.generic.base.UnconditionalBranch;
import com.wade.decompiler.generic.base.VariableLengthInstruction;

public abstract class EmptyVisitor implements Visitor {
    @Override
    public void visitStackInstruction(StackInstruction obj) {
    }

    @Override
    public void visitLocalVariableInstruction(LocalVariableInstruction obj) {
    }

    @Override
    public void visitBranchInstruction(BranchInstruction obj) {
    }

    @Override
    public void visitLoadClass(LoadClass obj) {
    }

    @Override
    public void visitFieldInstruction(FieldInstruction obj) {
    }

    @Override
    public void visitIfInstruction(IfInstruction obj) {
    }

    @Override
    public void visitConversionInstruction(ConversionInstruction obj) {
    }

    @Override
    public void visitPopInstruction(PopInstruction obj) {
    }

    @Override
    public void visitJsrInstruction(JsrInstruction obj) {
    }

    @Override
    public void visitGotoInstruction(GotoInstruction obj) {
    }

    @Override
    public void visitStoreInstruction(StoreInstruction obj) {
    }

    @Override
    public void visitTypedInstruction(TypedInstruction obj) {
    }

    @Override
    public void visitSelect(Select obj) {
    }

    @Override
    public void visitUnconditionalBranch(UnconditionalBranch obj) {
    }

    @Override
    public void visitPushInstruction(PushInstruction obj) {
    }

    @Override
    public void visitArithmeticInstruction(ArithmeticInstruction obj) {
    }

    @Override
    public void visitCPInstruction(CPInstruction obj) {
    }

    @Override
    public void visitInvokeInstruction(InvokeInstruction obj) {
    }

    @Override
    public void visitArrayInstruction(ArrayInstruction obj) {
    }

    @Override
    public void visitAllocationInstruction(AllocationInstruction obj) {
    }

    @Override
    public void visitReturnInstruction(ReturnInstruction obj) {
    }

    @Override
    public void visitFieldOrMethod(FieldOrMethod obj) {
    }

    @Override
    public void visitConstantPushInstruction(ConstantPushInstruction obj) {
    }

    @Override
    public void visitExceptionThrower(ExceptionThrower obj) {
    }

    @Override
    public void visitLoadInstruction(LoadInstruction obj) {
    }

    @Override
    public void visitVariableLengthInstruction(VariableLengthInstruction obj) {
    }

    @Override
    public void visitStackProducer(StackProducer obj) {
    }

    @Override
    public void visitStackConsumer(StackConsumer obj) {
    }

    @Override
    public void visitACONST_NULL(ACONST_NULL obj) {
    }

    @Override
    public void visitGETSTATIC(GETSTATIC obj) {
    }

    @Override
    public void visitIF_ICMPLT(IF_ICMPLT obj) {
    }

    @Override
    public void visitMONITOREXIT(MONITOREXIT obj) {
    }

    @Override
    public void visitIFLT(IFLT obj) {
    }

    @Override
    public void visitLSTORE(LSTORE obj) {
    }

    @Override
    public void visitPOP2(POP2 obj) {
    }

    @Override
    public void visitBASTORE(BASTORE obj) {
    }

    @Override
    public void visitISTORE(ISTORE obj) {
    }

    @Override
    public void visitCHECKCAST(CHECKCAST obj) {
    }

    @Override
    public void visitFCMPG(FCMPG obj) {
    }

    @Override
    public void visitI2F(I2F obj) {
    }

    @Override
    public void visitATHROW(ATHROW obj) {
    }

    @Override
    public void visitDCMPL(DCMPL obj) {
    }

    @Override
    public void visitARRAYLENGTH(ARRAYLENGTH obj) {
    }

    @Override
    public void visitDUP(DUP obj) {
    }

    @Override
    public void visitINVOKESTATIC(INVOKESTATIC obj) {
    }

    @Override
    public void visitLCONST(LCONST obj) {
    }

    @Override
    public void visitDREM(DREM obj) {
    }

    @Override
    public void visitIFGE(IFGE obj) {
    }

    @Override
    public void visitCALOAD(CALOAD obj) {
    }

    @Override
    public void visitLASTORE(LASTORE obj) {
    }

    @Override
    public void visitI2D(I2D obj) {
    }

    @Override
    public void visitDADD(DADD obj) {
    }

    @Override
    public void visitINVOKESPECIAL(INVOKESPECIAL obj) {
    }

    @Override
    public void visitIAND(IAND obj) {
    }

    @Override
    public void visitPUTFIELD(PUTFIELD obj) {
    }

    @Override
    public void visitILOAD(ILOAD obj) {
    }

    @Override
    public void visitDLOAD(DLOAD obj) {
    }

    @Override
    public void visitDCONST(DCONST obj) {
    }

    @Override
    public void visitNEW(NEW obj) {
    }

    @Override
    public void visitIFNULL(IFNULL obj) {
    }

    @Override
    public void visitLSUB(LSUB obj) {
    }

    @Override
    public void visitL2I(L2I obj) {
    }

    @Override
    public void visitISHR(ISHR obj) {
    }

    @Override
    public void visitTABLESWITCH(TABLESWITCH obj) {
    }

    @Override
    public void visitIINC(IINC obj) {
    }

    @Override
    public void visitDRETURN(DRETURN obj) {
    }

    @Override
    public void visitFSTORE(FSTORE obj) {
    }

    @Override
    public void visitDASTORE(DASTORE obj) {
    }

    @Override
    public void visitIALOAD(IALOAD obj) {
    }

    @Override
    public void visitDDIV(DDIV obj) {
    }

    @Override
    public void visitIF_ICMPGE(IF_ICMPGE obj) {
    }

    @Override
    public void visitLAND(LAND obj) {
    }

    @Override
    public void visitIDIV(IDIV obj) {
    }

    @Override
    public void visitLOR(LOR obj) {
    }

    @Override
    public void visitCASTORE(CASTORE obj) {
    }

    @Override
    public void visitFREM(FREM obj) {
    }

    @Override
    public void visitLDC(LDC obj) {
    }

    @Override
    public void visitBIPUSH(BIPUSH obj) {
    }

    @Override
    public void visitDSTORE(DSTORE obj) {
    }

    @Override
    public void visitF2L(F2L obj) {
    }

    @Override
    public void visitFMUL(FMUL obj) {
    }

    @Override
    public void visitLLOAD(LLOAD obj) {
    }

    @Override
    public void visitJSR(JSR obj) {
    }

    @Override
    public void visitFSUB(FSUB obj) {
    }

    @Override
    public void visitSASTORE(SASTORE obj) {
    }

    @Override
    public void visitALOAD(ALOAD obj) {
    }

    @Override
    public void visitDUP2_X2(DUP2_X2 obj) {
    }

    @Override
    public void visitRETURN(RETURN obj) {
    }

    @Override
    public void visitDALOAD(DALOAD obj) {
    }

    @Override
    public void visitSIPUSH(SIPUSH obj) {
    }

    @Override
    public void visitDSUB(DSUB obj) {
    }

    @Override
    public void visitL2F(L2F obj) {
    }

    @Override
    public void visitIF_ICMPGT(IF_ICMPGT obj) {
    }

    @Override
    public void visitF2D(F2D obj) {
    }

    @Override
    public void visitI2L(I2L obj) {
    }

    @Override
    public void visitIF_ACMPNE(IF_ACMPNE obj) {
    }

    @Override
    public void visitPOP(POP obj) {
    }

    @Override
    public void visitI2S(I2S obj) {
    }

    @Override
    public void visitIFEQ(IFEQ obj) {
    }

    @Override
    public void visitSWAP(SWAP obj) {
    }

    @Override
    public void visitIOR(IOR obj) {
    }

    @Override
    public void visitIREM(IREM obj) {
    }

    @Override
    public void visitIASTORE(IASTORE obj) {
    }

    @Override
    public void visitNEWARRAY(NEWARRAY obj) {
    }

    @Override
    public void visitINVOKEINTERFACE(INVOKEINTERFACE obj) {
    }

    @Override
    public void visitINEG(INEG obj) {
    }

    @Override
    public void visitLCMP(LCMP obj) {
    }

    @Override
    public void visitJSR_W(JSR_W obj) {
    }

    @Override
    public void visitMULTIANEWARRAY(MULTIANEWARRAY obj) {
    }

    @Override
    public void visitDUP_X2(DUP_X2 obj) {
    }

    @Override
    public void visitSALOAD(SALOAD obj) {
    }

    @Override
    public void visitIFNONNULL(IFNONNULL obj) {
    }

    @Override
    public void visitDMUL(DMUL obj) {
    }

    @Override
    public void visitIFNE(IFNE obj) {
    }

    @Override
    public void visitIF_ICMPLE(IF_ICMPLE obj) {
    }

    @Override
    public void visitLDC2_W(LDC2_W obj) {
    }

    @Override
    public void visitGETFIELD(GETFIELD obj) {
    }

    @Override
    public void visitLADD(LADD obj) {
    }

    @Override
    public void visitNOP(NOP obj) {
    }

    @Override
    public void visitFALOAD(FALOAD obj) {
    }

    @Override
    public void visitINSTANCEOF(INSTANCEOF obj) {
    }

    @Override
    public void visitIFLE(IFLE obj) {
    }

    @Override
    public void visitLXOR(LXOR obj) {
    }

    @Override
    public void visitLRETURN(LRETURN obj) {
    }

    @Override
    public void visitFCONST(FCONST obj) {
    }

    @Override
    public void visitIUSHR(IUSHR obj) {
    }

    @Override
    public void visitBALOAD(BALOAD obj) {
    }

    @Override
    public void visitDUP2(DUP2 obj) {
    }

    @Override
    public void visitIF_ACMPEQ(IF_ACMPEQ obj) {
    }

    @Override
    public void visitIMPDEP1(IMPDEP1 obj) {
    }

    @Override
    public void visitMONITORENTER(MONITORENTER obj) {
    }

    @Override
    public void visitLSHL(LSHL obj) {
    }

    @Override
    public void visitDCMPG(DCMPG obj) {
    }

    @Override
    public void visitD2L(D2L obj) {
    }

    @Override
    public void visitIMPDEP2(IMPDEP2 obj) {
    }

    @Override
    public void visitL2D(L2D obj) {
    }

    @Override
    public void visitRET(RET obj) {
    }

    @Override
    public void visitIFGT(IFGT obj) {
    }

    @Override
    public void visitIXOR(IXOR obj) {
    }

    @Override
    public void visitINVOKEVIRTUAL(INVOKEVIRTUAL obj) {
    }

    @Override
    public void visitFASTORE(FASTORE obj) {
    }

    @Override
    public void visitIRETURN(IRETURN obj) {
    }

    @Override
    public void visitIF_ICMPNE(IF_ICMPNE obj) {
    }

    @Override
    public void visitFLOAD(FLOAD obj) {
    }

    @Override
    public void visitLDIV(LDIV obj) {
    }

    @Override
    public void visitPUTSTATIC(PUTSTATIC obj) {
    }

    @Override
    public void visitAALOAD(AALOAD obj) {
    }

    @Override
    public void visitD2I(D2I obj) {
    }

    @Override
    public void visitIF_ICMPEQ(IF_ICMPEQ obj) {
    }

    @Override
    public void visitAASTORE(AASTORE obj) {
    }

    @Override
    public void visitARETURN(ARETURN obj) {
    }

    @Override
    public void visitDUP2_X1(DUP2_X1 obj) {
    }

    @Override
    public void visitFNEG(FNEG obj) {
    }

    @Override
    public void visitGOTO_W(GOTO_W obj) {
    }

    @Override
    public void visitD2F(D2F obj) {
    }

    @Override
    public void visitGOTO(GOTO obj) {
    }

    @Override
    public void visitISUB(ISUB obj) {
    }

    @Override
    public void visitF2I(F2I obj) {
    }

    @Override
    public void visitDNEG(DNEG obj) {
    }

    @Override
    public void visitICONST(ICONST obj) {
    }

    @Override
    public void visitFDIV(FDIV obj) {
    }

    @Override
    public void visitI2B(I2B obj) {
    }

    @Override
    public void visitLNEG(LNEG obj) {
    }

    @Override
    public void visitLREM(LREM obj) {
    }

    @Override
    public void visitIMUL(IMUL obj) {
    }

    @Override
    public void visitIADD(IADD obj) {
    }

    @Override
    public void visitLSHR(LSHR obj) {
    }

    @Override
    public void visitLOOKUPSWITCH(LOOKUPSWITCH obj) {
    }

    @Override
    public void visitDUP_X1(DUP_X1 obj) {
    }

    @Override
    public void visitFCMPL(FCMPL obj) {
    }

    @Override
    public void visitI2C(I2C obj) {
    }

    @Override
    public void visitLMUL(LMUL obj) {
    }

    @Override
    public void visitLUSHR(LUSHR obj) {
    }

    @Override
    public void visitISHL(ISHL obj) {
    }

    @Override
    public void visitLALOAD(LALOAD obj) {
    }

    @Override
    public void visitASTORE(ASTORE obj) {
    }

    @Override
    public void visitANEWARRAY(ANEWARRAY obj) {
    }

    @Override
    public void visitFRETURN(FRETURN obj) {
    }

    @Override
    public void visitFADD(FADD obj) {
    }

    @Override
    public void visitBREAKPOINT(BREAKPOINT obj) {
    }

    @Override
    public void visitINVOKEDYNAMIC(INVOKEDYNAMIC obj) {
    }
}
