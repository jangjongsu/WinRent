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

public class WinICarSearch extends JDialog {
	private JTable table;
	private String cindex;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinICarSearch dialog = new WinICarSearch();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WinICarSearch() {
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
	}

	public WinICarSearch(String rtdate, String returndate) {
		// TODO Auto-generated constructor stub
		this();
		String columns[] = {"차량고유식별번호","차량브랜드","차종","차량명칭","차량색상","차량유종","차량기어타입","단위비용/일","차량이미지","비고"};
		DefaultTableModel dtm = (DefaultTableModel)table.getModel();
		dtm.setColumnIdentifiers(columns);
	}

	public String getCindex() {
		return cindex;
	}
  
	public void setCindex(String cindex) {
		this.cindex = cindex;
	}


}
