<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<p>Check all images that you want to delete and then click Remove.</p>
<form method="post" action="ImageController?action=DoRemove">
           
<c:forEach var="image" items="${requestScope.images}">
	<input type="checkbox" value="${image.id}" name="todelete[]" />
	<img src="${initParam.domain}/images/thumbs/${image.id}.png"/><br />
</c:forEach>
               
<hr>
<input type="submit" name="submit" value="Remove" />
</form>
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>

