<!DOCTYPE html>
<html>
<head>
<jsp:include page="includes.jsp" />
<title>Add Sequence</title>
</head>
<body>
	<jsp:include page="nav.jsp" />
	<div class="container">
		<p>To add a sequence, provide a description of the sequence, the
			nucleotide string (consisting of A, C, G, and T bases), and then
			click 'Add'.</p>
		<form method="post" action="/Controller?action=AddSequence">
			<div class="form-group">
				<label for="description">Description:</label> <input type="text"
					id="description" name="description" class="form-control"
					placeholder="Enter description" required />
			</div>
			<div class="form-group">
				<label for="data">Sequence Data:</label> <input type="text"
					id="data" name="data" class="form-control" placeholder="Enter data"
					required />
			</div>
			<input type="submit" value="Add" class="btn btn-default"
				name="submit">
		</form>
	</div>
</body>
</html>
