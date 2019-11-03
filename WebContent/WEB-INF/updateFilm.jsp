<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
 <head>
   <meta charset="UTF-8">
   <title>Title</title>
 </head>
 <body>
  ${result.id}
   <form action="update.do" method="POST">
   	<input type="hidden" name="id" value="${result.id}" />
     Title <input type="text" name="title" value="${result.title}" required/><br>
     Description<input type="text" name="description" value="${result.description}"/><br>
     Release Year <input type="number" name="releaseYear" value="${result.releaseYear}" required/><br>
     Language Id<input type="number" name="languageId" value="${result.languageId}" required/><br>
     Rental Duration<input type="number" name="rentalDuration" value="${result.rentalDuration}" required/><br>
     Rental Rate<input name="rentalRate" value="${result.rentalRate}" required/><br>
     Length<input type="number" name="length" required value="${result.length}" required/><br>
     Replacement Cost<input name="replacementCost" value="${result.replacementCost}" required/><br>
     Rating<input type="text" name="rating" value="${result.rating}" /><br> 
     Special Features<input type="text" name="specialFeatures" value="${result.specialFeatures}"/><br>
     <input type="submit" value="Submit" /><br />
   </form>
 </body>
</html>
 