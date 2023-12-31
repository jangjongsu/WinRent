package rentcarProject.car;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinCarInsert extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfCname;
	private JTextField tfCbrend;
	private JTextField tfCclass;
	private JTextField tfCcolor;
	private JTextField tfCoil;
	private JTextField tfCtype;
	private JTextField tfPrice;
	private JFileChooser chooser;
	private String filePath;
	private JTextArea tfCnote;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinCarInsert dialog = new WinCarInsert();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WinCarInsert() {
		setTitle("차량 등록");
		setBounds(100, 100, 574, 420);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCimg = new JLabel("");
		lblCimg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					chooser = new JFileChooser("C:/eclipse-workspace/rentcarProject/src/rentcarProject/resources");
					FileNameExtensionFilter filter =new FileNameExtensionFilter("이미지파일", "jpg","gif","png","bmp");
					chooser.setFileFilter(filter);
					
					if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						filePath = chooser.getSelectedFile().getPath();
						ImageIcon icon = new ImageIcon(filePath);
						Image image = icon.getImage();
						image = image.getScaledInstance(150, 200, Image.SCALE_SMOOTH);
						icon = new ImageIcon(image);
						lblCimg.setIcon(icon);
						filePath = filePath.replaceAll("\\\\","\\\\\\\\");
					}
				}
			}
		});
		lblCimg.setOpaque(true);
		lblCimg.setBackground(new Color(255, 255, 255));
		lblCimg.setBounds(22, 10, 150, 201);
		contentPanel.add(lblCimg);
		
		JLabel lblCname = new JLabel("차량 명칭 :");
		lblCname.setBounds(194, 13, 83, 15);
		contentPanel.add(lblCname);
		
		tfCname = new JTextField();
		tfCname.setBounds(289, 10, 261, 21);
		contentPanel.add(tfCname);
		tfCname.setColumns(10);
		
		JLabel lblCname_1 = new JLabel("브 랜 드 :");
		lblCname_1.setBounds(194, 41, 83, 15);
		contentPanel.add(lblCname_1);
		
		tfCbrend = new JTextField();
		tfCbrend.setColumns(10);
		tfCbrend.setBounds(289, 38, 261, 21);
		contentPanel.add(tfCbrend);
		
		JLabel lblCname_2 = new JLabel("차 종 :");
		lblCname_2.setBounds(194, 69, 83, 15);
		contentPanel.add(lblCname_2);
		
		tfCclass = new JTextField();
		tfCclass.setColumns(10);
		tfCclass.setBounds(289, 66, 261, 21);
		contentPanel.add(tfCclass);
		
		JLabel lblCname_3 = new JLabel("차량 색상 :");
		lblCname_3.setBounds(194, 97, 83, 15);
		contentPanel.add(lblCname_3);
		
		tfCcolor = new JTextField();
		tfCcolor.setColumns(10);
		tfCcolor.setBounds(289, 94, 261, 21);
		contentPanel.add(tfCcolor);
		
		JLabel lblCname_5 = new JLabel("차량 유종 :");
		lblCname_5.setBounds(194, 125, 83, 15);
		contentPanel.add(lblCname_5);
		
		tfCoil = new JTextField();
		tfCoil.setColumns(10);
		tfCoil.setBounds(289, 122, 261, 21);
		contentPanel.add(tfCoil);
		
		JLabel lblCname_6 = new JLabel("기어 타입 :");
		lblCname_6.setBounds(194, 153, 83, 15);
		contentPanel.add(lblCname_6);
		
		tfCtype = new JTextField();
		tfCtype.setColumns(10);
		tfCtype.setBounds(289, 150, 261, 21);
		contentPanel.add(tfCtype);
		
		tfCnote = new JTextArea();
		tfCnote.setBounds(68, 221, 482, 108);
		contentPanel.add(tfCnote);
		
		JLabel lblCname_6_1 = new JLabel("비 고 :");
		lblCname_6_1.setBounds(22, 221, 528, 15);
		contentPanel.add(lblCname_6_1);
		
		JLabel lblCname_6_2 = new JLabel("단위 비용/일 :");
		lblCname_6_2.setBounds(194, 181, 83, 15);
		contentPanel.add(lblCname_6_2);
		
		tfPrice = new JTextField();
		tfPrice.setColumns(10);
		tfPrice.setBounds(289, 178, 261, 21);
		contentPanel.add(tfPrice);
		
		JButton btnCarInsert = new JButton("차량 등록");
		btnCarInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carInsert();
			}
		});
		btnCarInsert.setBounds(196, 339, 114, 36);
		contentPanel.add(btnCarInsert);
	}

	protected void carInsert() {
		// TODO Auto-generated method stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
	        
				
				String sql = "insert into cartbl(cindex, cbrend, cclass, cname, ccolor, coil, ctype, price, cimg, cnote) values (cartbl_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1,tfCbrend.getText());
				pstmt.setString(2,tfCclass.getText());
				pstmt.setString(3,tfCname.getText());
				pstmt.setString(4,tfCcolor.getText());
				pstmt.setString(5,tfCoil.getText());
				pstmt.setString(6,tfCtype.getText());
				pstmt.setString(7,tfPrice.getText());
				String[] sfilePath = filePath.replaceAll("\\\\","/").replaceAll("//", "/").split("/");
				String ssfilePath = "/"+sfilePath[5]+"/"+sfilePath[6]+"/"+sfilePath[7];
				pstmt.setString(8,ssfilePath);
				pstmt.setString(9,tfCnote.getText());
				int joinCheck = pstmt.executeUpdate();
				if(joinCheck == 1) {
					dispose(); 
					JOptionPane.showMessageDialog(null, "차량 정보가 등록되었습니다.");
				}
					
				
			}	 catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		
	}
}
