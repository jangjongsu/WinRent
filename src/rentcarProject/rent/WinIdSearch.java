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

public class WinIdSearch extends JDialog {
	private JTable table;
	private JTextField tfSearchWord;
	private String rid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinIdSearch dialog = new WinIdSearch();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WinIdSearch() {
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
						setRid(table.getValueAt(row, 0).toString()); // id
						dispose();
					}
					
				}
			});
			scrollPane.setViewportView(table);
		}
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("아이디 : ");
		panel.add(lblNewLabel);
		
		tfSearchWord = new JTextField();
		panel.add(tfSearchWord);
		tfSearchWord.setColumns(10);
		
		JButton btnIdSearch = new JButton("찾기");
		btnIdSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchId();
				
			}
		});
		panel.add(btnIdSearch);
	}

	protected void searchId() {
		// TODO Auto-generated method stub
		String cols[] = {"ID", "비밀번호","회원이름","전화번호", "이메일","가입일"};
		DefaultTableModel dtm = new DefaultTableModel(cols, 0);
		table.setModel(dtm);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
			
				String sql = "SELECT * FROM rMember";
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				ResultSet rs = pstmt.executeQuery();
				
				DefaultTableModel dtm2 = (DefaultTableModel)table.getModel();
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

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

}
