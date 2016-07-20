<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<label>Taxons:</label><br/>
<ul class="list-group">
<c:forEach var="part" items="${parts}">
	<li class="list-group-item">${part.taxon} [${part.startPos}, ${part.endPos})</li>
</c:forEach>
</ul>