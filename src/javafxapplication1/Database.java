/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database
{
  public static void main(String[] args) throws ClassNotFoundException
  {
    // load the sqlite-JDBC driver using the current class loader
    Class.forName("org.sqlite.JDBC");

    Connection conn = null;
    try
    {
      // create a database connection
      conn = DriverManager.getConnection("jdbc:sqlite:HW5.db");
      Statement st = conn.createStatement();
      st.setQueryTimeout(30);  // set timeout to 30 sec.

      //ResultSet rs = st.executeQuery("SELECT Id, Title, Price FROM Book");
      st.execute("Create table test (id INTEGER, nameID VARCHAR(30), PRIMARY KEY (id))");
      //st=conn.createStatement();
      st.execute("insert into test values (1, 'Andrew')");
      //st=conn.createStatement();
      st.execute("update test set id = 3 where id=1");
      //st=conn.createStatement();
      st.execute("delete from test where id=3");
      ResultSet rs = st.executeQuery("select sid, name,cname from student natural join took natural join section natural join course where sid = 1");
      
   
        // read the result set
        System.out.println("Id = " + rs.getInt("sid"));
        System.out.println("Name = " + rs.getString("name"));
        while(rs.next())
        System.out.println("Course = " + rs.getString("cname"));
 
    }
    catch(SQLException e)
    {
      // if the error message is "out of memory", 
      // it probably means no database file is found
      System.err.println(e.getMessage());
    }
    finally
    {
      try
      {
        if(conn != null)
          conn.close();
      }
      catch(SQLException e)
      {
        // connection close failed.
        System.err.println(e);
      }
    }
  }
}