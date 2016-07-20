<!DOCTYPE html>
<html>
<head>
	<jsp:include page="includes.jsp" />
	<title>Login</title>
</head>
<body>
	<div id="container">
		<jsp:include page="nav.jsp" />
		
		<div class="container">
			<p>Please login.</p>
			<form method="post" action="j_security_check">
				<div class="form-group">
					<label for="j_username">Username:</label>
					<input type="text" class="form-control" name="j_username" placeholder="Enter username">
				</div>
				<div class="form-group">
					<label for="j_password">Password:</label>
					<input type="password" class="form-control" name="j_password" placeholder="Enter password">
				</div>
				<input type="submit" value="Login" class="btn btn-default" name="submit">
			</form>
		</div>
	</div>
</body>
</html>