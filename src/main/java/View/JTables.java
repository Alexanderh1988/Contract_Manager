/*
package View;

import Model.TableObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


public class JTables extends JFrame {

    public Container setmJTablePane;

    public JTables() {
        setmJTablePane = CreateJTable(getContentPane());
    }

    public Container CreateJTable(Container pane) {

        //  pane.setLayout(new GridBagLayout());
        pane.setLayout(gridType);
//        GridBagConstraints c = new GridBagConstraints();

        constraints.fill = GridBagConstraints.CENTER;
        constraints.insets = new Insets(15, 15, 15, 15);
        constraints.ipadx = 3;
        constraints.gridx = 0;
        constraints.gridy = 1;


        ArrayList data = new ArrayList<TableObject>(Arrays.asList(new TableObject("asd", "Amit", "670000"),
                new TableObject("123", "Jai", "  Raku"), new TableObject("123", "Sachin", "700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000")));

        String column[] = {"ID", "NAME", "SALARY"};

        DefaultTableModel model = new DefaultTableModel(column, 0);

        JTable jt = new JTable(model) {

            private static final long serialVersionUID = 1L;

            @Override
            public void doLayout() {
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
                super.doLayout();
            }            //http://tips4java
        };

        jt.setBounds(30, 40, 200, 300);

        jt.getColumnModel().getColumn(2).setCellRenderer(new TextAreaCellRenderer());

        jt.setBounds(30, 40, 200, 300);

        model.addRow(new String[]{"asf", "as", "s"});

        JScrollPane sp = new JScrollPane(jt);
        pane.add(sp, constraints);

        return pane;
    }

    private int getPreferredHeight(JTextComponent c) {
        Insets insets = c.getInsets();
        javax.swing.text.View view = c.getUI().getRootView(c).getView(0);
        int preferredHeight = (int) view.getPreferredSpan(javax.swing.text.View.Y_AXIS);
        return preferredHeight + insets.top + insets.bottom;
    }

    public Container getmJTablePane() {
        return setmJTablePane;
    }

    public void setmJTablePane(Container mJTableContainer) {
        this.setmJTablePane = mJTableContainer;
    }
}
*/
