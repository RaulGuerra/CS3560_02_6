import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
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
import java.awt.Component;

public class TableView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Point startPoint;
	private int count = 1;
	private Table tab = new Table();
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	
	private static TableView dialog;
	public static void main(String[] args) {
		try {
			dialog = new TableView();
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
	public TableView() {
		setModal(true);
		setTitle("Table Viewer");
		setBounds(100, 100, 558, 339);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Table Layout", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		setButtons(panel);
		
		JLabel lblNewLabel = new JLabel("Add a new table:");
		lblNewLabel_3 = new JLabel("Number of Seats at Table :");
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton jbtn = new JButton("Table " + String.valueOf(count));
				jbtn.setBackground(new Color(144, 238, 144));
				jbtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dispose();
						ReceiptPage rp = new ReceiptPage(Integer.parseInt(((JButton) e.getComponent()).getActionCommand().substring(6)));
						rp.setVisible(true);
					}
					@Override
					public void mousePressed(MouseEvent e) {
						startPoint = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), e.getComponent().getParent());
					}
					@Override
					public void mouseReleased(MouseEvent e) {
						startPoint = null;
						String num = ((JButton) e.getComponent()).getActionCommand();
						num = num.substring(6);
						try {
							tab.setXCoord(Integer.parseInt(num), ((JButton) e.getComponent()).getX());
							tab.setYCoord(Integer.parseInt(num), ((JButton) e.getComponent()).getY());
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						int tabNum = Integer.parseInt(((JButton) e.getComponent()).getActionCommand().substring(6));
						
						try {
							lblNewLabel_3.setText("Number of Seats at Table " + tabNum + ": "+ String.valueOf(tab.getSeat(tabNum)));
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
					}
					@Override
					public void mouseExited(MouseEvent e) {
						lblNewLabel_3.setText("Number of Seats at Table :");
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
				jbtn.setBounds(panel.getX(), panel.getY(), 85, 23);
				panel.add(jbtn);
				panel.repaint();
				// panel.revalidate();
				
				try {
					tab.createTable(count);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
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
					;// Cancel button was clicked
				else if (s.length() == 0)
					JOptionPane.showMessageDialog(null, "Please enter a number before clicking \"OK\"");
				else {
					Component[] allComp = panel.getComponents();
					boolean validTable = false;
					
					int i;
					for (i = 0; i < allComp.length; i++) {
						if (((JButton) allComp[i]).getActionCommand().substring(6).equals(s)) {
							validTable = true;
							break;
						}
					}
					
					if (validTable) {
						panel.remove(allComp[i]);
						panel.revalidate();
						panel.repaint();
						try {
							tab.deleteTable(Integer.parseInt(s));
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else
						JOptionPane.showMessageDialog(null, "Please enter a valid table number.");
				}
			}
		});
		JLabel lblNewLabel_2 = new JLabel("Update seats of table:");
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = JOptionPane.showInputDialog(null, "Enter the table number to update:",
						"Update Table",
						JOptionPane.INFORMATION_MESSAGE);
				if (s == null)
					;// Cancel button was clicked
				else if (s.length() == 0)
					JOptionPane.showMessageDialog(null, "Please enter a number before clicking \"OK\"");
				else {
					Component[] allComp = panel.getComponents();
					boolean validTable = false;
					
					int i;
					for (i = 0; i < allComp.length; i++) {
						if (((JButton) allComp[i]).getActionCommand().substring(6).equals(s)) {
							validTable = true;
							break;
						}
					}
					if (validTable) {
						String s1 = JOptionPane.showInputDialog(null, "Enter the new number of seats:",
								"Update Seat",
								JOptionPane.INFORMATION_MESSAGE);
						if (s1 == null)
							; // Cancel button was clicked
						else if (s1.length() == 0)
							JOptionPane.showMessageDialog(null, "Please enter a number before clicking \"OK\"");
						else {
							try {
								Integer.parseInt(s1);
								try {
									tab.setSeat(Integer.parseInt(s), Integer.parseInt(s1));
									JOptionPane.showMessageDialog(null, "The seats at Table " + s + " have been updated.");
								} catch (Exception e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, "Please enter a valid Integer.");
							}
						}
					}
					else
						JOptionPane.showMessageDialog(null, "Please enter a valid table number.");
				}
			}
		});
		
		
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
		panel.setLayout(null);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton returnButton = new JButton("Return");
				returnButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				returnButton.setActionCommand("Cancel");
				buttonPane.add(returnButton);
			}
		}
	}
	
	public void setButtons(JPanel pan) {
		int[] allX = null;
		int[] allY = null;
		boolean[] allOcc = null;
		int[] allTabNums = null;
		
		try {
			allX = tab.getAllXCoords();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			allY = tab.getAllYCoords();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			allOcc = tab.getAllOccupied();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			allTabNums = tab.getAllTableNums();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			if (tab.getNumRows() != 0) {
				for (int i = 0; i < tab.getNumRows(); i++) {
					JButton jbtn = new JButton("Table " + String.valueOf(allTabNums[i]));
					jbtn.setBounds(allX[i], allY[i], 85, 23);
					if (allOcc[i])
						jbtn.setBackground(new Color(250, 128, 114));
					else
						jbtn.setBackground(new Color(144, 238, 144));
					jbtn.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							dispose();
							ReceiptPage rp = new ReceiptPage(Integer.parseInt(((JButton) e.getComponent()).getActionCommand().substring(6)));
							rp.setVisible(true);
						}
						@Override
						public void mousePressed(MouseEvent e) {
							startPoint = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), e.getComponent().getParent());
						}
						@Override
						public void mouseReleased(MouseEvent e) {
							startPoint = null;
							String num = ((JButton) e.getComponent()).getActionCommand();
							num = num.substring(6);
							try {
								tab.setXCoord(Integer.parseInt(num), ((JButton) e.getComponent()).getX());
								tab.setYCoord(Integer.parseInt(num), ((JButton) e.getComponent()).getY());
							} catch (NumberFormatException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						@Override
						public void mouseEntered(MouseEvent e) {
							int tabNum = Integer.parseInt(((JButton) e.getComponent()).getActionCommand().substring(6));
							
							try {
								lblNewLabel_3.setText("Number of Seats at Table " + tabNum + ": "+ String.valueOf(tab.getSeat(tabNum)));
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} 
						}
						@Override
						public void mouseExited(MouseEvent e) {
							lblNewLabel_3.setText("Number of Seats at Table :");
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
					
					pan.add(jbtn);
				}
				count = allTabNums[tab.getNumRows() - 1] + 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
