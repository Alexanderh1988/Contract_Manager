package Model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;


public class OpenDocExample {

    public static void main(String[] args) throws AWTException {

        File file = new File("D:\\Software_hstech\\Contract_Manager\\articles-97403_Teatro.docx");

        try {

            //Open the file using Desktop class

            Desktop.getDesktop().open(file);


            Robot r2 = new Robot();

            r2.keyPress(KeyEvent.VK_CONTROL);
            r2.keyPress(KeyEvent.VK_B);
            r2.keyRelease(KeyEvent.VK_B);
            r2.keyRelease(KeyEvent.VK_CONTROL);


            //robot controller y bucar texto

        } catch (IOException exception) {

            exception.printStackTrace();

        }

    }


}
