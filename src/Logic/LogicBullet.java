package Logic;

import Graphic.PanzerDirection;

import static Graphic.PanzerDirection.*;

//Логическое отображение пули
public class LogicBullet {
    private LogicPanzer logicPanzer;//Танк, которому принадлежит пуля
    private double translateX;
    private double translateY;
    private double speed; //Скорость полета пули
    private double size;//Размер пули
    private double damage;//Урон, который пуля наносит
    private PanzerDirection vector = STOP;//Направление движения пули

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


    private LogicBullet() {
    }

    //Статические методы генерации пуль

    static LogicBullet LightBullet(LogicPanzer logicPanzer) {
        LogicBullet logicBullet = new LogicBullet();
        logicBullet.logicPanzer = logicPanzer;
        logicBullet.speed = 20;
        logicBullet.size = 14;
        logicBullet.damage = 1;
        return logicBullet;

    }

    static LogicBullet HeavyBullet(LogicPanzer logicPanzer){
        LogicBullet logicBullet = new LogicBullet();
        logicBullet.logicPanzer = logicPanzer;
        logicBullet.speed = 20;
        logicBullet.size = 24;
        logicBullet.damage = 3;
        return logicBullet;

    }
//____________________________________________________________________________________________________________________________________

    //Д
    //В
    //И
    //Ж
    //Е
    //Н
    //И
    //E


    //Устанавливает пулю в текущее местанахождение танка
    //И запускает
    //Вызвается в Panzers.fire()
    void fire() {
        setTranslate(logicPanzer.getTranslateX(), logicPanzer.getTranslateY());
        setVector(logicPanzer.getVector());
    }

    //Изменяет координаты пулю в зависимости от значения поля vector
    public void move() {


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
                    break;
                case STOP:
                    setTranslate(-1500, -1500);
                    break;
            }
    }


//____________________________________________________________________________________________________________________________________
    //C
    //Е
    //Т
    //Е
    //Р
    //Ы


    public void setVector(PanzerDirection vector) {
        this.vector = vector;
    }


    private void setTranslate(double translateX, double translateY) {
        this.translateX = translateX;
        this.translateY = translateY;
    }


//____________________________________________________________________________________________________________________________________
    //Г
    //Е
    //Т
    //Е
    //Р
    //Ы

    public double getSize() {
        return size;
    }

    public double getDamage() {
        return damage;
    }

    public double getTranslateX() {
        return translateX;
    }

    public double getTranslateY() {
        return translateY;
    }

    public LogicPanzer getLogicPanzer() {
        return logicPanzer;
    }

//____________________________________________________________________________________________________________________________________
}
