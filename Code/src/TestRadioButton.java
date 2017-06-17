import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TestRadioButton extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root=new BorderPane();
        HBox hBox=new HBox();
        hBox.setAlignment(Pos.CENTER);//Обычное выравнивание


        //Создаем группу
        ToggleGroup tgroup=new ToggleGroup();
        RadioButton rb1=new RadioButton("1");
        RadioButton rb2=new RadioButton("2");
        RadioButton rb3=new RadioButton("3");

        //В группе можно выбрать только одну кнопку
        rb1.setToggleGroup(tgroup);
        rb2.setToggleGroup(tgroup);
        rb3.setToggleGroup(tgroup);

        //По этой информации получаем адрес картинки
        rb1.setUserData("1");
        rb2.setUserData("2");
        rb3.setUserData("Schar");


        ImageView imageView=new ImageView();

        tgroup.selectedToggleProperty().addListener(event->{
            Image image=new Image(tgroup.getSelectedToggle().getUserData()+".jpg");
            imageView.setImage(image);
            imageView.setFitWidth(300);
            imageView.setFitHeight(300);
        });


        //Значение кнопки можно задать по умолчанию
        rb2.setSelected(true);


        hBox.getChildren().addAll(rb1,rb2,rb3);
        root.setTop(hBox);
        root.setCenter(imageView);
        Scene scene=new Scene(root,600,600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
