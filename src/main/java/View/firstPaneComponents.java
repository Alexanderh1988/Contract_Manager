package View;

import Model.TableObject;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;


public class firstPaneComponents extends JFrame implements ActionListener {

    Container componentsContainer;

    private TextField textField1;
    private TextField textField2;
    private TextField textField3;
    private JButton Seekbutton;
    private JButton selectLocation;
    private JButton borrar;
    private DefaultTableModel model;
    private JCheckBox checkbtn;
    private JTable jt;

    public static void main(String[] args) {
        new firstPaneComponents();
    }

    public firstPaneComponents() {

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
        GridBagConstraints subconstraints = new GridBagConstraints();

        constraints.insets = new Insets(2, 3, 2, 3);
        subconstraints.insets = new Insets(2, 3, 2, 3);

        Container searchPanel = new Container();

        searchPanel.setLayout(new GridBagLayout());

        subconstraints.anchor = GridBagConstraints.WEST;
        subconstraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel BuscarLabel = new JLabel("Buscar:");
        JLabel ExluirLabel = new JLabel("Excluir:");
        //   JLabel mJLabel3 = new JLabel("Estricto:");

        textField1 = new TextField("   ");
        textField2 = new TextField("   ");
        textField3 = new TextField("   ");

        searchPanel.setSize(new Dimension(100, 20));

        subconstraints.gridx = 0;
        subconstraints.gridy = 1;
        // subconstraints.gridwidth = 1;

        subconstraints.ipadx = 50;

        searchPanel.add(BuscarLabel, subconstraints);
        subconstraints.gridx = 1;
        searchPanel.add(textField1, subconstraints);
        subconstraints.gridx = 2;
        searchPanel.add(textField2, subconstraints);
        subconstraints.gridx = 3;
        JCheckBox checkbtn2 = new JCheckBox();
        searchPanel.add(checkbtn2, subconstraints);
        subconstraints.gridx = 0;
        subconstraints.gridy = 2;
        searchPanel.add(ExluirLabel, subconstraints);
        subconstraints.gridx = 1;
        searchPanel.add(textField3, subconstraints);
        subconstraints.gridy = 2;
        // searchPanel.add(textField2, subconstraints);


        //subconstraints.fill = GridBagConstraints.LAST_LINE_END;
        subconstraints.insets = new Insets(2, 2, 2, 2);
        subconstraints.ipadx = 50;
        subconstraints.gridwidth = 1;

        subconstraints.gridx = 1;
        subconstraints.gridx = 3;


        //---------------------------------------------------------

        //  constraints.fill = GridBagConstraints.CENTER;
        constraints.ipadx = 30;
        // constraints.weightx = 0.5;

        subconstraints.gridx = 0;
        subconstraints.gridy = 1;
        pane.add(searchPanel, subconstraints);
        subconstraints.gridx = 4;

        //constraints.insets = new Insets(2, 2, 2, 2);

        Seekbutton = new JButton("Buscar");
        searchPanel.add(Seekbutton, subconstraints);
        subconstraints.gridy = 2;
        selectLocation = new JButton("Cambiar Directorio");
        searchPanel.add(selectLocation, subconstraints);
        //cambio de directorio:
        Container cone1 = new Container();

        JButton btn = new JButton("?");
        subconstraints.gridx = 5;


        searchPanel.add(btn, subconstraints);
        btn.addActionListener(this);

        //se agrega el container de directorio:
        //constraints.gridy = 1;
        //constraints.gridx = 2;
        //constraints.gridwidth = 1;
        //constraints.ipadx = 1;
        //  pane.add(selectLocation, constraints);
        pane.add(cone1, subconstraints);

        subconstraints.gridx = 0;
        subconstraints.gridy = 3;
        subconstraints.fill = GridBagConstraints.HORIZONTAL;
        subconstraints.insets = new Insets(5, 5, 5, 5);
        subconstraints.ipadx = 900;
        subconstraints.ipady = 550;


        constraints.gridwidth = 4;

        String column[] = {"ID", "DOCUMENTO", "TEXTO", "PAGINAS"};

        model = new DefaultTableModel(column, 0);

        jt = new JTable(model) {

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
                super.doLayout();
            }            //http://tips4java
        };

        //para resize textarea hay que anotar colunindex
        jt.getColumnModel().getColumn(0).setCellRenderer(new TextAreaCellRenderer());
        jt.getColumnModel().getColumn(0).setPreferredWidth(50);
        jt.getColumnModel().getColumn(0).setMaxWidth(60);
        jt.getColumnModel().getColumn(0).setMinWidth(60);

        jt.getColumnModel().getColumn(1).setCellRenderer(new TextAreaCellRenderer());
        jt.getColumnModel().getColumn(1).setPreferredWidth(100);
        jt.getColumnModel().getColumn(2).setCellRenderer(new TextAreaCellRenderer());
        jt.getColumnModel().getColumn(2).setPreferredWidth(100);
        jt.getColumnModel().getColumn(2).setPreferredWidth(700);
        jt.getColumnModel().getColumn(3).setMaxWidth(80);

        JScrollPane sp = new JScrollPane(jt);
        pane.add(sp, subconstraints);

        //pane.add(scrollPane);
        return pane;
    }
    //   }

    private void createAndShowGUI() {
        componentsContainer = addComponentsToPane(getContentPane());
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

    public void setListenerOnSearchButton(ActionListener mListener) {
        Seekbutton.addActionListener(mListener);
    }


    public void setListenerOnDeleteButton(ActionListener mListener) {
        borrar.addActionListener(mListener);
    }

    public void addColumnToJtable(ArrayList<TableObject> data, String soughtWord) {

        //primero borra todas las tablas
        model.setRowCount(0);

        for (int i = 0; i < data.size(); i++) {

            TableObject row = new TableObject(data.get(i).getId(), data.get(i).getDocumentName(), data.get(i).getText(), data.get(i).getKeyWord(), data.get(i).getPage());

            model.addRow(new String[]{row.getId(), row.getDocumentName(), row.getText().replaceAll(soughtWord, "<<<" + soughtWord + ">>>"), row.getPage().toString()});
            //  model.addRow(new String[]{"id","asdasd"+"<strong>+asdasd</strong>sdadsdasdasdasdasdasd", "asfasd", "asasdasd"});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Estricto significa buscada con ambos criterios considerados al mismo tiempo   /n " +
                "cierra todas las instancias de word de los archivos en los que quieras buscar");
    }

    public JButton getSeekbutton() {
        return Seekbutton;
    }

    public JButton getSelectLocation() {
        return selectLocation;
    }

    public JButton getBorrar() {
        return borrar;
    }

    public JCheckBox getCheckbtn() {
        return checkbtn;
    }

    public void onRowSelected(ListSelectionListener listListener) {
        jt.getSelectionModel().addListSelectionListener(listListener);
    }

    public void setButtonSeekerListener(ActionListener mListener) {
        Seekbutton.addActionListener(mListener);
    }

    public void setListenerOnResetData(ActionListener mListener) {
        borrar.addActionListener(mListener);
    }

    public void onCellMouseClickListener(MouseListener mouseListener) {
        jt.addMouseListener(mouseListener);
    }

    public JTable getJt() {
        return jt;
    }

    public void highlight(JTextComponent textComp, String pattern) {
        // First remove all old highlights
        //  removeHighlights(textComp);

        try {
            Highlighter hilite = textComp.getHighlighter();
            Document doc = textComp.getDocument();
            String text = doc.getText(0, doc.getLength());

            int pos = 0;
            // Search for pattern
            while ((pos = text.indexOf(pattern, pos)) >= 0) {
                // Create highlighter using private painter and apply around pattern
                hilite.addHighlight(pos, pos + pattern.length(), myHighlightPainter);
                pos += pattern.length();
            }

        } catch (BadLocationException e) {
        }
    }

    // An instance of the private subclass of the default highlight painter
    MyHighlightPainter myHighlightPainter = new MyHighlightPainter(Color.red);


    // A private subclass of the default highlight painter
    static class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {

        public MyHighlightPainter(Color color) {
            super(color);
        }
    }

    void clearTable() {
        model.setRowCount(0);
    }

    public TextField getTextField1() {
        return textField1;
    }

    public TextField getTextField2() {
        return textField2;
    }

    public TextField getTextField3() {
        return textField3;
    }
}
