import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

//Для для создания и управления танками
public class GraphicPanzer extends Pane {
    private LogicPanzer logicPanzer;
    private GraphicBullet[] bullets;
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


    public GraphicPanzer(LogicPanzer logicPanzer, Color color) {
        this.logicPanzer = logicPanzer;
        makeBody(color);
        setTranslateX(logicPanzer.getTranslateX());
        setTranslateY(logicPanzer.getTranslateY());

        bullets = new GraphicBullet[logicPanzer.getBullets().length];
        for (int i = 0; i < bullets.length; i++)
            bullets[i] = new GraphicBullet(logicPanzer.getBullets()[i]);


    }

    private void makeBody(Color color) {
        Rectangle gun = new Rectangle(logicPanzer.getWidthGun(), logicPanzer.getHightGun(), Color.BLACK);
        Rectangle body = new Rectangle(logicPanzer.getWidthGun(), logicPanzer.getHightBody(), color);
        gun.setTranslateX(body.getTranslateX() + logicPanzer.getWidthBody());
        gun.setTranslateY(body.getTranslateY() + (logicPanzer.getWidthBody() - logicPanzer.getHightGun()) / 2);
        getChildren().addAll(body, gun);


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


    //Постоянно работающий метод движения танка
    public void move() {
        setVector(logicPanzer.getVector());
        setTranslateX(logicPanzer.getTranslateX());
        setTranslateY(logicPanzer.getTranslateY());

    }


    //Метод поворачивающий танк
    private void setVector(PanzerDirection vector) {
        getTransforms().add(new Rotate(logicPanzer.getVector().getAngle() - vector.getAngle(),
                logicPanzer.getWidthBody() / 2,
                logicPanzer.getHightBody() / 2));
        logicPanzer.setVector(vector);
    }
//____________________________________________________________________________________________________________________________________

    //Г
    //Е
    //Т
    //Е
    //Р
    //Ы

    public LogicPanzer getLogicPanzer() {
        return logicPanzer;
    }

    public GraphicBullet[] getBullets() {
        return bullets;
    }
}


