<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="includes.jsp" />
	<title>Manage Sequences</title>
</head>
<body>
	<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">Confirm Delete</div>
				<div class="modal-body"><p>Are you sure you want to delete the sequence?</p></div>
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
			<p>To manage a sequence, choose an action in the dropdown menu.</p>
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Description</th>
						<th>View/Update/Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="sequence" items="${requestScope.sequences}">
						<tr>
							<td class="col-sm-2">${sequence.id}</td>
							<td>${sequence.description}</td>
							<td class="col-sm-1">
							<a href="/Controller?action=ViewSequence&id=${sequence.id}"><span
												class="glyphicon glyphicon-eye-open"></a>
							<a href="/Controller?action=SetupUpdateSequence&id=${sequence.id}"><span
												class="glyphicon glyphicon-edit"></a>
							<a data-toggle="modal" data-target="#confirm-delete"
									href="#" data-href="/Controller?action=DeleteSequence&id=${sequence.id}"><span
									class="glyphicon glyphicon-remove"></a>
							</td>
	
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<br /> <br /> <br />
	</div>

	<script>
		$('#confirm-delete').on('show.bs.modal', function(e) {
			$(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
		});
	</script>
</body>
</html>
