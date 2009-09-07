package gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

@SuppressWarnings("serial")
public class Graph extends Canvas implements MouseMotionListener, MouseListener, MouseWheelListener {
	
	private int positionX;
	private int positionY;
	private int pressedInX;
	private int pressedInY;
	private int moveToX;
	private int moveToY;
	
	private int scale;
	
	private boolean positionsInit;
	
	public Graph() {
		this.setBackground(Color.WHITE);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		this.addMouseWheelListener(this);
		scale = 24;
	}
	public void paint(Graphics g) {
		if(!positionsInit) {
			positionX = getWidth()/2;
			positionY = getHeight()/2;
			moveToX = positionX;
			moveToY = positionY;
			positionsInit = true;
		}
		g.setFont(new Font("", Font.PLAIN, (scale-5)/2));
		paintAxis(g);
		paintGrid(g);
	}
	
	private void paintAxis(Graphics g) {
		g.setColor(Color.RED);
		g.drawLine(0, moveToY, this.getWidth(), moveToY);
		g.drawLine(moveToX, 0, moveToX, this.getHeight());
	}
	
	private void paintGrid(Graphics g) {
		paintGridX(g);
		paintGridY(g);
	}
	
	private void paintGridY(Graphics g) {
		if(moveToX < 0) {
			int pos = moveToX;
			int quantity = -1;
			while(pos < 0) {
				pos += scale;
				quantity++;
			}
			for(int i = 0; i <= this.getWidth()/scale; i++) {
				g.setColor(new Color(210,210,210));
				g.drawLine(pos + (i*scale), 0, pos + (i * scale), this.getHeight());
				g.setColor(new Color(50,50,50));
				g.drawString(""+((i+1)+(quantity)), pos+(scale*i)+1, moveToY-1);
			}
				
		} else if(moveToX < this.getWidth()) {
			g.setColor(new Color(50,50,50));
			g.drawString("0", moveToX+1, moveToY-1);
			for(int i = moveToX/scale; i > 0; i--) {
				g.setColor(new Color(210,210,210));
				g.drawLine(moveToX - (i*scale), 0, moveToX - (i * scale), this.getHeight());
				g.setColor(new Color(50,50,50));
				g.drawString("" + (-i), (moveToX-i*scale)+1, moveToY-1);
			}
			for(int i = 1; i<=(getWidth()-moveToX)/scale; i++) {
				g.setColor(new Color(210,210,210));
				g.drawLine(moveToX + (i*scale), 0, moveToX + (i*scale), this.getHeight());
				g.setColor(new Color(50,50,50));
				g.drawString("" + i, (moveToX+i*scale)+1, moveToY-1);
			}
		} else {
			int pos = moveToX;
			int quantity = -1;
			while(pos > getWidth()) {
				pos -= scale;
				quantity++;
			}
			for(int i = getWidth()/scale; i >= 0; i--) {
				g.setColor(new Color(210,210,210));
				g.drawLine(pos - (i*scale), 0, pos - (i*scale), getWidth());
				g.setColor(new Color(50,50,50));
				g.drawString(""+(-(i+1)-(quantity)), pos-(scale*i)+1, moveToY-1);
			}
		}
	}
	
	private void paintGridX(Graphics g) {
		g.setColor(new Color(210,210,210));
		if(moveToY < 0) {
			int pos = moveToY;
			int quantity = -1;
			while(pos < 0) {
				pos += scale;
				quantity++;
			}
			for(int i = 0; i <= this.getHeight()/scale; i++) {
				g.setColor(new Color(210,210,210));
				g.drawLine(0, pos + (i*scale), this.getWidth(), pos + (i * scale));
				g.setColor(new Color(50,50,50));
				g.drawString(""+(-(i+1)-(quantity)), moveToX+1, pos+(scale*i)+1);
			}
		} else if(moveToY < this.getHeight()) {
			for(int i = moveToY/scale; i > 0; i--) {
				g.setColor(new Color(210,210,210));
				g.drawLine(0, moveToY - (i*scale), this.getWidth(), moveToY - (i * scale));
				g.setColor(new Color(50,50,50));
				g.drawString("" + i, moveToX+1,(moveToY-i*scale)+1);
			}
			for(int i = 1; i<=(getHeight()-moveToY)/scale; i++) {
				g.setColor(new Color(210,210,210));
				g.drawLine(0, moveToY + (i*scale), this.getWidth(), moveToY + (i*scale));
				g.setColor(new Color(50,50,50));
				g.drawString("" + (-i), moveToX+1, (moveToY+i*scale)+1);
			}
		} else {
			int pos = moveToY;
			int quantity = -1;
			while(pos > getHeight()) {
				pos -= scale;
				quantity++;
			}
			for(int i = getHeight()/scale; i >= 0; i--) {
				g.setColor(new Color(210,210,210));
				g.drawLine(0, pos - (i*scale), getWidth(), pos - (i*scale));
				g.setColor(new Color(50,50,50));
				g.drawString(""+((i+1)+(quantity)), moveToX+1, pos-(scale*i)+1);
			}
		}
	}

	
	private void moveX(int quantity) {
		moveToX = positionX + quantity - pressedInX;
		repaint();
	}
	
	private void moveY(int quantity) {
		moveToY = positionY + quantity - pressedInY;
		repaint();
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		moveX(e.getX());
		moveY(e.getY());
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		pressedInX = e.getX();
		pressedInY = e.getY();
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		positionX = moveToX;
		positionY = moveToY;
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(scale>2 || e.getWheelRotation() < 0) {
			//Cambio la escala
			scale -= e.getWheelRotation();
			
			//Muevo los ejes
			if(e.getX() < positionX) {
				moveToX = positionX - e.getWheelRotation()*scale;
			} else if(e.getX() > positionX) {
				moveToX = positionX + e.getWheelRotation()*scale;
			}
			if(e.getY() < positionY) {
				moveToY = positionY - e.getWheelRotation()*scale;
			} else if(e.getY() > positionY) {
				moveToY = positionY + e.getWheelRotation()*scale;
			}
			repaint();
			
			positionX = moveToX;
			positionY = moveToY;
		}
		repaint();
	}
}
