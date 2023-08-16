package com.Book_Store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BooksDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/bookstore_db?useSSL=false";
    private String jdbcUserName = "root";
    private String jdbcPassword = "12345";
    
    private static final String INSERT_BOOK_SQL = "INSERT INTO BookList" + "  (BookName, AuthorName, Price) VALUES " +
            " (?, ?, ?)";

        private static final String SELECT_BOOK_BY_ID = "select ID,BookName,AuthorName,Price from BookList where ID =?";
        private static final String SELECT_ALL_BOOK = "select * from BookList";
        private static final String DELETE_BOOK_SQL = "delete from BookList where ID = ?;";
        private static final String UPDATE_BOOK_SQL = "update BookList set BookName = ?,AuthorName= ?, Price=? where ID = ?";
    public BooksDAO(){
    }
    protected Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    public void insertBook(Books book) throws SQLException {
        System.out.println(INSERT_BOOK_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); 
        	PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOK_SQL)) {
            preparedStatement.setString(1, book.getBookName());
            preparedStatement.setString(2, book.getAuthorName());
            preparedStatement.setFloat(3, book.getPrice());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public Books selectBook(int ID) {
        Books book = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_BY_ID);) {
            preparedStatement.setInt(1, ID);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String BookName = rs.getString("BookName");
                String AuthorName = rs.getString("AuthorName");
                Float Price = rs.getFloat("Price");
                book = new Books(ID,BookName,AuthorName,Price);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return book;
    }
    public List < Books > selectAllBooks() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Books > book = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOK);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int ID = rs.getInt("ID");
                String BookName = rs.getString("BookName");
                String AuthorName = rs.getString("AuthorName");
                Float Price = rs.getFloat("Price");
                System.out.println(ID+" - "+BookName+" - "+AuthorName+" - "+Price);
                book.add(new Books(ID, BookName, AuthorName, Price));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        System.out.println(book);
        return book;
    }
    public boolean deleteBook(int ID) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); 
        	PreparedStatement statement = connection.prepareStatement(DELETE_BOOK_SQL);) {
            statement.setInt(1, ID);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
	public boolean updateBook(Books book) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_BOOK_SQL);) {
            statement.setString(1, book.getBookName());
            statement.setString(2, book.getAuthorName());
            statement.setFloat(3, book.getPrice());
            statement.setInt(4, book.getID());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
	private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }


    	
	
}
