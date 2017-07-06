import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.*;
import java.util.ArrayList;


//Класс для создания и хранения карт
public class Levels {


    //[0] Для колличества танков, вертикальных ограждений и горизонтальных ограждений
    //[1] Для координат танков,сначала X и Y чередуются allDigits[0].get[0]-число танков
    //[2] Для координат вертикальных препятствий X и Y чередуются allDigits[0].get[1]-число вертикальных препятствий
    //[3] Для координат горизонтальных препятствий X и Y чередуются allDigits[0].get[2]-число горизонтальных препятствий
    //[4] Для рамки либо 1 либо 0
    private ArrayList<Integer>[] allDigits = new ArrayList[5];
    private ArrayList<Panzer> panzers = new ArrayList<>();
    private ArrayList<PanzerElement> elements = new ArrayList<>();
    private Scene scene;
    private Scene menu;
    private Pane root;

    public Levels(String fileName) throws IOException {
        FileReader fileReader = new FileReader(new File(fileName));
        for (int i = 0; i < allDigits.length; i++)
            allDigits[i] = new ArrayList<Integer>();
        int c;
        int digit = 0;
        int i = 0;//Идентификатор листа который заполняем
        boolean pars = false;
        while ((c = fileReader.read()) != -1) {
            while (pars) {
                if ((char) c == '>') {
                    allDigits[i].add(digit);
                    pars = false;
                    digit = 0;
                    break;
                }
                digit = digit * 10 + Character.getNumericValue(c);
                c = fileReader.read();
            }
            if ((char) c == '<')
                pars = true;

            if ((char) c == '|')
                i++;


        }


        int numberCoordinate = 0;
        for (i = 0; i < allDigits[0].get(0); i++) {
            panzers.add(new Panzer(Color.GREEN, allDigits[1].get(numberCoordinate), allDigits[1].get(numberCoordinate + 1), this));
            numberCoordinate += 2;

        }
        for (Panzer panzer : panzers) {
            panzer.addOpponents(panzers);

        }
        numberCoordinate = 0;
        for (i = 0; i < allDigits[0].get(1); i++) {
            elements.add(PanzerElement.generateVertical(100, allDigits[2].get(numberCoordinate), allDigits[2].get(numberCoordinate + 1)));
            numberCoordinate += 2;
        }
        numberCoordinate = 0;
        for (i = 0; i < allDigits[0].get(2); i++) {
            elements.add(PanzerElement.generateGorizontal(100, allDigits[3].get(numberCoordinate), allDigits[3].get(numberCoordinate + 1)));
            numberCoordinate += 2;
        }

        if (allDigits[4].get(0) == 1){
            elements.add(PanzerElement.getGorizontal());
            elements.add(PanzerElement.getGorizontal2());
            elements.add(PanzerElement.getVertical());
            elements.add(PanzerElement.getVertical2());
        }




        root = new Pane();

        for (i = 0; i < Panzer.getBulletDigit(); i++)
            for (Panzer panzer : panzers)
                root.getChildren().addAll(panzer.getBullets().get(i));

        for (PanzerElement element : elements)
            root.getChildren().addAll(element);

        for (Panzer panzer : panzers)
            root.getChildren().addAll(panzer);


        scene = new Scene(root, PanzerGame.sceneWidt, PanzerGame.sceneHeight);
        menu = new Scene(PanzerMenu.menuBox, PanzerGame.sceneWidt, PanzerGame.sceneHeight);


    }

    public Scene getMenu() {
        return menu;
    }

    public Scene getScene() {
        return scene;
    }

    public ArrayList<PanzerElement> getElements() {
        return elements;
    }

    public ArrayList<Panzer> getPanzers() {
        return panzers;
    }


}
