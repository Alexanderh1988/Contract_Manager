import Model.TableObject;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ExcelExport {


    public static void main(String[] args) {

        new ExcelExport("C:\\Users\\Alex Hs\\Desktop\\Curso Sence de Osvaldo\\excel.xls", null);

    }

    public ExcelExport(String path, ArrayList<TableObject> data) {


        try {
            //declare file name to be create
            String filename = path;
            //creating an instance of HSSFWorkbook class
            HSSFWorkbook workbook = new HSSFWorkbook();
            //invoking creatSheet() method and passing the name of the sheet to be created
            HSSFSheet sheet = workbook.createSheet("Punteo");
            //creating the 0th row using the createRow() method
            HSSFRow rowhead = sheet.createRow((short) 0);
            //creating cell by using the createCell() method and setting the values to the cell by using the setCellValue() method
            rowhead.createCell(0).setCellValue("Id");
            rowhead.createCell(1).setCellValue("Documento");
            rowhead.createCell(2).setCellValue("Texto");
            rowhead.createCell(3).setCellValue("Clave");
            rowhead.createCell(4).setCellValue("Pagina");

            HSSFRow row;

            for (int i = 0; i < data.size(); i++) {


                //creating the 1st row
                row = sheet.createRow((short) i + 1);

                //inserting data in the first row
                row.createCell(0).setCellValue(data.get(i).getId());
                row.createCell(1).setCellValue(data.get(i).getDocumentName());

                row.createCell(2).setCellValue(data.get(i).getText());


                //*}  Cell cell = row.getCell(2);
            /*    XSSFRichTextString rts= new XSSFRichTextString("Hello ");
                //   XSSFFont fontBold= wb.createFont();
                fontBold.setBold(true); //set bold
                fontBold.setFontHeight(12); //add font size

                rts.append("world ",fontBold);
                rts.append("Hello");

                sheet.getRow(1).getCell(1).setCellValue(rts);
                //*
*/
                row.createCell(3).setCellValue(data.get(i).getKeyWord());
                row.createCell(4).setCellValue(data.get(i).getPage());

            }


            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            //closing the Stream
            fileOut.close();
            //closing the workbook
            workbook.close();
            //prints the message on the console

            System.out.println("Excel file has been generated successfully.");
            JOptionPane.showMessageDialog(null, "Exportado a la misma ubicacion que el directorio");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



/*        try {
        //declare file name to be create
        String filename = path;
        //creating an instance of HSSFWorkbook class
        HSSFWorkbook workbook = new HSSFWorkbook();
        //invoking creatSheet() method and passing the name of the sheet to be created
        HSSFSheet sheet = workbook.createSheet("January");
        //creating the 0th row using the createRow() method
        HSSFRow rowhead = sheet.createRow((short) 0);
        //creating cell by using the createCell() method and setting the values to the cell by using the setCellValue() method
        rowhead.createCell(0).setCellValue("S.No.");
        rowhead.createCell(1).setCellValue("Customer Name");
        rowhead.createCell(2).setCellValue("Account Number");
        rowhead.createCell(3).setCellValue("e-mail");
        rowhead.createCell(4).setCellValue("Balance");
        //creating the 1st row
        HSSFRow row = sheet.createRow((short) 1);
        //inserting data in the first row
        row.createCell(0).setCellValue("1");
        row.createCell(1).setCellValue("John William");
        row.createCell(2).setCellValue("9999999");
        row.createCell(3).setCellValue("william.john@gmail.com");
        row.createCell(4).setCellValue("700000.00");
        //creating the 2nd row
        HSSFRow row1 = sheet.createRow((short) 2);
        //inserting data in the second row
        row1.createCell(0).setCellValue("2");
        row1.createCell(1).setCellValue("Mathew Parker");
        row1.createCell(2).setCellValue("22222222");
        row1.createCell(3).setCellValue("parker.mathew@gmail.com");
        row1.createCell(4).setCellValue("200000.00");
        FileOutputStream fileOut = new FileOutputStream(filename);
        workbook.write(fileOut);
        //closing the Stream
        fileOut.close();
        //closing the workbook
        workbook.close();
        System.out.println("Excel file has been generated successfully.");
        //prints the message on the console*/

}

