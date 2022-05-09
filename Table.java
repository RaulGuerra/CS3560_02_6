import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Table {
	
	private int tableNum;
	private int seats;
	private boolean isOccupied;
	private int xCoord;
	private int yCoord;
	private static Connection con;
	
	/**
     * Constructs object and connects to database
     */
	public Table() {
		try{
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/posdb";
			String username = "root";
			String password = "DBpassword1";
			Class.forName(driver);
	 		   
			con = DriverManager.getConnection(url,username,password);
		} catch(Exception e){System.out.println(e);}		  
	}
	
	/**
     * Returns the number of rows (Tables) present in the database\
     * @return the table row count
     * @throws Exception
     */
    public static int getNumRows() throws Exception 
    {
    	PreparedStatement stmt = con.prepareStatement("select count(*) from `table`");
        //Executing the query
        ResultSet rs = stmt.executeQuery();
        //Retrieving the result
        rs.next();
        int count = rs.getInt(1);
        return count;
    }
	
	// Getters:
    
    /**
     * Returns all the numbers of every table
     * @return Array of all table numbers
     * @throws Exception
     */
	public static int[] getAllTableNums() throws Exception {
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT tableNumber FROM `table`");
			
			int count = getNumRows();
			int i = 0;
			int[] tableNums = new int[count];
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				tableNums[i] = rs.getInt("tableNumber");
				i++;
			}
			return tableNums;
		}
		catch (Exception e) {System.out.println(e);}
		return null;
	}
	
	/**
     * Returns the number of seats at this table
     * @param int tableNum number of the table to check
     * @return number of seats at a table
     * @throws Exception
     */
	public int getSeat(int tableNum) throws Exception {
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT seats FROM `table` WHERE tableNumber=?");
			stmt.setInt(1, tableNum);
			
			int seats = 0;
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				seats = rs.getInt("seats");
			}
			return seats;
		}
		catch (Exception e) {System.out.println(e);}
		return seats;
	}
	
	/**
     * Returns all of the amount of seats of the 'table' table
     * @return array of integers for the number of seats at each table
     * @throws Exception
     */
	public static int[] getAllSeats() throws Exception {
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT seats FROM `table`");
			
			int count = getNumRows();
			int i = 0;
			int[] allSeats = new int[count];
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				allSeats[i] = rs.getInt("seats");
				i++;
			}
			return allSeats;
		}
		catch (Exception e) {System.out.println(e);}
		return null;
	}

	/**
     * Returns all of the occupations of every table
     * @return array of booleans for each table's occupation status
     * @throws Exception
     */
	public static boolean[] getAllOccupied() throws Exception{
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT occupied FROM `table`");
			
			int count = getNumRows();
			int i = 0;
			boolean[] occ = new boolean[count];
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				occ[i] = rs.getBoolean("occupied");
				i++;
			}
			return occ;
		}
		catch (Exception e) {System.out.println(e);}
		return null;
	}

	/**
     * Returns all of the x-coordinates of where the table is on the UI
     * @return array of integers for all table x coordinates
     * @throws Exception
     */
	public static int[] getAllXCoords() throws Exception {
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT xCoord FROM `table`");
			
			int count = getNumRows();
			int i = 0;
			int[] xCor = new int[count];
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				xCor[i] = rs.getInt("xCoord");
				i++;
			}
			return xCor;
		}
		catch (Exception e) {System.out.println(e);}
		return null;
	}
	
	/**
     * Returns all of the y-coordinates of where the table is on the UI
     * @return array of integers for all table y coordinates
     * @throws Exception
     */
	public static int[] getAllYCoords() throws Exception {
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT yCoord FROM `table`");
			
			int count = getNumRows();
			int i = 0;
			int[] yCor = new int[count];
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				yCor[i] = rs.getInt("yCoord");
				i++;
			}
			return yCor;
		}
		catch (Exception e) {System.out.println(e);}
		return null;
	}
	
	// Setters:
	
	/**
     * Create a new table with tableNumber key as parameter
     * @param table number to create
     * @throws Exception
     */
	public static void createTable(int tableNum) throws Exception {
		try {
			PreparedStatement stmt = con.prepareStatement("INSERT INTO `table` (tableNumber, seats, occupied, xCoord, yCoord) VALUES (?, ?, ?, ?, ?)");
			stmt.setInt(1, tableNum);
			stmt.setInt(2, 5);
			stmt.setBoolean(3, false);
			stmt.setInt(4, 0);
			stmt.setInt(5, 0);
			stmt.executeUpdate();
		} catch (Exception e) {System.out.println(e);}
	}
	
	/**
	 * Deletes the specified table using the table number
	 * @param tableNum
	 * @throws Exception
	 */
	public static void deleteTable(int tableNum) throws Exception {
		try {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM `table` WHERE tableNumber=?");
			stmt.setInt(1, tableNum);
			stmt.executeUpdate();
		} catch (Exception e) {System.out.println(e);}
	}
	
	/**
     * Set the number of seats at the table
     * @param table number to edit
     * @throws Exception
     */
	public static void setSeat(int tableNum, int seatNum) throws Exception {
		try {
			PreparedStatement stmt = con.prepareStatement("UPDATE `table` SET seats=? WHERE tableNumber=?");
			stmt.setInt(1, seatNum);
			stmt.setInt(2, tableNum);
			stmt.executeUpdate();
		} catch (Exception e) {System.out.println(e);}
		
	}
	
	/**
     * Sets whether the table is occupied
     * @param table number to edit
     * @throws Exception
     */
	public static void setOccupied(int tableNum, boolean occupied) throws Exception {
		try {
			PreparedStatement stmt = con.prepareStatement("UPDATE `table` SET occupied=? WHERE tableNumber=?");
			stmt.setBoolean(1, occupied);
			stmt.setInt(2, tableNum);
			stmt.executeUpdate();
		} catch (Exception e) {System.out.println(e);}
	}
	
	public boolean getOccupied(int tableNum) {
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT occupied FROM `table` WHERE tableNumber=?");
			stmt.setInt(1, tableNum);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			return rs.getBoolean("occupied");
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	
	/**
     * Set the x-coordinate of where the table is on the UI
     * @param table number to edit
     * @throws Exception
     */
	public static void setXCoord(int tableNum, int x) throws Exception {
		try {
			PreparedStatement stmt = con.prepareStatement("UPDATE `table` SET xCoord=? WHERE tableNumber=?");
			stmt.setInt(1, x);
			stmt.setInt(2, tableNum);
			stmt.executeUpdate();
		} catch (Exception e) {System.out.println(e);}
	}
	
	/**
     * Set the y-coordinate of where the table is on the UI
     * @param table number to edit
     * @throws Exception
     */
	public static void setYCoord(int tableNum, int y) throws Exception {
		try {
			PreparedStatement stmt = con.prepareStatement("UPDATE `table` SET yCoord=? WHERE tableNumber=?");
			stmt.setInt(1, y);
			stmt.setInt(2, tableNum);
			stmt.executeUpdate();
		} catch (Exception e) {System.out.println(e);}
	}
}