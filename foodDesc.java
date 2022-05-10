//package posSystem;

import java.awt.EventQueue;
import java.awt.Dialog.ModalityType;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class foodDesc {

	private static JDialog frame;

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
		frame = new JDialog();
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 546, 374);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(food);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(10, 84, 470, 50);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(desc);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setBounds(10, 181, 470, 105);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Close button is clicked
				
				frame.dispose();
			}
		});
		btnNewButton.setBounds(211, 297, 91, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Food Item:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_2.setBounds(10, 72, 151, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Description:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_3.setBounds(10, 156, 122, 14);
		frame.getContentPane().add(lblNewLabel_3);
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
		
	}
}
