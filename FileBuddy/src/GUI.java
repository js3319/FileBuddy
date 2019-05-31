import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.prefs.Preferences;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.PieStyler.AnnotationType;
import org.knowm.xchart.style.Styler.ChartTheme;

import java.awt.Button;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;



public class GUI {
	private String input;
	private String change;
	public Desktop desktop;
	private File file;
	public String PS;
	private JPanel panel;
	private Image backgroundImage;
	private JPanel panel2;
	public File fileName;
	private boolean isOrganized=false;
	private boolean isSelected=false;
	private final int WIDTH = 40;
	private final int HEIGHT = 40;
	private static DecimalFormat df = new DecimalFormat("0.00");
	PieChart chart;
	JFrame frame=new JFrame();
	boolean changer;
	JPanel pnlChart;
	int count= 0;
	ArrayList<FileData>fileContainer=new ArrayList<>();
	List<String> fileList = new ArrayList<String>();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();






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

		frame = new JFrame();
		frame.setTitle("FileBuddy");
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setBounds(50, 50, screenSize.width, screenSize.height);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image image = getImage("files.png");
		// Image img2 = img.getScaledInstance(625, 418, Image.SCALE_DEFAULT);
		frame.setIconImage(image);
		backgroundImage = image;



		frame.getContentPane().setLayout(null);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(0, 0, screenSize.width, screenSize.height);
		frame.getContentPane().add(tabbedPane);
		panel = new JPanel() 


		{
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				int w = this.getWidth(), h = this.getHeight();
				BufferedImage img =((BufferedImage) image).getSubimage(0,0,w,h);
				g.drawImage(img, 0, 0, null);

			}
		};


		// panel_2.setPreferredSize(new Dimension(800, 800));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(image));

		panel.setPreferredSize(new Dimension(800, 600));
		// panel.add(lblNewLabel);
		frame.getContentPane().add(panel);
		tabbedPane.addTab("Starting", null, panel, null);

		JButton btnNewButton = new JButton("Select File");
		btnNewButton.setBounds(587, 329, 89, 43);
		btnNewButton.addActionListener(new ActionListener() {
			// Put the action for making the folder
			public void actionPerformed(ActionEvent e) {
				getUserInput();
				System.out.println(fileName.length());
				System.out.println("Selecting File");

			}
		});
		panel.setLayout(null);
		panel.add(btnNewButton);


		JButton btnNewButton_1 = new JButton("Open File");
		btnNewButton_1.setBounds(493, 206, 105, 43);
		btnNewButton_1.addActionListener(new ActionListener() {
			// Put the action for Selecting File
			public void actionPerformed(ActionEvent e) {
				openFile();

			}
		});
		panel.add(btnNewButton_1);

		JButton btnStart = new JButton("Organize");
		btnStart.setBounds(299, 206, 105, 43);
		btnStart.addActionListener(new ActionListener() {
			// Put the action to Start the Program
			public void actionPerformed(ActionEvent e) {
				if(isSelected) {
					createFolder(fileName.getPath());
					massMove(fileName);
					if(isSelected) {
						openFile();

						System.out.println("Opening File");

					}
					System.out.println("Start Organizing");
				}
			}
		});
		panel.add(btnStart);

		JPanel panel_1 = new JPanel() {

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				int w = this.getWidth(), h = this.getHeight();

				Image img = backgroundImage.getScaledInstance(w, h, Image.SCALE_SMOOTH);
				System.out.println(w + "  " + h + " img " + img);
				g.drawImage(img, 0, 0, w, h, null);
			}
		};
		tabbedPane.addTab("Graph", null, panel_1, null);


		JButton btnNewButton_2 = new JButton("Show Directory (used for testing purposes)");
		btnNewButton_2.setBounds(1020, 208, 105, 43);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getDirectory(new File(fileName.getParent()));
			}
		});
		panel.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Remove Password");
		btnNewButton_3.setBounds(674, 206, 105, 43);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Preferences UserPrefs = Preferences.userNodeForPackage(Runner.class);
				UserPrefs.remove("password");
			}
		});
		panel.add(btnNewButton_3);
		JButton btnNewButton_4 = new JButton("Graph");
		btnNewButton_4.setBounds(850, 206, 105, 43);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				createChart();
				if(pnlChart!=null) {
				panel_1.remove(pnlChart);
				pnlChart=null;
				}
				pnlChart=new XChartPanel(chart);
				panel_1.add(pnlChart);
				frame.validate();
				frame.repaint();





			}
		});

		panel.add(btnNewButton_4);
		JLabel lblFileOrganizer = new JLabel("File Organizer");
		lblFileOrganizer.setBounds(534, 89, 259, 57);
		lblFileOrganizer.setFont(new Font("Segoe Print", Font.BOLD, 32));
		lblFileOrganizer.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblFileOrganizer);


		JButton btnNewButton_5 = new JButton("Remove Chart");
		btnNewButton_5.setBounds(390, 329, 89, 43);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.remove(pnlChart);
				frame.validate();
				frame.repaint();
			}
		});
		panel.add(btnNewButton_5);

		JButton btnNewButton_6 = new JButton("Help");
		btnNewButton_6.setBounds(773, 329, 89, 43);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "<html>1. If this is your first time using this program, you need to set a password. If you wish to remove your password click the Remove Password button. All passwords are encrypted.<br> 2. You need to select a File as a reference point.<br>3. Choose operation to initiate, Organize organizes the file you selected by file extension type, Graph shows a visual of your disk space of the selected file's parent directory.</html>");
						
			}
		});
		panel.add(btnNewButton_6);

				JButton btnNewButton_7 = new JButton("Amador Website");
				btnNewButton_7.setBounds(587, 329, 89, 43);
				btnNewButton_7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					desktop = Desktop.getDesktop();
					if(Desktop.isDesktopSupported()) {


						//URI is essentially url, this segment opens 
								URI uri;
								try {
									uri = new URI("https://amador.pleasantonusd.net/");
									desktop.browse(uri);
								} catch (URISyntaxException | IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
					}
				}
			});
		
				panel.add(btnNewButton_7);



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
		isSelected=true;
		//input = JOptionPane.showInputDialog("Enter Directory");	

		desktop = Desktop.getDesktop();
		if(Desktop.isDesktopSupported()) {


			//URI is essentially url, this segment opens 
//					URI uri;
//					try {
//						uri = new URI("https://amador.pleasantonusd.net/");
//						desktop.browse(uri);
//					} catch (URISyntaxException | IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
			File a = new File(input);
			File file = new File (a.getParent());
			//		try {
			//		desktop.open(file);
			//	} catch (IOException e) {
			//		// TODO Auto-generated catch block
			//		e.printStackTrace();
			//	}

			System.out.println(input);
			fileName=a;
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
		return null;
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
				if(count==0) {
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
			if(getFileExtension(file.getName())!=null) {
				if(getFileExtension(file.getName()).toLowerCase().equals(getFileExtension(directory.getName()).toLowerCase())){
					moveFile(file.getPath(),file.getParent()+PS+getFileExtension(file.getName()));
					//	System.out.println(file.getParent()+PS+getFileExtension(file.getName()));
				}
			}
		}
	}
	public void openFile(){
		try {
			String parent = fileName.getParent();
			File folder = new File(parent);
			File openLoc;
			if(isOrganized) {
				openLoc = new File(parent+PS+getFileExtension(fileName.getName()));
			}
			else {
				openLoc = new File(parent+PS+getFileExtension(fileName.getName()));}
			System.out.println(openLoc);
			desktop.open(openLoc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public void getDirectory (File fileName) {

		File[] fList=fileName.listFiles();

		// Get all files from a directory.






		//   if(fList != null)
		for (File file : fList) {   

			if (file.isFile()) {
				fileContainer.add(new FileData(file.length(),file.getName()));

				//            	if(fileList.indexOf(getFileExtension(file.getPath()))>=0){
				//            		
				//            		fileSizes.add(fileList.indexOf(getFileExtension(file.getPath())),Integer.toString(((int)file.length())));
				//if(fileSizes.size()>0) {
				//	fileSizes.remove(fileList.indexOf(getFileExtension(file.getPath())));
				//}
				//            	}
				//            	else {
				//            		fileList.add(getFileExtension(file.getAbsolutePath()));
				//            		fileSizes.add(Integer.toString((int)file.length()));
				//            	}
				//            	if(file.length()>1000000000)
				//                filesSize.add(file.getPath()+", "+df.format(file.length()/1000000000.0)+"GB");
				//            	if(file.length()>1000000&&file.length()<100000000)
				//            		filesSize.add(file.getPath()+", "+df.format(file.length()/1000000.0)+"MB");
				//            	if(file.length()>1000&&file.length()<1000000)
				//            		filesSize.add(file.getPath()+", "+df.format(file.length()/1000.0)+"KB");
			}


			if(file.isDirectory()) {
				System.out.println(file.getName());
				getDirectory(file);


			}
		}

	}

	//return fileList;


	public void checkDupe() {
		int count=0;
		for(int x=0;x<fileContainer.size();x++){
			for(int y=0;y<fileContainer.size();y++) {
				if(fileContainer.get(x)!=null&&fileContainer.get(y)!=null&&fileContainer.get(x).compareTo(fileContainer.get(y))==0&&(x!=y)){
					fileContainer.get(x).merge(fileContainer.get(y));
					fileContainer.set(x,null);
				}
			}
		}
		if(count>0) {
			checkDupe();
		}
	}


	public void createChart() {
		chart = new PieChartBuilder().width((int) screenSize.getWidth()-300).height((int) screenSize.getHeight()-300).title(fileName.getParent()).theme(ChartTheme.GGPlot2).build();
		System.out.println(frame.getWidth());
		// Customize Chart
		chart.getStyler().setLegendVisible(true);
		chart.getStyler().setAnnotationType(AnnotationType.LabelAndPercentage);
		chart.getStyler().setAnnotationDistance(1.15);
		chart.getStyler().setPlotContentSize(.7);
		chart.getStyler().setStartAngleInDegrees(90);
		getDirectory(new File(fileName.getParent()));
		Color[]sliceColors= new Color[fileContainer.size()];



		checkDupe();
		while(fileContainer.remove(null));

		for(int x=0;x<fileContainer.size();x++) {
			System.out.println(fileContainer.get(x).getName());
			System.out.println(fileContainer.get(x).getSize());
		}

		for(int x=0; x<fileContainer.size();x++) {
			Random rand = new Random();
			float r = rand.nextFloat();
			float g = rand.nextFloat();
			float b = rand.nextFloat();
			sliceColors[x]= new Color(r,g,b);	
			chart.getStyler().setSeriesColors(sliceColors);
			if(fileContainer.get(x).fileExtension()!=""&&fileContainer.get(x)!=null) {

				chart.addSeries(fileContainer.get(x).fileExtension()+fixedName(fileContainer.get(x).getSize()),fileContainer.get(x).getSize());
			}

		}


	}
	public String fixedName(long size) {
		if(size>1000000000)
			return(", "+df.format(size/1000000000.0)+"GB");
		if(size>1000000&&size<1000000000)
			return(", "+df.format(size/1000000.0)+"MB");
		if(size>0&&size<1000000)
			return(", "+df.format(size/1000.0)+"KB");
		else {
			return"null";
		}

	}


}







