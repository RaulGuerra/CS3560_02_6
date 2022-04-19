import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class Food {

	
	
    //constructor
    public void Food()
    {
 		  
    }
   
    
    /**
     * Class to establish a connection to the database using the JDBC driver
     * @return Connection to database
     * @throws Exception if can't access sql database
     */
    public static Connection getConnection() throws Exception{
		  try{
		   String driver = "com.mysql.cj.jdbc.Driver";
		   String url = "jdbc:mysql://localhost:3306/posdb";
		   String username = "root";
		   String password = "DBpassword1";
		   Class.forName(driver);
		   
		   Connection conn = DriverManager.getConnection(url,username,password);
		   System.out.println("Sucessfully connected to database...\n");
		   return conn;
		  } catch(Exception e){System.out.println(e);}
		  
		  
		  return null;
	}
    
    /**
     * Returns the food ID given the name of the item
     * @param foodName name of the item
     * @return the integer foodID
     * @throws Exception
     */
    public int getFoodID(String foodName) throws Exception
    {
    	try {
    		//Get connection, the use select query to find the row with that food name
    		Connection con = getConnection();
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
    public String getFoodinfo(int foodID) throws Exception
    {
    	try {
    		//Obtains all information associated with the food ID
    		Connection con = getConnection();
    		PreparedStatement stmt = con.prepareStatement("SELECT * FROM Food where foodID=?");
    		stmt.setInt(1,foodID);
    		 
    		//Prints all the attributes in that associated row
    		String result = "";
    	    ResultSet rs = stmt.executeQuery();
    	    while(rs.next()) {
    			String id = rs.getString("foodID");
    			String foodName = rs.getString("name");
    			String foodPrice = rs.getString("price");
    			String calo = rs.getString("calories");
    			String descrip = rs.getString("description");
    			System.out.println("foodID: "+ id);
    			System.out.println("name: "+ foodName);
    			System.out.println("price: "+ foodPrice);
    			System.out.println("calories: "+ calo);
    			System.out.println("description: "+ descrip);
    			result = id + " " + foodName + " " + foodPrice + " " + calo + " " + descrip;
    	    }
    	    
    	    return result;
    	}catch(Exception e){System.out.println(e);}
    	return null;
    }

    /**
     * Method to connect to database and a row to the database. FoodID is auto incremented, while the 
     * other attributes are asked by the user before being saved to the database. Uses INSERT INTO db
     * VALUES query to add a food item
     * @throws Exception if sql connection/access fails
     */
    public void addFoodItem() throws Exception
    {
    	try {
    		//establish connection to database
        	Connection con = getConnection();
        	//ask user for values
        	Scanner scnr = new Scanner(System.in);
        	System.out.println("What is the name of the food item?");
        	String foodName = scnr.nextLine();
        	System.out.println("What is the price of the item?");
        	double price = scnr.nextDouble();
        	System.out.println("What is the calorie amount of the item?");
        	int calories = scnr.nextInt();
        	scnr.nextLine();
        	System.out.println("What is the description of the item?");
        	String description = scnr.nextLine();
        	
        	//Use query and statement to add values
        	String sql = "INSERT INTO Food (name, price, calories, description) VALUES (?, ?, ?, ?)";
        	//add respective values
        	PreparedStatement stmt = con.prepareStatement(sql);
    		stmt.setString(1, foodName);
    		stmt.setDouble(2, price);
    		stmt.setInt(3, calories);
    		stmt.setString(4, description);
    		
    		stmt.executeUpdate();
    	} catch(Exception e){System.out.println(e);}
    }

    public void removeFoodItem(int foodID) throws Exception
    {
    	try {
    		//Uses DELETE query, with the condition of the foodID. Deletes that row with that ID
    		Connection con = getConnection();
        	String sql ="DELETE FROM Food WHERE foodID=?";
        	PreparedStatement stmt = con.prepareStatement(sql);
    		stmt.setInt(1, foodID);
    		stmt.executeUpdate();
    	} catch(Exception e){System.out.println(e);}
    }

    public void modifyFoodItem(int foodID) throws Exception
    {
        try {
        	Connection con = getConnection();
        	String sql = "";

        	//ask user which attribute to modify
        	Scanner scnr = new Scanner(System.in);
        	System.out.println("What attribute would you like to modify?");
        	String attribute = scnr.nextLine();
        	String changeName = "";
        	int changeCalo = 0;
        	double changePrice = 0;
        	String changeDescrip = "";
        	//if-else where it updates the attribute the user requests with UPDATE query
        	if(attribute.equals("name"))
        	{
        		System.out.println("What would you like to change it to?");
        		changeName = scnr.nextLine();
        		sql = "UPDATE Food SET name=? WHERE foodID=?";
        		PreparedStatement stmt = con.prepareStatement(sql);
        		stmt.setString(1, changeName);
        		stmt.setInt(2, foodID);
        		stmt.executeUpdate();
        	}
        	else if(attribute.equals("price"))
        	{
        		System.out.println("What would you like to change it to?");
        		changePrice = scnr.nextDouble();
        		scnr.nextLine();
        		sql = "UPDATE Food SET price=? WHERE foodID=?";
        		PreparedStatement stmt = con.prepareStatement(sql);
        		stmt.setDouble(1, changePrice);
        		stmt.setInt(2, foodID);
        		stmt.executeUpdate();
        	}
        	else if(attribute.equals("calories"))
        	{
        		System.out.println("What would you like to change it to?");
        		changeCalo = scnr.nextInt();
        		scnr.nextLine();
        		sql = "UPDATE Food SET calories=? WHERE foodID=?";
        		PreparedStatement stmt = con.prepareStatement(sql);
        		stmt.setInt(1, changeCalo);
        		stmt.setInt(2, foodID);
        		stmt.executeUpdate();
        	}
        	else if (attribute.equals("description"))
        	{

        		System.out.println("What would you like to change it to?");
        		changeDescrip = scnr.nextLine();
        		sql = "UPDATE Food SET description=? WHERE foodID=?";
        		PreparedStatement stmt = con.prepareStatement(sql);
        		stmt.setString(1, changeDescrip);
        		stmt.setInt(2, foodID);
        		stmt.executeUpdate();
        	}
        } catch(Exception e){System.out.println(e);}
    	
    	
    }


}
