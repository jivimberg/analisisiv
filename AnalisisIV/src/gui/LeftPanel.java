package gui;

import java.awt.Font;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LeftPanel extends JPanel {

	private JButton showNumberOffsetButton;
	private JButton restartAxisActionButton;
	private Action showNumberOffsetAction;
	private Action restartAxisAction;
	
    public LeftPanel(Action showNumberOffsetAction, Action restartAxisAction) {
    	this.showNumberOffsetAction = showNumberOffsetAction;
    	this.restartAxisAction = restartAxisAction;
        this.setBorder(BorderFactory.createTitledBorder("Left Panel"));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initComponents();
    }

    private void initComponents() {
    	JLabel function = new JLabel("f(x)   = ");
    	Font f = new Font("Arial",Font.BOLD, 15);
    	function.setFont(f);
    	
        JTextField textField = new JTextField(15);
        
    	JPanel panel1 = new JPanel();
    	panel1.add(Box.createGlue());
    	panel1.add(function);
    	panel1.add(textField);
        panel1.add(Box.createGlue());
        this.add(panel1);
        
        showNumberOffsetButton = new JButton(showNumberOffsetAction);
        showNumberOffsetButton.setToolTipText("Muestra la posición si alguno de los ejes no se ve");
        restartAxisActionButton = new JButton(restartAxisAction);
        restartAxisActionButton.setToolTipText("Centra los ejes en el medio de la pantalla");
        JPanel panel2 = new JPanel();
        panel2.add(Box.createGlue());
        panel2.add(showNumberOffsetButton);
        panel2.add(Box.createGlue());
        panel2.add(restartAxisActionButton);
        panel2.add(Box.createGlue());
        this.add(panel2);
        this.add(Box.createGlue());
    }
}
