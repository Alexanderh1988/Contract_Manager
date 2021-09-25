package Model;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class Model {

    ArrayList<String> filesNames;
    ArrayList<TableObject> data;
    String textToSearch;
    ArrayList<String> allFilesPaths = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Model();
    }

    public Model() throws IOException {


        // ListadoDirectorio directorios = new ListadoDirectorio();

        //  System.out.println("directorios:    " + directorios.getPaths());

        searchFilesPaths(readCurrentDirectory());

        //  System.out.println(readCurrentDirectory());

        //saveNewCurrentDirectory("C:\\Users\\Alex Hs\\Desktop\\Code Composer");

        data = new ArrayList<>();

     /*   data.add((new TableObject("342", "231", "231")));
        data.add((new TableObject("342", "231", "231")));
        data.add((new TableObject("342", "231", "231 as as sa fsa fa af fsdsafd a fa")));
        data.add((new TableObject("342", "231sadf  fa asdf sdaf sa dfsad fsad fa sdf ", "231")));
        data.add((new TableObject("342", "231", "231as as af fa adf dfasd fsadf asf sa dsf asf as")));*/

       /*        String actualDirectory = System.getProperty("user.dir");

        //new ListadoDirectorio(actualDirectory);

        filesNames = new ListadoDirectorio(actualDirectory).getPaths();

        for (String a : filesNames) {
            System.out.println(a);
        }*/

    }

    public ArrayList<TableObject> getData() {
        return data;
    }

    public void setData(ArrayList<TableObject> data) {
        this.data = data;
    }

    public void searchingInDocument(String textToFind, ArrayList<String> filesToSearch) throws IOException {

        System.out.println("searchingInDocument");
        //sin lo siguiente el campo "  " no lo reconoce como vacio o empthy
        textToFind = textToFind.replaceAll("\\s+", "");

        data.clear();
        if (!textToFind.isEmpty()) {

            for (int j = 0; j < filesToSearch.size(); j++) {
                //wordSeekerInWord doc1 = new wordSeekerInWord(textToFind, "D:\\Software_hstech\\Contract_Manager\\articles-97403_Teatro.docx");
                wordSeekerInWord doc1 = new wordSeekerInWord(textToFind, filesToSearch.get(j));

                for (int i = 0; i < doc1.getSoughtWords().size(); i++) {
                    System.out.println(doc1.getSoughtWords().get(i));
                    data.add((new TableObject(i + "", "name " + i, doc1.getSoughtWords().get(i))));
                }
            }
        }
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

    public void searchFilesPaths(String path) {

        //solo archivos word por ahora:
        File f = new File(path);
        String[] pathnames;
        // Populates the array with names of files and directories
        pathnames = f.list();

        // For each pathname in the pathnames array
        for (String pathname : pathnames) {

            // Print the names of files and directories
            int wordLength = pathname.length();

            String extention = wordLength >= 4 ? pathname.substring(wordLength - 4, wordLength) : "";

            if (extention.equals("docx"))  //solo se agregan archivos word
                allFilesPaths.add(pathname);
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

        }
        return pathName;
    }

    public ArrayList<String> getAllFilesPaths() {
        return allFilesPaths;
    }

    public void saveNewCurrentDirectory(String currentDirectory) throws IOException {

        // create and load default properties
        Properties defaultProps = new Properties();

        defaultProps.setProperty("workingDirectory", currentDirectory);
        defaultProps.store(new FileWriter("custom.properties"), "no comment");

    }
}
