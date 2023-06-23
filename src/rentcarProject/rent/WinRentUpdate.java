package rentcarProject.rent;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;



import rentcarProject.util.Win_calendar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextArea;

public class WinRentUpdate extends JDialog {
	private JTextField tfId;
	private JTextField tfCindex;
	private JTextField tfRtdate;
	private JTextField tfReturndate;
	private JButton btnCal1;
	private JTextField tfPrice;
	private JTextField tfCtype;
	private JTextField tfCoil;
	private JTextField tfCcolor;
	private JTextField tfCclass;
	private JTextField tfCbrend;
	private JTextField tfCname;
	private JButton btnCal2;
	private JButton btnUpCar;
	private JTextArea tfCnote;
	private JLabel lblCimg;
	private String rtnum;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinRentUpdate dialog = new WinRentUpdate();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WinRentUpdate() {
		setTitle("예약 등록");
		setBounds(100, 100, 571, 541);
		getContentPane().setLayout(null);
		{
			JLabel lblId = new JLabel("아이디 : ");
			lblId.setBounds(12, 14, 78, 15);
			getContentPane().add(lblId);
		}
		{
			tfId = new JTextField();
			tfId.setEnabled(false);
			tfId.setBounds(102, 11, 139, 21);
			getContentPane().add(tfId);
			tfId.setColumns(10);
		}
		{
			JLabel lbCindex = new JLabel("차량번호 :");
			lbCindex.setBounds(12, 100, 78, 15);
			getContentPane().add(lbCindex);
		}
		{
			tfCindex = new JTextField();
			tfCindex.setEnabled(false);
			tfCindex.setColumns(10);
			tfCindex.setBounds(102, 97, 139, 21);
			getContentPane().add(tfCindex);
		}
		{
			btnUpCar = new JButton("차량 조회");
			btnUpCar.setEnabled(false);
			btnUpCar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String rtdate = tfRtdate.getText();
					String returndate = tfReturndate.getText();
					WinCarSearch winCarSearch = new WinCarSearch(rtdate, returndate);
					winCarSearch.setModal(true);
					winCarSearch.setVisible(true);
					tfCindex.setText(winCarSearch.getCindex());
					carView(winCarSearch.getCindex());
				}
			});
			btnUpCar.setBounds(253, 96, 133, 23);
			getContentPane().add(btnUpCar);
		}
		{
			JLabel lbCindex = new JLabel("대여 일시  :");
			lbCindex.setBounds(12, 43, 78, 15);
			getContentPane().add(lbCindex);
		}
		{
			tfRtdate = new JTextField();
			tfRtdate.setEnabled(false);
			tfRtdate.setColumns(10);
			tfRtdate.setBounds(102, 40, 139, 21);
			getContentPane().add(tfRtdate);
		}
		{
			JLabel lbCindex = new JLabel("반납 일시 :");
			lbCindex.setBounds(12, 71, 78, 15);
			getContentPane().add(lbCindex);
		}
		{
			tfReturndate = new JTextField();
			tfReturndate.setEnabled(false);
			tfReturndate.setColumns(10);
			tfReturndate.setBounds(102, 68, 139, 21);
			getContentPane().add(tfReturndate);
		}
		{
			btnCal1 = new JButton("날짜 선택");
			btnCal1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Win_calendar calendar = new Win_calendar();
					calendar.setModal(true);
					calendar.setVisible(true);
					
					Calendar today = Calendar.getInstance();
					String sYear = Integer.toString(today.get(Calendar.YEAR));
					LocalDate localDate1 = LocalDate.parse(calendar.getDate());
					LocalDate now = LocalDate.now();
					
					if(localDate1.isBefore(now)) {
						JOptionPane.showMessageDialog(null, "오늘보다 이전의 날짜는 선택할수없습니다.");
						tfRtdate.setText("");
					}else {
						tfRtdate.setText(calendar.getDate());
						btnCal2.setEnabled(true);
					}
				}
			});
			btnCal1.setBounds(253, 39, 133, 23);
			getContentPane().add(btnCal1);
		}
		{
			btnCal2 = new JButton("날짜 선택");
			btnCal2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Win_calendar calendar = new Win_calendar();
					calendar.setModal(true);
					calendar.setVisible(true);
					
					Calendar today = Calendar.getInstance();
					String sYear = Integer.toString(today.get(Calendar.YEAR));
					LocalDate localDate1 = LocalDate.parse(tfRtdate.getText());
					LocalDate localDate2 = LocalDate.parse(calendar.getDate());
					
					if(localDate2.isBefore(localDate1) || localDate2.equals(localDate1)) {
						JOptionPane.showMessageDialog(null, "시작 이후의 날짜를 선택해주세요.");
						tfReturndate.setText("");
					}else {
						tfReturndate.setText(calendar.getDate());
						btnUpCar.setEnabled(true);
					}
				}
			});
			btnCal2.setEnabled(false);
			btnCal2.setBounds(253, 67, 133, 23);
			getContentPane().add(btnCal2);
		}
		
		lblCimg = new JLabel("");
		lblCimg.setOpaque(true);
		lblCimg.setBackground(Color.WHITE);
		lblCimg.setBounds(12, 131, 150, 201);
		getContentPane().add(lblCimg);
		
		JLabel lblCname_6_1 = new JLabel("비 고 :");
		lblCname_6_1.setBounds(12, 342, 528, 15);
		getContentPane().add(lblCname_6_1);
		
		tfCnote = new JTextArea();
		tfCnote.setEnabled(false);
		tfCnote.setBounds(58, 342, 482, 108);
		getContentPane().add(tfCnote);
		
		tfPrice = new JTextField();
		tfPrice.setEnabled(false);
		tfPrice.setColumns(10);
		tfPrice.setBounds(279, 299, 261, 21);
		getContentPane().add(tfPrice);
		
		tfCtype = new JTextField();
		tfCtype.setEnabled(false);
		tfCtype.setColumns(10);
		tfCtype.setBounds(279, 271, 261, 21);
		getContentPane().add(tfCtype);
		
		tfCoil = new JTextField();
		tfCoil.setEnabled(false);
		tfCoil.setColumns(10);
		tfCoil.setBounds(279, 243, 261, 21);
		getContentPane().add(tfCoil);
		
		tfCcolor = new JTextField();
		tfCcolor.setEnabled(false);
		tfCcolor.setColumns(10);
		tfCcolor.setBounds(279, 215, 261, 21);
		getContentPane().add(tfCcolor);
		
		tfCclass = new JTextField();
		tfCclass.setEnabled(false);
		tfCclass.setColumns(10);
		tfCclass.setBounds(279, 187, 261, 21);
		getContentPane().add(tfCclass);
		
		tfCbrend = new JTextField();
		tfCbrend.setEnabled(false);
		tfCbrend.setColumns(10);
		tfCbrend.setBounds(279, 156, 261, 21);
		getContentPane().add(tfCbrend);
		
		tfCname = new JTextField();
		tfCname.setEnabled(false);
		tfCname.setColumns(10);
		tfCname.setBounds(279, 131, 261, 21);
		getContentPane().add(tfCname);
		
		JLabel lblCname = new JLabel("차량 명칭 :");
		lblCname.setBounds(184, 134, 83, 15);
		getContentPane().add(lblCname);
		
		JLabel lblCname_1 = new JLabel("브 랜 드 :");
		lblCname_1.setBounds(184, 162, 83, 15);
		getContentPane().add(lblCname_1);
		
		JLabel lblCname_2 = new JLabel("차 종 :");
		lblCname_2.setBounds(184, 190, 83, 15);
		getContentPane().add(lblCname_2);
		
		JLabel lblCname_3 = new JLabel("차량 색상 :");
		lblCname_3.setBounds(184, 218, 83, 15);
		getContentPane().add(lblCname_3);
		
		JLabel lblCname_5 = new JLabel("차량 유종 :");
		lblCname_5.setBounds(184, 246, 83, 15);
		getContentPane().add(lblCname_5);
		
		JLabel lblCname_6 = new JLabel("기어 타입 :");
		lblCname_6.setBounds(184, 274, 83, 15);
		getContentPane().add(lblCname_6);
		
		JLabel lblCname_6_2 = new JLabel("단위 비용/일 :");
		lblCname_6_2.setBounds(184, 302, 83, 15);
		getContentPane().add(lblCname_6_2);
		
		JButton btnRentUpdate = new JButton("예약 정보 수정");
		btnRentUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    try {
					rentUpdate(rtnum);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnRentUpdate.setBounds(197, 461, 121, 31);
		getContentPane().add(btnRentUpdate);
	}

	protected void rentUpdate(String rtnum2) throws ParseException {
		// TODO Auto-generated method stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
	        String sql = "update rReservation set cindex = ?, rtdate = ?, returndate= ?, rpdate = ?, rtPrice = ?  where rtnum = ?";
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setString(1,tfCindex.getText());
	        pstmt.setString(2,tfRtdate.getText());
	        pstmt.setString(3,tfReturndate.getText());
	        pstmt.setString(4, tfRtdate.getText()+"~"+tfReturndate.getText());
			
			Date formatRtdate = new SimpleDateFormat("yyyy-MM-dd").parse(tfRtdate.getText());
			Date formatReturnDate = new SimpleDateFormat("yyyy-MM-dd").parse(tfReturndate.getText());
			
			pstmt.setString(2,tfRtdate.getText());
		    pstmt.setString(3,tfReturndate.getText());
		    pstmt.setString(4, tfRtdate.getText()+"~"+tfReturndate.getText());
			long diffdays = ((formatReturnDate.getTime()-formatRtdate.getTime()) / 1000 )/ (24*60*60);
			int totalPrice = (Integer.parseInt(tfPrice.getText()) * (int)diffdays);
			
			pstmt.setInt(5, totalPrice);
			pstmt.setString(6, rtnum2);
	      
	        int updateCheck = pstmt.executeUpdate();
	        if(updateCheck == 1) {
	        	dispose();
	        	JOptionPane.showMessageDialog(null, "예약 정보가 수정되었습니다.");
	        }else {
	        	JOptionPane.showMessageDialog(null, "예약 수정 실패");
	        }
					
			}catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		
	}

	public WinRentUpdate(String rtnum) {
		this();
		// TODO Auto-generated constructor stub
		rentView(rtnum);
	}

	private void rentView(String rtnum) {
		// TODO Auto-generated method stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
			
				String sql = "select * "
						+ "    from rReservation"
						+ "        INNER join cartbl"
						+ "            on cartbl.CINDEX = rreservation.cindex"
						+ "                where rtnum = ?";
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1,rtnum);
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
					tfId.setText(rs.getString("rid"));
					tfRtdate.setText(rs.getString("rtdate"));
					tfReturndate.setText(rs.getString("returndate"));
					tfCindex.setText(rs.getString("cindex"));
					tfCbrend.setText(rs.getString("cbrend"));
					tfCclass.setText(rs.getString("cclass"));
					tfCname.setText(rs.getString("cname"));
					tfCcolor.setText(rs.getString("ccolor"));
					tfCoil.setText(rs.getString("coil"));
					tfCtype.setText(rs.getString("ctype"));
					tfPrice.setText(rs.getString("price"));
					String cimg = rs.getString("cimg");
					String filePath = "C:\\\\eclipse-workspace\\\\rentcarProject\\\\src\\\\rentcarProject"+cimg.replaceAll("/","\\\\\\\\");
					tfCnote.setText(rs.getString("cnote"));
					ImageIcon icon = new ImageIcon(filePath);
					Image image = icon.getImage();
					image = image.getScaledInstance(150, 200, Image.SCALE_SMOOTH);
					icon = new ImageIcon(image);
					lblCimg.setIcon(icon);
				}
				
			}	 catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		this.rtnum = rtnum;
		
	}

	

	protected void carView(String cindex) {
		// TODO Auto-generated method stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","1234");
			
				String sql = "SELECT * FROM cartbl where cindex = ?";
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1,cindex);
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
					cindex = rs.getString("cindex");
					tfCbrend.setText(rs.getString("cbrend"));
					tfCclass.setText(rs.getString("cclass"));
					tfCname.setText(rs.getString("cname"));
					tfCcolor.setText(rs.getString("ccolor"));
					tfCoil.setText(rs.getString("coil"));
					tfCtype.setText(rs.getString("ctype"));
					tfPrice.setText(rs.getString("price"));
					String cimg = rs.getString("cimg");
					String filePath = "C:\\\\eclipse-workspace\\\\rentcarProject\\\\src\\\\rentcarProject"+cimg.replaceAll("/","\\\\\\\\");
					tfCnote.setText(rs.getString("cnote"));
					ImageIcon icon = new ImageIcon(filePath);
					Image image = icon.getImage();
					image = image.getScaledInstance(150, 200, Image.SCALE_SMOOTH);
					icon = new ImageIcon(image);
					lblCimg.setIcon(icon);
				}
				
			}	 catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		
		
	}
}
