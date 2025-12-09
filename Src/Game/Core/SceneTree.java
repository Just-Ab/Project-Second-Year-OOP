package Game.Core;

import java.util.ArrayList;

public class SceneTree {

    private Node root;

    public SceneTree(Node _root) {
        root = _root;
        root.enterTree();
    }

    public void update(float delta) {
        root.update(delta);
        flushQueuedFree(root);
    }

    private void flushQueuedFree(Node node) {

        for (Node child : new ArrayList<>(node.getChildren())) {
            flushQueuedFree(child);
        }

        if (node.getIsQueueFree()) {
            node.exitTree();
            if (node.getParent() != null) {
                node.getParent().removeChild(node);
            }
        }
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node _node) {
        root = _node;
        root.enterTree();
    }
}
