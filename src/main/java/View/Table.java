package View;

import Model.TableObject;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Table extends JFrame {

    static DefaultTableModel tableModel;
    static ArrayList<TableObject> data;
    static Integer contador = 0;
    static java.util.Timer timer;

    static class Helper1 extends TimerTask {

        public void run() {
            //createAndShowGUI();
            feedData();
        }

        private void feedData() {

            //jt.addColumn(data,column);
            // for (int i = 0; i < 4; i++) {
           // for (int i = 0; i <contador ; i++) {
            //    tableModel.addRow(new String[]{data.get(i).getId().toString(), data.get(i).getName(), data.get(i).getSalary()});
            //}
            //}
            tableModel.addRow(new String[]{data.get(contador).getId().toString(), data.get(contador).getName(), data.get(contador).getSalary()});

            tableModel.addRow(new String[]{"asf", "as", "s"});
            contador++;

            System.out.println("contador: " + contador);
            if (contador == 3) {
                timer.cancel();
                timer.purge();
            }

        }
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame f;

        f = new JFrame();
        /*String data[][] = {{"101", "Amit", "670000"},
                {"102", "Jai", "780000"},
                {"101", "Sachin", "700000"}};*/

        data = new ArrayList<TableObject>(Arrays.asList(new TableObject("asd", "Amit", "670000"),
                new TableObject("ssa", "Jai", "780000"), new TableObject("asd", "Sachin", "700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000")));


        String column[] = {"ID", "NAME", "SALARY"};

        //JTable jt = new JTable(data, column);



        tableModel = new DefaultTableModel(column, 0);
        final JTable jt = new JTable(tableModel);

        //row click listener
        jt.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
                System.out.println(jt.getValueAt(jt.getSelectedRow(), 0).toString());
            }
        });

        //int fontHeight = this.getFontMetrics(this.getFont()).getHeight();
        //int textLength = this.getText().length();
       // int lines = textLength / this.getColumns() +1;//+1, cause we need at least 1 row.
      //  int height = fontHeight * lines;
        int height = 40;
        jt.setRowHeight(height);


        //jt.add();

        jt.setBounds(30, 40, 200, 300);

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

                timer = new Timer();
                // old.Helper class extends TimerTask
                TimerTask task = new Table.Helper1();

                timer.schedule(task, 1000, 3000);

            }
        });
    }
}
