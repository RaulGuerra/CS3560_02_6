// package posSystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class SQLConnect {

	public static void main(String[] args) throws Exception {
		
		selectFoodTable();
	}
	
	//*****************************************

	
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
	
	//*****************************************
	
	public static ArrayList<String> selectFoodTable() throws Exception{
		try {
			Connection con = getConnection();
			PreparedStatement selectFood = con.prepareStatement("SELECT * FROM food;");
			
			ResultSet result = selectFood.executeQuery();
			
			ArrayList<String> array = new ArrayList<String>();
			while(result.next()) {
			//	System.out.println(result.getString("foodID"));
			//	System.out.println(" ");
			//	System.out.println(result.getString("name"));
			/*	System.out.println(" ");
				System.out.println(result.getString("price"));
				System.out.println(" ");
				System.out.println(result.getString("calories"));
				System.out.println(" ");
				System.out.println(result.getString("description"));
				*/
				array.add(result.getString("name"));
			}
				System.out.println("All records have been selected!");
				return array;
			
		}catch(Exception e){System.out.println(e);}
		return null;
	}
	
	
	
	public static String getDesc(String foodName) throws Exception{
		try {
			Connection con = getConnection();
			
			String test = "SELECT description FROM food WHERE NAME = \'" + foodName + "\';";
			
			PreparedStatement selectDesc = con.prepareStatement(test);
			
			ResultSet result = selectDesc.executeQuery();
			
			
			while(result.next()) {
				test = result.getString("description");
				}
			return test;
			
		}catch(Exception e){System.out.println(e);}
		return null;
	}

	
//end of main
}
