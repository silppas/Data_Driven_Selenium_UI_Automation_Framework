
package FTH.DataProvider;

import FTH.Util.ExcelReaderUtil;
import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "getData")
    public static Object[][] getData() {

        String path = System.getProperty("user.dir")
                + "\\src\\test\\java\\FTH\\Data\\LoginTestData.xlsx";

        int rowCount = ExcelReaderUtil.getRowCount(path, "Sheet1");

        int colCount = ExcelReaderUtil.getColumnCount(
                path, "Sheet1", 0);

        // Exclude header row
        int dataRows = rowCount - 1;

        if (dataRows <= 0) {
            return new Object[0][0];
        }

        Object[][] data = new Object[dataRows][colCount];

        for (int i = 1; i < rowCount; i++) {

            for (int j = 0; j < colCount; j++) {

                data[i - 1][j] =
                        ExcelReaderUtil.getCellData(
                                path, "Sheet1", i, j);
            }
        }

        return data;
    }
}
