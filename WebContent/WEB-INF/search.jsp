<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
 <head>
   <meta charset="UTF-8">
   <title>IMDb</title>
 </head>
 <body>
 <c:forEach items ="${results}" var = "result"> 
  	<ul>
  	<li> Title:${result.title} </li>
  	<li> Description:${result.description} </li>
   	<li> Release Year:${result.releaseYear} </li>
   	<li> Language Id:${result.languageId} </li>
   	<li> Rental Duration:${result.rentalDuration} </li>
   	<li> Rental Rate:${result.rentalRate} </li>
   	<li> Length:${result.length} </li>
   	<li> Replacement Cost:${result.replacementCost} </li>
   	<li> Rating:${result.rating} </li>
   	<li> Special Features:${result.specialFeatures} </li>
  	</ul>
 </c:forEach>
 
  
   <%-- <p>The result is: ${result.title}</p> --%>
   <ul>
   	<li> Description:${result.description} </li>
   	<li> Description:${result.releaseYear} </li>
   	<li> Description:${result.languageId} </li>
   	<li> Description:${result.rentalDuration} </li>
   	<li> Description:${result.rentalRate} </li>
   	<li> Description:${result.length} </li>
   	<li> Description:${result.replacementCost} </li>
   	<li> Description:${result.rating} </li>
   	<li> Description:${result.specialFeatures} </li>
   	<li> Description:${result.filmActors} </li>
   	<li> Description:${result.category} </li>
   	
   	
   <a href="addVideo.html">Add Video</a>
   <form action="delete.do" method="POST">
   	<input type="hidden" name="filmIdDelete" value="${result.id }" />
   <input type="submit" value="Delete film" /><br />
   </form>
   
   <form action="getfilmtoupdate.do" method="GET">
    	<input type="hidden" name="filmId" value="${result.id }" />
    <input type="submit" value="Update film" /><br />
   </form>
   </ul>
 </body>
</html>

