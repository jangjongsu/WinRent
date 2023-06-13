package rentcarProject.rent;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import rentcarProject.member.WinMemberList;
import rentcarProject.util.Win_calendar;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextArea;

public class WinRentInsert extends JDialog {
	private JTextField tfId;
	private JTextField textField;
	private JTextField tfRtdate;
	private JTextField tfReturndate;
	private JButton btnCal1;
	private JTextField tfPrice;
	private JTextField tfCtype;
	private JTextField tfCoil;
	private JTextField tfCcolor;
	private JTextField tfCclass;
	private JTextField tfCbend;
	private JTextField tfCname;
	private JButton btnCal2;
	private JButton btnUpCar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinRentInsert dialog = new WinRentInsert();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WinRentInsert() {
		setTitle("예약 등록");
		setBounds(100, 100, 817, 541);
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
			JButton btnUpId = new JButton("아이디 조회");
			btnUpId.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					WinIdSearch winIdSearch = new WinIdSearch();
					winIdSearch.setModal(true);
					winIdSearch.setVisible(true);
					tfId.setText(winIdSearch.getRid());
					if(tfId.getText() !=null) {
						btnCal1.setEnabled(true);
					}
				}
			});
			btnUpId.setBounds(253, 10, 133, 23);
			getContentPane().add(btnUpId);
		}
		{
			JLabel lbCindex = new JLabel("차량번호 :");
			lbCindex.setBounds(12, 100, 78, 15);
			getContentPane().add(lbCindex);
		}
		{
			textField = new JTextField();
			textField.setEnabled(false);
			textField.setColumns(10);
			textField.setBounds(102, 97, 139, 21);
			getContentPane().add(textField);
		}
		{
			btnUpCar = new JButton("차량 조회");
			btnUpCar.setEnabled(false);
			btnUpCar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String rtdate = tfRtdate.getText();
					String returndate = tfReturndate.getText();
					WinICarSearch winICarSearch = new WinICarSearch(rtdate, returndate);
					winICarSearch.setVisible(true);
					winICarSearch.setModal(true);
					
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
			btnCal1.setEnabled(false);
			btnCal1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Win_calendar calendar = new Win_calendar();
					calendar.setModal(true);
					calendar.setVisible(true);
					tfRtdate.setText(calendar.getDate());
					
					Calendar today = Calendar.getInstance();
					String sYear = Integer.toString(today.get(Calendar.YEAR));
					if(tfRtdate.getText() !=null) {
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
					tfReturndate.setText(calendar.getDate());
					
					Calendar today = Calendar.getInstance();
					String sYear = Integer.toString(today.get(Calendar.YEAR));
					if(tfReturndate.getText() !=null) {
						btnUpCar.setEnabled(true);
					}
				}
			});
			btnCal2.setEnabled(false);
			btnCal2.setBounds(253, 67, 133, 23);
			getContentPane().add(btnCal2);
		}
		
		JLabel lblCimg = new JLabel("");
		lblCimg.setEnabled(false);
		lblCimg.setOpaque(true);
		lblCimg.setBackground(Color.WHITE);
		lblCimg.setBounds(12, 131, 150, 201);
		getContentPane().add(lblCimg);
		
		JLabel lblCname_6_1 = new JLabel("비 고 :");
		lblCname_6_1.setBounds(12, 342, 528, 15);
		getContentPane().add(lblCname_6_1);
		
		JTextArea tfCnote = new JTextArea();
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
		
		tfCbend = new JTextField();
		tfCbend.setEnabled(false);
		tfCbend.setColumns(10);
		tfCbend.setBounds(279, 156, 261, 21);
		getContentPane().add(tfCbend);
		
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
		
		JButton btnRentInsert = new JButton("예약 정보 등록");
		btnRentInsert.setBounds(207, 460, 121, 31);
		getContentPane().add(btnRentInsert);
	}
}
