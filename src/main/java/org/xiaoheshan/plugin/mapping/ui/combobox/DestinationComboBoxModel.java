package org.xiaoheshan.plugin.mapping.ui.combobox;

import org.xiaoheshan.plugin.mapping.util.CollectionsUtil;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author _Chf
 * @date 2017-12-16
 */
public class DestinationComboBoxModel extends DefaultComboBoxModel<String> {

    public DestinationComboBoxModel(List<String> data) {
        super(data);
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
