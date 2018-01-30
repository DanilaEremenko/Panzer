package Graphic;


import javafx.scene.control.Label;
//TODO
public class HealthLabel extends Label {
    private GraphicPanzer graphicPanzer;

    HealthLabel(GraphicPanzer graphicPanzer,double x, double y){
        this.graphicPanzer=graphicPanzer;
        setTranslateX(x);
        setTranslateY(y);
        setText(""+graphicPanzer.getLogicPanzer().getCurrentHealth());
    }

    void update(){
        setText(""+graphicPanzer.getLogicPanzer().getCurrentHealth());
    }
}
