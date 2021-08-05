package tools;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;


public class ExcelReader {
    public static Sheet worksheet;  //The worksheet to read in Excel file

    public static Workbook workbook = null;

    public static Hashtable<String, Integer> dictionary = new Hashtable<String, Integer>();

    public ExcelReader(String excelSheetPath) throws IOException, BiffException {
        try {
            workbook = Workbook.getWorkbook(new File(excelSheetPath));
            worksheet = workbook.getSheet("sheet1");

        } catch (Exception e) {
            throw new IOException();
        }
    }

    //Returns the Number of Rows
    public static int RowCount() {
        return worksheet.getRows();
    }

    //Returns the Cell value by taking row and Column values as argument
    public static String ReadCell(int column, int row) {
        return worksheet.getCell(column, row).getContents();
    }

    //Create Column Dictionary to hold all the Column Names
    public static void ColumnDictionary() {
        for (int col = 0; col < worksheet.getColumns(); col++) {
            dictionary.put(ReadCell(col, 0), col);
        }
    }

    //Read Column Names
    public static int GetCell(String colName) {
        try {
            int value;
            value = ((Integer) dictionary.get(colName)).intValue();
            return value;
        } catch (NullPointerException e) {
            return (0);
        }
    }

}
