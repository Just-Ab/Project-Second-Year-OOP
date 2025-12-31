package CodeNameNeutronStar.Units;

import java.util.*;
import org.joml.Vector2i;
import CodeNameNeutronStar.World.WorldSystem;

public class PathFinder {

    private final WorldSystem world = WorldSystem.getSingleton();

    public PathFinder(){} 

    public Path findPath(Unit2D unit) {

        Vector2i start = unit.getPositionNormalized();
        Vector2i goal  = unit.getDestination();
        if (goal == null) return null;

        if (!world.isInside(goal.x, goal.y)) return null;
        if (!world.isWalkable(goal.x, goal.y)) return null;

        Map<String, PathNode> nodes = new HashMap<>();
        Set<String> closed = new HashSet<>();

        PriorityQueue<PathNode> open =
            new PriorityQueue<>(Comparator.comparingInt(n -> n.fCost));

        PathNode startNode = new PathNode(start.x, start.y);
        startNode.gCost = 0;
        startNode.hCost = heuristic(start, goal);
        startNode.fCost = startNode.hCost;

        open.add(startNode);
        nodes.put(key(start.x, start.y), startNode);

        while (!open.isEmpty()) {

            PathNode current = open.poll();

            if (current.x == goal.x && current.y == goal.y) {
                return buildPath(current);
            }

            closed.add(key(current.x, current.y));

            for (Vector2i dir : directions()) {

                int nx = current.x + dir.x;
                int ny = current.y + dir.y;

                if (!world.isInside(nx, ny)) continue;
                if (!world.isWalkable(nx, ny)) continue;

                String nodeKey = key(nx, ny);
                if (closed.contains(nodeKey)) continue;

                int tentativeG =
                    current.gCost + world.getMovementCost(nx, ny);

                PathNode neighbor =
                    nodes.computeIfAbsent(nodeKey, k -> new PathNode(nx, ny));

                if (!open.contains(neighbor) || tentativeG < neighbor.gCost) {
                    neighbor.gCost = tentativeG;
                    neighbor.hCost = heuristic(new Vector2i(nx, ny), goal);
                    neighbor.fCost = neighbor.gCost + neighbor.hCost;
                    neighbor.parent = current;

                    if (!open.contains(neighbor)) {
                        open.add(neighbor);
                    }
                }
            }
        }

        return null;
    }

    private int heuristic(Vector2i a, Vector2i b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    private String key(int x, int y) {
        return x + "," + y;
    }

    private List<Vector2i> directions() {
        return List.of(
            new Vector2i(1, 0),
            new Vector2i(-1, 0),
            new Vector2i(0, 1),
            new Vector2i(0, -1)
        );
    }

    private Path buildPath(PathNode end) {
        List<Vector2i> points = new ArrayList<>();
        PathNode current = end;

        while (current != null) {
            points.add(new Vector2i(current.x, current.y));
            current = current.parent;
        }

        Collections.reverse(points);
        return new Path(points);
    }
    
}

class PathNode {
    int x;
    int y;
    int gCost;
    int hCost;
    int fCost;
    PathNode parent;

    PathNode(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

