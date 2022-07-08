package Model;

import View.View;
import com.sun.istack.NotNull;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

public class Model {

    Boolean checkstate = false;

    ArrayList<TableObject> data = new ArrayList<>();

    ArrayList<TableObject> data1;
    ArrayList<TableObject> data2;
    ArrayList<TableObject> data3;
    String actualAux1 = "", actualAux2 = "", actualAux3 = "", actualAux4 = "";

    ArrayList<String> allFilesPaths = new ArrayList<>();
    private Boolean returnStatement = false;
    directoriePathFinder mDirectoriePathFinder = new directoriePathFinder();
    ArrayList<String> allDirectories = new ArrayList<>();
    ArrayList<String> textToFindCheck = new ArrayList<>(Arrays.asList(
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

    public static void main(String[] args) {
        new Model();
    }

    public ArrayList<TableObject> getData() {
        return data;
    }

    @NotNull
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

        return fileChooser.getSelectedFile() != null ? fileChooser.getSelectedFile().toString() : null;
    }

    public void searchingInDocument(ArrayList<String> filesToSearch, String word1, String word2, Boolean check) throws IOException {

        //sin lo siguiente el campo "  " no lo reconoce como vacio o empthy
        // textToFind = textToFind.replaceAll("\\s+", "");

        System.out.println("searchingInDocument");

        ArrayList<String> wordsToCheck = new ArrayList<>();
        ArrayList<String> swapRule = new ArrayList<>();

        if (check.equals("check")) {  //chequeo estandar
            wordsToCheck = textToFindCheck;
        } else {   //palabra especifica a buscar
            wordsToCheck.add(word1);
            //if (!word2.equals(""))
            //swapRule.add(word2);
        }

        data.clear();
        wordSeekedInPdf doc2 = new wordSeekedInPdf(wordsToCheck, filesToSearch, check);

        if (!wordsToCheck.isEmpty()) {

            try {
                for (int i = 0; i < doc2.getNameOfFileWordFound().size(); i++) {
                    //id, extracto encontrado, la palabra buscada, pagina

                    data.add((new TableObject(i + "", doc2.getNameOfFileWordFound().get(i), doc2.getSoughtWords().get(i).replaceAll(doc2.getKeyWord().get(i), "<<<<" + doc2.getKeyWord().get(i) + ">>>>"), doc2.getKeyWord().get(i), doc2.getPagesOfTextFound().get(i))));
                    //   data.add((new TableObject(  "12", "name of file", doc2.getSoughtWords().get(i), 12)));
                    //row.getText().repla  ceAll(row.getKeyWord(), "<<<" + row.getKeyWord() + ">>>")
                }
            } catch (Exception e) {
            }

            Integer IndexSwap = 1;
            //ordenar por palabra adicional contenida:
            //if (swapRule.size() != 0)
            if (word2 != "")
                for (int j = 0; j < data.size(); j++) {
                    for (int i = 0; i < data.size(); i++) {
                        if (data.get(i).getText().contains(word2)) {
                            IndexSwap = i;
                        }
                    }
                    Collections.swap(data, j, IndexSwap);
                    data.get(j).setId(String.valueOf(j));
                }

            //ordenar por pagina:
            if (false) {        //deje el de arriba, mas importante ponderar por palabra buscada que por pagina
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
    }

    public String readCurrentDirectory(View mView) {

        boolean dir1 = mView.getmFirstComponentsPane().getDir1().isSelected();
        boolean dir2 = mView.getmFirstComponentsPane().getDir2().isSelected();
        boolean dir3 = mView.getmFirstComponentsPane().getDir3().isSelected();
        boolean dir4 = mView.getmFirstComponentsPane().getDir4().isSelected();

        Properties p = new Properties();
        String pathName = null;

        try {
            p.load(new FileReader("custom.properties"));
            //   System.out.println(p.getProperty("workingDirectory"));
            if (dir1) pathName = p.getProperty("workingDirectory1");
            if (dir2) pathName = p.getProperty("workingDirectory2");
            if (dir3) pathName = p.getProperty("workingDirectory3");
            if (dir4) pathName = p.getProperty("workingDirectory4");
        } catch (Exception e) {
            System.out.println("error5 " + e);
        }
        return pathName;
    }

    public void saveNewCurrentDirectory(String currentDirectory, View mView) throws IOException {

        // create and load default properties
        if (currentDirectory != null) {

            Properties defaultProps = new Properties();

            boolean dir1 = mView.getmFirstComponentsPane().getDir1().isSelected();
            boolean dir2 = mView.getmFirstComponentsPane().getDir2().isSelected();
            boolean dir3 = mView.getmFirstComponentsPane().getDir3().isSelected();
            boolean dir4 = mView.getmFirstComponentsPane().getDir4().isSelected();

            defaultProps.load(new FileReader("custom.properties"));

            defaultProps.setProperty("workingDirectory1", dir1 ? currentDirectory : defaultProps.getProperty("workingDirectory1"));
            defaultProps.setProperty("workingDirectory2", dir2 ? currentDirectory : defaultProps.getProperty("workingDirectory2"));
            defaultProps.setProperty("workingDirectory3", dir3 ? currentDirectory : defaultProps.getProperty("workingDirectory3"));
            defaultProps.setProperty("workingDirectory4", dir4 ? currentDirectory : defaultProps.getProperty("workingDirectory4"));

            defaultProps.store(new FileWriter("custom.properties"), "no comment");
            System.out.println("Se grabo: " + currentDirectory);

        }
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
            //    System.out.println(directory.getAbsoluteFile() + " is not a directory!");
        }
    }

    private void search(File file) {

        if (file.isDirectory()) {
            //      System.out.println("Searching directory ... " + file.getAbsoluteFile());

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
            try {
                for (File temp : file.listFiles()) {
                    if (!temp.isDirectory()) {
                        System.out.println(temp);

                        Integer FileNameLength = temp.toString().length();

                        String extention = FileNameLength >= 3 ? temp.toString().substring(FileNameLength - 3, FileNameLength) : "";

                        //       if (!allFilesPaths.contains(temp.toString()) && (extention.equals("pdf") || extention.equals("doc"))) {
                        if (!allFilesPaths.contains(temp.toString()) && (extention.equals("pdf"))) {
                            //    System.out.println("se agrego archivo: " + temp.toString());
                            allFilesPaths.add(temp.toString());
                        }
                    }
                    //  System.out.println("no es directorio");
                }

            } catch (Exception e) {
            }
        }

    }


    public ArrayList<String> getAllDirectories() {
        return allDirectories;
    }

    public String setSynonym(int i, String textToSearch) {

        switch (i) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }

        return "asd";
    }

    public ArrayList<TableObject> getData1() {
        return data1;
    }

    public ArrayList<TableObject> getData2() {
        return data2;
    }

    public ArrayList<TableObject> getData3() {
        return data3;
    }
}

