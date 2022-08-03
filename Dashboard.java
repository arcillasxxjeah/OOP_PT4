package login;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.TableView;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.sql.ResultSet;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Dashboard extends JFrame {

	private JPanel contentPane;
	public JFrame frmDashboard;
	JLabel lbluserT;
	private JTextField textNumber;
	private JTextField textName;
	private JTextField textAddress;
	private JTextField textSearch;
	private JTable tableView;
	DefaultTableModel mod;
	private JTextField textContact;
	private JComboBox txtGender;
	private JLabel lblClock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Dashboard() {
		initialize();
		clock();
		viewRecords();
	}
	/**
	 * Create the frame.
	 * @return 
	 */
	
	
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1068, 525);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(112, 128, 144));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lbluserT = new JLabel();
		lbluserT.setForeground(new Color(255, 255, 255));
		lbluserT.setFont(new Font("DialogInput", Font.BOLD, 16));
		lbluserT.setText("name");
		lbluserT.setBounds(953, 0, 162, 53);
		contentPane.add(lbluserT);
		
		JLabel lblHello = new JLabel("USER :");
		lblHello.setForeground(Color.WHITE);
		lblHello.setFont(new Font("DialogInput", Font.BOLD, 16));
		lblHello.setBounds(883, 0, 60, 53);
		contentPane.add(lblHello);
		
		JLabel lblNewLabel_1 = new JLabel("Student Record System");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("DialogInput", Font.BOLD, 30));
		lblNewLabel_1.setBounds(122, 31, 382, 40);
		contentPane.add(lblNewLabel_1);
		
		textNumber = new JTextField();
		textNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textNumber.setBounds(122, 110, 209, 35);
		contentPane.add(textNumber);
		textNumber.setColumns(10);
		
		textName = new JTextField();
		textName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textName.setColumns(10);
		textName.setBounds(122, 156, 209, 35);
		contentPane.add(textName);
		
		textAddress = new JTextField();
		textAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textAddress.setColumns(10);
		textAddress.setBounds(122, 293, 209, 35);
		contentPane.add(textAddress);
		
		JLabel lblStudent_no = new JLabel("Student No.");
		lblStudent_no.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStudent_no.setForeground(Color.WHITE);
		lblStudent_no.setFont(new Font("DialogInput", Font.PLAIN, 12));
		lblStudent_no.setBounds(10, 110, 102, 35);
		contentPane.add(lblStudent_no);
		
		JLabel lblName = new JLabel("Student Name");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("DialogInput", Font.PLAIN, 12));
		lblName.setBounds(10, 156, 102, 35);
		contentPane.add(lblName);
		
		JLabel lblAddress = new JLabel("Student Address");
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setFont(new Font("DialogInput", Font.PLAIN, 12));
		lblAddress.setForeground(Color.WHITE);
		lblAddress.setBounds(-12, 294, 124, 35);
		contentPane.add(lblAddress);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.setFont(new Font("DialogInput", Font.PLAIN, 14));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTextfield();
			}
		});
		btnClear.setBounds(122, 360, 102, 32);
		contentPane.add(btnClear);
		
		textSearch = new JTextField();
		textSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DefaultTableModel mod = (DefaultTableModel)tableView.getModel();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(mod);
				tableView.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(textSearch.getText().trim()));
			}
		});
		textSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textSearch.setBounds(745, 75, 246, 23);
		contentPane.add(textSearch);
		textSearch.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Search");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("DialogInput", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(687, 69, 48, 35);
		contentPane.add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setBounds(341, 110, 701, 219);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 701, 281);
		panel.add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		
		tableView = new JTable();
		scrollPane.setViewportView(tableView);
		tableView.setColumnSelectionAllowed(true);
		tableView.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textNumber.setEnabled(false);
				String id = tableView.getValueAt(tableView.getSelectedRow(),0).toString();
				textField(id);
			}
		});
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\JEAH\\2ND YEAR\\OOP LAB\\record.png"));
		lblNewLabel.setBounds(10, 0, 111, 114);
		contentPane.add(lblNewLabel);
		
		
		txtGender = new JComboBox();
		txtGender.setForeground(Color.BLACK);
		txtGender.setFont(new Font("DialogInput", Font.PLAIN, 14));
		txtGender.setBounds(122, 202, 209, 34);
		txtGender.setModel(new DefaultComboBoxModel(new String[] {"Choose", "Male", "Female", "Other"}));
		contentPane.add(txtGender);
		
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGender.setForeground(Color.WHITE);
		lblGender.setFont(new Font("DialogInput", Font.PLAIN, 12));
		lblGender.setBounds(37, 202, 75, 35);
		contentPane.add(lblGender);
		
		JLabel lblContactNo = new JLabel("Contact No");
		lblContactNo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContactNo.setForeground(Color.WHITE);
		lblContactNo.setFont(new Font("DialogInput", Font.PLAIN, 12));
		lblContactNo.setBounds(10, 248, 102, 35);
		contentPane.add(lblContactNo);
		
		textContact = new JTextField();
		textContact.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textContact.setColumns(10);
		textContact.setBounds(122, 247, 209, 35);
		contentPane.add(textContact);
		
		lblClock = new JLabel();
		lblClock.setText("Time : ");
		lblClock.setForeground(Color.WHITE);
		lblClock.setFont(new Font("DialogInput", Font.PLAIN, 16));
		lblClock.setBounds(10, 435, 335, 40);
		contentPane.add(lblClock);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Buttons", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(341, 340, 701, 76);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnView = new JButton("REFRESH");
		btnView.setBounds(127, 21, 101, 29);
		panel_1.add(btnView);
		btnView.setFont(new Font("DialogInput", Font.PLAIN, 14));
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setBounds(238, 21, 101, 29);
		panel_1.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableView.getSelectedRow() >= 0) {
					updateRecord(textNumber.getText());
				}
				
			}
		});
		btnUpdate.setFont(new Font("DialogInput", Font.PLAIN, 14));
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBounds(349, 21, 101, 29);
		panel_1.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableView.getSelectedRow() >= 0) {
					deleteRecord(textNumber.getText());
				}
			}
		});
		btnDelete.setFont(new Font("DialogInput", Font.PLAIN, 14));
		
		JButton btnExit = new JButton("EXIT");
		btnExit.setBounds(460, 21, 101, 29);
		panel_1.add(btnExit);
		btnExit.setFont(new Font("DialogInput", Font.PLAIN, 14));
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
		
		JButton btnAdd = new JButton("SAVE");
		btnAdd.setBounds(229, 360, 102, 32);
		contentPane.add(btnAdd);
		btnAdd.setFont(new Font("DialogInput", Font.PLAIN, 14));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addRecord(); 
			}
		});
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewRecords();
			}
		});
		//tableView.setBounds(800, 800, 800, 800);
	}
	//db connection
			static Connection connect(){
				try {
					String myDriver = "com.mysql.cj.jdbc.Driver";
					String url = "jdbc:mysql://localhost:3306/oop_db";
					Class.forName(myDriver);
					return(Connection) DriverManager.getConnection(url, "root", "");
				}catch(Exception e) {
					System.out.print("Cannot connect to database.");
				}
				return null;
			}
			
	//save method
			private void addRecord() {
				Connection con = connect();
				//Calendar date = Calendar.getInstance();
				//java.sql.Date datecreated = new java.sql.Date(date.getTime().getTime());
				
				try {
					String sql = "insert into student_tbl(student_no, student_name, gender, contact_no, address, date_created) values (?,?,?,?,?,NOW())";
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
					
					if (textNumber.getText().equals("") || textName.getText().equals("") || txtGender.getSelectedItem().toString().equals("") ||textContact.getText().equals("") || textAddress.getText().equals("") ) {
						JOptionPane.showMessageDialog(null, "Please Fill Complete Information");
					}else {
						ps.setString(1, textNumber.getText());
						ps.setString(2, textName.getText());
						ps.setString(3, txtGender.getSelectedItem().toString());
						ps.setString(4, textContact.getText());
						ps.setString(5, textAddress.getText());
						//ps.setDate(4, datecreated);
						ps.execute();
						
						JOptionPane.showMessageDialog(null, "Record Saved Successfully");
						clearTextfield();
					}
		
				}catch(Exception e) {
					System.out.print("Error" + e);
				}
			}
			
			//view method
			private void viewRecords() {
				Connection con = connect();
				DefaultTableModel mod = new DefaultTableModel();
				mod.addColumn("No.");
				mod.addColumn("Student No");
				mod.addColumn("Student Name");
				mod.addColumn("Gender");
				mod.addColumn("Contact No");
				mod.addColumn("Student Address");
				mod.addColumn("Date of Registration");
				
				try {
					String sql = "SELECT * from student_tbl";
					Statement st = (Statement) con.createStatement();
					ResultSet rs = st.executeQuery(sql);
					while(rs.next()) {
						mod.addRow(new Object []  {	
							rs.getInt("id"),	
							rs.getString("student_no"),	
							rs.getString("student_name"),
							rs.getString("gender"),
							rs.getString("contact_no"),
							rs.getString("address"),
							rs.getString("date_created"),	
						});
					}
					rs.close();
					st.close();
					con.close();
					
					tableView.setModel(mod);
					//tableView.setAutoResizeMode(0);
					tableView.getColumnModel().getColumn(0).setPreferredWidth(30);
					tableView.getColumnModel().getColumn(1).setPreferredWidth(80);
					tableView.getColumnModel().getColumn(2).setPreferredWidth(130);
					tableView.getColumnModel().getColumn(3).setPreferredWidth(80);
					tableView.getColumnModel().getColumn(4).setPreferredWidth(110);
					tableView.getColumnModel().getColumn(5).setPreferredWidth(115);
					tableView.getColumnModel().getColumn(6).setPreferredWidth(125);
					
				}catch(Exception ex) {
					System.out.print("Error : " + ex);
				}
			}
			
			//update method
			private void updateRecord(String id) {
				Connection con = connect();
				try {
					String sql = "UPDATE student_tbl set student_no = ?, student_name = ?, gender = ?, contact_no = ?, address = ? where student_no = ?";
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
					ps.setString(1, textNumber.getText());
					ps.setString(2, textName.getText());
					ps.setString(3, txtGender.getSelectedItem().toString());
					ps.setString(4, textContact.getText());
					ps.setString(5, textAddress.getText());
					ps.setString(6, textNumber.getText());
					ps.execute();
					
					ps.close();
					con.close();
					JOptionPane.showMessageDialog(null, "Record Updated Successfully");
					clearTextfield();
					
				}catch(Exception e) {
					System.out.print("Error" + e);
				}
			}
			
			//click event form table to input fields
			private void textField(String id) {
				Connection con = connect();
				try {
					String sql = "SELECT * from student_tbl where id = ?";
					PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
					ps.setString(1, id);
					ResultSet rs = ps.executeQuery();
					
					while(rs.next()) {
						textNumber.setText(rs.getString("student_no"));
						textName.setText(rs.getString("student_name"));
						if(rs.getString("gender")== "Male") {
							txtGender.setSelectedItem("Male");
						}else {
							txtGender.setSelectedItem("Female");
						}
						textContact.setText(rs.getString("contact_no"));
						textAddress.setText(rs.getString("address"));
					}	
				}catch(Exception e) {
					System.out.print("Error" + e);
				}
				
			}
			
			//delete method
			private void deleteRecord(String id) {
				Connection con = connect();
				
				try {
						String sql = "DELETE from student_tbl where student_no = ?";
						PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
						ps.setString(1, id);
						ps.execute();
						
						ps.close();
						con.close();
						JOptionPane.showMessageDialog(null, "Record Deleted Successfully");
						clearTextfield();
				}catch(Exception e) {
					System.out.print("Error" + e);
				}
				
			}
			
			//clear method
			private void clearTextfield() {
				textNumber.setText("");
				textName.setText("");
				txtGender.setSelectedItem("Choose");
				textContact.setText("");
				textAddress.setText("");
			}
			
			public void clock() {
				Thread clock = new Thread() {
					public void run() {
						try {
							while(true) {
								Calendar cl = new GregorianCalendar();
								int day = cl.get(Calendar.DAY_OF_MONTH);
								int month = cl.get(Calendar.MONTH);
								int year = cl.get(Calendar.YEAR);
								
								int sec = cl.get(Calendar.SECOND);
								int min = cl.get(Calendar.MINUTE);
								int hr = cl.get(Calendar.HOUR);
								
								
								lblClock.setText("Time : " + hr + ":" + min + ":" + sec + " | Date : " + month + "/" + day + "/" + year);
								sleep(1000);
							}
						}catch(InterruptedException ex) {
							ex.printStackTrace();
						}
					}
				};
				clock.start();
			}
}