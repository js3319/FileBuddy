
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Button;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

public class GUI {
	String input;
	String change;
	Desktop desktop;
	File file;
	String PS;
	File fileName;
	private final int WIDTH = 40;
	private final int HEIGHT = 40;
	JFrame frame=new JFrame();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GUI window = new GUI();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame.getContentPane().setBackground(new Color(240, 255, 240));
		frame.setBounds(100, 100, 503, 338);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		frame.getContentPane().setLayout(null);
		
		JButton btnSelectFile = new JButton("Select File");
		btnSelectFile.setFont(new Font("Javanese Text", Font.BOLD, 12));
		btnSelectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getUserInput();
				System.out.println("Selecting File");
				
				
			}
		});
		btnSelectFile.setBounds(346, 111, 103, 38);
		frame.getContentPane().add(btnSelectFile);
		
		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font("Javanese Text", Font.BOLD, 14));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				massMove(fileName);
				System.out.println("Start Organizing");
				
				
				
			}
		});
		btnStart.setBounds(196, 180, 89, 35);
		frame.getContentPane().add(btnStart);
		
		JButton btnNewFolder = new JButton("New Folder");
		btnNewFolder.setFont(new Font("Javanese Text", Font.BOLD, 12));
		btnNewFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Creating New Folder");
				
				
			}
		});
		btnNewFolder.setBounds(46, 114, 103, 35);
		frame.getContentPane().add(btnNewFolder);
		
		JLabel lblNewLabel = new JLabel("                    File Organizer");
		lblNewLabel.setToolTipText("");
		lblNewLabel.setFont(new Font("Nirmala UI", Font.BOLD, 23));
		lblNewLabel.setBounds(46, 29, 389, 67);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("res/images/files.png")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(0, 0, 487, 299);
		frame.getContentPane().add(label);
		
		
		
		

	
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
		 try {
				FileRetriever finder= new FileRetriever(input);
		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("finder input invalid");
				getUserInput();
			}
		 
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
	public void createFolder(String name) {
		fileName=new File(name);	
		//fileName is the original input
		String parent = fileName.getParent();
		//gets parent directory
		String folderName = parent +PS+ getFileExtension(name);
		//generates foldername based on parent directory and file extension name
		System.out.println (folderName + "1");
		new File(folderName).mkdirs();
		System.out.println(getFileExtension(name + "2"));
	}
	
	public void setOS(String s) {
		PS= s;
	}
	
	public String getFileExtension(String name) {
		if(name.indexOf(".")>0) {


			return(name.substring(1+name.lastIndexOf(".")));}
		return "";
	}

	public void openFolder() {

	}
	public void moveFile(String original, String newLoc) {
		File select= new File(original);
		System.out.println(newLoc+actualFileName(original)+"3");
		//System.out.println(select.getName()+"4");

		select.renameTo(new File(newLoc+PS+actualFileName(original)));


	}
	public String actualFileName(String sample) {
		int count = 1;
		for(int x=sample.length();x>0;x--) {
			if (sample.substring(x-1,x).equals(PS)){
				count--;
				if(count ==0) {
					return PS+sample.substring(x);
				}

			}
		}
		return null;
	}

	public String actualFileName(File file) {
		return file.getName();
	}
	
	public void massMove(File directory) {
		List<String> results = new ArrayList<String>();


		File[] files = new File(directory.getParent()).listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null. 

		for (File file : files) {
			if (file.isFile()) {
				results.add(file.getName());
			}
			if(getFileExtension(file.getName()).toLowerCase().equals(getFileExtension(directory.getName()).toLowerCase())){
				moveFile(file.getPath(),file.getParent()+PS+getFileExtension(file.getName()));
				//	System.out.println(file.getParent()+PS+getFileExtension(file.getName()));

			}
		}
	}
}