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

    JMenuBar menuBar;
    JMenu menu, submenu;
    JMenuItem menuItem;
    JMenuItem menuItem1;

    public JMenu1() {

        JRadioButtonMenuItem rdmi;
        JCheckBoxMenuItem cbmi;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the File Menu.
        menu = new JMenu("Buscador");
        //   menu.setMnemonic(KeyEvent.VK_F);
        menu.getAccessibleContext().setAccessibleDescription("Dealing with Files");
        menuBar.add(menu);

        ImageIcon Icon = new ImageIcon("");

        //a group of JMenuItems
          menuItem1 = new JMenuItem("Nuevo proyecto...", new ImageIcon("C:\\Users\\Alex Hs\\Desktop\\chile.jpg"));
        menuItem = new JMenuItem("Nuevo proyecto...", new ImageIcon("C:\\Users\\Alex Hs\\Desktop\\chile.jpg"));

        //  menuItem.setMnemonic(KeyEvent.VK_P);
        //  menuItem.setAccelerator(KeyStroke.getKeyStroke(                KeyEvent.VK_1, ActionEvent.ALT_MASK));

        menuItem.getAccessibleContext().setAccessibleDescription("Nuevo proyecto");
        //menu.add(menuItem);
        menu.add(menuItem1);

        menuItem = new JMenuItem("Nuevo Archivo...", new ImageIcon("C:\\Users\\Alex Hs\\Desktop\\chile.jpg"));
        menuItem.setMnemonic(KeyEvent.VK_F);
        menu.add(menuItem);

        //a group of check box menu items
        menu.addSeparator();
        cbmi = new JCheckBoxMenuItem("Check 1 habilitado");


        cbmi.setMnemonic(KeyEvent.VK_C);
        menu.add(cbmi);

        cbmi = new JCheckBoxMenuItem("Check 2 habilitado");
        // cbmi.setMnemonic(KeyEvent.VK_H);
        menu.add(cbmi);

        //a group of radio button menu items
        menu.addSeparator();
        ButtonGroup group = new ButtonGroup();
        rdmi = new JRadioButtonMenuItem("Radio 1");
        rdmi.setSelected(true);
        //   rdmi.setMnemonic(KeyEvent.VK_R);
        group.add(rdmi);
        menu.add(rdmi);

        rdmi = new JRadioButtonMenuItem("Radio 2");
        //   rdmi.setMnemonic(KeyEvent.VK_O);
        group.add(rdmi);
        menu.add(rdmi);

        //a submenu
        menu.addSeparator();
        submenu = new JMenu("Categorias");
        //   submenu.setMnemonic(KeyEvent.VK_S);

        menuItem = new JMenuItem("Subcategorias 1");

        //menuItem.setAccelerator(KeyStroke.getKeyStroke(                KeyEvent.VK_2, ActionEvent.ALT_MASK));

        submenu.add(menuItem);

        menuItem = new JMenuItem("Subcategorias 2");
        submenu.add(menuItem);
        menu.add(submenu);

        //Build Edit menu in the menu bar.
        menu = new JMenu("Checkeo");
        //  menu.setMnemonic(KeyEvent.VK_E);        menu.getAccessibleContext().setAccessibleDescription(        "Edit Menu");
        menuBar.add(menu);

        menu = new JMenu("Actualizar Documento");
        menuBar.add(menu);

        menu = new JMenu("Mostrar consola");

        submenu = new JMenu("Mostrar pantlla");
        menu.add(submenu);
        menuBar.add(menu);

    }


    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public void setItem1Click(ActionListener Listener) {
        menuItem1.addActionListener(Listener);
       /* menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getSource());
                System.out.println("click " + e);

            }
        });
*/
    }

    public JMenuItem getMenuItem1() {
        return menuItem1;
    }
}
