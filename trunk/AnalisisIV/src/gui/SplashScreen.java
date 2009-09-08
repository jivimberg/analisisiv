package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class SplashScreen extends JWindow {
	private JLabel logoLabel;
	private JProgressBar progressBar;
	
	public SplashScreen (String logoImagePath) {
		initComponents(logoImagePath);
		layoutComponents();
		setVisible(true);
	}

	private void initComponents(String logoImagePath) {
		logoLabel = new JLabel(new ImageIcon(logoImagePath));
		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
	}

	private void layoutComponents() {
		setLayout(new BorderLayout(5, 5));
		
		add(logoLabel, BorderLayout.CENTER);
		add(progressBar, BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
	}

	public void open () {
		Timer timer = new Timer (Integer.MAX_VALUE, new ActionListener() {
			public void actionPerformed (ActionEvent event) {
				((Timer) event.getSource()).stop();
				close();
			};
		});

		timer.start();
	}

	/**
	 * Close the splash screen.
	 */
	public void close(){
		setVisible (false);
		dispose();
	}
}