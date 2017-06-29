public enum  Direction {U(90),D(270),R(0),L(180),STOP(0);
private int angle;
Direction(int angle){
    this.angle=angle;

}

    public int getAngle() {
        return angle;
    }
}
