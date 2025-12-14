package Game.Core;

import java.util.ArrayList;
import java.util.List;

public class Node {
    protected String group="Global";
    protected Node parent=null;
    protected List<Node> children=new ArrayList<>();

    protected boolean isInTree = false;
    protected boolean isReady = false;
    protected boolean isQueuedFree = false;

    public Node(){
    }

    public Node(String _Group){
        group = _Group;
    }

    protected void setParent(Node _parent){
        parent = _parent;
    }

    public void addChild(Node _node){
        children.addLast(_node);
        _node.setParent(this);
        _node.enterTree();
    }
    
    public void removeChild  (Node _node){
        children.remove(_node);
        _node.parent=null;
    }
   
    public List<Node> getChildren(){
        return children;
    }
   
    public Node getParent(){
        return parent;
    }
   

    protected final void enterTree(){
        if(isInTree){return;}
        isInTree=true;

        for (Node child : children) {
            child.enterTree();
        }
        _enterTree();
    }

    protected void _enterTree(){

    } 

    protected final void exitTree(){
        if(!isInTree){return;}
        for (Node child : new ArrayList<>(children)) {
            child.exitTree();
        }
        _exitTree();
        children.clear();
        parent = null;
        isInTree=false;

    }

    protected void _exitTree(){

    }


    public final void update(float _delta){
        if(!isReady){
            _ready();
            setReady();
        }

        updateEngine(_delta);
        _update(_delta);

        for (Node child : children) {
            child.update(_delta);
        }

    }

    protected void updateEngine(float _delta){

    }

    public void _ready(){
    }

    public void _update(float _delta){
    }


    public void setGroup(String _group){group = _group;}

    public String getGroup(){return group;}

    public boolean isInGroup(String _group){return group.equals(_group);}

    public boolean getIsReady(){return isReady;}

    public void setReady(){isReady = true;}

    public void queueFree(){isQueuedFree=true;}

    public boolean getIsQueueFree(){return isQueuedFree;}

}
