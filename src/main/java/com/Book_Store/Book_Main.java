package com.Book_Store;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Book_Main
 */
@WebServlet("/")
public class Book_Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BooksDAO books;
	
	public void init() {
		books = new BooksDAO(); 
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Book_Main() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			        String action = request.getServletPath();

			        try {
			            switch (action) {
			                case "/new":
			                    showNewForm(request, response);
			                    break;
			                case "/insert":
			                    insertBook(request, response);
			                    break;
			                case "/delete":
			                    deletebook(request, response);
			                    break;
			                case "/edit":
			                    showEditForm(request, response);
			                    break;
			                case "/update":
			                    updatebook(request, response);
			                    break;
			                default:
			                    listBooks(request, response);
			                    break;
			            }
			        } catch (SQLException ex) {
			            throw new ServletException(ex);
			        }
			    }
	  private void listBooks(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, IOException, ServletException {
			        List < Books > book = books.selectAllBooks();
			        request.setAttribute("book", book);
			        RequestDispatcher dispatcher = request.getRequestDispatcher("book-list.jsp");
		               System.out.println(book);
			        dispatcher.forward(request, response);
			    }
	  private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			    throws ServletException, IOException {
			        RequestDispatcher dispatcher = request.getRequestDispatcher("book-form.jsp");
			        dispatcher.forward(request, response);
			    }
	  private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, ServletException, IOException {
			        int ID = Integer.parseInt(request.getParameter("ID"));
			        Books book = books.selectBook(ID);
			        RequestDispatcher dispatcher = request.getRequestDispatcher("book-form.jsp");
			        request.setAttribute("book", book);
			        dispatcher.forward(request, response);

			    }
	  private void insertBook(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, IOException {
			        String BookName = request.getParameter("BookName");
			        String AuthorName = request.getParameter("AuthorName");
			        Float Price = Float.parseFloat(request.getParameter("Price"));
			        Books newBook = new Books(BookName, AuthorName, Price);
			        books.insertBook(newBook);
			        response.sendRedirect("list");
			    }
	  private void updatebook(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, IOException {
			        int ID = Integer.parseInt(request.getParameter("ID"));
			        String BookName = request.getParameter("BookName");
			        String AuthorName = request.getParameter("AuthorName");
			        Float Price = Float.parseFloat(request.getParameter("Price"));

			        Books book = new Books(ID, BookName, AuthorName, Price);
			        books.updateBook(book);
			        response.sendRedirect("list");
			    }
	  private void deletebook(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, IOException {
			        int ID = Integer.parseInt(request.getParameter("ID"));
			        books.deleteBook(ID);
			        response.sendRedirect("list");

			    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
