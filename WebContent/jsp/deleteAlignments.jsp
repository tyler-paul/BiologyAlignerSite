<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="includes.jsp" />
<title>Delete Alignments</title>
</head>
<body>
	<div id="container">
		<jsp:include page="nav.jsp" />
		<div class="container">
			<p>To delete an alignment, click the corresponding row in the table.</p>
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Type</th>
						<th>Description</th>
						<th>Local/Global</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="alignment" items="${requestScope.alignments}">
						<tr 
						<c:if test="${alignment.status ne 'Pending'}">
						onclick="window.document.location='/Controller?action=DeleteAlignment&id=${alignment.id}';"
						</c:if>
						>
							<td>${alignment.id}</td>
							<c:choose>
								<c:when test="${alignment.discriminator eq 's'}">
									<td>Single Alignment</td>
								</c:when>
								<c:otherwise>
									<td>Multiple Alignment</td>
								</c:otherwise>
							</c:choose>
							<td>${alignment.description}</td>
							<c:choose>
								<c:when test="${alignment.isLocal eq true}">
									<td>Local</td>
								</c:when>
								<c:otherwise>
									<td>Global</td>
								</c:otherwise>
							</c:choose>
							<td>${alignment.status}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
