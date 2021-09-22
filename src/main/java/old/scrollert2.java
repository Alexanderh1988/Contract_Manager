package old;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class scrollert2 extends JFrame {

    // dobu :   https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html

    final static boolean shouldFill = false;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    public static void addComponentsToPane(Container pane) {

        pane.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        int row = 1;
        c.gridy = row;
        c.gridx = 0;

        JScrollPane scrollPane = new JScrollPane();

        for (int column = 1; column <= 4; column++) {

            c.gridx = 0;
            c.gridy = column;
            c.ipadx = 0;
            c.ipady = 0;
            c.gridwidth = 2;
            c.weightx = 1;

            JTextArea textArea3 = new JTextArea(10,30);

            //Border border = BorderFactory.createLineBorder(Color.BLACK);

            //textArea3.setBorder(BorderFactory.createCompoundBorder(border,
              //      BorderFactory.createEmptyBorder(10, 10, 10, 10)));

            JPanel areaPane = new JPanel();
            areaPane.add(textArea3, c);
            textArea3.setEditable(false);

            pane.add(areaPane, c);
            pane.add(scrollPane,c);

        }
        pane.add(scrollPane);



    }

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
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
