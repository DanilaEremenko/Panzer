package Logic;

import Controller.Level;

import java.util.ArrayList;

public class LogicPanzer implements LogicElement {

    private double translateX;
    private double translateY;

    private double lastTranslateX;//Предыдущеие координаты
    private double lastTranslateY;//Нужны чтобы не проезжать сквозь препятствия

    private Level myLevel;

    private int defaultHealth;//Максимальное здоровье танка
    private int currentHealth;//текущее здоровье танка

    private double angleOfMove = 0;//Угол по которому едет танка в полярной системе координат
    private double currentSpeed;//текущая скорость передвжиения танков
    private double maxSpeed;//максимальная скорость передвижения танков
    private double acceleration;//Ускорение танка

    private LogicBullet bullets[];//Массив пуль танка
    private int numberofBullet = 0;//Пуля на очереди
    private ArrayList<LogicPanzer> opponents = new ArrayList<>();//Список соперников

    private int hightBody;//Высота тела
    private int widthBody;//Ширина тела
    private int widthGun;//Ширина пушки
    private int hightGun;//Высота пушки

    private boolean needDraw = false;//необходимость отрисовки метки со здоровьем


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
        logicPanzer.currentSpeed = 0;
        logicPanzer.maxSpeed = 5;
        logicPanzer.acceleration = 0.25;
        logicPanzer.currentHealth = 2;
        logicPanzer.defaultHealth = 2;
        logicPanzer.bullets = new LogicBullet[10];
        for (int i = 0; i < logicPanzer.bullets.length; i++) {
            logicPanzer.bullets[i] = LogicBullet.LightBullet(logicPanzer);
            logicPanzer.bullets[i].setOnDefaultPosition();
        }


        return logicPanzer;


    }

    public static LogicPanzer HeavyPanzer() {
        LogicPanzer logicPanzer = new LogicPanzer();
        logicPanzer.hightBody = 50;
        logicPanzer.widthBody = 50;
        logicPanzer.widthGun = 30;
        logicPanzer.hightGun = 24;
        logicPanzer.currentSpeed = 0;
        logicPanzer.maxSpeed = 2;
        logicPanzer.acceleration = 0.1;
        logicPanzer.currentHealth = 4;
        logicPanzer.defaultHealth = 4;
        logicPanzer.bullets = new LogicBullet[10];
        for (int i = 0; i < logicPanzer.bullets.length; i++) {
            logicPanzer.bullets[i] = LogicBullet.HeavyBullet(logicPanzer);
            logicPanzer.bullets[i].setOnDefaultPosition();
        }

        return logicPanzer;

    }


//____________________________________________________________________________________________________________________________________


    //Поворот
    public void rotate(double angleOfTurn) {
        if (currentHealth <= 0)
            return;
        angleOfMove = (angleOfMove + angleOfTurn) % (2 * Math.PI);
        setNeedDraw(true);
        commonMove();
    }

    //Ускорение
    public void accelerate() {
        if (currentSpeed <= maxSpeed)
            currentSpeed += acceleration;
    }

    //Езда назад
    public void backAccelerate() {
        if (currentSpeed >= -maxSpeed)
            currentSpeed -= acceleration;
    }

    @Override
    //Передвижение для AnimationTimer
    public void move() {
        if (currentHealth <= 0)
            return;
        commonMove();
    }

    //Общая часть из двух верхних move
    private void commonMove() {
        lastTranslateX = translateX;
        lastTranslateY = translateY;
        setTranslate(getTranslateX() + Math.cos(angleOfMove) * currentSpeed,
                getTranslateY() + Math.sin(angleOfMove) * currentSpeed);
    }

    //Стрельба
    public void fire() {
        if (currentHealth <= 0)
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
        currentHealth -= logicBullet.getDamage();
        needDraw = true;
    }

    public void reload() {
        currentHealth = defaultHealth;
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


    public Level getMyLevel() {
        return myLevel;
    }

    public int getCurrentHealth() {
        return currentHealth;
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

    public double getAngleOfMove() {
        return angleOfMove;
    }


//____________________________________________________________________________________________________________________________________

}
