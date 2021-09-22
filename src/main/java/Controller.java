import MVC.ControllerSample;
import MVC.ModelSample;
import MVC.ViewSample;
import Model.Model;
import View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    public static void main(String[] args) {
        new Controller(new View());
    }

    public Controller(View mView) {

        mView.setListenerOnButton(new ListenerClass());
    }

    class ListenerClass implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("clock");
        }
    }
}
