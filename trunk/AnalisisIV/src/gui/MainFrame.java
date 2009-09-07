package gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{

	LeftPanel lPanel;
	Graph g;
	
    public MainFrame() {
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setTitle("Analisis IV");
        
        this.getContentPane().setLayout(new BorderLayout());
//        corrector.listeners.add(this);
//        buttonsManager = new ButtonsManager(corrector, this);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });

        
        JToolBar toolBar = new JToolBar();
        Graficacion graficacion = new Graficacion();
        graficacion.setDimensiones(500, 500);
        double[] X = new double[5];
        X[0] = 1;
        X[1] = 2;
        X[2] = 3;
        X[3] = 4;
        X[4] = 5;
		double[] Y = new double[5];
		Y[0] = 1;
        Y[1] = 2;
        Y[2] = 3;
        Y[3] = 4;
        Y[4] = 5;
		graficacion.Datos(X,Y,500,500,"Grafica 1 X vs Y","X","Y"); 
        lPanel = new LeftPanel();
        g = new Graph();
        this.getContentPane().add(lPanel, BorderLayout.WEST);
        this.getContentPane().add(toolBar, BorderLayout.NORTH);
        this.getContentPane().add(g, BorderLayout.CENTER);
    }
}
