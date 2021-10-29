import Model.Model;
import View.View;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;


public class ContractMaster {

    public static void main(String[] args) throws IOException {


        //Model model = new Model();
        //View view = new View();
        //  ControllerSample cntr = new ControllerSample().calculate(model1,view1);
        // ControllerSample cntr = new ControllerSample().calculate();
        //Controller mController = new Controller(new View());
        // ControllerSample cntr = new ControllerSample();

        new Controller(new View(), new Model());
        // create an instance of Properties


    }

}


