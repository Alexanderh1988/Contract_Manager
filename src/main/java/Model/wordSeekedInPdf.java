package Model;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;

public class wordSeekedInPdf {

    //public static  String filePath1 ="C:\\Users\\Alex Hs\\Desktop\\Contratos2\\articles-97403_casa_particular_puertas_afuera.pdf";
    private ArrayList<String> soughtWords = new ArrayList<>();
    private ArrayList<String> KeyWord = new ArrayList<>();
    private ArrayList<Integer> pagesOfTextFound = new ArrayList<>();
    private ArrayList<String> nameOfFileWordFound = new ArrayList<>();
    boolean DocuEncrypted;
    boolean thereIsABigFile;
    boolean wordFound;

    public static void main(String[] args) {
    }

    public wordSeekedInPdf(ArrayList<String> soughtWord, ArrayList<String> fileName, Boolean check) throws IOException {

        wordFound = false;

        for (int k = 0; k < soughtWord.size(); k++) {
            for (int j = 0; j < fileName.size(); j++) {

                //try (PDDocument document = PDDocument.load(new File(fileName.get(j)), MemoryUsageSetting.setupTempFileOnly())) {
                try (PDDocument document = PDDocument.load(new File(fileName.get(j)))) {
                    //  document.getClass();
                    // System.out.println("document.getNumberOfPages(): " + document.getNumberOfPages());
                    //  System.out.println("pagina 4: " + document.getPages());
                    //     if (document.getPages().getCount() > 350) thereIsABigFile = true;

                    if (!document.isEncrypted()) {

                        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                        stripper.setSortByPosition(true);

                        PDFTextStripper tStripper = new PDFTextStripper();

                        for (int i = 0; i < document.getNumberOfPages(); i++) {

                            tStripper.setStartPage(i);
                            tStripper.setEndPage(i);
                            String docText = tStripper.getText(document);

                            String decomposed = Normalizer.normalize(docText, Normalizer.Form.NFD);

                            // remove accents and diacritics
                            docText = decomposed.replaceAll("[^\\p{ASCII}]", "");

                            String restText = docText;
                            int largoDeExtracto = 450;
                            int foundIndex;

                    foundIndex = docText.toLowerCase().indexOf(soughtWord.get(k).toLowerCase());

                            while (foundIndex != -1 && foundIndex != 0) {
                                //  contadorMaximaIteracion++;

                                if (restText.length() > foundIndex + largoDeExtracto && foundIndex > largoDeExtracto) {
                                    //con esto se busca si esta la palabra completa
                                    // Integer index = restText.indexOf(foundIndex, foundIndex + soughtWord.length());

                                    Integer index = restText.substring(foundIndex, foundIndex + soughtWord.get(k).length()).toLowerCase().indexOf(soughtWord.get(k).toLowerCase());

                                    if (index != -1) {
                                        // System.out.println("soughtWords: " + restText.substring(foundIndex - largoDeExtracto, foundIndex + largoDeExtracto));
                                        soughtWords.add(restText.substring(foundIndex - largoDeExtracto, foundIndex + largoDeExtracto));
                                        pagesOfTextFound.add(i);
                                        KeyWord.add(soughtWord.get(k));
                                        nameOfFileWordFound.add(fileName.get(j));
                                        wordFound = true;

                                        int textLength = restText.length();
                                        restText = restText.substring(foundIndex + soughtWord.get(k).length(), textLength);
                                        foundIndex = restText.indexOf(soughtWord.get(k));
                                    }
                                } else {
                                    largoDeExtracto = largoDeExtracto - 20;
                                }
                            }
                        }
                    } else {
                        //  JOptionPane.showMessageDialog(null, "Documento encriptado " + fileName.get(j));
                        System.out.println("encriptado: " + fileName.get(j));
                        DocuEncrypted = true;
                    }
                } catch (Exception e) {
                    System.out.println("error 5 " + e);
                }

            }
        }
        // if (!wordFound) JOptionPane.showMessageDialog(null, "No se encontro palabras en " + fileName);
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

    public void setSoughtWords(ArrayList<String> soughtWords) {
        this.soughtWords = soughtWords;
    }

    public ArrayList<String> getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(ArrayList<String> keyWord) {
        KeyWord = keyWord;
    }
}