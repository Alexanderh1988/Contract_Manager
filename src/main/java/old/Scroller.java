package old;

import javax.swing.*;
import java.awt.*;

public class Scroller extends JFrame {

    public Scroller() throws HeadlessException {

        final JPanel panel = new JPanel();

        panel.setBorder(BorderFactory.createLineBorder(Color.red));

        panel.setPreferredSize(new Dimension(20, 40));

        for (int i = 0; i < 3; i++) {

            JScrollPane scroll = new JScrollPane(panel);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());
            add(scroll, BorderLayout.CENTER);
            setSize(10, 10);
            setVisible(true);
        }


    }

    public static void main(final String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Scroller().setVisible(true);
            }
        });
    }
}