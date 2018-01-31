package AWT.AWTContoller;


import javax.swing.*;
import java.awt.*;

public class AWTGame {

    private JFrame frame;



    public AWTGame() {

        initialize();
    }

    private void initialize() {
        frame = new JFrame("Чистое окно сгенерированное через Eclipse"); // Делаем новое окно с названием
        frame.setBounds(100, 100, 450, 300);
        frame.setSize(800, 800); // Размеры
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AWTGame window = new AWTGame();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }



}
