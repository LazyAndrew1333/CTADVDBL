import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class transactions {

	JFrame frame;
	
	Font CanvaSans, Inter;
	
	static int startingYPos = 11, currentYPos = 11;
	
	JPanel panel;
	
	boolean actionAllowed = false;
	private JTextField tableSearch;
	
	public void refresh() {
		currentYPos = startingYPos;
		panel.removeAll();
		loadTable(sortTable(8), "", false);
	}
	
	public String sortTable(int sortID) {
		panel.removeAll();
		currentYPos = startingYPos;
		
		switch (sortID) {
			case 0:
				return "ORDER BY campaign_id ASC ";
			case 1:
				return "ORDER BY campaign_id DESC ";
			case 2:
				return "ORDER BY plan_id ASC ";
			case 3:
				return "ORDER BY plan_id DESC ";
			case 4:
				return "ORDER BY sales_amount ASC ";
			case 5:
				return "ORDER BY sales_amount DESC ";
			case 6:
				return "ORDER BY DATE_FORMAT(sales_date, '%Y-%m') ASC";
			case 7:
				return "ORDER BY DATE_FORMAT(sales_date, '%Y-%m') DESC ";
			case 8:
				return "ORDER BY sales_id ASC ";
			case 9:
				return "ORDER BY sales_id DESC ";
			default:
				return "ORDER BY sales_id ASC ";
		}
	}
	
	public void searchTable(int sortID, String str) {
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		currentYPos = startingYPos;
		
		loadTable(sortTable(sortID), String.format("WHERE sales_id = %s OR sales_date = %s OR sales_amount = %s OR plan_id = %s OR campaign_id = %s ", str, str, str, str, str), !str.isBlank());
	}
	
	void loadTable(String sort, String search, boolean haveSearch) {
		try {
			Connection conn = DriverManager.getConnection(app.url, app.user, app.password);
			PreparedStatement preparedStmt = (haveSearch) ? conn.prepareStatement(
					"SELECT * " +
				    "FROM sales_data " +
				    search +
				    sort) :
				    conn.prepareStatement(
					"SELECT * " +
					"FROM sales_data " +
					sort);

		    try (ResultSet resultSet = preparedStmt.executeQuery()) {
              while (resultSet.next()) {
              	
            	JLabel sales_id = new JLabel("Sales ID");
          		sales_id.setFont(Inter.deriveFont(14f));
          		sales_id.setHorizontalAlignment(SwingConstants.CENTER);
          		sales_id.setBounds(18, currentYPos, 130, 33);
          		sales_id.setText("SID#" + resultSet.getString("sales_id"));
          		panel.add(sales_id);
          		
          		JLabel plan_id = new JLabel("Plan ID");
          		plan_id.setFont(Inter.deriveFont(14f));
          		plan_id.setHorizontalAlignment(SwingConstants.CENTER);
          		plan_id.setBounds(146, currentYPos, 130, 33);
          		plan_id.setText("PID#" + resultSet.getString("plan_id"));
          		panel.add(plan_id);
          		
          		JLabel sales_amount = new JLabel("Sales Amount");
          		sales_amount.setFont(Inter.deriveFont(14f));
          		sales_amount.setHorizontalAlignment(SwingConstants.CENTER);
          		sales_amount.setBounds(282, currentYPos, 161, 33);
          		DecimalFormat formatter = new DecimalFormat("#,###.00");
          		sales_amount.setText("P" + formatter.format(Double.parseDouble(resultSet.getString("sales_amount"))));
          		panel.add(sales_amount);
          		
          		JLabel sales_date = new JLabel("Sales Date");
          		sales_date.setFont(Inter.deriveFont(14f));
          		sales_date.setHorizontalAlignment(SwingConstants.CENTER);
          		sales_date.setBounds(441, currentYPos, 145, 33);
          		sales_date.setText(resultSet.getString("sales_date"));
          		panel.add(sales_date);
          		
          		JLabel campaign_id = new JLabel("Campaign ID");
          		campaign_id.setFont(Inter.deriveFont(14f));
          		campaign_id.setHorizontalAlignment(SwingConstants.CENTER);
          		campaign_id.setBounds(583, currentYPos, 156, 33);
          		if (resultSet.getString("campaign_id") == null) {
          			campaign_id.setText("--------");
          		} else {
          			campaign_id.setText("CID#" + resultSet.getString("campaign_id"));
          		}
          		panel.add(campaign_id);
          		
          		currentYPos += 30;
          		panel.revalidate();
  				panel.repaint();
  				panel.setPreferredSize(new Dimension(915, currentYPos + 11));
              }
          }
		} catch (SQLException f) {
			System.err.println("SQL Exception: " + f.getMessage());
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					transactions window = new transactions();
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
	public transactions() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, app.screenWidth + 14, app.screenHeight + 37);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Netflix MIS - Transaction");
		
		// ================================================================ // Fonts \\ ================================================================ \\
		
		try {
			CanvaSans = Font.createFont(Font.TRUETYPE_FONT, new File("Sans.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("FontsFree-Net-norwester.ttf")));
		} catch(IOException | FontFormatException e) {}

		try {
			Inter = Font.createFont(Font.TRUETYPE_FONT, new File("Inter-Medium.otf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("FontsFree-Net-norwester.ttf")));
		} catch(IOException | FontFormatException e) {}
		
		// =============================================================== // Main Menu \\ ============================================================== \\
		
		JLabel mainMenu = new JLabel("MAIN MENU");
		mainMenu.setForeground(new Color(155, 155, 155));
		mainMenu.setFont(Inter.deriveFont(Font.BOLD, 13f));
		mainMenu.setBounds(36, 100, 176, 33);
		frame.getContentPane().add(mainMenu);
		
		// Performance
		Image performanceIconSrc = new ImageIcon(this.getClass().getResource("/Performance Icon.png")).getImage();
		Image performanceIconImg = performanceIconSrc.getScaledInstance(28, 25, Image.SCALE_DEFAULT);
		
		Image performanceIconRedSrc = new ImageIcon(this.getClass().getResource("/Performance Icon(Red).png")).getImage();
		Image performanceIconRedImg = performanceIconRedSrc.getScaledInstance(28, 25, Image.SCALE_DEFAULT);
		
		JLabel performanceIcon = new JLabel("");
		performanceIcon.setIcon(new ImageIcon(performanceIconImg));
		performanceIcon.setBounds(38, 149, 28, 25);
		frame.getContentPane().add(performanceIcon);
		
		JLabel performanceLbl = new JLabel("INSIGHT");
		performanceLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				performanceLbl.setForeground(new Color(175, 31, 36));
				performanceIcon.setIcon(new ImageIcon(performanceIconRedImg));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				performanceLbl.setForeground(new Color(155, 155, 155));
				performanceIcon.setIcon(new ImageIcon(performanceIconImg));
			}
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				performance perf = new performance();
				perf.frame.show();
				frame.dispose();
			}
		});
		performanceLbl.setForeground(new Color(155, 155, 155));
		performanceLbl.setFont(Inter.deriveFont(Font.BOLD, 13f));
		performanceLbl.setBounds(76, 152, 114, 21);
		frame.getContentPane().add(performanceLbl);
		
		
		// Transaction
		Image transactionIconSrc = new ImageIcon(this.getClass().getResource("/Transaction Icon.png")).getImage();
		Image transactionIconImg = transactionIconSrc.getScaledInstance(28, 25, Image.SCALE_DEFAULT);
		
		Image transactionIconRedSrc = new ImageIcon(this.getClass().getResource("/Transaction Icon(Red).png")).getImage();
		Image transactionIconRedImg = transactionIconRedSrc.getScaledInstance(28, 25, Image.SCALE_DEFAULT);
		
		JLabel transactionIcon = new JLabel("");
		transactionIcon.setIcon(new ImageIcon(transactionIconImg));
		transactionIcon.setBounds(38, 196, 28, 25);
		frame.getContentPane().add(transactionIcon);
		
		JLabel transactionLbl = new JLabel("TRANSACTION");
		transactionLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				transactionLbl.setForeground(new Color(175, 31, 36));
				transactionIcon.setIcon(new ImageIcon(transactionIconRedImg));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				transactionLbl.setForeground(new Color(155, 155, 155));
				transactionIcon.setIcon(new ImageIcon(transactionIconImg));
			}
		});
		transactionLbl.setForeground(new Color(155, 155, 155));
		transactionLbl.setFont(Inter.deriveFont(Font.BOLD, 13f));
		transactionLbl.setBounds(76, 200, 104, 21);
		frame.getContentPane().add(transactionLbl);
		
		// Feedback
//		Image feedbacktransactionIconSrc = new ImageIcon(this.getClass().getResource("/Feedback Icon.png")).getImage();
//		Image feedbackIconImg = feedbacktransactionIconSrc.getScaledInstance(28, 25, Image.SCALE_DEFAULT);
//		
//		Image feedbackIconRedSrc = new ImageIcon(this.getClass().getResource("/Feedback Icon(Red).png")).getImage();
//		Image feedbackIconRedImg = feedbackIconRedSrc.getScaledInstance(28, 25, Image.SCALE_DEFAULT);
//		
//		JLabel feedbackIcon = new JLabel("");
//		feedbackIcon.setIcon(new ImageIcon(feedbackIconImg));
//		feedbackIcon.setBounds(38, 244, 28, 25);
//		frame.getContentPane().add(feedbackIcon);
//		
//		JLabel feedbackLbl = new JLabel("FEEDBACK");
//		feedbackLbl.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				feedbackLbl.setForeground(new Color(175, 31, 36));
//				feedbackIcon.setIcon(new ImageIcon(feedbackIconRedImg));
//			}
//			@Override
//			public void mouseExited(MouseEvent e) {
//				feedbackLbl.setForeground(new Color(155, 155, 155));
//				feedbackIcon.setIcon(new ImageIcon(feedbackIconImg));
//			}
//		});
//		feedbackLbl.setForeground(new Color(155, 155, 155));
//		feedbackLbl.setFont(Inter.deriveFont(Font.BOLD, 13f));
//		feedbackLbl.setBounds(76, 248, 90, 21);
//		frame.getContentPane().add(feedbackLbl);
		
		// Sign Out
		JLabel signOutIcon = new JLabel("");
		signOutIcon.setBounds(37, 894, 28, 28);
		signOutIcon.setBackground(new Color(54, 54, 55));
		Image signOutSrc = new ImageIcon(this.getClass().getResource("/Sign Out.png")).getImage();
		Image signOutImg = signOutSrc.getScaledInstance(signOutIcon.getWidth(), signOutIcon.getHeight(), Image.SCALE_DEFAULT);
		Image signOutRedSrc = new ImageIcon(this.getClass().getResource("/Sign Out (Red).png")).getImage();
		Image signOutRedImg = signOutRedSrc.getScaledInstance(signOutIcon.getWidth(), signOutIcon.getHeight(), Image.SCALE_DEFAULT);
		signOutIcon.setIcon(new ImageIcon(signOutImg));
		frame.getContentPane().add(signOutIcon);
		
		JLabel signOutLbl = new JLabel("SIGN OUT");
		signOutLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				signOutLbl.setForeground(new Color(175, 31, 36));
				signOutIcon.setIcon(new ImageIcon(signOutRedImg));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				signOutLbl.setForeground(new Color(155, 155, 155));
				signOutIcon.setIcon(new ImageIcon(signOutImg));
			}
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				login ln = new login();
				ln.frame.show();
				frame.dispose();
			}
		});
		signOutLbl.setForeground(new Color(155, 155, 155));
		signOutLbl.setFont(Inter.deriveFont(Font.BOLD, 13f));
		signOutLbl.setBounds(76, 897, 90, 21);
		frame.getContentPane().add(signOutLbl);
		
		// =========================================================== // Logo and Header \\ =========================================================== \\
		
		JLabel netflixLogo = new JLabel("");
		netflixLogo.setBounds(-3, 16, 73, 63);
		Image netflixLogoSrc = new ImageIcon(this.getClass().getResource("/Netflix Logo.png")).getImage();
		Image netflixLogoImg = netflixLogoSrc.getScaledInstance(netflixLogo.getWidth(), netflixLogo.getHeight(), Image.SCALE_DEFAULT);
		netflixLogo.setIcon(new ImageIcon(netflixLogoImg));
		frame.getContentPane().add(netflixLogo);

		JLabel logoTitle = new JLabel("<html><body>Marketing<br>Information System</body></html>");
		logoTitle.setForeground(new Color(175, 31, 36));
		logoTitle.setFont(CanvaSans.deriveFont(Font.BOLD, 15f));
		logoTitle.setBounds(55, 43, 164, 33);
		frame.getContentPane().add(logoTitle);
		
		JLabel transactionHeader = new JLabel("");
		transactionHeader.setFont(Inter.deriveFont(Font.BOLD, 20f));
		transactionHeader.setBounds(244, 11, 231, 26);
		transactionHeader.setText(transactionLbl.getText());
		frame.getContentPane().add(transactionHeader);
		
		// ================================================================ // Table \\ ================================================================ \\
		
		JLabel tableSortLbl = new JLabel("Sort By");
		tableSortLbl.setBounds(267, 79, 70, 22);
		tableSortLbl.setFont(Inter.deriveFont(14f));
		frame.getContentPane().add(tableSortLbl);
		
		JComboBox tableComboBox = new JComboBox();
		tableComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (actionAllowed) {
					searchTable(tableComboBox.getSelectedIndex(), tableSearch.getText());
				}
			}
		});
		tableComboBox.setModel(new DefaultComboBoxModel(new String[] {"CAMPAIGN ID ↑", "CAMPAIGN ID ↓", "PLAN ID ↑", "PLAN ID ↓", "SALES AMOUNT ↑", "SALES AMOUNT ↓", "SALES DATE ↑", "SALES DATE ↓", "SALES ID ↑", "SALES ID ↓"}));
		tableComboBox.setSelectedIndex(8);
		tableComboBox.setBounds(322, 79, 157, 22);
		tableComboBox.setFont(Inter.deriveFont(14f));
		frame.getContentPane().add(tableComboBox);
		
		tableSearch = new JTextField();
		tableSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					searchTable(tableComboBox.getSelectedIndex(), tableSearch.getText());
				}
			}
		});
		tableSearch.setBounds(489, 80, 318, 21);
		frame.getContentPane().add(tableSearch);
		tableSearch.setColumns(10);
		
		JLabel tableSearchLbl = new JLabel("Search");
		tableSearchLbl.setFocusable(true);
		tableSearchLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchTable(tableComboBox.getSelectedIndex(), tableSearch.getText());
			}
		});
		tableSearchLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tableSearchLbl.setBounds(814, 84, 104, 21);
		tableSearchLbl.setFont(CanvaSans.deriveFont(Font.BOLD, 17f));
		tableSearchLbl.setForeground(new Color(255, 255, 255));
		frame.getContentPane().add(tableSearchLbl);
		
		JLabel tableSearchButton = new JLabel("");
		tableSearchButton.setBounds(814, 27, 104, 127);
		frame.getContentPane().add(tableSearchButton);
		tableSearchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchTable(tableComboBox.getSelectedIndex(), tableSearch.getText());
			}
		});
		tableSearchButton.setBackground(new Color(54, 54, 55));
		Image loginSrc = new ImageIcon(this.getClass().getResource("/Login Button.png")).getImage();
		Image loginImg = loginSrc.getScaledInstance(tableSearchButton.getWidth(), tableSearchButton.getHeight(), Image.SCALE_DEFAULT);
		tableSearchButton.setIcon(new ImageIcon(loginImg));
		
		JLabel sales_idLbl = new JLabel("Sales ID");
		sales_idLbl.setHorizontalAlignment(SwingConstants.CENTER);
		sales_idLbl.setBounds(302, 133, 103, 25);
		sales_idLbl.setForeground(new Color(255, 255, 255));
		sales_idLbl.setFont(Inter.deriveFont(Font.BOLD, 13f));
		frame.getContentPane().add(sales_idLbl);
		
		JLabel plan_idLbl = new JLabel("Plan ID");
		plan_idLbl.setHorizontalAlignment(SwingConstants.CENTER);
		plan_idLbl.setBounds(430, 133, 103, 25);
		plan_idLbl.setForeground(new Color(255, 255, 255));
		plan_idLbl.setFont(Inter.deriveFont(Font.BOLD, 13f));
		frame.getContentPane().add(plan_idLbl);
		
		JLabel sales_amountLbl = new JLabel("Sales Amount");
		sales_amountLbl.setHorizontalAlignment(SwingConstants.CENTER);
		sales_amountLbl.setBounds(580, 133, 103, 25);
		sales_amountLbl.setForeground(new Color(255, 255, 255));
		sales_amountLbl.setFont(Inter.deriveFont(Font.BOLD, 13f));
		frame.getContentPane().add(sales_amountLbl);
		
		JLabel sales_dateLbl = new JLabel("Sales Date");
		sales_dateLbl.setHorizontalAlignment(SwingConstants.CENTER);
		sales_dateLbl.setBounds(732, 133, 103, 25);
		sales_dateLbl.setForeground(new Color(255, 255, 255));
		sales_dateLbl.setFont(Inter.deriveFont(Font.BOLD, 13f));
		frame.getContentPane().add(sales_dateLbl);
		
		JLabel campaign_idLbl = new JLabel("Campaign ID");
		campaign_idLbl.setHorizontalAlignment(SwingConstants.CENTER);
		campaign_idLbl.setBounds(881, 133, 103, 25);
		campaign_idLbl.setForeground(new Color(255, 255, 255));
		campaign_idLbl.setFont(Inter.deriveFont(Font.BOLD, 13f));
		frame.getContentPane().add(campaign_idLbl);
		
		JScrollPane transactionTableScrollPane = new JScrollPane();
		transactionTableScrollPane.setBounds(271, 176, 777, 721);
		transactionTableScrollPane.setOpaque(false);
		transactionTableScrollPane.getViewport().setOpaque(false);
		transactionTableScrollPane.setBorder(BorderFactory.createEmptyBorder());
		transactionTableScrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
		transactionTableScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		transactionTableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		frame.getContentPane().add(transactionTableScrollPane);
		
		panel = new JPanel();
		transactionTableScrollPane.setViewportView(panel);
		panel.setOpaque(false);
		panel.setLayout(null);
		
		JLabel transactionTable = new JLabel("");
		transactionTable.setBackground(new Color(54, 54, 55));
		transactionTable.setBounds(225, 112, 869, 797);
		transactionTable.setFocusable(true);
		Image transactionTableSrc = new ImageIcon(this.getClass().getResource("/Transaction Table.png")).getImage();
		Image transactionTableImg = transactionTableSrc.getScaledInstance(transactionTable.getWidth(), transactionTable.getHeight(), Image.SCALE_DEFAULT);
		transactionTable.setIcon(new ImageIcon(transactionTableImg));
		frame.getContentPane().add(transactionTable);
		
		// ============================================================== // Background \\ ============================================================== \\
		
		JLabel transactionBG = new JLabel("");
		transactionBG.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				transactionBG.grabFocus();
			}
		});
		transactionBG.setBackground(new Color(54, 54, 55));
		transactionBG.setBounds(0, 0, 1094, 945);
		transactionBG.setFocusable(true);
		Image transactionBGSrc = new ImageIcon(this.getClass().getResource("/Transaction Background.png")).getImage();
		Image transactionBGImg = transactionBGSrc.getScaledInstance(transactionBG.getWidth(), transactionBG.getHeight(), Image.SCALE_DEFAULT);
		transactionBG.setIcon(new ImageIcon(transactionBGImg));
		frame.getContentPane().add(transactionBG);
		
		refresh();
		actionAllowed = true;
	}
}
