package View;

import Model.TableObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import static View.View.constraints;
import static View.View.gridType;

public class addComponents extends JFrame {

    final static boolean shouldFill = false;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = true;
    Container componentsContainer;

    TextField textField;
    String SoughtWord;
    public static JButton Seekbutton;

    public static void main(String[] args) {
        new addComponents();
    }

    public addComponents() {

        createAndShowGUI();
        /*javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });*/

    }

    // dobu :   https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html

    public Container addComponentsToPane(Container pane) {

        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        //    button;
//        textField;
        //pane.setLayout(new GridBagLayout());
        pane.setLayout(gridType);

        if (shouldFill) {
            constraints.fill = GridBagConstraints.HORIZONTAL;
        }

         //c.fill = GridBagConstraints.HORIZONTAL;
        constraints.fill = GridBagConstraints.EAST;
        constraints.ipadx = 50;
        constraints.weightx = 0.5;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(10, 10, 10, 10);

        Seekbutton = new JButton("Buscar");
        pane.add(Seekbutton, constraints);

        textField = new TextField("Escriba");

        textField.setSize(new Dimension(100, 20));
        if (shouldWeightX) {
            constraints.weightx = 0.5;
        }

        constraints.fill = GridBagConstraints.CENTER;
        constraints.insets = new Insets(15, 15, 15, 15);
        constraints.ipadx = 60;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        pane.add(textField, constraints);


        int row = 1;
        constraints.gridy = row;
        constraints.fill = GridBagConstraints.CENTER;
        constraints.insets = new Insets(15, 15, 15, 15);
        constraints.ipadx = 2;
        constraints.gridx = 0;
        constraints.gridwidth = 2;

        ArrayList data = new ArrayList<TableObject>(Arrays.asList(new TableObject("asd", "Amit", "670000"),
                new TableObject("123", "Jai", "  Raku"), new TableObject("123", "Sachin", "700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000")));

        String column[] = {"ID", "NAME", "SALARY"};

        DefaultTableModel model = new DefaultTableModel(column, 0);

        JTable jt = new JTable(model) {

            private static final long serialVersionUID = 1L;

            @Override
            public void doLayout() {
                //  for (int i = 1; i < 2; i++) {
                TableColumn col = getColumnModel().getColumn(2);
                for (int row = 0; row < getRowCount(); row++) {
                    Component c = prepareRenderer(col.getCellRenderer(), row, 2);
                    if (c instanceof JTextArea) {
                        JTextArea a = (JTextArea) c;
                        int h = getPreferredHeight(a) + getIntercellSpacing().height;
                        if (getRowHeight(row) != h) {
                            setRowHeight(row, h);
                        }
                    }
                }
                //  }
                super.doLayout();
            }            //http://tips4java
        };

        jt.setBounds(30, 40, 200, 300);

        //para resize textarea hay que anotar colunindex
        jt.getColumnModel().getColumn(0).setCellRenderer(new TextAreaCellRenderer());
        jt.getColumnModel().getColumn(1).setCellRenderer(new TextAreaCellRenderer());
        jt.getColumnModel().getColumn(2).setCellRenderer(new TextAreaCellRenderer());

        jt.setBounds(30, 40, 200, 300);

        model.addRow(new String[]{"2412", "XXX", "60500"});

        model.addRow(new String[]{"4121", "YYYYYY", "60, 700, 800, 200, 800, 1200, 400, 123, 600, 670, 130, 540"});

        JScrollPane sp = new JScrollPane(jt);
        pane.add(sp, constraints);

    /*    for (int column = 1; column <= 4; column++) {

            //new old.elements().addTextArea(pane, column);

            constraints.gridx = 0;
            constraints.gridy = column;
            constraints.ipadx = 0;
            constraints.ipady = 0;
            constraints.gridwidth = 2;
            constraints.weightx = 1;

            JTextArea textArea3 = new JTextArea(3, 20);

            Border border = BorderFactory.createLineBorder(Color.BLACK);

            textArea3.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));

            JPanel areaPane = new JPanel();
            areaPane.add(textArea3, constraints);
            textArea3.setEditable(false);
            pane.add(areaPane, constraints);

        }*/
        //pane.add(scrollPane);
        return pane;
    }
    //   }

    private void createAndShowGUI() {

      /*   //Create and set up the window.
       JFrame frame = new JFrame("Contract Master");
        frame.setSize(new Dimension(400, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        //  SeekBar = addComponentsToPane(frame.getContentPane());*/

        componentsContainer = addComponentsToPane(getContentPane());

        //addComponentsToPane(frame.getContentPane());
        //Display the window.
        //frame.pack();
        //frame.setVisible(true);

    }

    public Container getComponentsContainer() {
        return componentsContainer;
    }


    private int getPreferredHeight(JTextComponent c) {
        Insets insets = c.getInsets();
        javax.swing.text.View view = c.getUI().getRootView(c).getView(0);
        int preferredHeight = (int) view.getPreferredSpan(javax.swing.text.View.Y_AXIS);
        return preferredHeight + insets.top + insets.bottom;
    }


  public void setListenerOnAddComponents(ActionListener mListener) {
        Seekbutton.addActionListener(mListener);
    }
}
