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

		JButton btnNewButton = new JButton("New Folder");
		btnNewButton.addActionListener(new ActionListener() {
			// Put the action for making the folder
			public void actionPerformed(ActionEvent e) {
			}
		});

		JButton btnNewButton_1 = new JButton("Select File");
		btnNewButton_1.addActionListener(new ActionListener() {
			// Put the action for Selecting File
			public void actionPerformed(ActionEvent e) {
			}
		});

		JButton btnStart = new JButton("Start");

		btnStart.addActionListener(new ActionListener() {
			// Put the action to Start the Program
			public void actionPerformed(ActionEvent e) {

			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("File Organizer");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Javanese Text", Font.BOLD, 18));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(186)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
					.addGap(274)
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
					.addGap(166))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(101)
					.addComponent(btnStart, GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
					.addGap(77))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(361)
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(334))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(165)
					.addComponent(lblNewLabel_1)
					.addGap(43)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton))
					.addGap(117)
					.addComponent(btnStart)
					.addContainerGap(135, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);

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