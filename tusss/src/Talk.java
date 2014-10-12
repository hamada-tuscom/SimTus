
import java.util.*;

public class Talk{
	
	double x,y;
	public ArrayList <People> participant;
	public int r,g,b;
	
	public void setNewTalk(){
		
	}
	
	public void update(){
		if(participant.size()<=1){
			participant.get(0).join=null;
			participant.get(0).alone=0;
			participant.remove(0);
		}
	}
	
	public Talk(double x,double y){
		this.x=x;
		this.y=y;
		participant=new ArrayList <People> ();
	}
	
	public void add(People p){
		participant.add(p);
		p.join=this;
	}

	public void remove(People p){
		p.join=null;
		p.alone=0;
		participant.remove(p);
	}
	
}

