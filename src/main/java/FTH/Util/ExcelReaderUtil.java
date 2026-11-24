
package FTH.Util;

import org.apache.poi.ss.usermodel.*;

import java.io.InputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class ExcelReaderUtil {

    private ExcelReaderUtil() {
        // Prevent object creation
    }

    // ==========================================
    // 1. GET SHEET COUNT
    // ==========================================

    public static int getSheetCount(String filePath) {

        return execute(filePath, Workbook::getNumberOfSheets);
    }

    // ==========================================
    // 2. GET SHEET NAME BY INDEX
    // ==========================================

    public static String getSheetName(
            String filePath, int sheetIndex) {

        return execute(filePath, workbook -> {

            validateSheetIndex(workbook, sheetIndex);

            return workbook.getSheetName(sheetIndex);
        });
    }

    // ==========================================
    // 3. CHECK SHEET EXISTS
    // ==========================================

    public static boolean isSheetPresent(
            String filePath, String sheetName) {

        return execute(filePath, workbook ->
                workbook.getSheet(sheetName) != null);
    }

    // ==========================================
    // 4. GET ROW COUNT
    // ==========================================

    // Returns number of physically defined rows.
    public static int getRowCount(
            String filePath, String sheetName) {

        return execute(filePath, workbook -> {

            Sheet sheet = getSheet(workbook, sheetName);

            return sheet.getPhysicalNumberOfRows();
        });
    }

    // ==========================================
    // 5. GET LAST ROW INDEX
    // ==========================================

    public static int getLastRowIndex(
            String filePath, String sheetName) {

        return execute(filePath, workbook -> {

            Sheet sheet = getSheet(workbook, sheetName);

            return sheet.getLastRowNum();
        });
    }

    // ==========================================
    // 6. GET COLUMN COUNT
    // ==========================================

    public static int getColumnCount(
        String filePath,
        String sheetName,
        int rowIndex) {

    return execute(filePath, workbook -> {

        Sheet sheet = getSheet(workbook, sheetName);

        validateRowIndex(sheet, rowIndex);

        Row row = sheet.getRow(rowIndex);

        if (row == null) {
            return 0;
        }

        return Math.max(0, row.getLastCellNum());
    });
}

    // ==========================================
    // 7. GET CELL DATA BY ROW AND COLUMN INDEX
    // ==========================================

    public static String getCellData(
            String filePath,
            String sheetName,
            int rowIndex,
            int columnIndex) {

        return execute(filePath, workbook -> {

            Sheet sheet = getSheet(workbook, sheetName);

            validateRowIndex(sheet, rowIndex);

            Row row = sheet.getRow(rowIndex);

            if (row == null) {
                return "";
            }

            Cell cell = row.getCell(
                    columnIndex,
                    Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

            return formatCell(cell, workbook);
        });
    }

    // ==========================================
    // 8. GET COMPLETE ROW DATA
    // ==========================================

    public static List<String> getRowData(
            String filePath,
            String sheetName,
            int rowIndex) {

        return execute(filePath, workbook -> {

            Sheet sheet = getSheet(workbook, sheetName);

            validateRowIndex(sheet, rowIndex);

            Row row = sheet.getRow(rowIndex);

            List<String> rowData = new ArrayList<>();

            if (row == null) {
                return rowData;
            }

            for (int i = 0; i < row.getLastCellNum(); i++) {

                Cell cell = row.getCell(
                        i,
                        Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

                rowData.add(formatCell(cell, workbook));
            }

            return rowData;
        });
    }

    // ==========================================
    // 9. GET COMPLETE COLUMN DATA
    // ==========================================

    public static List<String> getColumnData(
            String filePath,
            String sheetName,
            int columnIndex) {

        return execute(filePath, workbook -> {

            Sheet sheet = getSheet(workbook, sheetName);

            List<String> columnData = new ArrayList<>();

            for (int i = 0; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                if (row == null) {
                    columnData.add("");
                    continue;
                }

                Cell cell = row.getCell(
                        columnIndex,
                        Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

                columnData.add(formatCell(cell, workbook));
            }

            return columnData;
        });
    }

    // ==========================================
    // 10. GET COMPLETE SHEET DATA
    // ==========================================

    public static List<List<String>> getSheetData(
            String filePath,
            String sheetName) {

        return execute(filePath, workbook -> {

            Sheet sheet = getSheet(workbook, sheetName);

            List<List<String>> sheetData = new ArrayList<>();

            for (int i = 0; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                List<String> rowData = new ArrayList<>();

                if (row != null) {

                    for (int j = 0; j < row.getLastCellNum(); j++) {

                        Cell cell = row.getCell(
                                j,
                                Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

                        rowData.add(formatCell(cell, workbook));
                    }
                }

                sheetData.add(rowData);
            }

            return sheetData;
        });
    }

    // ==========================================
    // 11. GET DATA AS OBJECT ARRAY
    // ==========================================

    public static Object[][] getDataAsArray(
            String filePath,
            String sheetName,
            boolean skipHeader) {

        List<List<String>> data =
                getSheetData(filePath, sheetName);

        int startRow = skipHeader ? 1 : 0;

        if (data.size() <= startRow) {
            return new Object[0][0];
        }

        Object[][] result =
                new Object[data.size() - startRow][];

        for (int i = startRow; i < data.size(); i++) {

            List<String> row = data.get(i);

            result[i - startRow] =
                    row.toArray(new Object[0]);
        }

        return result;
    }

    // ==========================================
    // 12. PRIVATE HELPER METHODS
    // ==========================================

    private static Sheet getSheet(
            Workbook workbook, String sheetName) {

        Sheet sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            throw new IllegalArgumentException(
                    "Sheet not found: " + sheetName);
        }

        return sheet;
    }

    private static void validateSheetIndex(
            Workbook workbook, int sheetIndex) {

        if (sheetIndex < 0
                || sheetIndex >= workbook.getNumberOfSheets()) {

            throw new IllegalArgumentException(
                    "Invalid sheet index: " + sheetIndex);
        }
    }

    private static void validateRowIndex(
            Sheet sheet, int rowIndex) {

        if (rowIndex < 0
                || rowIndex > sheet.getLastRowNum()) {

            throw new IllegalArgumentException(
                    "Invalid row index: " + rowIndex);
        }
    }

    private static String formatCell(
            Cell cell, Workbook workbook) {

        if (cell == null) {
            return "";
        }

        DataFormatter formatter = new DataFormatter();

        FormulaEvaluator evaluator =
                workbook.getCreationHelper()
                        .createFormulaEvaluator();

        return formatter.formatCellValue(
                cell, evaluator).trim();
    }

    // ==========================================
    // 13. COMMON WORKBOOK EXECUTION METHOD
    // ==========================================

    private static <T> T execute(
            String filePath,
            java.util.function.Function<Workbook, T> operation) {

        Path path = Path.of(filePath);

        if (!Files.exists(path)) {
            throw new IllegalArgumentException(
                    "Excel file not found: " + filePath);
        }

        try (InputStream inputStream =
                     Files.newInputStream(path);

             Workbook workbook =
                     WorkbookFactory.create(inputStream)) {

            return operation.apply(workbook);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Error reading Excel file: " + filePath, e);
        }
    }
}
