/**
 * Name: FoodDialog.java
 * 
 * GUI application to provide a dialog to handle CRUD functions related to food menu.
 */

import java.awt.BorderLayout; 
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class FoodDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String[] foodNames;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FoodDialog dialog = new FoodDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws Exception 
	 */
	public FoodDialog() throws Exception {
		Food food = new Food();
		String name;
		setModal(true);
		setBounds(100, 100, 713, 433);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		
		//Gets food names to be shown through JList 
		foodNames = food.getAllFoodNames();
		//Use DefaultListModel to update the JList in when elements are added/updated/removed
		DefaultListModel listModel = new DefaultListModel();
		for(int i = 0; i < foodNames.length; i++)
		{
			listModel.addElement(foodNames[i]);
		}
		JList list = new JList(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
			}	
		});
		
		//add a food item
		JButton btnNewButton = new JButton("Add Item");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = (String) food.addFoodItem();
					//add name of new item to listModel to update in JList
					listModel.addElement(name);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//Update food item button and run method when pressed
		JButton btnNewButton_1 = new JButton("Update Item");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//get ID to edit this food item
					String name = (String) list.getSelectedValue();
					int id = food.getFoodID(name);
					String temp = food.updateFood(id);
					
					//If the user updates the name of the item, find the index of the item its updating and change the name
					//in the listModel to reflect in the Jlist
					int index = listModel.indexOf(name);
					listModel.set(index, temp);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//View all related details in a dialog of a particular food item
		JButton btnNewButton_2 = new JButton("View Details");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//get ID to edit this food item
					String name = (String) list.getSelectedValue();
					int id = food.getFoodID(name);
					String info = food.getFoodInfo(id);
					//Show the info
					JOptionPane.showMessageDialog(null, info);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//Delete item button and run method to delete it
		JButton btnNewButton_3 = new JButton("Delete Item");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//get ID to edit this food item
					String name = (String) list.getSelectedValue();
					int id = food.getFoodID(name);
					food.removeFoodItem(id);
					//Remove food name from the listModel to reflect the change in the JList
					listModel.removeElement(name);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//Label to give instructions as to how to use it
		JLabel lblNewLabel = new JLabel("Select an item from the list, then click on the right action buttons to perform an action");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addContainerGap(557, Short.MAX_VALUE)
							.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(210)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(88)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 507, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(34)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addGap(40))
		);
		
		
		
		//Scroll pane if the menu goes beyond the dimensions of the UI
		scrollPane.setViewportView(list);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Close");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
