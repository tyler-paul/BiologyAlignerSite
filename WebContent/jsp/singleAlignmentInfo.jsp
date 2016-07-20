<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<label>Taxons:</label><br/>
<ul class="list-group">
<c:forEach var="part" items="${parts}">
	<li class="list-group-item">${part.taxon} [${part.startPos}, ${part.endPos})</li>
</c:forEach>
</ul>

<table class="table table-striped table-bordered">
	<tr>
		<td>Matches:</td>
		<td>${numMatches}</td>
	</tr>
	<tr>
		<td>Mismatches:</td>
		<td>${numMismatches}</td>
	</tr>
	<tr>
		<td>Insertions:</td>
		<td>${numInsertions}</td>
	</tr>
	<tr>
		<td>Deletions:</td>
		<td>${numDeletions}</td>
	</tr>
	<tr>
		<td>Score:</td>
		<td>${score}</td>
	</tr>
</table>