package old;

import Model.TableObject;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.Timer;

public class TableConJareaIncorporado extends JFrame {

    static DefaultTableModel tableModel;
    static ArrayList<TableObject> data;
    static Integer contador = 0;
    static java.util.Timer timer;

   /* public static class JTextPaneCellEditor1 extends AbstractCellEditor implements TableCellEditor, KeyListener {

        private JViewport viewport;
        private JTable table;
        private int row;
        private JTextPane pane;

        public JTextPaneCellEditor1() {
            viewport = new JViewport();
            pane = new JTextPane();
            viewport.add(pane);
            pane.addKeyListener(this);
        }

        @Override
        public Object getCellEditorValue() {
            return pane.getText();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.table = table;
            this.row = row;
            pane.setText(value.toString());
            int newHeight = (int) pane.getPreferredSize().getHeight();
            if (table.getRowHeight(row) < newHeight) {
                table.setRowHeight(row, newHeight);
            }
            return pane;
        }

        @Override
        public boolean isCellEditable(EventObject e) {
            if (e instanceof MouseEvent) {
                return ((MouseEvent) e).getClickCount() >= 2;
            }
            return true;
        }

        @Override
        public void keyTyped(KeyEvent e) {
            table.setRowHeight(row, (int) pane.getPreferredSize().getHeight());
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                stopCellEditing();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }*/

    /*private static class JTextPaneCellRenderer extends JViewport implements TableCellRenderer {

        JTextPane pane;

        JTextPaneCellRenderer() {
            pane = new JTextPane();
            add(pane);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            pane.setText(value.toString());
            table.setRowHeight(row, (int) pane.getPreferredSize().getHeight());
            return this;
        }
    }*/

    /*static class Helper2 extends TimerTask {

        public void run() {
            feedData();
        }

        private void feedData() {

            //   tableModel.addRow(new String[]{data.get(contador).getId().toString(), data.get(contador).getName(), data.get(contador).getSalary()});

            tableModel.addRow(new String[]{"aaasda", "as", "s"});
            contador++;

            System.out.println("contador: " + contador);
            if (contador == 3) {
                timer.cancel();
                timer.purge();
            }
        }
    }*/

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame f;

        f = new JFrame();

        data = new ArrayList<TableObject>(Arrays.asList(new TableObject("asd", "Amit", "670000"),
                new TableObject("123", "Jai", "  Raku"), new TableObject("123", "Sachin", "700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000700000")));

        String
                //column[] = {"ID"};
                column[] = {"ID", "NAME", "SALARY"};
        //  Object data1[] = {"ID"};

        //  tableModel = new DefaultTableModel(column, 0);
        //  JTable jt = new JTable(tableModel);

        String[] columnNames = {"old.TextAreaCellRenderer"};
      /*  Object[][] data = {
                {"123456789012345678901234567890"},
                {"dddddddddddddddddddddddddddddddddddddddddddddddddddddddddx"},
                {"----------------------------------------------0"},
                {">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>|"},};*/

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
            }            //http://tips4java.wordpress.com/2008/10/26/text-utilities/

            private int getPreferredHeight(JTextComponent c) {
                Insets insets = c.getInsets();
                View view = c.getUI().getRootView(c).getView(0);
                int preferredHeight = (int) view.getPreferredSpan(View.Y_AXIS);
                return preferredHeight + insets.top + insets.bottom;
            }
        };

        model.addRow(new String[]{"aaaasdd asdasd as dwqrwcqerxq w qwecrqw ec wcq cqwddd dddddd ddddddd dddddddddd ddsda", "as", "s"});
        model.addRow(new String[]{"aaaasdd ", "as", "s daaa aaaa asd asd ad asd a a asddas aa aasdd dddd ddd dddd ddddd dddddd dddddddd ddddddddd dddddd ddd ddddv dddddd dddds"});
        model.addRow(new String[]{"f", "as", "s"});

        TableColumnModel columnModel = jt.getColumnModel();
        jt.getColumnModel().getColumn(2).setCellRenderer(new TextAreaCellRenderer());
        //columnModel.getColumn(0).setPreferredWidth(100);
        //columnModel.getColumn(1).setPreferredWidth(100);
        //columnModel.getColumn(2).setPreferredWidth(500);

        //jt.getColumnModel().getColumn(1).setWidth(100);

        //jt.setDefaultRenderer(Object.class, new old.TableConJareaIncorporado.JTextPaneCellRenderer());
        //jt.setDefaultEditor(Object.class, new JTextPaneCellEditor1.JTextPaneCellEditor1());

        //int height = 40;
        //jt.setRowHeight(height);

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

                //   timer = new Timer();
                // old.Helper class extends TimerTask
                //   TimerTask task = new old.TableConJareaIncorporado.Helper2();

                //  timer.schedule(task, 1000, 3000);

            }
        });
    }


    static class TextAreaCellRenderer extends JTextArea implements TableCellRenderer {

        private static final long serialVersionUID = 1L;
        private final Color evenColor = new Color(230, 240, 255);

        public TextAreaCellRenderer() {
            super();
            setLineWrap(true);
            setWrapStyleWord(true);
            setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(table.getBackground());
                setBackground((row % 2 == 0) ? evenColor : getBackground());
            }
            setFont(table.getFont());
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }
}

