package rentcarProject.member;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import java.awt.event.WindowFocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.WindowEvent;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WinMemberList extends JDialog {
	private JTable tableMemberList;
	private JTextField tfSearchword;
	private DefaultTableModel dtm;
	private JPopupMenu popupMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinMemberList dialog = new WinMemberList();
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
	public WinMemberList() {
		addWindowFocusListener(new WindowFocusListener() {
			
			public void windowGainedFocus(WindowEvent e) {
				String columns[] = {"회원아이디","회원비밀번호","회원이름","회원전화번호","회원이메일","회원가입일"};
				
				dtm = (DefaultTableModel)tableMemberList.getModel();
				dtm.setColumnIdentifiers(columns);
				
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});
		setTitle("회원정보 리스트");
		setBounds(100, 100, 583, 356);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		tableMemberList = new JTable();
		scrollPane.setViewportView(tableMemberList);
		
				
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JComboBox cbKeyword = new JComboBox();
		cbKeyword.setModel(new DefaultComboBoxModel(new String[] {"아이디", "이름", "이메일"}));
		panel.add(cbKeyword);
		
		tfSearchword = new JTextField();
		panel.add(tfSearchword);
		tfSearchword.setColumns(10);
		
		JButton btnSearch = new JButton("검색");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMemberListKeyword(dtm, cbKeyword.getSelectedItem().toString(), tfSearchword.getText());
			}
		});
		panel.add(btnSearch);
		
		JButton btnJoin = new JButton("회원 등록");
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			WinMemberJoin winJoin = new WinMemberJoin();
			winJoin.setModal(true);
			winJoin.setVisible(true);
			DefaultTableModel dtm2 = (DefaultTableModel)tableMemberList.getModel();
			dtm2.setRowCount(0);
			}
		});
		panel.add(btnJoin);
		
		popupMenu = new JPopupMenu();
		addPopup(tableMemberList, popupMenu);
		
		JMenuItem btnUpdate = new JMenuItem("수정");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row =tableMemberList.getSelectedRow();
				String rid = tableMemberList.getValueAt(row, 0).toString();
				WinMemberUpdate winMemberUpdate = new WinMemberUpdate(rid);
				winMemberUpdate.setModal(true);
				winMemberUpdate.setVisible(true);
				DefaultTableModel dtm2 = (DefaultTableModel)tableMemberList.getModel();
				dtm2.setRowCount(0);
				
			}
		});
		popupMenu.add(btnUpdate);
		
		JMenuItem btmDelete = new JMenuItem("삭제");
		btmDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row =tableMemberList.getSelectedRow();
				String rid = tableMemberList.getValueAt(row, 0).toString();
				memberDelete(rid);
				DefaultTableModel dtm2 = (DefaultTableModel)tableMemberList.getModel();
				dtm2.setRowCount(0);
				JOptionPane.showMessageDialog(null, "회원정보가 삭제되었습니다.");
			}
		});
		popupMenu.add(btmDelete);

	}

	

	protected void memberDelete(String rid) {
		// TODO Auto-generated method stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
	        
				
				String sql = "delete from rMember where rid =?";
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, rid);
				int deleteCheck = pstmt.executeUpdate();
				if(deleteCheck == 1) {
					DefaultTableModel dtm2 = (DefaultTableModel)tableMemberList.getModel();
					
					dtm2.setRowCount(0);
					
				}
					
				
			}	 catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		
	}

	protected void showMemberListKeyword(DefaultTableModel dtm, String keyword, String searchWord) {
		// TODO Auto-generated method stub
		String sql = "";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
	        
				if(keyword.equals("아이디")) {
					sql = "SELECT * FROM rMember where rid Like ?";
				}else if(keyword.equals("이름")) {
					sql = "SELECT * FROM rMember where rname Like ?";
				}else if(keyword.equals("이메일")) {
					sql = "SELECT * FROM rMember where remail Like ?";
				}
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1,'%'+searchWord+'%');
				ResultSet rs = pstmt.executeQuery();
				
				
				DefaultTableModel dtm2 = (DefaultTableModel)tableMemberList.getModel();
				
				dtm2.setRowCount(0);
				
				int count = 0;
				
				while(rs.next()) {
					Vector<String> vector = new Vector<String>();
					for(int i=1; i<=6; i++) {
						vector.add(rs.getString(i));
					}
					dtm.addRow(vector);
					count++;
				}
				
			}	 catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		
	}

	protected void showMemberList(DefaultTableModel dtm) {
		// TODO Auto-generated method stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
			
				String sql = "SELECT * FROM rMember";
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				ResultSet rs = pstmt.executeQuery();
				
				DefaultTableModel dtm2 = (DefaultTableModel)tableMemberList.getModel();
				dtm2.setRowCount(0);
				
				int count = 0;
				
				while(rs.next()) {
					Vector<String> vector = new Vector<String>();
					for(int i=1; i<=6; i++) {
						vector.add(rs.getString(i));
					}
					dtm.addRow(vector);
					count++;
				}
				
			}	 catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
					JTable source = (JTable)e.getSource();
					int row = source.rowAtPoint(e.getPoint());
					int column = source.columnAtPoint(e.getPoint());
					
					if(!source.isRowSelected(row))  // 행이 선택되지 않았다면 그 행을 선택한다.
						source.changeSelection(row, column, false, false);
					
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
