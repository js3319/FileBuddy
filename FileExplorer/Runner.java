import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.EventQueue;
import java.io.File;
public class Runner {
String input;
public Runner() {
	EventQueue.invokeLater(new Runnable() {
		@Override
		public void run() {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				start();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			}
		}
	});
} 
	public static void main(String[]args) {
		Runner run = new Runner();
	}

	
	public void getUserInput() {
		String ni = JOptionPane.showInputDialog("Enter ni");	
	}
	public void start() {
		getUserInput();
	}
}
