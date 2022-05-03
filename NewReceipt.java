import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;

public class NewReceipt extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewReceipt frame = new NewReceipt();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewReceipt() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 240, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Table number:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 76, 134, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(10, 101, 150, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewReceipt = new JLabel("New Receipt");
		lblNewReceipt.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblNewReceipt.setBounds(10, 11, 289, 54);
		contentPane.add(lblNewReceipt);
		
		JButton btnAddOrders = new JButton("Add Orders");
		btnAddOrders.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAddOrders.setBackground(SystemColor.controlHighlight);
		btnAddOrders.setBounds(10, 132, 180, 40);
		contentPane.add(btnAddOrders);
		
		JButton btnReturnToMenu = new JButton("Return to Menu");
		btnReturnToMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnReturnToMenu.setBackground(SystemColor.controlHighlight);
		btnReturnToMenu.setBounds(10, 183, 180, 40);
		contentPane.add(btnReturnToMenu);
	}
}
