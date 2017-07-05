import javafx.scene.paint.Color;

import java.util.ArrayList;


//Класс для создания и хранения карт
public class Levels {

    enum LevelNumbers {ONE, TWO;}

    static ArrayList<Panzer>panzers=new ArrayList<>();

    public static void level(LevelNumbers digit) {
        switch (digit) {
            case ONE:
                panzers.add(new Panzer(Color.GREEN));
                panzers.add(new Panzer(Color.RED));
                panzers.get(0).setTranslateX(100);
                panzers.get(0).setTranslateY(200);
                panzers.get(1).setTranslateX(500);
                panzers.get(1).setTranslateY(600);
                panzers.get(0).addOpponent(panzers.get(1));
                panzers.get(1).addOpponent(panzers.get(0));

                Panzer.elements.add(PanzerElement.generateVertical(100));
                Panzer.elements.add(PanzerElement.generateGorizontal(100));
                Panzer.elements.add(PanzerElement.generateVertical(100));
                Panzer.elements.add(PanzerElement.generateGorizontal(100));
                Panzer.elements.add(PanzerElement.generateVertical(100));
                Panzer.elements.add(PanzerElement.generateGorizontal(100));
                Panzer.elements.add(PanzerElement.generateVertical(100));
                Panzer.elements.add(PanzerElement.generateGorizontal(100));
                Panzer.elements.get(0).setTranslateX(250);//вертикальная
                Panzer.elements.get(0).setTranslateY(130);
                Panzer.elements.get(1).setTranslateX(150);//горизонтальная
                Panzer.elements.get(1).setTranslateY(230);
                Panzer.elements.get(2).setTranslateX(500);//вертикальная
                Panzer.elements.get(2).setTranslateY(130);
                Panzer.elements.get(3).setTranslateX(520);//горизонтальная
                Panzer.elements.get(3).setTranslateY(230);
                Panzer.elements.get(4).setTranslateX(500);//вертикальная
                Panzer.elements.get(4).setTranslateY(390);
                Panzer.elements.get(5).setTranslateX(520);//горизонтальная
                Panzer.elements.get(5).setTranslateY(370);
                Panzer.elements.get(6).setTranslateX(250);//вертикальная
                Panzer.elements.get(6).setTranslateY(390);
                Panzer.elements.get(7).setTranslateX(150);//горизонтальная
                Panzer.elements.get(7).setTranslateY(370);
                //Рамка
                PanzerElement.gorizontal.setTranslateY(PanzerGame.sceneHeight - 20);
                PanzerElement.vertical.setTranslateX(PanzerGame.sceneWidt - 20);
                Panzer.elements.add(PanzerElement.vertical);
                Panzer.elements.add(PanzerElement.vertical2);
                Panzer.elements.add(PanzerElement.gorizontal);
                Panzer.elements.add(PanzerElement.gorizontal2);
                break;

            case TWO:
                panzers.add(new Panzer(Color.GREEN));
                panzers.add(new Panzer(Color.RED));
                panzers.get(0).setTranslateX(100);
                panzers.get(0).setTranslateY(200);
                panzers.get(1).setTranslateX(500);
                panzers.get(1).setTranslateY(600);
                panzers.get(0).addOpponent(panzers.get(1));
                panzers.get(1).addOpponent(panzers.get(0));

                break;

        }


    }
}
