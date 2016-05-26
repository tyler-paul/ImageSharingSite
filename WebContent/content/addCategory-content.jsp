<p>Enter a category name, select an image file to represent the
	category, and click Add to add a category.</p>

<form enctype="multipart/form-data" method="post"
	action="CategoryController?action=DoAdd">
	<label for="Category">Category:</label> <br />
	<input type="text" id="categoryName" name="categoryName" /><br /> 
	<input type="file" id="fileName" name="fileName" />
	<hr>
	<input type="submit" value="Add" name="submit">
</form>