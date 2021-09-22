package old;

import Model.TableObject;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;


public class table2 extends JFrame {

    static DefaultTableModel tableModel;
    static ArrayList<TableObject> data;
    static Integer contador = 0;
    static java.util.Timer timer;

    protected static String[] columnToolTips = {null,
            null,
            "The person's favorite sport to participate in",
            "The number of years the person has played the sport",
            "If checked, the person eats no meat"};


    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame f;

        f = new JFrame();


        data = new ArrayList<TableObject>(Arrays.asList(new TableObject("ad", "Amit", "670000"),
                new TableObject("asd", "Jai", "780000"), new TableObject("asd", "Sachin", "700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000")));


        String column[] = {"ID", "NAME", "SALARY"};

        tableModel = new DefaultTableModel(column, 0);

        JTable jt = new JTable(new MyTableModel()) {

            //Implement table old.cell tool tips.
            public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);
                int realColumnIndex = convertColumnIndexToModel(colIndex);

                if (realColumnIndex == 2) { //Sport column
                    tip = "This person's favorite sport to "
                            + "participate in is: "
                            + getValueAt(rowIndex, colIndex);
                } else if (realColumnIndex == 4) { //Veggie column
                    TableModel model = getModel();
                    String firstName = (String) model.getValueAt(rowIndex, 0);
                    String lastName = (String) model.getValueAt(rowIndex, 1);
                    Boolean veggie = (Boolean) model.getValueAt(rowIndex, 4);
                    if (Boolean.TRUE.equals(veggie)) {
                        tip = firstName + " " + lastName
                                + " is a vegetarian";
                    } else {
                        tip = firstName + " " + lastName
                                + " is not a vegetarian";
                    }
                } else {
                    //You can omit this part if you know you don't
                    //have any renderers that supply their own tool
                    //tips.
                    tip = super.getToolTipText(e);
                }
                return tip;
            }

            //Implement table header tool tips.
            protected JTableHeader createDefaultTableHeader() {
                return new JTableHeader(columnModel) {
                    public String getToolTipText(MouseEvent e) {
                        String tip = null;
                        java.awt.Point p = e.getPoint();
                        int index = columnModel.getColumnIndexAtX(p.x);
                        int realIndex = columnModel.getColumn(index).getModelIndex();
                        return columnToolTips[realIndex];
                    }
                };
            }
        };


   TableColumn tb1 = jt.getColumnModel().getColumn(0);
   tb1.setMinWidth(300);



        jt.setBounds(30, 40, 200, 300);

        for (int i = 0; i < data.size(); i++) {
            tableModel.addRow(new String[]{data.get(i).getId().toString(), data.get(i).getName(), data.get(i).getSalary()});
        }

        JScrollPane sp = new JScrollPane(jt);

        f.add(sp);
        f.setSize(300, 400);
        f.setVisible(true);

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

    static class MyTableModel extends AbstractTableModel {

        private String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};

        private Object[][] data = {
                {"Kathy", "Smith",
                        "Snowboarding", new Integer(5), new Boolean(false)},
                {"John", "Doe",
                        "Rowing", new Integer(3), new Boolean(true)},
                {"Sue", "Black",
                        "Knitting", new Integer(2), new Boolean(false)},
                {"Jane", "White",
                        "Speed reading", new Integer(20), new Boolean(true)},
                {"Joe", "Brown",
                        "Pool", new Integer(10), new Boolean(false)}
        };

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }
    }
}
