package Utilities;
import org.testng.annotations.DataProvider;
import java.lang.reflect.Method;
public class DataProviders {

    @DataProvider(name = "loginData")
    public Object[][] getData(Method method) {
        String excelPath = "D:\\Freelancing\\Book1.xlsx";
        ExcelUtilities excel = new ExcelUtilities(excelPath, "Sheet2");

        int rowCount = excel.getRowCount();
        int colCount = excel.getColCount();

        Object data[][] = new Object[rowCount - 1][colCount];

        for (int i = 1; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = excel.getCellData(i, j);
            }
        }
        return data;
    }
}
