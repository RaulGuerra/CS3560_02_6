package posSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class OrderView extends JFrame {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderView frame = new OrderView();
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
	public OrderView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 443, 292);
		
		Object[][] empty = {};
		String[] columnNames = {"ID", "Name","Modification","Price"};
		
		DefaultTableModel model = new DefaultTableModel(empty, columnNames);
		table = new JTable(model);
		model.setRowCount(0);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.setDefaultEditor(Object.class, null);
		
		int tablenumber = 1;
		JScrollPane scrollPane = new JScrollPane();
		JLabel lblNewLabel = new JLabel("Orders for Table #" + tablenumber);
		
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 19));
		
		TableModel m = table.getModel();
		table.clearSelection();

		try {
			ArrayList<Order> orders = Order.getOrders();
			
			for(int i=0;i<orders.size();++i) {
				Order o = orders.get(i);
				Food f = Food.getFood(o.getFoodID());
				
				model.addRow(new Object[]{o.getOrderID(),f.getName(), o.getMod(), f.getPrice()});
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(10);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(20);

		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = table.getSelectedRow();
				int id = (int)table.getModel().getValueAt(row, 0);

				model.removeRow(row);

				try {	        		
					Order.removeOrder(id);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		table.changeSelection(0, 0, false, false);
		setLocationRelativeTo(null);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = table.getSelectedRow();
				
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
							.addGap(90)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(36)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)))
					.addGap(397))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(124)
					.addComponent(lblNewLabel)
					.addContainerGap(538, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
					.addGap(20))
		);
		getContentPane().setLayout(groupLayout);
	}
}
