
package GenericUtility;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;

public class ExcelUtility {

    public String getData(String sheetName, int rowNum, int cellNum)throws Exception {

        try (FileInputStream fis = new FileInputStream("C:\\Users\\Preethi.M\\OneDrive\\Desktop\\userdata(2).xlsx");
        		Workbook wb = WorkbookFactory.create(fis)) {
        	Row row = wb.getSheet(sheetName).getRow(rowNum);
            if (row == null) {
                return "";
            }
            return row.getCell(cellNum,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().trim();
        }
    }
}
