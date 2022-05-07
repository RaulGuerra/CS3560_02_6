// package posSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class foodDesc {

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
	public foodDesc(String food, String desc) {
		initialize(food, desc); //empty initializer
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public static void initialize(String food, String desc) {
		frame = new JFrame();
		frame.setBounds(100, 100, 546, 407);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(food);
		lblNewLabel.setBounds(10, 11, 438, 77);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(desc);
		lblNewLabel_1.setBounds(10, 97, 438, 204);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Close button is clicked
				
				frame.dispose();
			}
		});
		btnNewButton.setBounds(213, 326, 91, 23);
		frame.getContentPane().add(btnNewButton);
	}
	
	
	
	
	
	public static void invokeDescWindow(String food, String desc)
	{
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					foodDesc window = new foodDesc(food, desc);
					foodDesc.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	};
}
