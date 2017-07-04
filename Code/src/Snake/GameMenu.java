import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class GameMenu extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        ImageView image = new ImageView(new Image("3.jpg"));
        image.setFitHeight(600);
        image.setFitWidth(900);
        root.getChildren().addAll(image);


        Text text = new Text("НАЖМИТЕ ПРОБЕЛ");
        text.setFill(Color.WHITE);
        text.setX(360);
        text.setY(300);
        text.setScaleY(3);
        text.setScaleX(3);


        MenuItem newGame = new MenuItem("НОВАЯ ИГРА");
        MenuItem options = new MenuItem("НАСТРОЙКИ");
        MenuItem exitGame = new MenuItem("ВЫХОД");
        SubMenu mainMenu = new SubMenu(newGame, options, exitGame);

        //Настройки пока что не реализованы
        MenuItem sound = new MenuItem("ЗВУК");
        MenuItem video = new MenuItem("ВИДЕО");
        MenuItem keys = new MenuItem("УПРАВЛЕНИЕ");
        MenuItem optionsBack = new MenuItem("НАЗАД");
        SubMenu optionsMenu = new SubMenu(sound, video, keys, optionsBack);


        MenuBox menuuBox = new MenuBox(mainMenu);


        //НАПИСАТЬ СОБЫТИЕ ДЛЯ КНОПКИ НОВАЯ ИГРА
        newGame.setOnMouseClicked(event -> {

        });
        options.setOnMouseClicked(event -> menuuBox.setSubMenu(optionsMenu));
        exitGame.setOnMouseClicked(event -> primaryStage.close());
        optionsBack.setOnMouseClicked(event -> menuuBox.setSubMenu(mainMenu));


        root.getChildren().addAll(text);
        Scene scene = new Scene(root, 900, 600);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                text.setVisible(false);
                root.getChildren().addAll(menuuBox);
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("ИГРА");
        primaryStage.show();
    }

    //Класс иконок меню
    private static class MenuItem extends StackPane {
        public MenuItem(String name) {
            Rectangle bg = new Rectangle(300, 40, Color.WHITE);
            bg.setOpacity(0.5);//Прозрачность прямоугольника

            Text text = new Text(name);
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Arial", FontWeight.BOLD, 14));//Шрифт,жирность шрифта,размер шрифта

            setAlignment(Pos.CENTER);//Не указываем this
            getChildren().addAll(bg, text);//В пункт меню добавляем прямоугольник и текст
            //Далее создаем анимацию
            FillTransition st = new FillTransition(Duration.seconds(0.5), bg);//За какое время будет происходить анимация и на каком объекте

            //Анимация при наведении
            setOnMouseEntered(event -> {
                setScaleX(1.5);
                setScaleY(1.5);
                st.setFromValue(Color.BLACK);
                st.setToValue(Color.RED);
                st.setCycleCount(Animation.INDEFINITE);//Цикл анимации,в данном случае происходит бесконечно

            });

            //После того как сняли курсор
            setOnMouseExited(event -> {
                setScaleX(1);
                setScaleY(1);
                st.stop();
                bg.setFill(Color.WHITE);
            });


        }

    }

    //Класс хранящий икнонки
    private static class SubMenu extends VBox {
        public SubMenu(MenuItem... items) {
            setSpacing(20);//Отступы между элементами меню
            setTranslateY(250);
            setTranslateX(280);
            for (MenuItem item : items) {
                getChildren().addAll(item);

            }
        }
    }

    //Класс хранящий SubMen и меняющий их
    public static class MenuBox extends Pane {
        static SubMenu subMenu;

        public MenuBox(SubMenu subMenu) {
            MenuBox.subMenu = subMenu;
            //setVisible(false);//Когда открываем приложению меню не отбражено
            //Rectangle bg = new Rectangle(900, 600, Color.LIGHTBLUE);
            //bg.setOpacity(0.4);//Прозрачность
            subMenu.setAlignment(Pos.TOP_CENTER);
            getChildren().addAll(subMenu);

        }

        //Метод для перехода из одного меню в другое
        public void setSubMenu(SubMenu subMenu) {
            getChildren().remove(MenuBox.subMenu);
            MenuBox.subMenu = subMenu;
            getChildren().add(MenuBox.subMenu);

        }
        public void setRoot(Pane pane){
            getChildren().remove(MenuBox.subMenu);

        }

    }

    public static void main(String[] args) {
        launch(args);
    }


}


