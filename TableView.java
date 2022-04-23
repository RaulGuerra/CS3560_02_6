import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;

public class TableView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Point startPoint;
	private int count = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TableView dialog = new TableView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TableView() {
		setModal(true);
		setTitle("Table Viewer");
		setBounds(100, 100, 507, 339);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Table Layout", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblNewLabel = new JLabel("Add a new table:");
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton jbtn = new JButton("Table " + String.valueOf(count));
				jbtn.setBackground(new Color(144, 238, 144));
				jbtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// MyDialog dialog = new MyDialog();
						// dialog.setVisible(true);
						
					}
					@Override
					public void mousePressed(MouseEvent e) {
						startPoint = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), e.getComponent().getParent());
					}
					@Override
					public void mouseReleased(MouseEvent e) {
						startPoint = null;
					}
				});
				jbtn.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseDragged(MouseEvent e) {
						Point location = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), e.getComponent().getParent());
						if (e.getComponent().getParent().getBounds().contains(location)) {
							Point newLocation = e.getComponent().getLocation();
							newLocation.translate(location.x - startPoint.x, location.y - startPoint.y);
							newLocation.x = Math.max(newLocation.x, 0);
							newLocation.y = Math.max(newLocation.y, 0);
							newLocation.x = Math.min(newLocation.x, e.getComponent().getParent().getWidth() - e.getComponent().getWidth());
							newLocation.y = Math.min(newLocation.y, e.getComponent().getParent().getHeight() - e.getComponent().getHeight());
							e.getComponent().setLocation(newLocation);
							startPoint = location;
						}
					}
				});
				jbtn.setBounds(panel.getX(), panel.getY(), 80, 23);
				panel.add(jbtn);
				panel.repaint();
				// panel.revalidate();
				count++;
			}
		});
		JLabel lblNewLabel_1 = new JLabel("Delete a table:");
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = (String) JOptionPane.showInputDialog(null, "Enter the table number to delete:",
										"Delete Table",
										JOptionPane.INFORMATION_MESSAGE);
				if (s == null)
					System.out.println("Cancelled");
				else if (s.length() == 0)
					System.out.println("No string entered.\n");
				else if (Integer.parseInt(s) < 5)
					JOptionPane.showMessageDialog(null, "\"" + s + "\" is not a valid table num");
				else
					System.out.println("Valid\n");
			}
		});
		JLabel lblNewLabel_2 = new JLabel("Update a table:");
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = JOptionPane.showInputDialog(null, "Enter the table number to update:",
						"Update Table",
						JOptionPane.INFORMATION_MESSAGE);
				if (s == null)
					System.out.println("Cancelled");
				else if (s.length() == 0)
					System.out.println("No string entered.\n");
				else if (Integer.parseInt(s) < 5)
					JOptionPane.showMessageDialog(null, "\"" + s + "\" is not a valid table num");
				else {
					System.out.println("Valid\n"); // For valid, it will open a new window
					String s1 = JOptionPane.showInputDialog(null, "Enter new number of seats:",
							"Update Seat",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		JButton btnNewButton_3 = new JButton("Table 1");
		btnNewButton_3.setBackground(new Color(144, 238, 144));
		btnNewButton_3.setBounds(145, 27, 80, 23);
		
		JButton btnNewButton_4 = new JButton("Table 2");
		btnNewButton_4.setBackground(new Color(250, 128, 114));
		btnNewButton_4.setBounds(16, 68, 80, 23);
		
		JButton btnNewButton_5 = new JButton("Table 3");
		btnNewButton_5.setBackground(new Color(144, 238, 144));
		btnNewButton_5.setBounds(16, 109, 80, 23);
		
		JButton btnNewButton_6 = new JButton("Table 4");
		btnNewButton_6.setBackground(new Color(144, 238, 144));
		btnNewButton_6.setBounds(16, 150, 80, 23);
		
		JButton btnNewButton_7 = new JButton("Table 5");
		btnNewButton_7.setBackground(new Color(250, 128, 114));
		btnNewButton_7.setBounds(145, 68, 80, 23);
		
		JButton btnNewButton_8 = new JButton("Table 6");
		btnNewButton_8.setBackground(new Color(250, 128, 114));
		btnNewButton_8.setBounds(145, 109, 80, 23);
		
		JButton btnNewButton_9 = new JButton("Table 7");
		btnNewButton_9.setBackground(new Color(144, 238, 144));
		btnNewButton_9.setBounds(145, 150, 80, 23);
		panel.setLayout(null);
		panel.add(btnNewButton_4);
		panel.add(btnNewButton_5);
		panel.add(btnNewButton_6);
		panel.add(btnNewButton_7);
		panel.add(btnNewButton_8);
		panel.add(btnNewButton_9);
		panel.add(btnNewButton_3);
		
		JLabel lblNewLabel_3 = new JLabel("Number of Seats: ");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(10)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(6)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(btnNewButton)
								.addComponent(lblNewLabel_1)
								.addComponent(btnNewButton_1)
								.addComponent(lblNewLabel_2)
								.addComponent(btnNewButton_2)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_3))))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
					.addGroup(gl_contentPanel.createSequentialGroup()
						.addGap(28)
						.addComponent(lblNewLabel)
						.addGap(6)
						.addComponent(btnNewButton)
						.addGap(11)
						.addComponent(lblNewLabel_1)
						.addGap(6)
						.addComponent(btnNewButton_1)
						.addGap(11)
						.addComponent(lblNewLabel_2)
						.addGap(6)
						.addComponent(btnNewButton_2)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_3))
					.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
						.addGap(11)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton returnButton = new JButton("Return");
				returnButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				returnButton.setActionCommand("Cancel");
				buttonPane.add(returnButton);
			}
		}
	}
}
