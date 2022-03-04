package View;

import Model.TableObject;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class View extends JFrame {

    public secondPaneComponents mSecondComponentsPane = new secondPaneComponents();

    public JMenu1 mJMenu = new JMenu1();

    public static void main(String[] args) {

        //  addComponents addComponents = null;
        new View();
    }

    public View() {

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

    private void createAndShowGUI() {

        JFrame frame = new JFrame("Contract Master");
        //frame.setSize(new Dimension(950, 600));
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        frame.setMinimumSize(new Dimension(400, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = new Container();

        frame.add(pane);

        frame.add(mSecondComponentsPane.getComponentsContainer());
        //mFirstComponentsPanel.setContentPane(pane);

        //frame.add(mJMenu1.getMenuBar());
        frame.setJMenuBar(mJMenu.getMenuBar());

        frame.setResizable(true);
        // frame.pack();
        frame.setVisible(true);
    }

    public void setListenerOnSeekButton(ActionListener mListener) {
        mSecondComponentsPane.setListenerOnSearchButton(mListener);
    }

     public void setListenerOnLocationButton(ActionListener mListener) {
        mSecondComponentsPane.setListenerOnChangeLocation(mListener);
    }

    public void addNewRow(ArrayList<TableObject> data, String soughtWord) {
        mSecondComponentsPane.addColumnToJtable(data, soughtWord);
    }

    public void setListenerOnResetData(ActionListener mListener) {
        mSecondComponentsPane.setListenerOnResetData(mListener);
    }

    public void fileIsTooBig() {
        JOptionPane.showMessageDialog(null, "Archivo muy grande ");
    }


    public void setOnJtableRowListener(ListSelectionListener listSelectionListener) {
        mSecondComponentsPane.onRowSelected(listSelectionListener);
    }

    public void setOnCellJtableListener(MouseListener mouseListener) {
        mSecondComponentsPane.onCellMouseClickListener(mouseListener);
    }


    public JMenu1 getmJMenu() {
        return mJMenu;
    }

    public void setmJMenu(JMenu1 mJMenu) {
        this.mJMenu = mJMenu;
    }

    public void setMenuClickListener(ActionListener Listener) {
        mJMenu.setItem1Click(Listener);
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


}
