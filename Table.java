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
	
	// Constructor maybe
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
	// returns the table number of this table
	public int[] getAllTableNums() throws Exception {
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
	
	// returns the number of seats at this table
	public int getSeat(int tableNum) throws Exception {
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT seats FROM `table` where tableNumber=?");
			stmt.setInt(1, tableNum);
			
			int seats = 0;
			ResultSet rs = stmt.executeQuery();
			seats = rs.getInt("seats");
			return seats;
		}
		catch (Exception e) {System.out.println(e);}
		return seats;
	}
	
	// returns whether the table is occupied
	public boolean[] getAllOccupied() throws Exception{
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
	
	// returns the x-coordinate of where the table is on the UI
	public int[] getAllXCoords() throws Exception {
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
	
	// returns the y-coordinate of where the table is on the UI
	public int getAllYCoord() throws Exception {
		
		
		return yCoord;
	}
	
	// Setters:
	
	// set the table number
	public void createTable(int tableNum) {
		// 
	}
	
	// set the number of seats at the table
	public void setSeat(int tableNum, int seatNum) throws Exception {
		try {
			PreparedStatement stmt = con.prepareStatement("UPDATE `table` SET seats=? WHERE tableNumber=?");
			stmt.setInt(1, seatNum);
			stmt.setInt(2, tableNum);
			stmt.executeUpdate();
		} catch (Exception e) {System.out.println(e);}
		
	}
	
	// sets whether the table is occupied
	public void setOccupied(boolean occupied) {
		
	}
	
	// set the x-coordinate of where the table is on the UI
	public void setXCoord(int x) {
		
	}
	
	// set the y-coordinate of where the table is on the UI
	public void setYCoord(int y) {
		
	}
	
	public static void main(String[] args) throws Exception {
		Table tab = new Table();
		int[] xVals = tab.getAllXCoords();
		if (xVals != null) {
			for (int i = 0; i < xVals.length; i++)
				System.out.println(xVals[i]);
		}
		else
			System.out.println("Error occurred\n");
		
		int[] tabNum = tab.getAllTableNums();
		if (tabNum != null) {
			for (int i = 0; i < tabNum.length; i++)
				System.out.println(tabNum[i]);
		}
		else
			System.out.println("Error occurred\n");
		
		tab.setSeat(1, 10);
	}
}