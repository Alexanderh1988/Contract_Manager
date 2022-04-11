package Model;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TableListListenerClass1 implements ListSelectionListener {

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


    }}