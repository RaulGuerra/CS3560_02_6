package posSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class modifyFood {

	private static JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public modifyFood(String foodName) {
		initialize(foodName);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public static void initialize(String foodName) {
		frame = new JFrame();
		frame.setBounds(100, 100, 777, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
				
		JLabel lblNewLabel = new JLabel("Modifying: ".concat(foodName));
		lblNewLabel.setBounds(66, 47, 204, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(66, 97, 584, 127);
		frame.getContentPane().add(textArea);
		
		JButton btnNewButton = new JButton("Add Modification");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//when modification needs to 
				
				
				
			}
		});
		btnNewButton.setBounds(164, 311, 132, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				frame.dispose(); //close frame, don't save modifications
			}
		});
		btnNewButton_1.setBounds(402, 311, 91, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		
	}
	
	
	public static void invokeModWindow(String foodName)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					modifyFood window = new modifyFood(foodName);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	
	
}
