
import java.util.*;

public class Talk{
	
	double x,y;
	ArrayList <People> participant;
	public int r,g,b;
	
	public void setNewTalk(){
		
	}
	
	public void update(){
		
	}
	
	public Talk(double x,double y){
		this.x=x;
		this.y=y;
		participant=new ArrayList <People> ();
	}
	
	public void add(People p){
		participant.add(p);
	}
	
}

