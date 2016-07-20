<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="includes.jsp" />
<script src="/assets/alignSequences.js" type="text/javascript"></script>
<title>Align Sequences</title>
</head>
<body>
	<div id="container">
		<jsp:include page="nav.jsp" />
		<div class="container">
			<p>To align a sequence, first Choose if you want to do a Single or Multiple alignment. Then choose the sequences to align and change the taxon names if desired. Provide a description for the alignment. Then choose between a local alignment (to find regions of high similarity) or global alignment (to attempt to align the entire sequence). Lastly, alter the scoring matrix if desired to fine tune the behavior of the alignment algorithm.</p>
			<form method="post" action="/Controller?action=AlignSequence">
				<label>Alignment Type:</label> <br />
				<div class="radio">
					<label><input type="radio" class="alignment" name="alignment" value="single">Single</label>
				</div>
				<div class="radio">
					<label><input type="radio" class="alignment" name="alignment" value="multiple">Multiple</label>
				</div>
				<div id="singleAlignmentInfo">
					<div class="form-group">
						<label for="sequence1ID">Sequence 1:</label>
						<select class="form-control" name="sequence1ID" id="sequence1ID">
							<c:forEach var="sequence" items="${requestScope.sequences}">
								<option value="${sequence.id}">[Sequence #${sequence.id}]: ${sequence.description}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="sequenceID">Sequence 2:</label>
						<select class="form-control" name="sequence2ID" id="sequence2ID">
							<c:forEach var="sequence" items="${requestScope.sequences}">
								<option value="${sequence.id}">[Sequence #${sequence.id}]: ${sequence.description}</option>
							</c:forEach>
						</select>
					</div>
					<label>Enter names of the taxons:</label> <br />
					<table id="taxonNameTableSingle" class="table table-bordered">
						<thead>
							<tr>
								<th>ID</th>
								<th>Taxon name</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div id="multipleAlignmentInfo">
					<div class="form-group">
						<label for="multipleSequences">Sequences to align:</label> <br />
						<select class="form-control" id="sequencesToSelect" name="multipleSequences" multiple>
							<c:forEach var="sequence" items="${requestScope.sequences}">
								<option value="${sequence.id}">[Sequence #${sequence.id}]: ${sequence.description}</option>
							</c:forEach>
						</select>
					</div>
					<label>Enter names of the taxons:</label> <br />
					<table id="taxonNameTable" class="table table-bordered">
						<thead>
							<tr>
								<th>ID</th>
								<th>Taxon name</th>
							</tr>
						</thead>
					</table>
				</div>
				
				<br />
				<div class="form-group">
					<label>Description:</label>
					<input type="text" class="form-control" name="description" placeholder="Enter description">
				</div>
				
				<div class="form-group">
					<label for="alignmentType">Alignment Type:</label>
					<div class="radio">
						<label><input type="radio" name="alignmentType" value="local">Local</label>
					</div>
					<div class="radio">
						<label><input type="radio" name="alignmentType" value="global">Global</label>
					</div>
				</div>
				
				<label>Scoring Matrix:</label>
				<table class="table table-bordered">
					<thead>
					<tr>
						<td class="header-cell"></td>
						<th class="header-cell">A</th>
						<th class="header-cell">C</th>
						<th class="header-cell">G</th>
						<th class="header-cell">T</th>
						<th class="header-cell">-</th>
					</tr>
					</thead>
					<tbody>
					<tr>
						<th class="header-cell">A</th>
						<td><input class="form-control" type="number" name="tAA" value="5"></td>
						<td><input class="form-control" type="number" name="tAC" value="1"></td>
						<td><input class="form-control" type="number" name="tAG" value="2"></td>
						<td><input class="form-control" type="number" name="tAT" value="1"></td>
						<td><input class="form-control" type="number" name="tA-" value="3"></td>
					</tr>
					<tr>
						<th class="header-cell">C</th>
						<td></td>
						<td><input class="form-control" type="number" name="tCC" value="5"></td>
						<td><input class="form-control" type="number" name="tCG" value="3"></td>
						<td><input class="form-control" type="number" name="tCT" value="2"></td>
						<td><input class="form-control" type="number" name="tC-" value="4"></td>
					</tr>
					<tr>
						<th class="header-cell">G</th>
						<td></td>
						<td></td>
						<td><input class="form-control" type="number" name="tGG" value="5"></td>
						<td><input class="form-control" type="number" name="tGT" value="2"></td>
						<td><input class="form-control" type="number" name="tG-" value="2"></td>
					</tr>
					<tr>
						<th class="header-cell">T</th>
						<td></td>
						<td></td>
						<td></td>
						<td><input class="form-control" type="number" name="tTT" value="5"></td>
						<td><input class="form-control" type="number" name="tT-" value="1"></td>
					</tr>
					<tr>
						<th class="header-cell">-</th>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					</tbody>
				</table>
				<hr>
				<input type="submit" value="Align" class="btn btn-default" name="submit">
			</form>
		</div>
	</div>
</body>
</html>
