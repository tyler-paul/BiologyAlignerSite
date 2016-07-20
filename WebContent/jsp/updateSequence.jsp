<!DOCTYPE html>
<html>
<head>
<jsp:include page="includes.jsp" />
<title>Update Sequence</title>
</head>
<body>
	<jsp:include page="nav.jsp" />
	<div class="container">
		<p>To update a sequence, provide an optional new description of the sequence, an optional new
			nucleotide string (consisting of A, C, G, and T bases), and then
			click 'Update'.</p>
		<form method="post" action="/Controller?action=UpdateSequence">
			<div class="form-group">
				<label for="description">New Description:</label>
				<input type="text"
					id="description" name="newDescription" value="${oldDescription}" class="form-control" />
			</div>
			<div class="form-group">
				<label for="data">New Sequence Data:</label>
				<!-- <input type="text" id="data" name="newData" value="${oldData}" class="form-control"/>  -->
				<textarea id="data" name="newData" rows="8" class="form-control">${oldData}</textarea>
			</div>
			<input type="hidden" name="id" value="${id}">
			<input type="submit" value="Update" class="btn btn-default"
				name="submit">
		</form>
	</div>
</body>
</html>
