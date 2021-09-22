package old;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class timertask extends JFrame {

    // dobu :   https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html

    public static JFrame frame;
    public static Integer i=0;

    public static class Helper extends TimerTask {

         public void run() {
             addComponentsToPane(frame.getContentPane(),i);
             System.out.println("hola");
             i=i+1;
        }
    }

    public static void addComponentsToPane(Container pane, Integer index) {

        pane.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        int row = 1;
        c.gridy = row;
        c.gridx = 0;

        JScrollPane scrollPane = new JScrollPane();

     //   for (int column = 1; column <= 4; column++) {

            c.gridx = 0;
            c.gridy = index;
            c.ipadx = 0;
            c.ipady = 0;
            c.gridwidth = 2;
            c.weightx = 1;

            JTextArea textArea3 = new JTextArea(10, 30);

            JPanel areaPane = new JPanel();
            areaPane.add(textArea3, c);
            textArea3.setEditable(false);

            pane.add(areaPane, c);
           // pane.add(scrollPane, c);

      //  }
       // pane.add(scrollPane);

    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("GridBagLayoutDemo");
        frame.setSize(new Dimension(400, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        java.util.Timer timer = new Timer();
        // old.Helper class extends TimerTask
        TimerTask task = new Helper();

        timer.schedule(task, 1000, 4000);

        //Set up the content pane.
        //addComponentsToPane(frame.getContentPane());

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


    public static class Helper1 extends TimerTask {
        @Override
        public void run() {

        }
    }
}
