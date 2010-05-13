package gui;

import control.ActionManager;

import java.awt.Component;
import java.awt.Font;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LeftPanel extends JPanel {

	private Action showNumberOffsetAction;
	private Action restartAxisAction;
	private Action goToAction;
	private JTextField x;
	private JTextField y;
    private ActionManager am;

	public LeftPanel(ActionManager am) {
        this.am = am;
    }

    //Should be all in the constructor but then we would have a loop problem
    public void createActions(Graphic g) 
    {
        this.showNumberOffsetAction = am.getShowNumberOffsetAction(g);
        this.restartAxisAction = am.getRestartAxisAction(g);
        this.goToAction = am.getGoToAction(g, this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initComponents();
    }

	private void initComponents() {
		this.add(createTopPanel());
		this.add(createBottomPanel());
	}

	private JPanel createTopPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("top panel"));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(createFunctionPanel());
		return panel;
	}

	private JPanel createFunctionPanel() {
		JLabel function = new JLabel("f(x)   = ");
		Font f = new Font("Arial", Font.BOLD, 15);
		function.setFont(f);

		JTextField textField = new JTextField(15);

		JPanel panel = new JPanel();
		panel.add(Box.createGlue());
		panel.add(function);
		panel.add(textField);
		panel.add(Box.createGlue());
		return panel;
	}

	private JPanel createBottomPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("bottom panel"));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		panel.add(createFirstButtonPanel());
		panel.add(createGoToPanel());
		return panel;
	}

	private Component createGoToPanel() {
		JPanel panel = new JPanel();
		JButton goToButton = new JButton(goToAction);
		goToButton.setToolTipText("Se dirije hacia la posición especificada");

		x = new JTextField(3);
		x.setBackground(getBackground());
		x.setHorizontalAlignment(JTextField.CENTER);
		y = new JTextField(3);
		y.setBackground(getBackground());
		y.setHorizontalAlignment(JTextField.CENTER);

		Font f = new Font("Arial", Font.BOLD, 15);
		
		JLabel par1 = new JLabel("(");
		JLabel coma = new JLabel(",");
		JLabel par2 = new JLabel(")");
		par1.setFont(f);
		x.setFont(f);
		coma.setFont(f);
		y.setFont(f);
		par2.setFont(f);

		panel.add(Box.createGlue());
		panel.add(par1);
		panel.add(x);
		panel.add(coma);
		panel.add(y);
		panel.add(par2);
		panel.add(goToButton);
		panel.add(Box.createGlue());
		return panel;
	}

	private JPanel createFirstButtonPanel() {
		JButton showNumberOffsetButton = new JButton(showNumberOffsetAction);
		showNumberOffsetButton
				.setToolTipText("Muestra la posición si alguno de los ejes no se ve");
		JButton restartAxisButton = new JButton(restartAxisAction);
		restartAxisButton
				.setToolTipText("Centra los ejes en el medio de la pantalla");
		JPanel panel = new JPanel();
		panel.add(Box.createGlue());
		panel.add(showNumberOffsetButton);
		panel.add(Box.createGlue());
		panel.add(restartAxisButton);
		panel.add(Box.createGlue());
		return panel;
	}

	public Integer getGoToX() {
		Integer i;
		try {
			i = new Integer(x.getText());
			return i;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "El primer campo no fue correctamente rellenado", "Error", 0);
		}
		return null;
	}

	public Integer getGoToY() {
		Integer i;
		try {
			i = new Integer(y.getText());
			return i;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "El segundo campo no fue correctamente rellenado", "Error", 0);
		}
		return null;
	}

}
