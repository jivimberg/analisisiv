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
public class Graphics2 extends Canvas implements MouseMotionListener,
		MouseListener, MouseWheelListener {

	private int positionX;
	private int positionY;

	private Point pressedPoint;

	private int scale;

	private boolean positionsInit;
	private boolean showNumberOffset;

	private static final Color backgroundColor = Color.WHITE;
	private static final Color axisColor = Color.RED;
	private static final Color gridColor = new Color(210, 210, 210);
	private static final Color numberColor = new Color(50, 50, 50);

	public Graphics2() {
		showNumberOffset = true;
		this.setBackground(backgroundColor);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		this.addMouseWheelListener(this);
		scale = 24;
	}

	public void paint(Graphics g) {
		if (!positionsInit) {
			resetPosition();
		}
		g.setFont(new Font("", Font.PLAIN, (scale - 5) / 2));
		paintAxis(g);
		paintGrid(g);
		paintOutNumber(g);
		paintFunction(g);
	}

	private void paintAxis(Graphics g) {
		int moveToX = (int) positionX;
		int moveToY = (int) positionY;
		g.setColor(axisColor);
		g.drawLine(0, moveToY, this.getWidth(), moveToY);
		g.drawLine(moveToX, 0, moveToX, this.getHeight());
	}

	private void paintGrid(Graphics g) {
		g.setColor(numberColor);
		g.drawString("0", positionX + 1, positionY - 1);
		paintYGrid(g);
		paintXGrid(g);
	}

	private void paintYGrid(Graphics g) {
		for (int i = 1; i <= (getWidth() - positionX) / scale; i++) {
			if ((positionX + i * scale) > 0) {
				g.setColor(gridColor);
				g.drawLine(positionX + i * scale, 0, positionX + i * scale,
						getHeight());
				g.setColor(numberColor);
				g.drawString("" + i, positionX + i * scale + 1, positionY - 1);
			}
		}
		for (int i = positionX / scale; i > 0; i--) {
			if ((positionX - i * scale) < getWidth()) {
				g.setColor(gridColor);
				g.drawLine(positionX - i * scale, 0, positionX - i * scale,
						getHeight());
				g.setColor(numberColor);
				g.drawString("" + (-i), positionX - i * scale + 1,
						positionY - 1);
			}
		}
	}

	private void paintXGrid(Graphics g) {
		for (int i = 1; i <= (getHeight() - positionY) / scale; i++) {
			if ((positionY + i * scale) > 0) {
				g.setColor(gridColor);
				g.drawLine(0, positionY + i * scale, getWidth(), positionY + i
						* scale);
				g.setColor(numberColor);
				g.drawString("" + (-i), positionX + 1, positionY + i * scale
						- 1);
			}
		}
		for (int i = positionY / scale; i > 0; i--) {
			if ((positionY - i * scale) < getHeight()) {
				g.setColor(gridColor);
				g.drawLine(0, positionY - i * scale, getWidth(), positionY - i
						* scale);
				g.setColor(numberColor);
				g.drawString("" + i, positionX + 1, positionY - i * scale - 1);
			}
		}
	}

	private void paintOutNumber(Graphics g) {
		if (showNumberOffset) {
			if (positionX < 0) {
				paintOutNumberY(g, 20);
			} else if (positionX > getWidth()) {
				paintOutNumberY(g, getWidth() - 20);
			}
			if (positionY < 0) {
				paintOutNumberX(g, 20);
			} else if (positionY > getHeight()) {
				paintOutNumberX(g, getHeight() - 20);
			}
		}
	}

	private void paintOutNumberY(Graphics g, int position) {
		for (int i = 1; i <= (getHeight() - positionY) / scale; i++) {
			if ((positionY + i * scale) > 0) {
				g.setColor(numberColor);
				g.drawString("" + (-i), position, positionY + i * scale - 1);
			}
		}
		for (int i = positionY / scale; i > 0; i--) {
			if ((positionY - i * scale) < getHeight()) {
				g.setColor(numberColor);
				g.drawString("" + i, position, positionY - i * scale - 1);
			}
		}
	}

	private void paintOutNumberX(Graphics g, int position) {
		for (int i = 1; i <= (getWidth() - positionX) / scale; i++) {
			if ((positionX + i * scale) > 0) {
				g.setColor(numberColor);
				g.drawString("" + i, positionX + i * scale + 1, position);
			}
		}
		for (int i = positionX / scale; i > 0; i--) {
			if ((positionX - i * scale) < getWidth()) {
				g.setColor(numberColor);
				g.drawString("" + (-i), positionX - i * scale + 1, position);
			}
		}
	}

	private double getMouseXPosition() {
		return (this.getMousePosition().getX() - positionX) / scale;
	}

	private double getMouseYPosition() {
		return -(this.getMousePosition().getY() - positionY) / scale;
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
		int oldScale = scale;
		if (scale > 2 || e.getWheelRotation() < 0) {
			changeScale(-e.getWheelRotation());
			moveX((oldScale - scale) * getMouseXPosition());
			moveY(-(oldScale - scale) * getMouseYPosition());
			repaint();
		}
	}

	private void changeScale(int value) {
		scale += value;
	}

	public void showNumberOffset(boolean showNumberOffset) {
		this.showNumberOffset = showNumberOffset;
		repaint();
	}

	public boolean getShowNumberOffset() {
		return showNumberOffset;
	}

	public void resetPosition() {
		positionX = getWidth() / 2;
		positionY = getHeight() / 2;
		positionsInit = true;
		repaint();
	}

	private void paintFunction(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.BLUE);
		pow(g2, 2);
		g.setColor(Color.GREEN);
		pow(g2, 3);
		g.setColor(Color.MAGENTA);
		sin(g2);
		g.setColor(Color.CYAN);
		cos(g2);
	}

	private void pow(Graphics2D g2, int power) {
		double x1 = 0;
		double x2 = 0;
		double y1 = 0;
		double y2 = 0;

		double from = -(positionX + scale) / scale;
		double to = (scale + getWidth()) / scale + scale;
		while (from + 0.1 < to) {
			x1 = from;
			y1 = -Math.pow(x1, power);
			x2 = (from + 0.1);
			y2 = -Math.pow(x2, power);
			g2.draw(new Line2D.Double(x1 * scale + positionX, y1 * scale
				+ positionY, x2 * scale + positionX, y2 * scale + positionY));
			from += 0.1;
		}
	}

	private void sin(Graphics2D g2) {
		double x1 = 0;
		double x2 = 0;
		double y1 = 0;
		double y2 = 0;

		double from = -(positionX + scale) / scale;
		double to = (from + getWidth() / scale + scale * 2);

		while (from + 0.1 < to) {
			x1 = from;
			y1 = -Math.sin(x1);
			x2 = (from + 0.1);
			y2 = -Math.sin(x2);
			g2.draw(new Line2D.Double(x1 * scale + positionX, y1 * scale
				+ positionY, x2 * scale + positionX, y2 * scale + positionY));
			from += 0.1;
		}
	}
	
	private void cos(Graphics2D g2) {
		double x1 = 0;
		double x2 = 0;
		double y1 = 0;
		double y2 = 0;

		double from = -(positionX + scale) / scale;
		double to = (from + getWidth() / scale + scale * 2);

		while (from + 0.1 < to) {
			x1 = from;
			y1 = -Math.cos(x1);
			x2 = (from + 0.1);
			y2 = -Math.cos(x2);
			g2.draw(new Line2D.Double(x1 * scale + positionX, y1 * scale
				+ positionY, x2 * scale + positionX, y2 * scale + positionY));
			from += 0.1;
		}
	}

}
