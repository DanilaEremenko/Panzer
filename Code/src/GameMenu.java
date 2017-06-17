import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

        MenuItem newGame = new MenuItem("НОВАЯ ИГРА");
        MenuItem options = new MenuItem("НАСТРОЙКИ");
        MenuItem exitGame = new MenuItem("ВЫХОД");
        SubMenu mainMenu = new SubMenu(newGame, options, exitGame);

        MenuItem Sound = new MenuItem("ЗВУК");
        MenuItem Video = new MenuItem("ВИДЕО");
        MenuItem keys = new MenuItem("УПРАВЛЕНИЕ");
        MenuItem optionsBack = new MenuItem("НАЗАД");
        SubMenu optionsMenu = new SubMenu(Sound, Video, keys, optionsBack);


        MenuBox menuBox = new MenuBox(mainMenu);

        options.setOnMouseClicked(event -> menuBox.setSubMenu(optionsMenu));


        root.getChildren().addAll(menuBox);
        Scene scene = new Scene(root,900,600);


        primaryStage.setScene(scene);
        primaryStage.setTitle("Название игры");
        primaryStage.show();
    }

    //Класс иконок меню
    private static class MenuItem extends StackPane {
        public MenuItem(String name) {
            Rectangle bg = new Rectangle(200, 20, Color.WHITE);
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
                st.setFromValue(Color.BLACK);
                st.setToValue(Color.RED);
                st.setCycleCount(Animation.INDEFINITE);//Цикл анимации,в данном случае происходит бесконечно


            });

            //После того как сняли курсор
            setOnMouseExited(event -> {
                st.stop();
                bg.setFill(Color.WHITE);
            });

        }

    }

    private static class MenuBox extends Pane {
        static SubMenu subMenu;

        public MenuBox(SubMenu subMenu) {
            MenuBox.subMenu = subMenu;
            setVisible(false);//Когда открываем приложению меню не отбражено
            Rectangle bg = new Rectangle(900, 600, Color.LIGHTBLUE);
            bg.setOpacity(0.4);//Прозрачность
            getChildren().addAll(bg, subMenu);

        }

        //Метод для перехода из одного меню в другое
        public void setSubMenu(SubMenu subMenu) {
            getChildren().remove(MenuBox.subMenu);
            MenuBox.subMenu = subMenu;
            getChildren().add(MenuBox.subMenu);

        }

    }


    private static class SubMenu extends VBox {
        public SubMenu(MenuItem... items) {
            setSpacing(15);//Отступы между элементами меню
            setTranslateY(100);
            setTranslateX(50);
            for (MenuItem item : items) {
                getChildren().addAll(item);

            }
        }
    }



}


