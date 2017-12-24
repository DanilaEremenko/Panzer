public class LogicBullet {
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

    static LogicBullet LightBullet() {
        LogicBullet logicBullet = new LogicBullet();
        logicBullet.speed = 20;
        logicBullet.size = 14;
        logicBullet.damage = 1;
        return logicBullet;

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

    //Д
    //В
    //И
    //Ж
    //Е
    //Н
    //И
    //Я

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


//____________________________________________________________________________________________________________________________________

    //C
    //Е
    //Т
    //Е
    //Р
    //Ы

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

    public double getSpeed() {
        return speed;
    }

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

//____________________________________________________________________________________________________________________________________
}
