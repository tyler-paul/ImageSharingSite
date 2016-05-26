<p>Enter a username and password and click Create to create an account.</p>

<form method="post" action="UserController?action=Add">
	<label for="username">Username:</label> <br /> 
	<input type="text" id="username" name="username" /><br />
	<label for="password">Password:</label> <br />
	<input type="password" id="password" name="password" /><br />
	<label for="confirmPassword">Confirm Password:</label> <br /> 
	<input type="password" id="confirmPassword" name="confirmPassword" /><br />
	<hr>
	<input type="submit" value="Create" name="submit">
</form>