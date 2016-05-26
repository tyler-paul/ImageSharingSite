<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<p>Select a file and image category, write a comment, and click Add to add an image.</p>

<form enctype="multipart/form-data" method="post" action="ImageController?action=DoAdd">
<input type="hidden" name="MAX_FILE_SIZE" value="30000000" />
<label for="category">Category:</label><br />
<select name="category" id="category">
	<c:forEach var="aCategory" items="${requestScope.categories}">
		<option value="${aCategory.id}">${aCategory.name}</option>
	</c:forEach>
</select><br />
<label for="comment">Comment:</label><br />
<textarea id="comment" name="comment"></textarea><br />
<input type="file" id="filename" name="filename" />
<hr>
<input type="submit" value="Add" name="submit">
</form>


<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>

