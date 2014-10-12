
import java.util.ArrayList;

public class People{
	public double x,y;
	public int alone;
	/*
	 * 0:botti
	 * 1:real ju
	 * */
	
	ArrayList <Like> likes;
	
	public People(double x,double y){
		this.x=x;
		this.y=y;
		alone=0;
		likes=new ArrayList<Like>();
	}
	
	public void update(ArrayList <People> ops){
		
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

