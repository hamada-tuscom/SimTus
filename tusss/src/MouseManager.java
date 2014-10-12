import java.awt.event.*;

public class MouseManager implements MouseListener,MouseMotionListener,MouseWheelListener
{
	int mx,my;
	boolean isLClick,isRClick;
	int insx,insy;
	
	public void setIns(int x,int y){
		insx=x;
		insy=y;
	}
	
	public void mouseClicked(MouseEvent e)
	{}

	public void mouseEntered(MouseEvent e)
	{}

	public void mouseExited(MouseEvent e)
	{}

	public void mousePressed(MouseEvent e){
		if(e.getButton()==MouseEvent.BUTTON1){
			isLClick=true;
		}
		if(e.getButton()==MouseEvent.BUTTON3){
			isRClick=true;
		}
	}
	public void mouseReleased(MouseEvent e){
		if(e.getButton()==MouseEvent.BUTTON1){
			isLClick=false;
		}
		if(e.getButton()==MouseEvent.BUTTON3){
			isRClick=false;
		}
	}

	public void mouseMoved(MouseEvent e){
		mx=e.getX()-insx;
		my=e.getY()-insy;
	}
	public void mouseDragged(MouseEvent e){
		mx=e.getX()-insx;
		my=e.getY()-insy;
	}

	public void mouseWheelMoved(MouseWheelEvent e){
		
	}

	public int getX(){return mx;}
	public int getY(){return my;}
	public boolean isLClick(){return isLClick;}
	public boolean isRClick(){return isRClick;}
	
}


