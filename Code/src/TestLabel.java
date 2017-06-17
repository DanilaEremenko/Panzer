import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Label;


public class TestLabel extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();

        Label label = new Label();
        label.setText("Это метка");
        Image image = new Image(getClass().getResourceAsStream("1.jpg"));//Загружаем изображение
        ImageView imageView = new ImageView(image);//Класс для передачи картинки в метку
        imageView.setFitHeight(200);//Высота картинки на сцене
        imageView.setFitWidth(150);//Ширина картинки на сцене

        Label labelImage = new Label();//Новая метка которая будет хранить картинку
        labelImage.setGraphic(imageView);//Передаем картинку в метку
        labelImage.setTranslateX(200);//Задаем положение картинки
        labelImage.setTranslateY(150);
        labelImage.setText("Amazing");//Текст картинки
        labelImage.setGraphicTextGap(10);//Расстояние между картинкой и текстом
        labelImage.setContentDisplay(ContentDisplay.TOP);//Положение картинки относительно текста

        //Метка которая будет реагировать на наведение
        Label labelMagic = new Label("Реагирует на курсор");
        labelMagic.setTranslateX(220);
        labelMagic.setTranslateY(100);

        //Обработчик событий
        //Реакиця при наведение курсора
        labelMagic.setOnMouseEntered(event ->
        {
            labelMagic.setScaleX(1.5);
            labelMagic.setScaleY(1.5);
            labelMagic.setTextFill(Color.RED);


        });
        //Реакция после отведения курсора
        labelMagic.setOnMouseExited(event ->
        {
            labelMagic.setScaleX(1);
            labelMagic.setScaleY(1);
            labelMagic.setTextFill(Color.BLACK);
        });


        Scene scene = new Scene(root, 600, 600);

        root.getChildren().addAll(label, labelImage, labelMagic);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

