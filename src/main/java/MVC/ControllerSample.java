package MVC;

import View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerSample {

    //ModelSample model;
    //ViewSample view;

    //public ControllerSample calculate() {
    public ControllerSample(ViewSample view) {

        //this.model = model;
        //this.view = view ;

        //ViewSample view = new ViewSample();
        view.addSumaListener(new CalculationListener());
//        return null;
    }

/*    public ControllerSample calculate(ModelSample model, ViewSample view) {
        this.model = model;
        this.view = view;
        view.addSumaListener(new CalculationListener());
        return null;
    }*/

    private class CalculationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // int num1 = Integer.parseInt(view.getNumber1().getText());
            // int num2 = Integer.parseInt(view.getNumber2().getText());
            // model.calculate(num1,num2);
            // view.setResultado(model.getResult());
            System.out.println("hola");
        }
    }
}

