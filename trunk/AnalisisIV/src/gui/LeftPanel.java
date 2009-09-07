package gui;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel {

    public LeftPanel() {
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
        
        JButton toGraph = new JButton("Graficar");
        JButton derive = new JButton("Derivar");
        
        JPanel panel2 = new JPanel();
        panel2.add(Box.createGlue());
        panel2.add(toGraph);
        panel2.add(Box.createGlue());
        panel2.add(derive);
        panel2.add(Box.createGlue());
        this.add(panel2);
        this.add(Box.createGlue());
        
//        JTextField textField = new JTextField();
//        GridBagConstraints constraints = new GridBagConstraints();
//        constraints.gridx = 0; // El área de texto empieza en la columna cero.
//        constraints.gridy = 0; // El área de texto empieza en la fila cero
//        constraints.gridwidth = 2; // El área de texto ocupa dos columnas.
//        constraints.gridheight = 2; // El área de texto ocupa 2 filas.
//        this.add(textField, constraints);
//
//        JButton button = new JButton("Resolver");
//        constraints.gridx = 0; // El área de texto empieza en la columna cero.
//        constraints.gridy = 4; // El área de texto empieza en la fila cero
//        constraints.gridwidth = 2; // El área de texto ocupa dos columnas.
//        constraints.gridheight = 2; // El área de texto ocupa 2 filas.
//        this.add(button, constraints);
//
//        JButton button1 = new JButton("Derivar");
//        constraints.gridx = 2; // El área de texto empieza en la columna cero.
//        constraints.gridy = 4; // El área de texto empieza en la fila cero
//        constraints.gridwidth = 2; // El área de texto ocupa dos columnas.
//        constraints.gridheight = 2; // El área de texto ocupa 2 filas.
//        this.add(button1, constraints);
    }
}
