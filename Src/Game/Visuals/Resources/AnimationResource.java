package Game.Visuals.Resources;

import Game.Core.Resource;

public class AnimationResource extends Resource{
    private String name="";
    private int startingFrame=0,endingFrame=0;

    public AnimationResource(String _name,int _start,int _end){
        name = _name;startingFrame=_start;endingFrame=_end;
    }

    public int getStartingFrame(){return startingFrame;}
    public int getEndingFrame(){return endingFrame;}
    public String getName(){return name;}

    public void setStartingFrame(int _start){startingFrame=_start;}
    public void setEndingFrame(int _end){endingFrame= _end;}
    public void setName(String _name){name = _name;}
}
