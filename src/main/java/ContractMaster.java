import Model.Model;
import View.View;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;


public class ContractMaster extends Thread {


    public static void main(String[] args) {

        ContractMaster mainBuscador = new ContractMaster();
        ContractMaster buscadorAdicional = new ContractMaster();
        ContractMaster buscadorAdiciona2 = new ContractMaster();
       // ContractMaster buscadorAdiciona3 = new ContractMaster();
        mainBuscador.start();
        buscadorAdicional.start();
         buscadorAdiciona2.start();
      //  buscadorAdiciona3.start();

    }

    public void run() {

        SwingUtilities.invokeLater(() -> new Controller(new View(), new Model()));

    }

}




