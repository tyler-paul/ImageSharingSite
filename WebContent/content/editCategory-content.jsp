<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<p>To edit an image, change the category and/or edit the comment and click Edit.</p>
<hr>
<c:forEach var="category" items="${requestScope.categories}">
		<form method="post" enctype="multipart/form-data" action="CategoryController?action=DoEdit">
			<input type="hidden" name="id" value=${category.id}>
			<table class="commentTable">
				<tr>
					<td>${category.name}</td>
					<td>
						<label id="category">New category name:</label><br />
						<input type="text" id="category" name="category">
					</td>
					<td>
						<img src="${initParam.domain}categories/thumbs/${category.id}.png">
					</td>
					<td>
						<label id="filename">New image:</label><br />
	                	<input type="file" id="filename" name="filename" />
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
