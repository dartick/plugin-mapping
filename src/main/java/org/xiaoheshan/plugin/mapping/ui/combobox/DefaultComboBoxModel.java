/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.ui.combobox;

import javax.swing.*;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author chenhongfa 17-12-19.
 */
public class DefaultComboBoxModel<T> extends AbstractListModel<T> implements MutableComboBoxModel<T> {

    protected List<T> data;
    protected T selectElement;

    public DefaultComboBoxModel(List<T> data) {
        this.data = data;
    }

    @Override
    public void addElement(T item) {
        for (T datum : data) {
            if (datum.equals(item)) {
                return;
            }
        }
        data.add(item);
        fireIntervalAdded(this,data.size()-1, data.size()-1);
        if (data.size() == 1 && selectElement == null && item != null) {
            setSelectedItem(item);
        }
    }

    @Override
    public void removeElement(Object obj) {
        int index = data.indexOf(obj);
        if ( index != -1 ) {
            removeElementAt(index);
        }
    }

    @Override
    public void insertElementAt(T item, int index) {
        for (T datum : data) {
            if (datum.equals(item)) {
                return;
            }
        }
        data.add(index, item);
        fireIntervalAdded(this, index, index);
    }

    @Override
    public void removeElementAt(int index) {
        if ( getElementAt( index ).equals(selectElement)) {
            if ( index == 0 ) {
                setSelectedItem( getSize() == 1 ? null : getElementAt( index + 1 ) );
            }
            else {
                setSelectedItem( getElementAt( index - 1 ) );
            }
        }

        data.remove(index);

        fireIntervalRemoved(this, index, index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        if ((selectElement != null && !selectElement.equals(anItem))) {
            selectElement = (T) anItem;
            fireContentsChanged(this, -1, -1);
        } else if (selectElement == null && anItem != null) {
            selectElement = (T) anItem;
            fireContentsChanged(this, -1, -1);
        }
    }

    @Override
    public Object getSelectedItem() {
        return selectElement;
    }

    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public T getElementAt(int index) {
        if ( index >= 0 && index < data.size() ) {
            return data.get(index);
        } else {
            return null;
        }
    }

}
