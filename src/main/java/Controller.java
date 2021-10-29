import Model.Model;
import View.View;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


public class Controller {

    View mView;
    Model mModel;

    public static void main(String[] args) {
        //new Controller(new View());
    }

    public Controller(View mView, Model mModel) {

        //Menu 1:
        this.mView = mView;
        this.mModel = mModel;
        //set listener to seek button
        mView.setListenerOnSeekButton(new ListenerClass());
        //set listener to delete button
        mView.setListenerOnDeleteButton(new ListenerClass());
        //set listener to change location
        mView.setListenerOnLocationButton(new ListenerClass());
        //set listener to cell excel
        mView.setOnJtableRowListener(new TableListListenerClass());

        // mView.
        mView.setMenuClickListener(new ListenerClass());

    }

    class ListenerClass implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == View.getmFirstComponentsPanel().getSeekbutton()) { // boton buscar

                //primero se busca al directorio actual:
                if (mModel.readCurrentDirectory() == null) {
                    try {
                        mModel.saveNewCurrentDirectory(mModel.changeFolderLocation() == null ? mModel.changeFolderLocation() : "");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    mModel.directoriesFinder(mModel.readCurrentDirectory());
                }

                mModel.saveAllFiles(mModel.getAllDirectories());

                try {
                    // mModel.clearData();
                    mModel.searchingInDocument(mView.getTextToSearch(), mModel.getAllFilesPaths());

                    //   if (mModel.isOneOfTheFilesTooBig()) mView.fileIsTooBig();
                    //si no se encuentra nada:
                    if (mModel.getData().size() == 0) mView.noWordsFound();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                //se agrega a tabla
                mView.addNewRow(mModel.getData(), mView.getTextToSearch());

            } else if (e.getSource() == mView.getmFirstComponentsPanel().getSelectLocation()) {  //se cambia el nuevo directorio

                try {
                    mModel.saveNewCurrentDirectory(mModel.changeFolderLocation());
                } catch (IOException ex) {
                    System.out.println("error en gua");
                    ex.printStackTrace();
                }

            } else if (e.getSource() == mView.getmJMenu().getMenuItem1()) {
                System.out.println("Menu click");
            } else if (e.getSource() == mView.getmFirstComponentsPanel().getBorrar()) {
                System.out.println("click borrar");
                mView.clearTable();
            }
        }
    }

    private class TableListListenerClass implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            //System.out.println("jtab clicked: " );
            try {
                //  System.out.println("open docu: " + mModel.getIndexOfRowClicked(mView.getJTable()));
                //  if (e.getValueIsAdjusting() == false) {
                int fileNameClicked = mModel.getIndexOfRowClicked(View.getJTable());
                System.out.println(fileNameClicked);
                System.out.println(mModel.getData().get(fileNameClicked).getDocumentName());
                File fileClicked = new File(mModel.getData().get(fileNameClicked).getDocumentName());

                mModel.OpenWordFile(fileClicked);
                //        }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

    /*private class MouseListenerClass implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            System.out.println("Mouse Clicked");
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }*/
}
