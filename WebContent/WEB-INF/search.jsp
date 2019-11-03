<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>IMDb</title>
</head>
<body>
	<c:forEach items="${results}" var="result">
		<ul>
			<li>Title:${result.title}</li>
			<li>Description: ${result.description}</li>
			<li>Release Year: ${result.releaseYear}</li>
			<li>Language Id: ${result.languageId}</li>
			<li>Rental Duration: ${result.rentalDuration}</li>
			<li>Rental Rate: ${result.rentalRate}</li>
			<li>Length: ${result.length}</li>
			<li>Replacement Cost: ${result.replacementCost}</li>
			<li>Rating: ${result.rating}</li>
			<li>Special Features: ${result.specialFeatures}</li>
		</ul>
	</c:forEach>


	<%-- <p>The result is: ${result.title}</p> --%>
	<c:if test="${result.title != null}">
		<ul>
			<li>Title:${result.title}</li>
			<li>Description: ${result.description}</li>
			<li>Release Year: ${result.releaseYear}</li>
			<li>Language Id: ${result.languageId}</li>
			<li>Rental Duration: ${result.rentalDuration}</li>
			<li>Rental Rate: ${result.rentalRate}</li>
			<li>Length: ${result.length}</li>
			<li>Replacement Cost: ${result.replacementCost}</li>
			<li>Rating: ${result.rating}</li>
			<li>Special Features: ${result.specialFeatures}</li>
			<li>Actors: ${result.filmActors}</li>
			<li>Category: ${result.category}</li>
		</ul>

		<form action="delete.do" method="POST">
			<input type="hidden" name="filmIdDelete" value="${result.id }" /> <input
				type="submit" value="Delete film" /><br />
		</form>

		<form action="getFilmToUpdate.do" method="GET">
			<input type="hidden" name="filmId" value="${result.id}" />
				<p>${result.id}</p>
				<input type="submit" value="Update film" /><br />
		</form>
	</c:if>

	<c:if test="${result.title == null}">
		<p>That film does not exist! Try again.</p>
	</c:if>

	<a href="addVideo.html">Add Video</a><br>

	<a href="index.html">Home</a>
</body>
</html>

