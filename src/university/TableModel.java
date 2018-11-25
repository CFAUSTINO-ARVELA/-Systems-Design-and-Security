
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
        System.out.println(columnCount);
        for (int column = 0; column < columnCount; column++) {
            columnNames.add(dataList.get(0).get(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        for (int rowIndex = 1; rowIndex < dataList.size(); rowIndex++) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 0;  columnIndex < columnCount; columnIndex++) {
            	System.out.println(columnIndex);
            	System.out.println(dataList.get(rowIndex).get(columnIndex));
                vector.add(dataList.get(rowIndex).get(columnIndex));
            }
            System.out.println(vector.toString());
            data.add(vector);
        }

        
        return new DefaultTableModel(data, columnNames) {

            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                // all cells false
                return false;
            }
        };

    }
/**
    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames) {

            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                // all cells false
                return false;
            }
        };
    }  */
   
}