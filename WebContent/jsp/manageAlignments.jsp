<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="includes.jsp" />
<script src="/assets/script.js" type="text/javascript"></script>
<title>Manage Alignments</title>
</head>
<body>
	<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">Confirm Delete</div>
				<div class="modal-body"><p>Are you sure you want to delete the alignment?</p></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a class="btn btn-danger btn-ok">Delete</a>
				</div>
			</div>
		</div>
	</div>
	
	<div id="container">
		<jsp:include page="nav.jsp" />
		<div class="container">
			<p>To manage an alignment, choose an action in the dropdown menu.</p>
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Type</th>
						<th>Description</th>
						<th>Local/Global</th>
						<th>Status</th>
						<th>View/Update/Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="alignment" items="${requestScope.alignments}">
						<c:if test="${alignment.discriminator eq 's'}">
							<tr>
								<td>${alignment.id}</td>
								<td>Single Alignment</td>
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
								<td class="col-md-1">
									<a href="/Controller?action=ViewSingleAlignment&id=${alignment.id}"><span
												class="glyphicon glyphicon-eye-open"></a>
									<a href="/Controller?action=SetupUpdateAlignment&id=${alignment.id}"><span
												class="glyphicon glyphicon-edit"></a>
									<a data-toggle="modal" data-target="#confirm-delete"
												href="#" data-href="/Controller?action=DeleteAlignment&id=${alignment.id}"><span
												class="glyphicon glyphicon-remove"></a>
								</td>
							</tr>
						</c:if>
						<c:if test="${alignment.discriminator eq 'SequenceAlignment'}">
							<tr>
								<td>${alignment.id}</td>
								<td>Multiple Alignment</td>
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
								<td class="col-md-1">
									<a href="/Controller?action=ViewMultipleAlignment&id=${alignment.id}"><span
												class="glyphicon glyphicon-eye-open"></a>
									<a href="/Controller?action=SetupUpdateAlignment&id=${alignment.id}"><span
												class="glyphicon glyphicon-edit"></a>
									<a data-toggle="modal" data-target="#confirm-delete"
												href="#" data-href="/Controller?action=DeleteAlignment&id=${alignment.id}"><span
												class="glyphicon glyphicon-remove"></a>
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<br/><br/><br/>
	</div>
	
	<script>
		$('#confirm-delete').on('show.bs.modal', function(e) {
			$(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
		});
	</script>
</body>
</html>
