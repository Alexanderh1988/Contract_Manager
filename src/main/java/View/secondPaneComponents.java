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

import static java.awt.GridBagConstraints.FIRST_LINE_START;

public class secondPaneComponents extends JFrame implements ActionListener {

    private static final boolean RIGHT_TO_LEFT = true;
    private Container componentsContainer;
    private JButton selectLocation;
    private JButton SeekButton2;
    private JButton export;
    private JButton borrar;
    private DefaultTableModel model;
    private JTable jt;

    public static void main(String[] args) {
        new secondPaneComponents();
    }

    public secondPaneComponents() {

        createAndShowGUI();
    }

    // dobu :   https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html

    public Container addComponentsToPane(Container pane) {

        GridBagLayout g = new GridBagLayout();

        //ROW #1
        pane.setLayout(g);

        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        GridBagConstraints constraints = new GridBagConstraints();
        GridBagConstraints subconstraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.PAGE_END;
        subconstraints.fill = GridBagConstraints.PAGE_END;

        //https://www.javatpoint.com/java-gridbaglayout

        //  subconstraints.insets = new Insets(2, 2, 2, 2);

        // Container searchPanel = new Container();
        //   subconstraints.ipady=50;
        subconstraints.ipadx = 50;

        constraints.ipady = 50;
        constraints.ipadx = 200;
        subconstraints.insets = new Insets(2, 2, 2, 2);

        Container container1 = new Container();

        container1.setLayout(g);

        selectLocation = new JButton("Cambiar Directorio");
        SeekButton2 = new JButton("Buscar");

        export = new JButton("Exportar");
        //  constraints.fill = GridBagConstraints.CENTER;
        //   subconstraints.i = 50;
        // constraints.weightx = 0.5;

        subconstraints.gridwidth = 1;
        subconstraints.gridx = 0;
        subconstraints.gridy = 0;

        container1.add(selectLocation, subconstraints);
        subconstraints.gridx = 1;
        container1.add(SeekButton2, subconstraints);

        subconstraints.gridx = 2;
        container1.add(export, subconstraints);

        constraints.gridx = 0;
        constraints.gridy = 0;

        pane.add(container1, constraints);
        constraints.gridy = 1;
        constraints.ipadx = 500;

        String column[] = {"ID", "DOCUMENTO", "TEXTO", " CLAVE", "PAGINAS"};

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
        //id:
        jt.getColumnModel().getColumn(0).setCellRenderer(new TextAreaCellRenderer());
        jt.getColumnModel().getColumn(0).setPreferredWidth(50);
        jt.getColumnModel().getColumn(0).setMaxWidth(60);
        jt.getColumnModel().getColumn(0).setMinWidth(60);
        //Documento
        jt.getColumnModel().getColumn(1).setCellRenderer(new TextAreaCellRenderer());
        jt.getColumnModel().getColumn(1).setPreferredWidth(150);
        //Texto
        jt.getColumnModel().getColumn(2).setCellRenderer(new TextAreaCellRenderer());
        jt.getColumnModel().getColumn(2).setPreferredWidth(100);
        jt.getColumnModel().getColumn(2).setPreferredWidth(700);
        //Clave
        jt.getColumnModel().getColumn(3).setMaxWidth(220);
        //Paginas
        jt.getColumnModel().getColumn(4).setPreferredWidth(80);

        JScrollPane sp = new JScrollPane(jt);

        pane.add(sp, constraints);

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

    public void setListenerOnSeekButton2(ActionListener mListener) {
        SeekButton2.addActionListener(mListener);
    }

    public void setListenerOnExportButton(ActionListener mListener) {
        export.addActionListener(mListener);
    }


    public void addColumnToJtable(ArrayList<TableObject> data ) {

        //primero borra todas las tablas
        model.setRowCount(0);

        for (int i = 0; i < data.size(); i++) {

            TableObject row = new TableObject(data.get(i).getId(), data.get(i).getDocumentName(), data.get(i).getText(), data.get(i).getKeyWord(), data.get(i).getPage());

            //ESTE ERA UNA PALABRA FIJA
            //  model.addRow(new String[]{row.getId(), row.getDocumentName(), row.getText().replaceAll(soughtWord, "<<<" + soughtWord + ">>>"), row.getPage().toString()});
            model.addRow(new String[]{row.getId(), row.getDocumentName(), row.getText().replaceAll(row.getKeyWord(), "<<<" + row.getKeyWord() + ">>>"), row.getKeyWord(), row.getPage().toString()});
            //  model.addRow(new String[]{"id","asdasd"+"<strong>+asdasd</strong>sdadsdasdasdasdasdasd", "asfasd", "asasdasd"});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Agrega ',' entre palabras para buscar ambas al mismo tiempo /n " +
                "cierra todas las instancias de word de los archivos en los que quieras buscar");
    }

    public JButton getSeekbutton2() {
        return SeekButton2;
    }

    public JButton getSelectLocation() {
        return selectLocation;
    }

    public JButton getBorrar() {
        return borrar;
    }


    public void onRowSelected(ListSelectionListener listListener) {
        jt.getSelectionModel().addListSelectionListener(listListener);
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
                //     hilite.addHighlight(pos, pos + pattern.length(), myHighlightPainter);
                pos += pattern.length();
            }

        } catch (BadLocationException e) {
        }
    }

    // An instance of the private subclass of the default highlight painter
    //   MyHighlightPainter myHighlightPainter = new MyHighlightPainter(Color.red);

 /*   public void setListenerOnResetData(ActionListener mListener) {
        borrar.addActionListener(mListener);
    }

    // A private subclass of the default highlight painter
    static class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {

        public MyHighlightPainter(Color color) {
            super(color);
        }
    }*/

    void clearTable() {
        model.setRowCount(0);
    }

    public void setSelectLocation(JButton selectLocation) {
        this.selectLocation = selectLocation;
    }


    public void setBorrar(JButton borrar) {
        this.borrar = borrar;
    }

    public JButton getExportar() {
        return export;
    }

    public void setExportar(JButton exportar) {
        this.export = exportar;
    }

}
