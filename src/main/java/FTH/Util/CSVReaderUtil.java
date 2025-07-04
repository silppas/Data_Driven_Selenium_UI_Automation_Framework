package FTH.Util;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import lombok.var;

public class CSVReaderUtil {

    public static List<String> readCSVColumn(String filePath, String columnName) {
        List<String> columnData = new ArrayList<>();
        
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            // Read all lines
            List<String[]> allRows = reader.readAll();
            
            if (allRows.isEmpty()) {
                System.out.println("CSV file is empty.");
                return columnData;
            }

            // Read header and get column index
            String[] header = allRows.get(0);
            int columnIndex = -1;
            
            for (int i = 0; i < header.length; i++) {
                if (header[i].equalsIgnoreCase(columnName)) {
                    columnIndex = i;
                    break;
                }
            }
            
            if (columnIndex == -1) {
                System.out.println("Column name not found in the CSV file.");
                return columnData;
            }

            // Read data rows and add the required column to the list
            for (int i = 1; i < allRows.size(); i++) {
                String[] row = allRows.get(i);
                if (row.length > columnIndex) {
                    columnData.add(row[columnIndex]); // Add column value to list
                }
            }

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return columnData;
    }
}
