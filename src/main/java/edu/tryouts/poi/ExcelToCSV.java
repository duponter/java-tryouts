package edu.tryouts.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Class {@link ExcelToCSV}.
 * https://dzone.com/articles/the-programmers-way-to-convert-excel-to-csv
 *
 * @author Erwin Dupont
 * @since 2017-03-24 (v0.8)
 */
public class ExcelToCSV {
    public static void main(String[] args) throws Exception {
        int index = 0;
        String xlsxFile = args[0];
        File csvFile = new File(args[1]);

        try (Workbook wb = new XSSFWorkbook(new File(xlsxFile))) {
            int sheetNo = Integer.parseInt(args[index++]);
            FormulaEvaluator fe = null;
            if (index < args.length) {
                fe = wb.getCreationHelper().createFormulaEvaluator();
            }
            DataFormatter formatter = new DataFormatter();
            try (PrintStream out = new PrintStream(new FileOutputStream(csvFile),
                    true, "UTF-8")) {
                byte[] bom = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
                out.write(bom);
                {
                    Sheet sheet = wb.getSheetAt(sheetNo);
                    for (int r = 0, rn = sheet.getLastRowNum(); r <= rn; r++) {
                        Row row = sheet.getRow(r);
                        if (row == null) {
                            out.println(',');
                            continue;
                        }
                        boolean firstCell = true;
                        for (int c = 0, cn = row.getLastCellNum(); c < cn; c++) {
                            Cell cell = row.getCell(c, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                            if (!firstCell) out.print(',');
                            if (cell != null) {
                                if (fe != null) cell = fe.evaluateInCell(cell);
                                String value = formatter.formatCellValue(cell);
                                if (cell.getCellTypeEnum() == CellType.FORMULA) {
                                    value = "=" + value;
                                }
                                out.print(encodeValue(value));
                            }
                            firstCell = false;
                        }
                        out.println();
                    }
                }
            }
        }
    }

    static private Pattern rxquote = Pattern.compile("\"");

    static private String encodeValue(String value) {
        boolean needQuotes = false;
        if ( value.indexOf(',') != -1 || value.indexOf('"') != -1 ||
                value.indexOf('\n') != -1 || value.indexOf('\r') != -1 )
            needQuotes = true;
        Matcher m = rxquote.matcher(value);
        if ( m.find() ) needQuotes = true; value = m.replaceAll("\"\"");
        if ( needQuotes ) return "\"" + value + "\"";
        else return value;
    }
}
