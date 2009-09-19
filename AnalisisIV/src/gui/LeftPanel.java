package gui;

import java.awt.Component;
import java.awt.Font;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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

	public LeftPanel(Action showNumberOffsetAction, Action restartAxisAction,
			Action goToAction) {
		this.showNumberOffsetAction = showNumberOffsetAction;
		this.restartAxisAction = restartAxisAction;
		this.goToAction = goToAction;

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
		goToButton.setToolTipText("Se dirije hacia la posici�n especificada");

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
				.setToolTipText("Muestra la posici�n si alguno de los ejes no se ve");
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
