package old;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class Start {

     public static class JTextPaneCellEditor extends AbstractCellEditor implements TableCellEditor, KeyListener {

        private JViewport viewport;
        private JTable table;
        private int row;
        private JTextPane pane;

        public JTextPaneCellEditor() {
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
    }

    private static class JTextPaneCellRenderer extends JViewport implements TableCellRenderer {
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
    }

    public static void main(String[] args) {

        JTable table = new JTable(new String[][]{{"String String String String StringString String String String StringString String String String StringString String String String StringString String String String String"}, {"String 2"}}, new String[]{"Column"});
       table.setDefaultRenderer(Object.class, new JTextPaneCellRenderer());
    table.setDefaultEditor(Object.class, new JTextPaneCellEditor());

        JFrame test = new JFrame();
        test.setPreferredSize(new Dimension(300,300));
        test.add(new JScrollPane(table));
        test.setVisible(true);
    }
}