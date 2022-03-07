package Model;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

public class Model {

    ArrayList<TableObject> data = new ArrayList<>();
    ArrayList<String> allFilesPaths = new ArrayList<>();
    private Boolean returnStatement = false;
    directoriePathFinder mDirectoriePathFinder = new directoriePathFinder();
    ArrayList<String> allDirectories = new ArrayList<>();
    ArrayList<String> textToFind;

    public static void main(String[] args) {
        new Model();
    }

    public ArrayList<TableObject> getData() {
        return data;
    }

    public String changeFolderLocation() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.showOpenDialog(null);

        System.out.println(fileChooser.getSelectedFile());

        return fileChooser.getSelectedFile().toString();
    }

    public void searchingInDocument(ArrayList<String> filesToSearch) throws IOException, InvalidFormatException {

        //sin lo siguiente el campo "  " no lo reconoce como vacio o empthy
        // textToFind = textToFind.replaceAll("\\s+", "");

        textToFind = new ArrayList<>(Arrays.asList(
                "Notificar",
                "avisar",
                "cumplimiento",
                "cumplir",
                "debe",
                "entregar",
                "exige",
                "tener",
                "tomar",
                "acciones",
                "aprobado",
                "aceptado",
                "requerido",
                "permitido",
                "prohibido",
                "cargo",
                "costa",
                "costo",
                "cuenta",
                "responsabilidad",
                "pronunciarse",
                "rechazo",
                "prejuicios",
                "pagar",
                "garantías",
                "por escrito",
                "permitir",
                "detener",
                "modificar",
                "variar",
                "analizar",
                "aclarar",
                "revisar",
                "demorar",
                "gestionar",
                "negociar",
                "aprobar"
        ));

        data.clear();
        wordSeekedInPdf doc2 = new wordSeekedInPdf(textToFind, filesToSearch);
        //  wordSeekerInWord doc3 = null;

        if (!textToFind.isEmpty()) {

          /*  for (int j = 0; j < filesToSearch.size(); j++) {
                for (int i = 0; i < textToFind.size(); i++) {

                    //    String extension = filesToSearch.get(j).length() > 5 ? filesToSearch.get(j).substring(filesToSearch.get(j).length() - 3) : "";
                    //  if (extension.equals("pdf"))
                    doc2 = new wordSeekedInPdf(textToFind.get(i), filesToSearch.get(j));
                    //else
                    //   if (extension.equals("doc")) doc3 = new wordSeekerInWord(textToFind, filesToSearch.get(j));
                }
            }*/

            try {
                for (int i = 0; i < doc2.getNameOfFileWordFound().size(); i++) {
                    //id, extracto encontrado, la palabra buscada, pagina
                    data.add((new TableObject(i + "", doc2.getNameOfFileWordFound().get(i), doc2.getSoughtWords().get(i), doc2.getKeyWord().get(i), doc2.getPagesOfTextFound().get(i))));
                    //   data.add((new TableObject(  "12", "name of file", doc2.getSoughtWords().get(i), 12)));
                }
            } catch (Exception e) {

            }

            //ordenar por pagina:
            int Minimo = 900000;
            Integer Index = 1;
            for (int j = 0; j < data.size(); j++) {
                for (int i = j; i < data.size(); i++) {
                    if (Minimo >= data.get(i).getPage()) {
                        Minimo = data.get(i).getPage();
                        Index = i;
                    }
                }
                Collections.swap(data, j, Index);
                data.get(j).setId(String.valueOf(j));

                Minimo = 900000;
            }


        }
    }

    public String readCurrentDirectory() {

        Properties p = new Properties();
        String pathName = null;

        try {
            p.load(new FileReader("custom.properties"));
            System.out.println(p.getProperty("workingDirectory"));
            pathName = p.getProperty("workingDirectory");
        } catch (Exception e) {
            System.out.println("error5 " + e);
        }
        return pathName;
    }

    public void saveNewCurrentDirectory(String currentDirectory) throws IOException {

        // create and load default properties
        Properties defaultProps = new Properties();

        defaultProps.setProperty("workingDirectory", currentDirectory);
        defaultProps.store(new FileWriter("custom.properties"), "no comment");
        System.out.println("Se grabo: " + currentDirectory);
    }

    public void clearData() {
        data.clear();
    }

    public int getIndexOfRowClicked(JTable jTable) {
        Integer rowIndex = Integer.valueOf(String.valueOf(jTable.getValueAt(jTable.getSelectedRow(), 0)));
        return rowIndex != -1 ? rowIndex : 0;
    }

    public void OpenWordFile(File fileClicked) throws IOException {

        Desktop.getDesktop().open(fileClicked);

    }

 /*   public boolean isOneOfTheFilesTooBig() {result = "C:\Users\Alex Hs\Desktop\3381\Importante\Anexos Licitación.pdf"
        return new wordSeekedInPdf().isThereIsABigFile();result = "C:\Users\Alex Hs\Desktop\3381\Importante\Anexos Licitación.pdf"
    }*/

    public void directoriesFinder(String path) {
        searchDirectory(new File((path)));
    }

    public void searchDirectory(File directory) {

        // setFileNameToSearch(fileNameToSearch);
        if (directory.isDirectory()) {
            search(directory);
        } else {
            System.out.println(directory.getAbsoluteFile() + " is not a directory!");
        }
    }

    private void search(File file) {

        if (file.isDirectory()) {
            System.out.println("Searching directory ... " + file.getAbsoluteFile());

            allDirectories.add(file.getAbsoluteFile().toString());

            //do you have permission to read this directory?
            if (file.canRead()) {
                for (File temp : file.listFiles()) {
                    if (temp.isDirectory()) {
                        search(temp);
                    }
                }
            } else {
                System.out.println(file.getAbsoluteFile() + "Permission Denied");
            }
        }

    }

    public ArrayList<String> getAllFilesPaths() {
        return allFilesPaths;
    }

    public void saveAllFiles(ArrayList<String> directories) {

        System.out.println("saveAllFiles");

        for (int i = 0; i < directories.size(); i++) {

            System.out.println(directories.get(i));

            File file = new File(directories.get(i));

            for (File temp : file.listFiles()) {
                if (!temp.isDirectory()) {
                    System.out.println(temp);

                    Integer FileNameLength = temp.toString().length();

                    String extention = FileNameLength >= 3 ? temp.toString().substring(FileNameLength - 3, FileNameLength) : "";

                    //       if (!allFilesPaths.contains(temp.toString()) && (extention.equals("pdf") || extention.equals("doc"))) {
                    if (!allFilesPaths.contains(temp.toString()) && (extention.equals("pdf"))) {
                        System.out.println("se agrego archivo: " + temp.toString());
                        allFilesPaths.add(temp.toString());
                    }
                }
                System.out.println("no es directorio");
            }
        }

    }

    public ArrayList<String> getAllDirectories() {
        return allDirectories;
    }

    public ArrayList<String> getTextToFind() {
        return textToFind;
    }

    public void setTextToFind(ArrayList<String> textToFind) {
        this.textToFind = textToFind;
    }
}
