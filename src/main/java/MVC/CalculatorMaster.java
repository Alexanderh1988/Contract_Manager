package MVC;

public class CalculatorMaster {

    public static void main(String[] args) {
        ViewSample view1 = new ViewSample();
        ModelSample model1 = new ModelSample();
      //  ControllerSample cntr = new ControllerSample().calculate(model1,view1);
        // ControllerSample cntr = new ControllerSample().calculate();
        ControllerSample cntr = new ControllerSample(view1);
       // ControllerSample cntr = new ControllerSample();
    }
}