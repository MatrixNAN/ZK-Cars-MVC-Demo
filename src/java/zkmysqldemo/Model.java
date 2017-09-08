package zkmysqldemo;

// package zkmysqldemo;

import zkmysqldemo.Car;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class Model implements CarService
{
	Statement DBSql;
	Connection conn;
	
	public Model()
	{
	
	}
	
	private void InitializeDB() 
	{
	    try 
	    {
                java.io.File logfile = new java.io.File("/home/student/NetBeansProjects/ZkMySQLDemo/log.txt");
                java.io.PrintWriter logoutput = new java.io.PrintWriter(logfile);
                logoutput.println("Start Database Connection!!!");
	      	// Load the driver
	      	Class.forName("com.mysql.jdbc.Driver");
	      	// Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                logoutput.println("Driver loaded\n");
	
	      	// Connect to the ODBC - Access database
	      	conn = DriverManager.getConnection("jdbc:mysql://localhost/Cars", "root", "password");      
	      	// conn = DriverManager.getConnection("jdbc:odbc:Cars", "", "" );
                logoutput.println("Database connected");
	      	// Create a statement
	      	DBSql = conn.createStatement();
                logoutput.println("DBSql Sucess!!!");
                logoutput.close();
            }
            catch (Exception ex) 
            {
	      	System.out.println("Connection failed: " + ex);
            }
            
	}

	public List<Car> findAll()
	{
            InitializeDB();
            List<Car> result = new ArrayList<Car>();

	    // Build a SQL SELECT statement
	    String query = "SELECT * FROM Cars";
	
	    try 
	    {
	      	// Execute query
	      	ResultSet queryResults = DBSql.executeQuery(query);
	      
	      	while( queryResults.next() ) 
	      	{
	      		Car car = new Car( queryResults.getInt(1),
	      						   queryResults.getString(2),
	      						   queryResults.getString(3),
	      						   queryResults.getString(5),
	      						   queryResults.getString(4),
	      						   queryResults.getString(6)
	      						 );
	      		result.add(car);	
	      	}
			conn.close();
	    }
	    catch(SQLException ex) 
	    {
	      System.out.println("Select failed: " + ex);
	    }
	    
		return result;
	}
	
	public List<Car> search(String keyword)
	{
            InitializeDB();
            List<Car> result = new ArrayList<Car>();

	    // Build a SQL SELECT statement
	    String query = "SELECT * FROM Cars WHERE Make = "
	      + "'" + keyword.trim() + "' OR Model = " + "'" + keyword.trim() + "'";
	
	    try 
	    {
		// Execute query
		System.out.println(query);
	    	ResultSet queryResults = DBSql.executeQuery(query);
	      	
	      	while( queryResults.next() ) 
	      	{
                    Car car = new Car( queryResults.getInt(1),
                                       queryResults.getString(2),
                                       queryResults.getString(3),
                                       queryResults.getString(5),
                                       queryResults.getString(4),
                                       queryResults.getString(6)
                                     );
                    result.add(car);	
	      	}
		conn.close();
	    }
	    catch(SQLException ex) 
	    {
	      System.out.println("Select failed: " + ex);
	    }

	return result;
	}	
}
