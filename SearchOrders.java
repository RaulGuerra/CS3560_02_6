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
	private JTextField txtName;
	private JTextField txtCheckId;
	private JTextField txtFoodId;
	private JTextField txtModification;
	private JTextField txtPrice;
	private JTable table;
	
	private static SearchOrders frame;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("Launched");
					frame = new SearchOrders();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Initial variable values
	boolean txtOrderIdHelper = true;
	boolean txtFoodIdHelper = true;
	boolean txtCheckIdHelper = true;
	boolean txtNameHelper = true;
	boolean txtModificationHelper = true;
	boolean txtPriceHelper = true;
	
	boolean orderIdChoice = false;
	boolean foodIdChoice = false;
	boolean checkIdChoice = false;
	boolean nameChoice = false;
	boolean modificationChoice = false;
	boolean priceChoice = false;
	
	int orderId;
	int foodId;
	int checkId;
	String name;
	String modification;
	float price;

	//Establish previous so can refresh with same query when deleting
	boolean prevOrderIdChoice = false;
	boolean prevFoodIdChoice = false;
	boolean prevCheckIdChoice = false;
	boolean prevNameChoice = false;
	boolean prevModificationChoice = false;
	boolean prevPriceChoice = false;
	
	int prevOrderId;
	int prevFoodId;
	int prevCheckId;
	String prevName;
	String prevModification;
	float prevPrice;

	private void saveQueryParams() {
		prevOrderIdChoice = orderIdChoice;
		prevFoodIdChoice = foodIdChoice;
		prevCheckIdChoice = checkIdChoice;
		prevNameChoice = nameChoice;
		prevModificationChoice = modificationChoice;
		prevPriceChoice = priceChoice;
		prevOrderId = orderId;
		prevFoodId = foodId;
		prevCheckId = checkId;
		prevName = name;
		prevModification = modification;
		prevPrice = price;
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
		setTitle("Search Orders");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		String orderColumns[] = { "Order ID", "Food ID", "Receipt ID", "Name", "Modification", "Price ($)"};
		
		String orderData[][] = Order.getOrdersView();
		
		//Radio Buttons
		JRadioButton rdbtnOrderIdExactly = new JRadioButton("");
		rdbtnOrderIdExactly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderIdChoice = true;
			}
		});
		rdbtnOrderIdExactly.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnOrderIdExactly.setBounds(25, 70, 20, 20);
		contentPane.add(rdbtnOrderIdExactly);
				
		JRadioButton rdbtnFoodIdExactly = new JRadioButton("");
		rdbtnFoodIdExactly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				foodIdChoice = true;
			}
		});
		rdbtnFoodIdExactly.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnFoodIdExactly.setBounds(25, 146, 20, 20);
		contentPane.add(rdbtnFoodIdExactly);
				
		JRadioButton rdbtnCheckIdExactly = new JRadioButton("");
		rdbtnCheckIdExactly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkIdChoice = true;
			}
		});
		rdbtnCheckIdExactly.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnCheckIdExactly.setBounds(25, 222, 20, 20);
		contentPane.add(rdbtnCheckIdExactly);
				
		JRadioButton rdbtnNameExactly = new JRadioButton("");
		rdbtnNameExactly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameChoice = true;
			}
		});
		rdbtnNameExactly.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnNameExactly.setBounds(25, 299, 20, 20);
		contentPane.add(rdbtnNameExactly);
				
		JRadioButton rdbtnModificationExactly = new JRadioButton("");
		rdbtnModificationExactly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificationChoice = true;
			}
		});
		rdbtnModificationExactly.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnModificationExactly.setBounds(25, 376, 20, 20);
		contentPane.add(rdbtnModificationExactly);
				
		JRadioButton rdbtnPriceExactly = new JRadioButton("");
		rdbtnPriceExactly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				priceChoice = true;
			}
		});
		rdbtnPriceExactly.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnPriceExactly.setBounds(25, 453, 20, 20);
		contentPane.add(rdbtnPriceExactly);

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

		JRadioButton rdbtnFoodIdAny = new JRadioButton("Any");
		rdbtnFoodIdAny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				foodIdChoice = false;
			}
		});
		rdbtnFoodIdAny.setSelected(true);
		rdbtnFoodIdAny.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnFoodIdAny.setBounds(25, 123, 72, 20);
		contentPane.add(rdbtnFoodIdAny);

		JRadioButton rdbtnReceiptIdAny = new JRadioButton("Any");
		rdbtnReceiptIdAny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkIdChoice = false;
			}
		});
		rdbtnReceiptIdAny.setSelected(true);
		rdbtnReceiptIdAny.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnReceiptIdAny.setBounds(25, 199, 72, 20);
		contentPane.add(rdbtnReceiptIdAny);

		JRadioButton rdbtnNameAny = new JRadioButton("Any");
		rdbtnNameAny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameChoice = false;
			}
		});
		rdbtnNameAny.setSelected(true);
		rdbtnNameAny.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnNameAny.setBounds(25, 276, 72, 20);
		contentPane.add(rdbtnNameAny);
				
		JRadioButton rdbtnModificationAny = new JRadioButton("Any");
		rdbtnModificationAny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificationChoice = false;
			}
		});
		rdbtnModificationAny.setSelected(true);
		rdbtnModificationAny.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnModificationAny.setBounds(25, 353, 72, 20);
		contentPane.add(rdbtnModificationAny);

		JRadioButton rdbtnPriceAny = new JRadioButton("Any");
		rdbtnPriceAny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				priceChoice = false;
			}
		});
		rdbtnPriceAny.setSelected(true);
		rdbtnPriceAny.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnPriceAny.setBounds(25, 430, 72, 20);
		contentPane.add(rdbtnPriceAny);
		
		//Text Fields
		txtName = new JTextField();
		txtName.setForeground(Color.GRAY);
		txtName.setText("is exactly");
		txtName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				nameChoice = true;
				rdbtnNameExactly.setSelected(true);
				if (txtNameHelper) {
					txtName.setForeground(Color.BLACK);
					txtName.setText("");
					txtNameHelper = false;
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtName.getText().isEmpty()) {
					txtName.setForeground(Color.GRAY);
					txtName.setText("is exactly");
					txtNameHelper = true;
				}
			}

		});
		txtName.setText("is exactly");
		txtName.setColumns(10);
		txtName.setBounds(45, 299, 150, 20);
		contentPane.add(txtName);

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
		txtCheckId.setText("is exactly");
		txtCheckId.setColumns(10);
		txtCheckId.setBounds(45, 222, 150, 20);
		contentPane.add(txtCheckId);

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
		txtOrderId.setText("is exactly");
		txtOrderId.setColumns(10);
		txtOrderId.setBounds(45, 70, 150, 20);
		contentPane.add(txtOrderId);
		
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
		
		txtModification = new JTextField();
		txtModification.setForeground(Color.GRAY);
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
		txtModification.setText("is exactly");
		txtModification.setColumns(10);
		txtModification.setBounds(45, 376, 150, 20);
		contentPane.add(txtModification);
		
		txtPrice = new JTextField();
		txtPrice.setForeground(Color.GRAY);
		txtPrice.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				priceChoice = true;
				rdbtnPriceExactly.setSelected(true);
				if (txtPriceHelper) {
					txtPrice.setForeground(Color.BLACK);
					txtPrice.setText("");
					txtPriceHelper = false;
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtPrice.getText().isEmpty()) {
					txtPrice.setForeground(Color.GRAY);
					txtPrice.setText("is exactly");
					txtPriceHelper = true;
				}
			}
		});
		txtPrice.setText("is exactly");
		txtPrice.setColumns(10);
		txtPrice.setBounds(45, 453, 150, 20);
		contentPane.add(txtPrice);
		
		//Buttons
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
				if (nameChoice) {
					name = txtName.getText();
				}
				if (modificationChoice) {
					modification = txtModification.getText();
				}
				if (priceChoice) {
					price = Float.valueOf(txtPrice.getText());
				}

				DefaultTableModel data = null;
				try {
					data = new DefaultTableModel(Order.searchOrdersView(orderIdChoice, foodIdChoice, checkIdChoice, nameChoice, modificationChoice, priceChoice, orderId, foodId, checkId, name, modification, price), orderColumns);
				} catch (Exception e1) {
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
					e1.printStackTrace();
				}

				DefaultTableModel data = null;
				try {
					data = new DefaultTableModel(Order.searchOrdersView(prevOrderIdChoice, prevFoodIdChoice, prevCheckIdChoice, prevNameChoice, prevModificationChoice, prevPriceChoice, prevOrderId, prevFoodId, prevCheckId, prevName, prevModification, prevPrice), orderColumns);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				table.setModel(data);
				
				formatTable();

			}
		});
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRemove.setBounds(423, 516, 170, 40);
		contentPane.add(btnRemove);

		JButton btnEdit = new JButton("Edit Orders");
		btnEdit.setBackground(SystemColor.controlHighlight);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					OrderView ov = new OrderView(Integer.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 2).toString()));
					ov.setVisible(true);
				}
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEdit.setBounds(225, 516, 170, 40);
		contentPane.add(btnEdit);
		
		// Radial Groups
		ButtonGroup groupOrderId = new ButtonGroup();
		groupOrderId.add(rdbtnOrderIdAny);
		groupOrderId.add(rdbtnOrderIdExactly);
		rdbtnOrderIdAny.setSelected(true);

		ButtonGroup groupFoodId = new ButtonGroup();
		groupFoodId.add(rdbtnFoodIdAny);
		groupFoodId.add(rdbtnFoodIdExactly);
		rdbtnFoodIdAny.setSelected(true);

		ButtonGroup groupReceiptId = new ButtonGroup();
		groupReceiptId.add(rdbtnReceiptIdAny);
		groupReceiptId.add(rdbtnCheckIdExactly);
		rdbtnReceiptIdAny.setSelected(true);
				
		ButtonGroup groupName = new ButtonGroup();
		groupName.add(rdbtnNameAny);
		groupName.add(rdbtnNameExactly);
		rdbtnNameAny.setSelected(true);

		ButtonGroup groupModification = new ButtonGroup();
		groupModification.add(rdbtnModificationAny);
		groupModification.add(rdbtnModificationExactly);
		rdbtnModificationAny.setSelected(true);
				
		ButtonGroup groupPrice = new ButtonGroup();
		groupPrice.add(rdbtnPriceAny);
		groupPrice.add(rdbtnPriceExactly);
		rdbtnPriceAny.setSelected(true);
		
		//Labels
		JLabel lblOrderId = new JLabel("Order ID");
		lblOrderId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblOrderId.setBounds(25, 25, 105, 15);
		contentPane.add(lblOrderId);
		
		JLabel lblFoodId = new JLabel("Food ID");
		lblFoodId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFoodId.setBounds(25, 101, 105, 15);
		contentPane.add(lblFoodId);
		
		JLabel lblCheckId = new JLabel("Receipt ID");
		lblCheckId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCheckId.setBounds(25, 177, 105, 15);
		contentPane.add(lblCheckId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblName.setBounds(25, 254, 105, 15);
		contentPane.add(lblName);
		
		JLabel lblModification = new JLabel("Modification");
		lblModification.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblModification.setBounds(25, 331, 105, 15);
		contentPane.add(lblModification);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrice.setBounds(25, 408, 105, 15);
		contentPane.add(lblPrice);
		
		//Table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(205, 25, 583, 484);
		contentPane.add(scrollPane);

		table = new JTable(orderData, orderColumns);
		formatTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true); // fills in empty rows

		table.setDefaultEditor(Object.class, null); // makes cells un-editable
		scrollPane.setViewportView(table);
	}
}
