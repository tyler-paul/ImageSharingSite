<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<p>Select a category to delete and then click Remove.</p>
<form method="post" action="CategoryController?action=DoRemove">

<c:forEach var="category" items="${requestScope.categories}">
	<input type="checkbox" value="${category.id}" name="todelete[]" />
	${category.name}<br/>
</c:forEach>
               
<hr>
<input type="submit" name="submit" value="Remove" />
</form>
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
