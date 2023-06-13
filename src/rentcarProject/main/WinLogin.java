package rentcarProject.main;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinLogin extends JDialog {
	private JTextField tfId;
	private JTextField tfPw;
	private JLabel lbltitle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinLogin dialog = new WinLogin();
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
	public WinLogin() {
		setTitle("로그인");
		setBounds(100, 100, 253, 236);
		getContentPane().setLayout(null);
		
		tfId = new JTextField();
		tfId.setText("Userid");
		tfId.setBounds(12, 77, 216, 21);
		getContentPane().add(tfId);
		tfId.setColumns(10);
		
		tfPw = new JTextField();
		tfPw.setText("Password");
		tfPw.setColumns(10);
		tfPw.setBounds(12, 113, 216, 21);
		getContentPane().add(tfPw);
		
		lbltitle = new JLabel("관리자 로그인 ");
		lbltitle.setFont(new Font("굴림", Font.PLAIN, 20));
		lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbltitle.setBounds(12, 10, 216, 57);
		getContentPane().add(lbltitle);
		
		JButton btnNewButton = new JButton("Sign in");
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 15));
		btnNewButton.setBounds(12, 144, 216, 43);
		getContentPane().add(btnNewButton);

	}
}
