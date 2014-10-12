/*
*
*  Usefuls class
*   ver0.02
*
*/


import java.awt.*;
import java.awt.image.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;

public class Usefuls{

	final static public Image getImg(String filename){
		ClassLoader cl=(new Usefuls()).getClass().getClassLoader();
		return (new ImageIcon(cl.getResource(filename))).getImage();
	}
	final static public Graphics2D getRotateGraphics2D(Graphics g,double rad,double x,double y){
		Graphics2D g2d=(Graphics2D)g.create();
		g2d.transform(AffineTransform.getRotateInstance(rad,x,y));
		return g2d;
	}
	final static public void rotateGraphics2D(Graphics2D g2d,double rad,double x,double y){
		g2d.transform(AffineTransform.getRotateInstance(rad,x,y));
	}
	final static public Graphics2D getAlphaGraphics2D(Graphics g,float alpha){
		Graphics2D g2d=(Graphics2D)g.create();
		/*g2d.setComposite(AlphaComposite.SrcOver.derive(alpha));*/
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
		return g2d;
	}
	final static public void alphaGraphics2D(Graphics2D g2d,float alpha){
		/*g2d.setComposite(AlphaComposite.SrcOver.derive(alpha));*/
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
	}
	final static public double culRad(double fromx,double fromy,double tox,double toy){
		return Math.atan2(toy-fromy,tox-fromx);
	}
	final static public double culRangePow2(double fromx,double fromy,double tox,double toy){
		return Math.pow(tox-fromx,2)+Math.pow(toy-fromy,2);
	}
	final static public double culRange(double fromx,double fromy,double tox,double toy){
		return Math.sqrt(culRangePow2(fromx,fromy,tox,toy));
	}
	final static public double culLineAndPointRangePow2
			(double x1,double y1,double x2,double y2,double px,double py){
		// cul Length of Line
		double Sx=(x2-x1);
		double Sy=(y2-y1);
		double S=Sx*Sx+Sy*Sy;
		if(S==0){
			double dx=(px-x1);
			double dy=(py-y1);
			return dx*dx+dy*dy;
		}
		// Examine of the perpendicular
		double d;
		// 1
		double V1x=(px-x1);
		double V1y=(py-y1);
		double V1=V1x*V1x+V1y*V1y;
		if(V1==0){return 0;}
		if(Sx*V1x+Sy*V1y<=0){
			double dx=(px-x1);
			double dy=(py-y1);
			return dx*dx+dy*dy;
		}
		// 2
		double V2x=(px-x2);
		double V2y=(py-y2);
		double V2=V2x*V2x+V2y*V2y;
		if(V2==0){return 0;}
		if(-Sx*V2x-Sy*V2y<=0){
			double dx=(px-x2);
			double dy=(py-y2);
			return dx*dx+dy*dy;
		}
		// cul line and point range
		d=Sx*(py-y1)-Sy*(px-x1);
		return d*d/S;
	}
	final static public int culIntersectParOfLineToOval(double[] result,
			double x1,double y1,double x2,double y2,
			double xr,double yr,double r){
		double vx=x2-x1;
		double vy=y2-y1;
		double vrx=x1-xr;
		double vry=y1-yr;
		double a=vx*vx+vy*vy;
		double b=vx*vrx+vy*vry;
		double c=vrx*vrx+vry*vry-r*r;
		double d=b*b-a*c;
		if(d<0){return 0;}
		else if(d==0){
			result[0]=-b/a;
			return 1;
		}
		else{
			d=Math.sqrt(d);
			result[0]=(-b-d)/a;
			result[1]=(-b+d)/a;
			return 2;
		}
	}
	final static public int culLeftOrRight(double fromRad,double toRad){
		// left:-1  right:1
		if(fromRad<0){
			if(fromRad<toRad && toRad<=fromRad+Math.PI){return 1;}
			else {return -1;}
		}
		else{
			if(fromRad-Math.PI<toRad && toRad<=fromRad){return -1;}
			else {return 1;}
		}
	}
	final static public double adjustRad(double rad){
		if(-Math.PI<=rad && rad<Math.PI){return rad;}
		else if(rad<0){
			return Math.PI*2-((-rad)%(Math.PI*2));
		}
		else{
			return rad%(Math.PI*2)-Math.PI*2;
		}
	}
	final static public double culRadDifference(double fromRad,double toRad){
		fromRad=adjustRad(fromRad);
		toRad=adjustRad(toRad);
		double r=toRad-fromRad;
		if(r<-Math.PI){return r+Math.PI*2;}
		else if(r>Math.PI){return r-Math.PI*2;}
		return r;
	}
	final static public boolean isHit(double fromx,double fromy,double fromrange,
			double tox,double toy,double torange){
		double range=fromrange+torange;
		double rx=Math.abs(fromx-tox);
		double ry=Math.abs(fromy-toy);
		if(rx>range || ry>range){return false;}
		if(rx*rx+ry*ry<range*range){return true;}
		else{return false;}
	}
	final static public void paintAfterglow(Graphics g,Color color,double camx,double camy,double zoom,
			double range,int[][] afterglowPt,int num){
		if(num<=0 || afterglowPt.length<num){return;}
		g.setColor(color);
		int glowx1=400+(int)((afterglowPt[0][0]-camx)*zoom);
		int glowy1=300+(int)((afterglowPt[0][1]-camy)*zoom);
		double r1=range*zoom;
		for(int i=1;i<=num;i++){
			int glowx2=400+(int)((afterglowPt[i][0]-camx)*zoom);
			int glowy2=300+(int)((afterglowPt[i][1]-camy)*zoom);
			double r2=range*(num-i)/num*zoom;
			double rad=Math.atan2(glowy2-glowy1,glowx2-glowx1)+Math.PI/2;
			double cos=Math.cos(rad);
			double sin=Math.sin(rad);
			int px[]={glowx1+(int)(cos*r1),glowx1-(int)(cos*r1),
				glowx2-(int)(cos*r2),glowx2+(int)(cos*r2)};
			int py[]={glowy1+(int)(sin*r1),glowy1-(int)(sin*r1),
				glowy2-(int)(sin*r2),glowy2+(int)(sin*r2)};
			g.fillPolygon(px,py,4);
			glowx1=glowx2;
			glowy1=glowy2;
			r1=r2;
		}
	}
	final static public void paintNum(Graphics g,ImageObserver io,Image i,int num,
			int x,int y,int w,int h,int rsx,int rsy,int rw,int rh,int shiftx,int shifty){
		int beam=1;
		for(int n=num;n>=10;n/=10){beam*=10;}
		while(beam>0){
			int n=num/beam;
			n%=10;
			g.drawImage(i,x,y,x+w,y+h,rsx+rw*n,rsy,rsx+rw*(n+1),rsy+rh,io);
			x+=shiftx;
			y+=shifty;
			beam/=10;
		}
	}
}

