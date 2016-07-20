<!DOCTYPE html>
<html>
<head>
<jsp:include page="includes.jsp" />
<title>View Phylogeny Tree</title>
</head>
<body>
	<div id="container">
		<jsp:include page="nav.jsp" />
		<div class="container">
			<div class="panel panel-primary">
				<div class="panel-heading">Genetic Tree</div>
				<div class="panel-body">
					<img src="${treeFileName}" alt="Phylogeny Tree">
				</div>
			</div>
		</div>
	</div>
</body>
</html>
