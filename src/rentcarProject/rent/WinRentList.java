package rentcarProject.rent;

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
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WinRentList extends JDialog {
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
					WinRentList dialog = new WinRentList();
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
	public WinRentList() {
		addWindowFocusListener(new WindowFocusListener() {
			
			public void windowGainedFocus(WindowEvent e) {
				String columns[] = {"예약번호","회원아이디","차량명칭","대여기간","대여비용"};
				dtm = (DefaultTableModel)tableMemberList.getModel();
				dtm.setColumnIdentifiers(columns);
				
			}
			public void windowLostFocus(WindowEvent e) {
				
			}
		});
		setTitle("예약정보 리스트");
		setBounds(100, 100, 602, 356);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		tableMemberList = new JTable();
		scrollPane.setViewportView(tableMemberList);
		
				
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JComboBox cbKeyword = new JComboBox();
		cbKeyword.setModel(new DefaultComboBoxModel(new String[] {"예약번호", "회원아이디", "차량명칭"}));
		panel.add(cbKeyword);
		
		tfSearchword = new JTextField();
		panel.add(tfSearchword);
		tfSearchword.setColumns(10);
		
		JButton btnSearch = new JButton("검색");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showCarListKeyword(dtm, cbKeyword.getSelectedItem().toString(), tfSearchword.getText());
			}
		});
		panel.add(btnSearch);
		
		JButton btnJoin = new JButton("예약 등록");
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			WinRentInsert winRentInsert = new WinRentInsert();
			winRentInsert.setModal(true);
			winRentInsert.setVisible(true);
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
				String rtnum = tableMemberList.getValueAt(row, 0).toString();
				WinRentUpdate winRentUpdate = new WinRentUpdate(rtnum);
				winRentUpdate.setModal(true);
				winRentUpdate.setVisible(true);
				DefaultTableModel dtm2 = (DefaultTableModel)tableMemberList.getModel();
				dtm2.setRowCount(0);
				
			}
		});
		popupMenu.add(btnUpdate);
		
		JMenuItem btmDelete = new JMenuItem("삭제");
		btmDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row =tableMemberList.getSelectedRow();
				String rtnum = tableMemberList.getValueAt(row, 0).toString();
				rentDelete(rtnum);
				DefaultTableModel dtm2 = (DefaultTableModel)tableMemberList.getModel();
				dtm2.setRowCount(0);
			}
		});
		popupMenu.add(btmDelete);

	}

	

	protected void rentDelete(String rtnum) {
		// TODO Auto-generated method stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
	        
				
				String sql = "delete from rReservation where rtnum =?";
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, rtnum);
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

	protected void showCarListKeyword(DefaultTableModel dtm, String keyword, String searchWord) {
		// TODO Auto-generated method stub
		String sql = "";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
				if(keyword.equals("예약번호")) {
					sql = "SELECT rtnum, rid, cname, rpdate, price"
							+ "    from rReservation"
							+ "        inner join cartbl"
							+ "            on  rreservation.cindex = cartbl.cindex"
							+ "                where rtnum Like ?"
							+ "						order by rtnum";
				}else if(keyword.equals("회원아이디")) {
					sql = "SELECT rtnum, rid, cname, rpdate, price"
							+ "    from rReservation"
							+ "        inner join cartbl"
							+ "            on  rreservation.cindex = cartbl.cindex"
							+ "                where UPPER(rid) Like UPPER(?)"
							+ "						order by rtnum";
				}else if(keyword.equals("차량명칭")) {
					sql = "SELECT rtnum, rid, cname, rpdate, price"
							+ "    from rReservation"
							+ "        inner join cartbl"
							+ "            on  rreservation.cindex = cartbl.cindex"
							+ "                where UPPER(cname) Like UPPER(?)"
							+ "						order by rtnum";
				}
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1,'%'+searchWord+'%');
				ResultSet rs = pstmt.executeQuery();
				
				DefaultTableModel dtm2 = (DefaultTableModel)tableMemberList.getModel();
				
				dtm2.setRowCount(0);
				
				int count = 0;
				
				while(rs.next()) {
					Vector<String> vector = new Vector<String>();
					for(int i=1; i<=5; i++) {
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
