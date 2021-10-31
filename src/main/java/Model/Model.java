package Model;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class Model {

    ArrayList<TableObject> data = new ArrayList<>();
    ArrayList<String> allFilesPaths = new ArrayList<>();
    private Boolean returnStatement = false;
    directoriePathFinder mDirectoriePathFinder = new directoriePathFinder();
    ArrayList<String> allDirectories = new ArrayList<>();

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

    public void searchingInDocument(String textToFind, ArrayList<String> filesToSearch) throws IOException, InvalidFormatException {

        //sin lo siguiente el campo "  " no lo reconoce como vacio o empthy
        // textToFind = textToFind.replaceAll("\\s+", "");
        data.clear();
        wordSeekedInPdf doc2 = null;
        wordSeekerInWord doc3 = null;

        if (!textToFind.isEmpty()) {

            for (int j = 0; j < filesToSearch.size(); j++) {

                String extension = filesToSearch.get(j).length() > 5 ? filesToSearch.get(j).substring(filesToSearch.get(j).length()-3) : "";
                //  if (extension.equals("pdf")) doc2 = new wordSeekedInPdf(textToFind, filesToSearch.get(j));
                //else
                if (extension.equals("doc")) doc3 = new wordSeekerInWord(textToFind, filesToSearch.get(j));
            }
            //se pega todo lo de pdf primero
          /*  for (int i = 0; i < doc2.getSoughtWords().size(); i++) {
                data.add((new TableObject(i + "", doc2.getNameOfFileWordFound().get(i), doc2.getSoughtWords().get(i), doc2.getPagesOfTextFound().get(i))));
            }*/
            //se pega todo lo de word despues
            for (int i = 0; i < doc3.getSoughtWords().size(); i++) {
                data.add((new TableObject(i + "", doc3.getNameOfFileWordFound().get(i), doc3.getSoughtWords().get(i), doc3.getPagesOfTextFound().get(i))));
            }

        }
    }

    /*public void searchinAllDirectories(String path) {

        searchInSingleDirectory(path);

        File file = new File(path);
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                if (new File(current, name).isDirectory()) {
                    String text = new File(current, name).toString();
                    if (new File(current, name).toString().substring(text.length() - 5, text.length()).equals(".idea")) {
                        returnStatement = false;
                    } else if (new File(current, name).toString().substring(text.length() - 4, text.length()).equals(".git"))
                        returnStatement = false;
                    else
                        returnStatement = true;
                } else if (new File(current, name).isHidden()) { //elimina las carpetas invisibles
                    returnStatement = false;
                }
                return returnStatement;
            }
        });

        ArrayList<String> subpaths = new ArrayList();

        for (String text : directories) {
            subpaths.add(text);
            System.out.println("subpaths: " + text);
        }

        File f = new File(path);
        String[] pathnames;
        pathnames = f.list();
        if (pathnames != null) {
            for (String names : pathnames) {
                int wordLength = names.length();

                String extention = wordLength >= 3 ? names.substring(wordLength - 3, wordLength) : "";

                // String fileCompletePath = path + "\\" + subpaths.get(i) + "\\" + names;
                String partialPath = path + "\\" + names;
                //    System.out.println("complete path: " + fileCompletePath);
                System.out.println("partial path: " + path + "\\" + names);

                if (extention.equals("pdf"))  //solo se agregan archivos word
                {
                    //      System.out.println("se agrego: " + fileCompletePath);
                    //if (!allFilesPaths.contains(fileCompletePath)) allFilesPaths.add(fileCompletePath);
                    if (!allFilesPaths.contains(partialPath)) allFilesPaths.add(partialPath);
                }
            }
        }
        //  }
    }*/

   /*public void searchInSingleDirectory(String path) {

        //solo archivos word por ahora:
        File f = new File(path);
        String[] pathnames;
        // Populates the array with names of files and directories
        pathnames = f.list();

        // For each pathname in the pathnames array
        for (String pathname : pathnames) {

            // Print the names of files and directories
            int wordLength = pathname.length();

            String extention = wordLength >= 3 ? pathname.substring(wordLength - 3, wordLength) : "";

            if (extention.equals("pdf"))  //solo se agregan archivos word
                if (!allFilesPaths.contains(path + "\\" + pathname)) {
                    allFilesPaths.add(path + "\\" + pathname);
                }
        }
    }*/

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

    public void saveNewCurrentDirectory(String currentDirectory) throws IOException {

        // create and load default properties
        Properties defaultProps = new Properties();

        defaultProps.setProperty("workingDirectory", currentDirectory);
        defaultProps.store(new FileWriter("custom.properties"), "no comment");

    }

    public void clearData() {
        data.clear();
    }

    public int getIndexOfRowClicked(JTable jTable) {
        return Integer.valueOf(String.valueOf(jTable.getValueAt(jTable.getSelectedRow(), 0)));
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

                    if (!allFilesPaths.contains(temp.toString()) && (extention.equals("pdf") || extention.equals("doc"))) {
                        System.out.println("se agrego archivo: " + temp.toString());
                        allFilesPaths.add(temp.toString());
                    }
                }
            }
        }

    }

    public ArrayList<String> getAllDirectories() {
        return allDirectories;
    }
}
