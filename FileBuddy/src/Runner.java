import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.knowm.xchart.XChartPanel;

import org.knowm.xchart.PieChart;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;
import java.util.Scanner;
import java.util.prefs.Preferences;
import java.io.IOException;


public class Runner {
	String input;
	String change;
	Desktop desktop;
	File file;
	GUI window;
	boolean passed=false;
	int count =3;	
	private String password;
	String defaultPass="password";
	Preferences UserPrefs = Preferences.userNodeForPackage(Runner.class);
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	JFrame frame;

	public Runner() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					//remove();
					if(UserPrefs.get("password",defaultPass)!=defaultPass)
						password=UserPrefs.get("password",defaultPass);
					
					
					checkPass();
					System.out.println(password);
					passwordCreator();
					
					
					
					//start();
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
				}
			}
		});
	} 
	public static void main(String[]args) {
		
		Runner run = new Runner();		
		
		

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
	public void passwordCreator() {
	frame = new JFrame("Password");
	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(400,100);
	frame.setLocationRelativeTo(null);	
	JLabel label = new JLabel("Enter password");
	JPanel panel = new JPanel();
	frame.add(panel);
	
	JPasswordField pass = new JPasswordField(10);
	pass.setEchoChar('*');
	pass.addActionListener(new AL());
	panel.add(label, BorderLayout.WEST);
	panel.add(pass, BorderLayout.EAST);

	frame.setVisible(true);
	
	}
	class AL implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
		
		JPasswordField input = (JPasswordField) e.getSource();
		char[]passy=input.getPassword();
		String p = new String(passy);
		int y= 0;
		
		if(count>0) {
		if(hashConverter(p).equals(password)) {
			JOptionPane.showMessageDialog(null, "Correct");
			passed=true;
		
				
				window = new GUI();
				checkOS();
				window.frame.setVisible(true);
				frame.setVisible(false);
			
		}
		if(!(hashConverter(p).equals(password))) {
			JOptionPane.showMessageDialog(null, "Incorrect, you have "+count+" tries left");
			count--;
		}
		}
		else {
			JOptionPane.showMessageDialog(null, "Locked");
		}
		}
	
		
	}
	
	public String hashConverter(String password){
		String passwordToHash = password;
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
            return generatedPassword;
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
      	generatedPassword=password;
      	return generatedPassword;
    }
	public void checkPass() {
		if (!(password !=null &&  !password.isEmpty())) {			
			JFrame setPass = new JFrame("Set Password");
			password=hashConverter(JOptionPane.showInputDialog(setPass, "Set password"));
			Image image = getImage("files.png");
			setPass.setIconImage(image);
			setPass.setSize(400,100);
			setPass.setLocationRelativeTo(null);			
			setPass.setVisible(true);
			
			UserPrefs.put("password", password);
			
			
			}
	}
	public void remove() {
	 UserPrefs.remove("password");
	}
	
	
	public Image getImage(String str) {
		Image img = null;
		try {
			img = ImageIO.read(this.getClass().getResource("res/images/" + str));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return img;
	}


}



