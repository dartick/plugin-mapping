package org.xiaoheshan.plugin.mapping.ui.table;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * @author _Chf
 * @date 2017-12-16
 */
public class MappingTableModel extends AbstractTableModel implements DestinationTableCellEditor.AfterEditedListener {

    private static final String[] columnNames = new String[] {"Origin", "Destination"};
    private List<MappingModel> data;

    public static final int ORIGIN_COLUMN_INDEX = 0;
    public static final int DESTINATION_COLUMN_INDEX = 1;

    public MappingTableModel(List<MappingModel> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case ORIGIN_COLUMN_INDEX:
                return data.get(rowIndex).getOrigin();
            case DESTINATION_COLUMN_INDEX:
            default:
                 return data.get(rowIndex).getDestination();
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }


    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void afterEdited(int row, int column, Object value) {
        switch (column) {
            case ORIGIN_COLUMN_INDEX:
                data.get(row).setOrigin((String) value);
                break;
            case DESTINATION_COLUMN_INDEX:
            default:
                data.get(row).setDestination((String) value);
        }
        fireTableCellUpdated(row, column);
    }

    public void addMaping(String origin, String destination) {
        addMapping(new MappingModel(origin, destination));
    }

    public void addMapping(MappingModel mappingModel) {
        int lastRow = getRowCount();
        data.add(mappingModel);
        fireTableRowsInserted(lastRow, lastRow);
    }

}
