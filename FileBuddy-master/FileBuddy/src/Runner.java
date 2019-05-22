import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.PieStyler.AnnotationType;
import org.knowm.xchart.style.Styler.ChartTheme;



public class Runner{
	String input;
	String change;
	Desktop desktop;
	File file;
	GUI window;

	public Runner() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					window = new GUI();
					
					checkOS();
					window.frame.setVisible(true);
					//start();
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
				}
			}
		});
	} 
	


	public void getUserInput() {
		//mac os is forward slash /users/js/xxxxxxxx
		//ex. "/Users/js/Downloads/IMG_1388.jpg/"
		//perhaps put hints to help user guide, or adapt gui to have user select directory path
		boolean works;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(fileChooser);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			System.out.println("Selected file: " + selectedFile.getAbsolutePath());
			input=selectedFile.getAbsolutePath();
			works = selectedFile.exists();
		}

		//input = JOptionPane.showInputDialog("Enter Directory");	

		desktop = Desktop.getDesktop();
		if(Desktop.isDesktopSupported()) {


			//URI is essentially url, this segment opens 
			//		URI uri;
			//		try {
			//			uri = new URI("https://www.reddit.com/");
			//			desktop.browse(uri);
			//		} catch (URISyntaxException | IOException e1) {
			//			// TODO Auto-generated catch block
			//			e1.printStackTrace();
			//		}
			File a = new File(input);
			File file = new File (a.getParent());
			try {
				desktop.open(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println(input);
			//	System.out.println(desktop);
			if(file.isDirectory()) {
				System.out.println("is directory");
			}
		}
		else{
			System.out.println("desktop not supported!");
		}

		if(works=false)
			getUserInput();

		//	change = JOptionPane.showInputDialog("Enter Desired Change");	
	}
	//	public void openFile(String in) {
	//		File file=new File(in);
	//		desktop = Desktop.getDesktop();
	//		if(Desktop.isDesktopSupported()) {
	//		try {
	//			desktop.open(file);
	//		} catch (IOException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//		}
	//	}
	public void start() {
	

		//put methods here
		//	getUserInput();

		//checkInput();

		//			try {
		//				FileRetriever finder= new FileRetriever(input);
		//		
		//			} catch (IOException e) {
		//				// TODO Auto-generated catch block
		//				e.printStackTrace();
		//				System.out.println("finder input invalid");
		//				start();
		//			}
		//		openFile(file.getParent());

	}

	public void checkInput() {
		//	if(input)

	}
	public void checkOS() {
		if(System.getProperty("os.name").equals("Mac OS X")){

			window.setOS("/");
		}
		else {
			window.setOS("\\");
		}
	}
	


	public static void main(String[]args) throws IOException {
		Runner run = new Runner();		
		
	}
	}
	
	
	





