
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Timestamp;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Dialog.ModalityType;
import java.awt.Color;

public class SearchReceipts extends JDialog {

	private JPanel contentPane;
	private JTextField txtReceiptId;
	private JTextField txtDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchReceipts frame = new SearchReceipts();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Receipt receipt = new Receipt();

	// ghost text booleans
	boolean txtReceiptIdHelper = true;
	boolean txtTableNumberHelper = true;
	boolean txtTotalAmountHelper = true;
	boolean txtTotalMinHelper = true;
	boolean txtTotalMaxHelper = true;
	boolean txtDateHelper = true;
	boolean txtDateMinHelper = true;
	boolean txtDateMaxHelper = true;

	private JTextField txtDateMin;
	private JTextField txtDateMax;
	private JTextField txtTotalAmount;
	private JTextField txtTotalMin;
	private JTextField txtTotalMax;
	private JTextField txtTableNumber;
	private JTable table;

	boolean checkIdChoice = false;
	boolean tableNumberChoice = false;
	int totalAmountChoice = 0;
	int dateChoice = 0;
	boolean paidChoice = false;
	int checkID;
	int tableNumber;
	float totalAmount;
	Timestamp date;
	boolean paid;
	float totalAmountMin;
	float totalAmountMax;
	Timestamp dateMin;
	Timestamp dateMax;

	// need previous so can refresh with same query when deleting
	boolean prevCheckIdChoice = false;
	boolean prevTableNumberChoice = false;
	int prevTotalAmountChoice = 0;
	int prevDateChoice = 0;
	boolean prevPaidChoice = false;
	int prevCheckID;
	int prevTableNumber;
	float prevTotalAmount;
	Timestamp prevDate;
	boolean prevPaid;
	float prevTotalAmountMin;
	float prevTotalAmountMax;
	Timestamp prevDateMin;
	Timestamp prevDateMax;

	private void saveQueryParams() {
		prevCheckIdChoice = checkIdChoice;
		prevTableNumberChoice = tableNumberChoice;
		prevTotalAmountChoice = totalAmountChoice;
		prevDateChoice = dateChoice;
		prevPaidChoice = paidChoice;
		prevCheckID = checkID;
		prevTableNumber = tableNumber;
		prevTotalAmount = totalAmount;
		prevDate = date;
		prevPaid = paid;
		prevTotalAmountMin = totalAmountMin;
		prevTotalAmountMax = totalAmountMax;
		prevDateMin = dateMin;
		prevDateMax = dateMax;
	}

	private void formatTable() {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(160);
	}

	/**
	 * Create the frame.
	 */
	public SearchReceipts() {
		setModalityType(ModalityType.APPLICATION_MODAL);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 630, 670);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JRadioButton rdbtnReceiptIdExactly = new JRadioButton("");
		rdbtnReceiptIdExactly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkIdChoice = true;
			}
		});
		JRadioButton rdbtnTableNumberExactly = new JRadioButton("");
		rdbtnTableNumberExactly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableNumberChoice = true;
			}
		});
		JRadioButton rdbtnTotalAmountExactly = new JRadioButton("");
		rdbtnTotalAmountExactly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				totalAmountChoice = 1;
			}
		});
		JRadioButton rdbtnTotalAmountMinMax = new JRadioButton("");
		rdbtnTotalAmountMinMax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				totalAmountChoice = 2;
			}
		});
		JRadioButton rdbtnDateExactly = new JRadioButton("");
		rdbtnDateExactly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateChoice = 1;
			}
		});
		JRadioButton rdbtnDateMinMax = new JRadioButton("");
		rdbtnDateMinMax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateChoice = 2;
			}
		});

		String receiptColumns[] = { "Receipt ID", "Table #", "Total", "Date", "Paid" };

		String receiptData[][] = receipt.getAllReceipts();

		txtDateMax = new JTextField();
		txtDateMax.setForeground(Color.GRAY);
		txtDateMax.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				dateChoice = 2;
				rdbtnDateMinMax.setSelected(true);
				if (txtDateMaxHelper) {
					txtDateMax.setForeground(Color.BLACK);
					txtDateMax.setText("");
					txtDateMaxHelper = false;
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtDateMax.getText().isEmpty()) {
					txtDateMax.setForeground(Color.GRAY);
					txtDateMax.setText("max");
					txtDateMaxHelper = true;
				}
			}
		});

		txtDateMin = new JTextField();
		txtDateMin.setForeground(Color.GRAY);
		txtDateMin.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				dateChoice = 2;
				rdbtnDateMinMax.setSelected(true);
				if (txtDateMinHelper) {
					txtDateMin.setForeground(Color.BLACK);
					txtDateMin.setText("");
					txtDateMinHelper = false;
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtDateMin.getText().isEmpty()) {
					txtDateMin.setForeground(Color.GRAY);
					txtDateMin.setText("min");
					txtDateMinHelper = true;
				}
			}
		});

		txtDate = new JTextField();
		txtDate.setForeground(Color.GRAY);
		txtDate.setText("is exactly");
		txtDate.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				dateChoice = 1;
				rdbtnDateExactly.setSelected(true);
				if (txtDateHelper) {
					txtDate.setForeground(Color.BLACK);
					txtDate.setText("");
					txtDateHelper = false;
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtDate.getText().isEmpty()) {
					txtDate.setForeground(Color.GRAY);
					txtDate.setText("is exactly");
					txtDateHelper = true;
				}
			}

		});

		txtTotalMax = new JTextField();
		txtTotalMax.setForeground(Color.GRAY);
		txtTotalMax.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				totalAmountChoice = 2;
				rdbtnTotalAmountMinMax.setSelected(true);
				if (txtTotalMaxHelper) {
					txtTotalMax.setForeground(Color.BLACK);
					txtTotalMax.setText("");
					txtTotalMaxHelper = false;
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtTotalMax.getText().isEmpty()) {
					txtTotalMax.setForeground(Color.GRAY);
					txtTotalMax.setText("max");
					txtTotalMaxHelper = true;
				}
			}
		});

		txtTotalMin = new JTextField();
		txtTotalMin.setForeground(Color.GRAY);
		txtTotalMin.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				totalAmountChoice = 2;
				rdbtnTotalAmountMinMax.setSelected(true);
				if (txtTotalMinHelper) {
					txtTotalMin.setForeground(Color.BLACK);
					txtTotalMin.setText("");
					txtTotalMinHelper = false;
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtTotalMin.getText().isEmpty()) {
					txtTotalMin.setForeground(Color.GRAY);
					txtTotalMin.setText("min");
					txtTotalMinHelper = true;
				}
			}
		});

		txtTotalAmount = new JTextField();

		txtTotalAmount.setForeground(Color.GRAY);
		txtTotalAmount.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				totalAmountChoice = 1;
				rdbtnTotalAmountExactly.setSelected(true);
				if (txtTotalAmountHelper) {
					txtTotalAmount.setForeground(Color.BLACK);
					txtTotalAmount.setText("");
					txtTotalAmountHelper = false;
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtTotalAmount.getText().isEmpty()) {
					txtTotalAmount.setForeground(Color.GRAY);
					txtTotalAmount.setText("is exactly");
					txtTotalAmountHelper = true;
				}
			}
		});

		txtReceiptId = new JTextField();
		txtReceiptId.setForeground(Color.GRAY);
		txtReceiptId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				checkIdChoice = true;
				rdbtnReceiptIdExactly.setSelected(true);
				if (txtReceiptIdHelper) {
					txtReceiptId.setForeground(Color.BLACK);
					txtReceiptId.setText("");
					txtReceiptIdHelper = false;
				}
			}

			@Override
			public void focusLost(FocusEvent e) {

				if (txtReceiptId.getText().isEmpty()) {
					txtReceiptId.setForeground(Color.GRAY);
					txtReceiptId.setText("is exactly");
					txtReceiptIdHelper = true;
				}
			}
		});

		JLabel lblNewLabel = new JLabel("Receipt ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(25, 25, 105, 15);
		contentPane.add(lblNewLabel);

		JRadioButton rdbtnReceiptIdAny = new JRadioButton("Any");
		rdbtnReceiptIdAny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkIdChoice = false;
			}
		});
		rdbtnReceiptIdAny.setSelected(true);
		rdbtnReceiptIdAny.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnReceiptIdAny.setBounds(25, 47, 72, 20);
		contentPane.add(rdbtnReceiptIdAny);
		txtReceiptId.setText("is exactly");
		txtReceiptId.setBounds(45, 70, 150, 20);
		contentPane.add(txtReceiptId);
		txtReceiptId.setColumns(10);

		rdbtnReceiptIdExactly.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnReceiptIdExactly.setBounds(25, 70, 20, 20);
		contentPane.add(rdbtnReceiptIdExactly);

		JLabel lblTableNumber = new JLabel("Table Number");
		lblTableNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTableNumber.setBounds(25, 101, 105, 15);
		contentPane.add(lblTableNumber);

		txtTableNumber = new JTextField();
		txtTableNumber.setForeground(Color.GRAY);
		txtTableNumber.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tableNumberChoice = true;
				rdbtnTableNumberExactly.setSelected(true);
				if (txtTableNumberHelper) {
					txtTableNumber.setForeground(Color.BLACK);
					txtTableNumber.setText("");
					txtTableNumberHelper = false;
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtTableNumber.getText().isEmpty()) {
					txtTableNumber.setForeground(Color.GRAY);
					txtTableNumber.setText("is exactly");
					txtTableNumberHelper = true;
				}
			}
		});
		txtTableNumber.setText("is exactly");
		txtTableNumber.setColumns(10);
		txtTableNumber.setBounds(45, 146, 150, 20);
		contentPane.add(txtTableNumber);

		JRadioButton rdbtnTableNumberAny = new JRadioButton("Any");
		rdbtnTableNumberAny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableNumberChoice = false;
			}
		});
		rdbtnTableNumberAny.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnTableNumberAny.setBounds(25, 123, 72, 20);
		contentPane.add(rdbtnTableNumberAny);

		rdbtnTableNumberExactly.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnTableNumberExactly.setBounds(25, 146, 20, 20);
		contentPane.add(rdbtnTableNumberExactly);

		JLabel lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotalAmount.setBounds(25, 177, 105, 15);
		contentPane.add(lblTotalAmount);

		JRadioButton rdbtnTotalAmountAny = new JRadioButton("Any");
		rdbtnTotalAmountAny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				totalAmountChoice = 0;
			}
		});
		rdbtnTotalAmountAny.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnTotalAmountAny.setBounds(25, 199, 72, 20);
		contentPane.add(rdbtnTotalAmountAny);
		txtTotalAmount.setText("is exactly");
		txtTotalAmount.setColumns(10);
		txtTotalAmount.setBounds(45, 222, 150, 20);
		contentPane.add(txtTotalAmount);

		rdbtnTotalAmountExactly.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnTotalAmountExactly.setBounds(25, 222, 20, 20);
		contentPane.add(rdbtnTotalAmountExactly);
		txtTotalMin.setText("min");
		txtTotalMin.setColumns(10);
		txtTotalMin.setBounds(45, 245, 150, 20);
		contentPane.add(txtTotalMin);

		rdbtnTotalAmountMinMax.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnTotalAmountMinMax.setBounds(25, 245, 20, 20);
		contentPane.add(rdbtnTotalAmountMinMax);
		txtTotalMax.setText("max");
		txtTotalMax.setColumns(10);
		txtTotalMax.setBounds(45, 268, 150, 20);
		contentPane.add(txtTotalMax);

		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDate.setBounds(25, 299, 105, 15);
		contentPane.add(lblDate);

		JRadioButton rdbtnDateAny = new JRadioButton("Any");
		rdbtnDateAny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateChoice = 0;
			}
		});
		rdbtnDateAny.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnDateAny.setBounds(25, 321, 72, 20);
		contentPane.add(rdbtnDateAny);
		txtDate.setColumns(10);
		txtDate.setBounds(45, 344, 150, 20);
		contentPane.add(txtDate);

		rdbtnDateExactly.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnDateExactly.setBounds(25, 344, 20, 20);
		contentPane.add(rdbtnDateExactly);
		txtDateMin.setText("min");
		txtDateMin.setColumns(10);
		txtDateMin.setBounds(45, 367, 150, 20);
		contentPane.add(txtDateMin);

		rdbtnDateMinMax.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnDateMinMax.setBounds(25, 367, 20, 20);
		contentPane.add(rdbtnDateMinMax);
		txtDateMax.setText("max");
		txtDateMax.setColumns(10);
		txtDateMax.setBounds(45, 390, 150, 20);
		contentPane.add(txtDateMax);

		JLabel lblPaid = new JLabel("Paid");
		lblPaid.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPaid.setBounds(25, 421, 105, 15);
		contentPane.add(lblPaid);

		JRadioButton rdbtnPaidAny = new JRadioButton("Any");
		rdbtnPaidAny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paidChoice = false;
			}
		});
		rdbtnPaidAny.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnPaidAny.setBounds(25, 443, 72, 20);
		contentPane.add(rdbtnPaidAny);

		JRadioButton rdbtnPaidPaid = new JRadioButton("Paid");
		rdbtnPaidPaid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paidChoice = true;
				paid = true;
			}
		});
		rdbtnPaidPaid.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnPaidPaid.setBounds(25, 466, 72, 20);
		contentPane.add(rdbtnPaidPaid);

		JRadioButton rdbtnPaidUnpaid = new JRadioButton("Unpaid");
		rdbtnPaidUnpaid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paidChoice = true;
				paid = false;
			}
		});
		rdbtnPaidUnpaid.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnPaidUnpaid.setBounds(25, 489, 72, 20);
		contentPane.add(rdbtnPaidUnpaid);

		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (checkIdChoice) {
					checkID = Integer.valueOf(txtReceiptId.getText());
				}
				if (tableNumberChoice) {
					tableNumber = Integer.valueOf(txtTableNumber.getText());
				}
				if (totalAmountChoice == 1) {
					totalAmount = Float.valueOf(txtTotalAmount.getText());
				} else if (totalAmountChoice == 2) {
					if (txtTotalMinHelper && !txtTotalMaxHelper) { // totalmin unspecified
						totalAmountMin = 0;
						totalAmountMax = Float.valueOf(txtTotalMax.getText());
					} else if (!txtTotalMinHelper && txtTotalMaxHelper) { // totalmax unspecified
						totalAmountMin = Float.valueOf(txtTotalMin.getText());
						totalAmountMax = Float.MAX_VALUE;
					} else { // both specified
						totalAmountMin = Float.valueOf(txtTotalMin.getText());
						totalAmountMax = Float.valueOf(txtTotalMax.getText());
					}
				}
				if (dateChoice == 1) {
					date = Timestamp.valueOf(txtDate.getText());
				} else if (dateChoice == 2) {

					if (txtDateMinHelper && !txtDateMaxHelper) { // datemin unspecified
						dateMin = Timestamp.valueOf("1970-01-01 00:00:01");
						dateMax = Timestamp.valueOf(txtDateMax.getText());

					} else if (!txtDateMinHelper && txtDateMaxHelper) { // datemax unspecified
						dateMin = Timestamp.valueOf(txtDateMin.getText());
						dateMax = Timestamp.valueOf("2038-01-19 03:14:07");

					} else { // both specified
						dateMin = Timestamp.valueOf(txtDateMin.getText());
						dateMax = Timestamp.valueOf(txtDateMax.getText());
					}
				}

//				receipt.getReceipts(checkIdChoice, tableNumberChoice, totalAmountChoice, dateChoice, paidChoice,
//						checkID, tableNumber, totalAmountChoice, date, paid, totalAmountMin, totalAmountMax, dateMin,
//						dateMax);

				DefaultTableModel data = new DefaultTableModel(receipt.getReceipts(checkIdChoice, tableNumberChoice,
						totalAmountChoice, dateChoice, paidChoice, checkID, tableNumber, totalAmount, date, paid,
						totalAmountMin, totalAmountMax, dateMin, dateMax), receiptColumns);
				saveQueryParams();

				table.setModel(data);
				// format the table
				formatTable();
				table.setFillsViewportHeight(true); // fills in empty rows

			}
		});
		btnNewButton.setBackground(SystemColor.controlHighlight);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(25, 516, 180, 40);
		contentPane.add(btnNewButton);

		JButton btnUpdateReceipt = new JButton("Return to Menu");
		btnUpdateReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnUpdateReceipt.setBackground(SystemColor.controlHighlight);
		btnUpdateReceipt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdateReceipt.setBounds(405, 516, 180, 40);
		contentPane.add(btnUpdateReceipt);

		JButton btnRemoveReceipt = new JButton("Remove Receipt");
		btnRemoveReceipt.setBackground(SystemColor.controlHighlight);
		btnRemoveReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int col = 0;
				int row = table.getSelectedRow();
				String value = table.getModel().getValueAt(row, col).toString();
				receipt.removeReceipt(Integer.valueOf(value));

				DefaultTableModel data = new DefaultTableModel(
						receipt.getReceipts(prevCheckIdChoice, prevTableNumberChoice, prevTotalAmountChoice,
								prevDateChoice, prevPaidChoice, prevCheckID, prevTableNumber, prevTotalAmountChoice,
								prevDate, prevPaid, prevTotalAmountMin, prevTotalAmountMax, prevDateMin, prevDateMax),
						receiptColumns);

				table.setModel(data);
				formatTable();

			}
		});
		btnRemoveReceipt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRemoveReceipt.setBounds(215, 567, 180, 40);
		contentPane.add(btnRemoveReceipt);

		JButton btnTableMenu = new JButton("Return to Tables");
		btnTableMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TableView tv = new TableView();
				tv.setVisible(true);
			}
		});
		btnTableMenu.setBackground(SystemColor.controlHighlight);
		btnTableMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTableMenu.setBounds(405, 567, 180, 40);
		contentPane.add(btnTableMenu);

		JButton btnNewReceipt = new JButton("Update Receipt");
		btnNewReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				int col = 0;
				int row = table.getSelectedRow();
				String value = table.getModel().getValueAt(row, col).toString();
				System.out.println(value);
				ReceiptPage2 rp = new ReceiptPage2(Integer.valueOf(value));
				rp.setVisible(true);
			}
		});
		btnNewReceipt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewReceipt.setBackground(SystemColor.controlHighlight);
		btnNewReceipt.setBounds(215, 516, 180, 40);
		contentPane.add(btnNewReceipt);

		// Radial Groups
		ButtonGroup groupReceiptId = new ButtonGroup();
		groupReceiptId.add(rdbtnReceiptIdAny);
		groupReceiptId.add(rdbtnReceiptIdExactly);

		ButtonGroup groupTableNumber = new ButtonGroup();
		groupTableNumber.add(rdbtnTableNumberAny);
		groupTableNumber.add(rdbtnTableNumberExactly);
		rdbtnTableNumberAny.setSelected(true);

		ButtonGroup groupTotalAmount = new ButtonGroup();
		groupTotalAmount.add(rdbtnTotalAmountAny);
		groupTotalAmount.add(rdbtnTotalAmountExactly);
		groupTotalAmount.add(rdbtnTotalAmountMinMax);
		rdbtnTotalAmountAny.setSelected(true);

		ButtonGroup groupDate = new ButtonGroup();
		groupDate.add(rdbtnDateAny);
		groupDate.add(rdbtnDateExactly);
		groupDate.add(rdbtnDateMinMax);
		rdbtnDateAny.setSelected(true);

		ButtonGroup groupPaid = new ButtonGroup();
		groupPaid.add(rdbtnPaidAny);
		groupPaid.add(rdbtnPaidPaid);
		groupPaid.add(rdbtnPaidUnpaid);
		rdbtnPaidAny.setSelected(true);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(205, 25, 380, 484);
		contentPane.add(scrollPane);

		table = new JTable(receiptData, receiptColumns);
		formatTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true); // fills in empty rows

		table.setDefaultEditor(Object.class, null); // makes cells uneditable
		scrollPane.setViewportView(table);

		JButton btnEditOrders = new JButton("Edit Orders");
		btnEditOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int col = 0;
				int row = table.getSelectedRow();
				
				if(row == -1)
				{
					JOptionPane.showMessageDialog(null, "Please select a receipt first.");
				}
				else {
					dispose();
					String value = table.getModel().getValueAt(row, col).toString();
					System.out.println(value);
					OrderView ov = new OrderView(Integer.valueOf(value));
					ov.setVisible(true);
					
				}
				
				
				
				
				
				
				
			}
		});
		btnEditOrders.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEditOrders.setBackground(SystemColor.controlHighlight);
		btnEditOrders.setBounds(25, 567, 180, 40);
		contentPane.add(btnEditOrders);

	}
}
