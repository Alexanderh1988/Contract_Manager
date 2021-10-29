import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainClass extends JFrame {

    // dobu :   https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html

    final static boolean shouldFill = false;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    public static void addComponentsToPane(Container pane) {

        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        JButton button;
        JTextField textField;
        pane.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        textField = new JTextField("escribia para buscar");
        if (shouldWeightX) {
            c.weightx = 0.5;
        }

        c.fill = GridBagConstraints.CENTER;
        c.insets = new Insets(15, 15, 15, 15);
        c.ipadx = 30;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(15, 15, 15, 15);
        pane.add(textField, c);

        if (shouldFill) {
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        button = new JButton("Buscar");
        //c.fill = GridBagConstraints.HORIZONTAL;
        c.fill = GridBagConstraints.EAST;
        c.ipadx = 50;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10);
        pane.add(button, c);

   /*       for (int i = 0; i < 4; i++) {

        c.fill=GridBagConstraints.NONE;

        c.gridx = 0;
        //    c.gridy=1;
        c.gridwidth = 10;
        c.insets = new Insets(5, 5, 5, 5);

        c.gridheight = 20;
        c.gridwidth = 10;
            c.gridy = i;
            pane.add(textArea, c);
        }*/

        int row = 1;
        c.gridy = row;
        c.gridx = 0;

             for (int column = 1; column <= 4; column++) {

            //new old.elements().addTextArea(pane, column);

            c.gridx = 0;
            c.gridy = column;
            c.ipadx = 0;
            c.ipady = 0;
            c.gridwidth = 2;
            c.weightx = 1;

            JTextArea textArea3 = new JTextArea(3, 20);

            Border border = BorderFactory.createLineBorder(Color.BLACK);

            textArea3.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));

            JPanel areaPane = new JPanel();
            areaPane.add(textArea3, c);
            textArea3.setEditable(false);
            pane.add(areaPane, c);
/*            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = column;
            c.ipadx = 0;
            c.ipady = 0;
            c.gridwidth = 2;
            c.weightx = 1;

            JTextArea textArea3 = new JTextArea(3, 20);

            Border border = BorderFactory.createLineBorder(Color.BLACK);

            textArea3.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));

            JPanel areaPane = new JPanel();
            areaPane.add(textArea3);
            textArea.setEditable(false);
            pane.add(areaPane, c);
            scrollPane.add(areaPane, c);*/

        }


        //pane.add(scrollPane);
    }
    //   }

/*
    button = new JButton("Button 3");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.5;
    c.gridx = 3;
    c.gridy = 0;
    pane.add(button, c);

    button = new JButton("Long-Named Button 4");
    //c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 40;      //make this component tall
    c.weightx = 0.0;
    c.gridwidth = 3;
    c.gridx = 0;
    c.gridy = 1;
    pane.add(button, c);
*/

   /* button = new JButton("5");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 0;       //reset to default
    c.weighty = 1.0;   //request any extra vertical space
    c.anchor = GridBagConstraints.PAGE_END; //bottom of space
    c.insets = new Insets(10,0,0,0);  //top padding
    c.gridx = 1;       //aligned with button 2
    c.gridwidth = 2;   //2 columns wide
    c.gridy = 2;       //third row
    pane.add(button, c);*/


    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("GridBagLayoutDemo");
        frame.setSize(new Dimension(400, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
  /*      javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });*/

        System.out.println("holaa world");

    }
}
