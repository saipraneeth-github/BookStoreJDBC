<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>Book Store</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<style>
</style>

<body style="background-color: white;">

	<header style="color: white;">
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: black;">

			<div>
				<h1>
					<a class="navbar-brand">BookStore</a>
				</h1>
			</div>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link"><button>
							<b>Books</b>
						</button></a></li>
			</ul>
			</div>
		</nav>
	</header>
	<br>

	<div class="row">
		<div class="container">
			<h3 class="text-center" style="color:black "><b>List of Books</b></h3>
			<hr>
			<div class="container text-right" >

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New Book</a>
			</div>
			<br>
			<table class="table table-bordered" style="background-color:black">
				<thead>
					<tr style="background-color:cyan; text-align:center;"  >
						<th>Book Name</th>
						<th>Author Name</th>
						<th>Price</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${book}">

						<tr style="color:white; text-align:center;">
							<td>${book.getBookName() }</td>
							<td>${book.getAuthorName() }</td>
							<td><a>₹ </a>${book.getPrice() }<a>/-</a></td>
							<td style="text-align:center"><a href="edit?ID=${book.getID()}">Edit</a>
								&nbsp;&nbsp;
								<a>|</a>
								&nbsp;&nbsp;
								<a href="delete?ID=${book.getID()}">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
</body>

</html>