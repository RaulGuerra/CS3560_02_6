
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import javax.swing.SwingConstants;

import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ReceiptPage extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReceiptPage frame = new ReceiptPage();
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
	public ReceiptPage() {
		boolean paid = false;
		
		double salesTax = 0.0725;

		String[] orderColumns = { "Quantity", "Food Name", "Price" };

		String[][] orderData = { { "3", "Chicken Burger", "$30.00" }, { "2", "Ham Sandwich", "$15.00" },
				{ "1", "Ice Cream", "$5.00" }, { "1", "Waffle", "$5.00" }, { "1", "Pancake", "$6.00" },
				{ "1", "Eggs", "$7.00" }, { "1", "French Fries", "$5.00" }, { "1", "Steak", "$10.00" },
				{ "1", "Turkey", "$10.00" },
				{ "3", "Chicken Burger", "$30.00" }, { "2", "Ham Sandwich", "$15.00" },
				{ "1", "Ice Cream", "$5.00" }, { "1", "Waffle", "$5.00" }, { "1", "Pancake", "$6.00" },
				{ "1", "Eggs", "$7.00" }, { "1", "French Fries", "$5.00" }, { "1", "Steak", "$10.00" },
				{ "1", "Turkey", "$10.00" },
				{ "3", "Chicken Burger", "$30.00" }, { "2", "Ham Sandwich", "$15.00" },
				{ "1", "Ice Cream", "$5.00" }, { "1", "Waffle", "$5.00" }, { "1", "Pancake", "$6.00" },
				{ "1", "Eggs", "$7.00" }, { "1", "French Fries", "$5.00" }, { "1", "Steak", "$10.00" },
				{ "1", "Turkey", "$10.00" },
				{ "3", "Chicken Burger", "$30.00" }, { "2", "Ham Sandwich", "$15.00" },
				{ "1", "Ice Cream", "$5.00" }, { "1", "Waffle", "$5.00" }, { "1", "Pancake", "$6.00" },
				{ "1", "Eggs", "$7.00" }, { "1", "French Fries", "$5.00" }, { "1", "Steak", "$10.00" },
				{ "1", "Turkey", "$10.00" },

		};

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(25, 25, 260, 476);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("McDonald's");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(10, 10, 240, 14);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Washington, DC 20500");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2_1.setBounds(10, 40, 240, 14);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("1600 Pennsylvania Ave NW");
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2_1_1_1.setBounds(10, 25, 240, 14);
		lblNewLabel_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_2_1_1_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("4/20/22 2:42 PM");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1.setBounds(10, 55, 240, 14);
		panel.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_3 = new JLabel("Total");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(10, 450, 46, 14);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("Tax");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3_1.setBounds(10, 435, 46, 14);
		panel.add(lblNewLabel_3_1);

		JLabel lblNewLabel_3_1_1 = new JLabel("Subtotal");
		lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3_1_1.setBounds(10, 420, 46, 14);
		panel.add(lblNewLabel_3_1_1);

		JLabel lblNewLabel_3_2 = new JLabel("$0.00");
		lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3_2.setBounds(178, 450, 72, 14);
		panel.add(lblNewLabel_3_2);

		JLabel lblNewLabel_3_1_2 = new JLabel("$0.00");
		lblNewLabel_3_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3_1_2.setBounds(178, 435, 72, 14);
		panel.add(lblNewLabel_3_1_2);

		JLabel lblNewLabel_3_1_2_1 = new JLabel("$0.00");
		lblNewLabel_3_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3_1_2_1.setBounds(178, 420, 72, 14);
		panel.add(lblNewLabel_3_1_2_1);

		JLabel lblNewLabel_2_1_1_2 = new JLabel("Receipt #123");
		lblNewLabel_2_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2_1_1_2.setBounds(10, 70, 240, 14);
		panel.add(lblNewLabel_2_1_1_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(10, 90, 240, 319);
		panel.add(scrollPane);

		table = new JTable(orderData, orderColumns);
		table.setRowSelectionAllowed(false);
		table.setShowGrid(false);
		table.setTableHeader(null); // removes column header
		scrollPane.setColumnHeader(null); // removes column header
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer); // aligns it to the right
		table.setDefaultEditor(Object.class, null); // makes cells uneditable
		table.setFillsViewportHeight(true); // fills in empty rows
		scrollPane.setViewportView(table);

		JButton btnViewTicket = new JButton("New Receipt");
		btnViewTicket.setBounds(295, 279, 180, 40);
		btnViewTicket.setBackground(SystemColor.controlHighlight);
		btnViewTicket.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(btnViewTicket);

		JLabel lblNewLabel_4 = new JLabel("Table number:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(295, 75, 141, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_4_1_1 = new JLabel("Date:");
		lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4_1_1.setBounds(295, 50, 141, 14);
		contentPane.add(lblNewLabel_4_1_1);

		JLabel lblNewLabel_4_1_1_1 = new JLabel("Payment status:");
		lblNewLabel_4_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4_1_1_1.setBounds(295, 100, 108, 14);
		contentPane.add(lblNewLabel_4_1_1_1);

		// radial buttons
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Paid");
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnNewRadioButton.setBounds(305, 121, 74, 23);
		contentPane.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Unpaid");
		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnNewRadioButton_1.setBounds(305, 147, 74, 23);
		contentPane.add(rdbtnNewRadioButton_1);

		// radial buttons group
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton_1);

		if (paid) {
			rdbtnNewRadioButton.setSelected(true);
		} else {
			rdbtnNewRadioButton_1.setSelected(true);
		}

		JButton btnUpdateReceipt = new JButton("Update Receipt");
		btnUpdateReceipt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdateReceipt.setBackground(SystemColor.controlHighlight);
		btnUpdateReceipt.setBounds(295, 177, 180, 40);
		contentPane.add(btnUpdateReceipt);

		JLabel lblNewLabel_4_2 = new JLabel("1");
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4_2.setBounds(383, 75, 93, 14);
		contentPane.add(lblNewLabel_4_2);

		JLabel lblNewLabel_4_2_2 = new JLabel("4/20/22 2:42 PM");
		lblNewLabel_4_2_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_2_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4_2_2.setBounds(328, 50, 147, 14);
		contentPane.add(lblNewLabel_4_2_2);

		JButton btnSearchReceipts = new JButton("Search Receipts");
		btnSearchReceipts.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearchReceipts.setBackground(SystemColor.controlHighlight);
		btnSearchReceipts.setBounds(295, 381, 180, 40);
		contentPane.add(btnSearchReceipts);

		JButton btnReturnToTables = new JButton("Return to Tables");
		btnReturnToTables.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnReturnToTables.setBackground(SystemColor.controlHighlight);
		btnReturnToTables.setBounds(295, 432, 180, 40);
		contentPane.add(btnReturnToTables);

		JButton btnRemoveReceipt = new JButton("Remove Receipt");
		btnRemoveReceipt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRemoveReceipt.setBackground(SystemColor.controlHighlight);
		btnRemoveReceipt.setBounds(295, 330, 180, 40);
		contentPane.add(btnRemoveReceipt);

		JLabel lblNewLabel_4_1_1_1_1 = new JLabel("Receipt:");
		lblNewLabel_4_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4_1_1_1_1.setBounds(295, 25, 108, 14);
		contentPane.add(lblNewLabel_4_1_1_1_1);

		JLabel lblNewLabel_4_2_1_1 = new JLabel("123");
		lblNewLabel_4_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4_2_1_1.setBounds(345, 25, 130, 14);
		contentPane.add(lblNewLabel_4_2_1_1);

		JLabel lblNewLabel_4_2_1 = new JLabel("Unpaid");
		lblNewLabel_4_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4_2_1.setBounds(401, 100, 74, 14);
		contentPane.add(lblNewLabel_4_2_1);

		JButton btnEditOrders = new JButton("Edit Orders");
		btnEditOrders.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEditOrders.setBackground(SystemColor.controlHighlight);
		btnEditOrders.setBounds(295, 228, 180, 40);
		contentPane.add(btnEditOrders);

	}
}
