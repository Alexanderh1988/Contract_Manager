package View;

import Model.TableObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class View extends JFrame {

    static ArrayList<TableObject> data;
    public static GridBagConstraints constraints;
    public static GridBagLayout gridType;

    public static void main(String[] args) {

        //  addComponents addComponents = null;
        new View();

    }

    public View() {


        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                createAndShowGUI(data);

                //   timer = new Timer();
                // old.Helper class extends TimerTask
                //   TimerTask task = new old.TableConJareaIncorporado.Helper2();
                //  timer.schedule(task, 1000, 3000);

            }
        });


    }

    private void createAndShowGUI(ArrayList<TableObject> datos) {

        //Create and set up the window.
         /*   data = new ArrayList<TableObject>(Arrays.asList(new TableObject("asd", "Amit", "670000"),
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

                }*/
        //http://tips4java.wordpress.com/2008/10/26/text-utilities/

        /*        private int getPreferredHeight(JTextComponent c) {
                    Insets insets = c.getInsets();
                    javax.swing.text.View view = c.getUI().getRootView(c).getView(0);
                    int preferredHeight = (int) view.getPreferredSpan(javax.swing.text.View.Y_AXIS);
                    return preferredHeight + insets.top + insets.bottom;
                }*/
        // };
          /*  model.addRow(new String[]{"aaaasdd asdasd as dwqrwcqerxq w qwecrqw ec wcq cqwddd dddddd ddddddd dddddddddd ddsda", "as", "s"});
            model.addRow(new String[]{"aaaasdd ", "as", "s daaa aaaa asd asd ad asd a a asddas aa aasdd dddd ddd dddd ddddd dddddd dddddddd ddddddddd dddddd ddd ddddv dddddd dddds"});
            model.addRow(new String[]{"f", "as", "s"});*/
        //jt.getColumnModel().getColumn(2).setCellRenderer(new TextAreaCellRenderer());
        //  jt.setBounds(30, 40, 200, 300);
        //JScrollPane sp = new JScrollPane(jt);

        JFrame frame = new JFrame("Contract Master");
        frame.setSize(new Dimension(600, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = new Container();
        gridType = new GridBagLayout();
        constraints = new GridBagConstraints();
        frame.add(pane);

        //new addComponents().getSeekbutton().addActionListener(this);

        frame.add(new addComponents().getComponentsContainer());

        frame.setResizable(false);
        frame.setVisible(true);

        addComponents component = new addComponents();

        component.setContentPane(pane);

        //this.addComponents = addComponents;
        //  component.setOnClickListener(new CalculationListener1());

        //  component.setOnClickListener(new CalculationListener1());

        //  JButton seekerView = component.getSeekbutton();
        //  seekerView.addActionListener(new CalculationListener1());


    }

    public void setListenerOnButton(ActionListener mListener) {
        new addComponents().setListenerOnAddComponents(mListener);
    }


}
