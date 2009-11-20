package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputFunctionPanel extends JPanel implements KeyListener, ActionListener{

	JTextField funcTextArea;
	JLabel state;
	
	public InputFunctionPanel() {
		super();
		funcTextArea = new JTextField(20);
		//Full text
		funcTextArea.addActionListener(this);
		//Key by key
//		funcTextArea.addKeyListener(this);
		state = new JLabel();
		
		add(funcTextArea);
		add(state);
	}

	private boolean validate(String input) {
		//Check symbols
		if(!checkSymbols(input)){
			return false;
		}
		
		for (int i = 0; i < input.length(); i++) {
			//check sen
			if(input.charAt(i) == 's' && input.length() >= i+3){
				if(!input.substring(i, i + 3).equalsIgnoreCase("sen")){
					System.out.println("sen error");
					return false;					
				}
				i += 3;
			}
			
			//check cos
			if(input.charAt(i) == 'c' && input.length() >= i+3){
				if(!input.substring(i, i + 3).equalsIgnoreCase("cos")){
					System.out.println("cos error");
					return false;
				}
				i += 3;
			}
			
			//check tan
			if(input.charAt(i) == 't' && input.length() >= i+3){
				if(!input.substring(i, i + 3).equalsIgnoreCase("tan")){
					System.out.println("tan error");
					return false;
				}
				i += 3;
			}
			
			//check sqrt
			if(input.charAt(i) == 's' && input.charAt(++i) == 'q' && input.length() >= i+4){
				if(!input.substring(i, i + 4).equalsIgnoreCase("sqrt")){
					System.out.println("sqrt error");
					return false;
				}
				i += 4;
			}
		}
		return true;
	}
	
	private boolean checkSymbols(String input) {
		Stack<Character> symbols = new Stack<Character>();
		
		for (int i = 0; i < input.length(); i++) {
			/* FILL STACK */ 
			
			//add {
			if(input.charAt(i) == '{'){
				symbols.add('{');
			}
			
			//add [
			if(input.charAt(i) == '['){
				symbols.add('[');
			}
			
			//add (
			if(input.charAt(i) == '('){
				symbols.add('(');
			}
			
			/* EMPTY STACK */ 
			
			//remove {
			if(input.charAt(i) == '}'){
				if(!symbols.pop().equals('{')){
					return false;
				}
			}
			
			//remove [
			if(input.charAt(i) == ']'){
				if(!symbols.pop().equals('[')){
					return false;
				}
			}
			
			//remove (
			if(input.charAt(i) == ')'){
				if(!symbols.pop().equals('(')){
					return false;
				}
			}
			
		}
		System.out.println(symbols.isEmpty());
		return symbols.isEmpty();
	}

	private void convert() {
		// TODO Auto-generated method stub

	}
	
	public void keyPressed(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {}

	public void keyTyped(KeyEvent e) {
		System.out.println(funcTextArea.getText());
		if(validate(funcTextArea.getText())){
			state.setText("OK");
		}else{
			state.setText("Syntax Error");
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JPanel ifp = new InputFunctionPanel();
		
		frame.setSize(new Dimension (300, 200));
		frame.add(ifp);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println(funcTextArea.getText());
		if(validate(funcTextArea.getText())){
			state.setText("OK");
		}else{
			state.setText("Syntax Error");
		}
		
	}
	
}
