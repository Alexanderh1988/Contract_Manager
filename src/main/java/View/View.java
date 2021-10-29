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

    public static firstPaneComponents mFirstComponentsPanel = new firstPaneComponents();
    public static JMenu1 mJMenu = new JMenu1();

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

        frame.add(mFirstComponentsPanel.getComponentsContainer());
        //mFirstComponentsPanel.setContentPane(pane);

        //frame.add(mJMenu1.getMenuBar());
        frame.setJMenuBar(mJMenu.getMenuBar());

        frame.setResizable(true);
        // frame.pack();
        frame.setVisible(true);
    }

    public void setListenerOnSeekButton(ActionListener mListener) {
        mFirstComponentsPanel.setListenerOnSearchButton(mListener);
    }

    public void setListenerOnDeleteButton(ActionListener mListener) {
        mFirstComponentsPanel.setListenerOnDeleteButton(mListener);
    }
    public void setListenerOnLocationButton(ActionListener mListener) {
        mFirstComponentsPanel.setListenerOnChangeLocation(mListener);
    }

    public void addNewRow(ArrayList<TableObject> data, String soughtWord) {
        mFirstComponentsPanel.addColumnToJtable(data, soughtWord);
    }

    public void setListenerOnResetData(ActionListener mListener) {
        mFirstComponentsPanel.setListenerOnResetData(mListener);
    }

    public void noWordsFound() {
        JOptionPane.showMessageDialog(null, "No se encontró nada ");
    }

    public void fileIsTooBig() {
        JOptionPane.showMessageDialog(null, "Archivo muy grande ");
    }

    public Boolean getSubFolderValue() {
        return mFirstComponentsPanel.getCheckbtn().isSelected();
    }

    public void setOnJtableRowListener(ListSelectionListener listSelectionListener) {
        mFirstComponentsPanel.onRowSelected(listSelectionListener);
    }

    public void setOnCellJtableListener(MouseListener mouseListener) {
        mFirstComponentsPanel.onCellMouseClickListener(mouseListener);
    }

    public static JTable getJTable() {
        return mFirstComponentsPanel.getJt();
    }

    public static firstPaneComponents getmFirstComponentsPanel() {
        return mFirstComponentsPanel;
    }

    public void setMenuClickListener(ActionListener Listener) {
        mJMenu.setItem1Click(Listener);
    }

    public static JMenu1 getmJMenu() {
        return mJMenu;
    }

    public String getTextToSearch() {
        return mFirstComponentsPanel.getTextField().getText();
    }

    public void clearTable() {
        mFirstComponentsPanel.clearTable();
    }


}
