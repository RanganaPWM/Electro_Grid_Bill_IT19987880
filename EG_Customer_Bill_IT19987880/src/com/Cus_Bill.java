package com;

import java.sql.*;

public class Cus_Bill {

	private static String url = "jdbc:mysql://localhost:3306/electrical_grid_system";
	private static String userName = "root";
	private static String password = "1997";
	
	
	public Connection connect()
	{
	Connection con = null;
	
	try
	{
	  Class.forName("com.mysql.jdbc.Driver");
	  con= DriverManager.getConnection(url,userName,password);
	  //For testing
	  System.out.print("Successfully connected");
	}
	catch(Exception e)
	{
		System.out.println("Database connection is not success!!!");
	}
	
	return con;
	}

	//user account insert method
	
	public String insertDetails(String Account_Number, String days, String units, String Month, String Amount) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into electrical_grid_system.user_bill(`Bill_id`,`User_Account_Number`,`No_of_days`,`No_of_units`,`Month`,`Amount`)" + " values (?, ?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, Account_Number); 
	 preparedStmt.setInt(3, Integer.parseInt(days)); 
	 preparedStmt.setDouble(4, Double.parseDouble(units)); 
	 preparedStmt.setString(5, Month); 
	 preparedStmt.setDouble(6, Double.parseDouble(Amount)); 
	 // execute the statement
	 
	 preparedStmt.execute(); 
	 con.close(); 
	 String newBill = readDetails();
	 output = "{\"status\":\"success\", \"data\": \"" + newBill + "\"}";
	 } 
	 catch (Exception e) 
	 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while inserting the Bill.\"}";
		  System.err.println(e.getMessage());
	 } 
	 return output; 
	 } 
	
			

			//View Account function
			
	public String readDetails() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Account Number</th><th>No of Days</th>" +
	 "<th>No of Units</th>" + 
	 "<th>Month</th>" + "<th>Amount</th>" +
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from electrical_grid_system.user_bill"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String Bill_id = Integer.toString(rs.getInt("Bill_id")); 
	 String User_Account_Number = rs.getString("User_Account_Number"); 
	 String No_of_days = Integer.toString(rs.getInt("No_of_days")); 
	 String No_of_units = Double.toString(rs.getDouble("No_of_units")); 
	 String Month = rs.getString("Month"); 
	 String Amount = Double.toString(rs.getDouble("Amount")); 
	 
	 // Add into the html table
	 
	 output += "<tr><td><input id=\'hidCustomerIDUpdate\' name=\'hidCustomerIDUpdate\' type=\'hidden\' value=\'"
				+ Bill_id + "'>" + User_Account_Number + "</td>";
		output += "<td>" + No_of_days + "</td>";
		output += "<td>" + No_of_units + "</td>";
		output += "<td>" + Month + "</td>";
		output += "<td>" + Amount + "</td>";
		
	 // buttons
	 
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
				+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-Bill_id='"
				+ Bill_id + "'>" + "</td></tr>";
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the details."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
			  
			    
			

	 //update
	
	public String updateDetails(String BillID, String AccountNumber, String Days, String Units, String Month, String Amount) 
	 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for updating."; }
	 
	  // create a prepared statement
	 
	 String query = "UPDATE electrical_grid_system.user_bill SET User_Account_Number=?,No_of_days=?,No_of_units=?,Month=?,Amount=? WHERE Bill_id=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	
	 // binding values
	 preparedStmt.setString(1, AccountNumber); 
	 preparedStmt.setInt(2, Integer.parseInt(Days)); 
	 preparedStmt.setDouble(3, Double.parseDouble(Units)); 
	 preparedStmt.setString(4, Month); 
	 preparedStmt.setDouble(5, Double.parseDouble(Amount));
	 preparedStmt.setInt(6, Integer.parseInt(BillID));
	 
	
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 String newBill = readDetails();
	   output = "{\"status\":\"success\", \"data\": \"" + newBill + "\"}";
	 
	 
	 } 
	 catch (Exception e) 
	 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while updating the Details.\"}";
		   System.err.println(e.getMessage());
	 } 
	 return output; 
	 }
	

				
			


	//delete account function
	
					
	public String deleteDetails(String Bill_id) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from electrical_grid_system.user_bill where Bill_id=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(Bill_id)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 String newBill = readDetails();
     output = "{\"status\":\"success\", \"data\": \"" + newBill + "\"}";
	  
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the details."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
}