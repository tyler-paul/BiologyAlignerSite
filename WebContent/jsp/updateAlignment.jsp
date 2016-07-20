<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="includes.jsp" />
<title>Update Alignment</title>
</head>
<body>
	<jsp:include page="nav.jsp" />
	<div class="container">
		<p>To update an alignment, provide an optional new description of the alignment, optional new
			names for the taxons, and then click 'Update'.</p>
		<form method="post" action="/Controller?action=UpdateAlignment">
			<div class="form-group">
				<label for="description">New Description:</label>
				<input type="text"
					id="description" name="newDescription" value="${oldDescription}" class="form-control" />
			</div>
			<div class="form-group">
				<label>New Taxon Names:</label>
				<c:forEach var="oldTaxonName" items="${requestScope.oldTaxonNames}">
					<input type="text" name="newTaxonNames" value="${oldTaxonName}" class="form-control"/>
				</c:forEach>
			</div>
			<input type="hidden" name="id" value="${id}">
			<input type="submit" value="Update" class="btn btn-default"
				name="submit">
		</form>
	</div>
</body>
</html>
