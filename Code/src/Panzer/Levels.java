import javafx.scene.paint.Color;

//Класс для создания и хранения карт
public class Levels {

    enum LevelNumbers {ONE, TWO;}

    static public Panzer panzer;
    static public Panzer panzer2;


    public static void level(LevelNumbers digit) {
        switch (digit) {
            case ONE:
                panzer = new Panzer(Color.GREEN);
                panzer.setTranslateX(100);
                panzer.setTranslateY(200);
                panzer2 = new Panzer(Color.RED);
                panzer2.setTranslateX(500);
                panzer2.setTranslateY(600);
                panzer.addOpponent(panzer2);
                panzer2.addOpponent(panzer);
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
                panzer = new Panzer(Color.GREEN);
                panzer.setTranslateX(100);
                panzer.setTranslateY(200);
                panzer2 = new Panzer(Color.RED);
                panzer2.setTranslateX(500);
                panzer2.setTranslateY(600);
                panzer.addOpponent(panzer2);
                panzer2.addOpponent(panzer);
                break;

        }


    }
}
