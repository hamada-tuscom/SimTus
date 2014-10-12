
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
	
	public int r,g,b;
	
	public int brave,solo;
	
	public People(double x,double y){
		this.x=x;
		this.y=y;
		alone=0;
		likes=new ArrayList<Like>();
		rnd=new Random();
		r=rnd.nextInt(200)+30;
		g=rnd.nextInt(200)+30;
		b=rnd.nextInt(200)+30;
		brave=rnd.nextInt(10);
		solo=rnd.nextInt(9)+1;
	}
	
	public void update(ArrayList <People> ops,ArrayList<Talk> talks){
		if(alone==0){
			x+=(50.0-rnd.nextInt(100))/50.0d*solo;
			y+=(50.0-rnd.nextInt(100))/50.0d*solo;
			if(x<0){x=0;}
			if(x>500){x=500;}
			if(y<0){y=0;}
			if(y>500){y=500;}
			
			People np=null;
			double range=20;
			for(People op:ops){
				if(op==this || op.alone!=0){continue;}
				double r=Usefuls.culRange(op.x,op.y,x,y);
				if(r<range){
					range=r;
					np=op;
				}				
			}
			if(range<20){
				if(getLike(np)/2+brave*5+rnd.nextInt(100)>=100){
					alone=1;
					np.alone=1;
					Talk t=new Talk((x+np.x)/2,(y+np.y)/2);
					t.add(this);
					t.add(np);
					talks.add(t);
				}
			}
		}
		else{
			
		}
	}
	
	public int getColorInt(double d){
		int i=(int)d;
		if(i<0){return 0;}
		if(i>=255){return 254;}
		return i;
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

