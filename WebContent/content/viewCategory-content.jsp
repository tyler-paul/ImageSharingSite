<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>${category.name}</h1>
<c:forEach var="image" items="${requestScope.images}">
	<span class="imageBox">
		<table>
			<tr>
				<td>
				<div class="box">
					<a href="${initParam.domain}ImageController?action=View&id=${image.id}">
						<img src="${initParam.domain}images/thumbs/${image.id}.png"/>
					</a><br/>
					</div>
				</td>
				
			</tr>
			<tr>
				<td>
					<textarea rows="7" cols="30" readonly>${image.comment}</textarea>
				</td>
			</tr>
		</table>
	</span>
</c:forEach>             