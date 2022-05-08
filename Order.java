// package posSystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
/**
 * 
 * @author Christopher, Abhinav Neelam, Eric Wagner-Roberts
 * class name:  Order
 * date: 3/17/2022
 * class section: CS 3560.02
 * 
 * This class represents a food order, including its ID, the check ID it corresponds to, 
 * the foodID it contains, and any modifications it may have
 *
 */

public class Order
{
	private int orderID;
	private int checkID;
	private int foodID;
	private String modification;
	
	//Constructor class to initiate values
	Order(int orderID, int checkID, int foodID, String modification)
	{
		this.orderID = orderID;
		this.checkID = checkID;
		this.foodID = foodID;
		this.modification = modification;
	}
	
	/**
	    * Gets all orders from the database
	     * @return array of Order objects
	     * @throws Exception
	*/
	public static ArrayList<Order> getOrders() throws Exception {
		Connection c = Main.getConnection();
		ArrayList<Order> orders = new ArrayList<Order>();

		try {
    		PreparedStatement stmt = c.prepareStatement("SELECT * FROM `order`");
    	    ResultSet rs = stmt.executeQuery();
    	    
    	    while(rs.next()) {
    			int orderID = rs.getInt("orderID");
    			int receiptID = rs.getInt("receiptID");
    			int foodID = rs.getInt("foodID");
    			String modification = rs.getString("modification");
    			
    			orders.add(new Order(orderID, receiptID, foodID, modification));
    	    }
    	}catch(Exception e){System.out.println(e);}
		
    	return orders;
	}
	
	/**
	    * Gets all orders from the database and return them in a 2D Array
	     * @return 2D array of order data
	     * @throws Exception
	*/
	public static String[][] getOrdersView() throws Exception {
		Connection c = Main.getConnection();
		try {
			PreparedStatement stmt = c.prepareStatement("SELECT * FROM `orderprice` ORDER BY orderID ASC", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery();
			
            rs.last();
			int length = rs.getRow();
			String[][] orders = new String[length][6];
			rs.first();
			
			if (length > 0) {
				int arrayRow = 0;
				do {
					orders[arrayRow][0] = Integer.toString(rs.getInt("orderID"));
					orders[arrayRow][1] = Integer.toString(rs.getInt("foodID"));
					orders[arrayRow][2] = Integer.toString(rs.getInt("checkID"));
					orders[arrayRow][3] = rs.getString("name");
					orders[arrayRow][4] = rs.getString("modification");
					orders[arrayRow][5] = Float.toString(rs.getFloat("price"));
					arrayRow++;
				} while (rs.next());
				return orders;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	/**
	    * Gets specific orders from the OrderPrice view and return them in a 2D Array
	     * @return 2D array of order data
	     * @throws Exception
	*/	
	public static String[][] searchOrdersView(boolean orderIdChoice, boolean foodIdChoice, boolean checkIdChoice,
			boolean nameChoice, boolean modificationChoice, boolean priceChoice, int orderId, int foodId, int checkId,
			String name, String modification, float price) {
		
		System.out.println(name);
		System.out.println(modification);
		
		try {
			Connection c = Main.getConnection();
			boolean startWhere = true;

			String sql = "SELECT * FROM OrderPrice";

			//Order ID
			if (orderIdChoice) { // is exactly
				if (startWhere) {
					startWhere = false;
					sql += " WHERE (";
				}
				sql += "orderID = " + String.valueOf(orderId);
			}

			//Food ID
			if (foodIdChoice) { // is exactly
				if (startWhere) {
					startWhere = false;
					sql += " WHERE (";
				} else {
					sql += " AND ";
				}
				sql += "foodID = " + String.valueOf(foodId);
			}

			//Check ID
			if (checkIdChoice) { // is exactly
				if (startWhere) {
					startWhere = false;
					sql += " WHERE (";
				} else {
					sql += " AND ";
				}
				sql += "checkID = " + String.valueOf(checkId);
			}

			//Name
			if (nameChoice) { // is exactly
				if (startWhere) {
					startWhere = false;
					sql += " WHERE (";
				} else {
					sql += " AND ";
				}
				sql += "name = '" + String.valueOf(name) + "'";
			}

			//Modification
			if (modificationChoice) { // is exactly
				if (startWhere) {
					startWhere = false;
					sql += " WHERE (";
				} else {
					sql += " AND ";
				}
				sql += "modification = '" + String.valueOf(modification) + "'";
			}
			
			//Price
			if (priceChoice) { // is exactly
				if (startWhere) {
					startWhere = false;
					sql += " WHERE (";
				} else {
					sql += " AND ";
				}
				sql += "price = " + String.valueOf(price);
			}
			
			if (!startWhere) {
				sql += ")";
			}
			
			sql += " ORDER BY orderID ASC";
			
			System.out.println(sql);
			
			PreparedStatement stmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery();
			rs.last();
			int length = rs.getRow();
			String[][] orders = new String[length][6];
			rs.first();
			
			if (length > 0) {
				int arrayRow = 0;
				do {
					orders[arrayRow][0] = Integer.toString(rs.getInt("orderID"));
					orders[arrayRow][1] = Integer.toString(rs.getInt("foodID"));
					orders[arrayRow][2] = Integer.toString(rs.getInt("checkID"));
					orders[arrayRow][3] = rs.getString("name");
					orders[arrayRow][4] = rs.getString("modification");
					orders[arrayRow][5] = Float.toString(rs.getFloat("price"));
					arrayRow++;
				} while (rs.next());
				return orders;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	/**
	    * Gets all orders which belong to table
	     * @return array of Order objects
	     * @throws Exception
	*/
	public static ArrayList<Order> getOrdersFromTable(int tableNum) throws Exception {
		Connection c = Main.getConnection();
		ArrayList<Order> orders = new ArrayList<Order>();

		try {
    		PreparedStatement stmt = c.prepareStatement("SELECT * FROM `order` WHERE receiptID = ANY (SELECT checkID FROM `receipt` WHERE tableNumber=?)");
    		stmt.setInt(1, tableNum);
    	    ResultSet rs = stmt.executeQuery();
    	    
    	    while(rs.next()) {
    			int orderID = rs.getInt("orderID");
    			int receiptID = rs.getInt("receiptID");
    			int foodID = rs.getInt("foodID");
    			String modification = rs.getString("modification");
    			
    			orders.add(new Order(orderID, receiptID, foodID, modification));
    	    }
    	}catch(Exception e){System.out.println(e);}
		
    	return orders;
	}
	
	/**
	    * Updates Order object with a new modification
	     * @param orderID. The orderID 
	     * @param modification. The newly modified string
	     * @return void
	     * @throws Exception
	*/
	public static void updateOrder(int orderID, String modification) throws Exception
    {
		Connection con = Main.getConnection();
		
        try {
    		String sql = "UPDATE `Order` SET modification=? WHERE orderID=?";
    		
    		PreparedStatement stmt = con.prepareStatement(sql);
    		stmt.setString(1, modification);
    		stmt.setInt(2, orderID);
    		
    		stmt.executeUpdate();
        } catch(Exception e){System.out.println(e);}
    }

	/**
	    * Removes Order object referenced by ID
	     * @param orderID. The orderID 
	     * @return void
	     * @throws Exception
	*/
	public static void removeOrder(int orderID) throws Exception {
		Connection con = Main.getConnection();
		
		try {
        	String sql ="DELETE FROM `Order` WHERE orderID=?";
        	
        	PreparedStatement stmt = con.prepareStatement(sql);
        	stmt.setInt(1, orderID);
        	
    		stmt.executeUpdate();
    	} catch(Exception e){System.out.println(e);}
	}
	
	/**
	    * Removes Order objects that are part of checkID
	     * @param checkID. The checkID 
	     * @return void
	     * @throws Exception
	*/
	public static void removeCheckOrders(int checkID) throws Exception {
		Connection con = Main.getConnection();
		
		try {
        	String sql ="DELETE FROM `Order` WHERE receiptID=?";
        	
        	PreparedStatement stmt = con.prepareStatement(sql);
        	stmt.setInt(1, checkID);
        	
    		stmt.executeUpdate();
    	} catch(Exception e){System.out.println(e);}
	}
	
	/**
	    * Inserts Order object to database
	     * @param order. The order object 
	     * @return void
	     * @throws Exception
	*/
	public static void insertOrder(Order o) throws Exception {
		Connection con = Main.getConnection();
		
		try {
			String sql = "INSERT INTO Order (orderID, checkID, foodID, modification) VALUES (?, ?, ?, ?)";
	    	//add respective values
	    	PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, o.getOrderID());
			stmt.setInt(2, o.getCheckID());
			stmt.setInt(3, o.getFoodID());
			stmt.setString(4, o.getMod());
			
    		stmt.executeUpdate();
    	} catch(Exception e){System.out.println(e);}
	}
	
	/**
	    * Getter method of name attribute
	     * @param orderID The order ID that the attribute belongs to 
	     * @param con Connection to establish the database
	     * @return the modification details
	     * @throws Exception
	*/
	public static String getModification(int orderID) throws Exception {		
		Connection c = Main.getConnection();
		
		try {
    		PreparedStatement stmt = c.prepareStatement("SELECT modification FROM `Order` where orderID=?");
    		stmt.setInt(1,orderID);

       	    ResultSet rs = stmt.executeQuery();
       	    rs.next();
       	
	    	String mod = rs.getString("modification");
	        
    	    return mod;
    	}catch(Exception e){System.out.println(e);}
		
		return null;
	}
	
	//getter function = returns the attribute values 
	public int getOrderID()
	{
		return orderID;
	}
	
	public int getCheckID()
	{
		return checkID;
	}
	
	public int getFoodID()
	{
		return foodID;
	}
	
	public String getMod()
	{
		return modification;
	}
	
	//Setter methods. Sets one attribute to a new value if changes need to be made
	public void setOrderID(int orderID)
	{
		this.orderID = orderID;
	}
	
	public void setCheckID(int checkID)
	{
		this.checkID = checkID;
	}
	
	public void setFoodID(int foodID)
	{
		this.foodID = foodID;
	}
	
	public void setMod(String modification)
	{
		this.modification = modification;
	}
}
