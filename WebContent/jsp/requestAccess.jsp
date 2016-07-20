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
			<p>Please fill out this form to request access to the site.</p>
			<form method="post" action="/AccessRequestServlet">
				<div class="form-group">
					<label for="username">Username:</label>
					<input type="text" class="form-control" name="username" required>
				</div>
				<div class="form-group">
					<label for="password">Password:</label>
					<input type="password" class="form-control" name="password" required>
				</div>
				<div class="form-group">
					<label for="email">Email:</label>
					<input type="text" class="form-control" name="email" required>
				</div>
				<div class="form-group">
					<label for="description">Describe why you want access to the site:</label>
					<input type="text" class="form-control" name="description">
				</div>
				<input type="submit" value="Submit" class="btn btn-default" name="submit">
			</form>
		</div>
	</div>
</body>
</html>