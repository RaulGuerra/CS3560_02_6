//package posSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrderView extends JDialog {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderView frame = new OrderView(8);
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
	public OrderView(int receiptNumber) {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 443, 292);
		setResizable(false);
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		Object[][] empty = {};
		String[] columnNames = {"ID", "Name","Modification","Price"};
		
		DefaultTableModel model = new DefaultTableModel(empty, columnNames);
		table = new JTable(model);
		model.setRowCount(0);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane();
		JLabel lblNewLabel = new JLabel("Orders for Receipt #" + receiptNumber);
		
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 19));
		
		TableModel m = table.getModel();
		table.clearSelection();
		
		try {
			ArrayList<Order> orders = Order.getOrdersFromReceipt(receiptNumber);
			
			for(int i=0;i<orders.size();++i) {
				Order o = orders.get(i);
				Food f = Food.getFood(o.getFoodID());
				
				model.addRow(new Object[]{o.getOrderID(),f.getName(), o.getMod(), df.format(f.getPrice())});
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(10);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(20);

		if(table.getRowCount() >= 1) {
			table.changeSelection(0, 0, false, false);
		}
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = table.getSelectedRow();
				
				if(row == -1) return;
				
				int id = (int)table.getModel().getValueAt(row, 0);

				model.removeRow(row);

				try {	        		
					Order.removeOrder(id);
					
					if(table.getRowCount() >= 1) {
						table.changeSelection(0, 0, false, false);
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		setLocationRelativeTo(null);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = table.getSelectedRow();
				if(row == -1) return;
				
		        String yo = JOptionPane.showInputDialog(null, "Enter modification", "Modification Window", JOptionPane.PLAIN_MESSAGE);

		        if(yo != null)
		        {
		        	table.getModel().setValueAt(yo, row, 2);
		        	int id = (int)table.getModel().getValueAt(row, 0);
		        	
		        	try {	        		
						Order.updateOrder(id,yo);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
		        }
			}
		});
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1_1 = new JButton("Back");
		
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				try {
					
					ReceiptPage2 rp2 = new ReceiptPage2(receiptNumber);
					rp2.setVisible(true);
					//SearchOrders so = new SearchOrders();
					//so.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});
		
		btnNewButton_1_1.setActionCommand("Cancel");
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FoodMenu menu = new FoodMenu(receiptNumber);				
				menu.setVisible(true);

				menu.addWindowListener(new WindowAdapter()
		        {
		            @Override
		            public void windowClosed(WindowEvent e)
		            {
		                try {	      
		                	Order lo = Order.getLatestOrder();
			                Food fo = Food.getFood(lo.getFoodID());	
			                
			                if (model.getRowCount() > 0 && lo.getOrderID() == (int)model.getValueAt(model.getRowCount()-1, 0)) {
			                	return;
			                }
			                
			                model.addRow(new Object[]{lo.getOrderID(),fo.getName(),lo.getMod(), df.format(fo.getPrice())});
			                
						} catch (Exception e1) {
							e1.printStackTrace();
						}               
		            }
		        });
			}
		});
		
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(0, 0, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 428, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)))
					.addGap(397))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(124)
					.addComponent(lblNewLabel)
					.addContainerGap(522, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
								.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}
}
