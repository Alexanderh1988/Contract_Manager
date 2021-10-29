package Model;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class wordSeekedInPdf {

    //public static  String filePath1 ="C:\\Users\\Alex Hs\\Desktop\\Contratos2\\articles-97403_casa_particular_puertas_afuera.pdf";
    private String docText;
    private ArrayList<String> soughtWords = new ArrayList<>();
    private ArrayList<Integer> pagesOfTextFound = new ArrayList<>();
    private ArrayList<String> nameOfFileWordFound = new ArrayList<>();
    boolean DocuEncrypted;
    boolean thereIsABigFile;
    boolean wordFound;

    public static void main(String[] args) throws IOException {
    }

    public wordSeekedInPdf(String soughtWord, String fileName) throws IOException {

        wordFound = false;

        System.out.println("fileName: " + fileName);

        try (PDDocument document = PDDocument.load(new File(fileName), MemoryUsageSetting.setupTempFileOnly())) {
            // try (PDDocument document = PDDocument.load(new File(fileName))) {

            document.getClass();

            System.out.println("document.getNumberOfPages(): " + document.getNumberOfPages());
            System.out.println("pagina 4: " + document.getPages());
            //  System.out.println("document.getDocumentId(): " + document.getDocumentId()); 0
            //     if (document.getPages().getCount() > 350) thereIsABigFile = true;

            if (!document.isEncrypted()) {

                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);

                PDFTextStripper tStripper = new PDFTextStripper();

         /*       for (int p = 1; p <= document.getNumberOfPages(); ++p) {
                    tStripper.setStartPage(p);
                    tStripper.setEndPage(p);
                }*/

                // loop through a range of pages

                //for (int i = 1; i < document.getNumberOfPages(); i++) {
                for (int i = 0; i < document.getNumberOfPages(); i++) {

                    tStripper.setStartPage(i);
                    tStripper.setEndPage(i);
                    String docText = tStripper.getText(document);

                  //  String docTextAux = docText.replaceAll("\\s+", "_");
                  //  String soughtWordAux = soughtWord.replaceAll("\\s+", "_");

                    //    int foundIndex = docText.toLowerCase().indexOf(soughtWord.toLowerCase());

                    int foundIndex = docText.toLowerCase().indexOf(soughtWord.toLowerCase());


                    String restText = docText;
                    int largoDeExtracto;
                    largoDeExtracto = 400;

                    while (foundIndex != -1) {
                        //  contadorMaximaIteracion++;

                        if (restText.length() > foundIndex + largoDeExtracto && foundIndex > largoDeExtracto) {
                            soughtWords.add(restText.substring(foundIndex - largoDeExtracto, foundIndex + largoDeExtracto));
                            pagesOfTextFound.add(i);
                            nameOfFileWordFound.add(fileName);
                            wordFound = true;

                            int textLength = restText.length();
                            restText = restText.substring(foundIndex + soughtWord.length(), textLength);
                            foundIndex = restText.indexOf(soughtWord);
                        } else {
                            largoDeExtracto = largoDeExtracto - 20;
                        }
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "Documento encriptado ");
                DocuEncrypted = true;
            }
        }

        if (!wordFound) JOptionPane.showMessageDialog(null, "No se encontro palabras en " + fileName);
    }

    public ArrayList<String> getSoughtWords() {
        return soughtWords;
    }

    public ArrayList<Integer> getPagesOfTextFound() {
        return pagesOfTextFound;
    }

    public boolean isThereIsABigFile() {
        return thereIsABigFile;
    }

    public ArrayList<String> getNameOfFileWordFound() {
        return nameOfFileWordFound;
    }
}