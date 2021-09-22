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
        frame.setSize(new Dimension(600, 600));
        frame.setMinimumSize(new Dimension(400,600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = new Container();
        // gridType = new GridBagLayout();
        //constraints = new GridBagConstraints();
        frame.add(pane);

        //new addComponents().getSeekbutton().addActionListener(this);

        addComponents component = new addComponents();

        frame.add(component.getComponentsContainer());

        component.setContentPane(pane);

        frame.setResizable(true);
        // frame.pack();
        frame.setVisible(true);
        //this.addComponents = addComponents;
        //  component.setOnClickListener(new CalculationListener1());

        //  component.setOnClickListener(new CalculationListener1());

        //  JButton seekerView = component.getSeekbutton();
        //  seekerView.addActionListener(new CalculationListener1());


    }

    public void setListenerOnButton(ActionListener mListener) {
        new addComponents().setListenerOnAddComponents(mListener);
    }


}
