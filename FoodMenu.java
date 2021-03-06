//package posSystem;
//import posSystem.SQLConnect;
//import posSystem.foodDesc;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Dialog.ModalityType;

public class FoodMenu extends JDialog {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FoodMenu window = new FoodMenu(1);
					window.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FoodMenu(int receiptID) {
		initialize(receiptID);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int receiptID) {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 456, 498);
		getContentPane().setLayout(null);
        
		
		String[] food = null;
		try {
			food = SQLConnect.selectFoodTable().toArray(new String[0]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JComboBox comboBox = new JComboBox(food);
		comboBox.setBounds(38, 109, 206, 22);
		getContentPane().add(comboBox);
		AutoCompleteDecorator.decorate(comboBox);
		
		JButton btnNewButton = new JButton("Add to order");
		btnNewButton.setBounds(273, 109, 126, 23);
		getContentPane().add(btnNewButton);
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String foodname = (String) comboBox.getSelectedItem();
				String modification = JOptionPane.showInputDialog(null, "Enter modification", "Modification Window", JOptionPane.PLAIN_MESSAGE);

				System.out.println(foodname);

		        if(modification != null)
		        {
		        	try {
		        		int foodid = Food.getFoodID(foodname);
		        		Order.insertOrder(receiptID, foodid, modification);
		        		
		        		System.out.println("Disposing...");
		        		
		        		dispose();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
		        }
			}
		});
		
		// Description button
		JButton btnNewButton_2 = new JButton("Description");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//Description gets clicked:
				
				String selectFoodName = (String) comboBox.getSelectedItem();
				try {
					String foodDescription = SQLConnect.getDesc(selectFoodName);
					foodDesc.invokeDescWindow(selectFoodName, foodDescription);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//End Description gets clicked:
			}
		});
		btnNewButton_2.setBounds(272, 143, 127, 22);
		getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("Food Menu");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel.setBounds(38, 11, 303, 45);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Return");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnNewButton_1.setBounds(273, 176, 126, 23);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Please select a food item to order or to view its description.");
		lblNewLabel_1.setBounds(38, 53, 377, 22);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Press return if no selection is desired.");
		lblNewLabel_2.setBounds(38, 73, 361, 14);
		getContentPane().add(lblNewLabel_2);
	}
}
