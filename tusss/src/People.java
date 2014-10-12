
import java.util.*;

public class People{
	public double x,y;
	public int alone;
	/*
	 * 0:botti
	 * 1:real ju
	 * */
	
	ArrayList <Like> likes;
	Random rnd;
	
	public int brave,solo;
	
	public People(double x,double y){
		this.x=x;
		this.y=y;
		alone=0;
		likes=new ArrayList<Like>();
		rnd=new Random();
		brave=rnd.nextInt(10);
		solo=rnd.nextInt(9)+1;
	}
	
	public void update(ArrayList <People> ops){
		if(alone==0){
			People np=null;
			double range=20;
			for(People op:ops){
				if(op==this){continue;}
				double r=Usefuls.culRange(op.x,op.y,x,y);
				if(r<range){
					range=r;
					np=op;
				}				
			}
			if(range<20){
				if(getLike(np)/2+brave*2.5+rnd.nextInt(50)>=100){
					
				}
			}
			else{
				x+=(50.0-rnd.nextInt(100))/50.0d*solo;
				y+=(50.0-rnd.nextInt(100))/50.0d*solo;
			}
		}
		else{
			
		}
	}
	
	
	public double getLike(People op){
		for(Like l:likes){
			if(op==l.op){
				return l.like;
			}
		}
		return 0;
	}
	
	public void addLike(People op,double v){
		for(Like l:likes){
			if(op==l.op){
				l.like+=v;
				return;
			}
		}
		likes.add(new Like(op,v));
	}
	
}

