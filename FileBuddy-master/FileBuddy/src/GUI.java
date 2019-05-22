import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
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

import com.sun.glass.events.KeyEvent;

import java.awt.Button;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;





public class GUI{
	private String input;
	private String change;
	public Desktop desktop;
	private File file;
	public String PS;
	public JPanel panel;
	private Image backgroundImage;  
	public File fileName;
	private boolean isOrganized=false;
	private boolean isSelected=false;
	private final int WIDTH = 40;
	private final int HEIGHT = 40;
	private List outPartition;
	boolean changer = true;
	PieChart chart;
	
	


	private static DecimalFormat df = new DecimalFormat("0.00");
	
	JFrame frame;
	

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
		frame.setLayout(new BorderLayout());
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setBackground(new Color(240, 255, 240));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image image = getImage("files.png");
		// Image img2 = img.getScaledInstance(625, 418, Image.SCALE_DEFAULT);
		frame.setIconImage(image);
		backgroundImage = image;
		createChart();
		

		JPanel pnlChart =new XChartPanel(chart);

		JPanel panel = new JPanel()
		

{
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				int w = this.getWidth(), h = this.getHeight();

				Image img = backgroundImage.getScaledInstance(w, h, Image.SCALE_SMOOTH);
				System.out.println(w + "  " + h + " img " + img);
				g.drawImage(img, 0, 0, w, h, null);
			}
		};
		  
	
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(image));
		
		panel.setPreferredSize(new Dimension(800, 600));
		// panel.add(lblNewLabel);
		
		frame.getContentPane().add(panel, BorderLayout.WEST);

		JButton btnNewButton = new JButton("Select File");
		btnNewButton.addActionListener(new ActionListener() {
			// Put the action for making the folder
			public void actionPerformed(ActionEvent e) {
				getUserInput();
				System.out.println(fileName.length());
				System.out.println("Selecting File");
			}
		});
		
		

		JButton btnNewButton_1 = new JButton("Open File");
		btnNewButton_1.addActionListener(new ActionListener() {
			// Put the action for Selecting File
			public void actionPerformed(ActionEvent e) {
				
				if(isSelected) {
					openFile();
					
					System.out.println("Opening File");
					
					}
			}
		});
		

		JButton btnStart = new JButton("Start");

		btnStart.addActionListener(new ActionListener() {
			// Put the action to Start the Program
			public void actionPerformed(ActionEvent e) {
				if(isSelected) {
					createFolder(fileName.getPath());
					massMove(fileName);
					System.out.println("Start Organizing");
					}
			}
		});
		JButton btnNewButton_2 = new JButton("Show Directory");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getDirectory(fileName);
			}
		});
		
		JButton btnNewButton_3 = new JButton("NIGGA");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(changer) {
				frame.add(pnlChart, BorderLayout.EAST);
				frame.validate();
				frame.repaint();
				}
				else {
					frame.remove(pnlChart);
					frame.validate();
					frame.repaint();
				}
				changer=!changer;
				
			
				
			}
		});
		
		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.remove(pnlChart);
				frame.revalidate();
				frame.repaint();
			}
	});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(359)
					.addComponent(btnStart))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(27)
					.addComponent(btnNewButton_3)
					.addGap(34)
					.addComponent(btnNewButton_2)
					.addGap(34)
					.addComponent(btnNewButton_4))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(46)
					.addComponent(btnNewButton)
					.addGap(80)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(58)
					.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(91)
					.addComponent(btnStart)
					.addGap(98)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_3)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_4))
					.addGap(68)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(2)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))
					.addGap(50)
					.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(gl_panel);

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
			if(getFileExtension(file.getName()).toLowerCase().equals(getFileExtension(directory.getName()).toLowerCase())){
				moveFile(file.getPath(),file.getParent()+PS+getFileExtension(file.getName()));
				//	System.out.println(file.getParent()+PS+getFileExtension(file.getName()));

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
	
	public void getDirectory(File fileName) {
			

		    // Get all files from a directory.
		
		    File[] fList = new File(fileName.getParent()).listFiles();
		    
		   
		    System.out.println("a");
		   
		    List<String> filesSize = new ArrayList<String>();
		    
		    if(fList != null)
		        for (File file : fList) {      
		            if (file.isFile()) {
		            	if(file.length()>100000000)
		                filesSize.add(file.getPath()+", "+df.format(file.length()/1000000000.0)+"GB");
		            	if(file.length()>1000000&&file.length()<100000000)
		            		filesSize.add(file.getPath()+", "+df.format(file.length()/1000000.0)+"MB");
		            	if(file.length()>1000&&file.length()<1000000)
		            		filesSize.add(file.getPath()+", "+df.format(file.length()/1000)+"KB");
		            	}
		            	
		        }
			System.out.println(filesSize);
		    }

	public void createChart() {
		chart = new PieChartBuilder().width(500).height(500).title("My Pie Chart").theme(ChartTheme.GGPlot2).build();
		System.out.println(frame.getWidth());
	    // Customize Chart
	    chart.getStyler().setLegendVisible(false);
	    chart.getStyler().setAnnotationType(AnnotationType.LabelAndPercentage);
	    chart.getStyler().setAnnotationDistance(1.15);
	    chart.getStyler().setPlotContentSize(.7);
	    chart.getStyler().setStartAngleInDegrees(90);

	    // Series
	    //A
	    chart.addSeries("Prague", 2);
	    chart.addSeries("Dresden", 4);
	    chart.addSeries("Munich", 34);
	    chart.addSeries("Hamburg", 22);
	    chart.addSeries("Berlin", 29);
	    
	}

}

	

