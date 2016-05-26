<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="image">
	<img src="${initParam.domain}images/${image.id}.png"/><br/>
</div>
<div class="imageComment">
	<span class="commentUsername">(uploaded by admin)</span>
	<span class="commentDate">${image.time}</span>
    <br />
	<p class="commentText">${image.comment}</p>
</div>

<h3>Comments:</h3>
<div id="commentPane">
	<c:forEach var="comment" items="${requestScope.comments}">
		<div class="imageComment">
			<span class="commentUsername">
				${comment.user}
			</span>
			<span class="commentDate">
				${comment.time}
			</span>
			<br />
			<p>${comment.comment}</p>
		</div>
	</c:forEach>
</div>
            

<c:if test="${sessionScope.user != null}">
	<a name="bottom"></a>
	<form method="post" action="CommentController?action=DoAdd">
		<input type="hidden" id="picId" name="picId" value="${requestScope.image.id}">
		<div class="reply">
			<div class="userLabel">
				<label for="comment">Reply (as user ${sessionScope.user}):</label><br />
			</div>
			<textarea class="imageReply" id = "comment" name="comment"></textarea><br />
			<input type="submit" value="Reply" name="submit">
		</div>
	</form>
</c:if>
<c:if test="${sessionScope.user == null}">
	<a class="loginUrl" href="${initParam.domain}login.jsp">Login to enter a comment</a>
</c:if>

