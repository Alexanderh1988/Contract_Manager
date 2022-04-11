package View;

import javax.accessibility.AccessibleContext;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class JMenu1 {

    JMenu menu1, menu2;

  JMenuBar menuBar1, menuBar2;
    JMenuItem menuBuscador, menuChequeo;
    JMenuItem menuItem1, menuItem2;


    public JMenu1() {

        menu1=new JMenu("Buscador");
        menu2=new JMenu("Menu Actualizacion");
        menu1.setMnemonic(KeyEvent.VK_I);


        //Build the File Menu.
        menuBuscador = new JMenuItem("Buscar");
        menuChequeo = new JMenuItem("Chequear Contrato");

        menuBar1 = new JMenuBar();

        menu1.add(menuBuscador);
        menu1.add(menuChequeo);


        menuBar1.add(menu1);
        menuBar1.add(menu2);
        //   menu.setMnemonic(KeyEvent.VK_F);
//        menu.getAccessibleContext().setAccessibleDescription("Dealing with Files");
        // menuBar.add(menu);
        //  menuBar1.add(menuBuscador);
        //  menuBar1.add(menuChequeo);


        ImageIcon Icon = new ImageIcon("");

        //a group of JMenuItems
        menuItem1 = new JMenuItem("Nuevo proyecto...", new ImageIcon("C:\\Users\\Alex Hs\\Desktop\\chile.jpg"));
     //   menuItem2 = new JMenuItem("Nuevo proyecto...", new ImageIcon("C:\\Users\\Alex Hs\\Desktop\\chile.jpg"));

        //  menuItem.setMnemonic(KeyEvent.VK_P);
        //  menuItem.setAccelerator(KeyStroke.getKeyStroke(                KeyEvent.VK_1, ActionEvent.ALT_MASK));

        menuItem1.getAccessibleContext().setAccessibleDescription("Nuevo proyecto");
        //menu.add(menuItem);
        menu2.add(menuItem1);


    }


  //  public JMenuBar getMenuBar1() {
   //     return menuBar ;
  //  }

    public JMenuItem getMenuItem1() {
        return menuItem1;
    }

    public JMenuItem getMenuBuscador() {
        return menuBuscador;
    }

    public JMenuItem getMenuChequeo() {
        return menuChequeo;
    }

    public JMenuBar getMenuBar1() {
        return menuBar1;
    }
}
