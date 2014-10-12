
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
	
	Talk join;
	int fun;
	
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
			
			for(Talk t:talks){
				if(Usefuls.culRange(t.x,t.y,x,y)<=30){
					int def=Math.abs(r-t.r)+Math.abs(g-t.g)+Math.abs(b-t.b);
					if(def<rnd.nextInt(300)){
						t.add(this);
						alone=1;
						fun=(int)(rnd.nextInt(10)+5);
						return;
					}
				}
			}
			
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
					fun=(int)(getLike(np)+rnd.nextInt(10)+5);
					np.fun=(int)(np.getLike(this)+rnd.nextInt(10)+5);
					Talk t=new Talk((x+np.x)/2,(y+np.y)/2);
					t.add(this);
					t.add(np);
					t.r=getColorInt( (np.r*getLike(np)+r*(100-getLike(np)) )/100 );
					t.g=getColorInt( (np.g*getLike(np)+g*(100-getLike(np)) )/100 );
					t.b=getColorInt( (np.b*getLike(np)+b*(100-getLike(np)) )/100 );
					talks.add(t);
				}
			}
		}
		else{
			if(Math.abs(r-join.r)<rnd.nextInt(100)){fun++;}
			else{fun-=1;}
			if(Math.abs(g-join.g)<rnd.nextInt(100)){fun++;}
			else{fun-=1;}
			if(Math.abs(b-join.b)<rnd.nextInt(100)){fun++;}
			else{fun-=1;}
			if(fun>100){fun=100;}
			if(fun<=0){join.remove(this);}
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

