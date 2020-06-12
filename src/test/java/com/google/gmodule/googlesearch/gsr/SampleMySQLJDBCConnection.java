package com.google.gmodule.googlesearch.gsr;
import java.sql.*;

public class SampleMySQLJDBCConnection{
public static void main(String args[]){
try{
Class.forName("com.mysql.jdbc.Driver");

Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/googlesearch","root","BlueVegan@5");
Statement stmt=con.createStatement();

ResultSet rs=stmt.executeQuery("SELECT * FROM googlesearch.searchtextlist;");

while(rs.next())
System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));

con.close();

}catch(Exception e){ System.out.println(e);}

}
}