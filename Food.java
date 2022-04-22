import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.swing.JOptionPane;

public class Food {

	private String name;
	private double price;
	private int calories; 
	private String description;
	private static Connection con;
	
	
    //constructor
    public Food(String name, double price, int calories, String description) throws ClassNotFoundException, SQLException
    {
 		  this.name = name;
 		  this.price = price;
 		  this.calories = calories;
 		  this.description = description;
 		 try{
 			   String driver = "com.mysql.cj.jdbc.Driver";
 			   String url = "jdbc:mysql://localhost:3306/posdb";
 			   String username = "root";
 			   String password = "DBpassword1";
 			   Class.forName(driver);
 			   
 			   Connection con = DriverManager.getConnection(url,username,password);
 			  
 			  } catch(Exception e){System.out.println(e);}
 			  
 			
 			 }
    
    //Constructor with no arguments
    public Food()
    {
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
     * Helper method for getting all food names, gets rows to return size for String array
     * @return number of rows in database table
     * @throws Exception
     */
    public static int getNumRows() throws Exception 
    {
    	PreparedStatement stmt = con.prepareStatement("select count(*) from Food");
        //Executing the query
        ResultSet rs = stmt.executeQuery();
        //Retrieving the result
        rs.next();
        int count = rs.getInt(1);
        return count;
    }
    
    /**
     * Returns all food names in the table - method to help with GUI menu to show list of items
     * @param con Connection to database
     * @return the integer foodID
     * @throws Exception
     */
    public static String[] getAllFoodNames() throws Exception
    {
    	try {
    		//SELECT statement for food table
    		PreparedStatement stmt = con.prepareStatement("SELECT name FROM Food");
    		
    		int count = getNumRows();
    		int i = 0;
    		String[] names = new String[count];
    		ResultSet rs = stmt.executeQuery();
    		while(rs.next()) {
    			//Set array entry to each food item name
    			 names[i]= rs.getString("name");
    			i++;
    		}
            return names;
    	} catch(Exception e){System.out.println(e);}
    	return null;
    }
    
    /**
     * Returns the food ID given the name of the item - helps for running other methods together with GUI
     * Essentially SELECT foodID FROM Food where name=? query
     * @param foodName name of the item
     * @return the integer foodID
     * @throws Exception
     */
    public int getFoodID(String foodName) throws Exception
    {
    	try {
    		PreparedStatement stmt = con.prepareStatement("SELECT foodID FROM Food where name=?");
    		stmt.setString(1,foodName);
    		
    		//execute query to find the ID and print it
    		int id = 0;
    		ResultSet rs = stmt.executeQuery();
    		while(rs.next()) {
    			id = rs.getInt("foodID");
    			System.out.println(id);
    		}
            return id;
    	} catch(Exception e){System.out.println(e);}
    	return 0;
    }

    /**
     * Returns all the associated information associated with the ID of an item
     * @param foodID The auto incremented ID of the item
     * @return A string containing all the information associated
     * @throws Exception If can't access database
     */
    public String getFoodInfo(int foodID) throws Exception
    {
    	try {
    		//Obtains all information associated with the food ID
    		PreparedStatement stmt = con.prepareStatement("SELECT * FROM Food where foodID=?");
    		stmt.setInt(1,foodID);
    		 
    		//Prints all the attributes in that associated row
    		String result = "";
    	    ResultSet rs = stmt.executeQuery();
    	    while(rs.next()) {
    			String id = rs.getString("foodID");
    			name = rs.getString("name");
    			price = rs.getDouble("price");
    			calories = rs.getInt("calories");
    			description = rs.getString("description");
    			
    			result = "ID: " + id + "\nName: " + name + "\nPrice: " + price + "\nCalories: " + calories + "\nDescropton: "+ description;
    	    }
    	    
    	    return result;
    	}catch(Exception e){System.out.println(e);}
    	return null;
    }
    
    
    /**
     * Method to connect to database and a row to the database. FoodID is auto incremented, while the 
     * other attributes are asked by the user before being saved to the database. Uses INSERT INTO db
     * VALUES query to add a food item
     * @param con Connection to establish the database
     * @return The name of the food item added - used by the GUI to update/refresh the list of items
     * @throws Exception if SQl connection/access fails
     */
    public String addFoodItem() throws Exception
    {
    	try {
        	//ask user for values
    		String input;
    		
    		//Prompt the user via GUI and have a textbox to enter the information
    		//cast the datatypes when needed
    		input = JOptionPane.showInputDialog("What is the name of the food item?");
        	name = input;
        	
        	input = JOptionPane.showInputDialog("What is the price of the item?");
        	price = Double.parseDouble(input);
        	
        	input = JOptionPane.showInputDialog("What is the calories of the item?");
        	calories = Integer.parseInt(input);
        	
        	input = JOptionPane.showInputDialog("What is the description of the item?");
        	description = input;
        	
        	//Use query and statement to add values
        	String sql = "INSERT INTO Food (name, price, calories, description) VALUES (?, ?, ?, ?)";
        	//add respective values
        	PreparedStatement stmt = con.prepareStatement(sql);
    		stmt.setString(1, name);
    		stmt.setDouble(2, price);
    		stmt.setInt(3, calories);
    		stmt.setString(4, description);
    		
    		stmt.executeUpdate();
    		
    		JOptionPane.showMessageDialog(null, "Food Item added");
    		return name;
    	} catch(Exception e){System.out.println(e);}
    	
    	return null;
    }

    /**
     * Removes a food item from the database given the ID
     * @param foodID Removes the item associated with this database
     * @param con Connection to establish the database
     * @throws Exception
     */
    public void removeFoodItem(int foodID) throws Exception
    {
    	try {
    		//Uses DELETE query, with the condition of the foodID. Deletes that row with that ID
        	String sql ="DELETE FROM Food WHERE foodID=?";
        	PreparedStatement stmt = con.prepareStatement(sql);
    		stmt.setInt(1, foodID);
    		stmt.executeUpdate();
    		JOptionPane.showMessageDialog(null, "Food item deleted");
    	} catch(Exception e){System.out.println(e);}
    }

    /**
     * Updates the price attribute of the food item given the ID
     * @param foodID The food item ID that the attribute belongs to 
     * @param con Connection to establish the database
     * @return name returns the name of the item. In the case where the user edits the name of a food item, this method
     * returns this so that the GUI can reflect the name change
     * @throws Exception
     */
    public String updateFood(int foodID) throws Exception
    {
        try {
        	String input;

        	//Use UPDATE query to change specific attribute in the database
        	//Use JOptionPane to print out dialog to allow text boxes for input
        	input = JOptionPane.showInputDialog("What would you like to update the name to?");
    		name = input;
    		
    		//execute query once user inputs the information
    		String sql = "UPDATE Food SET name=? WHERE foodID=?";
    		PreparedStatement stmt = con.prepareStatement(sql);
    		stmt.setString(1, name);
    		stmt.setInt(2, foodID);
    		stmt.executeUpdate();
    		
    		//Update price
    		input = JOptionPane.showInputDialog("What would you like to update the price to?");
    		price = Double.parseDouble(input);
    		sql = "UPDATE Food SET price=? WHERE foodID=?";
    	    stmt = con.prepareStatement(sql);
    		stmt.setDouble(1, price);
    		stmt.setInt(2, foodID);
    		stmt.executeUpdate();
    		
    		//Update calories
    		input = JOptionPane.showInputDialog("What would you like to update the calories to?");
    		calories = Integer.parseInt(input);
    		sql = "UPDATE Food SET calories=? WHERE foodID=?";
    	    stmt = con.prepareStatement(sql);
    		stmt.setInt(1, calories);
    		stmt.setInt(2, foodID);
    		stmt.executeUpdate();
    		
    		//Update description
    		input = JOptionPane.showInputDialog("What would you like to update the description to?");
    		description = input;
    		sql = "UPDATE Food SET description=? WHERE foodID=?";
    	    stmt = con.prepareStatement(sql);
    		stmt.setString(1, description);
    		stmt.setInt(2, foodID);
    		stmt.executeUpdate();
    		
    		JOptionPane.showMessageDialog(null, "Food Item updated");
    		
    		return name;
        } catch(Exception e){System.out.println(e);}
    	
    	return null;
    }
    


}
