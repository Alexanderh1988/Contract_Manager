package Model;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class wordSeekerInWord {

    public static String filenameToSeek = "D:\\Software_hstech\\Contract_Manager\\articles-97403_Teatro.docx";

    public static void main(String[] args) throws IOException {

        new wordSeekerInWord("nacido",filenameToSeek);
    }

    public wordSeekerInWord(String soughtWord, String fileName) throws IOException {

     //   String fileName = "D:\\Software_hstech\\Contract_Manager\\articles-97403_Teatro.docx";

        try (XWPFDocument doc = new XWPFDocument(Files.newInputStream(Paths.get(fileName)))) {

            XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(doc);
            String docText = xwpfWordExtractor.getText();
            //System.out.println(docText);

            System.out.println(docText.length());

            // find number of words in the document
            //long count = Arrays.stream(docText.split("\\s+")).count();
            //System.out.println("Total words: " + count);

            //System.out.println(docText.indexOf(" nacido(a) el "));


            System.out.println(docText.substring(docText.indexOf(soughtWord) - 50, docText.indexOf(soughtWord) + 50));

            // System.out.println(docText.substring(docText.indexOf(" nacido(a) el ") - 50, docText.indexOf(" nacido(a) el ") + 50));
        }

    }
}
