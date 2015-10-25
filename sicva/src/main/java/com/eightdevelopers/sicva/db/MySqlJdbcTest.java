package com.eightdevelopers.sicva.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlJdbcTest {
public static void main(String[] args){
 Connection conn=null;
 Statement stmt=null;
 ResultSet rs=null;
	
try{
//new.com.mysql.jdbc.Driver();
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	String connectionUrl="jdbc:mysql://localhost:3306/sicva";
	String connectionUser="root";
	String connectionPassword="saya";
	conn=DriverManager.getConnection(connectionUrl,connectionUser,connectionPassword);
	stmt=conn.createStatement();
	rs=stmt.executeQuery("select * from roles");
	while(rs.next()){
		String id=rs.getString("id_rol");
		String des=rs.getString("descripcion");
		
		System.out.println("ID= "+id+"desc= "+des);
	}
	
}catch(Exception e){
	e.printStackTrace();
	
}finally{
	try{if(rs!=null) rs.close();}catch(SQLException e){e.printStackTrace();}
	try{if(stmt!=null) stmt.close();}catch(SQLException e){e.printStackTrace();}
	try{if(conn!=null) conn.close();}catch(SQLException e){e.printStackTrace();}
}
}
}
