<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="header">
   <img src="site_graphics/turtleHeader.jpg">
   	<c:if test="${sessionScope.user != null}">
   		<p>Logged in as ${sessionScope.user}</p>
   	</c:if>
</div>
