<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<nav class="navbar navbar-default navbar-custom">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/jsp/index.jsp">Biology Aligner</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"><span
					class="glyphicon glyphicon-minus">&#8201Sequences<span
						class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="/jsp/addSequence.jsp"><span
							class="glyphicon glyphicon-plus">&#8201Add&#8201Sequence</a></li>
					<li><a href="/Controller?action=SetupManageSequences"><span
							class="glyphicon glyphicon-eye-open">&#8201Manage&#8201Sequences</a></li>
				</ul></li>
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"><span
					class="glyphicon glyphicon-tasks">&#8201Alignments<span
						class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="/Controller?action=SetupAlignSequence"><span
							class="glyphicon glyphicon-plus">&#8201Generate&#8201Alignment</a></li>
					<li><a href="/Controller?action=SetupManageAlignments"><span
							class="glyphicon glyphicon-eye-open">&#8201Manage&#8201Alignments</a></li>
				</ul></li>
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"><span
					class="glyphicon glyphicon-tree-conifer">&#8201Trees<span
						class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="/Controller?action=SetupCreateTree"><span
							class="glyphicon glyphicon-plus">&#8201Generate&#8201Tree</a></li>
				</ul></li>
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-book"></span>&#8201Tutorials<span
						class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="/jsp/findingDataTutorial.jsp"><span
							class="glyphicon glyphicon-info-sign">&#8201Finding&#8201Data</a></li>
					<li><a href="/jsp/alignmentTutorial.jsp"><span
							class="glyphicon glyphicon-tasks">&#8201Alignment&#8201Tutorial</a></li>
					<li><a href="/jsp/treeTutorial.jsp"><span
							class="glyphicon glyphicon-tree-conifer">&#8201Tree&#8201Tutorial</a></li>
				</ul></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<%
				if (request.isUserInRole("admin")) {
			%>
			<li><a href="/Controller?action=ViewAccessRequests"><span
					class="glyphicon glyphicon-user"></span>&#8201Confirm Users</a></li>
			<%
				}
			%>
			<%
				if (!request.isUserInRole("regular_user")) {
			%>
			<li><a href="/jsp/requestAccess.jsp"><span
					class="glyphicon glyphicon-user"></span>&#8201Request Access</a></li>
			<li><a href="/jsp/userLogin.jsp"><span
					class="glyphicon glyphicon-user"></span>&#8201Login</a></li>
			<%
				}
				else {
			%>
			<li><a href="/Controller?action=Logout"><span
					class="glyphicon glyphicon-off"></span>&#8201Logout</a></li>
			<%
				}
			%>
		</ul>
	</div>
</nav>