package Logic;

//Логическое отображение пули
public class LogicBullet implements LogicElement {
    private double translateX;
    private double translateY;

    private LogicPanzer logicPanzer;//Танк, которому принадлежит пуля

    private double currentSpeed = 0; //Скорость полета пули
    private double maxSpeed;//Максимальная скорость танка

    private double damage;//Урон, который пуля наносит

    private double size;//Размер пули

    private double angleOfMove = 0;//Угол по которому полетит пуля при выстреле
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
        logicBullet.maxSpeed = 20;
        logicBullet.size = 14;
        logicBullet.damage = 1;
        return logicBullet;

    }

    static LogicBullet HeavyBullet(LogicPanzer logicPanzer) {
        LogicBullet logicBullet = new LogicBullet();
        logicBullet.logicPanzer = logicPanzer;
        logicBullet.maxSpeed = 20;
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
        currentSpeed = maxSpeed;
        angleOfMove = logicPanzer.getAngleOfMove();
        translateX = logicPanzer.getTranslateX() + logicPanzer.getWidthBody() / 2 - size / 2;
        translateY = logicPanzer.getTranslateY() + logicPanzer.getWidthBody() / 2 - size / 2;
    }

    //Изменяет координаты пулю в зависимости от значения поля vector
    @Override
    public void move() {
        translateX = translateX + Math.cos(angleOfMove) * currentSpeed;
        translateY = translateY + Math.sin(angleOfMove) * currentSpeed;
    }


//____________________________________________________________________________________________________________________________________
    //C
    //Е
    //Т
    //Е
    //Р
    //Ы

    public void setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public void setOnDefaultPosition() {
        translateX = -200;
        translateY = -200;
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

    public LogicPanzer getLogicPanzer() {
        return logicPanzer;
    }

    public double getTranslateX() {
        return translateX;
    }

    public double getTranslateY() {
        return translateY;
    }

//____________________________________________________________________________________________________________________________________
}
