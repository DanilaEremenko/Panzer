import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.geometry.Pos;
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
import javafx.util.Duration;

public class PanzerMenu {

    static MenuItem newGame=new MenuItem("Battle");
    static MenuItem options=new MenuItem("Options");
    static MenuItem exit=new MenuItem("Exit");
    static SubMenu mainMenu=new SubMenu(newGame,options,exit);
    static MenuBox menuBox=new MenuBox(mainMenu);







    //Класс иконок меню
    public static class MenuItem extends StackPane {
        public MenuItem(String name) {
            Rectangle bg = new Rectangle(300, 40, Color.GREEN);
            bg.setOpacity(0.5);//Прозрачность прямоугольника

            Text text = new Text(name);
            text.setFill(Color.RED);
            text.setFont(Font.font("Arial", FontWeight.BOLD, 14));//Шрифт,жирность шрифта,размер шрифта

            setAlignment(Pos.CENTER);//Не указываем this
            getChildren().addAll(bg, text);//В пункт меню добавляем прямоугольник и текст
            //Далее создаем анимацию
            //FillTransition st = new FillTransition(Duration.seconds(0.5), bg);//За какое время будет происходить анимация и на каком объекте

            //Анимация при наведении
            setOnMouseEntered(event -> {
                setScaleX(1.5);
                setScaleY(1.5);
                //st.setFromValue(Color.BLACK);
                //st.setToValue(Color.RED);
                //st.setCycleCount(Animation.INDEFINITE);//Цикл анимации,в данном случае происходит бесконечно

            });

            //После того как сняли курсор
            setOnMouseExited(event -> {
                setScaleX(1);
                setScaleY(1);
                //    st.stop();
                // bg.setFill(Color.BLACK);
            });


        }

    }

    //Класс хранящий икнонки
    public static class SubMenu extends VBox {
        public SubMenu(MenuItem... items) {
            setSpacing(20);//Отступы между элементами меню
            setTranslateY(250);
            setTranslateX(280);
            for (MenuItem item : items) {
                getChildren().addAll(item);

            }
            setVisible(true);
        }
    }

    //Класс хранящий SubMen и меняющий их
    public static class MenuBox extends Pane {
        static SubMenu subMenu;
        MenuItem newGame = new MenuItem("НОВАЯ ИГРА");
        MenuItem options = new MenuItem("НАСТРОЙКИ");
        MenuItem exitGame = new MenuItem("ВЫХОД");
        SubMenu mainMenu = new SubMenu(newGame, options, exitGame);



        public MenuBox(SubMenu subMenu) {
            ImageView imageView=new ImageView(new Image("menu.png"));
            imageView.setFitHeight(PanzerGame.sceneHeight);
            imageView.setFitWidth(PanzerGame.sceneWidt);
            MenuBox.subMenu = subMenu;
            setVisible(true);
            subMenu.setAlignment(Pos.TOP_CENTER);
            getChildren().addAll(imageView,subMenu);

        }

        //Метод для перехода из одного меню в другое
        public void setSubMenu(SubMenu subMenu) {
            getChildren().remove(MenuBox.subMenu);
            MenuBox.subMenu = subMenu;
            getChildren().add(MenuBox.subMenu);

        }

        public void setRoot(Pane pane) {
            getChildren().remove(MenuBox.subMenu);

        }

    }



}
