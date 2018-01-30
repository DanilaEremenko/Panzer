package Server;
import java.util.List;

class BulletPos {
    private double x;
    private double y;

    public BulletPos(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

class PanzerPos {
    private double x;
    private double y;
    private double angle;

    public PanzerPos(double x, double y, double angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAngle() {
        return angle;
    }
}

public class State {
    List<BulletPos> bulletPoses;
    List<PanzerPos> panzerPoses;

    public State(List<BulletPos> bulletPoses, List<PanzerPos> panzerPoses) {
        this.bulletPoses = bulletPoses;
        this.panzerPoses = panzerPoses;
    }

    public List<BulletPos> getBulletPoses() {
        return bulletPoses;
    }

    public List<PanzerPos> getPanzerPoses() {
        return panzerPoses;
    }
}
