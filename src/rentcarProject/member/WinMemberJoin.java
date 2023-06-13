package rentcarProject.member;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;

public class WinMemberJoin extends JDialog {
	private JTextField tfId;
	private JTextField tfName;
	private JTextField tfMobile;
	private JTextField tfEmail;
	private JPasswordField tfPw;
	private JPasswordField tfPw2;
	private JComboBox cbEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinMemberJoin dialog = new WinMemberJoin();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public WinMemberJoin() {
		setBounds(100, 100, 383, 411);
		getContentPane().setLayout(null);
		
		JLabel lbltitle = new JLabel("회원등록");
		lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbltitle.setFont(new Font("굴림", Font.PLAIN, 20));
		lbltitle.setBounds(12, 10, 325, 57);
		getContentPane().add(lbltitle);
		
		tfId = new JTextField();
		tfId.setText("");
		tfId.setColumns(10);
		tfId.setBounds(96, 77, 241, 21);
		getContentPane().add(tfId);
		
		JButton btnNewButton = new JButton("중복확인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idCheck(tfId.getText());
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 15));
		btnNewButton.setBounds(96, 108, 241, 32);
		getContentPane().add(btnNewButton);
		
		JLabel lblId = new JLabel("아이디");
		lblId.setBounds(12, 77, 74, 21);
		getContentPane().add(lblId);
		
		JLabel lblPw = new JLabel("비밀번호");
		lblPw.setBounds(12, 150, 74, 21);
		getContentPane().add(lblPw);
		
		JLabel lblPw_1 = new JLabel("비밀번호 확인");
		lblPw_1.setBounds(12, 181, 84, 21);
		getContentPane().add(lblPw_1);
		
		JLabel lblPw_1_1 = new JLabel("이름");
		lblPw_1_1.setBounds(12, 212, 84, 21);
		getContentPane().add(lblPw_1_1);
		
		tfName = new JTextField();
		tfName.setEnabled(false);
		tfName.setColumns(10);
		tfName.setBounds(96, 212, 241, 21);
		getContentPane().add(tfName);
		
		JLabel lblmobile = new JLabel("휴대폰 번호");
		lblmobile.setBounds(12, 243, 84, 21);
		getContentPane().add(lblmobile);
		
		tfMobile = new JTextField();
		tfMobile.setEnabled(false);
		tfMobile.setColumns(10);
		tfMobile.setBounds(96, 243, 241, 21);
		getContentPane().add(tfMobile);
		
		JLabel lblEmail = new JLabel("이메일");
		lblEmail.setBounds(12, 274, 84, 21);
		getContentPane().add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setEnabled(false);
		tfEmail.setColumns(10);
		tfEmail.setBounds(96, 274, 109, 21);
		getContentPane().add(tfEmail);
		
		JButton btnJoin = new JButton("회원등록");
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memberJoin();
			}
			
		});
		btnJoin.setFont(new Font("굴림", Font.PLAIN, 15));
		btnJoin.setBounds(12, 305, 325, 51);
		getContentPane().add(btnJoin);
		
		JLabel lblNewLabel = new JLabel("@");
		lblNewLabel.setBounds(217, 277, 20, 15);
		getContentPane().add(lblNewLabel);
		
		cbEmail = new JComboBox();
		cbEmail.setModel(new DefaultComboBoxModel(new String[] {"naver.com", "abc.com", "gmail.com", "nate.com"}));
		cbEmail.setBounds(239, 273, 98, 23);
		getContentPane().add(cbEmail);
		
		tfPw = new JPasswordField();
		tfPw.setEnabled(false);
		tfPw.setBounds(96, 150, 241, 21);
		getContentPane().add(tfPw);
		
		tfPw2 = new JPasswordField();
		tfPw2.setEnabled(false);
		tfPw2.setBounds(96, 181, 241, 21);
		getContentPane().add(tfPw2);

	}

	protected void idCheck(String rid) {
		// TODO Auto-generated method stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
			
				String sql = "SELECT count(*) FROM rMember where rid = ?";
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				pstmt.setNString(1, rid);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					if(rs.getInt("count(*)") == 1) {
						JOptionPane.showMessageDialog(null, "중복된 아이디 입니다.");
					}else {
						JOptionPane.showMessageDialog(null, "사용 가능한 아이디 입니다.");
						setJoin();
					}
				}
			}catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
	}

	private void setJoin() {
		// TODO Auto-generated method stub
		tfPw.setEnabled(true);
		tfPw2.setEnabled(true);
		tfName.setEnabled(true);
		tfMobile.setEnabled(true);
		tfEmail.setEnabled(true);
	}

	protected void memberJoin() {
		// TODO Auto-generated method stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
	        
				
				String sql = "insert into rMember(rid, rpw, rname, rmobile, remail) values (?, ?, ?, ?, ?)";
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1,tfId.getText());
				pstmt.setString(2,new String(tfPw.getPassword()));
				pstmt.setString(3,tfName.getText());
				pstmt.setString(4,tfMobile.getText());
				pstmt.setString(5,tfEmail.getText()+"@"+cbEmail.getSelectedItem());
				int joinCheck = pstmt.executeUpdate();
				if(joinCheck == 1) {
					dispose(); 
				}
					
				
			}	 catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		
	}
}
