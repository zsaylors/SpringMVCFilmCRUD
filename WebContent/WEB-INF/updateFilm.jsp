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
   <form action="update.do" method="POST">
   	
     Title <input type="text" name="title" value="${result.title }"/><br>
     Description<input type="text" name="description" /><br>
     Release Years <input type="number" name="releaseYear" /><br>
     Language Id<input type="number" name="languageId" /><br>
     Rental Duration<input type="number" name="rentalDuration" /><br>
     Rental Rate<input type="number" name="rentalRate" /><br>
     Length<input type="number" name="length" /><br>
     Replacement Cost<input type="number" name="replacementCost" /><br>
     Rating<input type="text" name="rating" /><br> 
     Special Features<input type="text" name="specialFeatures" /><br>
     <input type="submit" value="Submit" /><br />
   </form>
 </body>
</html>