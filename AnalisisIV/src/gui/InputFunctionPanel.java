package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class InputFunctionPanel extends JPanel implements KeyListener{

	JTextArea funcTextArea;
	
	public InputFunctionPanel() {
		super();
		this.funcTextArea = new JTextArea();
		
		add(funcTextArea);
	}

	private void validate(JTextArea textArea) {
		// TODO Auto-generated method stub
		
	}
	
	public void keyPressed(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {}

	public void keyTyped(KeyEvent e) {
		validate(funcTextArea);
	}


	
}
