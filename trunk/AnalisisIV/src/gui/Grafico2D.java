package gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Line2D;

@SuppressWarnings("serial")
public class Grafico2D extends Canvas implements MouseMotionListener,
		MouseListener, MouseWheelListener {

	private double positionX;
	private double positionY;

	private Point pressedPoint;

	private double scale;

	private boolean positionsInit;
	private boolean showNumberOffset;

	private static final Color backgroundColor = Color.WHITE;
	private static final Color axisColor = Color.RED;
	private static final Color gridColor = new Color(210, 210, 210);
	private static final Color numberColor = new Color(50, 50, 50);

	public Grafico2D() {
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		this.addMouseWheelListener(this);

		setBackground(backgroundColor);
		scale = 24;
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (!positionsInit) {
			resetPosition();
		}
		g.setFont(new Font("", Font.PLAIN, (int)(scale - 5) / 2));
		paintAxis(g2);
		paintGrid(g2);
		// paintMousePosition(g);
	}

	private void paintAxis(Graphics2D g2) {
		g2.setColor(axisColor);
		g2.draw(new Line2D.Double(0, positionY, getWidth(), positionY));
		g2.draw(new Line2D.Double(positionX, 0, positionX, getHeight()));
	}

	private void paintGrid(Graphics2D g2) {
		g2.setColor(numberColor);
		g2.drawString("0", (int)positionX + 1, (int)positionY - 1);
		
		for(double i = positionX/scale; i<getWidth()/scale; i++) {//derecha del eje Y
			if((i+1)*scale>0) {
				g2.setColor(gridColor);
				g2.draw(new Line2D.Double((i+1)*scale, 0, (i+1)*scale, getHeight()));
				g2.setColor(numberColor);
				g2.drawString(""+(int)(i-positionX/scale), (int)((i+1)*scale), (int)positionY);
			}
		}
		for(double i = positionX/scale; i>0; i--) {//izquierda del eje Y
			if((i-1)*scale<getWidth()) {
				g2.setColor(gridColor);
				g2.draw(new Line2D.Double((i-1)*scale, 0, (i-1)*scale, getHeight()));
			}
		}
		for(double i = positionY/scale; i<getHeight()/scale; i++) {//debajo del eje x
			if((i+1)*scale>0) {
				g2.setColor(gridColor);
				g2.draw(new Line2D.Double(0, (i+1)*scale, getWidth(), (i+1)*scale));
			}
		}
		for(double i = positionY/scale; i>0; i--) {//arriba del eje x
			if((i-1)*scale<getHeight()) {
				g2.setColor(gridColor);
				g2.draw(new Line2D.Double(0, (i-1)*scale, getWidth(), (i-1)*scale));
			}
		}
	}

	// private void paintGridY(Graphics g) {
	// int moveToX = (int) this.moveToX;
	// int moveToY = (int) this.moveToY;
	// if (moveToX < 0) {
	// int pos = moveToX;
	// int quantity = -1;
	// while (pos < 0) {
	// pos += scale;
	// quantity++;
	// }
	// for (int i = 0; i <= this.getWidth() / scale; i++) {
	// g.setColor(gridColor);
	// g.drawLine(pos + (i * scale), 0, pos + (i * scale), this
	// .getHeight());
	// g.setColor(numberColor);
	// g.drawString("" + ((i + 1) + (quantity)),
	// pos + (scale * i) + 1, moveToY - 1);
	// }
	// if (showNumberOffset) {
	// paintNumberOffsetY(g, 20);
	// }
	// } else if (moveToX < this.getWidth()) {
	// g.setColor(numberColor);
	// g.drawString("0", moveToX + 1, moveToY - 1);
	// for (int i = moveToX / scale; i > 0; i--) {
	// g.setColor(gridColor);
	// g.drawLine(moveToX - (i * scale), 0, moveToX - (i * scale),
	// this.getHeight());
	// g.setColor(numberColor);
	// g.drawString("" + (-i), (moveToX - i * scale) + 1, moveToY - 1);
	// }
	// for (int i = 1; i <= (getWidth() - moveToX) / scale; i++) {
	// g.setColor(gridColor);
	// g.drawLine(moveToX + (i * scale), 0, moveToX + (i * scale),
	// this.getHeight());
	// g.setColor(numberColor);
	// g.drawString("" + i, (moveToX + i * scale) + 1, moveToY - 1);
	// }
	// } else {
	// int pos = moveToX;
	// int quantity = -1;
	// while (pos > getWidth()) {
	// pos -= scale;
	// quantity++;
	// }
	// for (int i = getWidth() / scale; i >= 0; i--) {
	// g.setColor(gridColor);
	// g.drawLine(pos - (i * scale), 0, pos - (i * scale), getWidth());
	// g.setColor(numberColor);
	// g.drawString("" + (-(i + 1) - (quantity)), pos - (scale * i)
	// + 1, moveToY - 1);
	// }
	// if (showNumberOffset) {
	// paintNumberOffsetY(g, this.getWidth() - 20);
	// }
	// }
	// }
	//
	// private void paintNumberOffsetY(Graphics g, int position) {
	// int moveToY = (int) this.moveToY;
	// for (int i = moveToY / scale; i > 0; i--) {
	// g.setColor(numberColor);
	// g.drawString("" + i, position, (moveToY - i * scale) + 1);
	// }
	// for (int i = 1; i <= (getHeight() - moveToY) / scale; i++) {
	// g.setColor(numberColor);
	// g.drawString("" + (-i), position, (moveToY + i * scale) + 1);
	// }
	// }
	//
	// private void paintGridX(Graphics g) {
	// int moveToX = (int) this.moveToX;
	// int moveToY = (int) this.moveToY;
	// g.setColor(gridColor);
	// if (moveToY < 0) {
	// int pos = moveToY;
	// int quantity = -1;
	// while (pos < 0) {
	// pos += scale;
	// quantity++;
	// }
	// for (int i = 0; i <= this.getHeight() / scale; i++) {
	// g.setColor(gridColor);
	// g.drawLine(0, pos + (i * scale), this.getWidth(), pos
	// + (i * scale));
	// g.setColor(numberColor);
	// g.drawString("" + (-(i + 1) - (quantity)), moveToX + 1, pos
	// + (scale * i) + 1);
	// }
	// if (showNumberOffset) {
	// paintNumberOffsetX(g, 20);
	// }
	// } else if (moveToY < this.getHeight()) {
	// for (int i = moveToY / scale; i > 0; i--) {
	// g.setColor(gridColor);
	// g.drawLine(0, moveToY - (i * scale), this.getWidth(), moveToY
	// - (i * scale));
	// g.setColor(numberColor);
	// g.drawString("" + i, moveToX + 1, (moveToY - i * scale) + 1);
	// }
	// for (int i = 1; i <= (getHeight() - moveToY) / scale; i++) {
	// g.setColor(gridColor);
	// g.drawLine(0, moveToY + (i * scale), this.getWidth(), moveToY
	// + (i * scale));
	// g.setColor(numberColor);
	// g.drawString("" + (-i), moveToX + 1, (moveToY + i * scale) + 1);
	// }
	// } else {
	// int pos = moveToY;
	// int quantity = -1;
	// while (pos > getHeight()) {
	// pos -= scale;
	// quantity++;
	// }
	// for (int i = getHeight() / scale; i >= 0; i--) {
	// g.setColor(gridColor);
	// g.drawLine(0, pos - (i * scale), getWidth(), pos - (i * scale));
	// g.setColor(numberColor);
	// g.drawString("" + ((i + 1) + (quantity)), moveToX + 1, pos
	// - (scale * i) + 1);
	// }
	// if (showNumberOffset) {
	// paintNumberOffsetX(g, this.getHeight() - 20);
	// }
	// }
	// }
	//
	// private void paintNumberOffsetX(Graphics g, int position) {
	// int moveToX = (int) this.moveToX;
	// for (int i = moveToX / scale; i > 0; i--) {
	// g.setColor(numberColor);
	// g.drawString("" + (-i), (moveToX - i * scale) + 1, position);
	// }
	// for (int i = 1; i <= (getWidth() - moveToX) / scale; i++) {
	// g.setColor(numberColor);
	// g.drawString("" + (-i), (moveToX + i * scale) + 1, position);
	// }
	// }

	// private double getMouseXPosition() {
	// return (this.getMousePosition().getX() - moveToX) / scale;
	// }
	//
	// private double getMouseYPosition() {
	// return -(this.getMousePosition().getY() - moveToY) / scale;
	// }
	//
	// private void paintMousePosition(Graphics g) {
	// if (getMousePosition().getX() < getWidth()
	// && getMousePosition().getX() > 0
	// && getMousePosition().getY() < this.getHeight()
	// && getMousePosition().getY() > 0) {
	// int x = (int) getMousePosition().getX() + 4;
	// int y = (int) getMousePosition().getY() - 4;
	// String position = "(" + (int) getMouseXPosition() + ","
	// + (int) getMouseYPosition() + ")";
	// System.out.println("x= " + x + "y= " + y);
	//
	// g.drawString(position, x, y);
	// }
	// repaint();
	// }
	//
	// private void moveX(double quantity) {
	// moveToX = positionX + quantity;
	// repaint();
	// }
	//
	// private void moveY(double quantity) {
	// moveToY = positionY + quantity;
	// repaint();
	// }

	// @Override
	// public void mouseDragged(MouseEvent e) {
	// moveX(e.getX() - pressedInX);
	// moveY(e.getY() - pressedInY);
	// }
	//
	// @Override
	// public void mouseMoved(MouseEvent e) {
	// }
	//
	// @Override
	// public void mouseClicked(MouseEvent e) {
	// }
	//
	// @Override
	// public void mouseEntered(MouseEvent e) {
	//
	// }
	//
	// @Override
	// public void mouseExited(MouseEvent e) {
	// }
	//
	// @Override
	// public void mousePressed(MouseEvent e) {
	// pressedInX = e.getX();
	// pressedInY = e.getY();
	// }
	//
	// @Override
	// public void mouseReleased(MouseEvent e) {
	// positionX = moveToX;
	// positionY = moveToY;
	// }
	//
	// @Override
	// public void mouseWheelMoved(MouseWheelEvent e) {
	// int oldScale = scale;
	// if (scale > 2 || e.getWheelRotation() < 0) {
	// // Cambio la escala
	// scale -= e.getWheelRotation();
	//
	// // Muevo los ejes
	// moveX((oldScale - scale) * getMouseXPosition());
	// moveY(-(oldScale - scale) * getMouseYPosition());
	// repaint();
	// positionX = moveToX;
	// positionY = moveToY;
	// }
	// }

	// public void showNumberOffset(boolean showNumberOffset) {
	// this.showNumberOffset = showNumberOffset;
	// repaint();
	// }

	// public boolean getShowNumberOffset() {
	// return showNumberOffset;
	// }

	public void resetPosition() {
		positionX = getWidth() / 2;
		positionY = getHeight() / 2;
		positionsInit = true;
		repaint();
	}

	private void moveX(double value) {
		positionX += value;
	}
	private void moveY(double value) {
		positionY += value;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		moveX(e.getX() - pressedPoint.getX());
		moveY(e.getY() - pressedPoint.getY());
		pressedPoint = new Point(e.getX(), e.getY());
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		pressedPoint = e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (scale > 2 || e.getWheelRotation() < 0) {
			// Cambio la escala
			scale -= e.getWheelRotation();
			
			repaint();
		}
	}
}
