package Game.Core;

import java.util.ArrayList;
import java.util.List;

public class Node {
    protected String name="";
    protected Node parent=null;
    protected List<Node> children=new ArrayList<>();

    protected boolean isInTree = false;
    protected boolean isReady = false;

    public Node(String _name){
        name = _name;
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
    
    public String getName(){return name;}

    
    protected void enterTree(){
        if(isInTree){return;}
        isInTree=true;

        for (Node child : children) {
            child.enterTree();
        }
        _enterTree();
    }

    protected void _enterTree(){

    } 

    public void _ready(){
    }

    public void _update(float _delta){
    }

    public boolean getIsReady(){return isReady;}
    public void setReady(){isReady = true;}

}
