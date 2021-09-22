package MVC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewSample {

    TextField number1 = new TextField();
    TextField number2 = new TextField();
    TextField resultado = new TextField();
    JButton suma = new JButton("Sumar");

    ViewSample() {
        JPanel pn1 = new JPanel();
        JFrame fr1 = new JFrame();
        fr1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fr1.setSize(300, 300);
        number1.setPreferredSize(new Dimension(50, 20));
        number2.setPreferredSize(new Dimension(50, 20));
        resultado.setPreferredSize(new Dimension(50, 20));
        pn1.add(number1);
        pn1.add(number2);
        pn1.add(resultado);
        pn1.add(suma);
        fr1.add(pn1);
        fr1.setVisible(true);
    }

    public TextField getNumber1() {
        return number1;
    }

    public void setNumber1(TextField number1) {
        this.number1 = number1;
    }

    public TextField getNumber2() {
        return number2;
    }

    public void setNumber2(TextField number2) {
        this.number2 = number2;
    }

    public TextField getResultado() {
        return resultado;
    }

    public void setResultado(Integer resultadoSuma) {
        resultado.setText(String.valueOf(resultadoSuma));
    }

    public JButton getSuma() {
        return suma;
    }

    public void setSuma(JButton suma) {
        this.suma = suma;
    }

    public void addSumaListener(ActionListener sumaClickListener) {
        suma.addActionListener(sumaClickListener);
    }
}