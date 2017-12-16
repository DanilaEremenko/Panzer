import java.util.ArrayList;

public class LogicPanzer {
    private int hightBody = 30;//Высота тела
    private int widthBody = 30;//Ширина тела
    private int widthGun = 20;//Ширина пушки
    private int hightGun = 14;//Высота пушки
    private static double scaley = 1;//Должно хранится в классе Levels
    private double speed = 4 * scaley;//скорость передвжиения танков
    private static int bulletDigit = 30;//Колличество пуль у каждого танка
    private int numberofBullet = 0;//Пуля на очереди
    private int health = 2;//Здоровье танка
    private ArrayList<LogicPanzer> opponents = new ArrayList<>();//Соперник
    private PanzerDirection vector = PanzerDirection.R;
    private Levels myLevel;


    public LogicPanzer(Levels level) {
        myLevel = level;

    }

    void takeDamage(int damage) {
        health -= damage;
    }


    public int getHightBody() {
        return hightBody;
    }

    public int getWidthBody() {
        return widthBody;
    }

    public int getWidthGun() {
        return widthGun;
    }

    public int getHightGun() {
        return hightGun;
    }
}
