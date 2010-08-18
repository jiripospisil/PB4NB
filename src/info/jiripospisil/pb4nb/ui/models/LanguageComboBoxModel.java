/*
 * PB4NB
 *
 * Copyright (c) 2010 Jiri Pospisil (jiripospisil.info)
 *
 * This software is licensed under the New BSD License. See
 * license.txt in the root directory of this software package
 * for more details.
 *
 */
package info.jiripospisil.pb4nb.ui.models;

import info.jiripospisil.pb4nb.utils.stores.LanguageStore;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Jiri Pospisil <mekishizufu@gmail.com>
 */
public class LanguageComboBoxModel implements ComboBoxModel {

    private final LanguageStore store = new LanguageStore();
    private Object item = null;

    @Override
    public void setSelectedItem(Object item) {
        this.item = item;
    }

    public void setSelectedItem(String code) {
        setSelectedItem(store.getElementByContentType(code));
    }

    @Override
    public Object getSelectedItem() {
        return item;
    }

    @Override
    public int getSize() {
        return store.getSize();
    }

    @Override
    public Object getElementAt(int index) {
        return store.getElementAt(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
    }
}
