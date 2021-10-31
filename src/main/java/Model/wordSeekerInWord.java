package Model;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbookFactory;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class wordSeekerInWord {

    // public static String filenameToSeek = "D:\\Software_hstech\\Contract_Manager\\articles-97403_Teatro.docx";
    ArrayList<String> soughtWords = new ArrayList<>();
    Boolean wordFound=false;
    private ArrayList<Integer> pagesOfTextFound = new ArrayList<>();
    private ArrayList<String> nameOfFileWordFound = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        //  new wordSeekerInWord("nacido", filenameToSeek);
    }

    public wordSeekerInWord(String soughtWord, String fileName) throws IOException, InvalidFormatException {

        System.out.println(fileName);

        //   OPCPackage pkg = OPCPackage.open(new File(fileName));

        // try (Workbook doc = new HSSFWorkbook(Files.newInputStream(Paths.get(fileName)))) {

        try (HWPFDocument doc = new HWPFDocument(Files.newInputStream(Paths.get(fileName)))) {

            //try (XWPFDocument doc = new XWPFDocument(Files.newInputStream(Paths.get(fileName)))) {

            // /&/ try (XSSFWorkbook doc = new XSSFWorkbook(Files.newInputStream(Paths.get(fileName)))) {


            //   try (HSSFWorkbook doc = new HSSFWorkbook(Files.newInputStream(Paths.get(fileName)))) {
            //      doc.getSheetAt(2).get;
            //Get first/desired sheet from the workbook
            //   XSSFSheet mySheet = doc.getSheetAt(0);

            WordExtractor extractor = new WordExtractor(doc);

            System.out.println(extractor.getText());
            //  XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(doc);

            //System.out.println(xwpfWordExtractor.getText());

            //HSSFWorkbook myWorkBook = new HSSFWorkbook(doc);


            //HSSFWorkbookFactory

            //XSSFWorkbook myWorkBook = new XSSFWorkbook(Files.newInputStream(Paths.get(fileName)));

            // Return first sheet from the XLSX workbook
            //  XSSFSheet mySheet = myWorkBook.getSheetAt(0);


            //    String restText = xwpfWordExtractor.getText();
            int largoDeExtracto;

            String restText = "";
            //Integer pages = doc.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();
            //   System.out.println("PAGES " + pages);

            int foundIndex; // = restText.toLowerCase().indexOf(soughtWord.toLowerCase());

            largoDeExtracto = 400;
            foundIndex = restText.indexOf(soughtWord);

            while (foundIndex != -1) {

                if (restText.length() > foundIndex + largoDeExtracto && foundIndex > largoDeExtracto) {
                    soughtWords.add(restText.substring(foundIndex - largoDeExtracto, foundIndex + largoDeExtracto));
                    // pagesOfTextFound.add(i);
                    nameOfFileWordFound.add(fileName);
                    wordFound = true;

                    int textLength = restText.length();
                    restText = restText.substring(foundIndex + soughtWord.length(), textLength);
                    foundIndex = restText.indexOf(soughtWord);
                } else {
                    largoDeExtracto = largoDeExtracto - 20;
                }
                int textLength = restText.length();
                restText = restText.substring(foundIndex + soughtWord.length(), textLength);

                //foundWords++;
                //if (contadorMaximaIteracion == 30) break;
            }
            //System.out.println("palabras encontradas: " + foundWords);

        }
      //  if (!wordFound) JOptionPane.showMessageDialog(null, "No se encontro palabras en " + fileName);
    }

    public ArrayList<String> getSoughtWords() {
        return soughtWords;
    }

    public Boolean getWordFound() {
        return wordFound;
    }

    public ArrayList<Integer> getPagesOfTextFound() {
        return pagesOfTextFound;
    }

    public ArrayList<String> getNameOfFileWordFound() {
        return nameOfFileWordFound;
    }
}

