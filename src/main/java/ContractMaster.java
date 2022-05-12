import Model.Model;
import View.View;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;


public class ContractMaster {

  //  View mView1, mView2;
    //Model mModel1,mModel2;


    public static void main(String[] args) {

       // ContractMain ct1 = new ContractMain();

      //  ct1.start();

        new Controller(new View(),new Model());
        //   new Controller(new View(), new Model());
        //Model model = new Model();
        //View view = new View();
        //  ControllerSample cntr = new ControllerSample().calculate(model1,view1);
        // ControllerSample cntr = new ControllerSample().calculate();
        //Controller mController = new Controller(new View());
        // ControllerSample cntr = new ControllerSample();


        //    new Controller(new View(), new Model());
        // new Controller(new View(), new Model());
        // create an instance of Properties


    }

}

class ContractMain extends Thread {

       public void run() {
        System.out.println("Executing task 2");
        //  new Controller(new View(), new Model());

        new Controller(new View(),new Model());

        //new Controller(new View(), new Model());
    }
}




