
package org.apache.bcel.verifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class VerifierFactoryListModel implements VerifierFactoryObserver, javax.swing.ListModel<String> {

    private final List<ListDataListener> listeners = new ArrayList<>();
    private final Set<String> cache = new TreeSet<>();

    public VerifierFactoryListModel() {
        VerifierFactory.attach(this);
        update(null); // fill cache.
    }

    @Override
    public synchronized void addListDataListener(final ListDataListener l) {
        listeners.add(l);
    }

    @Override
    public synchronized String getElementAt(final int index) {
        return cache.toArray(new String[cache.size()])[index];
    }

    @Override
    public synchronized int getSize() {
        return cache.size();
    }

    @Override
    public synchronized void removeListDataListener(final javax.swing.event.ListDataListener l) {
        listeners.remove(l);
    }

    @Override
    public synchronized void update(final String s) {
        final Verifier[] verifiers = VerifierFactory.getVerifiers();
        final int num_of_verifiers = verifiers.length;
        cache.clear();
        for (final Verifier verifier : verifiers) {
            cache.add(verifier.getClassName().getName());
        }
        for (final ListDataListener listener : listeners) {
            final ListDataEvent e = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, num_of_verifiers - 1);
            listener.contentsChanged(e);
        }
    }

}
