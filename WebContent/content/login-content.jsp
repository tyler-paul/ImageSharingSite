<p>Enter a username and password and click Login to login to your account.</p>
<form method="post" action="UserController?action=Authenticate">
	<input type="hidden" name="referer" value="<?php echo $_SERVER['HTTP_REFERER']; ?>">
	<label for="username">Username:</label> <br />
	<input type="text" id="username" name="username" /><br />
	<label for="password">Password:</label> <br />
	<input type="password" id="password" name="password" /><br />
	<hr>
	<input type="submit" value="Login" name="submit">
</form>
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>

