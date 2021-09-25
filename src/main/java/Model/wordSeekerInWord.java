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
import java.util.ArrayList;
import java.util.Arrays;

public class wordSeekerInWord {

    public static String filenameToSeek = "D:\\Software_hstech\\Contract_Manager\\articles-97403_Teatro.docx";
    ArrayList<String> soughtWords = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        new wordSeekerInWord("nacido", filenameToSeek);
    }

    public wordSeekerInWord(String soughtWord, String fileName) throws IOException {

        //   String fileName = "D:\\Software_hstech\\Contract_Manager\\articles-97403_Teatro.docx";

        try (XWPFDocument doc = new XWPFDocument(Files.newInputStream(Paths.get(fileName)))) {

            XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(doc);
            String docText = xwpfWordExtractor.getText();

            //    System.out.println(docText.length());

            int foundIndex = docText.indexOf(soughtWord);
            int textLength = docText.length();
            String restText = docText;
            int foundWords = 0;
            int largoDeExtracto = 30;

            while (foundIndex != -1) {

                try {
                    soughtWords.add(restText.substring(foundIndex - largoDeExtracto, foundIndex + largoDeExtracto));
                } catch (Exception e) {
                }
                textLength = restText.length();
                restText = restText.substring(foundIndex + 10, textLength);
                foundIndex = restText.indexOf(soughtWord);
                foundWords++;
            }
            System.out.println("palabras encontradas: " + foundWords);

            //   System.out.println(docText.substring(docText.indexOf(soughtWord) - 50, docText.indexOf(soughtWord) + 50));

          /*  for (String e : soughtWords) {
                System.out.println(e);
            }*/

            // System.out.println(docText.substring(docText.indexOf(" nacido(a) el ") - 50, docText.indexOf(" nacido(a) el ") + 50));
        }

    }

    public ArrayList<String> getSoughtWords() {
        return soughtWords;
    }
}

