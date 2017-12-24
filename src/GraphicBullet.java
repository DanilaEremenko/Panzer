import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



//Класс для создания пуль и управления их траекторией
public class GraphicBullet extends Pane {
    private LogicBullet logicBullet;
//____________________________________________________________________________________________________________________________________
    //К
    //О
    //Н
    //С
    //Т
    //Р
    //У
    //К
    //Т
    //О
    //Р


    public GraphicBullet(LogicBullet logicBullet) {
        this.logicBullet = logicBullet;
        makeBody();
        setTranslateX(logicBullet.getTranslateX());
        setTranslateY(logicBullet.getTranslateY());
    }


    private void makeBody() {
        Rectangle rectangle = new Rectangle(logicBullet.getSize(), logicBullet.getSize(), Color.BLACK);
        rectangle.setArcHeight(50);
        rectangle.setArcWidth(50);
        getChildren().addAll(rectangle);

    }
//____________________________________________________________________________________________________________________________________

    //М
    //Е
    //Т
    //О
    //Д
    //Ы

    //Д
    //Л
    //Я

    //Д
    //В
    //И
    //Ж
    //Е
    //Н
    //И
    //Я

    public void move() {
        setTranslateX(logicBullet.getTranslateX());
        setTranslateY(logicBullet.getTranslateY());
//        for (GraphicPanzer opponent : myGraphicPanzer.getOpponents()) {
//
//            if (getBoundsInParent().intersects(opponent.getBoundsInParent())) {
//                opponent.setHealth(opponent.getHealth() - 1);
//                setTranslateX(-15);
//                setTranslateY(-15);
//                vector = PanzerDirection.STOP;
//            }
//        }
//
//        for (PanzerElement element : myGraphicPanzer.getMyLevel().getElements())
//            if (getBoundsInParent().intersects(element.getBoundsInParent())) {
//                setTranslateX(-15);
//                setTranslateY(-15);
//                vector = PanzerDirection.STOP;
//            }
//        //Такое
//        if (getTranslateX() > PanzerGame.sceneWidt + 20)
//            setTranslateX(1);
//        if (getTranslateX() < -20)
//            setTranslateX(PanzerGame.sceneWidt - 20);
//        if (getTranslateY() < -20)
//            setTranslateY(PanzerGame.sceneHeight - 20);
//        if (getTranslateY() > PanzerGame.sceneHeight + 20)
//            setTranslateY(1);
//
//

    }

//____________________________________________________________________________________________________________________________________

}


