package View;

import Model.TableObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class View extends JFrame {

    //static ArrayList<TableObject> data;
    //public static GridBagConstraints constraints;
    //public static GridBagLayout gridType;
    public static  addComponents component = new addComponents();

    public static void main(String[] args) {

        //  addComponents addComponents = null;
        new View();

    }

    public View() {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                createAndShowGUI();

                //   timer = new Timer();
                // old.Helper class extends TimerTask
                //   TimerTask task = new old.TableConJareaIncorporado.Helper2();
                //  timer.schedule(task, 1000, 3000);

            }
        });

    }

    private void createAndShowGUI() {

        JFrame frame = new JFrame("Contract Master");
        frame.setSize(new Dimension(650, 600));
        frame.setMinimumSize(new Dimension(400, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = new Container();

        frame.add(pane);

        frame.add(component.getComponentsContainer());

        component.setContentPane(pane);

        frame.setResizable(true);
        // frame.pack();
        frame.setVisible(true);
    }

    public void setListenerOnSeekButton(ActionListener mListener) {
        component.setListenerOnSearchButton(mListener);
    }

    public void setListenerOnLocationButton(ActionListener mListener) {
        component.setListenerOnChangeLocation(mListener);
    }

    public void addNewRow(ArrayList<TableObject> data) {
        component.addColumnToJtable(data);
    }

    public String getTextFieldFromComponents() {
        return component.getTextField().getText();
    }

    public void noWordsFound() {
        JOptionPane.showMessageDialog(null, "No se encontró nada ");
    }

}
