package com.Book_Store;

public class Books {
	    protected int ID;
	    protected String BookName;
	    protected String AuthorName;
	    protected Float Price;

	    public Books() {}

	    public Books(String BookName, String AuthorName, Float Price) {
	        super();
	        this.BookName = BookName;
	        this.AuthorName = AuthorName;
	        this.Price = Price;
	    }
	    public Books(int ID, String BookName, String AuthorName, Float Price) {
	        super();
	        this.ID = ID;
	        this.BookName = BookName;
	        this.AuthorName = AuthorName;
	        this.Price = Price;
	    }



	    public int getID() {
	        return ID;
	    }
	    public void setID(int ID) {
	        this.ID = ID;
	    }
	    public String getBookName() {
	        return BookName;
	    }
	    public void setBookName(String BookName) {
	        this.BookName = BookName;
	    }
	    public String getAuthorName() {
	        return AuthorName;
	    }
	    public void setAuthorName(String AuthorName) {
	        this.AuthorName = AuthorName;
	    }
	    public Float getPrice() {
	        return Price;
	    }
	    public void setPrice(Float Price) {
	        this.Price = Price;
	    }
	}