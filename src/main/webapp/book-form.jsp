<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>BookStore</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        </head>
        <style>
        label{
        color:white;
        }
        .btn-center{
        text-align:center;
        }
     
        </style>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: black">
                    <div>
                        <a class="navbar-brand" style="color:white"> BookStore </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link"><button><b>Books</b></button></a></li>
                    </ul>
                </nav>
            </header>
            <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body" style="background-color:black">
                        <c:if test="${book != null}">
                            <form action="update" method="post">
                        </c:if>
                        <c:if test="${book == null}">
                            <form action="insert" method="post">
                        </c:if>

                        <caption>
                            <h2 style="color:White">
                                <c:if test="${book != null}">
                                    Edit Book
                                </c:if>
                                <c:if test="${book == null}">
                                    Add New Book
                                </c:if>
                            </h2>
                            &nbsp;&nbsp;&nbsp;&nbsp; 
                        </caption>

                        <c:if test="${book != null}">
                            <input type="hidden" name="ID" value="<c:out value='${book.getID()}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Book Name</label> <input type="text" value="<c:out value='${book.getBookName()}' />" class="form-control" name="BookName" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Author Name</label> <input type="text" value="<c:out value='${book.getAuthorName()}' />" class="form-control" name="AuthorName">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Price</label> <input type="text" value="<c:out value='${book.getPrice()}' />" class="form-control" name="Price">
                        </fieldset>

                        <div class = "btn-center"><button type="submit" class="btn btn-success">Save</button></div>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>