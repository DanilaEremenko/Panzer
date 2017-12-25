public class LogicBullet {
    private LogicPanzer logicPanzer;
    private double translateX;
    private double translateY;
    private double speed; //Скорость полета пули
    private double size;//Размер пули
    private double damage;
    private PanzerDirection vector = PanzerDirection.STOP;//Направление движения пули

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

    static LogicBullet LightBullet(LogicPanzer logicPanzer) {
        LogicBullet logicBullet = new LogicBullet();
        logicBullet.logicPanzer = logicPanzer;
        logicBullet.speed = 20;
        logicBullet.size = 14;
        logicBullet.damage = 1;
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


    void fire() {
        setTranslate(logicPanzer.getTranslateX(), logicPanzer.getTranslateY());
        setVector(logicPanzer.getVector());
    }

    void move() {


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
                    setTranslate(-15, -15);
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

    public PanzerDirection getVector() {
        return vector;
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
