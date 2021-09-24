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


public class addComponents extends JFrame implements ActionListener{

    final static boolean shouldFill = false;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = true;
    Container componentsContainer;

    TextField textField;
    public static JButton Seekbutton;
    DefaultTableModel model;

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

        //ROW #1
        pane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel mJLabel = new JLabel("Palabra a buscar:");

        mJLabel.setSize(new Dimension(100, 20));

        // constraints.weightx = 0.3;
        constraints.ipadx = 120;
        constraints.anchor = GridBagConstraints.LAST_LINE_END;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        pane.add(mJLabel, constraints);

        //ROW #2

        textField = new TextField(" ");

        constraints.fill = GridBagConstraints.LAST_LINE_END;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.ipadx = 180;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(5, 5, 5, 5);
        pane.add(textField, constraints);


        constraints.fill = GridBagConstraints.CENTER;
        constraints.ipadx = 50;
        // constraints.weightx = 0.5;
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(5, 5, 5, 5);

        Seekbutton = new JButton("Buscar");
        pane.add(Seekbutton, constraints);

        JButton btn = new JButton("?");
        constraints.gridy = 1;
        constraints.gridx = 3;
        constraints.gridwidth = 1;
        constraints.ipadx = 1;
        pane.add(btn, constraints);
        btn.addActionListener(this);

        constraints.gridy = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(15, 15, 15, 15);
        constraints.ipadx = 0;
        constraints.gridx = 0;
        constraints.gridwidth = 4;

        String column[] = {"ID", "NAME", "SALARY"};

        model = new DefaultTableModel(column, 0);

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

        JScrollPane sp = new JScrollPane(jt);
        pane.add(sp, constraints);

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

    public void addColumnToJtable(ArrayList<TableObject> data) {

        //primero borra todas las tablas
        model.setRowCount(0);
        for (int i = 0; i < data.size(); i++) {

            TableObject row = new TableObject(data.get(i).getId(), data.get(i).getName(), data.get(i).getSalary());
            model.addRow(new String[]{row.getId(), row.getName(), row.getSalary()});
        }

/*        ArrayList data = new ArrayList<TableObject>(Arrays.asList(new TableObject("asd", "Amit", "670000"),
                new TableObject("123", "Jai", "  Raku"), new TableObject("123", "Sachin", "700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000")));*/
    }


    public TextField getTextField() {
        return textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Agrega ',' entre palabras para buscar ambas al mismo tiempo ");
    }
}
