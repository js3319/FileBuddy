import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Button;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class GUI {
	private final int WIDTH = 60;
	private final int HEIGHT = 60;
	private JFrame frame;
	private JPanel panel;
	private Image backgroundImage;
	private JPanel panel2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel("com.jtatto.plaf.aluminium.AluminiumLookAndFeel");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setBackground(new Color(240, 255, 240));
		frame.setBounds(100, 100, 838, 593);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image image = getImage("files.png");
		// Image img2 = img.getScaledInstance(625, 418, Image.SCALE_DEFAULT);
		frame.setIconImage(image);
		backgroundImage = image;

		panel = new JPanel() {
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
		frame.getContentPane().add(panel);
	
		PieChart piechart = new PieChart("Dank");
		frame.getContentPane().add(piechart);
		
		
/*
		JButton btnNewButton = new JButton("New Folder");
		btnNewButton.setBorder(new RoundedBorder(10));
		btnNewButton.setForeground(SystemColor.textHighlightText);
		btnNewButton.setBackground(new Color(255, 218, 185));
		btnNewButton.addActionListener(new ActionListener() {
			// Put the action for making the folder
			public void actionPerformed(ActionEvent e) {
			}
		});
*/
		JButton btnNewButton_1 = new JButton("Select File");
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBackground(new Color(255, 222, 173));
		btnNewButton_1.addActionListener(new ActionListener() {
			// Put the action for Selecting File
			public void actionPerformed(ActionEvent e) {
			}
		});

		JButton btnStart = new JButton("Start");
		btnStart.setForeground(Color.BLACK);
		btnStart.setBackground(new Color(255, 222, 173));

		btnStart.addActionListener(new ActionListener() {
			// Put the action to Start the Program
			public void actionPerformed(ActionEvent e) {

			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("File Organizer");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Javanese Text", Font.BOLD, 18));
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBackground(new Color(255, 228, 196));
		btnNewButton.setForeground(UIManager.getColor("OptionPane.messageForeground"));
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnNewButton_3 = new JButton("New button");
		
		JButton btnNewButton_4 = new JButton("New button");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(359)
					.addComponent(lblNewLabel_1))
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
					.addComponent(lblNewLabel_1)
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

	private PieDataset createDataset() {
		// TODO Auto-generated method stub
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("90-100", 95);
		
		return dataset;
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