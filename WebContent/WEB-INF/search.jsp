<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" href="style.css">
<title>IMDb 2</title>
</head>

<body>
	<nav class="navbar navbar-expand-lg navbar-light sticky-top">
		<a class="navbar-brand" href="index.html"> <img class="title__img" src="imdb2.png"> </a>
	</nav>

	<div class="container">
	
<!-- THE FOLLOWING PRINTS FOR SEARCH BY KEWORD -->
		<c:forEach items="${results}" var="result">
			<div class="container cont__style" style="margin-top: 15px">
				<div class="row">
	<!-- START LEFT SIDE (ON WIDE SCREENS) OF FILM DISPLAY -->
					<div class="col-sm">
						<h1><strong> ${result.title} </strong></h1>
						
						<h2>${result.description}</h2>
						
						<h3><span class="badge badge-dark"> ${result.rating} </span></h3>
						
						<c:if test="${result.filmActors != '[]'}">
						<div class="style__box shadow-lg">
							<strong>Staring:</strong><br>
							<c:forEach items="${result.filmActors}" var="actor">
								<span class="badge badge-secondary"> ${actor.firstName} ${actor.lastName} </span>
							</c:forEach>
						</div>
						</c:if>
						
						<c:if test="${result.specialFeatures != null}">
						<div class="style__box shadow-lg">
							<strong>Special Features:</strong><br>
							${result.specialFeatures}
						</div>
						</c:if>
						
						<c:if test="${result.category != ''}">
						<div class="style__box shadow-lg" style="margin-bottom: 20px">
							<strong>Category</strong><br> <span class="badge badge-info">
								${result.category} </span>
						</div>
						</c:if>
						
					</div>
					
	<!-- START RIGHT SIDE (ON WIDE SCREENS) OF FILM DISPLAY -->
					<div class="col-sm">
						<table class="table">
							<tr>
								<td class="desc shadow">Database Film ID</td>
								<td class="dec_c shadow">${result.id}</td>
							</tr>
							<tr>
								<td class="desc shadow">Release Year</td>
								<td class="dec_c shadow">${result.releaseYear}</td>
							</tr>
<%-- 							<tr>
								<td class="desc shadow">Language Id</td>
								<td class="dec_c shadow">${result.languageId}</td>
							</tr> --%>
							<tr>
								<td class="desc shadow">Language</td>
								<td class="dec_c shadow">${result.language}</td>
							</tr>							
							<tr>
								<td class="desc shadow">Rental Duration</td>
								<td class="dec_c shadow">${result.rentalDuration}</td>
							</tr>
							<tr>
								<td class="desc shadow">Rental Rate</td>
								<td class="dec_c shadow">${result.rentalRate}</td>
							</tr>
							<tr>
								<td class="desc shadow">Replacement Cost</td>
								<td class="dec_c shadow">${result.replacementCost}</td>
							</tr>
							<tr>
								<td class="desc shadow">Length</td>
								<td class="dec_c shadow">${result.length}</td>
							</tr>
						</table>
					</div>
				</div>

	<!-- BOTTOM BUTTONS TO EDIT OR DELETE -->	
			<div class="text-center">
				<div class="btn-group">
					<form action="delete.do" method="POST" class="bottom__buttons">
						<input type="hidden" name="filmIdDelete" value="${result.id }">
						<input class="btn btn-secondary" type="submit" value="Delete film">
					</form>
					<div>
						<button class="btn btn-secondary bottom__buttons" type="button"
							data-toggle="collapse" data-target="#collapseUpdateFilm${result.id}"
							aria-expanded="false" aria-controls="collapseExample">
							Update film</button>
					</div>
				</div>
			</div>
			
		<!-- UPDATE FORM -->
			<div class="collapse" id="collapseUpdateFilm${result.id}">
				<div class="card card-body">
					<form action="update.do" method="POST">
						<input type="hidden" name="id" value="${result.id}"> 
						Title <input class="form-control" type="text" name="title" value="${result.title}" required><br> 
						Description <input class="form-control" type="text" name="description" value="${result.description}"><br>
					 	Release Year <input class="form-control" type="number" name="releaseYear" min="0" max="2147483647" value="${result.releaseYear}" required><br> 
						Language Id<input class="form-control" type="number" name="languageId" min="1" max="6" value="${result.languageId}" required ><br>	
						Rental Duration<input class="form-control" type="number" min="0" max="2147483647" name="rentalDuration" value="${result.rentalDuration}" required><br>
						Rental Rate<input class="form-control" type="number" step="any" min="0" max="2147483647" name="rentalRate" value="${result.rentalRate}" required><br> 
						Length<input class="form-control" type="number" name="length" min="0" max="2147483647" value="${result.length}" required><br>
						Replacement Cost<input class="form-control" type="number" step="any" min="0" max="2147483647" name="replacementCost" value="${result.replacementCost}" required><br>
						Rating<input class="form-control" type="text" name="rating" value="${result.rating}"><br>
						Special Features<input class="form-control" type="text" name="specialFeatures" value="${result.specialFeatures}"><br>
						<input class="btn btn-outline-success" type="submit" value="Submit"><br>
					</form>
				</div>
			</div>
		</div>
		</c:forEach>



<!-- THIS STARTS HTML AND CODE FOR GETTING FILM BY ID -->
		<c:if test="${result.title != null}">
			<div class="container" style="margin-top: 15px">
				<div class="row">
	<!-- START LEFT SIDE (ON WIDE SCREENS) OF FILM DISPLAY -->		
					<div class="col-sm">
						<h1><strong> ${result.title} </strong></h1>
						
						<h2>${result.description}</h2>
						
						<h3><span class="badge badge-dark"> ${result.rating} </span></h3>

						<c:if test="${result.filmActors != '[]'}">
						<div class="style__box shadow-lg">
							<strong>Staring:</strong><br>
							<c:forEach items="${result.filmActors}" var="actor">
								<span class="badge badge-secondary"> ${actor.firstName} ${actor.lastName} </span>
							</c:forEach>
						</div>
						</c:if>
						
						<c:if test="${result.specialFeatures != null}">
						<div class="style__box shadow-lg">
							<strong>Special Features:</strong><br>
							${result.specialFeatures}
						</div>
						</c:if>
						
						<c:if test="${result.category != ''}">
						<div class="style__box shadow-lg" style="margin-bottom: 20px">
							<strong>Category</strong><br> <span class="badge badge-info">
								${result.category} </span>
						</div>
						</c:if>
					</div>
					
	<!-- START RIGHT SIDE (ON WIDE SCREENS) OF FILM DISPLAY -->
					<div class="col-sm">
						<table class="table">
							<tr>
								<td class="desc shadow">Database Film ID</td>
								<td class="dec_c shadow">${result.id}</td>
							</tr>
							<tr>
								<td class="desc shadow">Release Year</td>
								<td class="dec_c shadow">${result.releaseYear}</td>
							</tr>
<%-- 							<tr>
								<td class="desc shadow">Language Id</td>
								<td class="dec_c shadow">${result.languageId}</td>
							</tr> --%>
							<tr>
								<td class="desc shadow">Language</td>
								<td class="dec_c shadow">${result.language}</td>
							</tr>
							<tr>
								<td class="desc shadow">Rental Duration</td>
								<td class="dec_c shadow">${result.rentalDuration}</td>
							</tr>
							<tr>
								<td class="desc shadow">Rental Rate</td>
								<td class="dec_c shadow">${result.rentalRate}</td>
							</tr>
							<tr>
								<td class="desc shadow">Replacement Cost</td>
								<td class="dec_c shadow">${result.replacementCost}</td>
							</tr>
							<tr>
								<td class="desc shadow">Length</td>
								<td class="dec_c shadow">${result.length}</td>
							</tr>
						</table>
					</div>
				</div>
			</div>

	<!-- BOTTOM BUTTONS TO EDIT OR DELETE -->	
			<div class="text-center">
				<div class="btn-group">
					<form action="delete.do" method="POST" class="bottom__buttons">
						<input type="hidden" name="filmIdDelete" value="${result.id }">
						<input class="btn btn-secondary" type="submit" value="Delete film">
					</form>
					<div>
						<button class="btn btn-secondary bottom__buttons" type="button"
							data-toggle="collapse" data-target="#collapseUpdateFilm"
							aria-expanded="false" aria-controls="collapseExample">
							Update film</button>
					</div>
				</div>
			</div>
			
		<!-- UPDATE FORM -->
			<div class="collapse" id="collapseUpdateFilm">
				<div class="card card-body">
					<form action="update.do" method="POST">
						<input type="hidden" name="id" value="${result.id}"> 
						Title <input class="form-control" type="text" name="title" value="${result.title}" required><br> 
						Description <input class="form-control" type="text" name="description" value="${result.description}"><br>
					 	Release Year <input class="form-control" type="number" name="releaseYear" min="0" max="2147483647" value="${result.releaseYear}" required><br> 
						Language Id<input class="form-control" type="number" name="languageId" min="1" max="6" value="${result.languageId}" required ><br>	
						Rental Duration<input class="form-control" type="number" min="0" max="2147483647" name="rentalDuration" value="${result.rentalDuration}" required><br>
						Rental Rate<input class="form-control" type="number" step="any" min="0" max="2147483647" name="rentalRate" value="${result.rentalRate}" required><br> 
						Length<input class="form-control" type="number" name="length" min="0" max="2147483647" value="${result.length}" required><br>
						Replacement Cost<input class="form-control" type="number" step="any" min="0" max="2147483647" name="replacementCost" value="${result.replacementCost}" required><br>
						Rating<input class="form-control" type="text" name="rating" value="${result.rating}"><br>
						Special Features<input class="form-control" type="text" name="specialFeatures" value="${result.specialFeatures}"><br>
						<input class="btn btn-outline-success" type="submit" value="Submit"><br>
					</form>
				</div>
			</div>
		</c:if>

	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>

