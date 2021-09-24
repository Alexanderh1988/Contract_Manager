
import Model.Model;
import View.View;

import java.io.IOException;

public class ContractMaster {

    public static void main(String[] args) throws IOException {


        //Model model = new Model();
        //View view = new View();
        //  ControllerSample cntr = new ControllerSample().calculate(model1,view1);
        // ControllerSample cntr = new ControllerSample().calculate();
        //Controller mController = new Controller(new View());
        // ControllerSample cntr = new ControllerSample();

        new Controller(new View(), new Model());
    }
}
