package Model;

import java.io.IOException;
import java.util.ArrayList;

public class Model {

    ArrayList<String> filesNames;
    ArrayList<TableObject> data;
    String textToSearch;

    public Model() throws IOException {


        ListadoDirectorio directorios = new ListadoDirectorio();

        System.out.println("directorios:    " + directorios.getPaths());

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

    public void searchingInDocument(String textToFind) throws IOException {

        //sin lo siguiente el campo "  " no lo reconoce como vacio o empthy
        textToFind = textToFind.replaceAll("\\s+", "");

        data.clear();
        if (!textToFind.isEmpty()) {
            wordSeekerInWord doc1 = new wordSeekerInWord(textToFind, "D:\\Software_hstech\\Contract_Manager\\articles-97403_Teatro.docx");

            for (int i = 0; i < doc1.getSoughtWords().size(); i++) {
                System.out.println(doc1.getSoughtWords().get(i));
                data.add((new TableObject(i + "", "name " + i, doc1.getSoughtWords().get(i))));
            }
        }
    }
}

