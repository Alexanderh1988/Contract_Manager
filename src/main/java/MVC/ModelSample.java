package MVC;

public class ModelSample {

    private int result;
    public int calculate (int number1, int number2){
        result = number1+number2;
        return result;
    }
    public ModelSample() {
    }
    public int getResult() {
        return result;
    }
}
