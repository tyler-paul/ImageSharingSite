<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="nav">
	<h2>Navigation</h2>
	<p>
		<a href="${initParam.domain}Index">Home</a><br /> 
		<c:if test="${sessionScope.user eq 'admin'}">
			<a href="${initParam.domain}CategoryController?action=Add">Add Category</a><br /> 
			<a href="${initParam.domain}ImageController?action=Add">Add Image</a><br /> 
			<a href="${initParam.domain}CategoryController?action=Remove">Remove Category</a><br />
			<a href="${initParam.domain}ImageController?action=Remove">Remove Image</a><br />
			<a href="${initParam.domain}CategoryController?action=Edit">Edit Category</a><br />
			<a href="${initParam.domain}ImageController?action=Edit">Edit Images</a><br />
		</c:if>
		<a href="${initParam.domain}UserController?action=Add">Sign Up</a><br /> 
		<a href="${initParam.domain}UserController?action=Login">Login</a><br />
		<c:if test="${sessionScope.user != null}">
			<a href="${initParam.domain}UserController?action=Logout">Logout</a><br /> 
		</c:if>
	</p>
	<h2>Categories</h2>
	<p>
		<c:forEach var="category" items="${requestScope.categories}">
			<a href="${initParam.domain}CategoryController?action=View&id=${category.id}">${category.name}</a><br/>
		</c:forEach>
	</p>
</div>
