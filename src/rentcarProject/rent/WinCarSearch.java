package rentcarProject.rent;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WinCarSearch extends JDialog {
	private JTable table;
	private String cindex;
	private String rtdate;
	private String returndate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinCarSearch dialog = new WinCarSearch();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WinCarSearch() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int row = table.getSelectedRow();
					if(row !=-1) {
						setCindex(table.getValueAt(row, 0).toString()); // id
						dispose();
					}
					
					
				}
			});
			scrollPane.setViewportView(table);
		}
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JButton btnSearch = new JButton("조회");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carSearch(rtdate, returndate);
			}
		});
		panel.add(btnSearch);
	}

	protected void carSearch(String rtdate2, String returndate2) {
		// TODO Auto-generated method stub
		String columns[] = {"차량고유식별번호","차량브랜드","차종","차량명칭","차량색상","차량유종","차량기어타입","단위비용/일"};
		DefaultTableModel dtm = (DefaultTableModel)table.getModel();
		dtm.setColumnIdentifiers(columns);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
	        
			
				String sql = "select * "
						+ "    from cartbl "
						+ "        left join (select * "
						+ "    from rReservation"
						+ "         where rtdate BETWEEN ? AND ? or"
						+ "          					 returndate BETWEEN ? AND ?)A"
						+ "             on cartbl.CINDEX = A.cindex"
						+ "                where A.cindex is null" ;
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, rtdate);
				pstmt.setString(2, returndate);
				pstmt.setString(3, rtdate);
				pstmt.setString(4, returndate);
				ResultSet rs = pstmt.executeQuery();
				
				
				DefaultTableModel dtm2 = (DefaultTableModel)table.getModel();
				
				dtm2.setRowCount(0);
				
				int count = 0;
				
				while(rs.next()) {
					Vector<String> vector = new Vector<String>();
					for(int i=1; i<=10; i++) {
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

	public String getCindex() {
		return cindex;
	}

	public void setCindex(String cindex) {
		this.cindex = cindex;
	}

	public WinCarSearch(String rtdate, String returndate) {
		// TODO Auto-generated constructor stub
		this();
		this.rtdate = rtdate;
		this.returndate = returndate;
		
	}

}
