import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class TestToggleButton extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);

        ToggleGroup group = new ToggleGroup();

        //
        ToggleButton tb1 = new ToggleButton("Green");
        ToggleButton tb2 = new ToggleButton("Blue");
        ToggleButton tb3 = new ToggleButton("Red");

        tb1.setToggleGroup(group);
        tb2.setToggleGroup(group);
        tb3.setToggleGroup(group);

        tb1.setUserData(Color.GREEN);
        tb2.setUserData(Color.BLUE);
        tb3.setUserData(Color.RED);


        //
        Rectangle rect = new Rectangle(300, 300);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLACK);//Цвет рамки прямоугольника
        rect.setStrokeWidth(2);//Ширина рамки
        rect.setArcHeight(50);//??
        rect.setArcWidth(50); //??

        //Действие при выборе ToggleButton
        group.selectedToggleProperty().addListener(event -> {
            if (group.getSelectedToggle()!= null)
                rect.setFill((Color) group.getSelectedToggle().getUserData());
            else {
                rect.setFill(Color.WHITE);
            }
        });


        buttons.getChildren().addAll(tb1, tb2, tb3);
        root.setTop(buttons);//Устанавливаем элементы в BorderPane
        root.setCenter(rect);
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
