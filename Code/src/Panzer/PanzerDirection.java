public enum PanzerDirection {U(90),D(270),R(0),L(180),STOP(0);
private int angle;
PanzerDirection(int angle){
    this.angle=angle;

}

    public int getAngle() {
        return angle;
    }
}
