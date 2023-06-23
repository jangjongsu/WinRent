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

public class WinMemberUpdate extends JDialog {
	private JTextField tfId;
	private JTextField tfName;
	private JTextField tfMobile;
	private JTextField tfEmail;
	private JComboBox cbEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinMemberUpdate dialog = new WinMemberUpdate();
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
	public WinMemberUpdate() {
		setTitle("회원 정보 변경");
		setBounds(100, 100, 383, 309);
		getContentPane().setLayout(null);
		
		JLabel lbltitle = new JLabel("회원정보변경");
		lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbltitle.setFont(new Font("굴림", Font.PLAIN, 20));
		lbltitle.setBounds(12, 10, 325, 57);
		getContentPane().add(lbltitle);
		
		tfId = new JTextField();
		tfId.setEnabled(false);
		tfId.setText("");
		tfId.setColumns(10);
		tfId.setBounds(96, 77, 241, 21);
		getContentPane().add(tfId);
		
		JLabel lblId = new JLabel("아이디");
		lblId.setBounds(12, 77, 74, 21);
		getContentPane().add(lblId);
		
		JLabel lblPw_1_1 = new JLabel("이름");
		lblPw_1_1.setBounds(12, 108, 84, 21);
		getContentPane().add(lblPw_1_1);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(96, 108, 241, 21);
		getContentPane().add(tfName);
		
		JLabel lblmobile = new JLabel("휴대폰 번호");
		lblmobile.setBounds(12, 139, 84, 21);
		getContentPane().add(lblmobile);
		
		tfMobile = new JTextField();
		tfMobile.setColumns(10);
		tfMobile.setBounds(96, 139, 241, 21);
		getContentPane().add(tfMobile);
		
		JLabel lblEmail = new JLabel("이메일");
		lblEmail.setBounds(12, 170, 84, 21);
		getContentPane().add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(96, 170, 109, 21);
		getContentPane().add(tfEmail);
		
		JButton btnJoin = new JButton("수정");
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memberUpdate();
				
			}
			
		});
		btnJoin.setFont(new Font("굴림", Font.PLAIN, 15));
		btnJoin.setBounds(12, 201, 325, 51);
		getContentPane().add(btnJoin);
		
		JLabel lblNewLabel = new JLabel("@");
		lblNewLabel.setBounds(217, 173, 20, 15);
		getContentPane().add(lblNewLabel);
		
		cbEmail = new JComboBox();
		cbEmail.setModel(new DefaultComboBoxModel(new String[] {"naver.com", "abc.com", "gmail.com", "nate.com"}));
		cbEmail.setBounds(239, 169, 98, 23);
		getContentPane().add(cbEmail);

	}

	public WinMemberUpdate(String rid) {
		// TODO Auto-generated constructor stub
		this();
		showMember(rid);
	}

	private void showMember(String rid) {
		// TODO Auto-generated method stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
			
				String sql = "SELECT * FROM rMember where rid = ?";
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1,rid);
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
				tfId.setText(rs.getString("rid"));
				tfName.setText(rs.getString("rname"));
				tfMobile.setText(rs.getString("rmobile"));
				String [] email = rs.getString("remail").split("@");
				tfEmail.setText(email[0]);
				cbEmail.setSelectedItem(email[1]);
				}
				
			}	 catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		
		
	}

	protected void memberUpdate() {
		// TODO Auto-generated method stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
	        
				
				String sql = "update rMember set rname = ?, rmobile = ?, remail = ? where rid = ?";
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1,tfName.getText());
				pstmt.setString(2,tfMobile.getText());
				pstmt.setString(3,tfEmail.getText()+"@"+cbEmail.getSelectedItem());
				pstmt.setString(4,tfId.getText());
				int updateCheck = pstmt.executeUpdate();
				if(updateCheck == 1) {
					dispose(); 
					JOptionPane.showMessageDialog(null, "회원가 수정 되었습니다.");
				}else {
					JOptionPane.showMessageDialog(null, "회원 수정 실패");
				}
					
				
			}	 catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		
		
	}
}
