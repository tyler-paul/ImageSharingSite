<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<p>To edit an image, change the category and/or edit the comment and click Edit.</p>
<hr>
<c:forEach var="image" items="${requestScope.images}">
	<form method="post" action="ImageController?action=DoEdit">
		<input type="hidden" name="id" value="${image.id}">
		<table class="commentTable">
			<tr>
				<td>${image.category}</td>
				<td>
					<select name="categories">'
						<c:forEach var="category" items="${requestScope.categories}">
							<option
								<c:if test="${category.id == image.categoryId}">
									selected = "selected"
								</c:if>
								value="${category.id}"> ${category.name} </option>
						</c:forEach>
					</select><br/>
				</td>
				<td>
					<img src="${initParam.domain}images/thumbs/${image.id}.png">
				</td>
				<td>
					<textarea name="comment" rows="7" cols="30">${image.comment}</textarea><br />
				</td>
				<td class="editButton">
					<input type="submit" name="submit" value="Edit" />
				</td>
			</tr>
		</table>
		<hr>
	</form>
</c:forEach>

<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
