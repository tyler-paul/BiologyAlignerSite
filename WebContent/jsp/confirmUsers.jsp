<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="includes.jsp" />
	<title>Confirm User Access Requests</title>
</head>
<body>
	<div id="container">
		<jsp:include page="nav.jsp" />
		<div class="container">
			<p>To confirm a user account, click the corresponding row in the table.</p>
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>User</th>
						<th>Email</th>
						<th>Description</th>
						<th>Confirm/Deny</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="accessRequest" items="${requestScope.requests}">
							<td>${accessRequest.username}</td>
							<td>${accessRequest.email}</td>
							<td>${accessRequest.description}</td>
							<td class="col-md-1">
									<a href="/Controller?action=ConfirmAccessRequest&id=${accessRequest.id}"><span
												class="glyphicon glyphicon-ok"></a>
									<a href="/Controller?action=DenyAccessRequest&id=${accessRequest.id}"><span
												class="glyphicon glyphicon-remove"></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>