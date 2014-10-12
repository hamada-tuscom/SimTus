/*==================================================
  Game.class
==================================================*/

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Main extends JFrame implements Runnable{

	Thread thread;
	final long sleepTime=1000/30;
	long preTime;
	final int windowWidth=700,windowHeight=500;
	KeyManager KM;
	MouseManager MM;
	Image bufImg;
	static Insets ins;
	Random rnd;
	
	int pemutimer;

	ArrayList <People> peoples;
	ArrayList <Talk> talks;
	
	
	

	Main(){
		setTitle("Base");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setPreferredSize(new Dimension(windowWidth, windowHeight));
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
	}

	public static void main(String[] args){
		Main mf=new Main();
		ins=mf.getInsets();
		mf.init();
		mf.setVisible(true);
	}

	public void init(){
		KM=new KeyManager();
		addKeyListener(KM);
		requestFocusInWindow();
		MM=new MouseManager();
		addMouseListener(MM);
		addMouseMotionListener(MM);
		addMouseWheelListener(MM);
		rnd=new Random();
		
		pemutimer=0;
		
		peoples=new ArrayList<People>();
		talks=new ArrayList<Talk>();
		
		
		
		
		bufImg=createImage(windowWidth,windowHeight);
		
		preTime=System.currentTimeMillis();
		thread=new Thread(this);
		thread.start();
		
	}

	public void run(){
		while(true){
			
			
			for(People p:peoples){
				p.update(peoples,talks);
			}
			
			
			if(MM.isRClick){
				pemutimer++;
				if(pemutimer>5){
					pemutimer=0;
					if(0<=MM.getX() && MM.getX()<500 && 0<=MM.getY() && MM.getY()<500){
						peoples.add(new People(MM.getX(),MM.getY()));
					}
				}
			}
			else{pemutimer=0;}
			
			
			repaint();
			try{
				long nowTime=System.currentTimeMillis();
				if(nowTime<preTime+sleepTime){
					thread.sleep(sleepTime+preTime-nowTime);
					preTime+=sleepTime;
				}
				else{
					thread.sleep(sleepTime);
					preTime=System.currentTimeMillis();
				}
			}catch(InterruptedException e){
				System.out.println(e);
			}
		}
	}

	public void paint(Graphics g){
		MM.setIns(ins.left,ins.top);
		Graphics bufG=(Graphics)bufImg.getGraphics();
		bufG.setColor(Color.black);
		bufG.fillRect(0,0,windowWidth,windowHeight);
		
		
		for(Talk t:talks){
			bufG.setColor(new Color(150,255,150));
			bufG.drawOval((int)(t.x-30),(int)(t.y-30),60,60);
		}
		
		for(People p:peoples){
			bufG.setColor(new Color(p.r,p.g,p.b));
			bufG.fillOval((int)(p.x-5),(int)(p.y-5),10,10);
			bufG.setColor(Color.white);
			bufG.drawOval((int)(p.x-5),(int)(p.y-5),10,10);
		}
		
		
		bufG.setColor(new Color(100,200,200));
		bufG.fillRect(500,0,200,500);
		
		g.drawImage(bufImg,ins.left,ins.top,this);
	}
	public void update(Graphics g){
		paint(g);
	}

}

