package Model;

import java.io.File;
import java.util.ArrayList;

public class ListadoDirectorio {

    ArrayList<String> paths = new ArrayList<>();

    public static void main(String[] args) {

        // new ListadoDirectorio("D:\\Software_hstech\\Contract_Manager");
    }

    //  public ListadoDirectorio(String path) {
    public ListadoDirectorio() {

        //https://stackabuse.com/java-list-files-in-a-directory/

        String[] pathnames;
        String path = "D:\\Software_hstech\\Contract_Manager";

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
