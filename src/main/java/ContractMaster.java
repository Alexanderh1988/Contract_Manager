import MVC.ControllerSample;
import Model.Model;
import View.View;

public class ContractMaster {

    public static void main(String[] args) {


        //Model model = new Model();
        //View view = new View();
        //  ControllerSample cntr = new ControllerSample().calculate(model1,view1);
        // ControllerSample cntr = new ControllerSample().calculate();
        //Controller mController = new Controller(new View());
        // ControllerSample cntr = new ControllerSample();

        new Controller(new View());
    }
}
