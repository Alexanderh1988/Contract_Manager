package old;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class elements {

    final static boolean shouldFill = false;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    GridBagConstraints c;

    public elements() {
        super();

    }

    public void addButton(Container pane, String buttonName) {

        JButton button = new JButton(buttonName);

        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.CENTER;
        c.insets = new Insets(15, 15, 15, 15);
        c.ipadx = 30;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(15, 15, 15, 15);
        pane.add(button, c);
    }

    public void addTextArea(Container pane, Integer column) {

        c.fill = GridBagConstraints.HORIZONTAL;
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
        textArea3.setEditable(false);
        pane.add(areaPane, c);

    }

}
