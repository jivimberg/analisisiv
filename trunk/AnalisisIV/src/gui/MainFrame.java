package gui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{

	LeftPanel lPanel;
	Graphic g;
	
    public MainFrame() {
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setTitle("Analisis IV");
        
        this.getContentPane().setLayout(new BorderLayout());
//        corrector.listeners.add(this);
//        buttonsManager = new ButtonsManager(corrector, this);
        initComponents();
//        this.setVisible(false);
    }

    private void initComponents() {
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
        JToolBar toolBar = new JToolBar();
        lPanel = new LeftPanel(showNumberOffsetAction, restartAxisAction);
        g = new Graphic();
        this.getContentPane().add(lPanel, BorderLayout.WEST);
        this.getContentPane().add(toolBar, BorderLayout.NORTH);
        this.getContentPane().add(g, BorderLayout.CENTER);
    }
    
    
    
    
    
    
	
	
	
	//arreglar!!!
    //solo de prueba!!!
	private Action showNumberOffsetAction = new AbstractAction("Mostrar números") {
        public void actionPerformed(ActionEvent e) {
        	g.showNumberOffset(!g.getShowNumberOffset());
        }
    };
    
    private Action restartAxisAction = new AbstractAction("Centrar ejes") {
        public void actionPerformed(ActionEvent e) {
        	g.resetPosition();
        }
    };
}
