import Model.Model;
import View.View;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


public class Controller {

    public View mView;
    public Model mModel;
    private Thread searchThread;

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

        mView.setgetMenuBuscadorIndependienteListener(mListener);

        //firstPane:
        mView.setListenerOnSeekButton(mListener);
        mView.setListenerOnStopButton(mListener);

        //secondPane:
        // mView.setListenerOnSeekButton2(mListener);
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
                    mModel.saveNewCurrentDirectory(mModel.changeFolderLocation(), mView);
                } catch (IOException ex) {
                    System.out.println("error en gua");
                    ex.printStackTrace();
                }
            } else if (e.getSource() == mView.getmSecondComponentsPane().getBorrar()) {
                System.out.println("click borrar");
                mView.clearTable();
            }
            //listener para page1:
            //menu listeners:
            else if (e.getSource() == mView.getmJMenu().getMenuBuscador()) {

                System.out.println("menu buscador");
                mView.setMenuItemBuscador();

            } else if (e.getSource() == mView.getmJMenu().getMenuChequeo()) {

                System.out.println("menu chequeo");
                mView.setMenuItemChequeo();

            } else if (e.getSource() == mView.getmFirstComponentsPane().getStopButton()) {
                //   System.out.println("Stopped thread");
                //    searchThread.interrupt();   //stop deprecated
                searchThread.stop();
            }

            //transversal:
            else if (e.getSource() == mView.getmFirstComponentsPane().getSelectLocation() || e.getSource() == mView.getmSecondComponentsPane().getLocation()) {


                try {
                    mModel.saveNewCurrentDirectory(mModel.changeFolderLocation(), mView);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            } else if (e.getSource() == mView.mFirstComponentsPane.getBtnExportar()) {

                new ExcelExport(mModel.readCurrentDirectory(mView) + "\\excel2.xls", mModel.getData());

            } else if (e.getSource() == mView.getMenuBuscadorIndependiente()) {

                System.out.println("click");

            } else if ( /*e.getSource() == mView.getmSecondComponentsPane().getSeekbutton2() ||*/
                    e.getSource() == mView.getmFirstComponentsPane().getSeekbutton()) { // boton buscar

                  searchThread = new Thread(() -> {  // override the run() for the running behaviors

                    String textToSearch = mView.getmFirstComponentsPane().getTextField1().getText();

                    Boolean isSecondPane = e.getSource() == mView.getmSecondComponentsPane().getSeekbutton2();

                    if (mModel.readCurrentDirectory(mView) == null) {  //set location if there is nothing else

                        try {
                            mModel.saveNewCurrentDirectory(mModel.changeFolderLocation(), mView);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            System.out.println("error4 " + ex);
                        }
                    } else {
                        System.out.println("si hay directorio");
                        if (textToSearch != null)
                            mModel.directoriesFinder(mModel.readCurrentDirectory(mView));
                        // else JOptionPane.showMessageDialog(this,"Eggs are not supposed to be green.");
                    }
                    mModel.saveAllFiles(mModel.getAllDirectories());  //get all files in selected path

                    try {
                        String text1 = mView.getmFirstComponentsPane().getTextField1().getText().trim();
                        String text2 = mView.getmFirstComponentsPane().getTextField2().getText().trim();

                        //boolean check = mView.getmFirstComponentsPane().getCheckbtn().isSelected();

                        mModel.clearData();
                        if (isSecondPane)
                            mModel.searchingInDocument(mModel.getAllFilesPaths(), "check", null, null);
                        else
                            mModel.searchingInDocument(mModel.getAllFilesPaths(), text1, text2, false);

                    } catch (Exception ex) {
                        System.out.println("exepcion " + ex);
                        ex.printStackTrace();
                    }
                    //eliminar
                    //  if (isSecondPane)
                    //      mView.addNewRow2(mModel.getData());
                    //  else

                    if (areThereSynonimum(textToSearch)) {

                        mView.addNewRow(mModel.getData(), textToSearch);

                        //# on develpoment:
                        //  mView.getView1().addNewRow(mModel.getData1(), mModel.setSynonym(1, textToSearch));
                        //   mView.getView2().addNewRow(mModel.getData2(), mModel.setSynonym(2, textToSearch));
                        //    mView.getView3().addNewRow(mModel.getData3(), mModel.setSynonym(3, textToSearch));

                    } else
                        mView.addNewRow(mModel.getData(), mView.getmFirstComponentsPane().getTextField1().getText());

                });
                searchThread.start();


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

    private boolean areThereSynonimum(String textToSearch) {

        //pendiente aca revisar sinonimos para buscar varias instancias
        return true;
    }

    private void setLocation(int x, int y){
        mView.setLocation(   x,y);
    }

    public View getmView() {
        return mView;
    }


}


