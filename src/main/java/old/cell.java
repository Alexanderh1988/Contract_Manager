package old;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.*;

public class cell {

    public JComponent makeUI() {
        String[] columnNames = {"old.TextAreaCellRenderer"};
        Object[][] data = {
                {"123456789012345678901234567890"},
                {"dddddddddddddddddddddddddddddddddddddddddddddddddddddddddx"},
                {"----------------------------------------------0"},
                {">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>|"},};

        TableModel model = new DefaultTableModel(data, columnNames) {

            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model) {

            private static final long serialVersionUID = 1L;

            @Override
            public void doLayout() {
                TableColumn col = getColumnModel().getColumn(0);
                for (int row = 0; row < getRowCount(); row++) {
                    Component c = prepareRenderer(col.getCellRenderer(), row, 0);
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

        table.setEnabled(false);
        table.setShowGrid(false);
        table.setTableHeader(null);
        table.getColumnModel().getColumn(0).setCellRenderer(new TextAreaCellRenderer());
        JScrollPane sp = new JScrollPane(table);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel p = new JPanel(new BorderLayout());
        p.add(sp);
        return p;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public static void createAndShowGUI() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.getContentPane().add(new cell().makeUI());
        f.setSize(200, 200);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}

class TextAreaCellRenderer extends JTextArea implements TableCellRenderer {

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