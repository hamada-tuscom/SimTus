/*==================================================
  KeyManager.class
==================================================*/

import java.awt.event.*;

public class KeyManager implements KeyListener{
	private boolean isUp, isDown, isRight, isLeft;
	private boolean isSpace;

	KeyManager(){
		isUp = false;
		isDown = false;
		isRight = false;
		isLeft = false;
		isSpace=false;
	}

	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_UP) isUp = true;
		if(e.getKeyCode()==KeyEvent.VK_DOWN) isDown = true;
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) isRight = true;
		if(e.getKeyCode()==KeyEvent.VK_LEFT) isLeft = true;
		if(e.getKeyCode()==KeyEvent.VK_SPACE) isSpace = true;
	}

	public void keyReleased(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_UP) isUp = false;
		if(e.getKeyCode()==KeyEvent.VK_DOWN) isDown = false;
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) isRight = false;
		if(e.getKeyCode()==KeyEvent.VK_LEFT) isLeft = false;
		if(e.getKeyCode()==KeyEvent.VK_SPACE) isSpace = false;
	}

	public void keyTyped(KeyEvent e){ }

	public boolean isUp(){return isUp;}
	public boolean isDown(){return isDown;}
	public boolean isRight(){return isRight;}
	public boolean isLeft(){return isLeft;}
	public boolean isSpace(){return isSpace;}
}
