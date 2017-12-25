import javafx.scene.control.Label;

public class HealthLabel extends Label {
    private GraphicPanzer graphicPanzer;

    HealthLabel(GraphicPanzer graphicPanzer,double x, double y){
        this.graphicPanzer=graphicPanzer;
        setTranslateX(x);
        setTranslateY(y);
        setText(""+graphicPanzer.getLogicPanzer().getHealth());
    }

    void update(){
        setText(""+graphicPanzer.getLogicPanzer().getHealth());
    }
}
