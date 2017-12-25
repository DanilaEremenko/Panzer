import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

//Графическое отображение танка
public class GraphicPanzer extends Pane {
    private LogicPanzer logicPanzer;//Танк, который отрисовывается
    private GraphicBullet[] bullets;//Массив графических пуль
    private Level level;
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


    public GraphicPanzer(LogicPanzer logicPanzer, Color color, Level level) {
        this.level = level;
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
        Rectangle body = new Rectangle(logicPanzer.getWidthBody(), logicPanzer.getHightBody(), color);
        gun.setTranslateX(body.getTranslateX() + logicPanzer.getWidthBody());
        gun.setTranslateY(body.getTranslateY() + (logicPanzer.getWidthBody() - logicPanzer.getHightGun()) / 2);
        getChildren().addAll(body, gun);


    }

//____________________________________________________________________________________________________________________________________
    //Д
    //В
    //И
    //Ж
    //Е
    //Н
    //И
    //E


    //Постоянно работающий метод движения танка
    public void move() {

        transformPanzer();
        setTranslateX(logicPanzer.getTranslateX());
        setTranslateY(logicPanzer.getTranslateY());
        checkArea();


    }

    //Проверка того, что танк может ехать
    private void checkArea() {
        for (PanzerElement element : level.getElements())
            if (getBoundsInParent().intersects(element.getBoundsInParent()))
                logicPanzer.comeBackIfCanNotMove();

        for (GraphicPanzer graphicPanzer : level.getGraphicPanzers())
            if (getBoundsInParent().intersects(graphicPanzer.getBoundsInParent()) && graphicPanzer != this)
                logicPanzer.comeBackIfCanNotMove();
    }


    //Метод поворачивающий танк
    private void transformPanzer() {

            getTransforms().add(new Rotate(logicPanzer.getAngleOfTurn(),
                    logicPanzer.getWidthBody() / 2,
                    logicPanzer.getHightBody() / 2));
            logicPanzer.setAngleOfTurn(0);

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
//____________________________________________________________________________________________________________________________________
}


