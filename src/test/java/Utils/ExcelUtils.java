package Utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtils {

    /**
     * Reads Excel and returns data in TestNG compatible Object[][].
     * Each row -> one Map<String, String>
     */
    public static Object[][] getTestData(String filePath, String sheetName) {

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException(
                        "Sheet '" + sheetName + "' not found in Excel: " + filePath
                );
            }

            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new RuntimeException(
                        "Header row is missing in sheet: " + sheetName
                );
            }

            int lastRow = sheet.getLastRowNum();
            int lastColumn = headerRow.getLastCellNum();

            Object[][] data = new Object[lastRow][1];

            for (int i = 1; i <= lastRow; i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Map<String, String> rowData = new HashMap<>();

                for (int j = 0; j < lastColumn; j++) {
                    Cell headerCell = headerRow.getCell(j);
                    Cell cell = row.getCell(j);

                    String key = headerCell.getStringCellValue().trim();
                    String value = getCellValueAsString(cell);

                    rowData.put(key, value);
                }

                // Add row number for logging
                rowData.put("RowNumber", String.valueOf(i));

                data[i - 1][0] = rowData;
            }

            return data;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(
                    "Failed to read Excel file: " + filePath, e
            );
        }
    }

    /**
     * Safely converts any cell type to String
     */
    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                }
                return String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
            default:
                return "";
        }
    }
}
