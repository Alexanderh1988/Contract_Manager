import Model.Model;
import View.View;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

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
//        mView.setListenerOnDeleteButton(new ListenerClass());
        //set listener to change location
        mView.setListenerOnExportButton(new ListenerClass());

        mView.setListenerOnExportButton(new ListenerClass());
        //set listener to cell excel
        mView.setOnJtableRowListener(new TableListListenerClass());

        // mView.
        mView.setMenuClickListener(new ListenerClass());


    }

    class ListenerClass implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == mView.getmSecondComponentsPane().getBuscar()) { // boton buscar

                if (mModel.readCurrentDirectory() == null) {
                    System.out.println("no hay directorio");
                    try {
                        mModel.saveNewCurrentDirectory(mModel.changeFolderLocation());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        System.out.println("error4 " + ex);
                    }
                } else {
                    System.out.println("si hay directorio");
                    mModel.directoriesFinder(mModel.readCurrentDirectory());
                }

                mModel.saveAllFiles(mModel.getAllDirectories());

                try {

                    mModel.clearData();
                    mModel.searchingInDocument(mModel.getAllFilesPaths());


                } catch (Exception ex) {
                    System.out.println("exepcion " + ex);
                    ex.printStackTrace();
                }
                //se agrega a tabla
                //mView.addNewRow(mModel.getData(), mView.getTextToSearch());
                //       mView.addNewRow(mModel.getData(), mModel.getTextToFind());
                mView.addNewRow(mModel.getData(), "PALABRA");


            } else if (e.getSource() == mView.getmSecondComponentsPane().getSelectLocation()) {  //se cambia el nuevo directorio

                try {
                    mModel.saveNewCurrentDirectory(mModel.changeFolderLocation());
                } catch (IOException ex) {
                    System.out.println("error en gua");
                    ex.printStackTrace();
                }

            } else if (e.getSource() == mView.getmJMenu().getMenuItem1()) {
                System.out.println("Menu click");
            } else if (e.getSource() == mView.getmSecondComponentsPane().getBorrar()) {
                System.out.println("click borrar");
                mView.clearTable();
            } else if (e.getSource() == mView.getmSecondComponentsPane().getExportar()) {

                new ExcelExport(mModel.readCurrentDirectory()+"\\excel2.xls", mModel.getData());
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
                //     int fileNameClicked = mModel.getIndexOfRowClicked(View.getJTable());
                //    System.out.println(fileNameClicked);
                //    System.out.println(mModel.getData().get(fileNameClicked).getDocumentName());
                //    File fileClicked = new File(mModel.getData().get(fileNameClicked).getDocumentName());

                //     mModel.OpenWordFile(fileClicked);
                //        }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("error3 " + ex);
            }

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


