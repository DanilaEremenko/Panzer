package Server;
import java.util.List;

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
