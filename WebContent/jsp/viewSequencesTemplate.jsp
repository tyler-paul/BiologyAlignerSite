<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="includes.jsp" />
<script>
	var sequences = "${sequences}".split(", ");
	if (sequences.length > 1) {
		sequences[0]=sequences[0].substring(1, sequences[0].length);
		sequences[sequences.length-1]=sequences[sequences.length-1].substring(0, sequences[sequences.length-1].length-1);
	}
</script>
<script src="/assets/script.js" type="text/javascript"></script>
<script src="/assets/viewerScript.js" type="text/javascript"></script>
<title>${param.title}</title>
</head>
<body onload="setupTable()">
	<div id="container">
		<jsp:include page="nav.jsp" />
		<div class="container">
			<label>Description:</label><br/>
				<ul class="list-group"><li class="list-group-item">${description}</li></ul>
			
			<jsp:include page="${param.content}"/>
			<br/>
			
			<table id="viewerTable">
			</table>
			
			<div class="row">
				<div class="form">
					<div class="col-sm-6">
						<table>
							<tr>
								<td><label>Start Position:</label></td>
								<td><input class="form-control" type="number" id="startPos" value="0"></td>
							</tr>
							<tr>
								<td><label>End Position:</label></td>
								<td><input class="form-control" type="number" id="endPos" value="19"></td>
							</tr>
							<tr>
								<td><label>Number of Columns:</label></td>
								<td><input class="form-control" type="number" id="numColumns" value="20"></td>
							</tr>
							<tr>
								<td></td>
								<td><button class="btn btn-default" id="updateButton">Update</button></td>
							</tr>
						</table>
					</div>
				</div>
				<div class="form">
					<div class="col-sm-6">
						<table>
							<tr>
								<td><label>Slide Amount:</label></td>
								<td><input class="form-control" type="number" id="slideAmount" value="1"></td>
							</tr>
							<tr>
								<td><button class="btn btn-default" id="slideLeftButton">Slide Left</button></td>
								<td><button class="btn btn-default" id="slideRightButton">Slide Right</button></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
