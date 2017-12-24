import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LogicPanzer {
    private double translateX;
    private double translateY;
    private int hightBody = 30;//Высота тела
    private int widthBody = 30;//Ширина тела
    private int widthGun = 20;//Ширина пушки
    private int hightGun = 14;//Высота пушки
    private double speed;//скорость передвжиения танков
    private LogicBullet bullets[];//Массив пуль
    private int health;//Здоровье танка
    private int numberofBullet = 0;//Пуля на очереди
    private ArrayList<LogicPanzer> opponents = new ArrayList<>();//Соперник
    private PanzerDirection vector = PanzerDirection.R;
    private Levels myLevel;
    private ArrayList<LogicPanzer>opponentsPanzers;

//____________________________________________________________________________________________________________________________________
    //К
    //О
    //Н
    //С
    //Т
    //Р
    //У
    //К
    //Т
    //О
    //Р

    private LogicPanzer() {
    }

    static LogicPanzer LightPanzer() {
        LogicPanzer logicPanzer = new LogicPanzer();
        logicPanzer.speed = 5;
        logicPanzer.health = 2;
        LogicBullet bullets[] = new LogicBullet[20];
        for (int i = 0; i < bullets.length; i++)
            bullets[i] = LogicBullet.LightBullet();

        logicPanzer.bullets = bullets;

        return logicPanzer;


    }

//____________________________________________________________________________________________________________________________________

    //М
    //Е
    //Т
    //О
    //Д
    //Ы

    //Д
    //Л
    //Я

    //К
    //Н
    //О
    //П
    //О
    //К

    void move(PanzerDirection vector) {
        this.vector = vector;

        switch (vector) {
            case R:
                setTranslate(getTranslateX() + speed, getTranslateY());
                break;
            case L:
                setTranslate(getTranslateX() - speed, getTranslateY());
                break;
            case D:
                setTranslate(getTranslateX(), getTranslateY() + speed);
                break;
            case U:
                setTranslate(getTranslateX(), getTranslateY() - speed);
        }

    }

    void takeDamage(LogicBullet logicBullet) {
        health -= logicBullet.getDamage();
    }

//____________________________________________________________________________________________________________________________________
    //C
    //Е
    //Т
    //Е
    //Р
    //Ы


    public void setTranslate(double translateX, double translateY) {
        this.translateX = translateX;
        this.translateY = translateY;
    }

    public void setMyLevel(Levels myLevel) {
        this.myLevel = myLevel;
    }

    public void setVector(PanzerDirection vector) {
        this.vector = vector;
    }

    public void setOpponents(ArrayList<LogicPanzer>opponents){
        this.opponents=opponents;

    }
//____________________________________________________________________________________________________________________________________

    //Г
    //Е
    //Т
    //Е
    //Р
    //Ы
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

    public double getSpeed() {
        return speed;
    }

    public int getNumberofBullet() {
        return numberofBullet;
    }

    public int getHealth() {
        return health;
    }

    public ArrayList<LogicPanzer> getOpponents() {
        return opponents;
    }

    public PanzerDirection getVector() {
        return vector;
    }

    public Levels getMyLevel() {
        return myLevel;
    }

    public LogicBullet[] getBullets() {
        return bullets;
    }

    public double getTranslateX() {
        return translateX;
    }

    public double getTranslateY() {
        return translateY;
    }
//____________________________________________________________________________________________________________________________________

}
