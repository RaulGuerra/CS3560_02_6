package posSystem;
import posSystem.SQLConnect;
import posSystem.foodDesc;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FoodMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FoodMenu window = new FoodMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FoodMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 784, 498);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		String[] food = null;
		try {
			food = SQLConnect.selectFoodTable().toArray(new String[0]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JComboBox comboBox = new JComboBox(food);
		comboBox.setBounds(76, 162, 206, 22);
		frame.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setBounds(311, 162, 91, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Modify");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				//Description gets clicked:
				
				String selectFoodName = (String) comboBox.getSelectedItem();
				modifyFood.invokeModWindow(selectFoodName);
				
			}
		});
		btnNewButton_1.setBounds(432, 162, 91, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		
		// Description button
		
		JButton btnNewButton_2 = new JButton("Description");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
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
		btnNewButton_2.setBounds(553, 162, 91, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		
		
	}
}
