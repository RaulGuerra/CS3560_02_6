package posSystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 * 
 * @author Christopher
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
	
	public static String updateOrder(int orderID, String modification) throws Exception
    {
		Connection con = Main.getConnection();
		
        try {
    		String sql = "UPDATE `Order` SET modification=? WHERE orderID=?";
    		
    		PreparedStatement stmt = con.prepareStatement(sql);
    		stmt.setString(1, modification);
    		stmt.setInt(2, orderID);
    		
    		stmt.executeUpdate();
        } catch(Exception e){System.out.println(e);}
    	
    	return null;
    }

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
