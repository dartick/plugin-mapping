/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package org.xiaoheshan.plugin.mapping.ui.table;

import org.xiaoheshan.plugin.mapping.util.CollectionsUtil;

import javax.swing.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author chenhongfa 17-12-18.
 */
public class DestinationComboBoxModel extends AbstractListModel<String> implements MutableComboBoxModel<String> {

    private List<String> data;
    private String selectElement;

    public DestinationComboBoxModel(List<String> data) {
        this.data = data;
    }

    @Override
    public void addElement(String item) {
        for (String datum : data) {
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
    public void insertElementAt(String item, int index) {
        for (String datum : data) {
            if (datum.equals(item)) {
                return;
            }
        }
        data.add(index, item);
        fireIntervalAdded(this, index, index);
    }

    @Override
    public void removeElementAt(int index) {
        if ( getElementAt( index ) == selectElement ) {
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
            selectElement = (String) anItem;
            fireContentsChanged(this, -1, -1);
        } else if (selectElement == null && anItem != null) {
            selectElement = (String) anItem;
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
    public String getElementAt(int index) {
        if ( index >= 0 && index < data.size() ) {
            return data.get(index);
        } else {
            return null;
        }
    }

    public void addDestinations(Collection<String> destinations) {
        if (CollectionsUtil.isEmpty(destinations)) {
            return;
        }
        int firstIndex = data.size();
        for (String destination : destinations) {
            if (data.contains(destination)) {
                continue;
            }
            data.add(destination);
        }

        int lastIndex = data.size() - 1;
        fireIntervalAdded(this, firstIndex, lastIndex);
    }

    public void removeDestinations(Collection<String> destinations) {
        if (CollectionsUtil.isEmpty(destinations)) {
            return;
        }
        Iterator<String> iterator = data.iterator();
        int oldLastRow = data.size() - 1;
        while (iterator.hasNext()) {
            String next = iterator.next();
            for (String destination : destinations) {
                if (next.equals(destination)) {
                    iterator.remove();
                    break;
                }
            }
        }
        fireIntervalRemoved(this, 0, oldLastRow);
    }

}
