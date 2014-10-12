
import java.util.*;

public class Talk{
	
	double x,y;
	public ArrayList <People> participant;
	public int r,g,b;
	public int timer;
	Random rnd;
	
	
	public void update(){
		if(participant.size()<=1){
			if(participant.size()<=0){
				return;
			}
			participant.get(0).join=null;
			participant.get(0).alone=0;
			participant.remove(0);
		}
		else{
			timer++;
			if(timer>10){
				timer=0;
				for(People p:participant){
					if(p.r<r){p.r++;}
					else{p.r--;}
					if(p.g<g){p.g++;}
					else{p.g--;}
					if(p.b<b){p.b++;}
					else{p.b--;}
					if(p.r<0){p.r=0;}
					if(p.g<0){p.g=0;}
					if(p.b<0){p.b=0;}
					if(p.r>=254){p.r=254;}
					if(p.g>=254){p.g=254;}
					if(p.b>=254){p.b=254;}
					for(People p2:participant){
						if(p==p2){continue;}
						p.addLike(p2,0.1);
					}
				}
			}
			int newr=0,newg=0,newb=0;
			for(People p:participant){
				newr+=p.r+rnd.nextInt(20)-10;
				newg+=p.g+rnd.nextInt(20)-10;
				newb+=p.b+rnd.nextInt(20)-10;
			}
			newr/=participant.size();
			newg/=participant.size();
			newb/=participant.size();
			r=(r+newr+1)/2;
			g=(g+newg+1)/2;
			b=(b+newb+1)/2;
			if(r<30){r=30;}
			if(g<30){g=30;}
			if(b<30){b=30;}
			if(r>=254){r=254;}
			if(g>=254){g=254;}
			if(b>=254){b=254;}
		}
	}
	
	public Talk(double x,double y){
		this.x=x;
		this.y=y;
		participant=new ArrayList <People> ();
		timer=0;
		rnd=new Random();
	}
	
	public void add(People p){
		participant.add(p);
		p.join=this;
	}

	public void remove(People p){
		for(People p2:participant){
			if(p==p2){continue;}
			p.addLike(p2,-2);
		}
		p.join=null;
		p.alone=0;
		participant.remove(p);
	}
	
}

