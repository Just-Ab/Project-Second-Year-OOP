package CodeNameNeutronStar.Units;

import java.util.List;
import org.joml.Vector2i;

public class Path {

    private final List<Vector2i> points;
    private int currentIndex = 0;

    public Path(List<Vector2i> points) {
        this.points = points;
    }

    public boolean isFinished() {
        return currentIndex >= points.size();
    }

    public Vector2i getCurrentPoint() {
        if (isFinished()) return null;
        return points.get(currentIndex);
    }

    public void advance() {
        currentIndex++;
    }
}
