<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="includes.jsp" />
<title>Setup Outgroup</title>
</head>
<body>
	<div id="container">
		<jsp:include page="nav.jsp" />
		<div class="container">
			<p>You must select an outgroup in order to root the phylogeny tree. The outgroup should be chosen as the most distant taxon. A title for the tree can also be given.</p>
			<form method="post" action="/Controller?action=CreateTree&id=${alignmentID}">
				<div class="form-group">
					<label for="title">Title:</label>
					<input class="form-control" type="text" id="title" name="title" placeholder="Enter title" />
				</div>
				<div class="form-group">
					<label for="outgroup">Outgroup:</label>
					<select class="form-control" name="outgroup">
						<c:forEach var="taxonName" items="${requestScope.taxonNames}">
							<option value="${taxonName}">${taxonName}</option>
						</c:forEach>
					</select>
				</div>
				<input type="submit" value="Create Tree" class="btn btn-default" name="submit">
			</form>
		</div>
	</div>
</body>
</html>
