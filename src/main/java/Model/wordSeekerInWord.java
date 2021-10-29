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

   // public static String filenameToSeek = "D:\\Software_hstech\\Contract_Manager\\articles-97403_Teatro.docx";
    ArrayList<String> soughtWords = new ArrayList<>();

    public static void main(String[] args) throws IOException {

      //  new wordSeekerInWord("nacido", filenameToSeek);
    }

    public wordSeekerInWord(String soughtWord, String fileName) throws IOException {

        //   String fileName = "D:\\Software_hstech\\Contract_Manager\\articles-97403_Teatro.docx";

        System.out.println(fileName);

        try (XWPFDocument doc = new XWPFDocument(Files.newInputStream(Paths.get(fileName)))) {

            XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(doc);

            String docText = xwpfWordExtractor.getText();
            int foundIndex = docText.indexOf(soughtWord);
            String restText = docText;
            int foundWords = 0;
            int largoDeExtracto;
            int contadorMaximaIteracion = 0;

            while (foundIndex != -1) {

                largoDeExtracto = 30;
                contadorMaximaIteracion++;
                foundIndex = restText.indexOf(soughtWord);

                try {
                    if (restText.length() > foundIndex + largoDeExtracto) {
                        soughtWords.add(restText.substring(foundIndex - largoDeExtracto, foundIndex + largoDeExtracto));
                    } else { //se achica el extracto pk se termino el documento
                        largoDeExtracto = 10;
                        soughtWords.add(restText.substring(foundIndex - largoDeExtracto, foundIndex + largoDeExtracto));
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
                int textLength = restText.length();
                restText = restText.substring(foundIndex + soughtWord.length(), textLength);
                foundWords++;
                if (contadorMaximaIteracion == 30) break;
            }
            System.out.println("palabras encontradas: " + foundWords);

              }

    }

    public ArrayList<String> getSoughtWords() {
        return soughtWords;
    }
}

