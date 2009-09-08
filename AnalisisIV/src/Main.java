import gui.MainFrame;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.watermark.SubstanceImageWatermark;
import org.jvnet.substance.watermark.SubstancePlanktonWatermark;

public class Main {
	public static void main(String[] args) {
		setLookAndFeel();
		new MainFrame();
	}
	
	public static void setLookAndFeel(){
		try {
			UIManager.setLookAndFeel(new SubstanceLookAndFeel());
			SubstanceLookAndFeel.setCurrentWatermark(new SubstancePlanktonWatermark());
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
		} catch (UnsupportedLookAndFeelException ulafe) {
			System.out.println("Substance failed to set");
		}
	}
}
