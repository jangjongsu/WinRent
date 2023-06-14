package rentcarProject.main;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;

import rentcarProject.car.WinCarList;
import rentcarProject.member.WinMemberList;
import rentcarProject.rent.WinRentList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinMain extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinMain dialog = new WinMain();
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
	public WinMain() {
		setTitle("메인 페이지");
		setBounds(100, 100, 450, 265);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnMember = new JButton("회원관리");
		btnMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinMemberList winMemberList = new WinMemberList();
				winMemberList.setModal(true);
				winMemberList.setVisible(true);
			}
		});
		btnMember.setBounds(12, 10, 410, 62);
		panel.add(btnMember);
		
		JButton btnCar = new JButton("차량관리");
		btnCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinCarList winCarList = new WinCarList();
				winCarList.setModal(true);
				winCarList.setVisible(true);
			}
		});
		btnCar.setBounds(12, 82, 410, 62);
		panel.add(btnCar);
		
		JButton btnReservation = new JButton("예약관리");
		btnReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinRentList winRentList = new WinRentList();
				winRentList.setModal(true);
				winRentList.setVisible(true);
			}
		});
		btnReservation.setBounds(12, 154, 410, 62);
		panel.add(btnReservation);

	}

}
