import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.Book_Store.Books;

import java.sql.*;

public class JDBC_Test_Connection {
    public static void main(String[] args) throws Exception{
    	String jdbcURL = "jdbc:mysql://localhost:3306/bookstore_db?useSSL=false";
       String jdbcUserBookName = "root";
       String jdbcPassword = "12345";
       String query
       = "select * from BookList";
       String query2 = "";
       Class.forName("com.mysql.jdbc.Driver");
       Connection connection = DriverManager.getConnection(jdbcURL, jdbcUserBookName, jdbcPassword);
       System.out.println(
               "Connection Established successfully");
       Statement st = connection.createStatement();
       ResultSet rs
           = st.executeQuery(query);
       while (rs.next()) {
    		   int Id = rs.getInt("ID");
               String BookName = rs.getString("BookName");
               String AuthorName = rs.getString("AuthorName");
               Float Price = rs.getFloat("Price");
               System.out.println(Id+" - "+BookName+" - "+AuthorName+" - "+Price);
       }
    	   st.close(); // close statement
           connection.close(); // close connection
           System.out.println("Connection Closed....");

       
        
    }


}

