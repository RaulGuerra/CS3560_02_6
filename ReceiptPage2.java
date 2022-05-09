import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

// this is for specific checkID
public class ReceiptPage2 extends JDialog {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReceiptPage2 frame = new ReceiptPage2(1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private JTable table;

	private Receipt receipt;


	/**
	 * Create the frame.
	 */
	public ReceiptPage2(int checkID) {
		setModalityType(ModalityType.APPLICATION_MODAL);
		Table table1 = new Table(); // table in database

		receipt = new Receipt();
		receipt.getReceipt(checkID);
		int tableNumber = receipt.getTableNumber();

		String[] orderColumns = { "Quantity", "Food Name", "Price" };
		String[][] orderData = receipt.getOrderQuantities(receipt.getCheckID());
		// dummy data start
//		String[][] orderData = { { "3", "Chicken Burger", "$30.00" }, { "2", "Ham Sandwich", "$15.00" },
//				{ "1", "Ice Cream", "$5.00" }, { "1", "Waffle", "$5.00" }, { "1", "Pancake", "$6.00" },
//				{ "1", "Eggs", "$7.00" }, { "1", "French Fries", "$5.00" }, { "1", "Steak", "$10.00" },
//				{ "1", "Turkey", "$10.00" },
//				{ "3", "Chicken Burger", "$30.00" }, { "2", "Ham Sandwich", "$15.00" },
//				{ "1", "Ice Cream", "$5.00" }, { "1", "Waffle", "$5.00" }, { "1", "Pancake", "$6.00" },
//				{ "1", "Eggs", "$7.00" }, { "1", "French Fries", "$5.00" }, { "1", "Steak", "$10.00" },
//				{ "1", "Turkey", "$10.00" },
//				{ "3", "Chicken Burger", "$30.00" }, { "2", "Ham Sandwich", "$15.00" },
//				{ "1", "Ice Cream", "$5.00" }, { "1", "Waffle", "$5.00" }, { "1", "Pancake", "$6.00" },
//				{ "1", "Eggs", "$7.00" }, { "1", "French Fries", "$5.00" }, { "1", "Steak", "$10.00" },
//				{ "1", "Turkey", "$10.00" },
//				{ "3", "Chicken Burger", "$30.00" }, { "2", "Ham Sandwich", "$15.00" },
//				{ "1", "Ice Cream", "$5.00" }, { "1", "Waffle", "$5.00" }, { "1", "Pancake", "$6.00" },
//				{ "1", "Eggs", "$7.00" }, { "1", "French Fries", "$5.00" }, { "1", "Steak", "$10.00" },
//				{ "1", "Turkey", "$10.00" },
//
//		};
		// dummy data end

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 520, 610);
		setLocationRelativeTo(null);

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

		JLabel lblNewLabel_2_1_1 = new JLabel(String.valueOf(receipt.getDate()));
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1.setBounds(10, 55, 240, 14);
		panel.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_3 = new JLabel("Total");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(10, 450, 46, 14);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_3_2 = new JLabel("$" + String.format("%.02f", receipt.getTotal()));
		lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3_2.setBounds(178, 450, 72, 14);
		panel.add(lblNewLabel_3_2);

		JLabel lblNewLabel_2_1_1_2 = new JLabel("Receipt #" + receipt.getCheckID());
		lblNewLabel_2_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2_1_1_2.setBounds(10, 70, 240, 14);
		panel.add(lblNewLabel_2_1_1_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(10, 90, 240, 349);
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
		btnViewTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table1.getOccupied(tableNumber)) { // can't create receipt because occupied table
					JOptionPane.showMessageDialog(btnViewTicket,
							"Cannot create new receipt because the table is currently occupied");
				} else { // new receipt created
					receipt.addReceipt(tableNumber);
					try {
						table1.setOccupied(tableNumber, true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					dispose();
					ReceiptPage rp = new ReceiptPage(tableNumber);
					rp.setVisible(true);
				}
			}
		});
		btnViewTicket.setBounds(295, 308, 180, 40);
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
		lblNewLabel_4_1_1_1.setBounds(295, 125, 108, 14);
		contentPane.add(lblNewLabel_4_1_1_1);

		// radial buttons
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Paid");
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnNewRadioButton.setBounds(305, 146, 74, 23);
		contentPane.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Unpaid");
		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnNewRadioButton_1.setBounds(305, 172, 74, 23);
		contentPane.add(rdbtnNewRadioButton_1);


		// radial buttons group
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton_1);

		if (receipt.getCheckID() == -1) { // no checks for table
			rdbtnNewRadioButton.setEnabled(false);
			rdbtnNewRadioButton_1.setEnabled(false);
			rdbtnNewRadioButton.setSelected(false);
			rdbtnNewRadioButton_1.setSelected(false);
		} else if (receipt.getPaid()) {
			rdbtnNewRadioButton.setSelected(true);
		} else {
			rdbtnNewRadioButton_1.setSelected(true);
		}

		JLabel lblNewLabel_4_2 = new JLabel(String.valueOf(tableNumber));
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4_2.setBounds(383, 75, 93, 14);
		contentPane.add(lblNewLabel_4_2);

		JLabel lblNewLabel_4_2_2 = new JLabel(String.valueOf(receipt.getDate()));
		lblNewLabel_4_2_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_2_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4_2_2.setBounds(328, 50, 147, 14);
		contentPane.add(lblNewLabel_4_2_2);

		JButton btnSearchReceipts = new JButton("Search Receipts");
		btnSearchReceipts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				SearchReceipts sr = new SearchReceipts();
				sr.setVisible(true);

			}

		});
		btnSearchReceipts.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearchReceipts.setBackground(SystemColor.controlHighlight);
		btnSearchReceipts.setBounds(295, 512, 180, 40);
		contentPane.add(btnSearchReceipts);

		JButton btnReturnToTables = new JButton("Return to Tables");
		btnReturnToTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TableView tv = new TableView();
				tv.setVisible(true);
			}
		});
		btnReturnToTables.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnReturnToTables.setBackground(SystemColor.controlHighlight);
		btnReturnToTables.setBounds(25, 512, 260, 40);
		contentPane.add(btnReturnToTables);

		JButton btnRemoveReceipt = new JButton("Remove Receipt");
		btnRemoveReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				receipt.removeReceipt(receipt.getCheckID());
				dispose();
				ReceiptPage rp = new ReceiptPage(tableNumber);
				rp.setVisible(true);
			}
		});
		btnRemoveReceipt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRemoveReceipt.setBackground(SystemColor.controlHighlight);
		btnRemoveReceipt.setBounds(295, 359, 180, 40);
		contentPane.add(btnRemoveReceipt);

		JLabel lblNewLabel_4_1_1_1_1 = new JLabel("Receipt:");
		lblNewLabel_4_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4_1_1_1_1.setBounds(295, 25, 108, 14);
		contentPane.add(lblNewLabel_4_1_1_1_1);

		JLabel lblNewLabel_4_2_1_1 = new JLabel(String.valueOf(receipt.getCheckID()));
		lblNewLabel_4_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4_2_1_1.setBounds(345, 25, 130, 14);
		contentPane.add(lblNewLabel_4_2_1_1);

		JLabel lblNewLabel_4_2_1 = new JLabel("Unpaid");
		if (receipt.getPaid()) {
			lblNewLabel_4_2_1.setText("Paid");
		} else {
			lblNewLabel_4_2_1.setText("Unpaid");

		}
		lblNewLabel_4_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4_2_1.setBounds(401, 125, 74, 14);
		contentPane.add(lblNewLabel_4_2_1);

		JButton btnEditOrders = new JButton("Edit Orders");
		btnEditOrders.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEditOrders.setBackground(SystemColor.controlHighlight);
		btnEditOrders.setBounds(295, 257, 180, 40);
		contentPane.add(btnEditOrders);

		JButton btnUpdateReceipt = new JButton("Update Receipt");
		btnUpdateReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnNewRadioButton.isSelected()) { // paid selected
					lblNewLabel_4_2_1.setText("Paid");
					receipt.updateReceipt(receipt.getCheckID(), true);
				} else {
					lblNewLabel_4_2_1.setText("Unpaid");
					receipt.updateReceipt(receipt.getCheckID(), false);
				}
			}
		});
		btnUpdateReceipt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdateReceipt.setBackground(SystemColor.controlHighlight);
		btnUpdateReceipt.setBounds(295, 206, 180, 40);
		contentPane.add(btnUpdateReceipt);

		JLabel lblNewLabel_4_1 = new JLabel("Occupied status:");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4_1.setBounds(295, 100, 141, 14);
		contentPane.add(lblNewLabel_4_1);

		JLabel lblNewLabel_4_2_1_2 = new JLabel("Open");
		if (table1.getOccupied(tableNumber)) {
			lblNewLabel_4_2_1_2.setText("Closed");
		} else {
			lblNewLabel_4_2_1_2.setText("Open");
		}

		lblNewLabel_4_2_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4_2_1_2.setBounds(401, 100, 74, 14);
		contentPane.add(lblNewLabel_4_2_1_2);

		JButton btnOpenTable = new JButton("Open Table");
		btnOpenTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lblNewLabel_4_2_1_2.setText("Open");
					table1.setOccupied(tableNumber, false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnOpenTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnOpenTable.setBackground(SystemColor.controlHighlight);
		btnOpenTable.setBounds(295, 410, 180, 40);
		contentPane.add(btnOpenTable);

		JButton btnCloseTable = new JButton("Close Table");
		btnCloseTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lblNewLabel_4_2_1_2.setText("Closed");
					table1.setOccupied(tableNumber, true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCloseTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCloseTable.setBackground(SystemColor.controlHighlight);
		btnCloseTable.setBounds(295, 461, 180, 40);
		contentPane.add(btnCloseTable);

	}

}
