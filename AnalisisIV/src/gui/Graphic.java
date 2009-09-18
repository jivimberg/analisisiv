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
public class Graphic extends Canvas implements MouseMotionListener,
		MouseListener, MouseWheelListener {

	private double positionX;
	private double positionY;
	private double pressedInX;
	private double pressedInY;
	private double moveToX;
	private double moveToY;

	private int scale;

	private boolean positionsInit;
	private boolean showNumberOffset;

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
		g.setFont(new Font("", Font.PLAIN, (scale - 5) / 2));
		paintAxis(g);
		paintGrid(g);
		paintMousePosition(g);
	}

	private void paintAxis(Graphics g) {
		int moveToX = (int) this.moveToX;
		int moveToY = (int) this.moveToY;
		g.setColor(axisColor);
		g.drawLine(0, moveToY, this.getWidth(), moveToY);
		g.drawLine(moveToX, 0, moveToX, this.getHeight());
	}

	private void paintGrid(Graphics g) {
		paintGridX(g);
		paintGridY(g);
	}

	private void paintGridY(Graphics g) {
		int moveToX = (int) this.moveToX;
		int moveToY = (int) this.moveToY;
		if (moveToX < 0) {
			int pos = moveToX;
			int quantity = -1;
			while (pos < 0) {
				pos += scale;
				quantity++;
			}
			for (int i = 0; i <= this.getWidth() / scale; i++) {
				g.setColor(gridColor);
				g.drawLine(pos + (i * scale), 0, pos + (i * scale), this
						.getHeight());
				g.setColor(numberColor);
				g.drawString("" + ((i + 1) + (quantity)),
						pos + (scale * i) + 1, moveToY - 1);
			}
			if (showNumberOffset) {
				paintNumberOffsetY(g, 20);
			}
		} else if (moveToX < this.getWidth()) {
			g.setColor(numberColor);
			g.drawString("0", moveToX + 1, moveToY - 1);
			for (int i = moveToX / scale; i > 0; i--) {
				g.setColor(gridColor);
				g.drawLine(moveToX - (i * scale), 0, moveToX - (i * scale),
						this.getHeight());
				g.setColor(numberColor);
				g.drawString("" + (-i), (moveToX - i * scale) + 1, moveToY - 1);
			}
			for (int i = 1; i <= (getWidth() - moveToX) / scale; i++) {
				g.setColor(gridColor);
				g.drawLine(moveToX + (i * scale), 0, moveToX + (i * scale),
						this.getHeight());
				g.setColor(numberColor);
				g.drawString("" + i, (moveToX + i * scale) + 1, moveToY - 1);
			}
		} else {
			int pos = moveToX;
			int quantity = -1;
			while (pos > getWidth()) {
				pos -= scale;
				quantity++;
			}
			for (int i = getWidth() / scale; i >= 0; i--) {
				g.setColor(gridColor);
				g.drawLine(pos - (i * scale), 0, pos - (i * scale), getWidth());
				g.setColor(numberColor);
				g.drawString("" + (-(i + 1) - (quantity)), pos - (scale * i)
						+ 1, moveToY - 1);
			}
			if (showNumberOffset) {
				paintNumberOffsetY(g, this.getWidth() - 20);
			}
		}
	}

	private void paintNumberOffsetY(Graphics g, int position) {
		int moveToY = (int) this.moveToY;
		for (int i = moveToY / scale; i > 0; i--) {
			g.setColor(numberColor);
			g.drawString("" + i, position, (moveToY - i * scale) + 1);
		}
		for (int i = 1; i <= (getHeight() - moveToY) / scale; i++) {
			g.setColor(numberColor);
			g.drawString("" + (-i), position, (moveToY + i * scale) + 1);
		}
	}

	private void paintGridX(Graphics g) {
		int moveToX = (int) this.moveToX;
		int moveToY = (int) this.moveToY;
		g.setColor(gridColor);
		if (moveToY < 0) {
			int pos = moveToY;
			int quantity = -1;
			while (pos < 0) {
				pos += scale;
				quantity++;
			}
			for (int i = 0; i <= this.getHeight() / scale; i++) {
				g.setColor(gridColor);
				g.drawLine(0, pos + (i * scale), this.getWidth(), pos
						+ (i * scale));
				g.setColor(numberColor);
				g.drawString("" + (-(i + 1) - (quantity)), moveToX + 1, pos
						+ (scale * i) + 1);
			}
			if (showNumberOffset) {
				paintNumberOffsetX(g, 20);
			}
		} else if (moveToY < this.getHeight()) {
			for (int i = moveToY / scale; i > 0; i--) {
				g.setColor(gridColor);
				g.drawLine(0, moveToY - (i * scale), this.getWidth(), moveToY
						- (i * scale));
				g.setColor(numberColor);
				g.drawString("" + i, moveToX + 1, (moveToY - i * scale) + 1);
			}
			for (int i = 1; i <= (getHeight() - moveToY) / scale; i++) {
				g.setColor(gridColor);
				g.drawLine(0, moveToY + (i * scale), this.getWidth(), moveToY
						+ (i * scale));
				g.setColor(numberColor);
				g.drawString("" + (-i), moveToX + 1, (moveToY + i * scale) + 1);
			}
		} else {
			int pos = moveToY;
			int quantity = -1;
			while (pos > getHeight()) {
				pos -= scale;
				quantity++;
			}
			for (int i = getHeight() / scale; i >= 0; i--) {
				g.setColor(gridColor);
				g.drawLine(0, pos - (i * scale), getWidth(), pos - (i * scale));
				g.setColor(numberColor);
				g.drawString("" + ((i + 1) + (quantity)), moveToX + 1, pos
						- (scale * i) + 1);
			}
			if (showNumberOffset) {
				paintNumberOffsetX(g, this.getHeight() - 20);
			}
		}
	}

	private void paintNumberOffsetX(Graphics g, int position) {
		int moveToX = (int) this.moveToX;
		for (int i = moveToX / scale; i > 0; i--) {
			g.setColor(numberColor);
			g.drawString("" + (-i), (moveToX - i * scale) + 1, position);
		}
		for (int i = 1; i <= (getWidth() - moveToX) / scale; i++) {
			g.setColor(numberColor);
			g.drawString("" + (-i), (moveToX + i * scale) + 1, position);
		}
	}

	private double getMouseXPosition() {
		return (this.getMousePosition().getX() - moveToX) / scale;
	}

	private double getMouseYPosition() {
		return -(this.getMousePosition().getY() - moveToY) / scale;
	}

	private void paintMousePosition(Graphics g) {
		if (getMousePosition().getX() < getWidth()
				&& getMousePosition().getX() > 0
				&& getMousePosition().getY() < this.getHeight()
				&& getMousePosition().getY() > 0) {
			int x = (int) getMousePosition().getX() + 4;
			int y = (int) getMousePosition().getY() - 4;
			String position = "(" + (int) getMouseXPosition() + ","
					+ (int) getMouseYPosition() + ")";
			System.out.println("x= " + x + "y= " + y);

			g.drawString(position, x, y);
		}
		repaint();
	}

	private void moveX(double quantity) {
		moveToX = positionX + quantity;
		repaint();
	}

	private void moveY(double quantity) {
		moveToY = positionY + quantity;
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		moveX(e.getX() - pressedInX);
		moveY(e.getY() - pressedInY);
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
		int oldScale = scale;
		if (scale > 2 || e.getWheelRotation() < 0) {
			// Cambio la escala
			scale -= e.getWheelRotation();

			// Muevo los ejes
			moveX((oldScale - scale) * getMouseXPosition());
			moveY(-(oldScale - scale) * getMouseYPosition());
			repaint();
			positionX = moveToX;
			positionY = moveToY;
		}
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
		moveToX = positionX;
		moveToY = positionY;
		positionsInit = true;
		repaint();
	}
}
