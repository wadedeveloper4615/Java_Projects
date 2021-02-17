package com.wade.decompiler.generic.base;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.wade.decompiler.Const;
import com.wade.decompiler.classfile.constant.Constant;
import com.wade.decompiler.generic.Select;
import com.wade.decompiler.generic.gen.ClassGenException;
import com.wade.decompiler.generic.gen.CodeExceptionGen;
import com.wade.decompiler.generic.gen.ConstantPoolGen;
import com.wade.decompiler.generic.gen.InstructionListObserver;
import com.wade.decompiler.generic.gen.LocalVariableGen;
import com.wade.decompiler.generic.gen.TargetLostException;
import com.wade.decompiler.util.ByteSequence;

@SuppressWarnings("incomplete-switch")
public class InstructionList implements Iterable<InstructionHandle> {
    private InstructionHandle start = null;
    private InstructionHandle end = null;
    private int length = 0; // number of elements in list
    private int[] bytePositions; // byte code offsets corresponding to instructions
    private List<InstructionListObserver> observers;

    public InstructionList() {
    }

    public InstructionList(BranchInstruction i) {
        append(i);
    }

    public InstructionList(byte[] code) {
        int count = 0; // Contains actual length
        int[] pos;
        InstructionHandle[] ihs;
        try (ByteSequence bytes = new ByteSequence(code)) {
            ihs = new InstructionHandle[code.length];
            pos = new int[code.length]; // Can't be more than that
            while (bytes.available() > 0) {
                // Remember byte offset and associate it with the instruction
                int off = bytes.getIndex();
                pos[count] = off;
                Instruction i = Instruction.readInstruction(bytes);
                InstructionHandle ih;
                if (i instanceof BranchInstruction) {
                    ih = append((BranchInstruction) i);
                } else {
                    ih = append(i);
                }
                ih.setPosition(off);
                ihs[count] = ih;
                count++;
            }
        } catch (IOException e) {
            throw new ClassGenException(e.toString(), e);
        }
        bytePositions = new int[count]; // Trim to proper size
        System.arraycopy(pos, 0, bytePositions, 0, count);
        for (int i = 0; i < count; i++) {
            if (ihs[i] instanceof BranchHandle) {
                BranchInstruction bi = (BranchInstruction) ihs[i].getInstruction();
                int target = bi.getPosition() + bi.getIndex();
                // Search for target position
                InstructionHandle ih = findHandle(ihs, pos, count, target);
                if (ih == null) {
                    throw new ClassGenException("Couldn't find target for branch: " + bi);
                }
                bi.setTarget(ih); // Update target
                // If it is a Select instruction, update all branch targets
                if (bi instanceof Select) { // Either LOOKUPSWITCH or TABLESWITCH
                    Select s = (Select) bi;
                    int[] indices = s.getIndices();
                    for (int j = 0; j < indices.length; j++) {
                        target = bi.getPosition() + indices[j];
                        ih = findHandle(ihs, pos, count, target);
                        if (ih == null) {
                            throw new ClassGenException("Couldn't find target for switch: " + bi);
                        }
                        s.setTarget(j, ih); // Update target
                    }
                }
            }
        }
    }

    public InstructionList(CompoundInstruction c) {
        append(c.getInstructionList());
    }

    public InstructionList(Instruction i) {
        append(i);
    }

    public void addObserver(InstructionListObserver o) {
        if (observers == null) {
            observers = new ArrayList<>();
        }
        observers.add(o);
    }

    public BranchHandle append(BranchInstruction i) {
        BranchHandle ih = BranchHandle.getBranchHandle(i);
        append(ih);
        return ih;
    }

    public InstructionHandle append(CompoundInstruction c) {
        return append(c.getInstructionList());
    }

    public InstructionHandle append(Instruction i) {
        InstructionHandle ih = InstructionHandle.getInstructionHandle(i);
        append(ih);
        return ih;
    }

    public InstructionHandle append(Instruction i, CompoundInstruction c) {
        return append(i, c.getInstructionList());
    }

    public InstructionHandle append(Instruction i, Instruction j) {
        return append(i, new InstructionList(j));
    }

    public InstructionHandle append(Instruction i, InstructionList il) {
        InstructionHandle ih;
        if ((ih = findInstruction2(i)) == null) {
            throw new ClassGenException("Instruction " + i + " is not contained in this list.");
        }
        return append(ih, il);
    }

    private void append(InstructionHandle ih) {
        if (isEmpty()) {
            start = end = ih;
            ih.setNext(ih.setPrev(null));
        } else {
            end.setNext(ih);
            ih.setPrev(end);
            ih.setNext(null);
            end = ih;
        }
        length++; // Update length
    }

    public BranchHandle append(InstructionHandle ih, BranchInstruction i) {
        BranchHandle bh = BranchHandle.getBranchHandle(i);
        InstructionList il = new InstructionList();
        il.append(bh);
        append(ih, il);
        return bh;
    }

    public InstructionHandle append(InstructionHandle ih, CompoundInstruction c) {
        return append(ih, c.getInstructionList());
    }

    public InstructionHandle append(InstructionHandle ih, Instruction i) {
        return append(ih, new InstructionList(i));
    }

    public InstructionHandle append(InstructionHandle ih, InstructionList il) {
        if (il == null) {
            throw new ClassGenException("Appending null InstructionList");
        }
        if (il.isEmpty()) {
            return ih;
        }
        InstructionHandle next = ih.getNext();
        InstructionHandle ret = il.start;
        ih.setNext(il.start);
        il.start.setPrev(ih);
        il.end.setNext(next);
        if (next != null) {
            next.setPrev(il.end);
        } else {
            end = il.end; // Update end ...
        }
        length += il.length; // Update length
        il.clear();
        return ret;
    }

    public InstructionHandle append(InstructionList il) {
        if (il == null) {
            throw new ClassGenException("Appending null InstructionList");
        }
        if (il.isEmpty()) {
            return null;
        }
        if (isEmpty()) {
            start = il.start;
            end = il.end;
            length = il.length;
            il.clear();
            return start;
        }
        return append(end, il); // was end.instruction
    }

    private void clear() {
        start = end = null;
        length = 0;
    }

    public boolean contains(Instruction i) {
        return findInstruction1(i) != null;
    }

    public boolean contains(InstructionHandle i) {
        if (i == null) {
            return false;
        }
        for (InstructionHandle ih = start; ih != null; ih = ih.getNext()) {
            if (ih == i) {
                return true;
            }
        }
        return false;
    }

    public InstructionList copy() {
        Map<InstructionHandle, InstructionHandle> map = new HashMap<>();
        InstructionList il = new InstructionList();
        for (InstructionHandle ih = start; ih != null; ih = ih.getNext()) {
            Instruction i = ih.getInstruction();
            Instruction c = i.copy(); // Use clone for shallow copy
            if (c instanceof BranchInstruction) {
                map.put(ih, il.append((BranchInstruction) c));
            } else {
                map.put(ih, il.append(c));
            }
        }
        InstructionHandle ih = start;
        InstructionHandle ch = il.start;
        while (ih != null) {
            Instruction i = ih.getInstruction();
            Instruction c = ch.getInstruction();
            if (i instanceof BranchInstruction) {
                BranchInstruction bi = (BranchInstruction) i;
                BranchInstruction bc = (BranchInstruction) c;
                InstructionHandle itarget = bi.getTarget(); // old target
                // New target is in hash map
                bc.setTarget(map.get(itarget));
                if (bi instanceof Select) { // Either LOOKUPSWITCH or TABLESWITCH
                    InstructionHandle[] itargets = ((Select) bi).getTargets();
                    InstructionHandle[] ctargets = ((Select) bc).getTargets();
                    for (int j = 0; j < itargets.length; j++) { // Update all targets
                        ctargets[j] = map.get(itargets[j]);
                    }
                }
            }
            ih = ih.getNext();
            ch = ch.getNext();
        }
        return il;
    }

    public void delete(Instruction i) throws TargetLostException {
        InstructionHandle ih;
        if ((ih = findInstruction1(i)) == null) {
            throw new ClassGenException("Instruction " + i + " is not contained in this list.");
        }
        delete(ih);
    }

    public void delete(Instruction from, Instruction to) throws TargetLostException {
        InstructionHandle from_ih;
        InstructionHandle to_ih;
        if ((from_ih = findInstruction1(from)) == null) {
            throw new ClassGenException("Instruction " + from + " is not contained in this list.");
        }
        if ((to_ih = findInstruction2(to)) == null) {
            throw new ClassGenException("Instruction " + to + " is not contained in this list.");
        }
        delete(from_ih, to_ih);
    }

    public void delete(InstructionHandle ih) throws TargetLostException {
        remove(ih.getPrev(), ih.getNext());
    }

    public void delete(InstructionHandle from, InstructionHandle to) throws TargetLostException {
        remove(from.getPrev(), to.getNext());
    }

    public void dispose() {
        // Traverse in reverse order, because ih.next is overwritten
        for (InstructionHandle ih = end; ih != null; ih = ih.getPrev()) {
            ih.dispose();
        }
        clear();
    }

    public InstructionHandle findHandle(int pos) {
        int[] positions = bytePositions;
        InstructionHandle ih = start;
        for (int i = 0; i < length; i++) {
            if (positions[i] == pos) {
                return ih;
            }
            ih = ih.getNext();
        }
        return null;
    }

    private InstructionHandle findInstruction1(Instruction i) {
        for (InstructionHandle ih = start; ih != null; ih = ih.getNext()) {
            if (ih.getInstruction() == i) {
                return ih;
            }
        }
        return null;
    }

    private InstructionHandle findInstruction2(Instruction i) {
        for (InstructionHandle ih = end; ih != null; ih = ih.getPrev()) {
            if (ih.getInstruction() == i) {
                return ih;
            }
        }
        return null;
    }

    public byte[] getByteCode() {
        // Update position indices of instructions
        setPositions();
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            for (InstructionHandle ih = start; ih != null; ih = ih.getNext()) {
                Instruction i = ih.getInstruction();
                i.dump(out); // Traverse list
            }
            out.flush();
        } catch (IOException e) {
            System.err.println(e);
            return new byte[0];
        }
        return b.toByteArray();
    }

    public InstructionHandle getEnd() {
        return end;
    }

    public InstructionHandle[] getInstructionHandles() {
        InstructionHandle[] ihs = new InstructionHandle[length];
        InstructionHandle ih = start;
        for (int i = 0; i < length; i++) {
            ihs[i] = ih;
            ih = ih.getNext();
        }
        return ihs;
    }

    public int[] getInstructionPositions() {
        return bytePositions;
    }

    public Instruction[] getInstructions() {
        List<Instruction> instructions = new ArrayList<>();
        try (ByteSequence bytes = new ByteSequence(getByteCode())) {
            while (bytes.available() > 0) {
                instructions.add(Instruction.readInstruction(bytes));
            }
        } catch (IOException e) {
            throw new ClassGenException(e.toString(), e);
        }
        return instructions.toArray(new Instruction[instructions.size()]);
    }

    public int getLength() {
        return length;
    }

    public InstructionHandle getStart() {
        return start;
    }

    public BranchHandle insert(BranchInstruction i) {
        BranchHandle ih = BranchHandle.getBranchHandle(i);
        insert(ih);
        return ih;
    }

    public InstructionHandle insert(CompoundInstruction c) {
        return insert(c.getInstructionList());
    }

    public InstructionHandle insert(Instruction i) {
        InstructionHandle ih = InstructionHandle.getInstructionHandle(i);
        insert(ih);
        return ih;
    }

    public InstructionHandle insert(Instruction i, CompoundInstruction c) {
        return insert(i, c.getInstructionList());
    }

    public InstructionHandle insert(Instruction i, Instruction j) {
        return insert(i, new InstructionList(j));
    }

    public InstructionHandle insert(Instruction i, InstructionList il) {
        InstructionHandle ih;
        if ((ih = findInstruction1(i)) == null) {
            throw new ClassGenException("Instruction " + i + " is not contained in this list.");
        }
        return insert(ih, il);
    }

    private void insert(InstructionHandle ih) {
        if (isEmpty()) {
            start = end = ih;
            ih.setNext(ih.setPrev(null));
        } else {
            start.setPrev(ih);
            ih.setNext(start);
            ih.setPrev(null);
            start = ih;
        }
        length++;
    }

    public BranchHandle insert(InstructionHandle ih, BranchInstruction i) {
        BranchHandle bh = BranchHandle.getBranchHandle(i);
        InstructionList il = new InstructionList();
        il.append(bh);
        insert(ih, il);
        return bh;
    }

    public InstructionHandle insert(InstructionHandle ih, CompoundInstruction c) {
        return insert(ih, c.getInstructionList());
    }

    public InstructionHandle insert(InstructionHandle ih, Instruction i) {
        return insert(ih, new InstructionList(i));
    }

    public InstructionHandle insert(InstructionHandle ih, InstructionList il) {
        if (il == null) {
            throw new ClassGenException("Inserting null InstructionList");
        }
        if (il.isEmpty()) {
            return ih;
        }
        InstructionHandle prev = ih.getPrev();
        InstructionHandle ret = il.start;
        ih.setPrev(il.end);
        il.end.setNext(ih);
        il.start.setPrev(prev);
        if (prev != null) {
            prev.setNext(il.start);
        } else {
            start = il.start; // Update start ...
        }
        length += il.length; // Update length
        il.clear();
        return ret;
    }

    public InstructionHandle insert(InstructionList il) {
        if (isEmpty()) {
            append(il); // Code is identical for this case
            return start;
        }
        return insert(start, il);
    }

    public boolean isEmpty() {
        return start == null;
    } // && end == null

    @Override
    public Iterator<InstructionHandle> iterator() {
        return new Iterator<InstructionHandle>() {
            private InstructionHandle ih = start;

            @Override
            public boolean hasNext() {
                return ih != null;
            }

            @Override
            public InstructionHandle next() throws NoSuchElementException {
                if (ih == null) {
                    throw new NoSuchElementException();
                }
                InstructionHandle i = ih;
                ih = ih.getNext();
                return i;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public void move(InstructionHandle ih, InstructionHandle target) {
        move(ih, ih, target);
    }

    public void move(InstructionHandle start, InstructionHandle end, InstructionHandle target) {
        // Step 1: Check constraints
        if ((start == null) || (end == null)) {
            throw new ClassGenException("Invalid null handle: From " + start + " to " + end);
        }
        if ((target == start) || (target == end)) {
            throw new ClassGenException("Invalid range: From " + start + " to " + end + " contains target " + target);
        }
        for (InstructionHandle ih = start; ih != end.getNext(); ih = ih.getNext()) {
            if (ih == null) {
                throw new ClassGenException("Invalid range: From " + start + " to " + end);
            } else if (ih == target) {
                throw new ClassGenException("Invalid range: From " + start + " to " + end + " contains target " + target);
            }
        }
        // Step 2: Temporarily remove the given instructions from the list
        InstructionHandle prev = start.getPrev();
        InstructionHandle next = end.getNext();
        if (prev != null) {
            prev.setNext(next);
        } else {
            this.start = next;
        }
        if (next != null) {
            next.setPrev(prev);
        } else {
            this.end = prev;
        }
        start.setPrev(end.setNext(null));
        // Step 3: append after target
        if (target == null) { // append to start of list
            if (this.start != null) {
                this.start.setPrev(end);
            }
            end.setNext(this.start);
            this.start = start;
        } else {
            next = target.getNext();
            target.setNext(start);
            start.setPrev(target);
            end.setNext(next);
            if (next != null) {
                next.setPrev(end);
            } else {
                this.end = end;
            }
        }
    }

    public void redirectBranches(InstructionHandle old_target, InstructionHandle new_target) {
        for (InstructionHandle ih = start; ih != null; ih = ih.getNext()) {
            Instruction i = ih.getInstruction();
            if (i instanceof BranchInstruction) {
                BranchInstruction b = (BranchInstruction) i;
                InstructionHandle target = b.getTarget();
                if (target == old_target) {
                    b.setTarget(new_target);
                }
                if (b instanceof Select) { // Either LOOKUPSWITCH or TABLESWITCH
                    InstructionHandle[] targets = ((Select) b).getTargets();
                    for (int j = 0; j < targets.length; j++) {
                        if (targets[j] == old_target) {
                            ((Select) b).setTarget(j, new_target);
                        }
                    }
                }
            }
        }
    }

    public void redirectExceptionHandlers(CodeExceptionGen[] exceptions, InstructionHandle old_target, InstructionHandle new_target) {
        for (CodeExceptionGen exception : exceptions) {
            if (exception.getStartPC() == old_target) {
                exception.setStartPC(new_target);
            }
            if (exception.getEndPC() == old_target) {
                exception.setEndPC(new_target);
            }
            if (exception.getHandlerPC() == old_target) {
                exception.setHandlerPC(new_target);
            }
        }
    }

    public void redirectLocalVariables(LocalVariableGen[] lg, InstructionHandle old_target, InstructionHandle new_target) {
        for (LocalVariableGen element : lg) {
            InstructionHandle start = element.getStart();
            InstructionHandle end = element.getEnd();
            if (start == old_target) {
                element.setStart(new_target);
            }
            if (end == old_target) {
                element.setEnd(new_target);
            }
        }
    }

    private void remove(InstructionHandle prev, InstructionHandle next) throws TargetLostException {
        InstructionHandle first;
        InstructionHandle last; // First and last deleted instruction
        if ((prev == null) && (next == null)) {
            first = start;
            last = end;
            start = end = null;
        } else {
            if (prev == null) { // At start of list
                first = start;
                start = next;
            } else {
                first = prev.getNext();
                prev.setNext(next);
            }
            if (next == null) { // At end of list
                last = end;
                end = prev;
            } else {
                last = next.getPrev();
                next.setPrev(prev);
            }
        }
        first.setPrev(null); // Completely separated from rest of list
        last.setNext(null);
        List<InstructionHandle> target_vec = new ArrayList<>();
        for (InstructionHandle ih = first; ih != null; ih = ih.getNext()) {
            ih.getInstruction().dispose(); // e.g. BranchInstructions release their targets
        }
        StringBuilder buf = new StringBuilder("{ ");
        for (InstructionHandle ih = first; ih != null; ih = next) {
            next = ih.getNext();
            length--;
            if (ih.hasTargeters()) { // Still got targeters?
                target_vec.add(ih);
                buf.append(ih.toString(true)).append(" ");
                ih.setNext(ih.setPrev(null));
            } else {
                ih.dispose();
            }
        }
        buf.append("}");
        if (!target_vec.isEmpty()) {
            InstructionHandle[] targeted = new InstructionHandle[target_vec.size()];
            target_vec.toArray(targeted);
            throw new TargetLostException(targeted, buf.toString());
        }
    }

    public void removeObserver(InstructionListObserver o) {
        if (observers != null) {
            observers.remove(o);
        }
    }

    public void replaceConstantPool(ConstantPoolGen old_cp, ConstantPoolGen new_cp) {
        for (InstructionHandle ih = start; ih != null; ih = ih.getNext()) {
            Instruction i = ih.getInstruction();
            if (i instanceof CPInstruction) {
                CPInstruction ci = (CPInstruction) i;
                Constant c = old_cp.getConstant(ci.getIndex());
                ci.setIndex(new_cp.addConstant(c, old_cp));
            }
        }
    }

    public void setPositions() { // TODO could be package-protected? (some test code would need to be repackaged)
        setPositions(false);
    }

    public void setPositions(boolean check) { // called by code in other packages
        int max_additional_bytes = 0;
        int additional_bytes = 0;
        int index = 0;
        int count = 0;
        int[] pos = new int[length];
        if (check) {
            for (InstructionHandle ih = start; ih != null; ih = ih.getNext()) {
                Instruction i = ih.getInstruction();
                if (i instanceof BranchInstruction) { // target instruction within list?
                    Instruction inst = ((BranchInstruction) i).getTarget().getInstruction();
                    if (!contains(inst)) {
                        throw new ClassGenException("Branch target of " + Const.getOpcodeName(i.getOpcode()) + ":" + inst + " not in instruction list");
                    }
                    if (i instanceof Select) {
                        InstructionHandle[] targets = ((Select) i).getTargets();
                        for (InstructionHandle target : targets) {
                            inst = target.getInstruction();
                            if (!contains(inst)) {
                                throw new ClassGenException("Branch target of " + Const.getOpcodeName(i.getOpcode()) + ":" + inst + " not in instruction list");
                            }
                        }
                    }
                    if (!(ih instanceof BranchHandle)) {
                        throw new ClassGenException("Branch instruction " + Const.getOpcodeName(i.getOpcode()) + ":" + inst + " not contained in BranchHandle.");
                    }
                }
            }
        }
        for (InstructionHandle ih = start; ih != null; ih = ih.getNext()) {
            Instruction i = ih.getInstruction();
            ih.setPosition(index);
            pos[count++] = index;
            switch (i.getOpcode()) {
                case JSR:
                case GOTO:
                    max_additional_bytes += 2;
                    break;
                case TABLESWITCH:
                case LOOKUPSWITCH:
                    max_additional_bytes += 3;
                    break;
            }
            index += i.getLength();
        }
        for (InstructionHandle ih = start; ih != null; ih = ih.getNext()) {
            additional_bytes += ih.updatePosition(additional_bytes, max_additional_bytes);
        }
        index = count = 0;
        for (InstructionHandle ih = start; ih != null; ih = ih.getNext()) {
            Instruction i = ih.getInstruction();
            ih.setPosition(index);
            pos[count++] = index;
            index += i.getLength();
        }
        bytePositions = new int[count]; // Trim to proper size
        System.arraycopy(pos, 0, bytePositions, 0, count);
    }

    public int size() {
        return length;
    }

    @Override
    public String toString() {
        return toString(true);
    }

    public String toString(boolean verbose) {
        StringBuilder buf = new StringBuilder();
        for (InstructionHandle ih = start; ih != null; ih = ih.getNext()) {
            buf.append(ih.toString(verbose)).append("\n");
        }
        return buf.toString();
    }

    public void update() {
        if (observers != null) {
            for (InstructionListObserver observer : observers) {
                observer.notify(this);
            }
        }
    }

    public static InstructionHandle findHandle(InstructionHandle[] ihs, int[] pos, int count, int target) {
        int l = 0;
        int r = count - 1;
        do {
            int i = (l + r) >>> 1;
            int j = pos[i];
            if (j == target) {
                return ihs[i];
            } else if (target < j) {
                r = i - 1;
            } else {
                l = i + 1;
            }
        } while (l <= r);
        return null;
    }
}
