<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="includes.jsp" />
<title>Delete Sequences</title>
</head>
<body>
	<div id="container">
		<jsp:include page="nav.jsp" />
		<div class="container">
			<p>To delete a sequence, click the corresponding row in the table.</p>
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Description</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="sequence" items="${requestScope.sequences}">
					<tr onclick="window.document.location='/Controller?action=DeleteSequence&id=${sequence.id}';">
						<td>${sequence.id}</td>
						<td>${sequence.description}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
