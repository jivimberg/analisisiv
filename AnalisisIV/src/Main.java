import functions.Constant;
import functions.Cos;
import functions.Sin;
import functions.Variable;
import functions.basics.Sum;
import gui.MainFrame;
import gui.SplashScreen;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.watermark.SubstancePlanktonWatermark;

public class Main {
	private static MainFrame fm;

	public static void main(String[] args) {
		setLookAndFeel();
		 fm = new MainFrame();
		 showSplashScreen();
//		 new MainFrame().setVisible(true);

		Sum s = new Sum();
		s.addFunction(new Cos(new Variable(-1)));
		s.addFunction(new Constant(4));
		s.addFunction(new Sin(new Variable(-0.5)));

		System.out.println("0: " + s.toString());
		System.out.println("1: " + s.derive().toString());
	}

	public static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(new SubstanceLookAndFeel());
			SubstanceLookAndFeel
					.setCurrentWatermark(new SubstancePlanktonWatermark());
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
		} catch (UnsupportedLookAndFeelException ulafe) {
			System.out.println("Substance failed to set");
		}
	}

	public static void showSplashScreen() {
		SplashScreen splashScreen = new SplashScreen("images/GStar.gif");
		splashScreen.open();
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		fm.setVisible(true);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		splashScreen.close();
	}
}
