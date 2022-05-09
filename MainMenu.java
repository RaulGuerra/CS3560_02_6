import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class MainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
		
	private static MainMenu frame;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainMenu();
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
	public MainMenu() {
		setTitle("MainMenu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 215, 332);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnTables = new JButton("Tables");
		btnTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableView tv = new TableView();
				tv.setVisible(true);
			}
		});
		btnTables.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTables.setBackground(SystemColor.controlHighlight);
		btnTables.setBounds(10, 76, 180, 40);
		contentPane.add(btnTables);
		
		JButton btnReceipts = new JButton("Receipts");
		btnReceipts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchReceipts sr = new SearchReceipts();
				sr.setVisible(true);
			}
		});
		btnReceipts.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnReceipts.setBackground(SystemColor.controlHighlight);
		btnReceipts.setBounds(10, 127, 180, 40);
		contentPane.add(btnReceipts);
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FoodDialog fd = new FoodDialog();
					fd.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMenu.setBackground(SystemColor.controlHighlight);
		btnMenu.setBounds(10, 178, 180, 40);
		contentPane.add(btnMenu);
		
		JLabel lblNewLabel = new JLabel("Main Menu");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblNewLabel.setBounds(10, 11, 289, 54);
		contentPane.add(lblNewLabel);
		
		JButton btnOrders = new JButton("Orders");
		btnOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchOrders so = null;
				try {
					so = new SearchOrders();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				so.setVisible(true);
			}
		});
		btnOrders.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnOrders.setBackground(SystemColor.controlHighlight);
		btnOrders.setBounds(10, 229, 180, 40);
		contentPane.add(btnOrders);
	}
}
