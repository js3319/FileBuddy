
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.Icon;
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

import com.sun.glass.events.MouseEvent;

import java.awt.Button;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JToggleButton;

public class GUI {
	String input;
	String change;
	Desktop desktop;
	File file;
	String PS;
	File fileName;
	boolean isSelected = false;
	private final int WIDTH = 40;
	private final int HEIGHT = 40;
	JFrame frame = new JFrame();
	private Image backgroundImage;
	private JPanel panel;

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
		frame.setBackground(Color.WHITE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		frame.getContentPane().setBackground(new Color(240, 255, 240));
		frame.setBounds(50, 50 , 1127, 744);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(0, 0, 1366, 704);
		frame.getContentPane().add(tabbedPane);
		Image image = getImage("files.png");
		backgroundImage = image;
						
						
						panel = new JPanel() {
							@Override
							public void paintComponent(Graphics g) {
								super.paintComponent(g);
								int w = this.getWidth() , h = this.getHeight();
								Image img = backgroundImage.getScaledInstance(w, h, Image.SCALE_SMOOTH);
									System.out.println(w + "  " + h + " " + backgroundImage);
								g.drawImage(img,0,0,w,h,null);
								
							}
						};
						//1st tab
						tabbedPane.addTab("New tab", null, panel, null);
						
						JLabel lblFileOrganzier = new JLabel("File Organzier");
						lblFileOrganzier.setBounds(158, 178, 117, 37);
						
								JButton btnSelectFile = new JButton("Select File");
								btnSelectFile.setBounds(10, 253, 103, 38);
								btnSelectFile.setFont(new Font("Javanese Text", Font.BOLD, 12));
								panel.setLayout(null);
								panel.add(lblFileOrganzier);
								panel.add(btnSelectFile);
								
								JButton btnNewButton = new JButton("New button");
								btnNewButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
									}
								});
								btnNewButton.setBounds(10, 292, 89, 23);
								panel.add(btnNewButton);
								btnSelectFile.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										getUserInput();
										System.out.println(fileName.length());
										System.out.println("Selecting File");

									}
								});
								
						
						JPanel panel_1 = new JPanel();
						//Creates a new tab for the piechart
						tabbedPane.addTab("PieChart", null, panel_1, null);
						
						
		
						JButton btnNewFolder = new JButton("Open File");
						tabbedPane.addTab("New tab", null, btnNewFolder, null);
						btnNewFolder.setFont(new Font("Javanese Text", Font.BOLD, 12));
						
						btnNewFolder.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (isSelected) {
									openFile();

									System.out.println("Opening File");

								}

							}
						});
						
						JPanel panel_2 = new JPanel();
						panel_2.setBackground(Color.WHITE);
						tabbedPane.addTab("New tab", null, panel_2, null);
						panel_2.setLayout(null);
						
						JLabel lblNewLabel = new JLabel("") {public void paintComponent(Graphics g) {
							super.paintComponent(g);
							/*
							int w = this.getWidth() ,h = this.getHeight();
							Image img = backgroundImage.getScaledInstance(w, h, Image.SCALE_SMOOTH);
							*/
							
							g.drawImage(image, 0, 0, null);							
						}};
						lblNewLabel.setBounds(0, 0, 541, 443);
						panel_2.add(lblNewLabel);
				/*		
						JLabel lblNewLabel_1 = new JLabel("") {
							public void paintComponent(Graphics g) {
							super.paintComponent(g);
						
							g.drawImage(image, 0, 100,null);
						}};
						lblNewLabel_1.setBounds(0, 278, 527, 387);
						panel_2.add(lblNewLabel_1);
					*/	
						JLabel label = new JLabel("") {
							public void paintComponent(Graphics g) {
								super.paintComponent(g);
							
								g.drawImage(image, 0,10,null);
							}};
						label.setBounds(598, 39, 527, 297);
						panel_2.add(label);
						
						JLabel label_1 = new JLabel("") {
							public void paintComponent(Graphics g) {
								super.paintComponent(g);
							
								g.drawImage(image, 0, 100,null);
							}};
						label_1.setBounds(742, 289, 510, 387);
						panel_2.add(label_1);
						
						JLabel label2 = new JLabel("File Organizer");
						
						JButton btnNewButton_1 = new JButton("New button");
						btnNewButton_1.setBounds(346, 255, 89, 23);
						panel_2.add(btnNewButton_1);
						
						JButton btnNewButton_2 = new JButton("New button");
						btnNewButton_2.setBounds(559, 255, 89, 23);
						panel_2.add(btnNewButton_2);

						
	//	Image img = new ImageIcon(this.getClass().getResource("res/images/files.png")).getImage();
	
		

	}
	
	public JLabel makeANewJLabel() {
		for(int x = 0; x<frame.getWidth(); x++) {
			
		}
		return null;
		
	}

	private Image getImage(String str) {

		Image img = null;
		try {
			img = ImageIO.read(this.getClass().getResource("res/images/" + str));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return img;

	}

	public void getUserInput() {
		// mac os is forward slash /users/js/xxxxxxxx
		// ex. "/Users/js/Downloads/IMG_1388.jpg/"
		// perhaps put hints to help user guide, or adapt gui to have user select
		// directory path
		boolean works;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(fileChooser);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			System.out.println("Selected file: " + selectedFile.getAbsolutePath());
			input = selectedFile.getAbsolutePath();
			works = selectedFile.exists();
		}
		isSelected = true;
		// input = JOptionPane.showInputDialog("Enter Directory");

		desktop = Desktop.getDesktop();
		if (Desktop.isDesktopSupported()) {

			// URI is essentially url, this segment opens
//		URI uri;
//		try {
//			uri = new URI("https://www.reddit.com/");
//			desktop.browse(uri);
//		} catch (URISyntaxException | IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
			File a = new File(input);
			File file = new File(a.getParent());
//		try {
//		desktop.open(file);
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}

			System.out.println(input);
			fileName = a;
			// System.out.println(desktop);
			if (file.isDirectory()) {
				System.out.println("is directory");
			}
		} else {
			System.out.println("desktop not supported!");
		}

		if (works = false)
			getUserInput();
//		 try {
//				FileRetriever finder= new FileRetriever(input);
//		
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				System.out.println("finder input invalid");
//				getUserInput();
//			}
//		 
		// change = JOptionPane.showInputDialog("Enter Desired Change");
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
		fileName = new File(name);
		// fileName is the original input
		String parent = fileName.getParent();
		// gets parent directory
		String folderName = parent + PS + getFileExtension(name);
		// generates foldername based on parent directory and file extension name
		System.out.println(folderName + "1");
		new File(folderName).mkdirs();
		System.out.println(getFileExtension(name + "2"));
	}

	public void setOS(String s) {
		PS = s;
	}

	public String getFileExtension(String name) {
		if (name.indexOf(".") > 0) {

			return (name.substring(1 + name.lastIndexOf(".")));
		}
		return "";
	}

	public void openFolder() {

	}

	public void moveFile(String original, String newLoc) {
		File select = new File(original);
		System.out.println(newLoc + actualFileName(original) + "3");
		// System.out.println(select.getName()+"4");

		select.renameTo(new File(newLoc + PS + actualFileName(original)));

	}

	public String actualFileName(String sample) {
		int count = 1;
		for (int x = sample.length(); x > 0; x--) {
			if (sample.substring(x - 1, x).equals(PS)) {
				count--;
				if (count == 0) {
					return PS + sample.substring(x);
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
		// If this pathname does not denote a directory, then listFiles() returns null.

		for (File file : files) {
			if (file.isFile()) {
				results.add(file.getName());
			}
			if (getFileExtension(file.getName()).toLowerCase()
					.equals(getFileExtension(directory.getName()).toLowerCase())) {
				moveFile(file.getPath(), file.getParent() + PS + getFileExtension(file.getName()));
				// System.out.println(file.getParent()+PS+getFileExtension(file.getName()));

			}
		}
	}

	public void openFile() {
		try {
			String parent = fileName.getParent();
			File folder = new File(parent);
			File openLoc = new File(parent + PS + getFileExtension(fileName.getName()));
			System.out.println(openLoc);
			desktop.open(openLoc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}