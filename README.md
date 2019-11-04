## Spring MVC Film C.R.U.D. Project

[Click to visit IMDb2](http://3.132.170.184:8080/MVCFilmSite)


### Overview
The purpose of this project is to have a web enabled app that can access a database related to films. So we decided to name it IMDb2. The app needed to be able to Create, Read, Update, and Delete (CRUD) entries in the database.

The first page the app takes you to is the index.html which allows the user to search for films by Id or Keyword in the respective forms. There is also a link that adds a film object to the database via a controller.

Once a user looks up a film, the film info will display. The user can also delete the film or update its data fields. If an error is thrown by invalid input or trying to delete a default database film, the user will be shown an error page.  If updated successfully, the user will be brought to a success page.  The page will show the films title and id so the user can easily navigate back to it.  The keyword search returns multiple results in a list, and it displays all the results on the page.  Users can update films from this list.

To add a film the user has to click "add a video" button on the index or homepage. Then they fill out the form with the related fields for a film.


### Technologies
* *SpringToolSuite4* - This was the IDE that we wrote the code in.

* *MySQLWorkbench* - This was an alternative to terminal in order to access the database.

* *Bootstrap* - Provides a nice template to make the html display nicely on both a desktop and mobile device.

* *CSS* - Used to make some minor changes to margins, padding, colors, etc, to aid in interface design.

* *Database Accessor Objects* - Additional DAO's were needed to be created in order to create, update, and delete films.  Some reorganization was needed from the prior weeks project in order for the program to work properly.

* *RequestMapping and Controllers* - This was an important addition to the project in order to update the database and reroute to an appropriate page.  `mv.addObject("result", film)` was used to add and object to a .jsp page, and `mv.setViewName("WEB-INF/search.jsp")` was used to redirect to the page.

* *JavaServer Pages Standard Tag Library (JSTL)* - In order to print a list on the .jsp page, a <c:foreach> tag was needed.  Also, there were a few instances were <c:if> tags were utilized.

### Lessons Learned
A big lesson learned was how film controllers worked.  The project emphasised the difference between `GET` and `SET`.  It also allowed us to understand ModelAndView better.

Auto generated IDs can be hidden sometimes.

It helps to do a Gradle refresh and project refresh to make sure all changes take effect.
