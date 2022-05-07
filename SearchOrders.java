
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Dialog.ModalityType;
import java.awt.Color;

public class SearchOrders extends JDialog {
	
	private static final long serialVersionUID = 6075;
	private JPanel contentPane;
	private JTextField txtOrderId;
	private JTextField txtModification;
	private JTextField txtCheckId;
	private JTextField txtFoodId;
	private JTable table;

	//Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchOrders frame = new SearchOrders();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Ghost text booleans
	boolean txtOrderIdHelper = true;
	boolean txtFoodIdHelper = true;
	boolean txtCheckIdHelper = true;
	boolean txtModificationHelper = true;
	
	boolean orderIdChoice = false;
	boolean foodIdChoice = false;
	boolean checkIdChoice = false;
	boolean modificationChoice = false;
	
	int orderId;
	int foodId;
	int checkId;
	String modification;

	//Establish previous so can refresh with same query when deleting
	boolean prevOrderIdChoice = false;
	boolean prevFoodIdChoice = false;
	boolean prevCheckIdChoice = false;
	boolean prevModificationChoice = false;
	
	int prevOrderId;
	int prevFoodId;
	int prevCheckId;
	String prevModification;

	private void saveQueryParams() {
		prevOrderIdChoice = orderIdChoice;
		prevFoodIdChoice = foodIdChoice;
		prevCheckIdChoice = checkIdChoice;
		prevModificationChoice = modificationChoice;
		prevOrderId = orderId;
		prevFoodId = foodId;
		prevCheckId = checkId;
		prevModification = modification;
	}

	private void formatTable() {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(160);
		table.getColumnModel().getColumn(4).setPreferredWidth(160);
		table.getColumnModel().getColumn(5).setPreferredWidth(70);
	}

	//Create the frame.
	public SearchOrders() throws Exception {
		setModalityType(ModalityType.APPLICATION_MODAL);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JRadioButton rdbtnOrderIdExactly = new JRadioButton("");
		rdbtnOrderIdExactly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderIdChoice = true;
			}
		});
		JRadioButton rdbtnFoodIdExactly = new JRadioButton("");
		rdbtnFoodIdExactly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				foodIdChoice = true;
			}
		});
		JRadioButton rdbtnCheckIdExactly = new JRadioButton("");
		rdbtnCheckIdExactly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkIdChoice = true;
			}
		});
		JRadioButton rdbtnModificationExactly = new JRadioButton("");
		rdbtnModificationExactly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificationChoice = true;
			}
		});

		String orderColumns[] = { "Order ID", "Food ID", "Receipt ID", "Name", "Modification", "Price ($)"};
		
		String orderData[][] = Order.getOrdersView();
		
		
		

		txtModification = new JTextField();
		txtModification.setForeground(Color.GRAY);
		txtModification.setText("is exactly");
		txtModification.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				modificationChoice = true;
				rdbtnModificationExactly.setSelected(true);
				if (txtModificationHelper) {
					txtModification.setForeground(Color.BLACK);
					txtModification.setText("");
					txtModificationHelper = false;
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtModification.getText().isEmpty()) {
					txtModification.setForeground(Color.GRAY);
					txtModification.setText("is exactly");
					txtModificationHelper = true;
				}
			}

		});

		txtCheckId = new JTextField();

		txtCheckId.setForeground(Color.GRAY);
		txtCheckId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				checkIdChoice = true;
				rdbtnCheckIdExactly.setSelected(true);
				if (txtCheckIdHelper) {
					txtCheckId.setForeground(Color.BLACK);
					txtCheckId.setText("");
					txtCheckIdHelper = false;
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtCheckId.getText().isEmpty()) {
					txtCheckId.setForeground(Color.GRAY);
					txtCheckId.setText("is exactly");
					txtCheckIdHelper = true;
				}
			}
		});

		txtOrderId = new JTextField();
		txtOrderId.setForeground(Color.GRAY);
		txtOrderId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				orderIdChoice = true;
				rdbtnOrderIdExactly.setSelected(true);
				if (txtOrderIdHelper) {
					txtOrderId.setForeground(Color.BLACK);
					txtOrderId.setText("");
					txtOrderIdHelper = false;
				}
			}

			@Override
			public void focusLost(FocusEvent e) {

				if (txtOrderId.getText().isEmpty()) {
					txtOrderId.setForeground(Color.GRAY);
					txtOrderId.setText("is exactly");
					txtOrderIdHelper = true;
				}
			}
		});
		
		txtFoodId = new JTextField();
		txtFoodId.setForeground(Color.GRAY);
		txtFoodId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				foodIdChoice = true;
				rdbtnFoodIdExactly.setSelected(true);
				if (txtFoodIdHelper) {
					txtFoodId.setForeground(Color.BLACK);
					txtFoodId.setText("");
					txtFoodIdHelper = false;
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtFoodId.getText().isEmpty()) {
					txtFoodId.setForeground(Color.GRAY);
					txtFoodId.setText("is exactly");
					txtFoodIdHelper = true;
				}
			}
		});
		txtFoodId.setText("is exactly");
		txtFoodId.setColumns(10);
		txtFoodId.setBounds(45, 146, 150, 20);
		contentPane.add(txtFoodId);

		JLabel lblOrderId = new JLabel("Order ID");
		lblOrderId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblOrderId.setBounds(25, 25, 105, 15);
		contentPane.add(lblOrderId);

		JRadioButton rdbtnOrderIdAny = new JRadioButton("Any");
		rdbtnOrderIdAny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderIdChoice = false;
			}
		});
		rdbtnOrderIdAny.setSelected(true);
		rdbtnOrderIdAny.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnOrderIdAny.setBounds(25, 47, 72, 20);
		contentPane.add(rdbtnOrderIdAny);
		txtOrderId.setText("is exactly");
		txtOrderId.setBounds(45, 70, 150, 20);
		contentPane.add(txtOrderId);
		txtOrderId.setColumns(10);

		rdbtnOrderIdExactly.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnOrderIdExactly.setBounds(25, 70, 20, 20);
		contentPane.add(rdbtnOrderIdExactly);

		JLabel lblFoodId = new JLabel("Food ID");
		lblFoodId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFoodId.setBounds(25, 101, 105, 15);
		contentPane.add(lblFoodId);

		JRadioButton rdbtnFoodIdAny = new JRadioButton("Any");
		rdbtnFoodIdAny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				foodIdChoice = false;
			}
		});
		rdbtnFoodIdAny.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnFoodIdAny.setBounds(25, 123, 72, 20);
		contentPane.add(rdbtnFoodIdAny);

		rdbtnFoodIdExactly.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnFoodIdExactly.setBounds(25, 146, 20, 20);
		contentPane.add(rdbtnFoodIdExactly);

		JLabel lblCheckId = new JLabel("Receipt ID");
		lblCheckId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCheckId.setBounds(25, 177, 105, 15);
		contentPane.add(lblCheckId);

		JRadioButton rdbtnReceiptIdAny = new JRadioButton("Any");
		rdbtnReceiptIdAny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkIdChoice = false;
			}
		});
		rdbtnReceiptIdAny.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnReceiptIdAny.setBounds(25, 199, 72, 20);
		contentPane.add(rdbtnReceiptIdAny);
		txtCheckId.setText("is exactly");
		txtCheckId.setColumns(10);
		txtCheckId.setBounds(45, 222, 150, 20);
		contentPane.add(txtCheckId);

		rdbtnCheckIdExactly.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnCheckIdExactly.setBounds(25, 222, 20, 20);
		contentPane.add(rdbtnCheckIdExactly);

		JLabel lblModification = new JLabel("Modification");
		lblModification.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblModification.setBounds(25, 254, 105, 15);
		contentPane.add(lblModification);

		JRadioButton rdbtnModificationAny = new JRadioButton("Any");
		rdbtnModificationAny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificationChoice = false;
			}
		});
		rdbtnModificationAny.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnModificationAny.setBounds(25, 276, 72, 20);
		contentPane.add(rdbtnModificationAny);
		txtModification.setColumns(10);
		txtModification.setBounds(45, 299, 150, 20);
		contentPane.add(txtModification);

		rdbtnModificationExactly.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnModificationExactly.setBounds(25, 299, 20, 20);
		contentPane.add(rdbtnModificationExactly);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (orderIdChoice) {
					orderId = Integer.valueOf(txtOrderId.getText());
				}
				if (foodIdChoice) {
					foodId = Integer.valueOf(txtFoodId.getText());
				}
				if (checkIdChoice) {
					checkId = Integer.valueOf(txtCheckId.getText());
				}
				if (modificationChoice) {
					modification = txtModification.getText();
				}

				DefaultTableModel data = null;
				try {
					data = new DefaultTableModel(Order.getOrdersView(), orderColumns);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				saveQueryParams();

				table.setModel(data);
				//Format the table
				formatTable();
				table.setFillsViewportHeight(true); // fills in empty rows

			}
		});
		btnSearch.setBackground(SystemColor.controlHighlight);
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearch.setBounds(25, 516, 170, 40);
		contentPane.add(btnSearch);

		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnReturn.setBackground(SystemColor.controlHighlight);
		btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnReturn.setBounds(618, 516, 170, 40);
		contentPane.add(btnReturn);

		JButton btnRemove = new JButton("Remove Order");
		btnRemove.setBackground(SystemColor.controlHighlight);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Order.removeOrder(Integer.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0).toString()));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				DefaultTableModel data = null;
				try {
					data = new DefaultTableModel(Order.getOrdersView(), orderColumns);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				table.setModel(data);
				
				formatTable();

			}
		});
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRemove.setBounds(423, 516, 170, 40);
		contentPane.add(btnRemove);

		// Radial Groups
		ButtonGroup groupOrderId = new ButtonGroup();
		groupOrderId.add(rdbtnOrderIdAny);
		groupOrderId.add(rdbtnOrderIdExactly);

		ButtonGroup groupFoodId = new ButtonGroup();
		groupFoodId.add(rdbtnFoodIdAny);
		groupFoodId.add(rdbtnFoodIdExactly);
		rdbtnFoodIdAny.setSelected(true);

		ButtonGroup groupReceiptId = new ButtonGroup();
		groupReceiptId.add(rdbtnReceiptIdAny);
		groupReceiptId.add(rdbtnCheckIdExactly);
		rdbtnReceiptIdAny.setSelected(true);

		ButtonGroup groupModification = new ButtonGroup();
		groupModification.add(rdbtnModificationAny);
		groupModification.add(rdbtnModificationExactly);
		rdbtnModificationAny.setSelected(true);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(205, 25, 583, 484);
		contentPane.add(scrollPane);

		table = new JTable(orderData, orderColumns);
		formatTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true); // fills in empty rows

		table.setDefaultEditor(Object.class, null); // makes cells uneditable
		scrollPane.setViewportView(table);

		JButton btnEdit = new JButton("Edit Orders");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEdit.setBackground(SystemColor.controlHighlight);
		btnEdit.setBounds(225, 516, 170, 40);
		contentPane.add(btnEdit);

	}
}
