import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class Food {

	private String name;
	private double price;
	private int calories; 
	private String description;
	
	
    //constructor
    public Food(String name, double price, int calories, String description)
    {
 		  this.name = name;
 		  this.price = price;
 		  this.calories = calories;
 		  this.description = description;
    }
    
    //Constructor with no arguments
    public Food()
    {
    	
    }
    
    /**
     * Returns the food ID given the name of the item
     * @param foodName name of the item
     * @return the integer foodID
     * @throws Exception
     */
    public int getFoodID(String foodName, Connection con) throws Exception
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
    public String getFoodinfo(int foodID, Connection con) throws Exception
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
    			System.out.println("foodID: "+ id);
    			System.out.println("name: "+ name);
    			System.out.println("price: "+ price);
    			System.out.println("calories: "+ calories);
    			System.out.println("description: "+ description);
    			result = id + " " + name + " " + price + " " + calories + " " + description;
    	    }
    	    
    	    return result;
    	}catch(Exception e){System.out.println(e);}
    	return null;
    }
    
    /**
    * Getter method of name attribute
     * @param foodID The food item ID that the attribute belongs to 
     * @param con Connection to establish the database
     * @return name of item
     * @throws Exception
     */
    public String getName(int foodID, Connection con) throws Exception
    {
    	try {
    		//use SELECT query to return attribute of item
    		PreparedStatement stmt = con.prepareStatement("SELECT name FROM Food where foodID=?");
    		stmt.setInt(1,foodID);
    		
    	    ResultSet rs = stmt.executeQuery();
    	    while(rs.next()) {
    	    	name = rs.getString("name");
    	    	System.out.println("Name: " + name);
    	    }
    	    //return attribute
    	    return name;
    	}catch(Exception e){System.out.println(e);}
    	
    	return null;
    }
    
    /**
    * Getter method of price attribute
     * @param foodID The food item ID that the attribute belongs to 
     * @param con Connection to establish the database
     * @return price of item
     * @throws Exception
     */
    public double getPrice(int foodID, Connection con) throws Exception
    {
    	try {
    		//use SELECT query to return attribute of item
    		PreparedStatement stmt = con.prepareStatement("SELECT price FROM Food where foodID=?");
    		stmt.setInt(1,foodID);
    		
       	    ResultSet rs = stmt.executeQuery();
    	    while(rs.next()) {
    	    	price = rs.getDouble("price");
    	    	System.out.println("Price: " + price);
    	    }
    	    //return attribute
    	    return price;
    	}catch(Exception e){System.out.println(e);}
    	
    	return 0.0;
    }
    
    /**
    * Getter method of calories attribute
     * @param foodID The food item ID that the attribute belongs to 
     * @param con Connection to establish the database
     * @return calories of item
     * @throws Exception
     */
    public int getCalories(int foodID, Connection con) throws Exception
    {
    	try {
    		//use SELECT query to return attribute of item
    		PreparedStatement stmt = con.prepareStatement("SELECT calories FROM Food where foodID=?");
    		stmt.setInt(1,foodID);
    		
       	    ResultSet rs = stmt.executeQuery();
    	    while(rs.next()) {
    	    	calories = rs.getInt("calories");
    	    	System.out.println("Calories: " + calories);
    	    }
    	    //return attribute
    	    return calories;
    	}catch(Exception e){System.out.println(e);}
    	
    	return 0;
    }
    
    /**
     * Getter method of description attribute
     * @param foodID The food item ID that the attribute belongs to 
     * @param con Connection to establish the database
     * @return description of item
     * @throws Exception
     */
    public String getDescription(int foodID, Connection con) throws Exception
    {
    	try {
    		//use SELECT query to return attribute of item
    		PreparedStatement stmt = con.prepareStatement("SELECT description FROM Food where foodID=?");
    		stmt.setInt(1,foodID);
    		
    	    ResultSet rs = stmt.executeQuery();
    	    while(rs.next()) {
    	    	description = rs.getString("description");
    	    	System.out.println("Price: " + description);
    	    }
    	    //return attribute
    	    return description;
    	}catch(Exception e){System.out.println(e);}
    	
    	return null;
    }

    /**
     * Method to connect to database and a row to the database. FoodID is auto incremented, while the 
     * other attributes are asked by the user before being saved to the database. Uses INSERT INTO db
     * VALUES query to add a food item
     * @param con Connection to establish the database
     * @throws Exception if sql connection/access fails
     */
    public void addFoodItem(Connection con) throws Exception
    {
    	try {
        	//ask user for values
        	Scanner scnr = new Scanner(System.in);
        	System.out.println("What is the name of the food item?");
        	name = scnr.nextLine();
        	System.out.println("What is the price of the item?");
        	price = scnr.nextDouble();
        	System.out.println("What is the calorie amount of the item?");
        	calories = scnr.nextInt();
        	scnr.nextLine();
        	System.out.println("What is the description of the item?");
        	description = scnr.nextLine();
        	
        	//Use query and statement to add values
        	String sql = "INSERT INTO Food (name, price, calories, description) VALUES (?, ?, ?, ?)";
        	//add respective values
        	PreparedStatement stmt = con.prepareStatement(sql);
    		stmt.setString(1, name);
    		stmt.setDouble(2, price);
    		stmt.setInt(3, calories);
    		stmt.setString(4, description);
    		
    		stmt.executeUpdate();
    	} catch(Exception e){System.out.println(e);}
    }

    /**
     * Removes a food item from the database given the ID
     * @param foodID Removes the item associated with this database
     * @param con Connection to establish the database
     * @throws Exception
     */
    public void removeFoodItem(int foodID, Connection con) throws Exception
    {
    	try {
    		//Uses DELETE query, with the condition of the foodID. Deletes that row with that ID
        	String sql ="DELETE FROM Food WHERE foodID=?";
        	PreparedStatement stmt = con.prepareStatement(sql);
    		stmt.setInt(1, foodID);
    		stmt.executeUpdate();
    	} catch(Exception e){System.out.println(e);}
    }

    /**
     * Updates the name attribute of the food item given the ID
     * @param foodID The food item ID that the attribute belongs to 
     * @param con Connection to establish the database
     * @throws Exception
     */
    public void updateName(int foodID, Connection con) throws Exception
    {
        try {
        	String sql = "";
        	Scanner scnr = new Scanner(System.in);
        
        	//Use UPDATE query to change specific attribute in the database
        	System.out.println("What would you like to change it to?");
    		name = scnr.nextLine();
    		sql = "UPDATE Food SET name=? WHERE foodID=?";
    		PreparedStatement stmt = con.prepareStatement(sql);
    		stmt.setString(1, name);
    		stmt.setInt(2, foodID);
    		stmt.executeUpdate();
        		
        	
        } catch(Exception e){System.out.println(e);}
    	
    	
    }
    
    /**
     * Updates the price attribute of the food item given the ID
     * @param foodID The food item ID that the attribute belongs to 
     * @param con Connection to establish the database
     * @throws Exception
     */
    public void updatePrice(int foodID, Connection con) throws Exception
    {
        try {
        	String sql = "";
        	Scanner scnr = new Scanner(System.in);

        	//Use UPDATE query to change specific attribute in the database
        	System.out.println("What would you like to change it to?");
    		price = scnr.nextDouble();
    		scnr.nextLine();
    		sql = "UPDATE Food SET price=? WHERE foodID=?";
    		PreparedStatement stmt = con.prepareStatement(sql);
    		stmt.setDouble(1, price);
    		stmt.setInt(2, foodID);
    		stmt.executeUpdate();
        } catch(Exception e){System.out.println(e);}
    	
    	
    }
    
    /**
     * Updates the calories attribute of the food item given the ID
     * @param foodID The food item ID that the attribute belongs to 
     * @param con Connection to establish the database
     * @throws Exception
     */
    public void updateCalories(int foodID, Connection con) throws Exception
    {
        try {
        	String sql = "";
        	Scanner scnr = new Scanner(System.in);

        	//Use UPDATE query to change specific attribute in the database
        	System.out.println("What would you like to change it to?");
    		calories = scnr.nextInt();
    		scnr.nextLine();
    		sql = "UPDATE Food SET calories=? WHERE foodID=?";
    		PreparedStatement stmt = con.prepareStatement(sql);
    		stmt.setInt(1, calories);
    		stmt.setInt(2, foodID);
    		stmt.executeUpdate();
        } catch(Exception e){System.out.println(e);}
    	
    	
    }
    
    /**
     * Updates the description attribute of the food item given the ID
     * @param foodID The food item ID that the attribute belongs to 
     * @param con Connection to establish the database
     * @throws Exception
     */
    public void updateDescription(int foodID, Connection con) throws Exception
    {
        try {
        	String sql = "";
        	Scanner scnr = new Scanner(System.in);
        	
        	//Use UPDATE query to change specific attribute in the database
        	System.out.println("What would you like to change it to?");
    		description = scnr.nextLine();
    		sql = "UPDATE Food SET description=? WHERE foodID=?";
    		PreparedStatement stmt = con.prepareStatement(sql);
    		stmt.setString(1, description);
    		stmt.setInt(2, foodID);
    		stmt.executeUpdate();
        } catch(Exception e){System.out.println(e);}
    	
    	
    }


}
