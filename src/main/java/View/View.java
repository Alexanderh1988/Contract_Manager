package View;

import Model.TableObject;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class View extends JFrame {

    public secondPaneComponents mSecondComponentsPane = new secondPaneComponents();
    public firstPaneComponents mFirstComponentsPane = new firstPaneComponents();

    public JMenu1 mJMenu1 = new JMenu1();
    JFrame frame;
    Container pane;
    private Boolean currentPane1 = true;

    private View view1;
    private View view2;
    private View view3;

    public static void main(String[] args) {

        //  addComponents addComponents = null;
        new View();
    }

    public View() {

        javax.swing.SwingUtilities.invokeLater(() -> {

            createAndShowGUI();

            //   timer = new Timer();
            // old.Helper class extends TimerTask
            //   TimerTask task = new old.TableConJareaIncorporado.Helper2();
            //  timer.schedule(task, 1000, 3000);

        });

    }

    private void createAndShowGUI() {


        frame = new JFrame("Contract Master");

        //frame.setSize(new Dimension(950, 600));
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        frame.setMinimumSize(new Dimension(400, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pane = new Container();

        frame.add(pane);

        /* if (*//*m*//* true)   //get panel with control buttons
        {
            mFirstComponentsPane = new firstPaneComponents(true);
            frame.getContentPane().add(mFirstComponentsPane.getComponentsContainer());
        } else   //get just the table
*/

        frame.getContentPane().add(mFirstComponentsPane.getComponentsContainer());


//frame.add(mSecondComponentsPane.getComponentsContainer());
// frame.add(mFirstComponentsPane.getComponentsContainer());
//mFirstComponentsPanel.setContentPane(pane);
//frame.add(mJMenu1.getMenuBar());
        frame.setJMenuBar(mJMenu1.getMenuBar1());

        frame.setResizable(true);
        // frame.pack();
        frame.setVisible(true);
    }

    public void setListenerOnSeekButton(ActionListener mListener) {
        mFirstComponentsPane.setButtonSeekerListener(mListener);
    }

    public void setListenerOnStopButton(ActionListener mListener) {
        mFirstComponentsPane.setButtonStopListener(mListener);
    }

    public void setListenerOnSeekButton2(ActionListener mListener) {
        mSecondComponentsPane.setListenerOnSeekButton2(mListener);
    }

    public void setListenerOnExportButton(ActionListener mListener) {
        mSecondComponentsPane.setListenerOnExportButton(mListener);
    }

    public void addNewRow(ArrayList<TableObject> data, String soughtWord) {
        mFirstComponentsPane.addColumnToJtable(data, soughtWord);
    }

    public void addNewRow2(ArrayList<TableObject> data) {
        mSecondComponentsPane.addColumnToJtable(data);
    }


    public void fileIsTooBig() {
        JOptionPane.showMessageDialog(null, "Archivo muy grande ");
    }

    public void setOnJtableRowListener(ListSelectionListener listSelectionListener) {
        if (currentPane1)
            mFirstComponentsPane.onRowSelected(listSelectionListener);
        else
            mSecondComponentsPane.onRowSelected(listSelectionListener);
    }

    public void setOnCellJtableListener(MouseListener mouseListener) {
        mSecondComponentsPane.onCellMouseClickListener(mouseListener);
    }

    public JMenu1 getmJMenu() {
        return mJMenu1;
    }

    public void setmJMenu(JMenu1 mJMenu) {
        this.mJMenu1 = mJMenu;
    }

    public void setMenuChequearListener(ActionListener Listener) {
        mJMenu1.getMenuChequeo().addActionListener(Listener);
    }

    public void setMenuBuscarListener(ActionListener Listener) {
        mJMenu1.getMenuBuscador().addActionListener(Listener);
    }

    public void setgetMenuBuscadorIndependienteListener(ActionListener Listener) {
        mJMenu1.getMenuBuscadorIndependiente().addActionListener(Listener);
    }
       public JMenu getMenuBuscadorIndependiente() {
        return mJMenu1.getMenuBuscadorIndependiente();
    }

    public void clearTable() {
        mSecondComponentsPane.clearTable();
    }

    public secondPaneComponents getmSecondComponentsPane() {
        return mSecondComponentsPane;
    }

    public void setmSecondComponentsPane(secondPaneComponents mSecondComponentsPane) {
        this.mSecondComponentsPane = mSecondComponentsPane;
    }

    public void setLocationChooserListener(ActionListener mListener) {

        mSecondComponentsPane.getSelectLocation().addActionListener(mListener);
        mFirstComponentsPane.getSelectLocation().addActionListener(mListener);

    }

    public firstPaneComponents getmFirstComponentsPane() {
        return mFirstComponentsPane;
    }


    public void setMenuItemBuscador() {
        currentPane1 = true;
        frame.getContentPane().removeAll();
        frame.getContentPane().add(mFirstComponentsPane.getComponentsContainer());
        frame.validate();
        frame.repaint();
    }

    public void setMenuItemChequeo() {
        currentPane1 = false;
        frame.getContentPane().removeAll();
        frame.getContentPane().add(mSecondComponentsPane.getComponentsContainer());
        frame.validate();
        frame.repaint();
    }

    public Boolean getCurrentPane1() {
        return currentPane1;
    }

    public void showViews() {

        view1 = new View();
        view2 = new View();
        view2 = new View();
    }

    public View getView1() {
        return view1;
    }

    public View getView2() {
        return view2;
    }

    public View getView3() {
        return view3;
    }
}

