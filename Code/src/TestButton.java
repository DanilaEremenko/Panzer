import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TestButton extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();


        Button btn = new Button("Выход");
        btn.setTranslateY(30);
        btn.setTranslateX(200);

        ImageView imageView = new ImageView(new Image("Schar.jpg"));
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);

        btn.setGraphic(imageView);//Устанавливаем картинку в кнопку
        //Действие при наведение курсора
        btn.setOnMouseEntered(event -> {
            btn.setScaleX(2);
            btn.setScaleY(2);
        });
        //Действие при отведении курсора
        btn.setOnMouseExited(event -> {
            btn.setScaleX(1);
            btn.setScaleY(1);
        });

        //Действие при нажатии на кнопку
        btn.setOnMouseClicked(event -> {
            primaryStage.close();
        });


        root.getChildren().addAll(btn);
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
