<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${(param.needsAuthorization == 'true') and (sessionScope.user ne 'admin') }">
	<html>
		<head>
			<style>body {background-color: #98FB98;font-family: Verdena, Arial, sans-serif;color: #006400;}</style>
		</head>
		<body>
			<h1>Access Denied</h1>
			<img src="site_graphics/denied.jpg">
		</body>
	</html>
</c:if>
<c:if test="${(param.needsAuthorization == 'false') or (sessionScope.user eq 'admin') }">
	<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="turtle.css">
		<title>${param.title}</title>
	</head>
	<body>
		<jsp:include page="header.jsp" />
		<div id="container">
			<c:set var="categories" value="${categories}" scope="request" />
			<jsp:include page="nav.jsp"/>
			<div id="mainContent">
				<h1>${param.title}</h1>
				<jsp:include page="content/${param.content}.jsp"/>
			</div>
		</div>
	</body>
	</html>
</c:if>