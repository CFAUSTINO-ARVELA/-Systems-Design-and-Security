
package university;

import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class TableModel {
    public static DefaultTableModel buildTableModel(ArrayList<ArrayList<String>> dataList) {

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = dataList.get(0).size();
        for (int column = 0; column < columnCount; column++) {
            columnNames.add(dataList.get(0).get(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        for (int rowIndex = 1; rowIndex < dataList.size(); rowIndex++) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 0;  columnIndex < columnCount; columnIndex++) {
            	vector.add(dataList.get(rowIndex).get(columnIndex));
            }
            data.add(vector);
        }

        
        return new DefaultTableModel(data, columnNames) {

            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                
                return false;
            }
        };

    }
  
}