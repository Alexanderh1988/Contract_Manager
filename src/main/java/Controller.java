import Model.Model;
import View.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Controller {

    View mView;
    Model mModel;

    public static void main(String[] args) {
        //new Controller(new View());
    }

    public Controller(View mView, Model mModel) {

        this.mView = mView;
        this.mModel = mModel;
        mView.setListenerOnButton(new ListenerClass());


    }

    class ListenerClass implements ActionListener, KeyListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("clock");

            try {
                mModel.searchingInDocument(mView.getTextFieldFromComponents());
                if (mModel.getData().size()==0) mView.noWordsFound();
            } catch (IOException ex) {
                ex.printStackTrace();
                ex.printStackTrace();
            }
            mView.addNewRow(mModel.getData());


        }

        @Override
        public void keyTyped(KeyEvent e) {
            System.out.println(e);

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                System.out.println("Hello enter ¡");
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println();
        }
    }
}
