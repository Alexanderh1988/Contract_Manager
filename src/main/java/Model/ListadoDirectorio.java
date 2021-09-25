package Model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class ListadoDirectorio {

    ArrayList<String> paths = new ArrayList<>();

    public static void main(String[] args) {

        try {
            new ListadoDirectorio();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //  public ListadoDirectorio(String path) {
    public ListadoDirectorio() throws IOException {

        Properties p = new Properties();
        String path = null;

        try {
            p.load(new FileReader("custom.properties"));
            System.out.println(p.getProperty("workingDirectory"));
            path = p.getProperty("workingDirectory");
        } catch (Exception e) {

        }

        // add properties to it
     /*   p.setProperty("name", "Ganesh Chowdhary Sadanala");
        p.setProperty("email", "ganeshs.gfg@gmail.com");
        p.store(new FileWriter("custom.properties"), "");*/

        //https://stackabuse.com/java-list-files-in-a-directory/


        String[] pathnames;
        //  String path = "D:\\Software_hstech\\Contract_Manager";

        // Creates a new File instance by converting the given pathname string
        // into an abstract pathname
        //File f = new File("D:\\Software_hstech\\Contract_Manager");

        File f = new File(path);

        // Populates the array with names of files and directories
        pathnames = f.list();

        // For each pathname in the pathnames array
        for (String pathname : pathnames) {
            // Print the names of files and directories
            System.out.println(pathname);
            paths.add(pathname);
        }

    }

    public ArrayList<String> getPaths() {
        return paths;
    }
}
