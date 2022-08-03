package login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.SwingConstants;

public class login extends JFrame {

	private JFrame frame;
	public JFrame frmLogin;
	private JTextField textUsername;
	private JTextField textPassword;
	public static String userName;

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
		frame.setTitle("Login");
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.getContentPane().setForeground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 567, 344);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textUsername = new JTextField();
		textUsername.setFont(new Font("DialogInput", Font.PLAIN, 20));
		textUsername.setBounds(154, 84, 332, 45);
		frame.getContentPane().add(textUsername);
		textUsername.setColumns(10);
		
		textPassword = new JTextField();
		textPassword.setFont(new Font("DialogInput", Font.PLAIN, 20));
		textPassword.setColumns(10);
		textPassword.setBounds(154, 162, 332, 45);
		frame.getContentPane().add(textPassword);
		
		JLabel lblUsername = new JLabel("");
		lblUsername.setIcon(new ImageIcon("D:\\JEAH\\2ND YEAR\\OOP LAB\\uname.png"));
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 36));
		lblUsername.setBounds(71, 64, 70, 90);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("");
		lblPassword.setIcon(new ImageIcon("D:\\JEAH\\2ND YEAR\\OOP LAB\\pass.png"));
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 36));
		lblPassword.setBounds(71, 138, 70, 90);
		frame.getContentPane().add(lblPassword);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setBackground(new Color(95, 158, 160));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ex = JOptionPane.showConfirmDialog(null, "Do you really want to close this program?", "Exit Program",JOptionPane.YES_NO_OPTION);
				if (ex==JOptionPane.YES_OPTION) {
					System.exit(0);
				}
				else if (ex==JOptionPane.NO_OPTION) {
				}
			}
		});
		btnExit.setFont(new Font("Arial", Font.PLAIN, 20));
		btnExit.setBounds(180, 231, 135, 39);
		frame.getContentPane().add(btnExit);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setBackground(new Color(95, 158, 160));
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					//set the mysql jdbc driver
					Class.forName("com.mysql.cj.jdbc.Driver");
					//set the mysql connection string
					Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/oop_db","root","");
					Statement stmt = (Statement) con.createStatement();
					//sql query for the login 
					String sql = "Select * from users_tbl where username='"+textUsername.getText() + "'and password='"+textPassword.getText()+"'";
					
					//execute query
					ResultSet rs = ((java.sql.Statement)stmt).executeQuery(sql);
										
					
					// conditions for username & password
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "Login successful...","Login Alert",2);
						
						Dashboard dash = new Dashboard();
						dash.show();
						dispose();
						
						userName = textUsername.getText();
						dash.lbluserT.setText(userName);
						
					}else if (textUsername.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Username is required...","Login Warning",2);
					}else if (textPassword.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Password is required...","Login Warning",2);
					}else {
						JOptionPane.showMessageDialog(null, "Username or password incorrect...","Login Warning",2);
					}
					
				}catch(Exception ex) {
					System.out.print(ex);
				}
			}
		});
		btnLogin.setFont(new Font("Arial", Font.PLAIN, 20));
		btnLogin.setBounds(323, 231, 135, 39);
		frame.getContentPane().add(btnLogin);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setFont(new Font("DialogInput", Font.BOLD, 50));
		lblLogin.setBounds(209, 0, 150, 78);
		frame.getContentPane().add(lblLogin);
	}
}
