import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Receipt {

	private static Connection con;

	private Timestamp date;
	private int checkID;
	private float total;
	private int tableNumber;
	private boolean paid;
	String[][] orderQuantities;

	public Receipt() {
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/posdb";
			String username = "root";
			String password = "DBpassword1";
			Class.forName(driver);

			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void getReceipt(int checkID) {
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM ReceiptSearch WHERE checkID=?");
			stmt.setInt(1, checkID);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			this.checkID = checkID;
			date = rs.getTimestamp("date");
			total = rs.getFloat("total");
			tableNumber = rs.getInt("tableNumber");
			paid = rs.getBoolean("paid");
			orderQuantities = getOrderQuantities(checkID);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public Timestamp getDate() {
		return date;
	}
	
	public int getCheckID() {
		return checkID;
	}
	
	public float getTotal() {
		return total;
	}
	
	public int getTableNumber() {
		return tableNumber;
	}
	
	public boolean getPaid() {
		return paid;
	}
	
	public String[][] getOrderQuantities() {
		return orderQuantities;
	}

	public int getMostRecentReceiptID(int tableNumber) {
		try {
			PreparedStatement stmt = con.prepareStatement(
					"SELECT * FROM receipt WHERE tableNumber=? AND DATE = (SELECT MAX(DATE) FROM receipt WHERE tableNumber=?)");
			stmt.setInt(1, tableNumber);
			stmt.setInt(2, tableNumber);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			return rs.getInt("checkID");
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1; // table doesn't have
	}

	public String[][] getOrderQuantities(int checkID) {
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM OrderQuantity WHERE checkID=?");
			stmt.setInt(1, checkID);
			int rows = getNumRows(stmt);
			int cols = 3;
			String[][] orderQuantityData = new String[rows][cols];
			ResultSet rs = stmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				orderQuantityData[i][0] = String.valueOf(rs.getInt("quantity"));
				orderQuantityData[i][1] = String.valueOf(rs.getString("name"));
				orderQuantityData[i][2] = "$" + String.format("%.02f", rs.getFloat("amount"));
				i++;
			}
			return orderQuantityData;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public String[][] getAllReceipts() {
		try {
			PreparedStatement stmt = con
					.prepareStatement("SELECT checkID, tableNumber, total, date, paid FROM ReceiptSearch");

			int rows = getNumRows(stmt);
			int cols = 5;
			String[][] receiptData = new String[rows][cols];

			ResultSet rs = stmt.executeQuery();

			int i = 0;
			while (rs.next()) {
				receiptData[i][0] = String.valueOf(rs.getInt("checkID"));
				receiptData[i][1] = String.valueOf(rs.getInt("tableNumber"));
				receiptData[i][2] = "$" + String.format("%.02f", rs.getFloat("total"));
				receiptData[i][3] = String.valueOf(rs.getString("date"));
				receiptData[i][4] = String.valueOf(rs.getBoolean("paid"));
				i++;
			}

			return receiptData;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public String[][] getReceipts(boolean checkIdChoice, boolean tableNumberChoice, int totalAmountChoice,
			int dateChoice, boolean paidChoice, int checkID, int tableNumber, float totalAmount, Timestamp date,
			boolean paid, float totalAmountMin, float totalAmountMax, Timestamp dateMin, Timestamp dateMax) {
		try {
			boolean startWhere = true;

			String sql = "SELECT * FROM ReceiptSearch";

			// CHECK ID
			if (checkIdChoice) { // is exactly
				if (startWhere) {
					startWhere = false;
					sql += " WHERE (";
				}
				sql += "checkID = " + String.valueOf(checkID);
			}

			// TABLE NUMBER
			if (tableNumberChoice) { // is exactly
				if (startWhere) {
					startWhere = false;
					sql += " WHERE (";
				} else {
					sql += " AND ";
				}
				sql += "tableNumber = " + String.valueOf(tableNumber);
			}

			// TOTAL AMOUNT
			if (totalAmountChoice == 1) { // is exactly
				if (startWhere) {
					startWhere = false;
					sql += " WHERE (";
				} else {
					sql += " AND ";
				}
				sql += "total = " + String.valueOf(totalAmount);
			} else if (totalAmountChoice == 2) { // min/max
				if (startWhere) {
					startWhere = false;
					sql += " WHERE (";
				} else {
					sql += " AND ";
				}
				sql += "total BETWEEN " + String.valueOf(totalAmountMin) + " AND " + String.valueOf(totalAmountMax);
			}

			// DATE
			if (dateChoice == 1) { // is exactly
				if (startWhere) {
					startWhere = false;
					sql += " WHERE (";
				} else {
					sql += " AND ";
				}
				sql += "date = '" + String.valueOf(date) + "'";
			} else if (dateChoice == 2) { // min/max
				if (startWhere) {
					startWhere = false;
					sql += " WHERE (";
				} else {
					sql += " AND ";
				}
				sql += "date BETWEEN '" + String.valueOf(dateMin) + "' AND '" + String.valueOf(dateMax) + "'";
			}

			// PAID
			if (paidChoice) { // paid OR unpaid
				if (startWhere) {
					startWhere = false;
					sql += " WHERE (";
				} else {
					sql += " AND ";
				}
				sql += "paid = ";

				if (paid) {
					sql += "1";
				} else {
					sql += "0";
				}
			}

			if (!startWhere) { // conditions were set so ending parenthesis
				sql += ")";
			}

			System.out.println(sql);
			PreparedStatement stmt = con.prepareStatement(sql);

			int rows = getNumRows(stmt);
			int cols = 5;
			String[][] receiptData = new String[rows][cols];

			ResultSet rs = stmt.executeQuery();

			int i = 0;
			while (rs.next()) {
				receiptData[i][0] = String.valueOf(rs.getInt("checkID"));
				receiptData[i][1] = String.valueOf(rs.getInt("tableNumber"));
				receiptData[i][2] = "$" + String.format("%.02f", rs.getFloat("total"));
				receiptData[i][3] = String.valueOf(rs.getString("date"));
				receiptData[i][4] = String.valueOf(rs.getBoolean("paid"));
				i++;
			}

			return receiptData;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public int getNumRows(PreparedStatement stmt) throws SQLException {
		int count = 0;
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			count++;
		}
		return count;
	}

	public void addReceipt(int tableNumber) {
		// date will be added with current system time, and
		// paid will be automatically set to unpaid

		try {
			String sql = "INSERT INTO Receipt (tableNumber, date, paid) VALUES (?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, tableNumber);
			stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			stmt.setBoolean(3, false);
			stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void removeReceipt(int checkID) {
		try {
			String sql = "DELETE FROM Receipt WHERE checkID=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, checkID);
			stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void updateReceipt(int checkID, boolean paymentStatus) {
		try {
			String sql = "UPDATE Receipt SET paid=? WHERE checkID=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setBoolean(1, paymentStatus); // not sure if second parameter should be true or false
			stmt.setInt(2, checkID);
			stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		Receipt receipt = new Receipt();
//		receipt.getReceipts(false, true, 1, 0, false, 1465, 6, 4.64, null, false, 0, 0, null, null);
//		
//		String[][] arr = receipt.getReceipts(false, false, 0, 0, false, 0, 0, 0, null, true, 0, 0, null, null);
//		print2DArray(arr);.
//		ReceiptPage rp = new ReceiptPage(1);
//		rp.setVisible(true);
//		System.out.println(receipt.getMostRecentReceiptID(1));
//		print2DArray(receipt.getOrderQuantities(receipt.getMostRecentReceiptID(1)));
//		ReceiptPage rp = new ReceiptPage(1);
	}

	public static void print2DArray(String[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}
