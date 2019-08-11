package lesson4;

/**
 * Java. Level 2. Lesson 4
 *
 * @author Dzyubenko Vadim
 * @version dated 07.08.2019
 */

import javax.swing.*;

public class MainClass {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyWindow();
            }
        });
    }
}