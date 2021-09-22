package Model;

import java.util.ArrayList;

public class Model {

    ArrayList<String> filesNames;

    public Model() {

        String actualDirectory = System.getProperty("user.dir");

        //new ListadoDirectorio(actualDirectory);

        filesNames = new ListadoDirectorio(actualDirectory).getPaths();

        for (String a : filesNames) {
            System.out.println(a);
        }

    }
}
