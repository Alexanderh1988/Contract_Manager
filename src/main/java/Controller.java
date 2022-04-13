import Model.Model;
import View.View;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


public class Controller {

    public static View mView;
    public static Model mModel;

    public static void main(String[] args) {
        //new Controller(new View());
    }

    public Controller(View mView, Model mModel) {

        //Menu 1:
        this.mView = mView;
        this.mModel = mModel;

        ListenerClass mListener = new ListenerClass();
        TableListListenerClass mTableListener = new TableListListenerClass();

        //main menu:
        mView.setMenuChequearListener(mListener);
        mView.setMenuBuscarListener(mListener);

        //firstPane:
        mView.setListenerOnSeekButton(mListener);


        //secondPane:
        mView.setListenerOnSeekButton2(mListener);
        //    mView.setListenerOnDeleteButton(new ListenerClass());
        mView.setOnJtableRowListener(mTableListener);


        //transfersal
        mView.setListenerOnExportButton(mListener);
        mView.setLocationChooserListener(mListener);

    }

    public class ListenerClass implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            //listener para page2:
            if (e.getSource() == mView.getmSecondComponentsPane().getSelectLocation()) {  //se cambia el nuevo directorio

                try {
                    mModel.saveNewCurrentDirectory(mModel.changeFolderLocation());
                } catch (IOException ex) {
                    System.out.println("error en gua");
                    ex.printStackTrace();
                }
            } else if (e.getSource() == mView.getmSecondComponentsPane().getBorrar()) {

                System.out.println("click borrar");
                mView.clearTable();

            }
            //listener para page1:
            else                  //menu listeners:
                if (e.getSource() == mView.getmJMenu().getMenuBuscador()) {
                    System.out.println("menu buscador");

                    mView.setMenuItemBuscador();

                } else if (e.getSource() == mView.getmJMenu().getMenuChequeo()) {
                    System.out.println("menu chequeo");
                    mView.setMenuItemChequeo();
                }

                //transversal:
                else if (e.getSource() == mView.getmFirstComponentsPane().getSelectLocation() || e.getSource() == mView.getmSecondComponentsPane().getLocation()) {

                    try {
                        mModel.saveNewCurrentDirectory( mModel.changeFolderLocation());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                } else if (e.getSource() == mView.getmSecondComponentsPane().getExportar()) {
                    new ExcelExport(mModel.readCurrentDirectory() + "\\excel2.xls", mModel.getData());

                } else if (e.getSource() == mView.getmSecondComponentsPane().getSeekbutton2() ||
                        e.getSource() == mView.getmFirstComponentsPane().getSeekbutton()) { // boton buscar

                    Boolean isSecondPane = e.getSource() == mView.getmSecondComponentsPane().getSeekbutton2();

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
                        if (isSecondPane)
                            mModel.searchingInDocument(mModel.getAllFilesPaths(), "check");
                        else
                            mModel.searchingInDocument(mModel.getAllFilesPaths(), mView.getmFirstComponentsPane().getTextField1().getText().trim());
                    } catch (Exception ex) {
                        System.out.println("exepcion " + ex);
                        ex.printStackTrace();
                    }
                    if (isSecondPane)
                        mView.addNewRow2(mModel.getData());
                    else
                        mView.addNewRow(mModel.getData(), mView.getmFirstComponentsPane().getTextField1().getText());

                }
        }
    }

    public class TableListListenerClass implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            //System.out.println("jtab clicked: " );
            try {
                //   System.out.println("open docu: " + mModel.getIndexOfRowClicked(mView.getJTable()));
                //  if (e.getValueIsAdjusting() == false) {
                int fileNameClicked;
                if (mView.getCurrentPane1())
                    fileNameClicked = mModel.getIndexOfRowClicked(mView.getmFirstComponentsPane().getJt());
                else
                    fileNameClicked = mModel.getIndexOfRowClicked(mView.getmSecondComponentsPane().getJt());
                //    System.out.println(fileNameClicked);
                //    System.out.println(mModel.getData().get(fileNameClicked).getDocumentName());
                File fileClicked = new File(mModel.getData().get(fileNameClicked).getDocumentName());

                mModel.OpenWordFile(fileClicked);
                //        }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("error3 " + ex);
            }
        }
    }

}


