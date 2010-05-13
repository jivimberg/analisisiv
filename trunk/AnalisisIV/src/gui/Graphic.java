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

import functions.Constant;
import functions.Cos;
import functions.Function;
import functions.Sin;
import functions.Variable;
import functions.basics.Sum;

@SuppressWarnings("serial")
public class Graphic extends Canvas implements MouseMotionListener,
		MouseListener, MouseWheelListener {

	private int positionX;
	private int positionY;

	private int scale;
	private int fontSize;
	private Point pressedPoint;

	private boolean positionsInit;
	private boolean showNumberOffset;
	private boolean paintPoint;

	private static final Color backgroundColor = Color.WHITE;
	private static final Color axisColor = Color.RED;
	private static final Color gridColor = new Color(210, 210, 210);
	private static final Color numberColor = new Color(50, 50, 50);

	public Graphic() {
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
		fontSize = (scale - 5) / 2;
		g.setFont(new Font("", Font.PLAIN, fontSize));
		paintAxis(g);
		paintGrid(g);
		paintOutNumber(g);
		paintFunction(g);
		paintPoint(g);
	}

	private void paintPoint(Graphics g) {
		if (paintPoint) {
			g.setColor(Color.BLUE);
			g.fillOval(getWidth() / 2 - 1, getHeight() / 2 - 1, 5, 5);
			g.drawOval(getWidth() / 2 - 1, getHeight() / 2 - 1, 5, 5);
			g.drawString("(" + (getWidth() / 2 - positionX) / scale + ","
					+ -(getHeight() / 2 - positionY) / scale + ")",
					getWidth() / 2 + 4, getHeight() / 2 - 4);
			paintPoint = false;
		}
	}

	private void paintAxis(Graphics g) {
		g.setColor(axisColor);
		g.drawLine(0, positionY, getWidth(), positionY);
		g.drawLine(positionX, 0, positionX, getHeight());
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
				paintGridLine(g, positionX + i * scale, 0, positionX + i
						* scale, getHeight());
				paintNumber(g, "" + i, positionX + i * scale + 1, positionY - 1);
			}
		}
		for (int i = positionX / scale; i > 0; i--) {
			if ((positionX - i * scale) < getWidth()) {
				paintGridLine(g, positionX - i * scale, 0, positionX - i
						* scale, getHeight());
				paintNumber(g, "" + (-i), positionX - i * scale + 1,
						positionY - 1);
			}
		}
	}

	private void paintXGrid(Graphics g) {
		for (int i = 1; i <= (getHeight() - positionY) / scale; i++) {
			if ((positionY + i * scale) > 0) {
				paintGridLine(g, 0, positionY + i * scale, getWidth(),
						positionY + i * scale);
				paintNumber(g, "" + (-i), positionX + 1, positionY + i * scale
						- 1);
			}
		}
		for (int i = positionY / scale; i > 0; i--) {
			if ((positionY - i * scale) < getHeight()) {
				paintGridLine(g, 0, positionY - i * scale, getWidth(),
						positionY - i * scale);
				paintNumber(g, "" + i, positionX + 1, positionY - i * scale - 1);
			}
		}
	}

	private void paintGridLine(Graphics g, int x1, int y1, int x2, int y2) {
		g.setColor(gridColor);
		g.drawLine(x1, y1, x2, y2);
	}

	private void paintNumber(Graphics g, String number, int x, int y) {
		g.setColor(numberColor);
		g.drawString(number, x, y);
	}

	private void paintOutNumber(Graphics g) {
		if (showNumberOffset) {
			if (positionX + fontSize < 0) {
				paintOutNumberY(g, scale);
			} else if (positionX > getWidth()) {
				paintOutNumberY(g, getWidth() - scale);
			}
			if (positionY < 0) {
				paintOutNumberX(g, scale);
			} else if (positionY - fontSize > getHeight()) {
				paintOutNumberX(g, getHeight() - scale);
			}
		}
	}

	private void paintOutNumberY(Graphics g, int position) {
		for (int i = 1; i <= (getHeight() - positionY) / scale; i++) {
			if ((positionY + i * scale) > 0) {
				g.drawString("" + (-i), position, positionY + i * scale - 1);
			}
		}
		for (int i = positionY / scale; i > 0; i--) {
			if ((positionY - i * scale) < getHeight()) {
				g.drawString("" + i, position, positionY - i * scale - 1);
			}
		}
	}

	private void paintOutNumberX(Graphics g, int position) {
		g.setColor(numberColor);
		for (int i = 1; i <= (getWidth() - positionX) / scale; i++) {
			if ((positionX + i * scale) > 0) {
				g.drawString("" + i, positionX + i * scale + 1, position);
			}
		}
		for (int i = positionX / scale; i > 0; i--) {
			if ((positionX - i * scale) < getWidth()) {
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

	public void goTo(int x, int y) {
		positionX = getWidth() / 2 - x * scale;
		positionY = getHeight() / 2 + y * scale;
		paintPoint = true;
		repaint();
	}

	private void paintFunction(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		Sin s = new Sin(new Variable());
		g.setColor(Color.BLUE);
		resolveFunction(g2, s);
		
		Cos c = new Cos(new Variable());
		g.setColor(Color.MAGENTA);
		resolveFunction(g2, c);
		
		Sum f = new Sum();
		f.addFunction(new Constant(2));
		f.addFunction(new Variable());
		g.setColor(Color.GREEN);
		resolveFunction(g2, f);
	}

	private void resolveFunction(Graphics2D g2, Function f) {
		double x1;
		double x2;
		double y1;
		double y2;

		double from = -(positionX + scale) / scale;
		double to = (from + getWidth() / scale + scale * 2);

		while (from + 0.1 < to) {
			x1 = from;
			y1 = -f.resolve(x1);
			x2 = (from + 0.1);
			y2 = -f.resolve(x2);
			g2.draw(new Line2D.Double(x1 * scale + positionX, y1 * scale
					+ positionY, x2 * scale + positionX, y2 * scale + positionY));
			from += 0.1;
		}
	}
}
