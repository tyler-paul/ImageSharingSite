<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Categories</h1>
<c:forEach var="category" items="${requestScope.categories}">
	<span class="imageBox">
		<table>
			<tr>
				<td>
					<div class="box">
						<a href="${initParam.domain}CategoryController?action=View&id=${category.id}"><br/>
							<img src="${initParam.domain}categories/thumbs/${category.id}.png"/>
						</a>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<h3>${category.name}</h3>
				</td>
			</tr>
		</table>
	</span>
</c:forEach>

<h1>Most Recent Images</h1>
<c:forEach var="image" items="${requestScope.latestImages}">
	<a href="${initParam.domain}ImageController?action=View&id=${image.id}">
		<span class="boxFlow">
			<img src="${initParam.domain}images/thumbs/${image.id}.png"/>
		</span>
	</a>
</c:forEach>
<br />
<br />