package Logic;

import Controller.Level;
import Graphic.PanzerDirection;

import java.util.ArrayList;

import static Graphic.PanzerDirection.*;

public class LogicPanzer {

    private int defaultHealth;
    //Текущие координаты
    private double translateX;
    private double translateY;
    //
    private double lastTranslateX;//Предыдущеие координаты
    private double lastTranslateY;//Нужны чтобы не проезжать сквозь препятствия
    //
    private int hightBody;//Высота тела
    private int widthBody;//Ширина тела
    private int widthGun;//Ширина пушки
    private int hightGun;//Высота пушки
    private double speed;//Скорость передвжиения танков
    private LogicBullet bullets[];//Массив пуль танка
    private int numberofBullet = 0;//Пуля на очереди
    private int health;//Здоровье танка
    private ArrayList<LogicPanzer> opponents = new ArrayList<>();//Список соперников
    private PanzerDirection vector = R;//Направление, в котором движется танк
    private double angleOfTurn = 0;//Угол поворта.Нужен для отрисовки при поворотах
    private Level myLevel;
    private boolean needDraw = false;


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

    //Статические методы генерации для танков

    public static LogicPanzer LightPanzer() {
        LogicPanzer logicPanzer = new LogicPanzer();
        logicPanzer.hightBody = 30;
        logicPanzer.widthBody = 30;
        logicPanzer.widthGun = 20;
        logicPanzer.hightGun = 14;
        logicPanzer.speed = 5;
        logicPanzer.health = 2;
        logicPanzer.defaultHealth = 2;
        LogicBullet bullets[] = new LogicBullet[10];
        for (int i = 0; i < bullets.length; i++)
            bullets[i] = LogicBullet.LightBullet(logicPanzer);

        logicPanzer.bullets = bullets;

        return logicPanzer;


    }

    public static LogicPanzer HeavyPanzer() {
        LogicPanzer logicPanzer = new LogicPanzer();
        logicPanzer.hightBody = 50;
        logicPanzer.widthBody = 50;
        logicPanzer.widthGun = 30;
        logicPanzer.hightGun = 24;
        logicPanzer.speed = 2;
        logicPanzer.health = 4;
        logicPanzer.defaultHealth = 4;
        LogicBullet bullets[] = new LogicBullet[10];
        for (int i = 0; i < bullets.length; i++)
            bullets[i] = LogicBullet.HeavyBullet(logicPanzer);

        logicPanzer.bullets = bullets;

        return logicPanzer;

    }


//____________________________________________________________________________________________________________________________________
    //Д
    //Л
    //Я

    //К
    //Н
    //О
    //П
    //О
    //К

    //Передвижение для кнопок
    public void move(PanzerDirection vector) {
        if (health <= 0)
            return;
        angleOfTurn = this.vector.getAngle() - vector.getAngle();
        this.vector = vector;
        commonMove();
    }

    //Передвижение для AnimationTimer
    public void move() {
        if (health <= 0)
            return;
        commonMove();
    }

    //Общая часть из двух верхних move
    private void commonMove() {
        lastTranslateX = translateX;
        lastTranslateY = translateY;
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

    //Стрельба
    public void fire() {
        if (health <= 0)
            return;
        if (numberofBullet < bullets.length - 1) {
            bullets[numberofBullet].fire();
            numberofBullet++;
        }


    }

    //НЕ ДАЕТ ТАНКУ ПРОЕЗЖАТЬ СКВОЗЬ ПРЕПЯТСТВИЯ И ДРУГИЕ ТАНКИ
    public void comeBackIfCanNotMove() {
        translateX = lastTranslateX;
        translateY = lastTranslateY;
    }

    //Получание урона
    public void takeDamage(LogicBullet logicBullet) {
        health -= logicBullet.getDamage();
        needDraw = true;
    }

    public void reload() {
        health = defaultHealth;
        numberofBullet = 0;
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

    public void setNeedDraw(boolean needDraw) {
        this.needDraw = needDraw;
    }

    public void setMyLevel(Level myLevel) {
        this.myLevel = myLevel;
    }

    public void setAngleOfTurn(double angleOfTurn) {
        this.angleOfTurn = angleOfTurn;
    }


    public void addOpponent(LogicPanzer logicPanzer) {
        opponents.add(logicPanzer);
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

    public PanzerDirection getVector() {
        return vector;
    }

    public double getAngleOfTurn() {
        return angleOfTurn;
    }


    public Level getMyLevel() {
        return myLevel;
    }

    public int getHealth() {
        return health;
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

    public boolean isNeedDraw() {
        return needDraw;
    }


    public int getDefaultHealth() {
        return defaultHealth;
    }

    //____________________________________________________________________________________________________________________________________

}