import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class login {

	JFrame frame;
	
	Font CanvaSans, Inter;
	
	private JTextField usernameTextField, passwordTextField;
	
	// Verifies Log In details
	@SuppressWarnings("deprecation")
	void loginMethod() {
		if (usernameTextField.getText().equals("root") && passwordTextField.getText().equals("admin")) {
			performance perf = new performance();
			perf.frame.show();
			frame.dispose();
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
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
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, app.screenWidth + 14, app.screenHeight + 37);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Netflix MIS - Login");
		
		// ================================================================= // Fonts \\ ================================================================= \\
		
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
		
		// ================================================================= // Login \\ ================================================================= \\
		
		// ========== // Forgot Password \\ =========== \\
		JLabel forgotPassword = new JLabel("<html><u>Forgot Password?</u></html>");
		forgotPassword.setHorizontalAlignment(SwingConstants.CENTER);
		forgotPassword.setBounds(706, 587, 108, 21);
		forgotPassword.setFont(CanvaSans.deriveFont(Font.BOLD, 11f));
		forgotPassword.setForeground(new Color(166, 166, 166));
		forgotPassword.setBackground(new Color(54, 54, 55));
		frame.getContentPane().add(forgotPassword);
		
		// ============== // Username \\ =============== \\
		// Username Textfield
		usernameTextField = new JTextField();
		usernameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					loginMethod();
				}
			}
		});
		usernameTextField.setBounds(558, 422, 402, 55);
		usernameTextField.setForeground(new Color(0, 0, 0));
		usernameTextField.setFont(CanvaSans.deriveFont(20f));
		usernameTextField.setOpaque(false);
		usernameTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		frame.getContentPane().add(usernameTextField);
		usernameTextField.setColumns(10);
		
		// Username Label
		JLabel usernameLbl = new JLabel("Username");
		usernameLbl.setBounds(548, 398, 108, 21);
		usernameLbl.setFont(CanvaSans.deriveFont(16f));
		usernameLbl.setForeground(new Color(166, 166, 166));
		usernameLbl.setBackground(new Color(54, 54, 55));
		frame.getContentPane().add(usernameLbl);
		
		// Border
		JLabel username = new JLabel("");
		username.setBounds(545, 267, 421, 361);
		username.setBackground(new Color(54, 54, 55));
		Image usernameSrc = new ImageIcon(this.getClass().getResource("/Login Textfield.png")).getImage();
		Image usernameImg = usernameSrc.getScaledInstance(username.getWidth(), username.getHeight(), Image.SCALE_DEFAULT);
		username.setIcon(new ImageIcon(usernameImg));
		frame.getContentPane().add(username);
		
		// ============== // Password \\ =============== \\
		// Passwordfield
		passwordTextField = new JPasswordField();
		passwordTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					loginMethod();
				}
			}
		});
		passwordTextField.setBounds(558, 528, 402, 55);
		passwordTextField.setForeground(new Color(0, 0, 0));
		passwordTextField.setFont(CanvaSans.deriveFont(20f));
		passwordTextField.setOpaque(false);
		passwordTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		frame.getContentPane().add(passwordTextField);
		passwordTextField.setColumns(10);
		
		
		// Password Label
		JLabel passwordLbl = new JLabel("Password");
		passwordLbl.setBounds(548, 504, 108, 21);
		passwordLbl.setFont(CanvaSans.deriveFont(16f));
		passwordLbl.setForeground(new Color(166, 166, 166));
		passwordLbl.setBackground(new Color(54, 54, 55));
		frame.getContentPane().add(passwordLbl);
		
		
		// Border
		JLabel password = new JLabel("");
		password.setBounds(545, 373, 421, 361);
		password.setBackground(new Color(54, 54, 55));
		Image passwordSrc = new ImageIcon(this.getClass().getResource("/Login Textfield.png")).getImage();
		Image passwordImg = passwordSrc.getScaledInstance(password.getWidth(), password.getHeight(), Image.SCALE_DEFAULT);
		password.setIcon(new ImageIcon(passwordImg));
		frame.getContentPane().add(password);
		
		// ============= // Login \\ ============== \\
		JLabel loginLbl = new JLabel("Login");
		loginLbl.setFocusable(true);
		loginLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loginMethod();
			}
		});
		loginLbl.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				loginMethod();
			}
		});
		loginLbl.setHorizontalAlignment(SwingConstants.CENTER);
		loginLbl.setBounds(706, 643, 108, 21);
		loginLbl.setFont(CanvaSans.deriveFont(Font.BOLD, 17f));
		loginLbl.setForeground(new Color(255, 255, 255));
		frame.getContentPane().add(loginLbl);
		
		JLabel loginButton = new JLabel("");
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loginMethod();
			}
		});
		loginButton.setBounds(545, 471, 421, 361);
		loginButton.setBackground(new Color(54, 54, 55));
		Image loginSrc = new ImageIcon(this.getClass().getResource("/Login Button.png")).getImage();
		Image loginImg = loginSrc.getScaledInstance(loginButton.getWidth(), loginButton.getHeight(), Image.SCALE_DEFAULT);
		loginButton.setIcon(new ImageIcon(loginImg));
		frame.getContentPane().add(loginButton);
		
		// =============================================================== // Background \\ =============================================================== \\
		
		JLabel getStartedLbl = new JLabel("Let's Get Started");
		getStartedLbl.setForeground(new Color(91, 18, 20));
		getStartedLbl.setBounds(545, 307, 362, 78);
		getStartedLbl.setFont(Inter.deriveFont(Font.BOLD, 42f));
		getStartedLbl.setBackground(new Color(54, 54, 55));
		frame.getContentPane().add(getStartedLbl);
		
		JLabel netflixLogo2Lbl = new JLabel("<html><body>Marketing<BR>Information System</body></html>");
		netflixLogo2Lbl.setForeground(new Color(233, 194, 196));
		netflixLogo2Lbl.setBounds(622, 209, 362, 78);
		netflixLogo2Lbl.setFont(CanvaSans.deriveFont(Font.BOLD, 27f));
		netflixLogo2Lbl.setBackground(new Color(54, 54, 55));
		frame.getContentPane().add(netflixLogo2Lbl);
		
		CustomLabel netflixLogo2 = new CustomLabel("/Netflix Logo.png", 0.3f);
		netflixLogo2.setBounds(503, 153, 133, 134);
		netflixLogo2.setBackground(new Color(54, 54, 55));
		netflixLogo2.setFocusable(true);
		frame.getContentPane().add(netflixLogo2);
		
		JLabel netflixLogo = new JLabel("");
		netflixLogo.setBounds(87, 280, 388, 361);
		netflixLogo.setBackground(new Color(54, 54, 55));
		Image netflixLogoSrc = new ImageIcon(this.getClass().getResource("/Netflix Logo.png")).getImage();
		Image netflixLogoImg = netflixLogoSrc.getScaledInstance(netflixLogo.getWidth(), netflixLogo.getHeight(), Image.SCALE_DEFAULT);
		netflixLogo.setIcon(new ImageIcon(netflixLogoImg));
		frame.getContentPane().add(netflixLogo);
		
		JLabel redSquare = new JLabel("");
		redSquare.setBounds(-114, 64, 816, 816);
		redSquare.setBackground(new Color(54, 54, 55));
		Image redSquareSrc = new ImageIcon(this.getClass().getResource("/Login Red Square.png")).getImage();
		Image redSquareImg = redSquareSrc.getScaledInstance(redSquare.getWidth(), redSquare.getHeight(), Image.SCALE_DEFAULT);
		redSquare.setIcon(new ImageIcon(redSquareImg));
		frame.getContentPane().add(redSquare);
		
		JLabel whiteSquare = new JLabel("");	
		whiteSquare.setBounds(0, 0, 1094, 945);
		whiteSquare.setBackground(new Color(54, 54, 55));
		Image whiteSquareSrc = new ImageIcon(this.getClass().getResource("/Login White Square.png")).getImage();
		Image whiteSquareImg = whiteSquareSrc.getScaledInstance(whiteSquare.getWidth(), whiteSquare.getHeight(), Image.SCALE_DEFAULT);
		whiteSquare.setIcon(new ImageIcon(whiteSquareImg));
		frame.getContentPane().add(whiteSquare);
	}
}

// Customer Label to adjust ImageIcon Opacity
@SuppressWarnings("serial")
class CustomLabel extends JLabel {
    private BufferedImage image;
    private float opacity;

    public CustomLabel(String imagePath, float opacity) {
        this.opacity = opacity;
        try {
            image = ImageIO.read(getClass().getResource(imagePath));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
            g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            g2d.dispose();
        }
    }
}
