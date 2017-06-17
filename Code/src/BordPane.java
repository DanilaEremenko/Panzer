import com.sun.javafx.font.freetype.HBGlyphLayout;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BordPane extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane borderPane = new BorderPane();
        VBox vBox = new VBox(10);//в скобках указан отступ между элементами
        HBox hBox = new HBox(10);
        HBox hBox2 = new HBox(10);


        hBox.setAlignment(Pos.CENTER);//Выравнивание(по центру)
        hBox2.setAlignment(Pos.CENTER);
        vBox.setAlignment(Pos.CENTER);

        Button btn1 = addButton("1");
        Button btn2 = addButton("2");
        Button btn3 = addButton("3");
        Button btn4 = addButton("4");

        Button btn5 = addButton("5");
        Button btn6 = addButton("6");
        Button btn7 = addButton("7");
        Button btn8 = addButton("8");

        Button btn11 = addButton("1");
        Button btn22 = addButton("2");
        Button btn33 = addButton("3");
        Button btn44 = addButton("4");


        vBox.getChildren().addAll(btn11, btn22, btn33, btn44);
        hBox.getChildren().addAll(btn1, btn2, btn3, btn4);
        hBox2.getChildren().addAll(btn5, btn6, btn7, btn8);

        borderPane.setLeft(vBox);
        borderPane.setTop(hBox);
        borderPane.setBottom(hBox2);


        Scene scene = new Scene(borderPane, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    //Метод для создания кнопок
    private Button addButton(String nameofButton) {

        Button btn = new Button(nameofButton);
        btn.setPrefSize(70, 20);
        return btn;

    }


    public static void main(String[] args) {
        launch(args);
    }
}


