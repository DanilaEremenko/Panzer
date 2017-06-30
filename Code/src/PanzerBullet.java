import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//Класс для создания пуль и управления их траекторией
public class PanzerBullet extends Pane {
    private int speed = 15; //Скорость полета пули
    private PanzerDirection vector = PanzerDirection.STOP;//Направление движения пули

    public PanzerBullet() {
        Rectangle bullet = new Rectangle(14, 14, Color.BLACK);
        setTranslateX(-15);
        setTranslateY(-15);
        getChildren().addAll(bullet);

    }

    public void move(Panzer vrag) {
        if (vector == PanzerDirection.STOP) {
            setTranslateY(-15);
            setTranslateX(-15);
        } else if (vector == PanzerDirection.R)
            setTranslateX(getTranslateX() + speed);
        else if (vector == PanzerDirection.L)
            setTranslateX(getTranslateX() - speed);
        else if (vector == PanzerDirection.D)
            setTranslateY(getTranslateY() + speed);
        else if (vector == PanzerDirection.U)
            setTranslateY(getTranslateY() - speed);
        if (getBoundsInParent().intersects(vrag.getBoundsInParent())) {
            vrag.setHealth(vrag.getHealth()-1);
            vector = PanzerDirection.STOP;
        }

        for (PanzerElement element : Panzer.elements)
            if (getBoundsInParent().intersects(element.getBoundsInParent())) {
                setTranslateX(-15);
                setTranslateY(-15);
                vector= PanzerDirection.STOP;
            }
        //Такое
        if (getTranslateX() > PanzerGame.sceneWidt + 20)
            setTranslateX(1);
        if (getTranslateX() < -20)
            setTranslateX(PanzerGame.sceneWidt - 20);
        if (getTranslateY() < -20)
            setTranslateY(PanzerGame.sceneHeight - 20);
        if (getTranslateY() > PanzerGame.sceneHeight + 20)
            setTranslateY(1);

    }

    public void setVector(PanzerDirection vector){
        this.vector=vector;
    }

    public PanzerDirection getVector(){
        return vector;
    }


}


